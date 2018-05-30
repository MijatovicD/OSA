package uns.ac.rs.osa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uns.ac.rs.osa.dto.CommentDTO;
import uns.ac.rs.osa.dto.PostDTO;
import uns.ac.rs.osa.entity.Comment;
import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.service.CommentServiceInterface;
import uns.ac.rs.osa.service.PostServiceInterface;
import uns.ac.rs.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/comments")
public class CommentController {

	
	@Autowired
	CommentServiceInterface commentService;
	
	@Autowired
	PostServiceInterface postService;
	
	@Autowired
	UserServiceInterface userService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<CommentDTO>> getComments(){
		List<Comment> comments = commentService.findAll();
		List<CommentDTO> commentDTO = new ArrayList<CommentDTO>();
		for (Comment c : comments) {
			commentDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CommentDTO> findById(@PathVariable("id") Integer id){
		Comment comment = commentService.findOne(id);
		if(comment != null) {
			return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.OK);	
		}
		return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping(value="/add", consumes="application/json")
	public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){
		Comment comment = new Comment();
		comment.setTitle(commentDTO.getTitle());
		comment.setDescription(commentDTO.getDescription());
		comment.setDate(commentDTO.getDate());
		comment.setLikes(commentDTO.getLikes());
		comment.setDislikes(commentDTO.getDislikes());
		comment.setUser(userService.findOne(commentDTO.getUser().getId()));
		comment.setPost(postService.findOne(commentDTO.getPost().getId()));
		
		comment = commentService.save(comment);
		return new ResponseEntity<CommentDTO>(new CommentDTO(comment), HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/post/{id}")
	public ResponseEntity<List<CommentDTO>> getCommentByPost(@PathVariable("id")Integer id){
		List<Comment> comments = commentService.findByPost(postService.findOne(id));
		List<CommentDTO>commentDTO = new ArrayList<>();
		for (Comment comment : comments) {
			commentDTO.add(new CommentDTO(comment)); }
	        return new ResponseEntity<List<CommentDTO>>(commentDTO,HttpStatus.OK);
	    }

	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id){
		Comment comment = commentService.findOne(id);
		if(comment != null) {
			commentService.remove(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping(value="date")
	public ResponseEntity<List<CommentDTO>> findAllByDate(){
		List<Comment> comments = commentService.findAllByOrderByDateAsc();
		List<CommentDTO> commentDTO = new ArrayList<CommentDTO>();
		for (Comment c : comments){
			commentDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentDTO, HttpStatus.OK);
	}dsa

	@GetMapping(value="like")
	public ResponseEntity<List<CommentDTO>> findAllByLike(){
		List<Comment> comments = commentService.findAllByOrderByLikesAsc();
		List<CommentDTO> commentDTO = new ArrayList<CommentDTO>();
		for (Comment c : comments){
			commentDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<List<CommentDTO>>(commentDTO, HttpStatus.OK);
	}
}
