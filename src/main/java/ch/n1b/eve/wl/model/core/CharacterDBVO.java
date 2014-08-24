package ch.n1b.eve.wl.model.core;

import ch.n1b.eve.wl.minions.Mappable;
import ch.n1b.eve.wl.model.vo.CharacterVO;

import javax.persistence.*;

/**
 * Created on 19.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "CHARACTER_TE")
public class CharacterDBVO implements Mappable<CharacterVO>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHARACTER_ID")
    private Long id;

    @Column(name = "CCP_ID")
    private int characterId;

    @Column(name = "NAME")
    private String characterName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CORPORATION_ID")
    private CorporationDBVO corporation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTION_ID")
    private FactionDBVO faction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CorporationDBVO getCorporation() {
        return corporation;
    }

    public void setCorporation(CorporationDBVO corporation) {
        this.corporation = corporation;
    }

    public FactionDBVO getFaction() {
        return faction;
    }

    public void setFaction(FactionDBVO faction) {
        this.faction = faction;
    }

    @Override
    public CharacterVO map() {
        CharacterVO vo = new CharacterVO();
        vo.setCharacterId(getCharacterId());
        vo.setCharacterName(getCharacterName());
        if(corporation!=null) {
            vo.setCorporationId(corporation.getCorporationId());
            vo.setCorporationName(corporation.getCorporationName());
            AllianceDBVO alliance = corporation.getAlliance();
            if(alliance!=null){
                vo.setAllianceId(alliance.getAllianceId());
                vo.setAllianceName(alliance.getAllianceName());
            }
        }

        return vo;
    }
}
