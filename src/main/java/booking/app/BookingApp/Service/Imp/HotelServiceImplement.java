package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Repository.HotelRepository;
import booking.app.BookingApp.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImplement implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public HotelServiceImplement(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
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
    public List<Hotel> findNearestHotel(double userLocationLatitude, double userLocationLongitude) {
        return hotelRepository.findAll().stream().sorted(Comparator.comparingDouble(hotel -> calculateDistance(userLocationLatitude, userLocationLongitude, hotel.getLatitude(), hotel.getLongitude())))
                .collect(Collectors.toList());
    }

    public static double calculateDistance(double latitudeUser, double longitudeUser, double latitudeHotel, double longitudeHotel) {
        double latDistance = Math.toRadians(latitudeHotel - latitudeUser);
        double longDistance = Math.toRadians(longitudeHotel - longitudeUser);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudeHotel)) + Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }


}
