package org.java.spring;

import org.java.spring.auth.conf.ApplicationConfig;
import org.java.spring.db.Role;
import org.java.spring.db.User;
import org.java.spring.serv.RoleService;
import org.java.spring.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestAuthApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TestAuthApplication.class, args);
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception{
		
		Role admin = new Role("ADMIN");
		roleService.save(admin);
		Role superadmin = new Role("SUPERADMIN");
		roleService.save(superadmin);
		
		String pwsd;
		
		pwsd = ApplicationConfig.passwordEncoder().encode("pswd");
		
		
		User u = new User("user1",pwsd);
		User u2 = new User("user2",pwsd);
		
		userService.save(u);
		userService.save(u2);
	
		
	}

}
