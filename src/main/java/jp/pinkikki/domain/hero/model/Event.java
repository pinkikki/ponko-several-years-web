package jp.pinkikki.domain.hero.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "event")
@Data
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @Enumerated(EnumType.STRING)
    private SceneName sceneName;

    private String objectName;

    private Integer procedure;

    @OneToMany
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private List<EventDetail> eventDetails;
}
