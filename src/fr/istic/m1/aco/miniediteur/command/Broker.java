package fr.istic.m1.aco.miniediteur.command;

import java.util.ArrayList;
import java.util.List;

import fr.istic.m1.aco.miniediteur.main.Fenetre;
import fr.istic.m1.aco.miniediteur.main.Main;

public class Broker {
	private List<Order> orderList;
	
	/**
	 * Le constructeur du Broker
	 */
	public Broker(){
		orderList = new ArrayList<Order>();
	}

	/**
	 * Méthode permettant d'ajouter un ordre dans la liste du Broker
	 * 
	 * @param order		Un ordre à insérer
	 */
	public void takeOrder(Order order){
		orderList.add(order);		
	}

	/**
	 * Méthode permettant d'exécuter les ordres de la liste du Broker
	 */
	public void placeOrders(){
		for (Order order : orderList) {
			order.execute();
			
			Fenetre.zoneTexte.setText(Main.moteur.getBuffer());
			Fenetre.zoneTexte.setCaretPosition(Main.moteur.getCurseur());
		}
		orderList.clear();
	}
}
