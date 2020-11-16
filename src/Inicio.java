import javax.swing.JOptionPane;

import BaseClinica.*;
import Menues.Menu;
import Menues.MyIcon;
import Prestaciones.Turno;

public class Inicio {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		MyIcon icon = new MyIcon();
		
		JOptionPane.showMessageDialog(null, "Bienvenido!\n\nMateria: Programacion Orientada a Objetos\n\nTrabajo Practico de programa Clinica - UNPAZ                                     "
				+ "\n\nProfesores:\n     Ing. Lucas Guaycochea\n     Lic. Cristian Ciarallo\n\nAlumnos:\n     Anahi Aguirre\n     Ariel Fernandez\n     Ricardo Gonzalez\n\n",
				"TP-POO - Programa clinica", JOptionPane.INFORMATION_MESSAGE, icon);

		Clinica unpaz = new Clinica();
		Paciente.setC(unpaz);
		AreaMedica.setC(unpaz);
		Turno.setC(unpaz);
		Auxiliar.altas(unpaz);
		
		Menu.setC(unpaz);
		Menu.display();
		

	}

}
