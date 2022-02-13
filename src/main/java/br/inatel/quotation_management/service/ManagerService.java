package br.inatel.quotation_management.service;

import br.inatel.quotation_management.model.Manager;
import br.inatel.quotation_management.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository quoteRepository;

    public Optional<Manager> create(Manager manager) {

        boolean managerNotExists =
                quoteRepository.findById(manager.getId()).isEmpty();

        Optional<Manager> response = Optional.empty();

        if (managerNotExists) {
            response = Optional.of(quoteRepository.save(manager));
        }

        return response;
    }

    public Iterable<Manager> findAll() {

        return quoteRepository.findAll();
    }

    public boolean existsByStockId(String stockId) {
        
        return quoteRepository.existsById(stockId);
    }
}
