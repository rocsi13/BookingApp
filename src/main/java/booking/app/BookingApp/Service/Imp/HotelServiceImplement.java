package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Repository.HotelRepository;
import booking.app.BookingApp.Repository.UserLocationRepository;
import booking.app.BookingApp.Service.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelServiceImplement implements HotelService {
    private UserLocationRepository userLocationRepository;
    private HotelRepository hotelRepository;

    public HotelServiceImplement(HotelRepository hotelRepository, UserLocationRepository userLocationRepository) {
        this.hotelRepository = hotelRepository;
        this.userLocationRepository = userLocationRepository;
    }

    @Override
    public Hotel findByName(String name) {
        return hotelRepository.findByName(name);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }
    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }
    @Override
    public Hotel findHotel(Long hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    @Override
    public void updateDistance(Long hotelId, Hotel hotel, UserLocation userLocation) {
        Hotel existingHotel = hotelRepository.getReferenceById(hotelId);
        existingHotel.setDistance(calculateDistance(userLocation.getLatitude(), userLocation.getLongitude(), existingHotel.getLatitude(), existingHotel.getLongitude()));
    }
    public static double calculateDistance(Double latitudeUser, Double longitudeUser, Double latitudeHotel, Double longitudeHotel) {
        double latDistance = Math.toRadians(latitudeHotel - latitudeUser);
        double longDistance = Math.toRadians(longitudeHotel - longitudeUser);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudeHotel)) + Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}

