package br.inatel.quotation_management.repository;

import br.inatel.quotation_management.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {
}
