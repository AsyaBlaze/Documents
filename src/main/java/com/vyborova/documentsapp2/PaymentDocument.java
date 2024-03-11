package com.vyborova.documentsapp2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;


@Getter
@Setter
@ToString
@Component
public class PaymentDocument {
    private Date paymentDate;
    private Invoice invoice;
}
