public class CheckIn {

	private int codCliente;
	private int codApartamento;
	private Data entrada;
	private Data saida;
	private int hospedes;
	private boolean finalizado;
	
	public CheckIn() {
	}
	
	public CheckIn(int codCliente, int codApartamento, Data entrada, Data saida, int hospedes) {
		this.codCliente = codCliente;
		this.codApartamento = codApartamento;
		this.entrada = entrada;
		this.saida = saida;
		this.hospedes = hospedes;
		this.finalizado = false;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public int getCodApartamento() {
		return codApartamento;
	}

	public void setCodApartamento(int codApartamento) {
		this.codApartamento = codApartamento;
	}

	public Data getEntrada() {
		return entrada;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setEntrada(Data entrada) {
		this.entrada = entrada;
	}

	public Data getSaida() {
		return saida;
	}

	public void setSaida(Data saida) {
		this.saida = saida;
	}

	public int getHospedes() {
		return hospedes;
	}

	public void setHospedes(int hospedes) {
		this.hospedes = hospedes;
	}
	
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	// Verifica se dois check-ins se sobrepõem (mesmo quarto em um mesmo período)
	public static boolean estaSobreposto(CheckIn a, CheckIn b) {
		if (a.getCodApartamento() == b.getCodApartamento()) {
			if((Data.compData(a.getSaida(), b.getEntrada()) > 0 && Data.compData(a.getEntrada(), b.getSaida()) > 0) 
					|| (Data.compData(b.getSaida(), a.getEntrada()) > 0 && Data.compData(b.getEntrada(), a.getSaida()) > 0)) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
}
