package com.fabrakadabra.webapp.dto.order;

import com.fabrakadabra.webapp.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    private long orderId;
    private CustomerDto customer;
    private List<ProductDto> items;
    @Nullable
    private String status;
}
