package com.glearning.students.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.glearning.students.dao.StudentRepository;
import com.glearning.students.dao.UserRepository;
import com.glearning.students.model.Role;
import com.glearning.students.model.Student;
import com.glearning.students.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void initializeData(ApplicationReadyEvent readyEvent) {
		
		Student ravi = new Student("Ravi", "Kumar", "Java", "India");
		Student harish = new Student("Harish", "Prasad", "Python", "India");
		Student ramesh = new Student("Ramesh", "Patil", "NodeJs", "India");
		Student krishna = new Student("Krishna", "Patel", "Java", "India");
		
		this.studentRepository.save(ravi);
		this.studentRepository.save(harish);
		this.studentRepository.save(ramesh);
		this.studentRepository.save(krishna);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUsersData(ApplicationReadyEvent readyEvent) {
		
			User ashutosh = new User("ashutosh", passwordEncoder.encode("welcome"));
			User ayushi = new User("ayushi", passwordEncoder.encode("welcome"));
			
			Role userRole = new Role("ROLE_USER");
			Role adminRole = new Role("ROLE_ADMIN");
			
			ayushi.addRole(userRole);
			
			ayushi.addRole(userRole);
			ayushi.addRole(adminRole);
			
			this.userRepository.save(ayushi);
			this.userRepository.save(ashutosh);
		
	}

}
