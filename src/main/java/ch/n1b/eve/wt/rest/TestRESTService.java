package ch.n1b.eve.wt.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Path("/test")
public class TestRESTService {

    @Inject
    private Logger log;

    @Path("headers")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void getHeaders(@Context HttpHeaders headers){
        log.info("Headers:");
        for (String line : headers.getRequestHeaders().keySet()){
            log.info(" " + line);
        }
    }

}
