package org.gd.eventhub.Repository;

import org.gd.eventhub.Entity.Booking;
import org.gd.eventhub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByUser(User user);
}
