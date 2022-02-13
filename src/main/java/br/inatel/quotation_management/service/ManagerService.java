package br.inatel.quotation_management.service;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.model.Manager;
import br.inatel.quotation_management.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public void create(Manager manager) throws AlreadyExistsException {

        boolean managerAlreadyExists = existsByStockId(manager.getId());

        if (managerAlreadyExists) {
            throw new AlreadyExistsException();
        }

        managerRepository.save(manager);
    }

    public Iterable<Manager> findAll() {

        return managerRepository.findAll();
    }

    public boolean existsByStockId(String stockId) {

        return managerRepository.existsById(stockId);
    }

    public boolean notExistsByStockId(String stockId) {

        return !existsByStockId(stockId);
    }
}
