package kz.rakhimov.marketplace_java_final_project.services.impl;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.ItemDto;
import kz.rakhimov.marketplace_java_final_project.mapper.ItemMapper;
import kz.rakhimov.marketplace_java_final_project.repositories.ItemRepository;
import kz.rakhimov.marketplace_java_final_project.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setDiscount(itemDto.getDiscount());
        double discountPrice = itemDto.getPrice() - (itemDto.getPrice() * (itemDto.getDiscount()/100.0));
        item.setDiscountPrice(discountPrice);
        item.setRating(itemDto.getRating());
        item.setQuantity(itemDto.getQuantity());
        return itemMapper.mapToDto(itemRepository.save(item));
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.mapToDtoList(itemRepository.findAll());
    }

    @Override
    public ItemDto updateItem(ItemDto updatedItemDto) {
        Item item = itemRepository.findAllById(updatedItemDto.getId());
        item.setName(updatedItemDto.getName());
        item.setCategory(updatedItemDto.getCategory());
        item.setDescription(updatedItemDto.getDescription());
        item.setPrice(updatedItemDto.getPrice());
        item.setDiscount(updatedItemDto.getDiscount());
        double discountPrice = updatedItemDto.getPrice() - (updatedItemDto.getPrice() *
                (updatedItemDto.getDiscount()/100.0));
        item.setDiscountPrice(discountPrice);
        item.setRating(updatedItemDto.getRating());
        item.setQuantity(updatedItemDto.getQuantity());
        return itemMapper.mapToDto(itemRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ItemDto getItem(Long id) {
        Item item = itemRepository.findAllById(id);
        return itemMapper.mapToDto(item);
    }

    @Override
    public List<ItemDto> findAllItemSearch(String name) {
        List<ItemDto> itemDtoListSearch = itemMapper.mapToDtoList(itemRepository.findAllByNameContainsIgnoreCase(name));
        return itemDtoListSearch;
    }

    @Override
    public List<ItemDto> findAllByCategory(Long id) {
        List<ItemDto> itemDtoListByCategory = itemMapper.mapToDtoList(itemRepository.findAllByCategoryId(id));
        return itemDtoListByCategory;
    }

}
