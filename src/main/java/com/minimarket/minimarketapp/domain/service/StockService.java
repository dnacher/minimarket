package com.minimarket.minimarketapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimarket.minimarketapp.persistence.dao.StockDAO;
import com.minimarket.minimarketapp.persistence.model.Stock;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockDAO stockDAO;

    public List<Stock> getStock() {
        return stockDAO.getStock();
    }

    public Stock getStockById(Integer id) {
        return stockDAO.getStockById(id);
    }

    public Stock saveStock(Stock stock) {
        return stockDAO.saveStock(stock);
    }

    public Stock updateStock(Stock stock) {
        return stockDAO.updateStock(stock);
    }

    public Stock addStock(Integer productId, Integer amount) {
        return stockDAO.addStock(productId, amount);
    }

    public void deleteStock(Stock stock) {
        stockDAO.deleteStock(stock);
    }

}
