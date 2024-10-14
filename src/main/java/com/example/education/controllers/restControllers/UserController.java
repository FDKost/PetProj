package com.example.education.controllers.restControllers;


import com.example.education.dao.UserDao;
import com.example.education.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserDao userDao;
    @PostMapping("/registration")
    public ModelAndView createUser(User user) {
        Long userId = userDao.createUser(user);
        String loginUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString();
        return new ModelAndView("redirect:" + loginUrl);
    }

    @GetMapping("/api/readUser")
    public User readUser(@RequestParam Long id){

        return userDao.getUserById(id);
    }
    @PutMapping("/api/editUser")
    public void editUser(@RequestBody User user){
        userDao.editUser(user);
    }
    @DeleteMapping("/api/deleteUser")
    public void deleteUser(@RequestParam long id){
        userDao.deleteUser(id);
    }
    @GetMapping("/api/readLogin")
    public User findByLogin(@RequestParam String login){
        return userDao.getUserByLogin(login);
    }

}
