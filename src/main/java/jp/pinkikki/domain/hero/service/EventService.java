package jp.pinkikki.domain.hero.service;

import jp.pinkikki.domain.hero.model.Event;
import jp.pinkikki.domain.hero.model.specification.EventSpecification;
import jp.pinkikki.domain.hero.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findAllByJoinFetch() {
        return eventRepository.findAllByJoinFetch();
    }

    public Event findOne(Integer eventId) {
        return eventRepository.findOne(eventId);
    }

    public List<Event> findByEventId(Integer eventId) {
        return eventRepository.findAll(Specifications.where(EventSpecification.containEventId(eventId)));
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
