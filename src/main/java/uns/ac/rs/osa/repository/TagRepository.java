package uns.ac.rs.osa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

	List<Tag> findByPosts(Post post);
	
	Tag findByName(String name);
}
