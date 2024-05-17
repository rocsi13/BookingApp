package booking.app.BookingApp.Repository;

import booking.app.BookingApp.Model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

}
