package com.example.Education.RestControllers;


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
    @GetMapping("/api/readUser")
    public User readUser(@RequestParam Long id){

        return dao.getUserById(id);
    }
    @PutMapping("/api/editUser")
    public void editUser(@RequestBody User user){
        dao.editUser(user);
    }
    @DeleteMapping("/api/deleteUser")
    public void deleteUser(@RequestParam long id){
        dao.deleteUser(id);
    }
    @GetMapping("/api/readLogin")
    public User findByLogin(@RequestParam String login){
        return dao.getUserByLogin(login);
    }

    /*@GetMapping("/login")
    public String createUserlog(){

        return "login";
    }*/
}
