package org.gd.eventhub.Repository;

import org.gd.eventhub.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
