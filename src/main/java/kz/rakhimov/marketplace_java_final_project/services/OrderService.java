package kz.rakhimov.marketplace_java_final_project.services;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.Order;

import java.util.List;

public interface OrderService {
    Order addItemToOrder(Long itemId, String userEmail);

    List<Item> getAllItemsInOrder (String userEmail);

    void deleteItemFromOrder(Long itemId);
}