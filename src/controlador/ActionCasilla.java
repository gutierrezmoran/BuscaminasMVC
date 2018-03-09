package controlador;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import modelo.Tablero;

public class ActionCasilla implements MouseListener {

	private Tablero control;
	private Bridge bridge;
	private int[] coordenadas;
	private int x;
	private int y;

	public ActionCasilla(Tablero control, Bridge bridge) {
		super();
		this.control = control;
		this.bridge = bridge;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getModifiers() & 16) != 0 && bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled()) { // Boton izquierdo
			if(e.getClickCount() > 1) { // Doble click
				this.bridge.jugarSeguro(coordenadas);
			} else {
				this.bridge.jugar(coordenadas);
			}
		}

		if ((e.getModifiers() & 4) != 0 && bridge.getCasillero().getCasillas()[coordenadas[0]][coordenadas[1]].isEnabled()) { // Boton derecho
			this.bridge.toogleBandera(coordenadas);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		coordenadas = convertirCoordenadasAEntero(((JButton) e.getSource()).getName());
		
		this.x = coordenadas[0];
		this.y = coordenadas[1];

		if (bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled() && bridge.getCasillero().getCasillas()[this.x][this.y].getText() == ""
				&& bridge.getCasillero().getCasillas()[this.x][this.y].getBackground() != bridge.getCasillero().getColorBandera()) {
			bridge.getCasillero().getCasillas()[this.x][this.y].setBackground(bridge.getCasillero().getColorCasillaSeleccionada());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled() && bridge.getCasillero().getCasillas()[this.x][this.y].getText() == ""
				&& bridge.getCasillero().getCasillas()[this.x][this.y].getBackground() != bridge.getCasillero().getColorBandera()) {
			bridge.getCasillero().getCasillas()[this.x][this.y].setBackground(bridge.getCasillero().getColorCasilla());
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	/**
	 * Convierte un String con coordenadas en un vector de entero
	 * 
	 * @param nameCoordenadas
	 *            String con coordenadas separadas por espacio
	 * @return Retorna un vector de entero de 2 posiciones
	 */
	public int[] convertirCoordenadasAEntero(String nameCoordenadas) {
		int x = Integer.parseInt(nameCoordenadas.substring(0, nameCoordenadas.indexOf(" ")));
		int y = Integer.parseInt(nameCoordenadas.substring(nameCoordenadas.indexOf(" ") + 1));
		int[] coordenadas = { x, y };
		return coordenadas;
	}

}