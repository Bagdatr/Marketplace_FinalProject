package kz.rakhimov.marketplace_java_final_project;

import kz.rakhimov.marketplace_java_final_project.entities.*;
import kz.rakhimov.marketplace_java_final_project.mapper.CategoryMapper;
import kz.rakhimov.marketplace_java_final_project.mapper.ItemMapper;
import kz.rakhimov.marketplace_java_final_project.repositories.CategoryRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.CommentRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.ItemRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.OrderRepository;
import kz.rakhimov.marketplace_java_final_project.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarketplaceJavaFinalProjectApplicationTests {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAllCategories(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto categoryDto1 = CategoryDto.builder()
				.name("Спортивные товары")
				.build();
		CategoryDto categoryDto2 = CategoryDto.builder()
				.name("Товары для дома")
				.build();
		CategoryDto checkReturnCategoryDto1 = categoryService.addCategory(categoryDto1);
		CategoryDto checkReturnCategoryDto2 = categoryService.addCategory(categoryDto2);
		Assertions.assertNotNull(checkReturnCategoryDto1);
		Assertions.assertNotNull(checkReturnCategoryDto2);
		List<CategoryDto> categories = categoryService.getAllCategories();
		Assertions.assertNotNull(categories);
	}

	@Test
	void addCategory(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto categoryDto = CategoryDto.builder()
				.name("Спортивные товары")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(categoryDto);
		Assertions.assertNotNull(checkReturnCategoryDto);
		List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
		Assertions.assertNotNull(categoryDtoList);
		Assertions.assertTrue(categoryDtoList.size() > 0);
		Assertions.assertEquals(checkReturnCategoryDto.getId(), categoryDtoList.get(0).getId());
		Assertions.assertEquals(checkReturnCategoryDto.getName(), categoryDtoList.get(0).getName());
	}

	@Test
	void deleteCategory(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto categoryDto = CategoryDto.builder()
				.name("Спортивные товары")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(categoryDto);
		Assertions.assertNotNull(checkReturnCategoryDto);
		categoryService.deleteCategory(checkReturnCategoryDto.getId());
		List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
		Assertions.assertTrue(categoryDtoList.size() == 0);
	}

	@Test
	void addComment(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		CommentDto commentDto = CommentDto.builder()
				.author("testAuthor")
				.text("testText")
				.item(itemMapper.mapToEntity(checkReturnItemDto))
				.build();
		CommentDto checkReturnCommentDto = commentService.addComment(commentDto);
		Assertions.assertNotNull(checkReturnCommentDto);
		List<CommentDto> commentDtoList = commentService.getAllCommentsByItemId(checkReturnItemDto.getId());
		Assertions.assertNotNull(commentDtoList);
		Assertions.assertTrue(commentDtoList.size() > 0);
		Assertions.assertEquals(checkReturnCommentDto.getId(), commentDtoList.get(0).getId());
		Assertions.assertEquals(checkReturnCommentDto.getAuthor(), commentDtoList.get(0).getAuthor());
		Assertions.assertEquals(checkReturnCommentDto.getText(), commentDtoList.get(0).getText());
	}

	@Test
	void deleteComment(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		CommentDto commentDto = CommentDto.builder()
				.author("testAuthor")
				.text("testText")
				.item(itemMapper.mapToEntity(checkReturnItemDto))
				.build();
		CommentDto checkReturnCommentDto = commentService.addComment(commentDto);
		Assertions.assertNotNull(checkReturnCommentDto);
		commentService.deleteComment(checkReturnItemDto.getId());
		List<CommentDto> commentDtoList = commentService.getAllCommentsByItemId(checkReturnItemDto.getId());
		Assertions.assertTrue(commentDtoList.size() == 0);
	}

	@Test
	void getAllCommentsByItemId(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		CommentDto commentDto1 = CommentDto.builder()
				.author("testAuthor1")
				.text("testText1")
				.item(itemMapper.mapToEntity(checkReturnItemDto))
				.build();
		CommentDto commentDto2 = CommentDto.builder()
				.author("testAuthor2")
				.text("testText2")
				.item(itemMapper.mapToEntity(checkReturnItemDto))
				.build();
		CommentDto checkReturnCommentDto1 = commentService.addComment(commentDto1);
		CommentDto checkReturnCommentDto2 = commentService.addComment(commentDto2);
		Assertions.assertNotNull(checkReturnCommentDto1);
		Assertions.assertNotNull(checkReturnCommentDto2);
		List<CommentDto> commentDtoList = commentService.getAllCommentsByItemId(checkReturnItemDto.getId());
		Assertions.assertTrue(commentDtoList.size() == 2);
	}

	@Test
	void deleteOneComment(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		CommentDto commentDto = CommentDto.builder()
				.author("testAuthor")
				.text("testText")
				.item(itemMapper.mapToEntity(checkReturnItemDto))
				.build();
		CommentDto checkReturnCommentDto = commentService.addComment(commentDto);
		Assertions.assertNotNull(checkReturnCommentDto);
		commentService.deleteOneComment(checkReturnCommentDto.getId());
		List<CommentDto> commentDtoList = commentService.getAllCommentsByItemId(checkReturnItemDto.getId());
		Assertions.assertTrue(commentDtoList.size() == 0);
	}

	@Test
	void addItem(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		Assertions.assertNotNull(checkReturnItemDto);
		Assertions.assertEquals(checkReturnItemDto.getName(), testItemDto.getName());
		Assertions.assertEquals(checkReturnItemDto.getCategory(), testItemDto.getCategory());
		Assertions.assertEquals(checkReturnItemDto.getDescription(), testItemDto.getDescription());
		Assertions.assertEquals(checkReturnItemDto.getPrice(), testItemDto.getPrice());
		Assertions.assertEquals(checkReturnItemDto.getDiscount(), testItemDto.getDiscount());
		Assertions.assertEquals(checkReturnItemDto.getRating(), testItemDto.getRating());
		Assertions.assertEquals(checkReturnItemDto.getQuantity(), testItemDto.getQuantity());
	}

	@Test
	void getAllItems(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription2")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		ItemDto checkReturnItemDto2 =  itemService.addItem(testItemDto2);
		Assertions.assertNotNull(checkReturnItemDto1);
		Assertions.assertNotNull(checkReturnItemDto2);
		List<ItemDto> itemDtoList = itemService.getAllItems();
		Assertions.assertNotNull(itemDtoList);
	}

	@Test
	void updateItem(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		Assertions.assertNotNull(checkReturnItemDto1);
		CategoryDto testCategoryDto2 = CategoryDto.builder()
				.name("testCategoryDto2")
				.build();
		CategoryDto checkReturnCategoryDto2 = categoryService.addCategory(testCategoryDto2);
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto2))
				.description("testDescription2")
				.price(101.0)
				.discount(11.0)
				.rating(4)
				.quantity(51)
				.build();
		Item item = itemRepository.findAllById(checkReturnItemDto1.getId());
		Assertions.assertNotNull(item);
		item.setName(testItemDto2.getName());
		item.setCategory(testItemDto2.getCategory());
		item.setDescription(testItemDto2.getDescription());
		item.setPrice(testItemDto2.getPrice());
		item.setDiscount(testItemDto2.getDiscount());
		item.setRating(testItemDto2.getRating());
		item.setQuantity(testItemDto2.getQuantity());
		ItemDto checkReturnItemDto2 = itemMapper.mapToDto(itemRepository.save(item));
		Assertions.assertNotNull(checkReturnItemDto2);
		Assertions.assertEquals(checkReturnItemDto2.getName(), testItemDto2.getName());
		Assertions.assertEquals(checkReturnItemDto2.getCategory().getName(), testItemDto2.getCategory().getName());
		Assertions.assertEquals(checkReturnItemDto2.getDescription(), testItemDto2.getDescription());
		Assertions.assertEquals(checkReturnItemDto2.getPrice(), testItemDto2.getPrice());
		Assertions.assertEquals(checkReturnItemDto2.getDiscount(), testItemDto2.getDiscount());
		Assertions.assertEquals(checkReturnItemDto2.getRating(), testItemDto2.getRating());
		Assertions.assertEquals(checkReturnItemDto2.getQuantity(), testItemDto2.getQuantity());
	}

	@Test
	void deleteItem(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		Assertions.assertNotNull(checkReturnItemDto);
		itemService.deleteItem(checkReturnItemDto.getId());
		List<ItemDto> itemDtoList = itemService.getAllItems();
		Assertions.assertTrue(itemDtoList.size() == 0);
	}

	@Test
	void getItem(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		Assertions.assertNotNull(checkReturnItemDto);
		ItemDto itemDto = itemService.getItem(checkReturnItemDto.getId());
		Assertions.assertEquals(itemDto.getId(), checkReturnItemDto.getId());
	}

	@Test
	void findAllItemSearch(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription2")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		ItemDto checkReturnItemDto2 =  itemService.addItem(testItemDto2);
		Assertions.assertNotNull(checkReturnItemDto1);
		Assertions.assertNotNull(checkReturnItemDto2);
		String searchWord = "test";
		List<ItemDto> itemDtoList = itemService.findAllItemSearch(searchWord);
		Assertions.assertTrue(itemDtoList.size() == 2);
	}

	@Test
	void findAllByCategory(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription2")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		ItemDto checkReturnItemDto2 =  itemService.addItem(testItemDto2);
		Assertions.assertNotNull(checkReturnItemDto1);
		Assertions.assertNotNull(checkReturnItemDto2);
		List<ItemDto> itemDtoList = itemService.findAllByCategory(checkReturnCategoryDto.getId());
		Assertions.assertNotNull(itemDtoList);
		Assertions.assertTrue(itemDtoList.size() == 2);
	}

	@Test
	void addItemToOrder(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto = ItemDto.builder()
				.name("testName")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto =  itemService.addItem(testItemDto);
		Assertions.assertNotNull(checkReturnItemDto);
		String testEmail = "johnsmith@gmail.com";
		Order order = orderService.addItemToOrder(checkReturnItemDto.getId(), testEmail);
		Assertions.assertNotNull(order);
		orderRepository.save(order);
		List<Order> checkOrderSaved = orderRepository.findAll();
		Assertions.assertNotNull(checkOrderSaved);
		Assertions.assertEquals(checkOrderSaved.get(0).getItem().getId(), checkReturnItemDto.getId());
		Assertions.assertEquals(checkOrderSaved.get(0).getEmail(), testEmail);
	}

	@Test
	void getAllItemsInOrder(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription2")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		ItemDto checkReturnItemDto2 =  itemService.addItem(testItemDto2);
		Assertions.assertNotNull(checkReturnItemDto1);
		Assertions.assertNotNull(checkReturnItemDto2);
		String testEmail = "johnsmith@gmail.com";
		orderService.addItemToOrder(checkReturnItemDto1.getId(), testEmail);
		orderService.addItemToOrder(checkReturnItemDto2.getId(), testEmail);
		List<Item> itemsList = orderService.getAllItemsInOrder(testEmail);
		Assertions.assertTrue(itemsList.size() == 2);
	}

	@Test
	void deleteItemFromOrder(){
		orderRepository.deleteAll();
		commentRepository.deleteAll();
		itemRepository.deleteAll();
		categoryRepository.deleteAll();
		CategoryDto testCategoryDto = CategoryDto.builder()
				.name("testCategoryDto")
				.build();
		CategoryDto checkReturnCategoryDto = categoryService.addCategory(testCategoryDto);
		ItemDto testItemDto1 = ItemDto.builder()
				.name("testName1")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription1")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto testItemDto2 = ItemDto.builder()
				.name("testName2")
				.category(categoryMapper.mapToEntity(checkReturnCategoryDto))
				.description("testDescription2")
				.price(100.0)
				.discount(10.0)
				.rating(5)
				.quantity(50)
				.build();
		ItemDto checkReturnItemDto1 =  itemService.addItem(testItemDto1);
		ItemDto checkReturnItemDto2 =  itemService.addItem(testItemDto2);
		Assertions.assertNotNull(checkReturnItemDto1);
		Assertions.assertNotNull(checkReturnItemDto2);
		String testEmail = "johnsmith@gmail.com";
		Order checkOrder1 = orderService.addItemToOrder(checkReturnItemDto1.getId(), testEmail);
		Order checkOrder2 = orderService.addItemToOrder(checkReturnItemDto2.getId(), testEmail);
		Assertions.assertNotNull(checkOrder1);
		Assertions.assertNotNull(checkOrder2);
		List<Item> testItemList = orderService.getAllItemsInOrder(testEmail);
		Assertions.assertTrue(testItemList.size() == 2);
		orderService.deleteItemFromOrder(checkReturnItemDto1.getId());
		List<Item> testItemListAfterDeletingOneItem = orderService.getAllItemsInOrder(testEmail);
		Assertions.assertTrue(testItemListAfterDeletingOneItem.size() == 1);
	}
}