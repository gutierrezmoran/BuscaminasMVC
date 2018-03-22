package modelo;

public class Coordenadas {

	private int coordenadaX;
	private int coordenadaY;
	
	public Coordenadas() {}

	public Coordenadas(int x, int y) {
		this.coordenadaX = x;
		this.coordenadaY = y;
	}
	
	public void setCoordenadas(int[] coordenadas) {
		this.coordenadaX = coordenadas[0];
		this.coordenadaY = coordenadas[1];
	}

	public int getCoordenadaX() {
		return coordenadaX;
	}
	public int getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaX(int coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public void setCoordenadaY(int coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

}
