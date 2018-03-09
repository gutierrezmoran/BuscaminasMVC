package controlador;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import modelo.Tablero;
import vista.BuscaminasUI;

public class Bridge extends BuscaminasUI {

	private Tablero control = new Tablero();
	private ActionCasilla listenerCasilla = new ActionCasilla(control, this);
	private ActionReiniciar listenerReiniciar = new ActionReiniciar(this);
	private ActionTemporizador listenerTemporizador = new ActionTemporizador(this);
	private ActionDificultad listenerDificultad = new ActionDificultad(this);
	private Timer timer = new Timer(1000, listenerTemporizador);
	private final int tiempoLimite = 9999;

	public Bridge() {
		super();
		setDificultad(20);
		setListeners();
		timer.setRepeats(true);
	}

	/**
	 * Comprueba si se ha superado el record de la partida
	 * 
	 * @return Retora TRUE en caso de que se haya superado el record o FALSE en caso
	 *         contrario
	 */
	private boolean comprobarRecord() {
		if (Integer.parseInt(txtTiempo.getText()) < control.getRecord()) {
			return true;
		}
		return false;
	}

	/**
	 * Establece un nuevo record
	 */
	private void establecerRecord() {
		control.setRecord(Integer.parseInt(txtTiempo.getText()));
	}

	/**
	 * Actualiza el componente grafico con el nuevo record
	 */
	private void mostrarRecord() {
		txtRecord.setText(String.valueOf(control.getRecord()));
		panelRecord.setVisible(true);
	}

	/**
	 * Oculta el componente grafico que representa el record
	 */
	private void ocultarRecord() {
		panelRecord.setVisible(false);
	}

	/**
	 * Establece el valor del componente grafico que representa el record a 0
	 */
	private void reiniciarRecord() {
		txtRecord.setText("0");
	}

	/**
	 * Establece las coordenadas del boton pulsado en el control. Realiza una jugada
	 * y en caso TRUE actualiza el casillero y la cantidad de banderas restantes. En
	 * caso contrario muestra las minas en el casillero y deshabilita todas las
	 * casillas.
	 * 
	 * @param coordenadas
	 *            Vector de coordenadas
	 */
	public void jugar(int[] coordenadas) {
		timer.start();
		control.setCoordenadas(coordenadas);
		if (control.realizarJugada()) {
			actualizarCasillero();
			toggleCasillasRestantes();
		} else {
			timer.stop();
			actualizarCasillero();
			toggleCasillasRestantes();
			mostrarMinas();
			getCasillero().deshabilitarCasillas();
			if (control.isFinalizado()) {
				comprobarRecord();
				establecerRecord();
				mostrarRecord();
			}
		}

	}

	/**
	 * Establece propiedades especificas para las casillas minadas.
	 */
	private void mostrarMinas() {
		for (int i = 0; i < casillero.getCasillas().length; i++) {
			for (int j = 0; j < casillero.getCasillas().length; j++) {
				if (control.getTablero()[i][j] == -1) {
					setPropiedadesMina(i, j);
				}
			}
		}
	}

	/**
	 * Establece propiedades a la casilla minada especificada por parametro
	 * 
	 * @param i
	 *            Entero que representa un valor de coordenada
	 * @param j
	 *            Entero que representa un valor de coordenada
	 */
	private void setPropiedadesMina(int i, int j) {
		casillero.getCasillas()[i][j].setFont(new Font("Arial", Font.BOLD, casillero.getCasillas()[i][j].getWidth()));
		if (control.isMinaTocada()) {
			this.casillero.getCasillas()[i][j].setBackground(getCasillero().getColorMinaTocada());
			this.casillero.getCasillas()[i][j].setBorder(new LineBorder(getCasillero().getColorMinaTocada(), 1, true));
		} else {
			this.casillero.getCasillas()[i][j].setBackground(getCasillero().getColorMinasSalvadas());
			this.casillero.getCasillas()[i][j]
					.setBorder(new LineBorder(getCasillero().getColorMinasSalvadas(), 1, true));
		}
	}

