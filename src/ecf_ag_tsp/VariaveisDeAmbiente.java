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
	
	//1. Inicializa a Popula��o
	/*
	 * O Algoritmo gen�tico inicializa a popula��o de candidatos de forma aleat�ria.
	 */
	
	//2. Avalia a Populacao
	/*
	 * Em seguida, a popula��o � avaliada atribuindo um valor de aptid�o para cada indiv�duo na popula��o.
	 * Nesta fase, frequentemente deseja anotar a solu��o mais adequada atual e a m�dia aptid�o da popula��o.
	 */
	
	//3. Finalizado? Se sim exibe os resultados. Se n�o continua...
	/*
	 * Ap�s a avalia��o, o algoritmo decide se deve encerrar a pesquisa dependendo das condi��es de encerramento
	 * definidas.
	 * Normalmente, isso acontecer� porque o algoritmo atingiu um valor fixo n�mero de gera��es ou uma solu��o adequada foi encontrada
	 */
	
	//4. Realiza a sele��o
	/*
	 * Se a condi��o de rescis�o n�o for atendida, a popula��o vai por meio de uma etapa de sele��o na qual indiv�duos da popula��o
	 * s�o selecionados com base em sua pontua��o de aptid�o - quanto maior a aptid�o, maior a chance de um indiv�duo ser selecionado. 
	 */
	
	//5. Realiza o cruzamento
	//6. Realiza a muta��o
	/*
	 * A pr�xima etapa � aplicar crossover e muta��o ao indiv�duos.
	 * Este est�gio � onde novos indiv�duos s�o criados para a pr�xima gera��o.
	 */
	
	//7. Retorna para o t�pico 2 para avaliar a nova popula��o.
	/*
	 * Neste ponto, a nova popula��o volta para a avalia��o etapa e o processo come�a novamente.
	 * Chamamos cada ciclo deste loop de gera��o.
	 */
}
