package com.example.education.dao;

import com.example.education.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class OrderDao {
    private final NamedParameterJdbcTemplate template;

    public Long createOrder(Order order) {
        String sql = "INSERT INTO orders (userid, paymentId, addressId, date,status) VALUES (:userId, :paymentId, :addressId, :date,:status) RETURNING orderid";
        Map<String, Object> map = new HashMap<>();
        map.put("userId", order.getUserId());
        map.put("paymentId", order.getPaymentId());
        map.put("addressId", order.getAddressId());
        map.put("date", order.getDate());
        map.put("status",1);
        return template.queryForObject(sql, map, Long.class);
    }

    public Order getOrderById(Long orderId) {
        String sql = "SELECT * FROM orders WHERE orderid = :orderId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("orderId", orderId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getLong("orderId"));
            order.setUserId(rs.getLong("userId"));
            order.setPaymentId(rs.getLong("paymentId"));
            order.setAddressId(rs.getLong("addressId"));
            order.setDate(rs.getDate("date"));
            order.setStatus(rs.getLong("status"));
            return order;
        });
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return template.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getLong("orderId"));
            order.setUserId(rs.getLong("userId"));
            order.setPaymentId(rs.getLong("paymentId"));
            order.setAddressId(rs.getLong("addressId"));
            order.setDate(rs.getDate("date"));
            order.setStatus(rs.getLong("status"));
            return order;
        });
    }

    public void editOrder(Order order) {
        String sql = "UPDATE orders SET status = :status WHERE orderid = :orderId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("status", order.getStatus())
                .addValue("orderId", order.getOrderId());
        template.update(sql, parameterSource);
    }

    public void deleteOrder(Long orderId) {
        String sql = "DELETE FROM orders WHERE orderid = :orderId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("orderId", orderId);
        template.update(sql, parameterSource);
    }
}
