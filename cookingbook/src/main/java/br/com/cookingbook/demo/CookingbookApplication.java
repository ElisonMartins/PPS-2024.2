package br.com.cookingbook.demo;

import br.com.cookingbook.demo.entities.Ingrediente;
import br.com.cookingbook.demo.entities.Preparo;
import br.com.cookingbook.demo.entities.Receita;
import br.com.cookingbook.demo.services.IngredienteService;
import br.com.cookingbook.demo.services.PreparoService;
import br.com.cookingbook.demo.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CookingbookApplication implements CommandLineRunner {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private PreparoService preparoService;

	public static void main(String[] args) {
		SpringApplication.run(CookingbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("======= CookingBook Application =======");
			System.out.println("1. Criar nova receita");
			System.out.println("2. Listar todas as receitas");
			System.out.println("3. Buscar receita por ID");
			System.out.println("4. Atualizar receita");
			System.out.println("5. Deletar receita");
			System.out.println("6. Gerenciar Ingredientes");
			System.out.println("7. Gerenciar Preparo");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();  // Consumir a nova linha

			switch (opcao) {
				case 1:
					criarReceita(scanner);
					break;
				case 2:
					listarReceitas();
					break;
				case 3:
					buscarReceitaPorId(scanner);
					break;
				case 4:
					atualizarReceita(scanner);
					break;
				case 5:
					deletarReceita(scanner);
					break;
				case 6:
					gerenciarIngredientes(scanner);
					break;
				case 7:
					gerenciarPreparo(scanner);
					break;
				case 0:
					System.out.println("Saindo...");
					scanner.close();
					return;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}

	// CRUD Receita

	private void criarReceita(Scanner scanner) {
		System.out.print("Digite o título da receita: ");
		String titulo = scanner.nextLine();

		System.out.print("Digite a descrição da receita: ");
		String descricao = scanner.nextLine();

		System.out.print("Digite o número de porções: ");
		int porcoes = scanner.nextInt();
		scanner.nextLine();  // Consumir a nova linha

		Receita receita = Receita.builder()
				.titulo(titulo)
				.descricao(descricao)
				.porcoes(porcoes)
				.build();

		receitaService.criarReceita(receita);
		System.out.println("Receita criada com sucesso!");
	}

	private void listarReceitas() {
		System.out.println("Listando todas as receitas...");
		receitaService.listarReceitas().forEach(receita -> {
			System.out.println("ID: " + receita.getReceita_id() + " | Título: " + receita.getTitulo() + " | Porções: " + receita.getPorcoes());
		});
	}

	private void buscarReceitaPorId(Scanner scanner) {
		System.out.print("Digite o ID da receita: ");
		Long id = scanner.nextLong();
		scanner.nextLine();  // Consumir a nova linha

		receitaService.listarReceitaPorId(id).ifPresentOrElse(receita -> {
			System.out.println("ID: " + receita.getReceita_id());
			System.out.println("Título: " + receita.getTitulo());
			System.out.println("Descrição: " + receita.getDescricao());
			System.out.println("Porções: " + receita.getPorcoes());
		}, () -> System.out.println("Receita não encontrada."));
	}

	private void atualizarReceita(Scanner scanner) {
		System.out.print("Digite o ID da receita que deseja atualizar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();  // Consumir a nova linha

		System.out.print("Digite o novo título: ");
		String novoTitulo = scanner.nextLine();

		System.out.print("Digite a nova descrição: ");
		String novaDescricao = scanner.nextLine();

		System.out.print("Digite o novo número de porções: ");
		int novasPorcoes = scanner.nextInt();
		scanner.nextLine();  // Consumir a nova linha

		Receita receitaAtualizada = Receita.builder()
				.titulo(novoTitulo)
				.descricao(novaDescricao)
				.porcoes(novasPorcoes)
				.build();

		try {
			receitaService.atualizarReceita(id, receitaAtualizada);
			System.out.println("Receita atualizada com sucesso!");
		} catch (RuntimeException e) {
			System.out.println("Erro ao atualizar a receita: " + e.getMessage());
		}
	}

	private void deletarReceita(Scanner scanner) {
		System.out.print("Digite o ID da receita que deseja deletar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();  // Consumir a nova linha

		try {
			receitaService.deletarReceita(id);
			System.out.println("Receita deletada com sucesso!");
		} catch (RuntimeException e) {
			System.out.println("Erro ao deletar a receita: " + e.getMessage());
		}
	}

	// CRUD Ingrediente

	private void gerenciarIngredientes(Scanner scanner) {
		System.out.println("== Gerenciar Ingredientes ==");
		System.out.print("Digite o nome do ingrediente: ");
		String nome = scanner.nextLine();

		System.out.print("Digite a quantidade do ingrediente: ");
		String quantidade = scanner.nextLine();

		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setNome(nome);
		ingrediente.setQuantidade(quantidade);

		ingredienteService.criarIngrediente(ingrediente);
		System.out.println("Ingrediente adicionado com sucesso!");
	}

	// CRUD Preparo

	private void gerenciarPreparo(Scanner scanner) {
		System.out.println("== Gerenciar Preparo ==");
		System.out.print("Digite o tipo de preparo: ");
		String tipo = scanner.nextLine();

		System.out.print("Digite a descrição do preparo: ");
		String descricao = scanner.nextLine();

		Preparo preparo = new Preparo();
		preparo.setTipo(tipo);
		preparo.setDescricao(descricao);

		preparoService.criarPreparo(preparo);
		System.out.println("Preparo adicionado com sucesso!");
	}
}
