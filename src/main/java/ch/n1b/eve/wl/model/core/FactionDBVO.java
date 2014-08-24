package ch.n1b.eve.wl.model.core;

import javax.persistence.*;

/**
 * Created on 19.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "FACTION_TE")
public class FactionDBVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACTION_ID")
    private Long id;

    @Column(name = "CCP_ID")
    private int factionId;

    @Column(name = "NAME")
    private String factionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
}
