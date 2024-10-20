/*package com.example.education.dao;

import com.example.education.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class OrderDao {
    private final NamedParameterJdbcTemplate template;

    public UUID createOrder(Order order) {
        String sql = "INSERT INTO orders (id,userid, paymentId, addressId, date,status) VALUES (:id,:userId, :paymentId, :addressId, :date,:status) RETURNING id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID());
        map.put("userId", order.getUserId());
        map.put("paymentId", order.getPaymentId());
        map.put("addressId", order.getAddressId());
        map.put("date", order.getDate());
        map.put("status",UUID.fromString("d896b4b7-4876-4de4-a5ed-604ac1442ab2"));
        return template.queryForObject(sql, map, UUID.class);
    }

    public Order getOrderById(UUID orderId) {
        String sql = "SELECT * FROM orders WHERE id = :orderId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("orderId", orderId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(UUID.fromString(rs.getString("id")));
            order.setUserId(UUID.fromString(rs.getString("userId")));
            order.setPaymentId(UUID.fromString(rs.getString("paymentId")));
            order.setAddressId(UUID.fromString(rs.getString("addressId")));
            order.setDate(rs.getDate("date"));
            order.setStatus(UUID.fromString(rs.getString("status")));
            return order;
        });
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return template.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(UUID.fromString(rs.getString("id")));
            order.setUserId(UUID.fromString(rs.getString("userId")));
            order.setPaymentId(UUID.fromString(rs.getString("paymentId")));
            order.setAddressId(UUID.fromString(rs.getString("addressId")));
            order.setDate(rs.getDate("date"));
            order.setStatus(UUID.fromString(rs.getString("status")));
            return order;
        });
    }

    public void editOrder(Order order) {
        String sql = "UPDATE orders SET status = :status WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("status", order.getStatus())
                .addValue("id", order.getId());
        template.update(sql, parameterSource);
    }

    public void deleteOrder(UUID orderId) {
        String sql = "DELETE FROM orders WHERE id = :orderId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", orderId);
        template.update(sql, parameterSource);
    }
}*/
