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
                case 8: Auxiliar.advertencia("Bienvenido TP Clinica Unpaz.\nEl programa incializa una instancia tipo Clinica en el main, tiene precargado en la clase AuxArmadoBase tres areas medicas, varias especialidades, pacientes"
				+ " turnos, prestaciones, medicos. \nSi desea inicializar una clinica desde cero puede cancelar en el main la linea 21 - Auxiliar.altas(unpaz) de esta manera el programa inicia sin ningun"
				+ " dato.\nPara el manejo se utiliza la informacion y toma de datos desde cuadros de dialgo de Java (JOptionPane) Como esta ventana de ayuda ;)\n\nCon la informacion precargada puede probar el programa\n\n"
				+ "En caso de comenzar sin datos precargados el orden para inicializar es: \n1 - Area Medica \n2 - Medico \n3 - Especialidad\n4 - Paciente\n\nLuego de "
				+ " esto se encuentra en condiciones de asignar turnos, prestaciones al/los pacientes cargado/s\n\nMuchas gracias! Esperamos sea de su agrado\n", "Ayuda");
                	break;
                case 0: System.out.println("Fin de programa"); System.exit(0);
                default: System.out.println("Opcion no válida");
            }}
    }
    
	
	
	
	
}
