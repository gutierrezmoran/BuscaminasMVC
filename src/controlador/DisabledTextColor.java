package controlador;

import java.awt.Color;

import javax.swing.plaf.metal.MetalButtonUI;

public class DisabledTextColor extends MetalButtonUI{
	
	private Color color;
	
	public DisabledTextColor(Color color) {
		this.color = color;
	}
	
	@Override
	protected Color getDisabledTextColor() {
		return this.color;
	}

}
