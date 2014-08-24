package ch.n1b.eve.wl.model.core;

import javax.persistence.*;

/**
 * Created on 19.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "ALLIANCE_TE")
public class AllianceDBVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALLIANCE_ID")
    private Long id;

    @Column(name = "CCP_ID")
    private int allianceId;

    @Column(name = "NAME")
    private String allianceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(int allianceId) {
        this.allianceId = allianceId;
    }

    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
    }
}
