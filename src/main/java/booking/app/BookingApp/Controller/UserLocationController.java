package booking.app.BookingApp.Controller;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Service.HotelService;
import booking.app.BookingApp.Service.Imp.UserLocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class UserLocationController {
    @Autowired
    private UserLocationService userLocationService;

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public String getLocation(HttpServletRequest request, Model model, @RequestParam("hotelId") Long hotelId) {
        String ipAddress = request.getRemoteAddr();
        UserLocation userLocation = userLocationService.getUserLocation(ipAddress);
        Hotel hotel = hotelService.findHotel(hotelId);
        if (userLocation !=null && hotel != null) {
            double distance = userLocationService.calculateDistance(userLocation.getLatitude(), userLocation.getLongitude(), hotel.getLatitude(), hotel.getLongitude());
            model.addAttribute("distance", distance);
        }
        return "listHotels";
    }

}
