package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionDificultad implements ActionListener {
	
	Bridge bridge;
	
	public ActionDificultad(Bridge bridge) {
		this.bridge = bridge;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int name = Integer.parseInt(((JButton) e.getSource()).getName());
		bridge.setDificultad(name);
	}
	
}
