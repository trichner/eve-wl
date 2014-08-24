package ch.n1b.eve.wl.model.waitlist;

import ch.n1b.eve.wl.minions.Mappable;
import ch.n1b.eve.wl.model.core.CharacterDBVO;
import ch.n1b.eve.wl.model.vo.WaitlistItemVO;

import javax.persistence.*;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "WAITLIST_ITEM_TE")
public class WaitlistItemDBVO implements Mappable<WaitlistItemVO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAITLIST_ITEM_ID")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "WAITLIST_ID")
    private WaitlistDBVO waitlist;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "SHIP_DNA")
    private String shipDNA;

    @Column(name = "SHIP_NAME")
    private String shipName;

    @Column(name = "SHIP_TYPE")
    private int shipType;

    @Column(name = "ITEM_ID")
    private String itemId;

    @OneToOne
    @JoinColumn(name = "CHARACTER_ID")
    private CharacterDBVO character;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public WaitlistDBVO getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(WaitlistDBVO waitlist) {
        this.waitlist = waitlist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getShipDNA() {
        return shipDNA;
    }

    public void setShipDNA(String shipDNA) {
        this.shipDNA = shipDNA;
    }

    public CharacterDBVO getCharacter() {
        return character;
    }

    public void setCharacter(CharacterDBVO character) {
        this.character = character;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public WaitlistItemVO map() {
        WaitlistItemVO vo = new WaitlistItemVO();
        vo.setCharacterId(character.getCharacterId());
        vo.setCharacterName(character.getCharacterName());
        vo.setShipDNA(shipDNA);
        vo.setShipType(shipType);
        vo.setItemId(itemId);
        vo.setShipName(shipName);
        return vo;
    }
}
