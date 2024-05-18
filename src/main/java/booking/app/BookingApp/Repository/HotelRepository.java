package booking.app.BookingApp.Repository;

import booking.app.BookingApp.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository <Hotel, Long> {
    Hotel findByName(String name);
    static Optional<Hotel> findHotel(Long hotelId) {
        return null;
    }

}
