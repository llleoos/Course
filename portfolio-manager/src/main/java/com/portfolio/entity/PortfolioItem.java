package com.portfolio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("portfolio_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioItem {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String ticker;
    
    @TableField("asset_type")
    private String assetType;
    
    private Integer quantity;
    
    @TableField("purchase_price")
    private BigDecimal purchasePrice;
    
    @TableField("current_price")
    private BigDecimal currentPrice;
    
    @TableField("purchase_date")
    private LocalDate purchaseDate;
    
    @TableField("created_at")
    private LocalDateTime createdAt;
    
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
