package br.edu.insper.exercicio.investidores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvestidorService {

    @Autowired
    private InvestidorRepository investidorRepository;

    public List<Investidor> getInvestidores() {
        return investidorRepository.findAll();
    }

    public Investidor createInvestidor(Investidor investidor) {
        return investidorRepository.save(investidor);
    }

    public Investidor getInvestidor(Integer id) {
        return investidorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Investidor não encontrado"));
    }

    public void deleteInvestidor(Integer id) {
        if (!investidorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investidor não encontrado");
        }
        investidorRepository.deleteById(id);
    }
}
