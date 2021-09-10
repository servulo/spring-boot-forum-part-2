package br.com.sprj.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sprj.forum.modelo.User;
import br.com.sprj.forum.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenFilterAuthentication extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository UserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	String token = getToken(request);
	boolean valid = tokenService.isValidToken(token);
	if (valid) {
	    authenticateClient(token);
	}
	filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
	Long userId = tokenService.getUserId(token);
	User user = UserRepository.findById(userId).get();
	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
		user.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
	String token = request.getHeader("Authorization");
	if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
	    return null;
	}
	return token.substring(7, token.length());
    }

}