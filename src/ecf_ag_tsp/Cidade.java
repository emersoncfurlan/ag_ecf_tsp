package ecf_ag_tsp;

import java.util.Arrays;

class Cidade implements Geolocation{
	private String nome;
	private int[] coordenadas;
	
	
	public Cidade() {
	}
	
	public Cidade(String label, int[] coordenadas) {
//		this.nome = label;
//		this.coordenadas = coordenadas;
		setNome(label);
		setCoordenadas(coordenadas);
	}
	
	public Cidade(int x, int y) {
		setCoordenadas(x,y);
	}
	
	public Cidade(String label, int x, int y) {
		setNome(label);
		setCoordenadas(x,y);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int[] getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(int x, int y) {
		int[] novasCoordenadas = new int[2];
		novasCoordenadas[0] = x;
		novasCoordenadas[1] = y;
		this.setCoordenadas(novasCoordenadas);
	}
	
	
	@Override
	public void setCoordenadas(int[] coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	public void setCoordenadas(String label, int[] coordenadas) {
		this.nome = label;
		this.coordenadas = coordenadas;
	}
	
	public double getDistancia(Cidade cidade) {
		double distancia;
		int[] coordenada = cidade.getCoordenadas();
		int x = (int) Math.pow((this.coordenadas[0] - coordenada[0]), 2);
		int y = (int) Math.pow((this.coordenadas[1] - coordenada[1]), 2);
		distancia = Math.sqrt(x + y);
		return distancia;
	}

	public String toString() {
		return nome + " ; " + Arrays.toString(coordenadas);
	}

}
