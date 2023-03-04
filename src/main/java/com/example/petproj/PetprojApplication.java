package com.example.petproj;

import com.example.petproj.dto.UserDto;
import com.example.petproj.model.UserRole;
import com.example.petproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PetprojApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PetprojApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start(){






	}

}
