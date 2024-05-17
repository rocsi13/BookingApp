package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Repository.HotelRepository;
import booking.app.BookingApp.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
