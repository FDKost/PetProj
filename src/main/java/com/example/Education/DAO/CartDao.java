package com.example.Education.DAO;

import com.example.Education.Cart;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CartDao {
    private final NamedParameterJdbcTemplate template;

    public Long createCart(Cart cart){
        String sql = "INSERT INTO cart (id_user, created_in) VALUES (:id_user,:password) RETURN id_cart";
        Map<String,Object> map = new HashMap<>();
        map.put("id_user",cart.getId_user());
        map.put("created_in",cart.getCreated_in());
                return template.queryForObject(sql,map,Long.class);
    }
    public Cart getCartById(long id_cart){
        String sql = "SELECT * FROM cart WHERE cart.id_cart = :id_cart";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_cart",id_cart);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Cart cart = new Cart();
            cart.setId_user(rs.getLong("id_user"));
            cart.setCreated_in(rs.getDate("created_in"));
            return cart;
        });
    }
    public void editCart(Cart cart){
        String sql = "UPDATE cart SET created_in = :created_in WHERE id_cart = :id_cart";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("created_in",cart.getCreated_in());
        template.update(sql,parameterSource);
    }
    public void deleteCart(long id_cart){
        String sql = "DELETE FROM cart WHERE id_cart = :id_cart";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_cart",id_cart);
        template.update(sql,parameterSource);
    }
}
