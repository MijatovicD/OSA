package uns.ac.rs.osa.controller;

import java.util.*;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import uns.ac.rs.osa.dto.PostDTO;
import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.service.PostServiceInterface;
import uns.ac.rs.osa.service.TagServiceInterface;
import uns.ac.rs.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/posts")
public class PostController {

	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private TagServiceInterface tagService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<PostDTO>> getPosts(){
		List<Post> posts = postService.findAll();
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		for (Post p : posts) {
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable("id") Integer id){
		Post post = postService.findOne(id);
		if(post != null) {
			return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);	
		}
		return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping(value="/tag/{id}")
	public ResponseEntity<List<PostDTO>> getPostByTag(@PathVariable("id") Integer id){
		List<Post> posts = postService.findByTag(tagService.findOne(id));
		List<PostDTO> postDTO = new ArrayList<>();
		for(Post p : posts) {
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/add", consumes="application/json")
	public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO){
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setPhoto(postDTO.getPhoto());
		post.setDate(postDTO.getDate());
		post.setLikes(postDTO.getLikes());
		post.setDislikes(postDTO.getDislikes());
		post.setLongitude(postDTO.getLongitude());
		post.setLatitude(postDTO.getLatitude());
		post.setUser(userService.findOne(postDTO.getUser().getId()));
		
		post = postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/update/{id}", consumes="application/json")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("id") Integer id){
		Post post = postService.findOne(id);
		
		if(post == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}

		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setDate(postDTO.getDate());
		post.setLikes(postDTO.getLikes());
		post.setDislikes(postDTO.getDislikes());
		post.setLongitude(postDTO.getLongitude());
		post.setLatitude(postDTO.getLatitude());
		post.setUser(userService.findOne(postDTO.getUser().getId()));
		
		post = postService.save(post);
		return new ResponseEntity<PostDTO>(new PostDTO(post), HttpStatus.OK);
	}
	
	
	@PutMapping(value="/addTag/{postId}/{tagId}")
	public ResponseEntity<PostDTO> addTagInPost(@PathVariable("postId") int postId,@PathVariable("tagId") int tagId){
		Post post = postService.findOne(postId);
		Tag tag = tagService.findOne(tagId);

		if(post == null || tag == null) {
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}

		post.getTags().add(tag);
		tag.getPosts().add(post);

		post = postService.save(post);
		tag = tagService.save(tag);
		return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
	}


	@RequestMapping(value="/searchTags/{name}")
	public ResponseEntity<List<PostDTO>> searchPostByTags(@PathVariable("name") String name){
		List<Post> posts = postService.findByTag(tagService.findByName(name));
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		for (Post p : posts) {
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}

	@RequestMapping(value="/searchUsers/{name}")
	public ResponseEntity<List<PostDTO>> searchPostByUsers(@PathVariable("name") String name){
		List<Post> posts = postService.findByUser(userService.findByUsername(name));
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		for (Post p : posts) {
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Integer id){
		Post post = postService.findOne(id);
		if(post != null) {
			postService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value="date")
	public ResponseEntity<List<PostDTO>> findAllByDate(){
		List<Post> posts = postService.findAllByOrderByDateAsc();
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		for (Post p : posts) {
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}

	@GetMapping(value="like")
	public ResponseEntity<List<PostDTO>> findAllByLike(){
		List<Post> posts = postService.findAllOrderByLikesAsc();
		List<PostDTO> postDTO = new ArrayList<PostDTO>();
		for (Post p : posts){
			postDTO.add(new PostDTO(p));
		}
		return new ResponseEntity<List<PostDTO>>(postDTO, HttpStatus.OK);
	}

}
