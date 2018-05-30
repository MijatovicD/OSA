package uns.ac.rs.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.osa.entity.User;
import uns.ac.rs.osa.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findOne(Integer userId) {
		return userRepository.findById(userId).get();
	}
	
	
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	

	@Override
	public void remove(Integer id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = userRepository.findByUsername(username);
		if(user.getUsername().equals(username) && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	@Override
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if(user.getUsername().equals(username))
			return user;
		else
			return null;
	}
}
