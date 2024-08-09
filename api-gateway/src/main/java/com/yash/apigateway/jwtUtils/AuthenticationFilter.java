package com.yash.apigateway.jwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.net.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			System.out.println("CAME HERE");
			if (validator.isSecured.test(exchange.getRequest())) {
				
				// header contains token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to application");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				    throw new RuntimeException("Invalid or missing authorization header");
				}else if (authHeader != null && authHeader.startsWith("Bearer ")) {
					
					authHeader = authHeader.substring(7);
					System.out.println(authHeader);
				}
				try {
					System.out.println("came to validate");
					jwtTokenUtil.validateToken(authHeader);
					System.out.println("Validated");
				} catch (Exception e) {
					System.out.println("invalid access...!");
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access to application");
				}
			}
			
			return chain.filter(exchange);
		});
	}

	public static class Config {

	}

}
