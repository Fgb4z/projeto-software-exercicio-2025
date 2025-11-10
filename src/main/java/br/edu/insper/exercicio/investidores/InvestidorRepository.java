package br.edu.insper.exercicio.investidores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestidorRepository extends JpaRepository<Investidor, Integer> {
}
