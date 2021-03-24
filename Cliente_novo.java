import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private List<codigo> codigo;
	private List<nome> nome;
	private List<telefone> telefone;
	
	public Cliente(int codigo, String nome, String telefone) {
		this.codigo = new ArrayList<codigo>();
		this.nome = new ArrayList<nome>();
		this.telefone = new ArrayList<telefone>();
	}

	public List getCodigo() {
		return this.codigo;
	}

	public List getNome() {
		return this.nome;
	}

	public List getTelefone() {
		return this.telefone;
	}

}
