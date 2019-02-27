package com.example.rudra.sqlliteroomdatabaseexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDetailsActivity extends AppCompatActivity {



    private EditText editTextName, editTextEmail, editTextAddress, editTextPhone;
    private Button button_save, button_show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_details);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);

        button_show = findViewById(R.id.button_show);

        button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveContact();

            }
        });


        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveContact() {

        final String name = editTextName.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String address = editTextAddress.getText().toString();
        final String phone = editTextPhone.getText().toString();

        //creating a task
        Contact contact = new Contact();

        contact.setName(name);
        contact.setEmail(email);
        contact.setAddress(address);
        contact.setPhoneNumber(phone);


        SaveContact sc = new SaveContact(contact);
        sc.execute();
    }

    class SaveContact extends AsyncTask<Void, Void, Void> {

        private Contact contact;

        SaveContact(Contact contact) {
            this.contact = contact;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .contactDAO()
                    .insert(contact);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }



}


