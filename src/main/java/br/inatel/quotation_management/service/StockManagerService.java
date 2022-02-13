package br.inatel.quotation_management.service;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.repository.StockManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockManagerService {

    private final StockManagerRepository managerRepository;

    public void create(StockManager manager) throws AlreadyExistsException {

        boolean managerAlreadyExists = existsByStockQuoteId(manager.getId());

        if (managerAlreadyExists) {
            throw new AlreadyExistsException();
        }

        managerRepository.save(manager);
    }

    public Iterable<StockManager> findAll() {

        return managerRepository.findAll();
    }

    public boolean existsByStockQuoteId(String stockQuoteId) {

        return managerRepository.existsById(stockQuoteId);
    }

    public boolean notExistsByStockQuoteId(String stockQuoteId) {

        return !existsByStockQuoteId(stockQuoteId);
    }
}
