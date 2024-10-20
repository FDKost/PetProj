/*package com.example.education.dao;

import com.example.education.entity.Product;
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
public class ProductDao {
    private final NamedParameterJdbcTemplate template;

    public UUID createProduct(Product product){
        String sql = "INSERT INTO product (id,name,price,details,imageURL) VALUES (:id,:name,:price,:details,:imageURL) RETURNING id";
        Map<String,Object>map = new HashMap<>();
        map.put("id",UUID.randomUUID());
        map.put("name",product.getName());
        map.put("price",product.getPrice());
        map.put("details",product.getDetails());
        map.put("imageURL",product.getImageURL());
        return template.queryForObject(sql,map, UUID.class);
    }
    public Product getProductById(UUID productId){
        String sql = "SELECT * FROM product WHERE id = :productId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",productId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
           Product product = new Product();
           product.setName(rs.getString("name"));
           product.setPrice(rs.getInt("price"));
           product.setImageURL(rs.getString("imageURL"));
           product.setDetails(rs.getString("details"));
           return product;
        });
    }
    public Product getProductByName(String name) {
        String sql = "SELECT * FROM product WHERE name = :name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(UUID.fromString(rs.getString("productId")));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageURL(rs.getString("imageURL"));
            product.setDetails(rs.getString("details"));
            return product;
        });
    }
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return template.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(UUID.fromString(rs.getString("id")));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageURL(rs.getString("imageURL"));
            product.setDetails(rs.getString("details"));
            return product;
        });
    }
    public void editProduct(Product product){
        String sql = "UPDATE product SET name=:name, price=:price, details=:details, imageURL=:imageURL WHERE id=:productId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price",product.getPrice())
                .addValue("details",product.getDetails())
                .addValue("imageURL",product.getImageURL())
                .addValue("id",product.getId());

        template.update(sql,parameterSource);
    }
    public void deleteProduct(UUID productId){
        String sql = "DELETE FROM product WHERE id = :productId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",productId);
        template.update(sql,parameterSource);
    }
}*/
