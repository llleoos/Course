package com.portfolio.controller;

import com.portfolio.dto.PortfolioCreateDTO;
import com.portfolio.dto.PortfolioUpdateDTO;
import com.portfolio.service.PortfolioService;
import com.portfolio.vo.PortfolioItemVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    
    private final PortfolioService portfolioService;
    
    @PostMapping
    public ResponseEntity<PortfolioItemVO> create(@Valid @RequestBody PortfolioCreateDTO createDTO) {
        PortfolioItemVO result = portfolioService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<PortfolioItemVO>> listAll() {
        List<PortfolioItemVO> items = portfolioService.listAll();
        return ResponseEntity.ok(items);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PortfolioItemVO> getById(@PathVariable Long id) {
        PortfolioItemVO item = portfolioService.getById(id);
        return ResponseEntity.ok(item);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PortfolioItemVO> update(
            @PathVariable Long id,
            @RequestBody PortfolioUpdateDTO updateDTO) {
        PortfolioItemVO updatedItem = portfolioService.update(id, updateDTO);
        return ResponseEntity.ok(updatedItem);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        portfolioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //add some annotation here,to be frequent contributers
    @GetMapping("/value")
    public ResponseEntity<Map<String, BigDecimal>> getTotalValue() {
        BigDecimal totalValue = portfolioService.calculateTotalValue();
        return ResponseEntity.ok(Map.of("totalValue", totalValue));
    }
    
    @GetMapping("/profit")
    public ResponseEntity<Map<String, BigDecimal>> getTotalProfit() {
        BigDecimal totalProfit = portfolioService.calculateTotalProfit();
        return ResponseEntity.ok(Map.of("totalProfit", totalProfit));
    }
}
