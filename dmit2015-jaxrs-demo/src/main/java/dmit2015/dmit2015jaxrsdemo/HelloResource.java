package dmit2015.dmit2015jaxrsdemo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Produces("text/html")
    public String helloInHtml() {
        return "<h1>Hello, World in HTML!</h1>";
    }
}