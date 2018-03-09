package controlador;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ActionReiniciar implements MouseListener, MouseMotionListener {

	private Bridge bridge;
	private Color entered = new Color(0, 89, 77);
	private Color exited = new Color(0, 131, 113);
	private Color pressed = new Color(44, 100, 66);

	public ActionReiniciar(Bridge paraBuscaminas) {
		super();
		this.bridge = paraBuscaminas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		bridge.reiniciarPartida();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		bridge.getBtnReiniciar().setBackground(entered);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		bridge.getBtnReiniciar().setBackground(exited);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		bridge.getBtnReiniciar().setBackground(entered);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		bridge.getBtnReiniciar().setBackground(pressed);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}