package com.example.education.dao;

import com.example.education.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final PasswordEncoder encoder;
    private final NamedParameterJdbcTemplate template;




    public UUID createUser(User user){
        String sql = "INSERT INTO client (id,login, password) VALUES(:id,:login,:password) RETURNING id ";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", UUID.randomUUID())
                .addValue("login",user.getLogin())
                .addValue("password",encoder.encode(user.getPassword()));
        return template.queryForObject(sql,parameterSource, UUID.class);
    }

    public UUID getIdByUser(String login) {
        String sql = "SELECT id FROM client WHERE login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource("login", login);
        return template.queryForObject(sql, parameterSource, UUID.class);
    }
    public User getUserById(UUID id){
        String sql = "SELECT * FROM client WHERE client.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        return template.queryForObject(sql,parameterSource,(rs, rowNum)-> {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }
    public User getUserByLogin(String login){
        String sql = "SELECT * FROM client WHERE client.login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource("login",login);
        List<User> users = template.query(sql, parameterSource, (rs, rowNum)-> {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        });

        if (users.isEmpty()) {
            return null; // Or throw an exception if no user is found
        } else if (users.size() > 1) {
            throw new RuntimeException("Multiple users found for login: " + login);
        } else {
            return users.get(0);
        }
    }
    public void editUser(User user){
        String sql = "UPDATE client SET password = :password WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("password",user.getPassword());
        template.update(sql,parameterSource);
    }
    public void deleteUser(UUID id){
        String sql = "DELETE FROM client WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        template.update(sql,parameterSource);
    }
}
