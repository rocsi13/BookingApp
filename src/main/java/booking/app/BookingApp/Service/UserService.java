package booking.app.BookingApp.Service;

import booking.app.BookingApp.Model.User;
import booking.app.BookingApp.Model.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByEmail(String email);
}
