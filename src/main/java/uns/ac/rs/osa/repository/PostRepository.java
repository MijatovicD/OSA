package uns.ac.rs.osa.repository;


import java.util.List;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.osa.entity.Post;
import uns.ac.rs.osa.entity.Tag;
import uns.ac.rs.osa.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByTags(Tag tagId);

	List<Post> findByUser(User userId);

	List<Post> findAllByOrderByDateDesc();

	List<Post> findAllByOrderByLikesDesc();

	List<Post> findByTagsName(Tag tagName);

	List<Post> findByUserName(User userName);
}
	