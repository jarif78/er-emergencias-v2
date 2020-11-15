package Menues;

import java.util.Scanner;

import BaseClinica.Medico;

public class menuMedico extends Menu {
	
	@SuppressWarnings("resource")
	public static void display() {
        int opcion;
        String mensajeOpciones = "\nClinica - Medicos: ->1) Alta ->2) Baja ->3) Listado Medicos ->0) salir";
        while (true) {
        	System.out.println(mensajeOpciones);
            opcion = scanner.nextInt();
            Scanner datos = new Scanner(System.in);
            switch (opcion) {
            	case 1: 
            		System.out.print("Nombre: "); String n = datos.nextLine();
            		System.out.print("DNI: "); int dni = scanner.nextInt();
            		System.out.print("Especialidad: "); String esp = datos.nextLine();
            		System.out.print("Telefono: "); String tel = datos.nextLine();
            		new Medico(n, esp, dni, tel);
            		break;
                case 2: 
                	System.out.print("DNI: "); dni = scanner.nextInt();
                	Medico.borrarMedico(dni);
                	break;
                case 3:
                	c.mostrarMedicos();
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }


}
