package ch.n1b.eve.wl.service;

import ch.n1b.eve.wl.dao.CharacterDAO;
import ch.n1b.eve.wl.minions.Mapper;
import ch.n1b.eve.wl.model.core.CharacterDBVO;
import ch.n1b.eve.wl.model.vo.CharacterVO;

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
