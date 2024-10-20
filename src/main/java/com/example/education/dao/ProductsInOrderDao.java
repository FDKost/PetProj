/*package com.example.education.dao;

import com.example.education.entity.ProductsInOrder;
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
public class ProductsInOrderDao {
    private final NamedParameterJdbcTemplate template;

    public UUID createProductsInOrder(ProductsInOrder productsInOrder) {
        String sql = "INSERT INTO products_in_order (id,orderid, productid, quantity) VALUES (:id,:orderId, :productId, :quantity) RETURNING id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID());
        map.put("orderId", productsInOrder.getOrderId());
        map.put("productId", productsInOrder.getProductId());
        map.put("quantity", productsInOrder.getQuantity());
        return template.queryForObject(sql, map, UUID.class);
    }

    public ProductsInOrder getProductsInOrderById(UUID orderItemId) {
        String sql = "SELECT * FROM products_in_order WHERE id = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", orderItemId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setId(UUID.fromString(rs.getString("orderItemId")));
            productsInOrder.setOrderId(UUID.fromString(rs.getString("orderId")));
            productsInOrder.setProductId(UUID.fromString(rs.getString("productId")));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }

    public List<ProductsInOrder> getAllProductsInOrder() {
        String sql = "SELECT * FROM products_in_order";
        return template.query(sql, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setId(UUID.fromString(rs.getString("orderItemId")));
            productsInOrder.setOrderId(UUID.fromString(rs.getString("orderId")));
            productsInOrder.setProductId(UUID.fromString(rs.getString("productId")));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }
    public void addProductsFromCart(UUID orderId, List<ProductsInOrder> cartItems) {
        String sql = "INSERT INTO products_in_order (id,orderid, productid, quantity) VALUES (:id,:orderId, :productId, :quantity)";
        for (ProductsInOrder item : cartItems) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", UUID.randomUUID());
            map.put("orderId", orderId);
            map.put("productId", item.getProductId());
            map.put("quantity", item.getQuantity());
            template.update(sql, map);
        }
    }

    public void editProductsInOrder(ProductsInOrder productsInOrder) {
        String sql = "UPDATE products_in_order SET orderid = :orderId, productid = :productId, quantity = :quantity WHERE id = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("orderId", productsInOrder.getOrderId())
                .addValue("productId", productsInOrder.getProductId())
                .addValue("quantity", productsInOrder.getQuantity())
                .addValue("id", productsInOrder.getId());

        template.update(sql, parameterSource);
    }

    public void deleteProductsInOrder(UUID orderItemId) {
        String sql = "DELETE FROM products_in_order WHERE id = :orderItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", orderItemId);
        template.update(sql, parameterSource);
    }
}*/
