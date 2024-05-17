package com.example.Education.DAO;

import com.example.Education.ProductsInOrder;
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
        String sql = "INSERT INTO products_in_order (id_order, product_id, quantity) VALUES (:id_order, :product_id, :quantity) RETURNING order_item_id";
        Map<String, Object> map = new HashMap<>();
        map.put("id_order", productsInOrder.getId_order());
        map.put("product_id", productsInOrder.getProduct_id());
        map.put("quantity", productsInOrder.getQuantity());
        return template.queryForObject(sql, map, Long.class);
    }

    public ProductsInOrder getProductsInOrderById(Long order_item_id) {
        String sql = "SELECT * FROM products_in_order WHERE order_item_id = :order_item_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("order_item_id", order_item_id);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setOrder_item_id(rs.getLong("order_item_id"));
            productsInOrder.setId_order(rs.getLong("id_order"));
            productsInOrder.setProduct_id(rs.getLong("product_id"));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }

    public List<ProductsInOrder> getAllProductsInOrder() {
        String sql = "SELECT * FROM products_in_order";
        return template.query(sql, (rs, rowNum) -> {
            ProductsInOrder productsInOrder = new ProductsInOrder();
            productsInOrder.setOrder_item_id(rs.getLong("order_item_id"));
            productsInOrder.setId_order(rs.getLong("id_order"));
            productsInOrder.setProduct_id(rs.getLong("product_id"));
            productsInOrder.setQuantity(rs.getLong("quantity"));
            return productsInOrder;
        });
    }
    public void addProductsFromCart(Long idOrder, List<ProductsInOrder> cartItems) {
        String sql = "INSERT INTO products_in_order (id_order, product_id, quantity) VALUES (:id_order, :product_id, :quantity)";
        for (ProductsInOrder item : cartItems) {
            Map<String, Object> map = new HashMap<>();
            map.put("id_order", idOrder);
            map.put("product_id", item.getProduct_id());
            map.put("quantity", item.getQuantity());
            template.update(sql, map);
        }
    }

    public void editProductsInOrder(ProductsInOrder productsInOrder) {
        String sql = "UPDATE products_in_order SET id_order = :id_order, product_id = :product_id, quantity = :quantity WHERE order_item_id = :order_item_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id_order", productsInOrder.getId_order())
                .addValue("product_id", productsInOrder.getProduct_id())
                .addValue("quantity", productsInOrder.getQuantity())
                .addValue("order_item_id", productsInOrder.getOrder_item_id());

        template.update(sql, parameterSource);
    }

    public void deleteProductsInOrder(Long order_item_id) {
        String sql = "DELETE FROM products_in_order WHERE order_item_id = :order_item_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("order_item_id", order_item_id);
        template.update(sql, parameterSource);
    }
}
