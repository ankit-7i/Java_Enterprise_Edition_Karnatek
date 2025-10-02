package com.ankit.jee.jdbc_projecthub.create_new_schema_program;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class JDBC_Create_SchemaCreation_MongoDB {
    public static void main(String[] args) {
        // 1. Connecting to MongoDB server
        MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
        System.out.println("Connected to MongoDB");

        // 2. Accessing the admin database to create a new user
        MongoDatabase adminDb = mongo.getDatabase("admin");

        // 3. Create user with readWrite role on 'jdbcdb' database
        Document createUserCommand = new Document("createUser", "jdbcdb")
                .append("pwd", "ankit07")
                .append("roles", java.util.Arrays.asList(
                        new Document("role", "readWrite").append("db", "jdbcdb")
                ));

        adminDb.runCommand(createUserCommand);
        System.out.println("User 'jdbcdb' created with readWrite role on 'jdbcdb' database");

        // 4. Close connection
        mongo.close();
    }
}
