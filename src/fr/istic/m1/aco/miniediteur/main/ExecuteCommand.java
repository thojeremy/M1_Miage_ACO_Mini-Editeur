package fr.istic.m1.aco.miniediteur.main;

import fr.istic.m1.aco.miniediteur.command.Broker;
import fr.istic.m1.aco.miniediteur.command.Order;

public class ExecuteCommand {
	private static Broker broker = new Broker();

	/**
	 * Permet d'ajouter un ordre au Broker
	 * 
	 * @param o		L'ordre à ajouter
	 * 
	 * @see fr.istic.m1.aco.miniediteur.command.Broker#takeOrder(Order)
	 */
	public static void addOrder(Order o){
		broker.takeOrder(o);
	}
	
	public static void executeOrder(){
		broker.placeOrders();
	}
}
