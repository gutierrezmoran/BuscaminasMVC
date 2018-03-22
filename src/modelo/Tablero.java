package modelo;

public class Tablero {

	private final int dimension = 20;
	private Casilla[][] tablero = new Casilla[dimension][dimension];
	private int cantidadMinas;
	private Coordenadas coordenadas = new Coordenadas();
	private Record record = new Record();

	/**
	 * Crea una instancia de tipo casilla en cada posicion de la matriz
	 */
	public void inicializarTablero() {
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				this.tablero[i][j] = new Casilla();
			}
		}
	}

	public void ponerMinas() {
		int x;
		int y;
		int establecidas = 0;
		do {
			x = generarNumeroAleatorio(dimension, 0);
			y = generarNumeroAleatorio(dimension, 0);

			if (!this.tablero[x][y].isMina() && contarMinasContiguas(x, y) < 6) {
				this.tablero[x][y].setMina(true);
				establecidas++;
			}
		} while (establecidas != this.cantidadMinas);
	}

	private int generarNumeroAleatorio(int max, int min) {
		return (int) (Math.random() * (max - min)) + min;
	}

	public boolean realizarJugada() {
		if (isMinaTocada(this.coordenadas)) {
			return false;
		} else {
			desvelarCasilla(this.coordenadas.getCoordenadaX(), this.coordenadas.getCoordenadaY());
			if (isPartidaFinalizada()) {
				return false;
			}
			return true;
		}
	}
	
		public boolean isMinaTocada(Coordenadas coordenadas) {
			if(isMina(coordenadas)) {
				return true;
			} else {
				return false;
			}
		}
		
			public boolean isMina(Coordenadas coordenadas) {
				if(this.tablero[coordenadas.getCoordenadaX()][coordenadas.getCoordenadaY()].isMina()) {
					return true;
				}
				return false;
			}

	public boolean isPartidaFinalizada() {
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.tablero[i][j].isVelada() && !this.tablero[i][j].isMina()) {
					return false;
				}
			}
		}
		return true;
	}

	private void desvelarCasilla(int x, int y) {
		int minasContiguas = contarMinasContiguas(x, y);
		if (minasContiguas != 0) {
			this.tablero[x][y].setMinasAlrededor(minasContiguas);
			this.tablero[x][y].setVelada(false);
		} else {
			this.tablero[x][y].setVelada(false);
			for (int i = 0; i < this.dimension; i++) {
				for (int j = 0; j < this.dimension; j++) {
					if (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1 && (i != x || j != y)
							&& tablero[i][j].isVelada()) {
						desvelarCasilla(i, j);
					}
				}
			}
		}
	}

		private int contarMinasContiguas(int x, int y) {
			int minas = 0;
			for (int i = 0; i < this.dimension; i++) {
				for (int j = 0; j < this.dimension; j++) {
					if (Math.abs(x - i) <= 1 && Math.abs(y - j) <= 1) {
						if (tablero[i][j].isMina()) {
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
	public int contarCasillasPorDesvelar() {
		int casillas = 0;
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.tablero[i][j].isVelada() && !this.tablero[i][j].isMina()) {
					casillas++;
				}
			}
		}
		return casillas;
	}

	public void reiniciarTablero() {
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				this.tablero[i][j].setVelada(true);
				this.tablero[i][j].setMina(false);
				this.tablero[i][j].setMarcada(false);
				this.tablero[i][j].setMinasAlrededor(0);
			}
		}
		ponerMinas();
	}

	public Casilla[][] getTablero() {
		return tablero;
	}

	public int getMinas() {
		return cantidadMinas;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

	public void setMinas(int cantidadMinas) {
		this.cantidadMinas = cantidadMinas;
	}

	public int getDimension() {
		return dimension;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

}
