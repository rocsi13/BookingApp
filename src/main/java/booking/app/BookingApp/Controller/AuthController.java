package booking.app.BookingApp.Controller;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.User;
import booking.app.BookingApp.Model.UserDto;
import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Repository.UserLocationRepository;
import booking.app.BookingApp.Service.HotelService;
import booking.app.BookingApp.Service.UserService;
import com.maxmind.geoip2.record.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserLocationRepository userLocationRepository;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(){
        System.out.println("home");
        return "home";
    }

    @GetMapping("/login")
    public String loginForm() {
        System.out.println("loginForm");
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        System.out.println("registration");
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        System.out.println("SaveUser");
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        System.out.println("dashboard");
        return "dashboard";
    }

    @GetMapping("/booking")
    public String booking(){
        System.out.println("Book a room");
        return "booking";
    }

    @RequestMapping(value = "/listHotels", method = RequestMethod.GET)
    public String listHotels(Model model){
        model.addAttribute("hotels", hotelService.findAll());

        System.out.println("Hotels");
        return "listHotels";
    }

    @RequestMapping("/admin")
    public String admin() {
        System.out.println("Admin");
        return "admin";
    }
    @GetMapping("/addHotel")
    public String addHotel(Model model){
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        System.out.println("addHotel");
        return "addHotel";
    }

    @PostMapping("/addHotel/save")
    public String registerHotel(@ModelAttribute("hotel") Hotel hotel, BindingResult result, Model model) {
        Hotel existingHotel = hotelService.findByName(hotel.getName());
        if (existingHotel != null) {
            result.rejectValue("name", null, "There is another hotel registered with this name");
        }
        if (result.hasErrors()) {
            model.addAttribute("hotel", hotel);
            return "addHotel";
        }
        hotelService.saveHotel(hotel);
        return "redirect:/dashboard";
    }

    @PostMapping("/userlocation")
    public UserLocation updateUserLocation(@RequestBody Location request, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        UserLocation location = new UserLocation();
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        location.setTimestamp(LocalDateTime.now());
        return userLocationRepository.save(location);
    }
    @GetMapping("/listHotels")
    public String getNearestHotel(@RequestParam double latitude, @RequestParam double longitude, Model model) {
        List<Hotel> nearestHotel = hotelService.findNearestHotel(latitude, longitude);
        model.addAttribute("hotels", nearestHotel);
        return "listHotels";
    }




}
