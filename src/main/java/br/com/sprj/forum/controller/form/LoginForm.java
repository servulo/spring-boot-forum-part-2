package br.com.sprj.forum.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class LoginForm {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken converter() {
	return new UsernamePasswordAuthenticationToken(username, password);
    }

}