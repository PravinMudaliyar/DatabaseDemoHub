package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.databasedemo.adapter.RecyclerViewAdapter;
import com.example.databasedemo.data.DataBaseHandler;
import com.example.databasedemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
   // private ListView listView;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    //private ArrayList<String> contactArrayList;
    private ArrayList<Contact> contactArrayList;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactArrayList = new ArrayList<>();

        DataBaseHandler db = new DataBaseHandler(MainActivity.this);

        List<Contact> contactList = db.getAllContacts();

        for(Contact contact : contactList){
            Log.d("MainActivity", "onCreate: " + contact.getName() + " " );
            //contactArrayList.add(contact.getName());  For listView.
            contactArrayList.add(contact);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this , contactArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);

        /*Log.d("Count", "onCreate: " + db.getCount());
         Contact jeremy = new Contact();
        jeremy.setName("Jeremy");
        jeremy.setPhoneNumber("9432094");
        // Log.d("Main", "onCreate: " + jeremy.getName() + " " + jeremy.getPhoneNumber());

       // db.getContact("Jeremy");

        //Get one contact
         Contact c = db.getContact(1);

         c.setName("NewJeremy");
         c.setPhoneNumber("3972040");
        // Log.d("Main", "onCreate: " + c.getName() + " " + c.getPhoneNumber());


       //  int updatedRow = db.updateContact(c);

      //  Log.d("Row", "onCreate: " + updatedRow);

      //  db.deleteContact(c);                         */

        db.addContact(new Contact("James1" , "42589071"));
       // db.addContact(new Contact("James2" , "42589072"));
        //db.addContact(new Contact("James3" , "42589073"));
        //db.addContact(new Contact("James4" , "42589074"));
        //db.addContact(new Contact("James5" , "42589075"));
        

     /*    arrayAdapter = new ArrayAdapter<>(
                 this , android.R.layout.simple_list_item_1 , contactArrayList );   // We just want one textView which shows name as per we want , hence we used android.R.l....

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Cunts", "onItemClick: " + position);
              //  Log.d("List", "onItemClick: " + contactArrayList.get(position));
            }
        });                                                                                           */





    }
}