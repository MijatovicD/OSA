package uns.ac.rs.osa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.osa.entity.Comment;
import uns.ac.rs.osa.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByPost(Post post);

	List<Comment> findAllByOrderByDateAsc();

	List<Comment> findAllByOrderByLikesAsc();
}
