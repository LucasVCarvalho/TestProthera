package com.lvcarvalho.teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lvcarvalho.teste.model.Funcionario;

public class Main {

	public static void main(String[] args) {
		Actions();
	}

	
	public static void Actions(){
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();	  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat ("#,##0.00");
		//3.1
		Funcionario f1 = new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.44"), "Operador");
		Funcionario f2 = new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2284.38"), "Operador");
		Funcionario f3 = new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter), new BigDecimal("9836.14"), "Coordenador");
		Funcionario f4 = new Funcionario("Miguel", LocalDate.parse("14/10/1998", formatter), new BigDecimal("19119.88"), "Diretor");
		Funcionario f5 = new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.68"), "Recepcionista");
		Funcionario f6 = new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter),new BigDecimal("1582.72"), "Operador");
		Funcionario f7 = new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador");
		Funcionario f8 = new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente");
		Funcionario f9 = new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista");
		Funcionario f10 = new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente");
		
		funcionarios.addAll(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10));
		
		//3.2
		for (int i = 0; i < funcionarios.size(); i++) {
			if(funcionarios.get(i).getNome().equals("João")) {
				funcionarios.remove(i);
			}
		}
		
		//3.3
		System.out.println("3.3");
		for (int i = 0; i < funcionarios.size(); i++) {
			System.out.println(funcionarios.get(i).getNome() + " " + funcionarios.get(i).getDataNascimento().format(formatter) + " " +  df.format(funcionarios.get(i).getSalario()) + " " + funcionarios.get(i).getFuncao());
		}
		
		//3.4
		for (int i = 0; i < funcionarios.size(); i++) {
			BigDecimal aumento = funcionarios.get(i).getSalario().multiply(new BigDecimal("0.1"));
			funcionarios.get(i).setSalario(funcionarios.get(i).getSalario().add(aumento));
		}
		
		//3.5
		Map<String, List<String>> funcoesMap = new HashMap<String, List<String>>();
		for (Funcionario f : funcionarios) {
			List<String> funcoesSemelhantes = funcoesMap.get(f.getFuncao());
			if(funcoesSemelhantes == null)
				funcoesMap.put(f.getFuncao(), funcoesSemelhantes = new ArrayList<String>());
			funcoesSemelhantes.add(f.getNome());
		}		
		//3.6
		System.out.println("\n3.6");
		System.out.println(funcoesMap.values());
		
		//3.8
		System.out.println("\n3.8");
		for (int i = 0; i < funcionarios.size(); i++) {
			if(funcionarios.get(i).getDataNascimento().getMonthValue() == 10 || funcionarios.get(i).getDataNascimento().getMonthValue() == 12)
				System.out.println(funcionarios.get(i).getNome() + " faz aniversário no mês " + funcionarios.get(i).getDataNascimento().getMonthValue());
		}
		
		//3.9
		System.out.println("\n3.9");
		String nomeFuncionarioMaisVelho = null;
		int idadeMaisVelho = 0;
		for (int i = 0; i < funcionarios.size(); i++) {
			LocalDate currentDate = LocalDate.now();
			int idade = Period.between(funcionarios.get(i).getDataNascimento(), currentDate).getYears();
			if(idade > idadeMaisVelho) {
				idadeMaisVelho = idade;
				nomeFuncionarioMaisVelho = funcionarios.get(i).getNome();
			}			
		}
		System.out.println("O funcionário mais velho é " + nomeFuncionarioMaisVelho + " tendo " + idadeMaisVelho + " anos de idade");
	
		//3.10
		System.out.println("\n3.10");
		List<String> nomeFuncionarios = new ArrayList<String>();
		for (int i = 0; i < funcionarios.size(); i++) {
			nomeFuncionarios.add(funcionarios.get(i).getNome().toString());
		}
		nomeFuncionarios.sort(Comparator.naturalOrder());
		for (int j = 0; j < funcionarios.size(); j++) {
			System.out.println(nomeFuncionarios.get(j));
		}
		
		//3.11
		System.out.println("\n3.11");
		BigDecimal totalSalarios = new BigDecimal("0.0");
		for (int i = 0; i < funcionarios.size(); i++) {
			totalSalarios = totalSalarios.add(funcionarios.get(i).getSalario());
		}
		System.out.println("O total dos salários é: " + df.format(totalSalarios));
	
		//3.12
		System.out.println("\n3.12");
		for (int i = 0; i < funcionarios.size(); i++) {
			BigDecimal salarioMinimo = new BigDecimal("1212.00");
			BigDecimal qtsSalariosMinimos = funcionarios.get(i).getSalario().divide(salarioMinimo, RoundingMode.HALF_UP);
			System.out.println(funcionarios.get(i).getNome() + " recebe o total de " + df.format(qtsSalariosMinimos) + " salários mínimos");
		}
	}
}
