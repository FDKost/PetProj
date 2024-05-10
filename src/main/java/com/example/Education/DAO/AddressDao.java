package com.example.Education.DAO;

import com.example.Education.Address;
import com.example.Education.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AddressDao {
    private final NamedParameterJdbcTemplate template;
    private final UserDao userDao;

    public Long createAddress(Address address){
        String sql = "INSERT INTO address (id_user,street, house, apartment) VALUES (:id_user,:street,:house,:apartment) RETURNING id_address";
        Map<String,Object> map = new HashMap<>();
        map.put("id_user", address.getId_user());
        map.put("street", address.getStreet());
        map.put("house",address.getHouse());
        map.put("apartment",address.getApartment());
        return template.queryForObject(sql,map,Long.class);
    }

    public Address getAddressById(long id_address){
        String sql = "SELECT * FROM address WHERE address.id_address = :id_address";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_address",id_address);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Address address = new Address();
            address.setId_user(rs.getInt("id_user"));
            address.setHouse(rs.getString("house"));
            address.setApartment(rs.getString("apartment"));
            address.setStreet(rs.getString("street"));
            return address;
        });
    }
    public void editAddress(Address address){
        String sql = "UPDATE address SET house=:house,apartment=:apartment,street=:street WHERE id_address=:id_address";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("house",address.getHouse())
                .addValue("apartment",address.getApartment())
                .addValue("street",address.getStreet());
        template.update(sql,parameterSource);
    }
    public void deleteAddress(long id_address){
        String sql = "DELETE FROM address WHERE id_address = :id_address";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_address",id_address);
        template.update(sql,parameterSource);
    }
}
