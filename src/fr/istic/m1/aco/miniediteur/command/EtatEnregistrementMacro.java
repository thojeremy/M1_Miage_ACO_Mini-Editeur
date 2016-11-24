package fr.istic.m1.aco.miniediteur.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MEImpl;

public class EtatEnregistrementMacro implements Order{
	private MEImpl moteur;
	
	public EtatEnregistrementMacro(MEImpl moteur){
		this.moteur = moteur;
	}
	
	@Override
	public void execute() {
		moteur.changerEtatEnregistrementMacro();
	}

}
