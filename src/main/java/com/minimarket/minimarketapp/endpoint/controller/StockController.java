package com.minimarket.minimarketapp.endpoint.controller;

import com.minimarket.minimarketapp.domain.service.StockService;
import org.springframework.web.bind.annotation.*;
import com.minimarket.minimarketapp.persistence.model.Stock;
import com.minimarket.minimarketapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping(value = "/")
    public Stock saveStock(@RequestBody Stock stock){
        return this.stockService.saveStock(stock);
    }

    @PostMapping(value = "/mul")
    public List<Stock> saveStocks(@RequestBody List<Stock> stocks){
        List<Stock> finalList = new ArrayList<>();
        stocks.forEach(stock -> {
            finalList.add(this.stockService.saveStock(stock));
        });
        return finalList;
    }

    @PutMapping(value = "/{id}")
    public Stock updateStock(@PathVariable Integer id, @RequestBody Stock stock){
        String msg = String.format("The Stock Id %s is different from the Url Id",stock.getId());
        Utils.validateUrlIdEqualsBodyId(id,stock.getId(),msg);
        return this.stockService.updateStock(stock);
    }

    @PutMapping(value = "/mul")
    public List<Stock> updateStock(@RequestBody List<Stock> stocks){
        List<Stock> finalList = new ArrayList<>();
        stocks.forEach(stock -> {
            finalList.add(this.stockService.updateStock(stock));
        });
        return finalList;
    }

    @GetMapping(value = "/")
    public List<Stock> getStock(){
        return this.stockService.getStock();
    }

    @GetMapping(value = "/{id}")
    public Stock getStockById(@PathVariable Integer id){
        return this.stockService.getStockById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStock(@PathVariable Integer id, Stock stock){
        String msg = String.format("The Stock Id %s is different from the Url Id",stock.getId());
        Utils.validateUrlIdEqualsBodyId(id,stock.getId(),msg);
        this.stockService.deleteStock(stock);
    }
}
