package com.example.Education.DAO;

import com.example.Education.ProductCart;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ProductCartDao {

    private final NamedParameterJdbcTemplate template;

    public Long createProductCart(ProductCart productCart){
        String sql = "INSERT INTO product_cart (product_id,id_cart,quantity) VALUES(:product_id,id_cart,quantity) RETURN cart_item_id";
        Map<String,Object> map = new HashMap<>();
        map.put("product_id",productCart.getProduct_id());
        map.put("id_cart",productCart.getId_cart());
        map.put("quantity",productCart.getQuantity());
        return template.queryForObject(sql,map,Long.class);
    }

    public ProductCart getProductCartById(long cart_item_id){
        String sql = "SELECT * FROM product_cart WHERE productCart.cart_item_id = :cart_item_id";
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
    public void editProductCart(ProductCart productCart){
        String sql = "UPDATE product_cart SET (product_id,quantity) VALUES(:product_id,:quantity)";
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
