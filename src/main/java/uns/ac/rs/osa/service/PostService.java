package uns.ac.rs.osa.service;

import java.util.Date;
import java.util.List;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.entity.User;
import uns.ac.rs.osa.repository.PostRepository;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	PostRepository postRepository;
	

	@Override
	public Post findOne(Integer postId) {
		return postRepository.findById(postId).get();
	}
	
	
	@Override
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public void remove(Integer id) {
		postRepository.deleteById(id);
	}
	
	@Override
	public List<Post> findByTag(Tag tag){
		return postRepository.findByTags(tag);
	}

	@Override
	public List<Post> findByUser(User user){
		return postRepository.findByUser(user);
	}

	@Override
	public List<Post> findByUsersName(User userName){
		return postRepository.findByUserName(userName);
	}

	@Override
	public List<Post> findAllByOrderByDateDesc(){
		return postRepository.findAllByOrderByDateDesc();
	}

	@Override
	public List<Post> findAllOrderByLikesDesc(){
		return postRepository.findAllByOrderByLikesDesc();
	}

}
