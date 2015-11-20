package org.etreball.temperatura;

public class Temperatura {
	double celsius;
	double kelvin;

	
	public Temperatura(double celsius) {
		this.celsius = celsius;
		
	}

	public double getCelsius() {
		return celsius;
	}

	public void setCelsius(double celsius) {
		this.celsius = celsius;
	}

	public double getKelvin() {
			return kelvin;
	}
	
}
