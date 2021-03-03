public class Data {

	private int dia;
	private int mes;
	private int ano;

	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	// Retorna o número de dias passados desde 01/01/0001
	public int emDias() {
		int dias = dia;
		
		// Adiciona os dias dos meses passados neste ano
		dias += (mes-1)*30;
		
		// Ajusta a quantidade de dias no mês (soma 1, para cada mês de 31 dias
		// e subtrai 2 em fevereiro).
		switch(mes) {
			case 3:
				dias -= 1;
				break;
			case 2:
			case 6:
			case 7:
				dias += 1;
				break;
			case 8:
				dias += 2;
				break;
			case 9:
			case 10:
				dias += 3;
				break;
			case 11:
			case 12:
				dias += 4;
				break;
		}
		
		// Adiciona os dias dos anos passados:
		dias += (ano-1)*365;
		
		// Verifica os anos bissestos e soma um dia a mais
		for(int i = 1; i < ano; i++) {
			if (i % 4 == 0 && (i % 100 != 0 || i % 400 == 0)) {
				dias++;
			}
		}
		
		// Por fim, soma um dia se o ano atual for bissexto e já estivermos após 29/02
		if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
			if (mes > 2) {
				dias++;
			}
		}
		
		return dias; 
	}
	
	public static int compData(Data anterior, Data posterior) {
		return posterior.emDias() - anterior.emDias();
	}

}
