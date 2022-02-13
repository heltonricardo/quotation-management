package br.inatel.quotation_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockQuote {

    @Id
    private String id;

    @Column(unique = true)
    private String stockQuoteId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Quote> quotes;
}
