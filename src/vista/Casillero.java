package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controlador.DisabledTextColor;
import java.awt.FlowLayout;

public class Casillero extends JPanel {

	protected JButton casillas[][];
	private int tamano = 20;

	private Color colorTexto = new Color(70, 70, 70);
	private final int MAXR = 112;
	private final int MAXG = 159;
	private final int MAXB = 177;
	private final int MINR = 92;
	private final int MING = 139;
	private final int MINB = 157;
	private final Color colorBandera = new Color(242, 212, 102);

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
		this.casillas[i][j].setForeground(colorTexto);
		this.casillas[i][j].setBackground(obtenerBackgroundBoton(this.MAXR, this.MINR, this.MAXG, this.MING, this.MAXB, this.MINB));
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
	
	/**
	 * Genera un numero aleatorio entre un maximo y un minimo
	 * 
	 * @param max
	 *            Valor maximo
	 * @param min
	 *            Valor minimo
	 * @return Retorna un valor entero
	 */
	private int generarColor(int max, int min) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	/**
	 * Genera un color
	 * 
	 * @param MAXR
	 *            Valor maximo de Red
	 * @param MINR
	 *            Valor minimo de Red
	 * @param MAXG
	 *            Valor maximo de Green
	 * @param MING
	 *            Valor minimo de Green
	 * @param MAXB
	 *            Valor maximo de Blue
	 * @param MINB
	 *            Valor minimo de Blue
	 * @return Retorna una instancia de la clase Color con unas propiedades RGB
	 */
	public Color obtenerBackgroundBoton(int MAXR, int MINR, int MAXG, int MING, int MAXB, int MINB) {
		return new Color(generarColor(MAXR, MINR), generarColor(MAXG, MING), generarColor(MAXB, MINB));
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

}