	/**
	 * Establece los listeners de los objetos
	 */
	private void setListeners() {
		btnReiniciar.addMouseListener(listenerReiniciar);
		btnFacil.addActionListener(listenerDificultad);
		btnMedio.addActionListener(listenerDificultad);
		btnDificil.addActionListener(listenerDificultad);
		for (int i = 0; i < this.casillero.getCasillas().length; i++) {
			for (int j = 0; j < this.casillero.getCasillas()[i].length; j++) {
				this.casillero.getCasillas()[i][j].addMouseListener(listenerCasilla);
			}
		}
	}

	/**
	 * Inicializa el tablero del control y establece minas. Esecifica el valor
	 * correspondiente de banderas. Agrega el listenerReiniciar al boton Reiniciar.
	 * Agrega el listenerCasilla a cada casilla.
	 */
	private void inicializarPartida() {
		reiniciarTiempo();
		control.inicializar();
		control.setMinas();
		toggleBanderasRestantes();
		toggleCasillasRestantes();
	}

	/**
	 * Actualiza el numero de casillas restantes por desvelar
	 */
	private void toggleCasillasRestantes() {
		txtCantidadCasillas.setText(String.valueOf(control.getCasillasRestantes()));
	}

	/**
	 * Actualiza todo el casillero deshabilitando las desveladas sin minas y dando
	 * valores a las desveladas con minas.
	 */
	public void actualizarCasillero() {

		int dimensionFuente = Math.round(casillero.getCasillas()[0][0].getWidth() / 1.3f);

		for (int i = 0; i < casillero.getCasillas().length; i++) {
			for (int j = 0; j < casillero.getCasillas().length; j++) {
				if (control.getTablero()[i][j] == -2) {
					casillero.getCasillas()[i][j].setEnabled(false);
					casillero.getCasillas()[i][j].setBackground(getCasillero().getColorCasillaDesvelada());
				}

				if (control.getTablero()[i][j] > 0) {
					casillero.getCasillas()[i][j].setText(String.valueOf(control.getTablero()[i][j]));
					casillero.getCasillas()[i][j].setFont(new Font("Arial", Font.BOLD, dimensionFuente));
					casillero.getCasillas()[i][j].setBackground(getCasillero().getColorCasillaDesvelada());

					switch (control.getTablero()[i][j]) {
					case 1:
						casillero.getCasillas()[i][j].setForeground(getCasillero().getColorUnaMina());
						break;
					case 2:
						casillero.getCasillas()[i][j].setForeground(getCasillero().getColorDosMinas());
						break;
					default:
						casillero.getCasillas()[i][j].setForeground(getCasillero().getColorMasMinas());
						break;
					}
				}
			}
		}

	}

	/**
	 * Cambia el color de fondo de la casilla segun el color que previamente tenga.
	 * 
	 * @param nameCoordenadas
	 *            Coordenadas de la casilla
	 */
	public void toogleBandera(int[] coordenadas) {
		if (casillero.getCasillas()[coordenadas[0]][coordenadas[1]].getText() == "") {
			if (casillero.getCasillas()[coordenadas[0]][coordenadas[1]].getBackground() != getCasillero()
					.getColorBandera()) {
				casillero.getCasillas()[coordenadas[0]][coordenadas[1]].setBackground(getCasillero().getColorBandera());
				toggleBanderasRestantes();
			} else {
				casillero.getCasillas()[coordenadas[0]][coordenadas[1]].setBackground(new Color(220, 220, 220));
				toggleBanderasRestantes();
			}
		}
	}

	/**
	 * Actualiza el numero de banderas restantes por marcar
	 */
	private void toggleBanderasRestantes() {
		txtCantidadMinas.setText(String.valueOf(control.getCantidadMinas() - getBanderas()));
	}

