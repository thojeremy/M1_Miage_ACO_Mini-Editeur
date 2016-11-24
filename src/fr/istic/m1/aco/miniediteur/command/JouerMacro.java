package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class JouerMacro implements Order{
	private MEImpl moteur;
	
	public JouerMacro(MEImpl moteur){
		this.moteur = moteur;
	}
	
	@Override
	public void execute() {
		moteur.jouerMacro();
	}

}
