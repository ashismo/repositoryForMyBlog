package com.org.coop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.society.data.customer.entities.Staff;
import com.org.coop.society.data.customer.repositories.StaffRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
 
    @Autowired
    private StaffRepository staffRepository;
     
    @Autowired
    private LoginService loginService;
    
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        Staff user = staffRepository.findByUserName(ssoId);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), "ashish", 
                 /*user.getState().equals("Active")*/true, true, true, true, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(Staff user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String role = loginService.getRole("ashish", "ashish").toUpperCase();
//        for(UserProfile userProfile : user.getUserProfiles()){
//            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
//        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
     
}