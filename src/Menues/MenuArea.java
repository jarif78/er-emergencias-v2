package Menues;

import BaseClinica.AreaMedica;

public class MenuArea extends Menu{

	public static void menuArea() {
        int opcion;
        String mensajeOpciones = "\nClinica - Area: ->1) Alta ->2) Baja ->3) Listado Area ->0) salir";
        while (true) {
        	System.out.println(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 1: 
            		System.out.print("Nombre: "); String n = datos.nextLine();
            		c.mostrarMedicos();
            		System.out.print("Ingrese ID Medico Coordinardor: "); 
            		int id = scanner.nextInt();
            		new AreaMedica(n, id);
            		break;
                case 2: 
                	c.mostrarAreas();
                	System.out.print("Ingrese Nro Area: "); id = scanner.nextInt();
                	AreaMedica.borrarArea(id);
                	break;
                case 3:
                	c.mostrarAreas();
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
	}
}
