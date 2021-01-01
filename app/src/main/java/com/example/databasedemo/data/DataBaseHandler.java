package com.example.databasedemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.databasedemo.R;
import com.example.databasedemo.model.Contact;
import com.example.databasedemo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context ) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);

    }

    //Create our table :
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL -Sequel Query Language
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME + " TEXT,"   // INTEGER PRIMARY KEY makes unique id for each element
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE); //Creating our table.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         String DROP_TABLE = String.valueOf(R.string.db_drop);
         db.execSQL(DROP_TABLE , new String[]{Util.DATABASE_NAME});

         //Create a table again
        onCreate(db);
    }
    //Create the CRUD operations (Create , Read , Update , Read)

    //Add contact
    public void addContact(Contact contact){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME , contact.getName()); // We are not gonna be adding 'id' here , bcoz it's gonna be automatically added.
        values.put(Util.KEY_PHONE_NUMBER , contact.getPhoneNumber());

        //Insert to row
        db.insert(Util.TABLE_NAME , null , values);
        Log.d("DBHandler", "addContact: " + "item added");
        db.close();

    }
    // Get a contact
    public  Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        // cursor will point to each rows of the table.
        Cursor cursor = db.query(Util.TABLE_NAME , new String[] { Util.KEY_ID , Util.KEY_NAME , Util.KEY_PHONE_NUMBER} ,
                Util.KEY_ID + "=?",new  String[]{String.valueOf(id)} , null , null , null);

        if(cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
       // Contact contact = new Contact("James", "213986");
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));

        return contact;

    }
    // Get all contacts
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Select all contacts from Database table.
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll , null );

        //Loop through our data
        if(cursor.moveToFirst()){
            do {

                //Contact contact = new Contact();
                Contact contact = new Contact("James", "213986");
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //Add contact objects to our list
                  contactList.add(contact);
            }while(cursor.moveToNext());

        }
       return  contactList;

    }

    //Update contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(Util.KEY_NAME , contact.getName());
         values.put(Util.KEY_PHONE_NUMBER , contact.getPhoneNumber());


         //Update rows
        return db.update(Util.TABLE_NAME , values , Util.KEY_ID + "=?" , new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Util.TABLE_NAME , Util.KEY_ID + "=?" , new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount(){
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery , null);

        return cursor.getCount();
        //We need not to close db here as it closes by itself.
    }
}
