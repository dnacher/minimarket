package com.minimarket.minimarketapp.persistence.dao;

import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.minimarket.minimarketapp.persistence.model.Stock;
import com.minimarket.minimarketapp.persistence.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockDAO {

    @Autowired
    private StockRepository repository;

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
        {
            String msg = String.format("The stock id %s does not exist", id.toString());
            return new MiniMarketException(msg);
        });
    }

    public Stock saveStock(Stock stock) throws MiniMarketException {
        return this.repository.save(stock);
    }

    public List<Stock> saveStocks(List<Stock> stockes) throws MiniMarketException {
        List<Stock> finalList= new ArrayList<>();
        this.repository.saveAll(stockes).forEach(stock -> {
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
            String msg = String.format("Cannot update a stock without an Id");
            throw new MiniMarketException(msg);
        }
    }
}
