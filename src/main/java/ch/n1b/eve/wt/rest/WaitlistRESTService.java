package ch.n1b.eve.wt.rest;

import ch.n1b.eve.wt.minions.FunctionalException;
import ch.n1b.eve.wt.minions.InputException;
import ch.n1b.eve.wt.model.vo.WaitlistItemVO;
import ch.n1b.eve.wt.model.vo.WaitlistVO;
import ch.n1b.eve.wt.rest.dto.WaitlistInfoDTO;
import ch.n1b.eve.wt.service.WaitlistService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Thomas on 19.08.2014.
 */
@Path("/wl")
@RequestScoped
public class WaitlistRESTService {

    @Inject
    private Logger log;

    @Inject
    private WaitlistService wlService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WaitlistInfoDTO> getAvailableWaitlists(){
        return wlService.getAllWaitlists();
    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public WaitlistVO getMyList(@HeaderParam("EVE_CHARID") int ownerId){
        log.info("getMyList");
        WaitlistVO waitlistVO = wlService.getWaitlistByOwnerId(ownerId);
        if(waitlistVO==null){
            log.info("creating new list");
            waitlistVO = wlService.getNewWaitlist(ownerId, "E-UNI Waitlist"); // TODO hardcoded name
        }
        return waitlistVO;
    }

    @POST
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public WaitlistVO getNewList(@HeaderParam("EVE_CHARID") int ownerId, String name){
        return wlService.getNewWaitlist(ownerId,name);
    }

    @GET
    @Path("/id/{waitlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public WaitlistVO getWaitlist(@PathParam("waitlistId") String waitlistId){
        log.info("getWaitlistt: " + waitlistId);
        return wlService.getWaitlistById(waitlistId);
    }

    @DELETE
    @Path("/id/{waitlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteItem(@HeaderParam("EVE_CHARID") int actingUser,@PathParam("waitlistId") String waitlistId, String itemId) throws Exception{
        log.info("deleteItem: " + waitlistId);
        wlService.deleteItemFromWaitlist(actingUser, waitlistId,itemId);
    }

    @POST
    @Path("/id/{waitlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public WaitlistItemVO enqueue(@HeaderParam("EVE_CHARID") int charId, @PathParam("waitlistId") String waitlistId, String chatCopyPasta) throws InputException, FunctionalException {
        log.info("enqueue: " + waitlistId);
        log.info("DNA: "+chatCopyPasta);
        return wlService.enqueue(charId,waitlistId, chatCopyPasta);
    }

}
