/*package com.example.education.dao;

import com.example.education.entity.Payment;
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
public class PaymentDao {
    private final NamedParameterJdbcTemplate template;

    public UUID createPayment(Payment payment){
        String sql = "INSERT INTO payment (id,total, checkURL, userid) VALUES (:id,:total, :checkURL, :userId) RETURNING id";
        Map<String,Object>map = new HashMap<>();
        map.put("id",UUID.randomUUID());
        map.put("total", payment.getTotal());
        map.put("checkURL", "https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_");
        map.put("userId", payment.getUserId());
        return template.queryForObject(sql, map, UUID.class);
    }

    public Payment getPaymentById(UUID paymentId){
        String sql = "SELECT * FROM payment WHERE id = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", paymentId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setId(UUID.fromString(rs.getString("paymentId")));
            payment.setTotal(rs.getLong("total"));
            payment.setCheckURL(rs.getString("checkURL"));
            payment.setUserId(UUID.fromString(rs.getString("userId")));
            return payment;
        });
    }

    public void editPayment(Payment payment){
        String sql = "UPDATE payment SET total = :total, checkURL = :checkURL, userid = :userId WHERE id = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("total", payment.getTotal())
                .addValue("checkURL", payment.getCheckURL())
                .addValue("userId", payment.getUserId())
                .addValue("id", payment.getId());
        template.update(sql, parameterSource);
    }

    public void deletePayment(UUID paymentId){
        String sql = "DELETE FROM payment WHERE id = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", paymentId);
        template.update(sql, parameterSource);
    }
}*/
