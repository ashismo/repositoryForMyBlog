package com.ashish.poc.security.config;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ashish.poc.dao.RoleDaoImpl;
import com.ashish.poc.dao.UserDaoImpl;
import com.ashish.poc.model.Role;
import com.ashish.poc.model.Users;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	private static final Logger log = Logger.getLogger(CustomUserDetailsService.class); 

    @Autowired
    private UserDaoImpl userDaoImpl;
    
    @Autowired
    private RoleDaoImpl roleDaoImpl;
    
    @Autowired
    private PasswordEncodeDecodeUtil passwordEncodeDecodeUtil;
    
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
    	Users user = null;
    	try {
	        user = userDaoImpl.findByUserName(ssoId);
	        //user.setPassword(passwordEncodeDecodeUtil.base64encode(user.getPassword()));
	        log.debug("Requested User for authentication: "+user);
    	} catch (Exception e) {
    		log.error("Unable to find user", e);
    		user = null;
    	}
    	
    	if(user==null){
            log.info("User not found: " + ssoId);
            throw new UsernameNotFoundException("Username not found");
        }
        boolean isUserActive = true;
        boolean accountNotLocked = true;
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
                 isUserActive, true, true, accountNotLocked, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(Users user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        try {
        	if(user.getRoleId() != null) {
		        Role role = roleDaoImpl.findByRoleId(user.getRoleId());
		        if(role != null) {
		        	authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		        }
        	}
        } catch (Exception e) {
        	log.error("Unable to find role by roleId: " + user.getRoleId(), e);
        }
        log.debug("GrantedAuthority= :" + authorities);
        return authorities;
    }
     
}