package uns.ac.rs.osa.service;

import java.util.List;

import uns.ac.rs.osa.entity.User;

public interface UserServiceInterface {

	User findOne(Integer userId);
	List<User> findAll();
	User save(User user);
	void remove(Integer id);
	User findByUsernameAndPassword(String Username, String password);
	User findByUsername(String username);
	
}
