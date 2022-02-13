package br.inatel.quotation_management.service;

import br.inatel.quotation_management.model.Stock;
import br.inatel.quotation_management.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository quoteRepository;

    public Stock create(Stock stock) {
        return quoteRepository.save(stock);
    }

    public Optional<Stock> findByStockId(String stockId) {
        return quoteRepository.findByStockId(stockId);
    }

    public Iterable<Stock> findAll() {
        return quoteRepository.findAll();
    }
}
