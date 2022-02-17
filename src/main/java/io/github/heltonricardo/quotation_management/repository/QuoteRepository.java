package io.github.heltonricardo.quotation_management.repository;

import io.github.heltonricardo.quotation_management.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
}
