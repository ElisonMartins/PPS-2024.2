package br.com.cookingbook.demo.factories;

import br.com.cookingbook.demo.entities.Ingrediente;
import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredienteFactoryImpl implements IngredienteFactory {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Override
    public Ingrediente criarIngrediente(String nome, String quantidade, Long receitaId) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(nome);
        ingrediente.setQuantidade(quantidade);

        // Associar o ingrediente à receita
        Receita receita = receitaRepository.findById(receitaId)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada com ID: " + receitaId));
        ingrediente.setReceita(receita);

        return ingrediente;
    }
}
