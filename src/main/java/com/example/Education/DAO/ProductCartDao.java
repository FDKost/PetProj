package com.example.Education.DAO;

import com.example.Education.Product;
import com.example.Education.ProductCart;
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
public class ProductCartDao {

    private final NamedParameterJdbcTemplate template;

    public Long createProductCart(ProductCart productCart){
        String sql = "INSERT INTO product_cart (product_id,id_cart,quantity) VALUES(:product_id,:id_cart,:quantity) RETURNING cart_item_id";
        Map<String,Object> map = new HashMap<>();
        map.put("product_id",productCart.getProduct_id());
        map.put("id_cart",productCart.getId_cart());
        map.put("quantity",productCart.getQuantity());
        return template.queryForObject(sql,map,Long.class);
    }

    public ProductCart getProductCartById(long cart_item_id){
        String sql = "SELECT * FROM product_cart WHERE cart_item_id = :cart_item_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("cart_item_id",cart_item_id);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            ProductCart productCart = new ProductCart();
            productCart.setProduct_id(rs.getLong("product_id"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setId_cart(rs.getLong("id_cart"));
            productCart.setCart_item_id(rs.getLong("cart_item_id"));
            return productCart;
        });
    }
    public ProductCart getProductCartByIdCart(long id_cart) {
        String sql = "SELECT * FROM product_cart WHERE id_cart = :id_cart";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_cart", id_cart);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            ProductCart productCart = new ProductCart();
            productCart.setProduct_id(rs.getLong("product_id"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setId_cart(rs.getLong("id_cart"));
            productCart.setCart_item_id(rs.getLong("cart_item_id"));
            return productCart;
        });
    }
    /*public List<ProductCart> getAllProductCartsByIdCart(long id_cart) {
        String sql = "SELECT * FROM product_cart WHERE id_cart = :id_cart";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_cart", id_cart);
        return template.query(sql, parameterSource, (rs, rowNum) -> {
            ProductCart productCart = new ProductCart();
            productCart.setProduct_id(rs.getLong("product_id"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setId_cart(rs.getLong("id_cart"));
            productCart.setCart_item_id(rs.getLong("cart_item_id"));
            return productCart;
        });
    }*/
    public List<ProductCart> getAllProductCartsByIdCart(long id_cart) {
        String sql = "SELECT pc.*, p.name AS product_name, p.image_URL AS product_image_URL, p.price AS product_price, p.details AS product_details " +
                "FROM product_cart pc " +
                "JOIN product p ON pc.product_id = p.product_id " +
                "WHERE pc.id_cart = :id_cart";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_cart", id_cart);
        return template.query(sql, parameterSource, (rs, rowNum) -> {
            ProductCart productCart = new ProductCart();
            productCart.setProduct_id(rs.getLong("product_id"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setId_cart(rs.getLong("id_cart"));
            // Другие поля productCart

            // Создание объекта Product и установка его значений
            Product product = new Product();
            product.setName(rs.getString("product_name"));
            product.setImage_URL(rs.getString("product_image_URL"));
            product.setPrice(rs.getInt("product_price"));
            product.setDetails(rs.getString("product_details"));

            // Установка объекта Product в ProductCart
            productCart.setProduct(product);

            return productCart;
        });
    }
    public void editProductCart(ProductCart productCart){
        String sql = "UPDATE product_cart SET product_id=:product_id,quantity=:quantity";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id",productCart.getProduct_id())
                .addValue("quantity",productCart.getQuantity());
        template.update(sql,parameterSource);
    }
    public void deleteProductCart(long cart_item_id){
        String sql = "DELETE FROM product_cart WHERE cart_item_id = :cart_item_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("cart_item_id",cart_item_id);
        template.update(sql,parameterSource);
    }
}
