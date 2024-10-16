package br.com.cookingbook.demo.entities;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // Necessário para o Hibernate
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receita_id;
    private String titulo;
    private String descricao;
    @OneToMany
    private List<Ingrediente> ingredientes;
    @OneToOne
    private Preparo preparo;
    private Date tempo;
    private int porcoes;

    // Método estático para retornar o builder
    public static Builder builder() {
        return new Builder();
    }

    // Implementação do padrão Builder
    public static class Builder {
        private String titulo;
        private String descricao;
        private List<Ingrediente> ingredientes;
        private Preparo preparo;
        private Date tempo;
        private int porcoes;

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder ingredientes(List<Ingrediente> ingredientes) {
            this.ingredientes = ingredientes;
            return this;
        }

        public Builder preparo(Preparo preparo) {
            this.preparo = preparo;
            return this;
        }

        public Builder tempo(Date tempo) {
            this.tempo = tempo;
            return this;
        }

        public Builder porcoes(int porcoes) {
            this.porcoes = porcoes;
            return this;
        }

        public Receita build() {
            Receita receita = new Receita();
            receita.setTitulo(this.titulo);
            receita.setDescricao(this.descricao);
            receita.setIngredientes(this.ingredientes);
            receita.setPreparo(this.preparo);
            receita.setTempo(this.tempo);
            receita.setPorcoes(this.porcoes);
            return receita;
        }
    }
}
