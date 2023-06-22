package kz.rakhimov.marketplace_java_final_project.repositories;

import jakarta.transaction.Transactional;
import kz.rakhimov.marketplace_java_final_project.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findAllById(Long id);

    List<Item> findAllByNameContainsIgnoreCase (String name);

    List<Item> findAllByCategoryId(Long id);
}
