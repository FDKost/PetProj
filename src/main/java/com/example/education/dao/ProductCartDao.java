package com.example.education.dao;

import com.example.education.entity.Product;
import com.example.education.entity.ProductCart;
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
        String sql = "INSERT INTO product_cart (productid,cartid,quantity) VALUES(:productId,:cartId,:quantity) RETURNING cartitemid";
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productCart.getProductId());
        map.put("cartId",productCart.getCartId());
        map.put("quantity",productCart.getQuantity());
        return template.queryForObject(sql,map,Long.class);
    }

    public ProductCart getProductCartById(long cartItemId){
        String sql = "SELECT * FROM product_cart WHERE cartitemid = :cartItemId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("cartItemId",cartItemId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            ProductCart productCart = new ProductCart();
            productCart.setProductId(rs.getLong("productId"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setCartId(rs.getLong("cartId"));
            productCart.setCartItemId(rs.getLong("cartItemId"));
            return productCart;
        });
    }
    public ProductCart getProductCartByIdCart(long cartId) {
        String sql = "SELECT * FROM product_cart WHERE cartid = :cartId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cartId", cartId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            ProductCart productCart = new ProductCart();
            productCart.setProductId(rs.getLong("productId"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setCartId(rs.getLong("cartId"));
            productCart.setCartItemId(rs.getLong("cartItemId"));
            return productCart;
        });
    }

    public List<ProductCart> getAllProductCartsByIdCart(long cartId) {
        String sql = "SELECT pc.*, p.name AS productName, p.imageurl AS productImageURL, p.price AS productPrice, p.details AS productDetails " +
                "FROM product_cart pc " +
                "JOIN product p ON pc.productid = p.productid " +
                "WHERE pc.cartid = :cartId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cartId", cartId);
        return template.query(sql, parameterSource, (rs, rowNum) -> {
            ProductCart productCart = new ProductCart();
            productCart.setProductId(rs.getLong("productId"));
            productCart.setQuantity(rs.getLong("quantity"));
            productCart.setCartId(rs.getLong("cartId"));
            // Другие поля productCart

            // Создание объекта Product и установка его значений
            Product product = new Product();
            product.setName(rs.getString("productName"));
            product.setImageURL(rs.getString("productImageURL"));
            product.setPrice(rs.getInt("productPrice"));
            product.setDetails(rs.getString("productDetails"));

            productCart.setProduct(product);

            return productCart;
        });
    }
    public void editProductCart(ProductCart productCart,Long cartId){
        String sql = "UPDATE product_cart SET productid=:productId,quantity=:quantity WHERE cartid=:cartId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("productId",productCart.getProductId())
                .addValue("quantity",productCart.getQuantity());
        template.update(sql,parameterSource);
    }
    public void deleteProductCart(long cartId, long productId){
        String sql = "DELETE FROM product_cart WHERE cartid = :cartId AND productid = :productId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cartId", cartId);
        paramMap.put("productId", productId);
        template.update(sql, paramMap);
    }
    public void deleteAllFromProductCart(long cartId){
        String sql = "DELETE FROM product_cart WHERE cartid = :cartId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cartId", cartId);
        template.update(sql, paramMap);
    }
}
