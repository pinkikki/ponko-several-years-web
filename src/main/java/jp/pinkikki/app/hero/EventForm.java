package jp.pinkikki.app.hero;

import jp.pinkikki.domain.hero.model.Event;
import jp.pinkikki.domain.hero.model.SceneName;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventForm implements Serializable {

    public static interface EventInput{}
    public static interface EventDetail{}

//    @NotNull(groups = EventInput.class)
//    @Min(0)
//    @Max(99999)
    private Integer eventId;

    @NotNull(groups = EventInput.class)
    private SceneName sceneName;

    @NotEmpty(groups = EventInput.class)
    private String objectName;

    @NotNull(groups = EventInput.class)
    @Min(value = 0, groups = EventInput.class)
    @Max(value = 99999, groups = EventInput.class)
    private Integer procedure;

    @NotNull(groups = EventInput.class)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate insertDate;
}
