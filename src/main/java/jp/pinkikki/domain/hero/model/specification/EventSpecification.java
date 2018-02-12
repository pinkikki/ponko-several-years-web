package jp.pinkikki.domain.hero.model.specification;

import jp.pinkikki.domain.hero.model.Event;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class EventSpecification {

    public static Specification<Event> containEventId(Integer eventId) {
        return StringUtils.isEmpty(eventId) ? null : (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("eventId"), eventId);
        };
    }
}
