package ecf_ag_tsp;

public class VariaveisDeAmbiente {
	int numCidades;
	 int maxGeracoes;
	 int tamPopulacao;
	 int tamTorneio;
	 int countElitismo;
	 double taxaDeMutacao;
	 double taxaDeCruzamento;
	
	
	public VariaveisDeAmbiente() {
		this.numCidades = 100;
		this.maxGeracoes = 3000;
		this.tamPopulacao = 324;
		this.tamTorneio = 5;
		this.countElitismo = 2;
		this.taxaDeMutacao = 0.3;
		this.taxaDeCruzamento = 0.7;
	}
	
	public VariaveisDeAmbiente(int numCidades, int maxGeracoes, int tamPopulacao, int tamTorneio, int countElitismo, double taxaDeMutacao,
			double taxaDeCruzamento) {
		super();
		this.numCidades = numCidades;
		this.maxGeracoes = maxGeracoes;
		this.tamPopulacao = tamPopulacao;
		this.tamTorneio = tamTorneio;
		this.taxaDeMutacao = taxaDeMutacao;
		this.taxaDeCruzamento = taxaDeCruzamento;
		this.countElitismo = countElitismo;
	}

	public VariaveisDeAmbiente(int numCidades, int maxGeracoes, int tamPopulacao, int tamTorneio, double taxaDeMutacao,
			double taxaDeCruzamento) {
		super();
		this.numCidades = numCidades;
		this.maxGeracoes = maxGeracoes;
		this.tamPopulacao = tamPopulacao;
		this.tamTorneio = tamTorneio;
		this.taxaDeMutacao = taxaDeMutacao;
		this.taxaDeCruzamento = taxaDeCruzamento;
	}

	public void setNumCidades(int numCidades) {
		this.numCidades = numCidades;
	}

	public void setMaxGeracoes(int maxGeracoes) {
		this.maxGeracoes = maxGeracoes;
	}

	public void setTamPopulacao(int tamPopulacao) {
		this.tamPopulacao = tamPopulacao;
	}

	public void setTamTorneio(int tamTorneio) {
		this.tamTorneio = tamTorneio;
	}
	
	public int getCountElitismo() {
		return countElitismo;
	}

	public void setCountElitismo(int countElitismo) {
		this.countElitismo = countElitismo;
	}

	public void setTaxaDeMutacao(double taxaDeMutacao) {
		this.taxaDeMutacao = taxaDeMutacao;
	}

	public void setTaxaDeCruzamento(double taxaDeCruzamento) {
		this.taxaDeCruzamento = taxaDeCruzamento;
	}

	public int getNumCidades() {
		return numCidades;
	}
	
	public int getMaxGeracoes() {
		return maxGeracoes;
	}
	public double getTamTorneio() {
		return tamTorneio;
	}
	public int getTamPopulacao() {
		return tamPopulacao;
	}
	public double getTaxaDeMutacao() {
		return taxaDeMutacao;
	}
	public double getTaxaDeCruzamento() {
		return taxaDeCruzamento;
	}
	
	//1. Inicializa a População
	/*
	 * O Algoritmo genético inicializa a população de candidatos de forma aleatória.
	 */
	
	//2. Avalia a Populacao
	/*
	 * Em seguida, a população é avaliada atribuindo um valor de aptidão para cada indivíduo na população.
	 * Nesta fase, frequentemente deseja anotar a solução mais adequada atual e a média aptidão da população.
	 */
	
	//3. Finalizado? Se sim exibe os resultados. Se não continua...
	/*
	 * Após a avaliação, o algoritmo decide se deve encerrar a pesquisa dependendo das condições de encerramento
	 * definidas.
	 * Normalmente, isso acontecerá porque o algoritmo atingiu um valor fixo número de gerações ou uma solução adequada foi encontrada
	 */
	
	//4. Realiza a seleção
	/*
	 * Se a condição de rescisão não for atendida, a população vai por meio de uma etapa de seleção na qual indivíduos da população
	 * são selecionados com base em sua pontuação de aptidão - quanto maior a aptidão, maior a chance de um indivíduo ser selecionado. 
	 */
	
	//5. Realiza o cruzamento
	//6. Realiza a mutação
	/*
	 * A próxima etapa é aplicar crossover e mutação ao indivíduos.
	 * Este estágio é onde novos indivíduos são criados para a próxima geração.
	 */
	
	//7. Retorna para o tópico 2 para avaliar a nova população.
	/*
	 * Neste ponto, a nova população volta para a avaliação etapa e o processo começa novamente.
	 * Chamamos cada ciclo deste loop de geração.
	 */
}
