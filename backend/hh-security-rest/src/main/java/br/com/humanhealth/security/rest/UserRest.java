package br.com.humanhealth.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1/user")
public class UserRest {
    
    @GET
    public Response test(){
	return Response.ok("teste").build();
    }

}
