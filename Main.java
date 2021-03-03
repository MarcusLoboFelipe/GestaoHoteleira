import java.util.Scanner;

public class Main {
	
	Scanner leitor;
	Hotel hotel;
	
	private void programa() {
		// Cria leitor de input
		leitor = new Scanner(System.in);
		
		// Cria e inicia o hotel
		hotel = new Hotel();
		
		// Variavel de opcao
		int opcao;
		boolean continua = true;
		
		while(continua) {
			System.out.println("========= Sistema Hotel v1.0 =========");
			System.out.println("(1) Check-in");
			System.out.println("(2) Check-out");
			System.out.println("(3) Consulta por cliente");
			System.out.println("(4) Consulta por quarto");
			System.out.println("(5) Relatório do hotel");
			System.out.println("(0) Encerra programa");
			System.out.print(">>> ");
			
			opcao = leitor.nextInt();
			
			switch(opcao) {
			case 1:
				hotel.checkIn();
				System.out.print("\n");
				break;
			case 2:
				hotel.checkOut();
				System.out.print("\n");
				break;
			case 3:
				hotel.consultaCliente();
				System.out.print("\n");
				break;
			case 4:
				hotel.consultaQuarto();
				System.out.print("\n");
				break;
			case 5:
				hotel.relatorio();
				System.out.print("\n");
				break;
			case 0:
				continua = false;
				break;
			}
		}
		
		System.out.println("Encerrando...");
		
		hotel.encerra();
		leitor.close();
	}
	
	public static void main(String[] args) {
		new Main().programa();		
	}

}

