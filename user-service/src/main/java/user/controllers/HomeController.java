package user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	@GetMapping("/hi")
	public ResponseEntity<String> home() {
    	System.out.println("CAME HERE HOME CONTROLLER");

		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
}
