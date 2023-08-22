package com.loginPage.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


public class DbConnection {

    public static MongoClient connection()
    {
        MongoClient mongoClient = null;
        try{
            String uri = "mongodb://localhost:27017";

            mongoClient = MongoClients.create(uri);
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return mongoClient;
    }
}
