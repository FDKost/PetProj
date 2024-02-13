package com.example.Education.DAO;

import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserDao {
    private final NamedParameterJdbcTemplate template;
    /*private final JdbcTemplate jdbcTemplate;*/



    public Long createUser(User user){
        String sql = "INSERT INTO user (login, password) VALUES(:login,:password) RETURN id ";
        Map<String,Object> map = new HashMap<>();
        map.put("login",user.getLogin());
        map.put("password",user.getPassword());
        return template.queryForObject(sql,map, Long.class);
    }

    public User getUserById(long id){
        String sql = "SELECT * FROM user WHERE user.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        return template.queryForObject(sql,parameterSource,(rs, rowNum)-> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
    public void editUser(User user){
        String sql = "UPDATE user SET password = :password WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("password",user.getPassword());
        template.update(sql,parameterSource);
    }
    public void deleteUser(long id){
        String sql = "DELETE FROM user WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        template.update(sql,parameterSource);
    }
}
