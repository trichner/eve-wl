package ch.n1b.eve.wl.model.vo;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
public class WaitlistItemVO {

    private int characterId;
    private String characterName;
    private String shipDNA;
    private String shipName;
    private String itemId;
    private int shipType;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getCharacterId() {
        return characterId;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
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

    public String getShipDNA() {
        return shipDNA;
    }

    public void setShipDNA(String shipDNA) {
        this.shipDNA = shipDNA;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
