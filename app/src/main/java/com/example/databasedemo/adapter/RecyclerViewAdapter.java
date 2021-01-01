// To use this recycler class we need to add dependencies in gradle.
package com.example.databasedemo.adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databasedemo.DetailsActivity;
import com.example.databasedemo.R;
import com.example.databasedemo.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context , List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_row , viewGroup , false);

        View view = LayoutInflater.from(this.context).inflate(R.layout.contact_row , viewGroup , false);



        return new ViewHolder(view); // Once we get the view inflated , we are passing it to viewHolder constructor , which is below.
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {    //As data source , recyclerView and adapters are different , we need to bind them.
        Contact contact = contactList.get(position); // each contact object in our list.

        viewHolder.phoneNumber.setText(contact.getPhoneNumber());
        viewHolder.contactName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            contactName = itemView.findViewById(R.id.name);          // As we are'nt in MainActivity we are making these changes
            phoneNumber = itemView.findViewById(R.id.phone_number);
            iconButton = itemView.findViewById(R.id.icon_button);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();                  // Position of each item is being retrieved
            Contact contact = contactList.get(position);

            Intent intent = new Intent(context , DetailsActivity.class);
            intent.putExtra("name" , contact.getName());
            intent.putExtra("number" , contact.getPhoneNumber());

            context.startActivity(intent);   //As we are'nt in the MainActivity

          /*  switch (v.getId()){
                case R.id.icon_button :
                    Log.d("IconClicked", "onClick: " + contact.getName());
                    break;
            }                                              */
        }
    }
}
