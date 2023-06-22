package kz.rakhimov.marketplace_java_final_project.mapper;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto mapToDto(Item item);
    List<ItemDto> mapToDtoList(List<Item> items);

    Item mapToEntity(ItemDto itemDto);
}
