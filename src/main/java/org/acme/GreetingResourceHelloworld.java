package org.acme;

import jakarta.ws.rs.QueryParam;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/helloworld")
public class GreetingResourceHelloworld {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("msg") String msg) {
        String response = "Hello World";
        // msg=from%20me
        // Falls der Parameter msg = from%20me   %ist eine Leerzeile in url und die 20 hexadezimal?
        if("from me".equals(msg)){
            response = response + " from me";
        }

        return response;

    }
}
