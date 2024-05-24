package booking.app.BookingApp.Service;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.UserLocation;

import java.util.List;

public interface HotelService {
    void saveHotel(Hotel hotel);
    Hotel findByName (String name);
    
    List<Hotel> findAll();
    void updateDistance(Long hotelId, Hotel hotel, UserLocation userLocation);

    Hotel findHotel(Long hotelId);
}
