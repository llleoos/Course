package com.portfolio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.portfolio.dto.PortfolioCreateDTO;
import com.portfolio.dto.PortfolioUpdateDTO;
import com.portfolio.entity.PortfolioItem;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.mapper.PortfolioItemMapper;
import com.portfolio.service.PortfolioService;
import com.portfolio.vo.PortfolioItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    
    private final PortfolioItemMapper portfolioItemMapper;
    
    @Override
    public PortfolioItemVO create(PortfolioCreateDTO createDTO) {
        PortfolioItem item = PortfolioItem.builder()
                .ticker(createDTO.getTicker())
                .assetType(createDTO.getAssetType())
                .quantity(createDTO.getQuantity())
                .purchasePrice(createDTO.getPurchasePrice())
                .currentPrice(createDTO.getPurchasePrice())
                .purchaseDate(createDTO.getPurchaseDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        portfolioItemMapper.insert(item);
        return convertToVO(item);
    }
    
    @Override
    public List<PortfolioItemVO> listAll() {
        return portfolioItemMapper.selectList(new LambdaQueryWrapper<>())
                .stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }
    
    @Override
    public PortfolioItemVO getById(Long id) {
        PortfolioItem item = portfolioItemMapper.selectById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Portfolio item not found with id: " + id);
        }
        return convertToVO(item);
    }
    
    @Override
    public PortfolioItemVO update(Long id, PortfolioUpdateDTO updateDTO) {
        PortfolioItem item = portfolioItemMapper.selectById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Portfolio item not found with id: " + id);
        }
        
        if (updateDTO.getTicker() != null) {
            item.setTicker(updateDTO.getTicker());
        }
        if (updateDTO.getAssetType() != null) {
            item.setAssetType(updateDTO.getAssetType());
        }
        if (updateDTO.getQuantity() != null) {
            item.setQuantity(updateDTO.getQuantity());
        }
        if (updateDTO.getPurchasePrice() != null) {
            item.setPurchasePrice(updateDTO.getPurchasePrice());
        }
        if (updateDTO.getCurrentPrice() != null) {
            item.setCurrentPrice(updateDTO.getCurrentPrice());
        }
        if (updateDTO.getPurchaseDate() != null) {
            item.setPurchaseDate(updateDTO.getPurchaseDate());
        }
        item.setUpdatedAt(LocalDateTime.now());
        
        portfolioItemMapper.updateById(item);
        return convertToVO(item);
    }
    
    @Override
    public void delete(Long id) {
        PortfolioItem item = portfolioItemMapper.selectById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Portfolio item not found with id: " + id);
        }
        portfolioItemMapper.deleteById(id);
    }
    
    @Override
    public BigDecimal calculateTotalValue() {
        return portfolioItemMapper.selectTotalPortfolioValue();
    }
    
    @Override
    public BigDecimal calculateTotalProfit() {
        return portfolioItemMapper.selectTotalPortfolioProfit();
    }
    
    private PortfolioItemVO convertToVO(PortfolioItem item) {
        BigDecimal profit = calculateProfit(item);
        
        return PortfolioItemVO.builder()
                .id(item.getId())
                .ticker(item.getTicker())
                .assetType(item.getAssetType())
                .quantity(item.getQuantity())
                .purchasePrice(item.getPurchasePrice())
                .currentPrice(item.getCurrentPrice())
                .purchaseDate(item.getPurchaseDate())
                .profit(profit)
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
    
    private BigDecimal calculateProfit(PortfolioItem item) {
        if (item.getCurrentPrice() == null || item.getPurchasePrice() == null) {
            return BigDecimal.ZERO;
        }
        return item.getCurrentPrice()
                .subtract(item.getPurchasePrice())
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}
