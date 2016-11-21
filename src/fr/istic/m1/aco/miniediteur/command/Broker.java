package fr.istic.m1.aco.miniediteur.command;

import java.util.ArrayList;
import java.util.List;

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
			order.execute();
		}
		orderList.clear();
	}
}
