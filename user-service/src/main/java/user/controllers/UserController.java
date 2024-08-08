package user.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.entity.User;
import user.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }
    
}

