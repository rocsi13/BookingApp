package booking.app.BookingApp.Controller;

import booking.app.BookingApp.Model.Hotel;
import booking.app.BookingApp.Model.User;
import booking.app.BookingApp.Model.UserDto;
import booking.app.BookingApp.Service.HotelService;
import booking.app.BookingApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;

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

}
