package ch.n1b.eve.wt.service;

import ch.n1b.eve.wt.dao.AllianceDAO;
import ch.n1b.eve.wt.dao.CharacterDAO;
import ch.n1b.eve.wt.dao.CorporationDAO;
import ch.n1b.eve.wt.dao.WaitlistDAO;
import ch.n1b.eve.wt.minions.AuthorizationException;
import ch.n1b.eve.wt.minions.FunctionalException;
import ch.n1b.eve.wt.minions.InputException;
import ch.n1b.eve.wt.minions.Mapper;
import ch.n1b.eve.wt.model.vo.WaitlistItemVO;
import ch.n1b.eve.wt.model.vo.WaitlistVO;
import ch.n1b.eve.wt.model.waitlist.WaitlistDBVO;
import ch.n1b.eve.wt.model.waitlist.WaitlistItemDBVO;
import ch.n1b.eve.wt.rest.dto.WaitlistInfoDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class WaitlistService {

    @Inject
    private AllianceDAO allianceDAO;

    @Inject
    private CharacterDAO characterDAO;

    @Inject
    private CorporationDAO corporationDAO;

    @Inject
    private WaitlistDAO waitlistDAO;

    @Inject
    private Logger log;

    private static Pattern fittingPattern = Pattern.compile(".*<url=fitting:([0-9:;]*)>(.*)</url>.*");
    private static Pattern shipTypePattern = Pattern.compile(".*fitting:([0-9]*):.*");

    public WaitlistItemVO enqueue(int characterId,String waitlistId, String chatCopyPaste) throws FunctionalException, InputException {
        // EXAMPLE: Jayce Belerene > o/  <url=fitting:24688:2048;1:1952;1:26082;1:7783;8:26088;1:2281;2:10190;3:1999;1:17559;2:1978;1:26076;1:2488;5:23719;5:21740;12000:28999;1:29001;1:29011;1::>PewPew</url> (got T2 guns, forgot to save build tho), in case we're running/formin
        // Osmand Erata > x standby scout if needed <url=fitting:605:31238;2:2605;2:5973;1:22175;4::>El</url>
        Matcher fittingMatcher = fittingPattern.matcher(chatCopyPaste);
        Matcher shipTypeMatcher = shipTypePattern.matcher(chatCopyPaste);
        if(fittingMatcher.find() && shipTypeMatcher.find()){
            String shipDNA = fittingMatcher.group(1);
            String shipName = fittingMatcher.group(2);
            int shipType = Integer.parseInt(shipTypeMatcher.group(1));
            WaitlistItemDBVO dbvo = waitlistDAO.addItem(characterId, waitlistId, shipDNA, shipName, shipType);
            return Mapper.map(dbvo);
        }else {
            throw new InputException("Could not read ship fitting, no dna found.");
        }
    }

    public List<WaitlistInfoDTO> getAllWaitlists(){
        List<WaitlistDBVO> result = waitlistDAO.getAllWaitlists();
        List<WaitlistVO> mappedVO = Mapper.mapList(result);
        return Mapper.mapList(mappedVO);
    }

    public WaitlistVO getWaitlistByOwnerId(int ownerId){
        WaitlistDBVO dbvo = waitlistDAO.findByOwnerId(ownerId);
        return Mapper.map(dbvo);
    }

    public WaitlistVO getWaitlistById(String waitlistId){
        WaitlistDBVO dbvo = waitlistDAO.findById(waitlistId);
        return Mapper.map(dbvo);
    }

    public WaitlistVO getNewWaitlist(int ownerId, String name) {
        WaitlistDBVO dbvo = waitlistDAO.createNew(ownerId,name);
        return Mapper.map(dbvo);
    }

    public void deleteItemFromWaitlist(int actingUser, String waitlistId,String waitlistItemId) throws AuthorizationException, FunctionalException {
        if(isAuthorizedToDeleteItem(actingUser,waitlistId,waitlistItemId)){
            waitlistDAO.deleteItemFromWaitlist(waitlistItemId);
        }else {
            throw new AuthorizationException("Not authorized to delete item");
        }
    }

    private boolean isAuthorizedToDeleteItem(int actingUser,String waitlistId,String waitlistItemId) throws FunctionalException {
        WaitlistDBVO waitlistDBVO = waitlistDAO.findById(waitlistId);
        WaitlistItemDBVO itemDBVO = waitlistDAO.findItemById(waitlistItemId);

        if(waitlistDBVO.getId()!=itemDBVO.getWaitlist().getId()){
            throw new FunctionalException("WaitlistId and itemId do not match");
        }

        // acting user is WL manager
        if(waitlistDBVO.getOwner().getCharacterId()==actingUser){
            return true;
        }

        // acting user is owner of item
        if(itemDBVO.getCharacter().getCharacterId()==actingUser){
            return true;
        }
        return false;
    }
}
