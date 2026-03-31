package com.portfolio.service;

import com.portfolio.dto.PortfolioCreateDTO;
import com.portfolio.dto.PortfolioUpdateDTO;
import com.portfolio.vo.PortfolioItemVO;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioService {
    
    PortfolioItemVO create(PortfolioCreateDTO createDTO);
    
    List<PortfolioItemVO> listAll();
    
    PortfolioItemVO getById(Long id);
    
    PortfolioItemVO update(Long id, PortfolioUpdateDTO updateDTO);
    
    void delete(Long id);
    
    BigDecimal calculateTotalValue();
    
    BigDecimal calculateTotalProfit();
}
