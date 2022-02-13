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
    private final ManagerService managerService;

    public Optional<Stock> create(Stock stock) {

        boolean stockNotExists =
                quoteRepository.findById(stock.getId()).isEmpty()
                && findByStockId(stock.getStockId()).isEmpty();

        boolean allowedToCreate =
                managerService.existsByStockId(stock.getStockId());

        Optional<Stock> response = Optional.empty();

        if (stockNotExists && allowedToCreate) {
            response = Optional.of(quoteRepository.save(stock));
        }

        return response;
    }

    public Optional<Stock> findByStockId(String stockId) {

        return quoteRepository.findByStockId(stockId);
    }

    public Iterable<Stock> findAll() {

        return quoteRepository.findAll();
    }
}
