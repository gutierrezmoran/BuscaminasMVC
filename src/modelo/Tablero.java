package modelo;

public class Tablero {

	private int dimension = 20;
	private int[][] tablero;
	private int cantidadMinas;
	private int[] coordenadas = new int[2];
	private boolean minaTocada = false;
	private int record;

	/**
	 * Establece las dimensiones de la matriz y cada posicion de la matriz con el
	 * valor 0
	 */
	public void inicializar() {
		tablero = new int[dimension][dimension];
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero.length; j++) {
				this.tablero[i][j] = 0;
			}
		}
	}

	/**
	 * Establece posiciones aleatorias del tablero con valor -1
	 */
	public void setMinas() {
		int x;
		int y;
		int minasEstablecidas = 0;
		do {
			x = generarNumero(dimension - 1, 0);
			y = generarNumero(dimension - 1, 0);
			
			if (this.tablero[x][y] != -1 && getMinasContiguas(x, y) < 6) {
				this.tablero[x][y] = -1;
				minasEstablecidas++;
			}
		} while(minasEstablecidas != this.cantidadMinas);
	}

	/**
	 * Genera un numero entre un maximo y un minimo
	 * 
	 * @param max
	 * @param min
	 * @return Retorna un numero entero
	 */
	private int generarNumero(int max, int min) {
		return (int) (Math.random() * (max - min)) + min;
	}

	/**
	 * Realiza una jugada a partir de unas coordenadas
	 * 
	 * @return Retorna TRUE si se ha realizado correctamente o FALSE si se toca mina
	 *         o se acaba la partida con exito (todas las casillas descubiertas)
	 */
	public boolean realizarJugada() {
		if (this.tablero[coordenadas[0]][coordenadas[1]] == -1) {
			minaTocada = true;
			return false;
		} else {
			desvelar(this.coordenadas[0], this.coordenadas[1]);
			if (isFinalizado()) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Comprueba si quedan casillas por descubrir en el tablero
	 * 
	 * @return Retorna TRUE si hay casillas por descubrir o FALSE si no las hay
	 */
	public boolean isFinalizado() {
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero.length; j++) {
				if (this.tablero[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Desvela posiciones libres de minas a su alrededor recursivamente desde una
	 * coordenada
	 */
	private void desvelar(int x, int y) {
		int minasContiguas = getMinasContiguas(x, y);
		if (minasContiguas != 0) {
			this.tablero[x][y] = minasContiguas;
		} else {
			this.tablero[x][y] = -2;
			for (int i = 0; i < tablero.length; i++) {
				for (int j = 0; j < tablero.length; j++) {
					if (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1 && (i != x || j != y) && tablero[i][j] == 0) {
						desvelar(i, j);
					}
				}
			}
		}
	}

	/**
	 * Cuenta las minas que hay alrededor de una coordenada
	 * 
	 * @return Retorna un numero entero que representa el numero de minas
	 */
	private int getMinasContiguas(int x, int y) {
		int minas = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1) {
					if (tablero[i][j] == -1) {
						minas++;
					}
				}
			}
		}
		return minas;
	}
	
	/**
	 * @return Retorna el numero de casillas que quedan por desvelar
	 */
	public int getCasillasRestantes() {
		int casillas = 0;
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.tablero[i][j] == 0) {
					casillas++;
				}
			}
		}
		return casillas;
	}

	/**
	 * Inicializa el tablero Establece minas Cambia el valor booleano de minaTocada
	 * a FALSE
	 */
	public void reiniciar() {
		inicializar();
		setMinas();
		this.minaTocada = false;
	}

	public int[][] getTablero() {
		return tablero;
	}

	public int getCantidadMinas() {
		return cantidadMinas;
	}

	public int[] getCoordenadas() {
		return coordenadas;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public void setCantidadMinas(int cantidadMinas) {
		this.cantidadMinas = cantidadMinas;
	}

	public void setCoordenadas(int[] coordenadas) {
		this.coordenadas = coordenadas;
	}

	public boolean isMinaTocada() {
		return minaTocada;
	}

	public void setMinaTocada(boolean minaTocada) {
		this.minaTocada = minaTocada;
	}

	public int getTamano() {
		return dimension;
	}

	public void setTamano(int tamano) {
		this.dimension = tamano;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

}
