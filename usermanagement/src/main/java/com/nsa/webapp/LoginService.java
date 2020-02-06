package com.nsa.webapp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService extends UserDetailsService{
	
//	private UserRepository repo;
//	
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user= repo.findByUsername(username);
//		if(user==null) {
//			throw new UsernameNotFoundException("USer 404");
//		}
//		return new LoginServiceImpl(user);
//	}

}
