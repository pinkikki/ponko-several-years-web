package jp.pinkikki.domain.hero.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EventDetailId implements Serializable {

    private Integer eventId;

    private Integer seq;

    private Integer typeId;
}
