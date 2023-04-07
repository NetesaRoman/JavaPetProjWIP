package com.example.petproj.service;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.User;
import com.example.petproj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 *
 * @author Roman Netesa
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public boolean hasUserByName(String name) {
        return userRepository.existsUserByName(name);
    }

    public User registerNewUserAccount(UserDto userDto) {


        // the rest of the registration operation
        User user = new User();
        user.setName(userDto.getName());
        user.setSecondName(userDto.getSecondName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setImageData(userDto.getImageData());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findByUserName(String username) {
        return userRepository.findByName(username);
    }

    public void updateUser(Integer id, String name, String surname, String phone, byte[] byteArr) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setImageData(byteArr);
            user.setName(name);
            user.setPhone(phone);
            user.setSecondName(surname);

            userRepository.save(user);
        }

    }
}
