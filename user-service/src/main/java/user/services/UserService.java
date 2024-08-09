package user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.entity.User;
import user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

	public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getUsers(){
    	return userRepository.findAll();
    }

	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}

