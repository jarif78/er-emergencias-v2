package Menues;
import java.util.Scanner;

import javax.swing.JOptionPane;

import BaseClinica.*;


public class Menu {
	protected static Clinica c;
	protected static Scanner scanner = new Scanner(System.in);
	protected static Scanner datos = new Scanner(System.in);
	
    public static void setC(Clinica unpaz) {
		c = unpaz;
	}

	public static void display() {
        
        String mensajeOpciones = "Menu Clinica: \n\n1) - Turnos \n2) - Prestacion \n3) - Pacientes \n4) - Medicos "
        		+ "\n5) - Especialidad \n6) - Area \n7) - Reportes \n8) - Ayuda \n0) - Salir \n\nSeleccione opcion:";
        while (true) {
        	String[] opciones = {"1 - Turnos", "2 - Prestacion", "3 - Pacientes", "4 - Medicos", "5 - Especialidad", "6 - Area", "7 - Reportes", "8 - Ayuda", "0 - Salir"};

        	
        	//Object opcion1 = JOptionPane.showInputDialog(null, mensajeOpciones, "Menu Principal - Unpaz - POO", JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
            Object o = Auxiliar.menuo(mensajeOpciones, "Menu Principal", opciones);
        	int opcion = Auxiliar.n(o);
            
            
            //System.out.print(mensajeOpciones);
            //opcion = scanner.nextInt();
            switch (opcion) {
            	case 1:
            		MenuTurno.display();
            		break;
            	case 2: MenuPrestacion.display();
        		break;
                case 3: menuPaciente.display();
                	break;
                case 4: menuMedico.display(); 
                	break;
                case 5: menuEspecialidad.display(); 
        			break;
                case 6: MenuArea.menuArea();;
                	break;
                case 7: menuReportes.display();
                	break;
                case 8: menuAyuda.display();
                	break;
                case 0: System.out.println("Fin de programa"); System.exit(0);
                default: System.out.println("Opcion no v�lida");
            }}
    }
    
	
	
	
	
}