	/**
	 * @return Retorna el numero de banderas actualmente establecidas en el
	 *         casillero
	 */
	private int getBanderas() {
		int banderas = 0;
		for (int i = 0; i < control.getTablero().length; i++) {
			for (int j = 0; j < control.getTablero().length; j++) {
				if (casillero.getCasillas()[i][j].getBackground() == getCasillero().getColorBandera()) {
					banderas++;
				}
			}
		}
		return banderas;
	}

	public void reiniciarPartida() {
		reiniciarTiempo();
		for (int i = 0; i < casillero.getCasillas().length; i++) {
			for (int j = 0; j < casillero.getCasillas().length; j++) {
				casillero.asignarPropiedadesCasilla(i, j);
			}
		}
		control.reiniciar();
		toggleBanderasRestantes();
		toggleCasillasRestantes();
	}

	/**
	 * Establece el valor del componente grafico que representa el tiempo a 0
	 */
	public void reiniciarTiempo() {
		timer.stop();
		txtTiempo.setText("0");
	}

	/**
	 * Realiza una jugada llamando al metodo Jugar, cuando la casilla doble
	 * clickeada tiene al menos una bandera a su alrededor
	 * 
	 * @param nameCoordenadas
	 *            String con las coordenadas de la casilla doble clickeado
	 */
	public void jugarSeguro(int[] coordenadas) {
		int[] coordenadasContiguas = new int[2];

		for (int i = 0; i < casillero.getCasillas().length; i++) {
			for (int j = 0; j < casillero.getCasillas().length; j++) {
				if (Math.abs(coordenadas[0] - i) <= 1 && Math.abs(coordenadas[1] - j) <= 1) {
					if (casillero.getCasillas()[i][j].getBackground() != getCasillero().getColorBandera()
							&& casillero.getCasillas()[i][j].isEnabled() && haveBandera(coordenadas)) {
						coordenadasContiguas[0] = i;
						coordenadasContiguas[1] = j;
						jugar(coordenadasContiguas);
					}
				}
			}
		}
	}

	/**
	 * Comprueba si una casilla tiene banderas a su alrededor
	 * 
	 * @param coordenadas
	 *            Coordenadas de la casilla
	 * @return Retorna TRUE si tiene banderas a su alrededor o FALSE en caso
	 *         contrario
	 */
	private boolean haveBandera(int[] coordenadas) {
		for (int i = 0; i < casillero.getCasillas().length; i++) {
			for (int j = 0; j < casillero.getCasillas().length; j++) {
				if (Math.abs(coordenadas[0] - i) <= 1 && Math.abs(coordenadas[1] - j) <= 1) {
					if (casillero.getCasillas()[i][j].getBackground() == getCasillero().getColorBandera()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Actualiza el tiempo actual de la partida
	 */
	public void setTime() {
		int tiempoActual = Integer.parseInt(txtTiempo.getText());

		if(tiempoActual < this.tiempoLimite) {
			txtTiempo.setText(String.valueOf(tiempoActual + 1));
		}
	}

	/**
	 * Establece la dificultad de la partida a partir de un valor que alberga el
	 * NAME del boton
	 * 
	 * @param dimension
	 */
	public void setDificultad(float dimension) {

		int minas = (int) (Math.pow(dimension, 2) * dimension / 100);

		control.setCantidadMinas(minas);
		control.inicializar();
		inicializarPartida();
		actualizarCasillero();
		ocultarRecord();
		reiniciarRecord();

		for (int i = 0; i < getCasillero().getCasillas().length; i++) {
			for (int j = 0; j < getCasillero().getCasillas().length; j++) {
				getCasillero().asignarPropiedadesCasilla(i, j);
			}
		}

		if (dimension == 15) {
			btnFacil.setEnabled(false);
			btnMedio.setEnabled(true);
			btnDificil.setEnabled(true);
		} else if (dimension == 20) {
			btnFacil.setEnabled(true);
			btnMedio.setEnabled(false);
			btnDificil.setEnabled(true);
		} else {
			btnFacil.setEnabled(true);
			btnMedio.setEnabled(true);
			btnDificil.setEnabled(false);
		}
	}

}