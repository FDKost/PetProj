package com.example.Education.DAO;

import com.example.Education.Cart;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Long createCart(Cart cart) {
        String sqlCheckCart = "SELECT id_cart FROM cart WHERE id_user = :id_user";
        Map<String, Object> paramsCheckCart = new HashMap<>();
        paramsCheckCart.put("id_user", cart.getId_user());

        try {
            // Пытаемся выполнить запрос для получения id_cart
            Long existingCartId = template.queryForObject(sqlCheckCart, paramsCheckCart, Long.class);
            System.out.println("Cart already exists with id: " + existingCartId);
            return existingCartId; // Возвращаем id_cart существующей корзины
        } catch (EmptyResultDataAccessException e) {
            // Если корзина не найдена, создаем новую
            String sqlCreateCart = "INSERT INTO cart (id_user, created_in) VALUES (:id_user, :created_in) RETURNING id_cart";
            Map<String, Object> paramsCreateCart = new HashMap<>();
            paramsCreateCart.put("id_user", cart.getId_user());
            paramsCreateCart.put("created_in", cart.getCreated_in());

            return template.queryForObject(sqlCreateCart, paramsCreateCart, Long.class);
        }
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
    public Cart getCartByUserId(long id_user) {
        String sql = "SELECT * FROM cart WHERE id_user = :id_user";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_user", id_user);

        try {
            return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                Cart cart = new Cart();
                cart.setId_cart(rs.getLong("id_cart"));
                cart.setId_user(rs.getLong("id_user"));
                cart.setCreated_in(rs.getDate("created_in"));
                return cart;
            });
        } catch (EmptyResultDataAccessException e) {
            // Если корзина не найдена, можно вернуть null или бросить исключение
            return null;
        }
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
