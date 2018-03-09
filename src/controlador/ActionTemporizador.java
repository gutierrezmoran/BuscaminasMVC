package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTemporizador implements ActionListener {
	
	private Bridge bridge;
	
	public ActionTemporizador(Bridge bridge) {
		this.bridge = bridge;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bridge.setTime();
	}

}
