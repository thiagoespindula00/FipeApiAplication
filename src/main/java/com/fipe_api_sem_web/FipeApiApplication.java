package com.fipe_api_sem_web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fipe_api_sem_web.model.*;
import com.fipe_api_sem_web.service.ConsumoAPI;
import com.fipe_api_sem_web.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class FipeApiApplication implements CommandLineRunner {

	final String CARRO = "carros";
	final String MOTO = "motos";
	final String CAMINHAO = "caminhoes";

	public static void main(String[] args) {
		SpringApplication.run(FipeApiApplication.class, args);
	}

	private void mostraMenu() {
		System.out.println("**** OPÇÕES ****");
		System.out.println(CARRO);
		System.out.println(MOTO);
		System.out.println(CAMINHAO);
		System.out.println("Digite uma das opções");
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);


		while (true) {
			mostraMenu();
			String tipoVeiculo = scanner.nextLine();

			String json = ConsumoAPI.getDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas");
			List<Marca> marcas = new ArrayList<>();
			marcas = ConverteDados.converteJsonParaClasse(json, new TypeReference<List<Marca>>() {});

			System.out.println("Marcas");
			for (Marca marca : marcas) {
				System.out.println("Cód: " + marca.codigo() + " Descrição: " + marca.nome());
			}
			System.out.println("Informe o código da marca para consulta:");
			String marca = scanner.nextLine();

			json = ConsumoAPI.getDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marca + "/modelos");
			//System.out.println(json);
			ModelosResponse modelosResponse = ConverteDados.converteJsonParaClasse(json, ModelosResponse.class);
			System.out.println("Modelos");
			modelosResponse.modelos().forEach((modelo -> {
				System.out.println("Cód: " + modelo.codigo() + " Descrição: " + modelo.nome());
			}));
			System.out.println("Digite um trecho do nome do veículo para consulta:");
			String trechoNomeVeiculo = scanner.nextLine();

			modelosResponse.modelos().stream()
					.filter(modelo -> modelo.nome().toLowerCase().contains(trechoNomeVeiculo.toLowerCase()))
					.forEach(modelo -> {
						System.out.println("Cód: " + modelo.codigo() + " Descrição: " + modelo.nome());
					});

			System.out.println("Digite o código do modelo para consultar valores:");
			String modelo = scanner.nextLine();
			json = ConsumoAPI.getDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marca + "/modelos/" + modelo + "/anos");
			List<Ano> anos = ConverteDados.converteJsonParaClasse(json, new TypeReference<List<Ano>>() {});


			List<Veiculo> veiculos = new ArrayList<>();
			for (Ano ano : anos) {
				json = ConsumoAPI.getDados("https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marca + "/modelos/" + modelo + "/anos/" + ano.codigo());
				Veiculo veiculo = ConverteDados.converteJsonParaClasse(json, Veiculo.class);
				veiculos.add(veiculo);
			}

			veiculos.forEach(System.out::println);
		}
	}
}
