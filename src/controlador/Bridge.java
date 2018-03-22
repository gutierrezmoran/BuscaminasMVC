package controlador;

import java.awt.Font;
import javax.swing.Timer;

import modelo.Coordenadas;
import modelo.Tablero;
import vista.BuscaminasUI;

public class Bridge extends BuscaminasUI {

	private Tablero control = new Tablero();
	private ActionCasilla listenerCasilla = new ActionCasilla(this);
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
		int tiempo = Integer.parseInt(txtTiempo.getText());
		int tiempoRecord = this.control.getRecord().getTiempoRecord();
		if (tiempo < tiempoRecord || tiempoRecord == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Establece un nuevo record
	 */
	private void actualizarRecord() {
		this.control.getRecord().setTiempoRecord(Integer.parseInt(this.txtTiempo.getText()));
	}

	/**
	 * Actualiza el componente grafico con el nuevo record
	 */
	private void mostrarRecord() {
		this.txtRecord.setText(String.valueOf(this.control.getRecord().getTiempoRecord()));
		this.panelRecord.setVisible(true);
	}

	/**
	 * Oculta el componente grafico que representa el record
	 */
	private void ocultarRecord() {
		this.panelRecord.setVisible(false);
	}

	/**
	 * Establece el valor del componente grafico que representa el record a 0
	 */
	private void reiniciarRecord() {
		this.txtRecord.setText("0");
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
		this.timer.start();
		this.control.getCoordenadas().setCoordenadas(coordenadas);
		if (this.control.realizarJugada()) {
			actualizarCasillero();
			toggleCasillasRestantes();
		} else {
			this.timer.stop();
			actualizarCasillero();
			toggleCasillasRestantes();
			mostrarMinas();
			getCasillero().deshabilitarCasillas();
			if (this.control.isPartidaFinalizada()) {
				if(comprobarRecord()) {
					actualizarRecord();
				}
				mostrarRecord();
			}
		}

	}

	/**
	 * Establece propiedades especificas para las casillas minadas.
	 */
	private void mostrarMinas() {
		for (int i = 0; i < this.casillero.getCasillas().length; i++) {
			for (int j = 0; j < this.casillero.getCasillas().length; j++) {
				if (this.control.isMina(new Coordenadas(i, j))) {
					this.casillero.setPropiedadesMina(this.control.isMinaTocada(control.getCoordenadas()), i, j);
				}
			}
		}
	}

	/**
	 * Establece los listeners de los objetos
	 */
	private void setListeners() {
		this.btnReiniciar.addMouseListener(this.listenerReiniciar);
		this.btnFacil.addActionListener(this.listenerDificultad);
		this.btnMedio.addActionListener(this.listenerDificultad);
		this.btnDificil.addActionListener(this.listenerDificultad);
		for (int i = 0; i < this.casillero.getCasillas().length; i++) {
			for (int j = 0; j < this.casillero.getCasillas()[i].length; j++) {
				this.casillero.getCasillas()[i][j].addMouseListener(this.listenerCasilla);
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
		this.control.inicializarTablero();
		this.control.ponerMinas();
		toggleBanderasRestantes();
		toggleCasillasRestantes();
	}

	/**
	 * Actualiza el numero de casillas restantes por desvelar
	 */
	private void toggleCasillasRestantes() {
		this.txtCantidadCasillas.setText(String.valueOf(this.control.contarCasillasPorDesvelar()));
	}

	/**
	 * Actualiza todo el casillero deshabilitando las desveladas sin minas y dando
	 * valores a las desveladas con minas.
	 */
	public void actualizarCasillero() {
		for (int i = 0; i < this.casillero.getCasillas().length; i++) {
			for (int j = 0; j < this.casillero.getCasillas().length; j++) {

				if (!this.control.getTablero()[i][j].isVelada() && !isRodeadaDeMinas(i, j)) {
					this.casillero.getCasillas()[i][j].setEnabled(false);
					this.casillero.getCasillas()[i][j].setBackground(getCasillero().getColorCasillaDesvelada());
				}

				if (isRodeadaDeMinas(i, j)) {
					ponerNumeroMinasEnCasilla(i, j);
					asignarColorNumeroMinas(i, j);
				}

			}
		}
	}
	
	private boolean isRodeadaDeMinas(int x, int y) {
		if(this.control.getTablero()[x][y].getMinasAlrededor() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void ponerNumeroMinasEnCasilla(int x, int y) {
		int dimensionFuente = Math.round(this.casillero.getCasillas()[0][0].getWidth() / 1.3f);

		this.casillero.getCasillas()[x][y]
				.setText(String.valueOf(control.getTablero()[x][y].getMinasAlrededor()));
		this.casillero.getCasillas()[x][y].setFont(new Font("Arial", Font.BOLD, dimensionFuente));
		this.casillero.getCasillas()[x][y].setBackground(getCasillero().getColorCasillaDesvelada());
		this.casillero.getCasillas()[x][y].setIcon(null);
	}
	
	private void asignarColorNumeroMinas(int x, int y) {
		if(this.control.getTablero()[x][y].getMinasAlrededor() == 1) {
			this.casillero.getCasillas()[x][y].setForeground(getCasillero().getColorUnaMina());
		} else if (this.control.getTablero()[x][y].getMinasAlrededor() == 2) {
			casillero.getCasillas()[x][y].setForeground(getCasillero().getColorDosMinas());
		} else {
			casillero.getCasillas()[x][y].setForeground(getCasillero().getColorMasMinas());
		}
	}

	/**
	 * Cambia el color de fondo de la casilla segun el color que previamente tenga,
	 * simulando una bandera.
	 * 
	 * @param nameCoordenadas
	 *            Coordenadas de la casilla
	 */
	public void toogleBandera(int x, int y) {
		if (control.getTablero()[x][y].isVelada()) {
			if (control.getTablero()[x][y].isMarcada()) {
				control.getTablero()[x][y].setMarcada(false);
				casillero.marcar(false, x, y);
			} else {
				control.getTablero()[x][y].setMarcada(true);
				casillero.marcar(true, x, y);
			}
			toggleBanderasRestantes();
		}
	}

	/**
	 * Actualiza el numero de banderas restantes por marcar
	 */
	private void toggleBanderasRestantes() {
		txtCantidadMinas.setText(String.valueOf(control.getMinas() - getBanderas()));
	}

	public Tablero getControl() {
		return control;
	}

	/**
	 * @return Retorna el numero de banderas actualmente establecidas en el
	 *         casillero
	 */
	private int getBanderas() {
		int banderas = 0;
		for (int i = 0; i < control.getTablero().length; i++) {
			for (int j = 0; j < control.getTablero().length; j++) {
				if (this.control.getTablero()[i][j].isMarcada()) {
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
		control.reiniciarTablero();
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
					if (!control.getTablero()[i][j].isMarcada() && casillero.getCasillas()[i][j].isEnabled()
							&& haveBandera(coordenadas)) {
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
					if (control.getTablero()[i][j].isMarcada()) {
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

		if (tiempoActual < this.tiempoLimite) {
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

		control.setMinas(minas);
		control.inicializarTablero();
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