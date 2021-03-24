public class Apartamento {

	// Categorias - Diária - Capacidade
	//
	// 0 - Standard - 268.00 - 2
	// 1 - Apartamento Vista Bosque - 315.00 - 3
	// 2 - Apartamento Vista Vale - 353.00 - 3
	// 3 - Suíte - 498.00 - 2
	private List<categoria> categoria;
	private List<numero> numero;
	private List<valor> valor;
	private List<capacidade> capacidade;
	private List<tipo> tipo;
	
	public Apartamento(int num, int cat) {
		this.numero = new ArrayList<numero>();
		this.categoria = new ArrayList<categoria>();
		
		switch(cat) {
		case 0:
			valor = 268.0;
			capacidade = 2;
			tipo = "Standard";
			break;
		case 1:
			valor = 315.0;
			capacidade = 4;
			tipo = "Apartamento Vista Bosque";
			break;
		case 2:
			valor = 353.0;
			capacidade = 4;
			tipo = "Apartamento Vista Vale";
			break;
		case 3:
			valor = 498.0;
			capacidade = 2;
			tipo = "Suíte";
			break;
		}
	}

	public boolean estaOcupado() {
		return true;
	}
	
	public void checkIn() {
		
	}

	public List getCategoria() {
		return this.categoria;
	}

	public List getNumero() {
		return this.numero;
	}

	public List getValor() {
		return this.valor;
	}

	public List getCapacidade() {
		return this.capacidade;
	}

	public List getTipo() {
		return this.tipo;
	}
	
	
}
