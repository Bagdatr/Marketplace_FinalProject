package kz.rakhimov.marketplace_java_final_project.controllers;

import kz.rakhimov.marketplace_java_final_project.entities.CategoryDto;
import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.ItemDto;
import kz.rakhimov.marketplace_java_final_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping(value="/main")
public class HomeController {
    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ItemService itemService;

    @Autowired
    public FileService fileService;

    @Autowired
    public MainUserService userService;

    @Autowired
    public CommentService commentService;

    @Autowired
    public OrderService orderService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/home")
    public String openHome(Model model){
        List<ItemDto> itemsList = itemService.getAllItems();
        model.addAttribute("itemsList", itemsList);
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "home";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/home-search")
    public String openSearch(Model model,
                             @RequestParam(name="key") String name){
        List<ItemDto> itemsList = itemService.findAllItemSearch(name);
        model.addAttribute("itemsList", itemsList);
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "home-search";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/home-sort-by-category")
    public String openSortByCategory(Model model,
                                     @RequestParam(name="categoryId") Long id){
        List<ItemDto> itemsList = itemService.findAllByCategory(id);
        model.addAttribute("itemsList", itemsList);
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "home-sort-by-category";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value="/add-item")
    public String openAddItem(Model model){
        List<CategoryDto> categoriesList = categoryService.getAllCategories();
        model.addAttribute("categoriesList", categoriesList);
        List<ItemDto> itemDtoList = itemService.getAllItems();
        Long tempId = 0L;
        for (int i = 0; i < itemDtoList.size(); i++) {
            if(tempId < itemDtoList.get(i).getId()){
                tempId = itemDtoList.get(i).getId();
            }
        }
        Long idForNewItemPhoto = tempId + 1L;
        model.addAttribute("idForNewItemPhoto", idForNewItemPhoto);
        return "add-item";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value="/add-category")
    public String openAddCategory(Model model){
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        model.addAttribute("categoryDtoList", categoryDtoList);
        return "add-category";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value="/details/{id}")
    public String openDetails(@PathVariable("id") Long id,
                              Model model){
        model.addAttribute("itemDetails", itemService.getItem(id));
        model.addAttribute("itemsList", itemService.getAllItems());
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        model.addAttribute("commentsList", commentService.getAllCommentsByItemId(id));
        return "details";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping(value="/details-user/{id}")
    public String openDetailsUser(@PathVariable("id") Long id,
                                  Model model){
        model.addAttribute("itemDetails", itemService.getItem(id));
        model.addAttribute("itemsList", itemService.getAllItems());
        model.addAttribute("categoriesList", categoryService.getAllCategories());
        model.addAttribute("commentsList", commentService.getAllCommentsByItemId(id));
        return "details-user";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value="/upload-file")
    public String uploadFilePost(@RequestParam(name="photo")MultipartFile file,
                                 @RequestParam(name="itemId") Long id){
        fileService.uploadFile(file, id);
        return "redirect:details/" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/category-menu")
    public String openCategoryMenu(Model model){
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        return "home";
    }

    @GetMapping(value="/login")
    public String openLogin(){
        return "login";
    }

    @GetMapping(value="/register")
    public String openRegister(){
        return "register";
    }

    @PostMapping(value="register")
    public String registerPost(@RequestParam("user-full-name") String fullName,
                               @RequestParam("user-email") String email,
                               @RequestParam("user-password") String password,
                               @RequestParam("user-re-password") String rePassword){
        String redirect = "";
        String check = userService.registerUser(fullName, email, password, rePassword);
        if(check.equals("user_registered_successfully")){
            redirect = "/main/login";
        }else if(check.equals("user_exists")){
            redirect = "/main/register?user_exists";
        }else{
            redirect = "/main/register?password_not_match";
        }
        return "redirect:" + redirect;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value="/change-password")
    public String openChangePassword(){
        return "change-password";
    }

    @PostMapping(value="/change-password")
    public String ChangePasswordPost(@RequestParam(name="user-email")String email,
                                     @RequestParam(name="user-old-password")String oldPassword,
                                     @RequestParam(name="user-new-password")String newPassword,
                                     @RequestParam(name="user-re-password")String rePassword){
        String redirect = "";
        String check = userService.changePassword(email,oldPassword,newPassword,rePassword);
        if(check.equals("password_has_been_changed_successfully")){
            redirect = "/main/change-password?password_has_been_changed_successfully";
        }else if(check.equals("new_password_not_match")){
            redirect = "/main/change-password?new_password_not_match";
        }else if(check.equals("incorrect_old_password")){
            redirect = "/main/change-password?incorrect_old_password";
        }else{
            redirect = "/main/change-password?user_not_found";
        }
        return "redirect:" + redirect;
    }

    @GetMapping(value="/403")
    public String openAccessDenied(){
        return "403";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/profile/{email}")
    public String openProfile(@PathVariable("email") String email, Model model){
        model.addAttribute("currentUser", userService.getUserByEmail(email));
        return "profile";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping(value="/order/{email}")
    public String openOrder(@PathVariable("email") String email, Model model){
        List<Item> itemsList = orderService.getAllItemsInOrder(email);
        model.addAttribute("itemsList", itemsList);
        double totalSumToPay = 0.0;
        for (int i = 0; i < itemsList.size(); i++) {
            totalSumToPay += itemsList.get(i).getDiscountPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedValue = decimalFormat.format(totalSumToPay);
        model.addAttribute("totalSumToPay", formattedValue);
        return "order";
    }
}