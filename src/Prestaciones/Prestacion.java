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
	


	public static LinkedList mostrarPrestacionesHoy(int e) {
		LinkedList <Integer> idturnos = new LinkedList<Integer>();
		Date hoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		for(Turno t : c.getListaTurnos()) {
			if(t.idEspecialidad == e&&t.idPrestacion==0) {	// agregar filtro de fecha de ser necesario &&t.fecha.after(hoy)
				idturnos.add(t.id); 
				System.out.println("Turno Nro: " + t.id + "\tPaciente: " + Paciente.nombrePacientexDni(t.dniPaciente) + "\tHora:" + t.horaInicio + "\t " + t.fecha); 
			}
		}
		return idturnos;
	}

	public static void prestacionImpagaPorDni(int dni) {
		Locale locale = new Locale ( "es" , "ES" );
		for(Prestacion p : c.getListaPrestacion()) {
			if(p.dniPaciente==dni&&p.formaPago==null) {	// agregar filtro de fecha de ser necesario
				String f = new SimpleDateFormat("EEEE dd/MM/yyyy", locale).format(p.fecha);
				System.out.println("Prestacion nro: " + p.id + "\t Especialidad: " + Especialidad.nombreAreaPorID(p.idEspecialidad)  + "\t " + p.tratamiento + "\tFecha: " + f); 
			}
		}
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
