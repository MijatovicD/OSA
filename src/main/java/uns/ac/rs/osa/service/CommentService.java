package uns.ac.rs.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.osa.entity.Comment;
import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.repository.CommentRepository;
import uns.ac.rs.osa.repository.UserRepository;

@Service
public class CommentService implements CommentServiceInterface {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Comment findOne(Integer commentId) {
		return commentRepository.findById(commentId).get();
	}
	
	@Override
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}
	
	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	@Override
	public List<Comment> findByPost(Post post){
		return commentRepository.findByPost(post);
	}

	@Override
	public List<Comment> findAllByOrderByDateAsc() {
		return commentRepository.findAllByOrderByDateAsc();
	}

	@Override
	public List<Comment> findAllByOrderByLikesAsc(){
		return  commentRepository.findAllByOrderByLikesAsc();
	}
	@Override
	public void remove(Integer id){
		commentRepository.deleteById(id);
	}

}
