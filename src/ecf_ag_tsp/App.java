package ecf_ag_tsp;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
		VariaveisDeAmbiente _env = new VariaveisDeAmbiente(100, 3000, 324, 5, 2, 0.3, 0.7);
		int geracao = 0;
		
		ArrayList<Cidade> cidades = new ArrayList<Cidade>();
		cidades = Util.carregarCidades("ecf_ag_tsp/utils/324.txt");
//		// Loop pra Criar cidades aleatórias
//		for (Cidade cidade : cidades) {
//			int xPos = (int) (100 * Math.random());
//			int yPos = (int) (100 * Math.random());
//			cidades.add(new Cidade(xPos, yPos));
//		}

		// Inicializa o AG
		AG ag = new AG(100, 0.001, 0.9, 2, 5); //
		// Inicializa a populacao
		Populacao populacao = ag.incializaPopulacao(100);

		// Avalia a População
		// Acompanhe a geração atual
		 geracao = 1;
		// Iniciar ciclo de evolução 
		while (ag.condicaoDeParada(geracao, _env.maxGeracoes) == false) {
			// Imprima o indivíduo mais apto da população 
			Rota rota = new Rota(populacao.getFittest(0), cidades);
			System.out.println("G" + geracao + " Melhor Fitness: " + rota.getDistancia());
			// Aplicar o cruzamento
			populacao = ag.crossoverPopulation(populacao);
			// TODO: Aplicara a mutação
			populacao = ag.mutarPopulacao(populacao);
			// Avaliar Populacao
			ag.avaliaPopulacao(populacao, cidades);
			// Incrementar a geração atual
			geracao++;
		}
		
		// Exibindo os resultados
		System.out.println("Finalizado após" + _env.maxGeracoes + "geracões.");
		Rota rota = new Rota(populacao.getFittest(0), cidades);
		System.out.println("Melhor Fitness: " + rota.getDistancia());
	}
}
