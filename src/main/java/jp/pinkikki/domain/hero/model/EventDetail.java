package jp.pinkikki.domain.hero.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "event_detail")
@Data
public class EventDetail implements Serializable {

    @EmbeddedId
    private EventDetailId eventDetailId;

    private String attr1;

    private String attr2;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    @MapsId("eventId")
    private Event event;
}
