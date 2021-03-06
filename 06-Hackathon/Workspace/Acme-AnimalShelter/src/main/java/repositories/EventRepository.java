package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("select e from Event e join e.center c where c.id=?1")
	Collection<Event> findEventByCenter(int centerId);
	
	@Query("select e from Event e where e.endDate>?1")
	Collection<Event> findEventNotEnd(Date today);
	
	@Query("select e from Event e join e.center c join c.boss b where b.id=?1")
	Collection<Event> findEventbyBoss(int bossId);
}
