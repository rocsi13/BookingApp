package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Repository.HotelRepository;
import booking.app.BookingApp.Repository.UserLocationRepository;
import booking.app.BookingApp.Service.HotelService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


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
}

