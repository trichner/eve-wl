package ch.n1b.eve.wl.model.core;

import javax.persistence.*;

/**
 * Created on 19.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "CORPORATION_TE")
public class CorporationDBVO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CORPORATION_ID")
    private Long id;

    @Column(name = "CCP_ID")
    private int corporationId;

    @Column(name = "NAME")
    private String corporationName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALLIANCE_ID")
    private AllianceDBVO alliance;

    public AllianceDBVO getAlliance() {
        return alliance;
    }

    public void setAlliance(AllianceDBVO alliance) {
        this.alliance = alliance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(int corporationId) {
        this.corporationId = corporationId;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }
}
