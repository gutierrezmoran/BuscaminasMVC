package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class ActionCasilla implements MouseListener {

	private Bridge bridge;
	private int[] coordenadas;
	private int x;
	private int y;

	public ActionCasilla(Bridge bridge) {
		super();
		this.bridge = bridge;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getModifiers() & 16) != 0 && bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled()) { // Boton
																												// izquierdo
			if (e.getClickCount() > 1) { // Doble click
				this.bridge.jugarSeguro(coordenadas);
			} else {
				this.bridge.jugar(coordenadas);
			}
		}

		if ((e.getModifiers() & 4) != 0
				&& bridge.getCasillero().getCasillas()[coordenadas[0]][coordenadas[1]].isEnabled()) { // Boton derecho
			this.bridge.toogleBandera(this.coordenadas[0], this.coordenadas[1]);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		coordenadas = convertirCoordenadasAEntero(((JButton) e.getSource()).getName());

		this.x = coordenadas[0];
		this.y = coordenadas[1];

		if (bridge.getControl().getTablero()[this.x][this.y].isVelada()
				&& bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled()) {
			bridge.getCasillero().getCasillas()[this.x][this.y]
					.setBackground(bridge.getCasillero().getColorCasillaSeleccionada());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (bridge.getControl().getTablero()[this.x][this.y].isVelada()
				&& bridge.getCasillero().getCasillas()[this.x][this.y].isEnabled()) {
			bridge.getCasillero().getCasillas()[this.x][this.y].setBackground(bridge.getCasillero().getColorCasilla());
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

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