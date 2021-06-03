package ecf_ag_tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AG {
	VariaveisDeAmbiente _env;
	private int tamPopulacao;
	private double taxaDeMutacao;
	private double taxaDeCruzamento;
	private int countElitismo;
	private int tamTorneio;
	

	public AG(int tamPopulacao, double taxaDeMutacao, double taxaDeCruzamento, int countElitismo, int tamTorneio) {
		this.tamPopulacao = 100;
		this.tamTorneio = 5;
		this.countElitismo = 2;
		this.taxaDeMutacao = 0.3;
		this.taxaDeCruzamento = 0.7;
	}

	public boolean condicaoDeParada(int countGeracoes, int maxGeracoes) {
		return (countGeracoes > maxGeracoes);
	}

    public Populacao incializaPopulacao(int tamCromossomo){
        // Inicializa a população
    	Populacao novaPopulacao = new Populacao(this.tamPopulacao, tamCromossomo);
        return novaPopulacao;
    }
    
	public void avaliaPopulacao(Populacao populacao, ArrayList<Cidade> cidades) {
		double fitnessPopulacao = 0;
		// Loop sobre a população avaliando os indivíduos e somando a aptidão da
		// população
		for (Individuo indivuo : populacao.getIndividuos()) {
			fitnessPopulacao += this.calculaFitness(indivuo, cidades);
		}
		double avgFitness = fitnessPopulacao / populacao.size();
		populacao.setPopulacaoFitness(avgFitness);
	}

	public double calculaFitness(Individuo individuo, ArrayList<Cidade> cidades) {
		// Get fitness
		Rota rota = new Rota(individuo, cidades);
		double fitness = 1 / rota.getDistancia();
		// Set fitness
		individuo.setFitness(fitness);
		return fitness;
	}

	/*
	 * Neste método, primeiro criamos uma nova população para manter a prole. Então
	 * o a população atual é repetida na ordem do indivíduo mais apto primeiro. Se
	 * elitismo estiver habilitado, os primeiros poucos indivíduos da elite são
	 * ignorados e adicionados ao novo população inalterada. Os indivíduos restantes
	 * são então considerados para crossover usando a taxa de cruzamento. Se o
	 * crossover deve ser aplicado ao indivíduo, um dos pais é selecionado usando o
	 * método selectParent (neste caso, selectParent implementa seleção de torneio
	 * como no Capítulo 3) e um novo indivíduo em branco é criado. Em seguida, duas
	 * posições aleatórias no cromossomo do pai1 são escolhidas e o subconjunto de
	 * informações genéticas entre essas posições são adicionadas à descendência
	 * cromossoma. Finalmente, as informações genéticas restantes necessárias são
	 * adicionadas no ordem encontrada em pai2; então, quando concluído, o indivíduo
	 * é adicionado ao novo população.
	 */
	public Populacao crossoverPopulation(Populacao populacao) {

		// Crie uma nova população
		Populacao novaPopulacao = new Populacao(populacao.size());

		// Loop sobre a população atual por fitness
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {

			// Obter pai1
			Individuo pai1 = populacao.getFittest(populacaoIndex);
			// Aplicar cruzamento a este indivíduo?

			if (this._env.taxaDeCruzamento > Math.random() && populacaoIndex >= countElitismo ) {
				
				// Encontre o pai2 com a seleção do torneio
				Individuo pai2 = this.selecionarPai(populacao);
				
				// Criar cromossomo filho vazio
				ArrayList<Integer> cromossomoFilho = new ArrayList<Integer>(pai1.getTamCromossomo());
				Collections.fill(cromossomoFilho, -1);
				Individuo filho = new Individuo(cromossomoFilho);
				
				// Obter subconjunto de cromossomos pais
				int substrPos1 = (int) (Math.random() * pai1.getTamCromossomo());
				int substrPos2 = (int) (Math.random() * pai1.getTamCromossomo());
				
				// Quanto menor o início e maior o final
				final int startSubstr = Math.min(substrPos1, substrPos2);
				final int endSubstr = Math.max(substrPos1, substrPos2);
				
				// Faça um loop e adicione o sub tour do pai1 ao nosso filho
				for (int i = startSubstr; i < endSubstr; i++) {
					filho.setGene(i, pai1.getGene(i));
				}
				
				// Faça um loop no tour pelas cidades de pai2 -> rota
				for (int i = 0; i < pai2.getTamCromossomo() ; i++) {
					int pai2Gene = i + endSubstr;
					if (pai2Gene >= pai2.getTamCromossomo()) {
						pai2Gene -= pai2.getTamCromossomo();
					}
					
					// Se o filho não tiver a cidade, adicione-a
					if (filho.contemGene(pai2Gene) == false) {
						
						// Faça um loop para encontrar uma posição livre na "rota" do filho
						for (int j = 0; j < filho.getTamCromossomo(); j++) {
							
							// Posição sobressalente encontrada, adicionar cidade
							if (filho.getGene(j) == -1) {
								filho.setGene(j, pai2.getGene(pai2Gene));
								break;
							}
						}
					}
				}
				// Adicionar filho
				novaPopulacao.setIndividuo(populacaoIndex, filho);
			} else {
				// Adicionar indivíduo a nova população sem aplicar o cruzamento
				novaPopulacao.setIndividuo(populacaoIndex, pai1);
			}
		}
		return novaPopulacao;
	}

	public Populacao mutarPopulacao(Populacao populacao) {
		// Inicialize a nova população
		Populacao novaPopulacao = new Populacao(tamPopulacao);
		// Loop sobre a população atual por fitness
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {
			Individuo individuo = populacao.getFittest(populacaoIndex);
			// Pule a mutação se este for um indivíduo de elite
			if (populacaoIndex >= countElitismo){
				// System.out.println("Mutating populacao member"+populacaoIndex);

				// Loop sobre os genes do indivíduo
				for (int geneIndex = 0; geneIndex < individuo.getTamCromossomo(); geneIndex++) {
					// Este gene precisa de mutação?
					if (taxaDeMutacao > Math.random()) {
						// Obter nova posição do gene
						int newGenePos = (int) (Math.random() * individuo.getTamCromossomo());
						// Faça os genes trocarem (swap)
						int gene1 = individuo.getGene(newGenePos);
						int gene2 = individuo.getGene(geneIndex);
						// Swap genes
						individuo.setGene(geneIndex, gene1);
						individuo.setGene(newGenePos, gene2);
					}
				}
			}
			// Adicionar indivíduo à população
			novaPopulacao.setIndividuo(populacaoIndex, individuo);
		}
		// Retornar população mutada
		return novaPopulacao;
	}

	@SuppressWarnings("unchecked")
	public Individuo selecionarPai(Populacao populacao) {
		// Criar um torneio
		Populacao torneio = new Populacao(this._env.tamTorneio);

		// Adicionando indivíduos aleatórios ao torneio
		Collections.shuffle((List<Individuo>) populacao);
		for (int i = 0; i < this._env.tamTorneio; i++) {
			Individuo torneioIndividuo = (Individuo) populacao.getIndividuo(i);
			torneio.setIndividuo(i, torneioIndividuo);
		}

		// Return the best
		return torneio.getFittest(0);
	}
}
