package com.example.rudra.sqlliteroomdatabaseexample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextAddress,editTextPhone;
    private CheckBox checkBoxFinished;
    private Button button_update,button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        editTextName=findViewById(R.id.editTextName);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextAddress=findViewById(R.id.editTextAddress);
        editTextPhone=findViewById(R.id.editTextPhone);

        button_update=findViewById(R.id.button_update);
        button_delete=findViewById(R.id.button_delete);

        final Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        loadContact(contact);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContact(contact);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(UpdateContactActivity.this);
                builder.setTitle("Are you want to delete this");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteContact(contact);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

    }

    private void loadContact(Contact contact) {
        editTextName.setText(contact.getName());
        editTextEmail.setText(contact.getEmail());
        editTextAddress.setText(contact.getAddress());
        editTextPhone.setText(contact.getPhoneNumber());
        //checkBoxFinished.setChecked(contact.isFinished());
    }

    private  void updateContact(final Contact contact){
        final String name=editTextName.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        final String address=editTextAddress.getText().toString().trim();
        final String phone=editTextPhone.getText().toString().trim();

        contact.setName(name);
        contact.setEmail(email);
        contact.setAddress(address);
        contact.setPhoneNumber(phone);



        UpdateContact ut=new UpdateContact(contact);
        ut.execute();

    }

    class UpdateContact extends AsyncTask<Void, Void, Void>{

        private Contact contact;

        public UpdateContact(Contact contact) {
            this.contact = contact;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                    .contactDAO()
                    .update(contact);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(UpdateContactActivity.this, MainActivity.class));
        }
    }
    public void deleteContact( final Contact contact){

       /* DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                .contactDAO()
                .delete(contact);*/
       class  DeleteContact extends AsyncTask<Void,Void,Void>{

           @Override
           protected Void doInBackground(Void... voids) {
               DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                       .contactDAO()
                       .delete(contact);
               return null;
           }

           @Override
           protected void onPostExecute(Void aVoid) {
               super.onPostExecute(aVoid);
               Toast.makeText(getApplicationContext(), " contact Deleted", Toast.LENGTH_LONG).show();
               finish();
               startActivity(new Intent(UpdateContactActivity.this, MainActivity.class));
           }
       }

       DeleteContact dc= new DeleteContact();
       dc.execute();
    }
}
