package com.example.Education.Controllers;


import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserDao dao;

    @PostMapping("/registry")
    public Long createUser(@RequestBody User user){

        return dao.createUser(user);
    }
    @GetMapping("/registry")
    public User readUser(@RequestParam Long id){

        return dao.getUserById(id);
    }
    @PutMapping("/registry")
    public void editUser(@RequestBody User user){
        dao.editUser(user);
    }
    @DeleteMapping("/registry")
    public void deleteUser(@RequestParam long id){
        dao.deleteUser(id);
    }

    /*@GetMapping("/login")
    public String createUserlog(){

        return "login";
    }*/
}
