package com.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioUpdateDTO {
    
    private String ticker;
    
    private String assetType;
    
    private Integer quantity;
    
    private BigDecimal purchasePrice;
    
    private BigDecimal currentPrice;
    
    private LocalDate purchaseDate;
}
