package com.example.rudra.sqlliteroomdatabaseexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mCtx;
    private List<Contact> contactList;

    public ContactAdapter(Context mCtx, List<Contact> contactList) {
        this.mCtx = mCtx;
        this.contactList = contactList;


    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_item, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {


        Contact contact = contactList.get(i);
        contactViewHolder.textViewName.setText(contact.getName());
        contactViewHolder.textViewAddress.setText(contact.getAddress());
        //contactViewHolder.textViewid.setText(contact.getId());


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName, textViewAddress, textViewid;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);

            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            //     textViewid=itemView.findViewById(R.id.textViewid);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {

            Contact contact = contactList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateContactActivity.class);
            intent.putExtra("contact", contact);
            mCtx.startActivity(intent);


        }
    }

}
