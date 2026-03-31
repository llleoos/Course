package com.portfolio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portfolio.entity.PortfolioItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PortfolioItemMapper extends BaseMapper<PortfolioItem> {
    
    @Select("SELECT COALESCE(SUM(current_price * quantity), 0) FROM portfolio_item")
    java.math.BigDecimal selectTotalPortfolioValue();
    
    @Select("SELECT COALESCE(SUM((current_price - purchase_price) * quantity), 0) FROM portfolio_item")
    java.math.BigDecimal selectTotalPortfolioProfit();
}
