package booking.app.BookingApp.Service;

import booking.app.BookingApp.Model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    void saveHotel(Hotel hotel);
    Hotel findByName (String name);
    
    List<Hotel> findAll();

    Hotel findHotel(Long hotelId);
}
