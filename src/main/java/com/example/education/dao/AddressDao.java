package com.example.education.dao;

import com.example.education.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AddressDao {
    private final NamedParameterJdbcTemplate template;
    private final UserDao userDao;

    public Long createAddress(Address address){
        String sql = "INSERT INTO address (useriD,street, house, apartment) VALUES (:userId,:street,:house,:apartment) RETURNING addressid";
        Map<String,Object> map = new HashMap<>();
        map.put("userId", address.getUserId());
        map.put("street", address.getStreet());
        map.put("house",address.getHouse());
        map.put("apartment",address.getApartment());
        return template.queryForObject(sql,map,Long.class);
    }

    public Address getAddressById(long addressId){
        String sql = "SELECT * FROM address WHERE address.addressId = :addressId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("addressId",addressId);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Address address = new Address();
            address.setUserId(rs.getInt("userId"));
            address.setHouse(rs.getString("house"));
            address.setApartment(rs.getString("apartment"));
            address.setStreet(rs.getString("street"));
            return address;
        });
    }
    public Address getAddressByUserId(long userId){
        try{
            String sql = "SELECT * FROM address WHERE userid = :userId";
            SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId);
            return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                Address address = new Address();
                address.setAddressId(rs.getLong("addressId"));
                address.setUserId(rs.getInt("userId"));
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
        String sql = "UPDATE address SET house=:house,apartment=:apartment,street=:street WHERE addressid=:addressId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("addressId",address.getAddressId())
                .addValue("house",address.getHouse())
                .addValue("apartment",address.getApartment())
                .addValue("street",address.getStreet());
        template.update(sql,parameterSource);
    }
    public void deleteAddress(long addressId){
        String sql = "DELETE FROM address WHERE addressid = :addressId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("addressId",addressId);
        template.update(sql,parameterSource);
    }
}
