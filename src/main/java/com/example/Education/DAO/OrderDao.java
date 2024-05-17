package com.example.Education.DAO;

import com.example.Education.Order;
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
        String sql = "INSERT INTO orders (id_user, id_payment, id_address, date) VALUES (:id_user, :id_payment, :id_address, :date) RETURNING id_order";
        Map<String, Object> map = new HashMap<>();
        map.put("id_user", order.getId_user());
        map.put("id_payment", order.getId_payment());
        map.put("id_address", order.getId_address());
        map.put("date", order.getDate());
        return template.queryForObject(sql, map, Long.class);
    }

    public Order getOrderById(Long id_order) {
        String sql = "SELECT * FROM orders WHERE id_order = :id_order";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_order", id_order);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Order order = new Order();
            order.setId_order(rs.getLong("id_order"));
            order.setId_user(rs.getLong("id_user"));
            order.setId_payment(rs.getLong("id_payment"));
            order.setId_address(rs.getLong("id_address"));
            order.setDate(rs.getDate("date"));
            return order;
        });
    }

    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return template.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId_order(rs.getLong("id_order"));
            order.setId_user(rs.getLong("id_user"));
            order.setId_payment(rs.getLong("id_payment"));
            order.setId_address(rs.getLong("id_address"));
            order.setDate(rs.getDate("date"));
            return order;
        });
    }

    public void editOrder(Order order) {
        String sql = "UPDATE orders SET id_user = :id_user, id_payment = :id_payment, id_address = :id_address, date = :date WHERE id_order = :id_order";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id_user", order.getId_user())
                .addValue("id_payment", order.getId_payment())
                .addValue("id_address", order.getId_address())
                .addValue("date", order.getDate())
                .addValue("id_order", order.getId_order());

        template.update(sql, parameterSource);
    }

    public void deleteOrder(Long id_order) {
        String sql = "DELETE FROM orders WHERE id_order = :id_order";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_order", id_order);
        template.update(sql, parameterSource);
    }
}
