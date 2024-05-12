package com.example.Education.DAO;

import com.example.Education.Product;
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
public class ProductDao {
    private final NamedParameterJdbcTemplate template;

    public Long createProduct(Product product){
        String sql = "INSERT INTO product (name,price,details,image_URL) VALUES (:name,:price,:details,:image_URL)";
        Map<String,Object>map = new HashMap<>();
        map.put("name",product.getName());
        map.put("price",product.getPrice());
        map.put("details",product.getDetails());
        map.put("image_URL",product.getImage_URL());
        return template.queryForObject(sql,map, Long.class);
    }
    public Product getProductById(long product_id){
        String sql = "SELECT * FROM product WHERE product_id = :product_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("product_id",product_id);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
           Product product = new Product();
           product.setName(rs.getString("name"));
           product.setPrice(rs.getInt("price"));
           product.setImage_URL(rs.getString("image_URL"));
           product.setDetails(rs.getString("details"));
           return product;
        });
    }
    public Product getProductByName(String name) {
        String sql = "SELECT * FROM product WHERE name = :name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Product product = new Product();
            product.setProduct_id(rs.getLong("product_id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImage_URL(rs.getString("image_URL"));
            product.setDetails(rs.getString("details"));
            return product;
        });
    }
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return template.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setProduct_id(rs.getLong("product_id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImage_URL(rs.getString("image_URL"));
            product.setDetails(rs.getString("details"));
            return product;
        });
    }
    public void editProduct(Product product){
        String sql = "UPDATE product SET name=:name, price=:price, details=:details, image_URL=:image_URL WHERE product_id=:product_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price",product.getPrice())
                .addValue("details",product.getDetails())
                .addValue("image_URL",product.getImage_URL())
                .addValue("product_id",product.getProduct_id());

        template.update(sql,parameterSource);
    }
    public void deleteProduct(long product_id){
        String sql = "DELETE FROM product WHERE product_id = :product_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("product_id",product_id);
        template.update(sql,parameterSource);
    }
}
