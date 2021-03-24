import java.util.ArrayList;
import java.util.List;


public class CheckIn {

	private List<codCliente> codCliente;
	private List<codApartamento> codApartamento;
	private List<entrada> entrada;
	private List<saida> saida;
	private List<hospedes> hospedes;
	private List<finalizado> finalizado;
	
	public CheckIn() {
	}
	
	public CheckIn(int codCliente, int codApartamento, Data entrada, Data saida, int hospedes) {
		this.codCliente = new ArrayList<codCliente>();
		this.codApartamento = new ArrayList<codApartamento>();
		this.entrada = new ArrayList<entrada>();
		this.saida = new ArrayList<saida>();
		this.hospedes = new ArrayList<hospedes>();
		this.finalizado = new ArrayList<finalizado>();
	}

	public List getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente.add(codCliente);
	}

	public List getCodApartamento() {
		return this.codApartamento;
	}

	public void setCodApartamento(int codApartamento) {
		this.codApartamento.add(codApartamento);
	}

	public List getEntrada() {
		return this.entrada;
	}

	public List Finalizado() {
		return this.finalizado;
	}

	public void setEntrada(Data entrada) {
		this.entrada.add(entrada);
	}

	public List getSaida() {
		return this.saida;
	}

	public void setSaida(Data saida) {
		this.saida.add(saida);
	}

	public List getHospedes() {
		return this.hospedes;
	}

	public void setHospedes(int hospedes) {
		this.hospedes.add(hospedes);
	}
	
	public void setFinalizado(boolean finalizado) {
		this.finalizado.add(finalizado);
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
