package com.primrose;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by evanpthompson on 10/26/2016.
 */

@Path("/")
public class UsersRS {

    private static UsersDB usersDB; // set in populate()

    public UsersRS() { }

    @POST
    @Path("/doLogin")
    @Produces({MediaType.TEXT_PLAIN})
    public Response doLogin(@FormParam("userName") String userName, @FormParam("password") String password) {

        checkContext();

        String msg = null;
        User user = usersDB.findUser(userName, password);


        if (user != null) {
            msg = user.toString() + " has been logged in!";
            return Response.ok(msg, "text/plain").build();
        }
        else {
            msg = "Username or password is incorrect.\n";
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(msg).
                    type(MediaType.TEXT_PLAIN).
                    build();
        }
    }





    /*  Utilities  */

    private void checkContext() {
        if (usersDB == null) {
            populate();
        }
    }

    private void populate() {

        usersDB = new UsersDB();
    }


    private String toJson(User user) {
        String json = "If you see this, there's a problem.";
        try {
            json = new ObjectMapper().writeValueAsString(user);
        }
        catch(Exception e) { e.getStackTrace(); }
        return json;
    }

    // PredictionsList --> JSON document
    private String toJson(UsersDB usersDB) {
        String json = "If you see this, there's a problem.";
        try {
            json = new ObjectMapper().writeValueAsString(usersDB);
        }
        catch(Exception e) { }
        return json;
    }


}
