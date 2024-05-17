package booking.app.BookingApp.Repository;

import booking.app.BookingApp.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository <Hotel, Long> {
    Hotel findByName(String name);
}
