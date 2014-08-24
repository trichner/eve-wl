package ch.n1b.eve.wl.model.waitlist;

import ch.n1b.eve.wl.minions.Mappable;
import ch.n1b.eve.wl.minions.Mapper;
import ch.n1b.eve.wl.model.core.CharacterDBVO;
import ch.n1b.eve.wl.model.vo.WaitlistVO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "WAITLIST_TE")
public class WaitlistDBVO implements Mappable<WaitlistVO>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WAITLIST_ID")
    private Long id;

    @Column(name = "PUBLIC_ID")
    private String waitlistId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATED")
    private Date created;

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private CharacterDBVO owner;

    @OneToMany
    @JoinColumn(name = "WAITLIST_ID")
    @OrderBy("priority")
    private List<WaitlistItemDBVO> waitlist;

    public List<WaitlistItemDBVO> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(List<WaitlistItemDBVO> waitlist) {
        this.waitlist = waitlist;
    }

    public Date getCreated() {
        return created;
    }

    public String getWaitlistId() {
        return waitlistId;
    }

    public void setWaitlistId(String waitlistId) {
        this.waitlistId = waitlistId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterDBVO getOwner() {
        return owner;
    }

    public void setOwner(CharacterDBVO owner) {
        this.owner = owner;
    }



    @Override
    public WaitlistVO map() {
        WaitlistVO vo = new WaitlistVO();
        vo.setOwnerId(owner.getCharacterId());
        vo.setOwnerName(owner.getCharacterName());
        vo.setWaitlist(Mapper.mapList(waitlist));
        vo.setWaitlistId(waitlistId);
        return vo;
    }

}
