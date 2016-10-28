package com.primrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

/**
 * Users are stored on the MongoDB.
 * Class provides access to the DB through the MongoConnector.getDatabase()
 */


public class EmployeesDB {

    private static MongoCollection<Document> collection;  // MongoCollection of type Document


    // instantiate connection to collection
    public EmployeesDB() {
        collection = MongoConnector.getInstance().getMongoCollection("employees");
    }



    // search DB.collection as specified in constructor. Returns PublicUser
    public Employee findEmployee(String givenName) {

        //PublicUser pubUser = null;
        Employee employee = null;

        ObjectMapper mapper = new ObjectMapper();

        Bson filter = eq("givenName", givenName);
        Bson projection = Projections.exclude("_id");

        MongoCursor<Document> itr = collection.find(filter)
                .projection(projection)
                .iterator();

        try {
            while (itr.hasNext()) {
                Document cur = itr.next();

                if (cur.getString("givenName").equalsIgnoreCase(givenName)) {

                    try {
                        employee = mapper.readValue(cur.toJson(), Employee.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return employee;
                }

            }
        } finally {  // always use when iterating with MongoCursor
            itr.close();  // ensure the cursor is closed in all situations, incase of an exception or break in loop
        }

        return employee;
    }

    public static Employee findEmployee(Integer id) {

        //PublicUser pubUser = null;
        Employee employee = null;

        ObjectMapper mapper = new ObjectMapper();

        Bson filter = eq("id", id);
        Bson projection = Projections.exclude("_id");

        MongoCursor<Document> itr = collection.find(filter)
                .projection(projection)
                .iterator();

        try {
            while (itr.hasNext()) {
                Document cur = itr.next();

                if (cur.getInteger("id").equals(id)) {

                    try {
                        // check why json is not being returned
                        employee = mapper.readValue(cur.toJson(), Employee.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return employee;
                }

            }
        } finally {  // always use when iterating with MongoCursor
            itr.close();  // ensure the cursor is closed in all situations, incase of an exception or break in loop
        }

        return employee;
    }


}
