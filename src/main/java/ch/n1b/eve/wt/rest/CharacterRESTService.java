package ch.n1b.eve.wt.rest;

import ch.n1b.eve.wt.minions.AuthenticationException;
import ch.n1b.eve.wt.model.vo.CharacterVO;
import ch.n1b.eve.wt.service.AuthenticationService;
import ch.n1b.eve.wt.service.CharacterService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Path("/character")
@RequestScoped
public class CharacterRESTService {

    @Inject
    private AuthenticationService authService;

    @Inject
    private CharacterService characterService;

    @Path("me")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CharacterVO getMe(@Context HttpHeaders headers) throws AuthenticationException {
        MultivaluedMap<String,String> headerMap = headers.getRequestHeaders();
        return authService.signin(headerMap);
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CharacterVO> getAllCharacters(){
        return characterService.findAllCharacters();
    }

}
