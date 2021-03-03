import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Hotel {

	Scanner leitor;
	Apartamento[] apartamentos;
	Cliente[] clientes;
	Data hoje;
	LinkedList<CheckIn> checkIns;
	LinkedList<CheckIn> checkOuts;
	
	public Hotel() {
		// Cria vetor de apartamentos e inicializar cada um deles 
		apartamentos = new Apartamento[10];		
		apartamentos[0] = new Apartamento(1, 0);
		apartamentos[1] = new Apartamento(2, 0);
		apartamentos[2] = new Apartamento(3, 1);
		apartamentos[3] = new Apartamento(4, 1);
		apartamentos[4] = new Apartamento(5, 1);
		apartamentos[5] = new Apartamento(6, 2);
		apartamentos[6] = new Apartamento(7, 2);
		apartamentos[7] = new Apartamento(8, 2);
		apartamentos[8] = new Apartamento(9, 3);
		apartamentos[9] = new Apartamento(10, 3);
		
		// Cria vetor de clientes e inicializar com cada um deles
		clientes = new Cliente[5];
		clientes[0] = new Cliente(101, "Reginaldo Farias", "(51) 9618-6617");
		clientes[1] = new Cliente(102, "Samanta Souza", "(21) 8153-4599");
		clientes[2] = new Cliente(103, "Augusto Santos", "(81) 9209-7644");
		clientes[3] = new Cliente(104, "Cláudio Silva", "(48) 9976-4831");
		clientes[4] = new Cliente(105, "Mariano Santana", "(11) 9452-3456");
		
		// Cria leitor de input
		leitor = new Scanner(System.in);
		
		// Inicializa a variavel dataAtual
		Date today = new Date();
		hoje = new Data(today.getDate(), today.getMonth()+1, today.getYear()+1900);
		
		// Cria a lista de check-ins existentes
		checkIns = new LinkedList<CheckIn>();
		
		// Cria a lista de check-outs existentes
		checkOuts = new LinkedList<CheckIn>();
		
		// Cadastra os dois check-ins existentes
		checkIns.add(new CheckIn(104, 1, hoje,
				new Data(hoje.getDia()+2, hoje.getMes(), hoje.getAno()), 3));
		
		checkIns.add(new CheckIn(101, 4, new Data(hoje.getDia()-1, hoje.getMes(), hoje.getAno()),
				new Data(hoje.getDia()+3, hoje.getMes(), hoje.getAno()), 2));
	}

	public void checkIn(){
		CheckIn checkIn = new CheckIn();
		
		// Le entrada de informações do check-in
		
		System.out.print("========= Sistema de Check-in =========\n");
		
		System.out.print("Código do cliente: ");
		checkIn.setCodCliente(leitor.nextInt());
		
		System.out.print("Número do quarto: ");
		checkIn.setCodApartamento(leitor.nextInt());
		
		System.out.print("Data de entrada (dd mm aaaa): ");
		int dia = leitor.nextInt();
		int mes = leitor.nextInt();
		int ano = leitor.nextInt();
		
		checkIn.setEntrada(new Data(dia, mes, ano));
		
		System.out.print("Dada prevista de saída (dd mm aaaa): ");
		dia = leitor.nextInt();
		mes = leitor.nextInt();
		ano = leitor.nextInt();
		
		checkIn.setSaida(new Data(dia, mes, ano));
		
		System.out.print("Número de hóspedes: ");
		checkIn.setHospedes(leitor.nextInt());
		
		
		int condicao = 1;
		
		// Inicia verificação
		// Para cara checkIn existente
		for (CheckIn c : checkIns){
			System.out.println(c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno());
			System.out.println(c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
			
			// Verifica se há sobreposição (condição 1)
			if(CheckIn.estaSobreposto(checkIn, c)){
				condicao *= 2;
				break;
			} 
		}
		
		// Verifica se a permanencia é de pelo menos 1 dia (condição 2)
		if (Data.compData(checkIn.getEntrada(), checkIn.getSaida()) < 1) {
			condicao *= 3;
		}
		
		// Verifica se o numero de hospedes não excede a capacidade do quarto
		if (checkIn.getHospedes() > apartamentos[checkIn.getCodApartamento() - 1].getCapacidade() + 1) {
			condicao *= 5;
		}
		
		// Exibe erros de check-in
		if(condicao > 1) {
			System.out.println("Não é possível realizar check-in:");
			
			if(condicao % 2 == 0) {
				System.out.println("Apartamento já está ocupado...");
			}
			
			if(condicao % 3 == 0) {
				System.out.println("Permanência é menor que 1 dia...");
			}
			
			if(condicao % 5 == 0) {
				System.out.println("Quantidade de hóspedes supera a capacidade...");
			}
		// Caso contrário, adiciona check-in
		} else {
			checkIns.add(checkIn);
			System.out.println("Check-in realizado com sucesso!");
		}
	}

	public void checkOut(){
		int quarto;
		System.out.println("\n========= Sistema de Check-in =========");
		System.out.print("Número do quarto: ");
		quarto = leitor.nextInt();
		
		// Para cada check realizado
		for (CheckIn c : checkIns) {
			// Verifica se ele é o check-in que vamos finalizar
			if(c.getCodApartamento() == quarto && Data.compData(c.getEntrada(), hoje) >= 0 && Data.compData(hoje, c.getSaida()) >= 0) {			
				// Atualiza a data de saída como hoje
				c.setSaida(hoje);			
				
				// Realiza o check-out
				checkIns.remove(c);
				checkOuts.add(c);
				c.setFinalizado(true);
				
				System.out.println("Check-out realizado com sucesso!");
				
				// Procura o cliente no cadastro 
				for(int i = 0; i < clientes.length; i++) {
					if (clientes[i].getCodigo() == c.getCodCliente()) {
						int numApart = c.getCodApartamento();
						double diaria = apartamentos[numApart-1].getValor();
						
						if (c.getHospedes() > apartamentos[numApart-1].getCapacidade()) {
							diaria *= 1.3;
						}
						
						// Exibe relatório do cliente
						System.out.println("\n========= Relatório de Estadia =========");
						System.out.println("Nome: " + clientes[i].getNome() + " | Código do cliente: " + clientes[i].getCodigo());
						System.out.print("Número do quarto: " + numApart + " | Tipo do quarto: " + apartamentos[numApart-1].getTipo() + " | Valor da diária: R$ " + String.format("%,.2f", diaria));
						System.out.print("Data da entrada: " + c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno() + " | Data de Saída: " + c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
						System.out.println("Número de hospedes: " + c.getHospedes() + " | Número de diarias: " + Data.compData(c.getEntrada(), c.getSaida()));
						System.out.println("Valor total da estadia: " + String.format("%,.2f", diaria*Data.compData(c.getEntrada(), c.getSaida())));
					}
				}
				
			}
		}
	}
	
	public void consultaCliente() {
		System.out.println("\n========= Consulta por cliente =========");
		System.out.print("Código do cliente: ");
		int cod = leitor.nextInt();
		
		// Procura cliente
		for (int i = 0; i < clientes.length; i++) {
			if(clientes[i].getCodigo() == cod){
				// Cliente encontrado
				System.out.println("Nome: " + clientes[i].getNome() + " | Código: " + cod + " | Telefone: " + clientes[i].getTelefone());
				
				System.out.println("Reservas: ");				
				boolean encontrou = false;
				
				for (CheckIn c : checkIns) {
					if (c.getCodCliente() == cod) {
						System.out.print("\tQuarto número: " + c.getCodApartamento());
						System.out.print(" | Entrada: " + c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno());
						System.out.println(" | Saída: " + c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
						encontrou = true;
					}
				}
				
				if (!encontrou) {
					System.out.println("\t-- Sem reservas --");
				}
				
				System.out.println("Histórico: ");
				encontrou = false;
				
				for (CheckIn c : checkOuts) {
					if (c.getCodCliente() == cod) {
						System.out.print("\tQuarto número: " + c.getCodApartamento());
						System.out.print(" | Entrada: " + c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno());
						System.out.println(" | Saída: " + c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
						encontrou = true;
					}
				}
				
				if (!encontrou) {
					System.out.println("\t-- Sem histórico --");
				}
			}
		}
		
	}
	
	public void consultaQuarto() {
		System.out.println("\n========= Consulta por quarto =========");
		System.out.print("Número do quarto: ");
		int num = leitor.nextInt();

		System.out.println("Tipo do quarto: " + apartamentos[num-1].getTipo() + " | Valor da diária: R$ " + String.format("%,.2f", apartamentos[num-1].getValor()) + " | Capacidade: " + apartamentos[num-1].getCapacidade());
		
		// Determina se está ocupado
		boolean ocupado = false;
		
		// Procura nas reservas se o quarto está ocupado
		for (CheckIn c : checkIns) {
			if(c.getCodApartamento() == num) {
				if(Data.compData(c.getEntrada(), hoje) > 0 && Data.compData(hoje, c.getSaida()) > 0) {
					ocupado = true;
					break;
				}
			}
		}
		
		if(ocupado) {
			System.out.println("Ocupado: Sim");
		} else {
			System.out.println("Ocupado: Não");
		}
		
		System.out.println("Reservas: ");				
		boolean encontrou = false;
		
		for (CheckIn c : checkIns) {
			if (c.getCodApartamento() == num) {
				System.out.print("\tCódigo do cliente: " + c.getCodCliente());
				System.out.print(" | Entrada: " + c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno());
				System.out.println(" | Saída: " + c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
				encontrou = true;
			}
		}
		
		if (!encontrou) {
			System.out.println("\t-- Sem reservas --");
		}
		
		System.out.println("Histórico: ");
		encontrou = false;
		
		for (CheckIn c : checkOuts) {
			if (c.getCodApartamento() == num) {
				System.out.print("\tCódigo do cliente: " + c.getCodCliente());
				System.out.print(" | Entrada: " + c.getEntrada().getDia() + "/" + c.getEntrada().getMes() + "/" + c.getEntrada().getAno());
				System.out.println(" | Saída: " + c.getSaida().getDia() + "/" + c.getSaida().getMes() + "/" + c.getSaida().getAno());
				encontrou = true;
			}
		}
		
		if (!encontrou) {
			System.out.println("\t-- Sem histórico --");
		}
	}
	
	public void relatorio() {
		System.out.println("\n========= Relatorio do hotel =========");
		System.out.println("Total de quartos: " + apartamentos.length);
		
		int ocupados = 0;
		
		for (CheckIn c : checkIns) {
			if(Data.compData(c.getEntrada(), hoje) >= 0 && Data.compData(hoje, c.getSaida()) >= 0) {
				ocupados++;
			}
		}

		System.out.println("Quartos ocupados: " + ocupados);
		
		double valorRecebido = 0.0;
		double diaria;
		
		for (CheckIn c : checkOuts) {
			if(c.getSaida().getMes() == hoje.getMes()) {
				diaria = apartamentos[c.getCodApartamento()-1].getValor();
				if (c.getHospedes() > apartamentos[c.getCodApartamento()-1].getCapacidade()) {
					diaria *= 1.3;
				}
				
				valorRecebido += diaria;
			}
		}
		
		System.out.println("Valor total recebido no mês: R$ " + String.format("%,.2f", valorRecebido));
	}
	
	public void encerra() {
		leitor.close();
	}
}
