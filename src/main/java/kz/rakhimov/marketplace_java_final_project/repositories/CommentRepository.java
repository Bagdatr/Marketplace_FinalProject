package kz.rakhimov.marketplace_java_final_project.repositories;

import jakarta.transaction.Transactional;
import kz.rakhimov.marketplace_java_final_project.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItemId(Long id);
    void deleteByItemId(Long itemId);
}
