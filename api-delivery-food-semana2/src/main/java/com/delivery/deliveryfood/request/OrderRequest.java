package com.delivery.deliveryfood.request;

import com.delivery.deliveryfood.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String customerName;
    private String customerEmail;
    private List<Food> items;
}


