package com.company.books.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.dao.IUserDao;
import com.company.books.backend.model.Usuario;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private IUserDao userDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
		
		Usuario user = this.userDao.findByUserName(username);
		
		if(user == null) {
			log.error("Error, el usuario no existe");
			throw new UsernameNotFoundException("Error, el usuario no existe");
		}
		
		List<GrantedAuthority> authorities = user.getRoleList()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.peek( authority-> log.info("Role: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(user.getUserName(), user.getPassword(), user.getActive(), true, true, true, authorities);
	}

}
