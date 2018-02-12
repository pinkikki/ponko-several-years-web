package jp.pinkikki.domain.hero.repository;

import jp.pinkikki.domain.hero.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    @Query("select e from Event e inner join fetch e.eventDetails")
    List<Event> findAllByJoinFetch();

    @Query("select e from Event e inner join fetch e.eventDetails where e.eventId = :eventId")
    Event findOneByJoinFetch(@Param("eventId") Integer eventId);
}
