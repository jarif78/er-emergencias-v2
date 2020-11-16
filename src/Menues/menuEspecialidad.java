package Menues;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BaseClinica.Auxiliar;
import BaseClinica.Especialidad;
import BaseClinica.Medico;

public class menuEspecialidad extends Menu {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList <Integer> dias = new ArrayList(7);
	private static String auxHora = null;
		
	public static void display() {
		
        int opcion;
        String mensajeOpciones = "Menu Especialidad: \n\n1) Alta \n2) Baja \n3) Listado Especialidades \n4) Informacion Especialidad \n0) Salir";
        String[] array = {"1 - Alta Especialidad", "2 - Baja Especialidad", "3 - Listado Especialidad", "4 - Informacion Especialidad", "0 - Salir"};

        while (true) {
        	
        	Object o = Auxiliar.menuo(mensajeOpciones, "Menu Especialidad", array);
    		opcion = Auxiliar.n(o);
            switch (opcion) {
            	case 1: 
            		String n = Auxiliar.menus("Ingrese nombre de la Especialidad:", "Pediatria");
            		o = Auxiliar.menuo("Areas Medicas disponibles\n\nSeleccione Area medica:", "Menu Especialidad - Alta", c.mostrarAreas());
            		int idarea = Auxiliar.n(o);
            		o = Auxiliar.menuo("Listado medicos\n\nSeleccione Medico:", "Menu Especialidad - Alta", Medico.arrayMedicos());
            		int idmed = Auxiliar.n(o);
            		
        			int ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Lunes:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(2);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Martes:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(3);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Miercoles:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(4);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Jueves:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(5);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Viernes:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(6);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Sabado:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(7);
        			ide = JOptionPane.showOptionDialog(null,"Dias de Atencion\n\nAtencion dias Domningo:", "Especialidad- Dias de Atencion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        			if(ide==0) dias.add(1);
            		boolean ok = true;
            		while(ok) {
            			ok = controlHora(Auxiliar.menus("Hora inicio Atencion (00:00 a 23:59 hs):", "08:00"));
            		}
            		String horaInicio = auxHora;
            		System.out.print("Hora fin Atencion (00:00 a 23:59 hs): ");
            		ok = true;
            		while(ok) {
            			ok = controlHora(Auxiliar.menus("Hora Fin Atencion (00:00 a 23:59 hs):", "18:00"));
            		}
            		String horaFin = auxHora;
            		new Especialidad(n, idarea, idmed, dias, horaInicio, horaFin);
            		break;
                case 2: 
                	
                	o = Auxiliar.menuo("Especialidades\n\nSeleccione la especialidad a borrar:", "Especialidad - Borrar", c.mostrarEspecialidades());
                	ide = Auxiliar.n(o); 
                	Especialidad.BorrarEspecialidad(ide);
                	break;
                case 3:
                	Auxiliar.advertencia("Listado de especialidades:\n\n" + Especialidad.reporteEspecialidades(), "Reporte - Especialidad");
                	break;
                case 4:
                	o = Auxiliar.menuo("Seleccione Especialidad para obtener informacion", "Especialidades - Informacion de especialidad" , c.mostrarEspecialidades());
                	ide = Auxiliar.n(o);
                	Especialidad e = new Especialidad();
                	e.cargarEspecialidadPorID(ide);
                	e.infoEspecialidad(ide);
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
	}

//public static void diasAtencion(String t, int dia) {
//		if(t.equalsIgnoreCase("s")) {
//			dias.add(dia);
//		}
//	}

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
		Auxiliar.advertencia("La hora debe ingresarse en el siguiente rango 00:00 a 23:59", "Especialidad - Error en el formato horario");
			r = true;
	}
	
	return r;
	
}

}