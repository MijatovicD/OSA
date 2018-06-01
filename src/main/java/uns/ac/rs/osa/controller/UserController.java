package uns.ac.rs.osa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uns.ac.rs.osa.dto.UserDTO;
import uns.ac.rs.osa.entity.User;
import uns.ac.rs.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/users")
public class UserController {

	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			userDTO.add(new UserDTO(u));
		}
		
		return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/all/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id){
		User user = userService.findOne(id);
		if(user != null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
			
		}

		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/add", consumes="application/json")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
		User user = new User();
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		
		user = userService.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
	}


	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<UserDTO> updatePost(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id){
		User user = userService.findOne(id);

		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}

		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());

		user = userService.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}

	@GetMapping(value="{username}/{password}")
	public ResponseEntity<UserDTO> login(@PathVariable("username") String username, @PathVariable("password") String password){
		User user = userService.findByUsernameAndPassword(username, password);
		if(user != null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
			
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="{username}")
	public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
		if(user != null) {
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
			
		}
		
		return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	}
}
