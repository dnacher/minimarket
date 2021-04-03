package com.minimarket.minimarketapp.persistence.dao;

import com.minimarket.minimarketapp.error.ErrorHandling;
import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.minimarket.minimarketapp.persistence.model.Stock;
import com.minimarket.minimarketapp.persistence.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockDAO {

    @Autowired
    private StockRepository repository;
    private final String STOCK = "El stock";

    public Stock geStockByProductId(Integer id){
        return this.repository.findStocksByProduct_Id(id);
    }

    public List<Stock> getStock(){
        List<Stock> stocks = new ArrayList<>();
        this.repository.findAll().forEach(stock -> stocks.add(stock));
        return stocks;
    }

    public Stock getStockById(Integer id) throws MiniMarketException {
        return this.repository.findById(id).orElseThrow(() ->
                new MiniMarketException(ErrorHandling.valueNotFound(STOCK, id)));
    }

    public Stock saveStock(Stock stock) throws MiniMarketException {
        if(repository.findStocksByProduct_Id(stock.getProduct().getId())!=null){
            return this.repository.save(stock);
        }else{
            throw new MiniMarketException(ErrorHandling.ALREADY_EXIST_PRODUCT_STOCK, HttpStatus.BAD_REQUEST);
        }
    }

    public List<Stock> saveStocks(List<Stock> stocks) throws MiniMarketException {
        List<Stock> finalList= new ArrayList<>();
        this.repository.saveAll(stocks).forEach(stock -> {
            finalList.add(stock);
        });
        return finalList;
    }

    public void deleteStock(Stock stock){
        this.repository.delete(stock);
    }

    public Stock updateStock(Stock stock) throws MiniMarketException {
        if(stock.getId()!=null){
            return this.repository.save(stock);
        }else{
            throw new MiniMarketException(ErrorHandling.valueUpdateError(STOCK));
        }
    }
}
