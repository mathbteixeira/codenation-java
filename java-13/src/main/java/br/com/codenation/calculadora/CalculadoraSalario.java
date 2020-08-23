package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		if (salarioBase < 1039.00)
			return 0;
		return Math.round(calcularIrrf(calcularInss(salarioBase)));
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase <= 0.0)
			return 0.0;
		else if (salarioBase <= 1500.0)
			return  salarioBase * 0.92;
		else if (salarioBase <= 4000.0)
			return salarioBase * 0.91;
		else
			return salarioBase * 0.89;
	}

	private double calcularIrrf(double salarioBase) {
		if (salarioBase <= 0.0)
			return 0.0;
		else if (salarioBase <= 3000.0)
			return salarioBase;
		else if (salarioBase > 3000.0 && salarioBase <= 6000.0)
			return salarioBase * 0.925;
		else
			return salarioBase *  0.85;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/