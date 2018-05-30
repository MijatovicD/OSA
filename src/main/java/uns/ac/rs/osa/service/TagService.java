package uns.ac.rs.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.repository.TagRepository;

@Service
public class TagService implements TagServiceInterface{

	@Autowired
	TagRepository tagRepository;
	
	@Override
	public Tag findOne(Integer id){
		return tagRepository.findById(id).get();
	}
	
	@Override
	public List<Tag> findAll(){
		return tagRepository.findAll();
	}
	
	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}
	
	@Override
	public Tag findByName(String name){
		return tagRepository.findByName(name);
	}
	
	@Override 
	public void remove(Integer id){
		tagRepository.deleteById(id);
	}
	
	@Override
	public List<Tag> findByPost(Post post){
		return tagRepository.findByPosts(post);
	}
}
