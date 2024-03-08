package com.example.Education.DAO;

import com.example.Education.Payment;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PaymentDao {
    private final NamedParameterJdbcTemplate template;

    public Long createPayment(Payment payment){
        String sql = "INSERT INTO payment (total,check_URL) VALUES (:total, :check_URL)";
        Map<String,Object>map = new HashMap<>();
        map.put("total",payment.getTotal());
        map.put("check_URL",payment.getCheck_URL());
        return template.queryForObject(sql,map,Long.class);
    }
    public Payment getPaymentById(long id_payment){
        String sql = "SELECT * FROM payment WHERE payment.id_payment = :id_payment";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_payment",id_payment);
        return template.queryForObject(sql,parameterSource,(rs,rowNum)->{
            Payment payment = new Payment();
            payment.setTotal(rs.getLong("total"));
            payment.setCheck_URL(rs.getString("check_URL"));
            return payment;
        });
    }
    public void editPayment(Payment payment){
        String sql = "UPDATE payment SET (total,check_URL) VALUES (:total,:check_URL)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("total",payment.getTotal())
                .addValue("check_URL",payment.getCheck_URL());
        template.update(sql,parameterSource);
    }
    public void deletePayment(long id_payment){
        String sql = "DELETE FROM payment WHERE id_payment = :id_payment";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_payment",id_payment);
        template.update(sql,parameterSource);
    }
}
