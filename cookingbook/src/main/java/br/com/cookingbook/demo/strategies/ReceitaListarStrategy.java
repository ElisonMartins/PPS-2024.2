package br.com.cookingbook.demo.strategies;

import java.util.List;

import br.com.cookingbook.demo.entities.Receita;

public interface ReceitaListarStrategy {
    List<Receita> Listar(List<Receita> receitas);
}
