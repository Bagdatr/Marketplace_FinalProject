package kz.rakhimov.marketplace_java_final_project.repositories;

import jakarta.transaction.Transactional;

import kz.rakhimov.marketplace_java_final_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByEmail(String email);
}
