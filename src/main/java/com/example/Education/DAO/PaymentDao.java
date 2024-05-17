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
        String sql = "INSERT INTO payment (total, check_URL, id_user) VALUES (:total, :check_URL, :id_user) RETURNING id_payment";
        Map<String,Object>map = new HashMap<>();
        map.put("total", payment.getTotal());
        map.put("check_URL", "https://drive.google.com/thumbnail?id=1SK68T2d4rLGx8_VC-EuFFyhIWorpLIu_");
        map.put("id_user", payment.getId_user());
        return template.queryForObject(sql, map, Long.class);
    }

    public Payment getPaymentById(long id_payment){
        String sql = "SELECT * FROM payment WHERE id_payment = :id_payment";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_payment", id_payment);
        return template.queryForObject(sql, parameterSource, (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setId_payment(rs.getLong("id_payment"));
            payment.setTotal(rs.getLong("total"));
            payment.setCheck_URL(rs.getString("check_URL"));
            payment.setId_user(rs.getLong("id_user"));
            return payment;
        });
    }

    public void editPayment(Payment payment){
        String sql = "UPDATE payment SET total = :total, check_URL = :check_URL, id_user = :id_user WHERE id_payment = :id_payment";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("total", payment.getTotal())
                .addValue("check_URL", payment.getCheck_URL())
                .addValue("id_user", payment.getId_user())
                .addValue("id_payment", payment.getId_payment());
        template.update(sql, parameterSource);
    }

    public void deletePayment(long id_payment){
        String sql = "DELETE FROM payment WHERE id_payment = :id_payment";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_payment", id_payment);
        template.update(sql, parameterSource);
    }
}
