package com.primrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

/**
 * Users are stored on the MongoDB.
 * Class provides access to the DB through the MongoConnector.getDatabase()
 */


public class UsersDB {

    private static MongoCollection<Document> collection;  // MongoCollection of type Document


    // instantiate connection to collection
    public UsersDB() {
        collection = MongoConnector.getInstance().getMongoCollection("users");
    }

    // search DB.collection as specified in constructor. Returns PublicUser
    public User findUser(String userName, String password) {

        //PublicUser pubUser = null;
        User user = null;

        ObjectMapper mapper = new ObjectMapper();

        Bson filter = eq("userName", userName);
        Bson projection = Projections.exclude("_id");

        MongoCursor<Document> itr = collection.find(filter)
                .projection(projection)
                .iterator();

        try {
            while (itr.hasNext()) {
                Document cur = itr.next();
                String hashed = cur.getString("password");
                if (BCrypt.checkpw(password, hashed)) {

                    try {
                        user = mapper.readValue(cur.toJson(), User.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return user;
                }

            }
        } finally {  // always use when iterating with MongoCursor
            itr.close();  // ensure the cursor is closed in all situations, incase of an exception or break in loop
        }

        return user;
    }
}