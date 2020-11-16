package Menues;

import java.util.ArrayList;

import BaseClinica.Auxiliar;
import BaseClinica.Especialidad;

public class menuEspecialidad extends Menu {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList <Integer> dias = new ArrayList(7);
	private static String auxHora = null;
		
	public static void display() {
        int opcion;
        String mensajeOpciones = "\nClinica - Especialidad: ->1) Alta ->2) Baja ->3) Listado Especialidades ->4) Informacion Especialidad ->0) salir";
        while (true) {
        	System.out.println(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 1: 
            		System.out.print("Nombre: "); String n = datos.nextLine();
            		c.mostrarAreas();
            		System.out.print("Nro. Area Medica: "); int idarea = scanner.nextInt();
            		c.mostrarMedicos();
            		System.out.print("Nro Medico: "); int idmed = scanner.nextInt();
            		System.out.println("Seleccionar dias de atencion (S) Si // (N) No: ");
            		System.out.print("2 - Lunes (S/N):"); diasAtencion(datos.next(), 1);
            		System.out.print("3 - Martes (S/N):"); diasAtencion(datos.next(), 2);
            		System.out.print("4 - Miercoles (S/N):"); diasAtencion(datos.next(), 3);
            		System.out.print("5 - Jueves (S/N):"); diasAtencion(datos.next(), 4);
            		System.out.print("6 - Viernes (S/N):"); diasAtencion(datos.next(), 5);
            		System.out.print("7 - Sabado (S/N):"); diasAtencion(datos.next(), 6);
            		System.out.print("1 - Domingo (S/N):"); diasAtencion(datos.next(), 7);
            		System.out.print("Hora incio Atencion (00:00 a 23:59 hs): ");
            		boolean ok = true;
            		while(ok) {
            			ok = controlHora(datos.next());
            		}
            		String horaInicio = auxHora;
            		System.out.print("Hora fin Atencion (00:00 a 23:59 hs): ");
            		ok = true;
            		while(ok) {
            			ok = controlHora(datos.next());
            		}
            		String horaFin = auxHora;
            		new Especialidad(n, idarea, idmed, dias, horaInicio, horaFin);
            		break;
                case 2: 
                	System.out.print("Ingrese el ID a borrar: ");
                	Especialidad.BorrarEspecialidad(scanner.nextInt());
                	break;
                case 3:
                	c.mostrarEspecialidades();
                	break;
                case 4:
                	System.out.print("ID Especilidad: "); int ide = scanner.nextInt();
                	Especialidad e = new Especialidad();
                	e.cargarEspecialidadPorID(ide);
                	e.infoEspecialidad(ide);
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
	}

public static void diasAtencion(String t, int dia) {
		if(t.equalsIgnoreCase("s")) {
			dias.add(dia);
		}

	}

public static boolean controlHora (String hora) {
	boolean r = true;
	try {
		int h = Integer.parseInt(hora.substring(0,2));
		int m = Integer.parseInt(hora.substring(3,5));
		String s = hora.substring(2,3);
		if(h>=0&&h<24&&m>=0&&m<60&&s.equals(":")) {
			auxHora = hora;
			r = false;
		} else 	Auxiliar.advertencia("La hora debe ingresarse en el siguiente rango 00:00 a 23:59\n\nReintentar ", "Ingreso Turno - Error fuera de rango horario");
	} catch(Exception e) {
		System.out.print("La hora debe ingresarse en el siguiente rango 00:00 a 23:59: ");
			r = true;
	}
	
	return r;
	
}

}