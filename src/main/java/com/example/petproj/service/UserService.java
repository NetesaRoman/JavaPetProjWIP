package com.example.petproj.service;

import com.example.petproj.dto.UserButtonDto;
import com.example.petproj.dto.UserDto;
import com.example.petproj.dto.VoteThreadButtonDto;
import com.example.petproj.model.User;
import com.example.petproj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public User findById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<UserButtonDto> findAllForButtons(){
        List<UserButtonDto> buttons = new ArrayList<>();
        userRepository.findAll().forEach(user -> buttons.add(makeButtonDto(user)));

        return buttons;
    }

    public UserButtonDto makeButtonDto(User user){
        UserButtonDto userButtonDto = new UserButtonDto();

        userButtonDto.setId(user.getId());
        userButtonDto.setName(user.getName());
        userButtonDto.setSecondName(user.getSecondName());
        userButtonDto.setImageData(new String(user.getImageData()));

        return userButtonDto;
    }
}