package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.UserLocation;
import booking.app.BookingApp.Repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


@Service
public class UserLocationService {
    @Value("${ipapi.access_key}")
    private String accessKey;
    private UserLocationRepository userLocationRepository;
    private RestTemplate restTemplate;
    public UserLocationService (RestTemplate restTemplate, UserLocationRepository userLocationRepository) {
        this.restTemplate = restTemplate;
        this.userLocationRepository = userLocationRepository;
    }
    public UserLocation getUserLocation(String ipAddress) {
        String url = "http://api.ipapi.com/" + ipAddress + "?access_key=" + accessKey;
        UserLocation userLocation = restTemplate.getForObject(url, UserLocation.class);
        if (userLocation != null) {
            userLocationRepository.save(userLocation);
        }
        return userLocation;
    }

    public static double calculateDistance(double latitudeUser, double longitudeUser, double latitudeHotel, double longitudeHotel) {
        double latDistance = Math.toRadians(latitudeHotel - latitudeUser);
        double longDistance = Math.toRadians(longitudeHotel - longitudeUser);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudeHotel)) + Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }
}
