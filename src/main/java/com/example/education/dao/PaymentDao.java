package com.example.education.dao;

import com.example.education.entity.Payment;
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
        String sql = "INSERT INTO payment (total, checkURL, userid) VALUES (:total, :checkURL, :userId) RETURNING paymentid";
        Map<String,Object>map = new HashMap<>();
        map.put("total", payment.getTotal());
        map.put("checkURL", "https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_");
        map.put("userId", payment.getUserId());
        return template.queryForObject(sql, map, Long.class);
    }

    public Payment getPaymentById(long paymentId){
        String sql = "SELECT * FROM payment WHERE paymentid = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("paymentId", paymentId);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setPaymentId(rs.getLong("paymentId"));
            payment.setTotal(rs.getLong("total"));
            payment.setCheckURL(rs.getString("checkURL"));
            payment.setUserId(rs.getLong("userId"));
            return payment;
        });
    }

    public void editPayment(Payment payment){
        String sql = "UPDATE payment SET total = :total, checkURL = :checkURL, userid = :userId WHERE paymentid = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("total", payment.getTotal())
                .addValue("checkURL", payment.getCheckURL())
                .addValue("userId", payment.getUserId())
                .addValue("paymentId", payment.getPaymentId());
        template.update(sql, parameterSource);
    }

    public void deletePayment(long paymentId){
        String sql = "DELETE FROM payment WHERE paymentid = :paymentId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("paymentId", paymentId);
        template.update(sql, parameterSource);
    }
}
