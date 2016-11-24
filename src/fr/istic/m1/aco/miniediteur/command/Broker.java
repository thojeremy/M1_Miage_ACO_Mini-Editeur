package fr.istic.m1.aco.miniediteur.command;

import java.util.ArrayList;
import java.util.List;

import fr.istic.m1.aco.miniediteur.main.Fenetre;
import fr.istic.m1.aco.miniediteur.main.Main;

public class Broker {
	private List<Order> orderList;
	
	public Broker(){
		orderList = new ArrayList<Order>();
	}

	public void takeOrder(Order order){
		orderList.add(order);		
	}

	public void placeOrders(){
		for (Order order : orderList) {
			Fenetre.zoneTexte.setText(Main.moteur.getBuffer());
			Fenetre.zoneTexte.setCaretPosition(Main.moteur.curseur);
			
			order.execute();
		}
		orderList.clear();
	}
}
