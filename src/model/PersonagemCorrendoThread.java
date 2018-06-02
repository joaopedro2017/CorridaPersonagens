/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JohnPeter
 */
public class PersonagemCorrendoThread extends Thread {

	String nome;					// nome do personagem
	int distanciaCorrida = 0;		// distância já corrida pelo personagem
	int distanciaTotalCorrida;	// distância a ser corrida pelo personagem
	int passo = 0;					// passp do personagem em m
	int passos = 0;					// quantidades de passos dados na corrida
	static int colocacao = 0;		// colocação do personagem ao final da corrida
	final static int PULO_MAXIMO = 50; // passo máximo em m que um personagem pode dar
        static int tempo = 250;

	/** Construtor da classe. Parâmtros : Nome do personagem e Distância da Corrida
     * @param nome
     * @param distanciaTotalCorrida
     * @param distanciaCorrida
     * @param colocacao */
	public PersonagemCorrendoThread (String nome, int distanciaTotalCorrida, int distanciaCorrida, int colocacao) {
		/* chamando o construtor de Thread passando o nome do personagem como parâmetro */
		super(nome);
		this.distanciaTotalCorrida = distanciaTotalCorrida;
		this.nome = nome;
                this.distanciaCorrida = distanciaCorrida;
                PersonagemCorrendoThread.colocacao = colocacao;
	}
	/** Imprime o último passo do personagem e a distância percorrida */
	public void personagemImprimirSituacao () {
		System.out.println("O " + nome +  " andou " + passo + "m \t e já percorreu " +
							distanciaCorrida + "m");
	}
	/** Faz o personagem correr */
	public void personagemCorrendo() {
		passos++;
		passo = (int) (Math.random() * PULO_MAXIMO);
		distanciaCorrida += passo;
		if (distanciaCorrida > distanciaTotalCorrida) {
			distanciaCorrida = distanciaTotalCorrida;                        
		}                
                switch(nome){
                    case "Pantera":
                        Corrida.panteraY = distanciaCorrida;
                        Corrida.panteraP = String.valueOf(passos) + " passos";
                        break;
                    case "IronMan":
                        Corrida.ironManY = distanciaCorrida;
                        Corrida.ironManP = String.valueOf(passos) + " passos";
                        break;
                    case "Hulk":
                        Corrida.hulkY = distanciaCorrida;
                        Corrida.hulkP = String.valueOf(passos) + " passos";
                       break;
                    case "Capitao":
                        Corrida.capitaoY = distanciaCorrida;
                        Corrida.capitaoP = String.valueOf(passos) + " passos";
                        break;
                    case "Spider":
                        Corrida.spiderY = distanciaCorrida;
                        Corrida.spiderP = String.valueOf(passos) + " passos";
                        break;
                }
	}
	/** Representando o descanso do personagem */
	public synchronized void personagemDescansando () {
            try {
                /* Método que passa vez a outras threads */
                sleep(tempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(PersonagemCorrendoThread.class.getName()).log(Level.SEVERE, null, ex);
            }
		//yield();
	}
	/** Imprime a colocação do personagem ao final da corrida */
	public synchronized void colocacaoPersonagem () {
		colocacao++;
		System.out.println(nome + " foi o " +
                        colocacao + "º colocado com " + passos + " passos");
                
                switch(nome){
                    case "Pantera":
                        Corrida.panteraC = String.valueOf(colocacao) + "º";
                        break;
                    case "IronMan":
                        Corrida.ironManC = String.valueOf(colocacao) + "º";
                        break;
                    case "Hulk":
                        Corrida.hulkC = String.valueOf(colocacao) + "º";
                       break;
                    case "Capitao":
                        Corrida.capitaoC = String.valueOf(colocacao) + "º";
                        break;
                    case "Spider":
                        Corrida.spiderC = String.valueOf(colocacao) + "º";
                        break;
                }
	}
	/** Método run da thread Corrida de personagens */
        @Override
	public void run () {
		while (distanciaCorrida < distanciaTotalCorrida) {
			personagemCorrendo();
			personagemImprimirSituacao();
			personagemDescansando();
		}
		colocacaoPersonagem();               
	}
}