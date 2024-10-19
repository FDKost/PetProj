package com.example.education.dto.payment;

import com.example.education.entity.User;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class PaymentCreateEditDTO {
    Long total;
    String checkurl;
    User userid;
}
