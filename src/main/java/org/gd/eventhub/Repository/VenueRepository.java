package org.gd.eventhub.Repository;

import org.gd.eventhub.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue,Integer> {

}
