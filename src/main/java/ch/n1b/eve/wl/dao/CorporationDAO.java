package ch.n1b.eve.wl.dao;

import ch.n1b.eve.wl.model.core.AllianceDBVO;
import ch.n1b.eve.wl.model.core.CorporationDBVO;

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
public class CorporationDAO {

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager em;

    public CorporationDBVO findCorporationByCCPId(int id){
        Query q = em.createQuery("select c from CorporationDBVO c where c.corporationId=:id");
        q.setParameter("id",id);
        List<CorporationDBVO> corps = q.getResultList();
        CorporationDBVO dbvo = null;
        if(corps.size()==1){
            dbvo=corps.get(0);
        }else if (corps.size()>1){
            log.warning("Multiple Corporations with same Id!");
            dbvo=corps.get(0);
        }

        return dbvo;
    }

    public CorporationDBVO insertOrUpdate(int corpId, String corpName, AllianceDBVO alliance){
        CorporationDBVO dbvo = findCorporationByCCPId(corpId);
        if (dbvo==null){
            dbvo = new CorporationDBVO();
            dbvo.setCorporationId(corpId);
            save(dbvo);
        }
        dbvo.setAlliance(alliance);
        dbvo.setCorporationName(corpName);
        return dbvo;
    }

    public void save(CorporationDBVO dbvo){
        em.persist(dbvo);
    }
}
