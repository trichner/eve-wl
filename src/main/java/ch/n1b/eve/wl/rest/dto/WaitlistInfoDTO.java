package ch.n1b.eve.wl.rest.dto;

/**
 * Created on 23.08.2014.
 *
 * @author Thomas
 */
public class WaitlistInfoDTO {

    private long ownerId;
    private String waitlistName;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getWaitlistName() {
        return waitlistName;
    }

    public void setWaitlistName(String waitlistName) {
        this.waitlistName = waitlistName;
    }
}
