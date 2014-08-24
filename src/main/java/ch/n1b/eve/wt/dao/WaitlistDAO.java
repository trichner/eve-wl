package ch.n1b.eve.wt.dao;

import ch.n1b.eve.wt.minions.FunctionalException;
import ch.n1b.eve.wt.model.core.CharacterDBVO;
import ch.n1b.eve.wt.model.waitlist.WaitlistCounterDBVO;
import ch.n1b.eve.wt.model.waitlist.WaitlistDBVO;
import ch.n1b.eve.wt.model.waitlist.WaitlistItemDBVO;
import org.apache.commons.lang3.RandomStringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class WaitlistDAO {

    @Inject
    private Logger log;

    @Inject
    private CharacterDAO characterDAO;

    @PersistenceContext
    private EntityManager em;

    public WaitlistDBVO findByOwnerId(int ownerId){
        Query q = em.createQuery("select c from WaitlistDBVO c where c.owner.characterId=:ownerId");
        q.setParameter("ownerId",ownerId);

        List<WaitlistDBVO> waitlistDBVOs = q.getResultList();
        WaitlistDBVO dbvo = null;
        if(waitlistDBVOs.size()==1){
            dbvo=waitlistDBVOs.get(0);
        }else if (waitlistDBVOs.size()>1){
            log.warning("Multiple waitlists for the same user!");
            dbvo=waitlistDBVOs.get(0);
        }
        return dbvo;
    }

    public WaitlistDBVO findById(String waitlistId){
        Query q = em.createQuery("select c from WaitlistDBVO c where c.waitlistId=:waitlistId");
        q.setParameter("waitlistId",waitlistId);
        List<WaitlistDBVO> waitlistDBVOs = q.getResultList();
        WaitlistDBVO dbvo = null;
        if(waitlistDBVOs.size()==1){
            dbvo=waitlistDBVOs.get(0);
        }else if (waitlistDBVOs.size()>1){
            log.warning("Multiple waitlists for the same user!");
            dbvo=waitlistDBVOs.get(0);
        }else {
            log.warning("Waitlist not found: " + waitlistId);
        }
        return dbvo;
    }

    public List<WaitlistDBVO> getAllWaitlists(){
        Query q = em.createQuery("select c from WaitlistDBVO c order by c.created desc");
        return q.getResultList();
    }

    public void deleteItemFromWaitlist(String itemId){
        Query q = em.createQuery("delete WaitlistItemDBVO t where t.itemId=:itemId");
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public WaitlistItemDBVO findItemById(String itemId) {
        Query q = em.createQuery("select c from WaitlistItemDBVO c where c.itemId=:itemId");
        q.setParameter("itemId",itemId);
        return (WaitlistItemDBVO) q.getSingleResult();
    }

    public WaitlistDBVO createNew(int actingUser,String name){
        WaitlistDBVO dbvo = new WaitlistDBVO();
        CharacterDBVO character = characterDAO.findCharacterByCCPId(actingUser);
        dbvo.setOwner(character);
        dbvo.setName(name);
        dbvo.setCreated(new Date());
        dbvo.setWaitlistId(RandomStringUtils.randomAlphanumeric(20));
        dbvo.setWaitlist(Collections.<WaitlistItemDBVO>emptyList());
        em.persist(dbvo);
        return dbvo;
    }

    public WaitlistItemDBVO addItem(int userId,String waitlistId,String shipDNA,String shipName,int shipType) throws FunctionalException {
        WaitlistDBVO listDBVO = findById(waitlistId);
        if(listDBVO==null){
            throw new FunctionalException("Illegal waitlistId");
        }

        CharacterDBVO characterDBVO = characterDAO.findCharacterByCCPId(userId);
        if(characterDBVO==null){
            throw new FunctionalException("Illegal characterId");
        }

        WaitlistItemDBVO dbvo = new WaitlistItemDBVO();
        dbvo.setWaitlist(listDBVO);
        dbvo.setCharacter(characterDBVO);
        dbvo.setShipType(shipType);
        dbvo.setShipDNA(shipDNA);
        dbvo.setShipName(shipName);
        dbvo.setPriority(getAndIncrement(waitlistId));
        dbvo.setItemId(RandomStringUtils.randomAlphanumeric(16));
        em.persist(dbvo);
        return dbvo;
    }
    private long getAndIncrement(String waitlistId) throws FunctionalException {
        Query q = em.createQuery("select c from WaitlistCounterDBVO c where c.waitlist.waitlistId=:waitlistId order by c.counter desc");
        q.setParameter("waitlistId",waitlistId);
        List<WaitlistCounterDBVO> counters = q.getResultList();

        // did we have any glitches?
        if(counters.size()>1){
            log.warning("Duplicate counters.");
        }

        long priority = 0;
        if(counters.size()>0){
            WaitlistCounterDBVO counter = counters.get(0);
            priority = counter.getCounter();
            counter.setCounter(priority+1);
        }else if(counters.size()==0){
            WaitlistCounterDBVO counter = new WaitlistCounterDBVO();
            counter.setCounter(priority);
            WaitlistDBVO waitlistDBVO = findById(waitlistId);
            if(waitlistDBVO==null){
                throw new FunctionalException("Illegal waitlist id.");
            }
            counter.setWaitlist(waitlistDBVO);
            em.persist(counter);
        }
        return priority;
    }
}
