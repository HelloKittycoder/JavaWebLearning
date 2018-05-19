package com.kittycoder.easydemo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by shucheng on 2018/5/19.
 */
@Path("/hello")
public class HelloJersey {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Jersey Get!";
    }

    /**
     * 参数是以url?param=value的形式传递
     * @param name
     * @return
     */
    @GET
    @Path("hello_get")
    @Produces("text/plain")
    public String hello(@QueryParam("name") String name) {
        return "Jersey Get! param" + name;
    }

    /**
     * 参数以路径的形式进行传递的
     * @param id
     * @return
     */
    @GET
    @Path("show/{id}")
    @Produces("text/plain")
    public String helloPost(@PathParam("id") int id) {
        return "Hello jersey!" + id;
    }

    @GET
    @Path("getPerson")
    @Produces(MediaType.APPLICATION_JSON)
    public Person showPerson() {
        Person p = new Person();
        p.setName("pName");
        p.setId(121212);
        return p;
    }
}
