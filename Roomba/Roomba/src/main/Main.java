package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import world.Room;
import world.RoombaGUI;
import world.RoombaSimulator;

public class Main {
	
	public static void main(String[] args) {
		
		Room r = new Room(400, 300, 10);
		MyRoomba robo = new MyRoomba(250, 250, 10);
		
		RoombaSimulator sim = new RoombaSimulator(robo, r);
		
		RoombaGUI gui = new RoombaGUI(sim);
		gui.setSize(new Dimension(1200,800));
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sim.setGui(gui);
		sim.simulate();
	}
}