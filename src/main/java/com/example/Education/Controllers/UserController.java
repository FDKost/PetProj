package com.example.Education.Controllers;


import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserDao dao;
    @PostMapping("/registration")
    public ModelAndView createUser(User user) {
        Long userId = dao.createUser(user);
        String loginUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString();
        return new ModelAndView("redirect:" + loginUrl);
    }

    /*@PostMapping("/registration")
    public Long createUser(@RequestBody User user){

        return dao.createUser(user);
    }*/
    @GetMapping
    public User readUser(@RequestParam Long id){

        return dao.getUserById(id);
    }
    @PutMapping
    public void editUser(@RequestBody User user){
        dao.editUser(user);
    }
    @DeleteMapping
    public void deleteUser(@RequestParam long id){
        dao.deleteUser(id);
    }

    public User findByLogin(@RequestParam String login){
        return dao.getUserByLogin(login);
    }

    /*@GetMapping("/login")
    public String createUserlog(){

        return "login";
    }*/
}
