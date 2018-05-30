package uns.ac.rs.osa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.osa.dto.PostDTO;
import uns.ac.rs.osa.dto.TagDTO;
import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.repository.TagRepository;
import uns.ac.rs.osa.service.PostServiceInterface;
import uns.ac.rs.osa.service.TagServiceInterface;

@RestController
@RequestMapping(value="api/tags")
public class TagController {

	@Autowired
	private TagServiceInterface tagService;
	
	@Autowired
	private PostServiceInterface postService;
	
	@GetMapping(value="all")
	public ResponseEntity<List<TagDTO>> getTags(){
		List<Tag> tags = tagService.findAll();
		List<TagDTO> tagDTO = new ArrayList<TagDTO>();
		for (Tag t : tags) {
			tagDTO.add(new TagDTO(t));
		}
		return new ResponseEntity<List<TagDTO>>(tagDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TagDTO> findById(@PathVariable("id") Integer id){
		Tag tag = tagService.findOne(id);
		if(tag != null) {
			return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.OK);	
		}
		return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping(value="/add", consumes="application/json")
	public ResponseEntity<TagDTO> savaTag(@RequestBody TagDTO tagDTO){
		Tag tag = new Tag();
		tag.setName(tagDTO.getName());
		
		tag = tagService.save(tag);
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.CREATED);
	}
	
    @GetMapping(value="/post/{id}")
    public ResponseEntity<List<TagDTO>>getPostsByTag(@PathVariable("id") Integer id){
    		List<Tag> tags = tagService.findByPost(postService.findOne(id));
    		List<TagDTO> tagDTO = new ArrayList<>();
    		for(Tag t : tags) {
    			tagDTO.add(new TagDTO(t));
    		}
    		return new ResponseEntity<List<TagDTO>>(tagDTO, HttpStatus.OK);
    }
    
    @GetMapping(value="/{name}")
    public ResponseEntity<TagDTO> getTagByName(@PathVariable("name") String name){
    	Tag tag = tagService.findByName(name);
    	if(tag == null) {
    		return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
    	}else {
    		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.OK);
    	}
    }


    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") Integer id){
    	Tag tag = tagService.findOne(id);
    	if (tag !=null){
    		tagService.remove(id);
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	}else{
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    }
    
}
