package Prestaciones;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import BaseClinica.Especialidad;
import BaseClinica.Paciente;

@SuppressWarnings({ "rawtypes", "null" })
public class Prestacion extends Turno {
	
	private String tratamiento;
	private String formaPago;

	public Prestacion() {};
	
	public Prestacion(Turno t) {
		dniPaciente = t.dniPaciente;
		fecha = t.fecha;
		idEspecialidad = t.idEspecialidad;
		estudio = t.estudio;
		setTratamiento("Estudio");
		setidPrestacion(t);
		c.getListaPrestacion().add(this);
	}
	
	public Prestacion(Turno t, String trat) {
		dniPaciente = t.dniPaciente;
		fecha = t.fecha;
		idEspecialidad = t.idEspecialidad;
		estudio = t.estudio;
		setidPrestacion(t);
		setTratamiento(trat);
		c.getListaPrestacion().add(this);
	}
	
	public Prestacion(int d, int e, Date f, String hi, String hf) {
		super(d, e, f, hi, hf);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void setidPrestacion(Turno t) {
		int i = 1;
		for(Prestacion p : c.getListaPrestacion()) {
			if(i<p.getId()) {
				i = p.getId();
			}
		}
		this.id = i + 1;
		t.setIdPrestacion(i);
	}
	


	public static String [] mostrarPrestacionesHoy(int e) {
		//LinkedList <Integer> idturnos = new LinkedList<Integer>();
		Locale locale = new Locale ( "es" , "ES" );
		String [] r = new String [cantPrestaciones(e)];
		int x = 0;
		for(Turno t : c.getListaTurnos()) {
			if(t.idEspecialidad == e&&t.idPrestacion==0) {	// agregar filtro de fecha de ser necesario &&t.fecha.after(hoy)
				String f = new SimpleDateFormat("dd/MM/yyyy", locale).format(t.fecha);
				r[x] = t.id + "  -  Paciente: " + Paciente.nombrePacientexDni(t.dniPaciente) + "  -  Hora: " + t.horaInicio + "  -  " + f; 
				x++;
			}
		}
		return r;
	}
	
	private static int cantPrestaciones(int e) {
		int r = 0;
		for(Turno t : c.getListaTurnos()) {
			if(t.idEspecialidad == e&&t.idPrestacion==0) r++;
		}
		return r;
	}

	public static String [] prestacionImpagaPorDni(int dni) {
		int x = 0;
		for(Prestacion p : c.getListaPrestacion()) {
			if(p.dniPaciente==dni&&p.formaPago==null) {	// agregar filtro de fecha de ser necesario
				x++;
			}
		}

		String r [] = new String [x];
		x = 0;
		Locale locale = new Locale ( "es" , "ES" );
		for(Prestacion p : c.getListaPrestacion()) {
			if(p.dniPaciente==dni&&p.formaPago==null) {	// agregar filtro de fecha de ser necesario
				String f = new SimpleDateFormat("dd/MM/yyyy", locale).format(p.fecha);
				r[x]= p.id + "  -  Especialidad: " + Especialidad.nombreAreaPorID(p.idEspecialidad)  + "  -  " + p.tratamiento + "  -  Fecha: " + f; 
				x++;
			}
		}
		return r;
	}
	
	
	public Prestacion prestacionPorId(int id) {
		Prestacion r = new Prestacion();
		for(Prestacion p : c.getListaPrestacion()) {
			if(p.id == id) {
				r = p;
			}
		}
		return r;
	}
	
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
}
