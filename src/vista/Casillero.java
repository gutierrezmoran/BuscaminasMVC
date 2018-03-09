package vista;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.DisabledTextColor;

public class Casillero extends JPanel {

	protected JButton casillas[][];
	private int tamano = 20;
	private Color colorTexto = new Color(70, 70, 70);
	private final Color colorBandera = new Color(236, 215, 133);
	private final Color colorCasilla = new Color(169, 169, 169);
	private final Color colorCasillaSeleccionada = new Color(227, 197, 143);
	private final Color colorMinaTocada = new Color(171, 72, 72);
	private final Color colorMinasSalvadas = new Color(72, 171, 141);
	private final Color colorCasillaDesvelada = new Color(204, 204, 204);
	private final Color colorUnaMina = new Color(50, 81, 142);
	private final Color colorDosMinas = new Color(50, 142, 103);
	private final Color colorMasMinas = new Color(142, 50, 50);

	/**
	 * Create the panel.
	 */
	public Casillero() {
		iniciarCasillero();
	}

	/**
	 * Establece la longitud de la matriz de casillas. Agrega botones a la matriz.
	 * Asigna propiedades visuales. Agrega el boton al JPanel casillero.
	 */
	public void iniciarCasillero() {
		this.casillas = new JButton[this.tamano][this.tamano];

		/*
		 * Crea la casilla Agrega la casilla al panel Agrega la casilla a la matriz
		 */
		for (int i = 0; i < this.casillas.length; i++) {
			for (int j = 0; j < this.casillas.length; j++) {
				this.casillas[i][j] = new JButton();
				asignarPropiedadesCasilla(i, j);
				this.add(this.casillas[i][j]);
			}
		}
	}

	/**
	 * Agrega propiedades a una casilla
	 * 
	 * @param i
	 * @param j
	 */
	public void asignarPropiedadesCasilla(int i, int j) {
		this.casillas[i][j].setEnabled(true);
		this.casillas[i][j].setText("");
		this.casillas[i][j].setName(String.valueOf(i) + " " + String.valueOf(j));
		this.casillas[i][j].setFocusPainted(false);
		this.casillas[i][j].setBackground(colorCasilla);
		this.casillas[i][j].setBorder(new EmptyBorder(1, 1, 1, 1));
	}
	
	/**
	 * Deshabilita las casillas del casillero
	 */
	public void deshabilitarCasillas() {
		for (int i = 0; i < this.casillas.length; i++) {
			for (int j = 0; j < this.casillas.length; j++) {
				this.casillas[i][j].setUI(new DisabledTextColor(this.colorTexto));
				this.casillas[i][j].setEnabled(false);
			}
		}
	}

	public JButton[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(JButton[][] casillas) {
		this.casillas = casillas;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public Color getColorTexto() {
		return colorTexto;
	}

	public void setColorTexto(Color colorTexto) {
		this.colorTexto = colorTexto;
	}
	
	public Color getColorBandera() {
		return colorBandera;
	}

	public Color getColorCasilla() {
		return colorCasilla;
	}

	public Color getColorCasillaSeleccionada() {
		return colorCasillaSeleccionada;
	}

	public Color getColorMinaTocada() {
		return colorMinaTocada;
	}

	public Color getColorMinasSalvadas() {
		return colorMinasSalvadas;
	}

	public Color getColorCasillaDesvelada() {
		return colorCasillaDesvelada;
	}

	public Color getColorUnaMina() {
		return colorUnaMina;
	}

	public Color getColorDosMinas() {
		return colorDosMinas;
	}

	public Color getColorMasMinas() {
		return colorMasMinas;
	}

}
