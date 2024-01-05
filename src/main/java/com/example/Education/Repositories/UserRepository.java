package com.example.Education.Repositories;

import com.example.Education.User;
/*import org.springframework.data.repository.CrudRepository;*/

public interface UserRepository /*extends CrudRepository<User, Long>*/ {

    User findByUsername(String username);

}
