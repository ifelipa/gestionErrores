package org.etreball.temperatura;

public class TemperaturaControlException extends Exception{
	
	
		private String error;

		public TemperaturaControlException(String  mensajes) {
			this.error= mensajes;		
		}	
		
		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
		
			

}
