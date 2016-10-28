package com.primrose;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by evanpthompson on 10/26/2016.
 */

@Path("/")
public class UsersRS {

    private static UsersDB usersDB; // set in populate()
    private static EmployeesDB employeesDB;  // set in populate()

    public UsersRS() { }



    /*  Make use of the Response return type for either .temporaryRedirect(URI) or .seeOther(URI)  */
    // post method used to accommodate the @FormParam,
    // since the password is being sent to the server.
    // this prevents it from being in the url.
    @POST
    @Path("/doLogin")
    @Produces({MediaType.TEXT_PLAIN})
    public Response doLogin(@FormParam("userName") String userName, @FormParam("password") String password) {

        checkContext();

        String msg = null;
        User user = usersDB.findUser(userName, password);


        if (user != null) {
            msg = loggedInUrl(userName);
            //msg = user.toString() + " has been logged in!";  // to send plain text confirmation of successful login
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

    @GET
    @Path("index/{givenName}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response showEmployee(@PathParam("givenName") String givenName) {
        checkContext();

        String msg = null;
        Employee employee = employeesDB.findEmployee(givenName);


        if (employee != null) {

            msg = toJson(employee);  // to send plain text confirmation of successful login
            return Response.ok(msg, "application/json").build();
        }
        else {
            msg = "Not found.\n";
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(msg).
                    type(MediaType.APPLICATION_JSON).
                    build();
        }
    }

    @GET
    @Path("index/{id: \\d+}")
    @Produces({MediaType.TEXT_PLAIN})
    public Response showEmployee(@PathParam("id") Integer id) {
        checkContext();

        String msg = null;
        Employee employee = employeesDB.findEmployee(id);


        if (employee != null) {

            msg = employee.toString();  // to send plain text confirmation of successful login
            return Response.ok(msg, "text/plain").build();
        }
        else {
            msg = "Not found.\n";
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
        employeesDB = new EmployeesDB();
    }

    // User to json
    private String toJson(User user) {
        String json = "If you see this, there's a problem.";
        try {
            json = new ObjectMapper().writeValueAsString(user);
        }
        catch(Exception e) { e.getStackTrace(); }
        return json;
    }

    private String toJson(Employee employee) {
        String json = "If you see this, there's a problem.";
        try {
            json = new ObjectMapper().writeValueAsString(employee);
        }
        catch(Exception e) { e.getStackTrace(); }
        return json;
    }


    // builds url for redirect upon successful login
    private String loggedInUrl(String userName) {
        String url = "/"; // begin from path

        url += "index/" + userName;

        return url;
    }


}
