package com.ankit.jee.jdbc_projecthub.connection_program;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class JDBC_Connection_MongoDB_01
{
    public static void main(String[] args)
    {

        String uri = "mongodb://localhost:27017";

        MongoClient mongoClient = MongoClients.create(uri);

        MongoDatabase database = mongoClient.getDatabase("j2eedb");

        System.out.println("Connection Established...");
        System.out.println("Database Name: " + database.getName());
    }
}
