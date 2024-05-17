package booking.app.BookingApp.Service;

import booking.app.BookingApp.Model.Hotel;

import java.util.List;

public interface HotelService {
    void saveHotel(Hotel hotel);
    Hotel findByName (String name);
    List<Hotel> findAll();

}
