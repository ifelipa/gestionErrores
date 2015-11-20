package org.etreball.temperatura;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Conversor {

	private JFrame frame;
	private JTextField celsiusTextField;
	private JTextField kelvinTextField;
	private JLabel lblKelvin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnConversor = new JButton("conversor");
		btnConversor.setBounds(89, 119, 117, 25);
		frame.getContentPane().add(btnConversor);
		btnConversor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Aquest és el codi que s'executa quan es clicka el botó
				 * conversor (btnConversor).
				 * 
				 * Per agafar un text d'un camp JTextField, utilitza el mètode
				 * getText per ex: kelvinTextField.getText()
				 * 
				 * Per posar un text en un camp JTextField, utilitza el mètode
				 * setText per ex: kelvinTextField.setText("text")
				 * 
				 * Si necessites mostrar un missatge en una finestra (tipus pop
				 * up) pots utilitzar: JOptionPane.showMessageDialog(null,
				 * missatge);
				 */

//				double celsius = Double.parseDouble(celsiusTextField.getText());
//				if (celsius <= 0){
//					new TemperaturaControlException("erro..! El numero pasado esta fuera de rango");
//					System.out.println();
//				}
				double celsius = 0;
				try {
					
					celsius = Double.parseDouble(celsiusTextField.getText());
					if (celsius<=0){
						 throw new TemperaturaControlException("El numero ingresado esta fuera del rango");
					}
				} catch (NumberFormatException e2) {
					// TemperaturaControlException
					// InvalidFormatException
					System.out.println("Error al convertir celsius");
				} catch (TemperaturaControlException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getError());
				}

				// try {
				//
				// } catch (InvalidRangeExceptios e3) {
				// // TODO: handle exception
				// }

				Temperatura temp = new Temperatura(celsius);
				double resul = temp.celsius - 273.15;

				try {
					if (resul <= 0);
						
				} catch (IllegalArgumentException e2) {
					System.out.println("Error, Kelvin muy bajo");
				}
				String x = Double.toString(resul);
				kelvinTextField.setText(x);

			}
		});

		celsiusTextField = new JTextField();
		celsiusTextField.setBounds(12, 65, 114, 19);
		frame.getContentPane().add(celsiusTextField);
		celsiusTextField.setColumns(10);

		kelvinTextField = new JTextField();
		kelvinTextField.setBounds(166, 65, 114, 19);
		frame.getContentPane().add(kelvinTextField);
		kelvinTextField.setColumns(10);

		JLabel lblCelsius = new JLabel("Celsius");
		lblCelsius.setBounds(26, 32, 70, 15);
		frame.getContentPane().add(lblCelsius);

		lblKelvin = new JLabel("kelvin");
		lblKelvin.setBounds(176, 32, 70, 15);
		frame.getContentPane().add(lblKelvin);
	}
}
