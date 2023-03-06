package com.example.petproj.service;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.User;
import com.example.petproj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
 *
 * @author Roman Netesa
 *
 */
@Service
@RequiredArgsConstructor
public class UserService {

//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;




    public boolean hasUserByName(String name) {
        return  userRepository.existsUserByName(name);
    }

    public User registerNewUserAccount(UserDto userDto)  {
        if (emailExists(userDto.getEmail())) {
//            throw new UserAlreadyExistException("There is an account with that email address: "
//                    + userDto.getEmail());
        }

        // the rest of the registration operation
        User user = new User();
        user.setName(userDto.getName());
        user.setSecondName(userDto.getSecondName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());

        return userRepository.save(user);
    }


    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
