package booking.app.BookingApp.Service.Imp;

import booking.app.BookingApp.Model.*;
import booking.app.BookingApp.Repository.RoleRepository;
import booking.app.BookingApp.Repository.UserLocationRepository;
import booking.app.BookingApp.Repository.UserRepository;
import booking.app.BookingApp.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImplement(UserRepository userRepository,  RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ADMIN");
        if (role == null) {
            role = checkRoleExist();
        } else {
            role = roleRepository.findByName("USER");
            if (role == null) {
                role = userRole();
            }
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ADMIN");
        return roleRepository.save(role);
    }
    private Role userRole() {
        Role role = new Role();
        role.setName("USER");
        return roleRepository.save(role);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
