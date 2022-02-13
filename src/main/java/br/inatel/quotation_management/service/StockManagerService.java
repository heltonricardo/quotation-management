package br.inatel.quotation_management.service;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.repository.StockManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockManagerService {

    private final StockManagerRepository stockManagerRepository;

    public void create(StockManager stockManager)
            throws AlreadyExistsException {

        boolean stockManagerAlreadyExists =
                existsByStockId(stockManager.getId());

        if (stockManagerAlreadyExists) {
            throw new AlreadyExistsException();
        }

        stockManagerRepository.save(stockManager);
    }

    public Iterable<StockManager> findAll() {

        return stockManagerRepository.findAll();
    }

    public boolean existsByStockId(String stockId) {

        return stockManagerRepository.existsById(stockId);
    }

    public boolean notExistsByStockQuoteId(String stockQuoteId) {

        return !existsByStockId(stockQuoteId);
    }
}
