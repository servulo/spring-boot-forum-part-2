package br.com.sprj.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sprj.forum.modelo.User;
import br.com.sprj.forum.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<User> user = userRepository.findByEmail(username);
	if (user.isPresent()) {
	    return user.get();
	}
	throw new UsernameNotFoundException("Invalid");

    }

}