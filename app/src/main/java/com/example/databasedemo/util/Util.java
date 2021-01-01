package com.example.databasedemo.util;
// We have created this util class so that we don't have to change variables all over the project but can just handle the here. Hence we have initialised variables as static.

public class Util {
    //Database related items

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact_db";
    public static final String TABLE_NAME = "contacts";

    //Contacts table columns names.

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE_NUMBER = "phone_number";
}
