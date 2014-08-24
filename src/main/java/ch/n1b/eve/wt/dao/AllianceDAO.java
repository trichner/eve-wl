package ch.n1b.eve.wt.dao;

import ch.n1b.eve.wt.model.core.AllianceDBVO;
import ch.n1b.eve.wt.model.core.CharacterDBVO;
import ch.n1b.eve.wt.model.core.CorporationDBVO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class AllianceDAO {

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager em;

    public AllianceDBVO findAllianceByCCPId(int id){
        Query q = em.createQuery("select c from AllianceDBVO c where c.allianceId=:id");
        q.setParameter("id",id);
        List<AllianceDBVO> corps = q.getResultList();
        AllianceDBVO dbvo = null;
        if(corps.size()==1){
            dbvo=corps.get(0);
        }else if (corps.size()>1){
            log.warning("Multiple Alliances with same Id!");
            dbvo=corps.get(0);
        }

        return dbvo;
    }

    public AllianceDBVO insertOrUpdate(int allianceId,String allianceName){
        AllianceDBVO dbvo = findAllianceByCCPId(allianceId);
        if (dbvo==null){
            dbvo = new AllianceDBVO();
            dbvo.setAllianceId(allianceId);
            save(dbvo);
        }
        dbvo.setAllianceName(allianceName);
        return dbvo;
    }

    public void save(AllianceDBVO dbvo){
        em.persist(dbvo);
    }
}
