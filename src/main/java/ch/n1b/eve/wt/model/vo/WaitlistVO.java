package ch.n1b.eve.wt.model.vo;

import ch.n1b.eve.wt.minions.Mappable;
import ch.n1b.eve.wt.rest.dto.WaitlistInfoDTO;

import java.util.List;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
public class WaitlistVO implements Mappable<WaitlistInfoDTO>{

    private long ownerId;
    private String waitlistId;
    private String ownerName;
    private String waitlistName;

    private List<WaitlistItemVO> waitlist;


    public String getWaitlistId() {
        return waitlistId;
    }

    public void setWaitlistId(String waitlistId) {
        this.waitlistId = waitlistId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getWaitlistName() {
        return waitlistName;
    }

    public void setWaitlistName(String waitlistName) {
        this.waitlistName = waitlistName;
    }

    public List<WaitlistItemVO> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(List<WaitlistItemVO> waitlist) {
        this.waitlist = waitlist;
    }

    @Override
    public WaitlistInfoDTO map() {
        WaitlistInfoDTO dto = new WaitlistInfoDTO();
        dto.setOwnerId(ownerId);
        dto.setWaitlistName(waitlistName);
        return dto;
    }
}
