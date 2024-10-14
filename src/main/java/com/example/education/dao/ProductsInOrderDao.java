package com.example.education.dao;

import com.example.education.entity.ProductsInOrder;
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
public class ProductsInOrderDao {
    private final NamedParameterJdbcTemplate template;

    public Long createProductsInOrder(ProductsInOrder productsInOrder) {
        String sql = "INSERT INTO products_in_order (orderid, productid, quantity) VALUES (:orderId, :productId, :quantity) RETURNING orderitemid";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", productsInOrder.getOrderId());
        map.put("productId", productsInOrder.getProductId());
        map.put("quantity", productsInOrder.getQuantity());
        return template.queryForObject(sql, map, Long.class);
    }

    public ProductsInOrder getProductsInOrderById(Long orderItemId) {
        String sql = "SELECT * FROM products_in_order WHERE orderitemid = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("orderItemId", orderItemId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setOrderItemId(rs.getLong("orderItemId"));
            productsInOrder.setOrderId(rs.getLong("orderId"));
            productsInOrder.setProductId(rs.getLong("productId"));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }

    public List<ProductsInOrder> getAllProductsInOrder() {
        String sql = "SELECT * FROM products_in_order";
        return template.query(sql, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setOrderItemId(rs.getLong("orderItemId"));
            productsInOrder.setOrderId(rs.getLong("orderId"));
            productsInOrder.setProductId(rs.getLong("productId"));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }
    public void addProductsFromCart(Long orderId, List<ProductsInOrder> cartItems) {
        String sql = "INSERT INTO products_in_order (orderid, productid, quantity) VALUES (:orderId, :productId, :quantity)";
        for (ProductsInOrder item : cartItems) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("productId", item.getProductId());
            map.put("quantity", item.getQuantity());
            template.update(sql, map);
        }
    }

    public void editProductsInOrder(ProductsInOrder productsInOrder) {
        String sql = "UPDATE products_in_order SET orderid = :orderId, productid = :productId, quantity = :quantity WHERE orderitemid = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("orderId", productsInOrder.getOrderId())
                .addValue("productId", productsInOrder.getProductId())
                .addValue("quantity", productsInOrder.getQuantity())
                .addValue("orderItemId", productsInOrder.getOrderItemId());

        template.update(sql, parameterSource);
    }

    public void deleteProductsInOrder(Long orderItemId) {
        String sql = "DELETE FROM products_in_order WHERE orderitemid = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("orderItemId", orderItemId);
        template.update(sql, parameterSource);
    }
}
