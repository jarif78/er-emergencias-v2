package Menues;
import BaseClinica.Paciente;

public class menuPaciente extends Menu {
	
	public static void display() {
        int opcion;
        String mensajeOpciones = "\nClinica - Pacientes: ->1) Alta ->2) Baja ->3) Listado Pacientes ->0) salir";
        while (true) {
        	System.out.println(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 1: 
            		System.out.print("Nombre: "); String n = datos.nextLine();
            		System.out.print("DNI: "); int dni = scanner.nextInt();
            		System.out.print("Telefono: "); String t = datos.nextLine();
            		System.out.print("Cobertura: "); String cob = datos.nextLine();
            		new Paciente(n, dni, t, cob);
            		break;
                case 2: 
                	System.out.print("DNI: "); dni = scanner.nextInt();
                	Paciente.BorrarPaciente(dni);
                	break;
                case 3:
                	c.mostrarPacientes();
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }


}
