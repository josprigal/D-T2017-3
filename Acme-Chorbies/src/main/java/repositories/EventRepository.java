
package repositories;

import domain.Chorbi;
import domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("select distinct c from Manager m join m.events e join e.chorbies c where m=?1")
    List<Chorbi> findAllChorbiesRelatedToManager(Manager manager);
}
