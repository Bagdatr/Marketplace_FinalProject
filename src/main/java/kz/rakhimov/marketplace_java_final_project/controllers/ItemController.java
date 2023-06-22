package kz.rakhimov.marketplace_java_final_project.controllers;

import kz.rakhimov.marketplace_java_final_project.entities.ItemDto;
import kz.rakhimov.marketplace_java_final_project.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping
    public ItemDto addItemPost(@RequestBody ItemDto itemDto){
        return itemService.addItem(itemDto);
    }

    @GetMapping(value="/{id}")
    public ItemDto getItem(@PathVariable("id") Long id){
        return itemService.getItem(id);
    }

    @PutMapping
    public ItemDto updateItemPost(@RequestBody ItemDto itemDto){
        return itemService.updateItem(itemDto);
    }

    @DeleteMapping(value="/{id}")
    void deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);
    }
}
