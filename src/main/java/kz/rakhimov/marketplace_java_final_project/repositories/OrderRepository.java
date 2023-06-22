package kz.rakhimov.marketplace_java_final_project.repositories;

import jakarta.transaction.Transactional;
import kz.rakhimov.marketplace_java_final_project.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByEmail(String userEmail);

    void deleteByItemId(Long itemId);
}
