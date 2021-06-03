package ecf_ag_tsp;

import java.util.ArrayList;

public class Individuo {
	private ArrayList<Integer> cromossomo;
	private double fitness = -1;
		
	public ArrayList<?> getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(ArrayList<Integer> cromossomo) {
		this.cromossomo = cromossomo;
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public int getTamCromossomo() {
		return this.cromossomo.size();
	}
	
	public void setGene(int index, int gene) {
		this.cromossomo.set(index, gene);
	}

	public int getGene(int index) {
		return this.cromossomo.get(index);
	}
	
	public Individuo(ArrayList<Integer> cromossomoFilho) {
		// Cria um individuo apartir de um cromossomo
		this.cromossomo = cromossomoFilho;
	}
	
	public Individuo(int tamCromossomo) {
		// Cria um individuo aleatorio
		ArrayList<Integer> individuo = new ArrayList<Integer>(tamCromossomo);
		
		 /*
		 * * Neste caso, não podemos mais escolher simplesmente 0s e 1s - precisamos
		 * usar todos os índices de cidades disponíveis. Também não precisamos randomizar ou
		 * embaralhar este cromossomo, pois o crossover e a mutação acabarão
		 * cuide disso para nós.
		 */
		
		this.cromossomo = individuo;
	}
	


	public boolean contemGene(int gene) {
		for (int i = 0; i < this.cromossomo.size(); i++) {
			if (this.cromossomo.get(i).equals(gene)) {
				return true;
			}
		}
		return false;
	}

}
