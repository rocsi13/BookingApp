package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Repository.UserLocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserLocationService {

    private UserLocationRepository userLocationRepository;
    private RestTemplate restTemplate;
    public UserLocationService (RestTemplate restTemplate, UserLocationRepository userLocationRepository) {
        this.restTemplate = restTemplate;
        this.userLocationRepository = userLocationRepository;
    }
    public UserLocation getUserLocation(String ipAddress) {
        String url = "https://api.geoapify.com/v1/ipinfo?&apiKey=2918a1c98d8d42cea35032478fd88342";
        UserLocation userLocation = restTemplate.getForObject(url, UserLocation.class);
        if (userLocation != null) {
            userLocationRepository.save(userLocation);
            System.out.println(userLocationRepository);
        }
        return userLocation;
    }


}
