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
        // Inicializa a popula��o
    	Populacao novaPopulacao = new Populacao(this.tamPopulacao, tamCromossomo);
        return novaPopulacao;
    }
    
	public void avaliaPopulacao(Populacao populacao, ArrayList<Cidade> cidades) {
		double fitnessPopulacao = 0;
		// Loop sobre a popula��o avaliando os indiv�duos e somando a aptid�o da
		// popula��o
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
	 * Neste m�todo, primeiro criamos uma nova popula��o para manter a prole. Ent�o
	 * o a popula��o atual � repetida na ordem do indiv�duo mais apto primeiro. Se
	 * elitismo estiver habilitado, os primeiros poucos indiv�duos da elite s�o
	 * ignorados e adicionados ao novo popula��o inalterada. Os indiv�duos restantes
	 * s�o ent�o considerados para crossover usando a taxa de cruzamento. Se o
	 * crossover deve ser aplicado ao indiv�duo, um dos pais � selecionado usando o
	 * m�todo selectParent (neste caso, selectParent implementa sele��o de torneio
	 * como no Cap�tulo 3) e um novo indiv�duo em branco � criado. Em seguida, duas
	 * posi��es aleat�rias no cromossomo do pai1 s�o escolhidas e o subconjunto de
	 * informa��es gen�ticas entre essas posi��es s�o adicionadas � descend�ncia
	 * cromossoma. Finalmente, as informa��es gen�ticas restantes necess�rias s�o
	 * adicionadas no ordem encontrada em pai2; ent�o, quando conclu�do, o indiv�duo
	 * � adicionado ao novo popula��o.
	 */
	public Populacao crossoverPopulation(Populacao populacao) {

		// Crie uma nova popula��o
		Populacao novaPopulacao = new Populacao(populacao.size());

		// Loop sobre a popula��o atual por fitness
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {

			// Obter pai1
			Individuo pai1 = populacao.getFittest(populacaoIndex);
			// Aplicar cruzamento a este indiv�duo?

			if (this._env.taxaDeCruzamento > Math.random() && populacaoIndex >= countElitismo ) {
				
				// Encontre o pai2 com a sele��o do torneio
				Individuo pai2 = this.selecionarPai(populacao);
				
				// Criar cromossomo filho vazio
				ArrayList<Integer> cromossomoFilho = new ArrayList<Integer>(pai1.getTamCromossomo());
				Collections.fill(cromossomoFilho, -1);
				Individuo filho = new Individuo(cromossomoFilho);
				
				// Obter subconjunto de cromossomos pais
				int substrPos1 = (int) (Math.random() * pai1.getTamCromossomo());
				int substrPos2 = (int) (Math.random() * pai1.getTamCromossomo());
				
				// Quanto menor o in�cio e maior o final
				final int startSubstr = Math.min(substrPos1, substrPos2);
				final int endSubstr = Math.max(substrPos1, substrPos2);
				
				// Fa�a um loop e adicione o sub tour do pai1 ao nosso filho
				for (int i = startSubstr; i < endSubstr; i++) {
					filho.setGene(i, pai1.getGene(i));
				}
				
				// Fa�a um loop no tour pelas cidades de pai2 -> rota
				for (int i = 0; i < pai2.getTamCromossomo() ; i++) {
					int pai2Gene = i + endSubstr;
					if (pai2Gene >= pai2.getTamCromossomo()) {
						pai2Gene -= pai2.getTamCromossomo();
					}
					
					// Se o filho n�o tiver a cidade, adicione-a
					if (filho.contemGene(pai2Gene) == false) {
						
						// Fa�a um loop para encontrar uma posi��o livre na "rota" do filho
						for (int j = 0; j < filho.getTamCromossomo(); j++) {
							
							// Posi��o sobressalente encontrada, adicionar cidade
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
				// Adicionar indiv�duo a nova popula��o sem aplicar o cruzamento
				novaPopulacao.setIndividuo(populacaoIndex, pai1);
			}
		}
		return novaPopulacao;
	}

	public Populacao mutarPopulacao(Populacao populacao) {
		// Inicialize a nova popula��o
		Populacao novaPopulacao = new Populacao(tamPopulacao);
		// Loop sobre a popula��o atual por fitness
		for (int populacaoIndex = 0; populacaoIndex < populacao.size(); populacaoIndex++) {
			Individuo individuo = populacao.getFittest(populacaoIndex);
			// Pule a muta��o se este for um indiv�duo de elite
			if (populacaoIndex >= countElitismo){
				// System.out.println("Mutating populacao member"+populacaoIndex);

				// Loop sobre os genes do indiv�duo
				for (int geneIndex = 0; geneIndex < individuo.getTamCromossomo(); geneIndex++) {
					// Este gene precisa de muta��o?
					if (taxaDeMutacao > Math.random()) {
						// Obter nova posi��o do gene
						int newGenePos = (int) (Math.random() * individuo.getTamCromossomo());
						// Fa�a os genes trocarem (swap)
						int gene1 = individuo.getGene(newGenePos);
						int gene2 = individuo.getGene(geneIndex);
						// Swap genes
						individuo.setGene(geneIndex, gene1);
						individuo.setGene(newGenePos, gene2);
					}
				}
			}
			// Adicionar indiv�duo � popula��o
			novaPopulacao.setIndividuo(populacaoIndex, individuo);
		}
		// Retornar popula��o mutada
		return novaPopulacao;
	}

	@SuppressWarnings("unchecked")
	public Individuo selecionarPai(Populacao populacao) {
		// Criar um torneio
		Populacao torneio = new Populacao(this._env.tamTorneio);

		// Adicionando indiv�duos aleat�rios ao torneio
		Collections.shuffle((List<Individuo>) populacao);
		for (int i = 0; i < this._env.tamTorneio; i++) {
			Individuo torneioIndividuo = (Individuo) populacao.getIndividuo(i);
			torneio.setIndividuo(i, torneioIndividuo);
		}

		// Return the best
		return torneio.getFittest(0);
	}
}
