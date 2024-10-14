package com.example.education.dao;

import com.example.education.entity.Cart;
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
        String sqlCheckCart = "SELECT cartId FROM cart WHERE userId = :userId";
        Map<String, Object> paramsCheckCart = new HashMap<>();
        paramsCheckCart.put("userId", cart.getUserId());

        try {
            // Пытаемся выполнить запрос для получения id_cart
            Long existingCartId = template.queryForObject(sqlCheckCart, paramsCheckCart, Long.class);
            System.out.println("Cart already exists with id: " + existingCartId);
            return existingCartId; // Возвращаем id_cart существующей корзины
        } catch (EmptyResultDataAccessException e) {
            // Если корзина не найдена, создаем новую
            String sqlCreateCart = "INSERT INTO cart (userid, createdin) VALUES (:userId, :createdIn) RETURNING cartid";
            Map<String, Object> paramsCreateCart = new HashMap<>();
            paramsCreateCart.put("userId", cart.getUserId());
            paramsCreateCart.put("createdIn", cart.getCreatedIn());

            return template.queryForObject(sqlCreateCart, paramsCreateCart, Long.class);
        }
    }

    public Cart getCartById(long cartId){
        String sql = "SELECT * FROM cart WHERE cart.cartid = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("cartId",cartId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Cart cart = new Cart();
            cart.setUserId(rs.getLong("userId"));
            cart.setCreatedIn(rs.getDate("createdIn"));
            return cart;
        });
    }
    public Cart getCartByUserId(long userId) {
        String sql = "SELECT * FROM cart WHERE userid = :userId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);

        try {
            return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                Cart cart = new Cart();
                cart.setCartId(rs.getLong("cartId"));
                cart.setUserId(rs.getLong("userId"));
                cart.setCreatedIn(rs.getDate("createdIn"));
                return cart;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void editCart(Cart cart){
        String sql = "UPDATE cart SET createdin = :createdIn WHERE cartid = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("createdIn",cart.getCreatedIn());
        template.update(sql,parameterSource);
    }
    public void deleteCart(long cartId){
        String sql = "DELETE FROM cart WHERE cartid = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("cartId",cartId);
        template.update(sql,parameterSource);
    }
}
