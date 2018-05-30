package uns.ac.rs.osa.service;

import java.util.List;

import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;

public interface TagServiceInterface {
	
	List<Tag> findAll();
	
	Tag findOne(Integer id);
	
	Tag save(Tag tag);
	
	Tag findByName(String name);
	
	void remove(Integer id);
	
	List<Tag> findByPost(Post post);
}
