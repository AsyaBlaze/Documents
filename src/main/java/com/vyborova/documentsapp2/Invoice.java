package com.vyborova.documentsapp2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@ToString
@Component
public class Invoice {
    private Date date;
    private double totalAmount;
    private String creditCardNumber;

}
