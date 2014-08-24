package ch.n1b.eve.wt.service;

import ch.n1b.eve.wt.dao.CharacterDAO;
import ch.n1b.eve.wt.minions.Mapper;
import ch.n1b.eve.wt.model.core.CharacterDBVO;
import ch.n1b.eve.wt.model.vo.CharacterVO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class CharacterService {

    @Inject
    private CharacterDAO characterDAO;

    public CharacterVO findByCharacterId(int characterId){
        CharacterDBVO dbvo = characterDAO.findCharacterByCCPId(characterId);
        return Mapper.map(dbvo);
    }

    public List<CharacterVO> findAllCharacters(){
        List characters = characterDAO.findAllCharacters();
        return Mapper.mapList(characters);
    }
}
