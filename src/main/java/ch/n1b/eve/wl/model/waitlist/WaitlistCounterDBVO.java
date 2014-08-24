package ch.n1b.eve.wl.model.waitlist;

import javax.persistence.*;

/**
 * Created on 24.08.2014.
 *
 * @author Thomas
 */
@Entity
@Table(name = "WAITLIST_COUNTER_TE")
public class WaitlistCounterDBVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTER_ID")
    private Long id;

    @Column(name = "COUNTER")
    private Long counter;

    @OneToOne
    @JoinColumn(name = "WAITLIST_ID")
    private WaitlistDBVO waitlist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public WaitlistDBVO getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(WaitlistDBVO waitlist) {
        this.waitlist = waitlist;
    }
}
