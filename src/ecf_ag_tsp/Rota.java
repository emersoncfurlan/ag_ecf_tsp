package ecf_ag_tsp;

import java.util.ArrayList;

public class Rota {
	private ArrayList<Cidade> rota;
	private double distancia = 0;
	
	public Rota(Individuo individuo, ArrayList<Cidade> cidades) {
	// GET - Cromossomo do Individuo -> Rotas
	ArrayList<?> cromossomo = individuo.getCromossomo();
	
	// Cria as rotas
	this.rota = new ArrayList<Cidade>(cidades.size());
		for (int geneIndex = 0; geneIndex < cromossomo.size(); geneIndex++) {
			this.rota.set(geneIndex, cidades.get(geneIndex));
		}
	}
	
	public double getDistancia() {
		if (this.distancia > 0) {
		return this.distancia;
		}
		// Percorre as cidades na rota e calcula a distância total da rota 
		double distanciaTotal = 0;
		for(int i = 0; i + 1 < this.rota.size(); i++) {
			distanciaTotal += this.rota.get(i).getDistancia(this.rota.get((i + 1)));
		}		
		distanciaTotal += this.rota.get(this.rota.size()-1).getDistancia(this.rota.get(0));
		this.distancia = distanciaTotal;
	return distanciaTotal;
	}
}
