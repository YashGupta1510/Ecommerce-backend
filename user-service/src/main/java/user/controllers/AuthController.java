package user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.entity.User;
import user.model.AuthenticationResponse;
import user.model.LoginRequestDTO;
import user.model.RegisterRequestDTO;
import user.services.UserService;
import user.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestDTO request) {
		this.doAuthenticate(request.getEmail(), request.getPassword());

		String token = this.jwtUtil.generateToken(request.getEmail());

		AuthenticationResponse response = new AuthenticationResponse.Builder().setJwtToken(token)
				.setUsername(request.getEmail()).build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequestDTO request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());

		User savedUser = userService.registerUser(user);
		
		return new ResponseEntity<>(savedUser, HttpStatus.OK);

	}

}
