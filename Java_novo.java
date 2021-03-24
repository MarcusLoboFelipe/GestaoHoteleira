package lista1.q01;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		NumeroComplexo z, w;
		
		System.out.print("\nDigite a parte real e imaginária do primeiro número complexo: ");
		z = new NumeroComplexo(scanner.nextDouble(), scanner.nextDouble()); 
		System.out.print("\nDigite a parte real e imaginária do segundo número complexo: ");
		w = new NumeroComplexo(scanner.nextDouble(), scanner.nextDouble()); 
		
		System.out.printf("\n%s + %s = %s", z, w, z.soma(w));
		System.out.printf("\n%s x %s = %s", z, w, z.produto(w));
		System.out.printf("\nConjugado de %s = %s", z, z.conjugado());
		System.out.printf("\nConjugado de %s = %s", w, w.conjugado());
				
		scanner.close();

	}

}
