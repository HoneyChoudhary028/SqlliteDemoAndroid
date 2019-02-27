package com.example.rudra.sqlliteroomdatabaseexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), VERTICAL, false);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerview.getContext(), linearLayoutManager.getOrientation());
        recyclerview.addItemDecoration(itemDecoration);


        getContact();

    }

    private void getContact() {
        class GetContacts extends AsyncTask<Void, Void, List<Contact>> {

            @Override
            protected List<Contact> doInBackground(Void... voids) {
                List<Contact> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .contactDAO()
                        .getContacts();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                super.onPostExecute(contacts);
                ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
                recyclerview.setAdapter(adapter);
            }
        }

        GetContacts gc = new GetContacts();
        gc.execute();
    }
}
