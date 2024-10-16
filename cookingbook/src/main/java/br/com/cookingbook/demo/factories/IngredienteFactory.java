package br.com.cookingbook.demo.factories;

import br.com.cookingbook.demo.entities.Ingrediente;

public interface IngredienteFactory {
    Ingrediente criarIngrediente(String nome, String quantidade, Long receitaId);
}
