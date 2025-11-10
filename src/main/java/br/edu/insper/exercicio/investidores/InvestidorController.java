package br.edu.insper.exercicio.investidores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/investidores")
public class InvestidorController {

    @Autowired
    private InvestidorService investidorService;

    @GetMapping
    public List<Investidor> getInvestidores() {
        return investidorService.getInvestidores();
    }

    @PostMapping
    public Investidor createInvestidor(@RequestBody Investidor investidor) {
        return investidorService.createInvestidor(investidor);
    }

    @GetMapping("/{id}")
    public Investidor getInvestidor(@PathVariable Integer id) {
        return investidorService.getInvestidor(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestidor(@PathVariable Integer id, @AuthenticationPrincipal Jwt jwt) {
        // Verificar se o usuário tem a role de admin
        List<String> roles = jwt.getClaimAsStringList("https://your-app.com/roles");

        if (roles == null || !roles.contains("admin")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem excluir investidores");
        }

        investidorService.deleteInvestidor(id);
    }
}
