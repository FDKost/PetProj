/*package com.example.education.dao;

import com.example.education.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class AddressDao {
    private final NamedParameterJdbcTemplate template;
    private final UserDao userDao;

    public UUID createAddress(Address address){
        String sql = "INSERT INTO address (id,userId,street, house, apartment) VALUES (:id,:userId,:street,:house,:apartment) RETURNING id";
        Map<String,Object> map = new HashMap<>();
        map.put("id",UUID.randomUUID());
        map.put("userId", address.getUserId());
        map.put("street", address.getStreet());
        map.put("house",address.getHouse());
        map.put("apartment",address.getApartment());
        return template.queryForObject(sql,map,UUID.class);
    }

    public Address getAddressById(UUID addressId){
        String sql = "SELECT * FROM address WHERE id = :addressId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",addressId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Address address = new Address();
            address.setUserId(UUID.fromString(rs.getString("userId")));
            address.setHouse(rs.getString("house"));
            address.setApartment(rs.getString("apartment"));
            address.setStreet(rs.getString("street"));
            return address;
        });
    }
    public Address getAddressByUserId(UUID userId){
        try{
            String sql = "SELECT * FROM address WHERE userid = :userId";
            SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
            return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                Address address = new Address();
                address.setId(UUID.fromString(rs.getString("id")));
                address.setUserId(UUID.fromString(rs.getString("userId")));
                address.setHouse(rs.getString("house"));
                address.setApartment(rs.getString("apartment"));
                address.setStreet(rs.getString("street"));
                return address;
            });
        }catch (Exception e){
            return null;
        }

    }
    public void editAddress(Address address){
        String sql = "UPDATE address SET house=:house,apartment=:apartment,street=:street WHERE id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id",address.getId())
                .addValue("house",address.getHouse())
                .addValue("apartment",address.getApartment())
                .addValue("street",address.getStreet());
        template.update(sql,parameterSource);
    }
    public void deleteAddress(UUID addressId){
        String sql = "DELETE FROM address WHERE id = :addressId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",addressId);
        template.update(sql,parameterSource);
    }
}*/
