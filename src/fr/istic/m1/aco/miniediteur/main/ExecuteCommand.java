package fr.istic.m1.aco.miniediteur.main;

import fr.istic.m1.aco.miniediteur.command.Broker;
import fr.istic.m1.aco.miniediteur.command.Order;

public class ExecuteCommand {
	private static Broker broker = new Broker();

	public static void addOrder(Order o){
		broker.takeOrder(o);
	}
	
	public static void executeOrder(){
		broker.placeOrders();

		System.out.println(Main.moteur.getBuffer() + " (" + Main.moteur.getCurseur() + ")");
	}
}
