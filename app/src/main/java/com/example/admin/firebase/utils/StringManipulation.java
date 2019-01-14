package com.example.admin.firebase.utils;

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username){
        return username.replace(" " , ".");
    }
}