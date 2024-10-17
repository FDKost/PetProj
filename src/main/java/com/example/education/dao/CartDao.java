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
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CartDao {
    private final NamedParameterJdbcTemplate template;

    public UUID createCart(Cart cart) {
        String sqlCheckCart = "SELECT id FROM cart WHERE userId = :userId";
        Map<String, Object> paramsCheckCart = new HashMap<>();
        paramsCheckCart.put("userId", cart.getUserId());

        try {
            // Пытаемся выполнить запрос для получения id_cart
            UUID existingCartId = template.queryForObject(sqlCheckCart, paramsCheckCart, UUID.class);
            System.out.println("Cart already exists with id: " + existingCartId);
            return existingCartId; // Возвращаем id_cart существующей корзины
        } catch (EmptyResultDataAccessException e) {
            // Если корзина не найдена, создаем новую
            String sqlCreateCart = "INSERT INTO cart (id,userid, createdin) VALUES (:id,:userId, :createdIn) RETURNING id";
            Map<String, Object> paramsCreateCart = new HashMap<>();
            paramsCreateCart.put("id", UUID.randomUUID());
            paramsCreateCart.put("userId", cart.getUserId());
            paramsCreateCart.put("createdIn", cart.getCreatedIn());

            return template.queryForObject(sqlCreateCart, paramsCreateCart, UUID.class);
        }
    }

    public Cart getCartById(UUID cartId){
        String sql = "SELECT * FROM cart WHERE cart.id = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",cartId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Cart cart = new Cart();
            cart.setUserId(UUID.fromString(rs.getString("userId")));
            cart.setCreatedIn(rs.getDate("createdIn"));
            return cart;
        });
    }
    public Cart getCartByUserId(UUID userId) {
        String sql = "SELECT * FROM cart WHERE userid = :userId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);

        try {
            return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                Cart cart = new Cart();
                cart.setId(UUID.fromString(rs.getString("id")));
                cart.setUserId(UUID.fromString(rs.getString("userId")));
                cart.setCreatedIn(rs.getDate("createdIn"));
                return cart;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void editCart(Cart cart){
        String sql = "UPDATE cart SET createdin = :createdIn WHERE id = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("createdIn",cart.getCreatedIn());
        template.update(sql,parameterSource);
    }
    public void deleteCart(UUID cartId){
        String sql = "DELETE FROM cart WHERE id = :cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",cartId);
        template.update(sql,parameterSource);
    }
}
