package ch.n1b.eve.wt.service;

import ch.n1b.eve.wt.dao.AllianceDAO;
import ch.n1b.eve.wt.dao.CharacterDAO;
import ch.n1b.eve.wt.dao.CorporationDAO;
import ch.n1b.eve.wt.minions.AuthenticationException;
import ch.n1b.eve.wt.model.core.AllianceDBVO;
import ch.n1b.eve.wt.model.core.CharacterDBVO;
import ch.n1b.eve.wt.model.core.CorporationDBVO;
import ch.n1b.eve.wt.model.vo.CharacterVO;
import ch.n1b.eve.wt.rest.EveHeaders;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Stateless
public class AuthenticationService {

    @Inject
    private AllianceDAO allianceDAO;

    @Inject
    private CharacterDAO characterDAO;

    @Inject
    private CorporationDAO corporationDAO;

    public CharacterVO signin(MultivaluedMap<String,String> eveHeaders) throws AuthenticationException {
        try {


        int charId = getFirstInt(eveHeaders, EveHeaders.EVE_CHARID);

        CharacterDBVO character = characterDAO.findCharacterByCCPId(charId);

        int allianceId = getFirstInt(eveHeaders,EveHeaders.EVE_ALLIANCEID);
        String allianceName = getFirst(eveHeaders,EveHeaders.EVE_ALLIANCENAME);

        int corpId = getFirstInt(eveHeaders,EveHeaders.EVE_CORPID);
        String corpName = getFirst(eveHeaders,EveHeaders.EVE_CORPNAME);

        AllianceDBVO alliance = allianceDAO.insertOrUpdate(allianceId,allianceName);
        CorporationDBVO corp = corporationDAO.insertOrUpdate(corpId,corpName,alliance);

        if(character==null){
            // new Character
            character = new CharacterDBVO();
            character.setCharacterId(charId);
            characterDAO.save(character);
        }
        character.setCharacterName(getFirst(eveHeaders,EveHeaders.EVE_CHARNAME));
        character.setCorporation(corp);

        return character.map();
        }catch (Exception e){
            throw new AuthenticationException("Could not authenticate user.", e);
        }
    }

    private  <K> int getFirstInt(MultivaluedMap<K,String> map, EveHeaders key){
        return Integer.parseInt(getFirst(map, key));
    }

    private <K,V> V getFirst(MultivaluedMap<K,V> map, EveHeaders key){
        List<V> values = map.get(key.name());
        V value = null;
        if(values!=null && values.size()>0){
            value = values.get(0);
        }
        return value;
    }
}
