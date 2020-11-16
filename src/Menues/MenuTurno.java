package Menues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import BaseClinica.*;
import Prestaciones.Turno;

public class MenuTurno extends Menu {
	
	private static Date fechaturno;
	private static String a = "";
	
	public static void display() {
		int dni = 0;
		String hti = null;
		String htf = null;
		Especialidad e = new Especialidad();

        String mensajeOpciones = "Menu Turnos: \n\n1) - Nuevo Turno \n2) - Cancelar Turno \n3) - Listado Turnos \n0) - Salir \n\nSeleccione opcion:";

    	String[] array = {"1 - Nuevo Turno", "2 - Cancelar Turno", "3 - Listado Turnos", "0 - Salir"};
        while (true) {
        	
		Object o = Auxiliar.menuo(mensajeOpciones, "Menu Turno", array);
		int opcion = Auxiliar.n(o);
		
 
        	//System.out.print(mensajeOpciones);
            //opcion = scanner.nextInt();
            switch (opcion) {
            	case 1: 
            		o = Auxiliar.menuo("Especialidades disponibles.\n\nSeleccione opcion:", "Nuevo Turno", c.mostrarEspecialidades());
            		int ide = Auxiliar.n(o);
            		e.cargarEspecialidadPorID(ide);
            		diasAtencion(e);
            		String tex = "Especialidad " + e.getNombre() + "\n\n";
            		tex = tex + "Dias de atencion: " + a + "\nHorario: " + e.getHoraInicio() + " Hasta: " + e.getHoraFin() + "\n\nIngrese fecha para el turno:";
            		boolean r = true;
            		while(r) {
            			String f = Auxiliar.menus(tex, Auxiliar.hoyString());
            			r =controlFecha(f, e);
            			if(!r) r = !fechaMayorAhoy(f);

            		}
            		tex = Turno.turnosDelDia(fechaturno, ide);
            		r = true;
            		while(r) {
            			dni = Integer.parseInt(Auxiliar.menus("Turnos asignados este dia:\n\n" + tex + "\n\nIngrese numero de DNI del paciente.\n\nDebe ingresarse el numero entero sin .(punto) ni , (coma):", "12345678"));
            			r = !Paciente.existeDNIPaciente(dni);
            			if(r)Auxiliar.advertencia("El DNI no es correcto, intente nuevamente", "Ingreso turno - Error DNI no dado de alta");
            		}
            			Paciente.turnosSinAsistir(dni);
            		r = true;
            		while(r) {
            			hti = Auxiliar.menus("Bienvenido paciente: " + Paciente.nombrePacientexDni(dni) + "\n\nIngrese la Hora del turno (formato hh:mm): ", "08:00");
            			r = controlDeHoras(hti, e);
            		}
            		
            		r = true;
            		while(r) {
            			htf = Auxiliar.menus("Hora finalizacion de turno: ", "08:10");
            			r = controlDeHoras(htf, e);
            			if(horaInicioMenorHorafin(hti, htf)) MenuTurno.display();
            		}
            		if(Turno.sobreTurno(fechaturno, hti, htf, e)||Turno.sobreTurno(fechaturno, hti, htf, dni)) {
            			ide = JOptionPane.showOptionDialog(null,"Desea otorgar soberturno?", "Ingreso Turno - Autorizacion Sobreturno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            			if(ide==0) new Turno(dni, ide, fechaturno, hti, htf);
            			else { Auxiliar.advertencia("Turno cancelado.", "Ingreso turno - No dar sobreturno");
            				}
            			} else { new Turno(dni, ide, fechaturno, hti, htf);}
            			a = "";
            			fechaturno = null;
            		break;
                case 2: 
            		r = true;
            		while(r) {
            			dni = Integer.parseInt(Auxiliar.menus("Ingrese numero de DNI del paciente.\n\nDebe ingresarse el numero entero sin .(punto) ni , (coma):", "12345678"));
            			r = !Paciente.existeDNIPaciente(dni);
            			if(r)Auxiliar.advertencia("El DNI no es correcto, intente nuevamente", "Ingreso turno - Error DNI no dado de alta");
            		}
            			tex = Turno.turnosDelPaciente(dni);
            			if(tex.length()<5) {
            				Auxiliar.advertencia("No existen turnos disponibles para borrar del DNI: " + dni, "Turnos - Error No hay DNI asociado");
            				opcion = 1;
            			} else {
            			int idturno = Integer.parseInt(Auxiliar.menus(tex + "\n\nIngrese nro Turno a eliminar: ", tex.substring(0, tex.indexOf(" "))));
            			Turno.borrarTurno(idturno);}
                	break;
                case 3:
                	Auxiliar.advertencia("Listado de turnos: \n\n" + Turno.mostrarTurnos(), "Reporte - Listado de turnos");
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }
	
	@SuppressWarnings("unchecked")
	public static void diasAtencion(Especialidad e) {
		ArrayList <Integer> lista = e.getDias();
		for(int dias : lista) {
			a = a + diaSemana(dias) + " - ";
		}
		a = a.substring(0, a.length()-3);
	}
	
	public static boolean controlFecha(String f, Especialidad e) {
		boolean r = true;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		fechaturno = null;
		try {
			fechaturno = df.parse(f);
			GregorianCalendar fechaCalendario = new GregorianCalendar();
			fechaCalendario.setTime(fechaturno);
			int dS = fechaCalendario.get(Calendar.DAY_OF_WEEK);
			if(e.getDias().contains(dS)) {
				r = false;
			} else {
				r = true;
				Auxiliar.advertencia("El dia ingresado (" + diaSemana(dS) + ") No brinda atencion esta especializacion. Reintentar", "Ingreso turno - Error dia de atencion");
			};
		 } catch (ParseException e2) {
			 Auxiliar.advertencia("El formato de fecha debe ser dd/mm/yyyy. Reintentar", "Ingreso turno - Error en formato de fecha");
			 //e2.printStackTrace();
		 }
		return r;
	}
	
	
	public static boolean controlDeHoras(String s, Especialidad e) {
		boolean r = true;
		if(menuEspecialidad.controlHora(s)==false) {
			if(e.controlHoras(s)) r = false;
		}
		return r;
	}
	
	public static boolean horaInicioMenorHorafin(String inicio, String fin) {
		int hi = Integer.parseInt(inicio.substring(0,2));
		int mi = Integer.parseInt(inicio.substring(3,5));
		int hf = Integer.parseInt(fin.substring(0,2));
		int mf = Integer.parseInt(fin.substring(3,5));
		boolean r = true;
		if(hi<hf) r = false;
		else if(hi==hf&&mi<mf) r = false;
		else {
			Auxiliar.advertencia("La hora de fin no puede ser menor a la hora inicio. Turno cancelado", "Ingreso turno - Error en hora fin" );
			r = true;
		}
		return r;
	}
	
	public static boolean fechaMayorAhoy(String f) {
		boolean r = false;
		LocalDate hoy = LocalDate.now();		
		LocalDate t = LocalDate.parse(f, DateTimeFormatter.ofPattern("dd/MM/yyyy") );
		if(t.compareTo(hoy)>=0) r = true;
		if(!r)Auxiliar.advertencia("La fecha ingresada es menor a hoy, reintentar", "Ingreso turno - Error quiere viajar al pasado");
		return r;
	}
	
	public static String diaSemana(int i) {
		String r = "";
		switch(i) {
			case 1: r = "Domingo"; break;
			case 2: r = "Lunes"; break;
			case 3: r = "Martes"; break;
			case 4: r = "Miercoles"; break;
			case 5: r = "Jueves"; break;
			case 6: r = "Viernes"; break;
			case 7: r = "Sabado"; break;
		}

		return r;
	}
	

}
