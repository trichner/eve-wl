package ch.n1b.eve.wt.dao;

import ch.n1b.eve.wt.model.core.CharacterDBVO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created on 19.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class CharacterDAO {

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager em;

    public CharacterDBVO findCharacterByCCPId(int id){
        Query q = em.createQuery("select c from CharacterDBVO c where c.characterId=:id");
        q.setParameter("id",id);
        List<CharacterDBVO> corps = q.getResultList();
        CharacterDBVO dbvo = null;
        if(corps.size()==1){
            dbvo=corps.get(0);
        }else if (corps.size()>1){
            log.warning("Multiple Characters with same Id!");
            dbvo=corps.get(0);
        }

        return dbvo;
    }

    public List<CharacterDBVO> findAllCharacters(){
        Query q = em.createQuery("select c from CharacterDBVO c");
        return q.getResultList();
    }

    public void save(CharacterDBVO dbvo){
        em.persist(dbvo);
    }
}
