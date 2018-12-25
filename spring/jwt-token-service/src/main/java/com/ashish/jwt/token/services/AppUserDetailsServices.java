package com.ashish.jwt.token.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ashish.jwt.token.db.model.User;
import com.ashish.jwt.token.db.repositories.AppUserRepository;

@Component
public class AppUserDetailsServices implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("The username %username doesn't exist.", username));
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		});
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for(int i = 0; i < 10; i++) {
			System.out.println(encoder.encode("jwtpass"));
		}
		return userDetails;
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for(int i = 0; i < 10; i++) {
			System.out.println(encoder.encode("jwtpass"));
		}
	}

}
