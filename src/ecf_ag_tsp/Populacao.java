package ecf_ag_tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Populacao {
	private ArrayList<Individuo> populacao;
	private double populacaoFitness = -1;

	/**
	 * Initializes blank populacao of individuos
	 * 
	 * @param populacaoSize
	 *            The size of the populacao
	 */
	public Populacao(int tamPopulacao) {
		// Populacao inicial
		this.populacao = new ArrayList<Individuo>(tamPopulacao);
	}

	/**
	 * Inicializa a população de individuos
	 */
	public Populacao(int tamPopulacao, int tamCromossomo) {
		// Populacao inicial
		this.populacao = new ArrayList<Individuo>(tamPopulacao);

		// Loop sobre o tamanho da população
//		do {
//			
//		}while(this.populacao.size()!= tamPopulacao)
		for (int individuoCount = 0; individuoCount < tamPopulacao; individuoCount++) {
			// Crie individuo
			Individuo individuo = new Individuo(tamCromossomo);
			// Adicionar individuo à população
			this.populacao.set(individuoCount, individuo);
		}
	}
	
	

    public static Populacao generateBasicPopulation(int tamanhoPopulacao, int qtdCidadesMap){
        // Mapeamento com as cidades default
        int map[][] = tools.mapCities(qtdCidadesMap);
        ArrayList<Cidade> cidadesLoad = new ArrayList();
        Cidade cidadeInicio = new Cidade();
        cidadeInicio = loadCidadesFromArray(map, cidadesLoad);
        Populacao popInicial = new Populacao();
        for (int i = 0; i < tamanhoPopulacao; i++) {
            // Array com a posicao dos genes
            // random sequencia de cidades p/ Caminhos/Rotas (possiveis - pop inicial)
            popInicial.populacao.add(new Caminho());
            ArrayList<Cidade> cities = cidadesLoad;
            Collections.shuffle(cities);
            popInicial.populacao.get(i).setInicio(cidadeInicio);
            for (Cidade c : cities) {
                popInicial.populacao.get(i).sequenciaCidades.add(c);
            }
            popInicial.populacao.get(i).setFitness(); // TODO: LOADFITNESS
        }
        return popInicial;
    }
	
    
    public static Cidade loadCidadesFromArray(int[][] map, ArrayList<Cidade> cidadesLoad){
        Cidade cidadeInicio = new Cidade();
        for (int i = 0; i < map.length; i++) {
            Cidade cidadeAdd = new Cidade();
            // A primeira cidade (coordenada x,y) do arquivo é setado como cidade INICIAL
            if (cidadesLoad.size() == 0) {
                cidadeInicio = cidadeAdd;
            }
            cidadeAdd.setCoordenadas(0,1);
            cidadesLoad.add(cidadeAdd);
        }
        // Remover cidade start do array
        cidadesLoad.remove(0);

        return cidadeInicio;
    }
	/**
	 * Get individuos from the populacao
	 * 
	 * @return individuos Individuos in populacao
	 */
	public ArrayList<Individuo> getIndividuos() {
		return this.populacao;
	}

	/**
	 * Find fittest individuo in the populacao
	 * 
	 * @param offset
	 * @return individuo Fittest individuo at offset
	 */
	public Individuo getFittest(int index) {
		// Ordena a população por fitness
		Collections.sort(this.populacao, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});

		// Retorna o individuo mais apto (maior fitness)
		return this.populacao.get(index);
	}

	/**
	 * Set populacao's fitness
	 * 
	 * @param fitness
	 *            The populacao's total fitness
	 */
	public void setPopulacaoFitness(double fitness) {
		this.populacaoFitness = fitness;
	}

	/**
	 * Get populacao's fitness
	 * 
	 * @return populacaoFitness The populacao's total fitness
	 */
	public double getPopulacaoFitness() {
		return this.populacaoFitness;
	}

	/**
	 * Get populacao's size
	 * 
	 * @return size The populacao's size
	 */
	public int size() {
		return this.populacao.size();
	}

	/**
	 * Set individuo at offset
	 * 
	 * @param individuo
	 * @param offset
	 * @return individuo
	 */
	public Individuo setIndividuo(int index, Individuo individuo) {
		return populacao.set(index, individuo);
	}

	/**
	 * Get individuo at offset
	 * 
	 * @param offset
	 * @return individuo
	 */
	public Individuo getIndividuo(int index) {
		return populacao.get(index);
	}

	/**
	 * Shuffles the populacao in-place
	 * 
	 * @param void
	 * @return void
	 */
	public void embaralhar() {
		Collections.shuffle(populacao);
//		Random rnd = new Random();
//		for (int i = populacao.length - 1; i > 0; i--) {
//			int index = rnd.nextInt(i + 1);
//			Individuo a = populacao[index];
//			populacao[index] = populacao[i];
//			populacao[i] = a;
//		}
	}

}