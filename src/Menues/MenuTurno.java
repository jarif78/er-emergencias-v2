package Menues;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
            		Auxiliar.menuo(c.mostrarEspecialidades(), "Nuevo Turno", array);
            		boolean r = true;
            		int ide = 0;
            		while(r) {
                		System.out.print("ID Especialidad: "); ide = datos.nextInt();
            			r = !Especialidad.existeIdEspecialidad(ide);

            		}
            		e.cargarEspecialidadPorID(ide);
            		diasAtencion(e);
            		System.out.println("Dias de atencion: " + a + "\nHorario: " + e.getHoraInicio() + " Hasta: " + e.getHoraFin());
            		datos.reset();
            		r = true;
            		while(r) {
                		System.out.print("Ingrese fecha: "); String f = datos.next();
            			r =controlFecha(f, e);
            			if(!r) r = !fechaMayorAhoy(f);

            		}
            		Turno.turnosDelDia(fechaturno, ide);
            		r = true;
            		while(r) {
            			System.out.print("DNI Paciente: "); dni = datos.nextInt();
            			r = !Paciente.existeDNIPaciente(dni);
            			if(r)System.out.println("El DNI no es correcto, intente nuevamente");
            		}
            			Paciente.turnosSinAsistir(dni);
            		r = true;
            		while(r) {
            			System.out.print("Hora del turno: ");  hti = datos.next();
            			r = controlDeHoras(hti, e);
            		}
            		
            		r = true;
            		while(r) {
            			System.out.print("Hora del fin turno: "); htf = datos.next();
            			r = controlDeHoras(htf, e);
            			if(horaInicioMenorHorafin(hti, htf)) MenuTurno.display();
            		}
            		if(Turno.sobreTurno(fechaturno, hti, htf, e)||Turno.sobreTurno(fechaturno, hti, htf, dni)) {
            			r = true; 
            			while(r) {
            				System.out.print("Dar sobre turno? s-n: "); char s = datos.next().charAt(0);
            				switch (s) {
            				case 's':
            					new Turno(dni, ide, fechaturno, hti, htf);
            					r = false;
            					break;
            				case 'n':
            					System.out.println("Turno cancelado.");
            					opcion = 'a';
            					r = false;
            					break;
            				default: System.out.println("Opcion no válida"); 
            				}
            			}
            			} else { new Turno(dni, ide, fechaturno, hti, htf);}
            			a = "";
            			fechaturno = null;
            		break;
                case 2: 
            		r = true;
            		while(r) {
            			System.out.print("DNI Paciente: "); dni = datos.nextInt();
            			r = !Paciente.existeDNIPaciente(dni);
            		}
            			Turno.turnosDelPaciente(dni);
            			System.out.print("Ingrese nro Turno a eliminar: "); int idturno = datos.nextInt();
            			Turno.borrarTurno(idturno);
                	break;
                case 3:
                	Turno.mostrarTurnos();
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
				System.out.println("El dia ingresado (" + diaSemana(dS) + ") No brinda atencion esta especializacion. Reintentar");
			};
		 } catch (ParseException e2) {
			 System.err.println("El formato de fecha debe ser dd/mm/yyyy.");
			 e2.printStackTrace();
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
			System.out.print("La hora de fin no puede ser menor a la hora inicio. Turno cancelado" );
			r = true;
		}
		return r;
	}
	
	public static boolean fechaMayorAhoy(String f) {
		boolean r = false;
		LocalDate hoy = LocalDate.now();		
		LocalDate t = LocalDate.parse(f, DateTimeFormatter.ofPattern("dd/MM/yyyy") );
		if(t.compareTo(hoy)>=0) r = true;
		if(!r)System.out.println("La fecha ingresada es menor a hoy, reintentar");
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
