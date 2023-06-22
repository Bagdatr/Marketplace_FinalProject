package kz.rakhimov.marketplace_java_final_project.services;

import kz.rakhimov.marketplace_java_final_project.entities.Permission;
import kz.rakhimov.marketplace_java_final_project.entities.User;
import kz.rakhimov.marketplace_java_final_project.repositories.PermissionRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public String registerUser(String fullName, String email, String password, String rePassword){
        String flag = "user_exists";
        User checkUser = userRepository.findAllByEmail(email);
        if(checkUser == null){
            flag = "password_not_match";
            if(password.equals(rePassword)){
                List<Permission> permissionList = new ArrayList<>();
                Permission permission = permissionRepository.findByRole("ROLE_USER");
                permissionList.add(permission);
                User user = User.builder()
                        .fullName(fullName)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .permissions(permissionList)
                        .build();
                if(userRepository.save(user) != null){
                    flag = "user_registered_successfully";
                }
            }
        }
        return flag;
    }

    public String changePassword(String email, String oldPassword, String newPassword, String rePassword){
        String flag = "user_not_found";
        User checkUser = userRepository.findAllByEmail(email);
        if(checkUser != null){
            flag = "incorrect_old_password";
            if(passwordEncoder.matches(oldPassword, checkUser.getPassword()) == true){
                flag = "new_password_not_match";
                if(newPassword.equals(rePassword)){
                    User user = userRepository.findAllByEmail(email);
                    user.setEmail(email);
                    user.setPassword(passwordEncoder.encode(newPassword));
                    if(userRepository.save(user) != null){
                        flag = "password_has_been_changed_successfully";
                    }
                }
            }
        }
        return flag;
    }

    public User getUserByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
