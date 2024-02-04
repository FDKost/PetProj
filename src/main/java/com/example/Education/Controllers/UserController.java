package com.example.Education.Controllers;


import com.example.Education.DAO.UserDao;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserDao dao;

    @PostMapping
    public Long createUser(@RequestBody User user){

        return dao.createUser(user);
    }
    @GetMapping
    public User createUser(@RequestParam Long id){

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
}
