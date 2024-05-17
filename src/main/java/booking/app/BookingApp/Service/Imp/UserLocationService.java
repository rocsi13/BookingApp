package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Repository.HotelRepository;
import booking.app.BookingApp.Repository.UserLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLocationService {
    private UserLocationRepository userLocationRepository;
    private HotelRepository hotelRepository;
    public List<UserLocation> getAllUserLocation() {
        return userLocationRepository.findAll();
    }


}
