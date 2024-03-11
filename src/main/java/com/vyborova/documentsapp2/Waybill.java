package com.vyborova.documentsapp2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
public class Waybill {
    private Date dateOfCreation;
    private String shippingAddress;
    private Map<Item, Integer> items;
}
