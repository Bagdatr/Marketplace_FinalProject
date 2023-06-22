package kz.rakhimov.marketplace_java_final_project.services;

import kz.rakhimov.marketplace_java_final_project.entities.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDto);
    List<ItemDto> getAllItems();
    ItemDto updateItem(ItemDto updatedItemDto);
    void deleteItem(Long id);
    ItemDto getItem(Long id);
    List<ItemDto> findAllItemSearch(String name);
    List<ItemDto> findAllByCategory(Long id);

}
