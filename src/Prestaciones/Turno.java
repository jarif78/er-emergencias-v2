package Prestaciones;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import BaseClinica.*;


public class Turno {
	protected int id;
	protected int dniPaciente;
	protected int idEspecialidad;
	protected Date fecha;
	protected String horaInicio;
	protected String horaFin;
	protected static Clinica c;
	protected int idPrestacion;  //Se agrega aca - si el idPrestacion = 0 el turno no fue utilizado

	protected boolean estudio;
	
	public Turno() {};
	
	public Turno(int d, int e, Date f, String hi, String hf) {
		setId();
		dniPaciente = d;
		idEspecialidad = e;
		fecha = f;
		horaInicio = hi;
		horaFin = hf;
		idPrestacion = 0;
		setEstudio(e);
		agregarTurno();
	}


	public void setIdPrestacion(int idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	public int getIdPrestacion() {
		return idPrestacion;
	}

	public int getId() {
		return id;
	}

	public int getDniPaciente() {
		return dniPaciente;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public boolean isEstudio() {
		return estudio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public static void setC(Clinica cli) {c = cli;}
	
	public void agregarTurno() {
		boolean p = Paciente.existeDNIPaciente(dniPaciente);
		if(p==false)System.out.println("DNI inexistente, debe dar de alta el paciente");
		boolean e = Especialidad.existeIdEspecialidad(idEspecialidad);
		if(e==false)System.out.println("Id Especiliadad inexistente, debe dar de alta la Especialidad");
		c.getListaTurnos().add(this);
	}
	
	
	public void setId() {
		int i = 0;
		for(int x = 0; x < c.getListaTurnos().size(); x++) {
			if(i<c.getListaTurnos().get(x).id) {
				i = c.getListaTurnos().get(x).id;
			}
		}
		id = i + 1;
	}
	
	public void setEstudio(int ide) {
		Especialidad e = new Especialidad();
		e.cargarEspecialidadPorID(ide);
		int idArea = e.getIdAreaMedica();
		estudio = 0=="Estudios".compareToIgnoreCase(AreaMedica.nombreAreaPorID(idArea));
	}
	
	
	public Turno cargarTurnoconId(int id) {
		Turno r = new Turno();
		for(Turno t : c.getListaTurnos()) {
			if(t.id == id) {
				r = t;
			}
		} return r;
	}
	
	
	public static void turnosDelPaciente(int dni) {
		Locale locale = new Locale ( "es" , "ES" );
		Date hoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		for(int x =0; x<c.getListaTurnos().size(); x++) {
			String b = new SimpleDateFormat("EEEE dd/MM/yyyy", locale).format(c.getListaTurnos().get(x).getFecha());
			if(c.getListaTurnos().get(x).getFecha().after(hoy)&&c.getListaTurnos().get(x).dniPaciente==dni&&c.getListaTurnos().get(x).idPrestacion==0) {
				System.out.println("Turno nro: " + c.getListaTurnos().get(x).getId() + " -\t Especialidad Nro: "+ c.getListaTurnos().get(x).idEspecialidad + " -\t Fecha: " + b + " -\tHorario: " + c.getListaTurnos().get(x).horaInicio);
		}}

	}
	
	public static void borrarTurno(int idturno) {
		
		for(int x =0; x<c.getListaTurnos().size(); x++){
			if(c.getListaTurnos().get(x).id==idturno&&c.getListaTurnos().get(x).idPrestacion==0) {

				c.getListaTurnos().remove(x);
			}
		}
	}
	
	
	public static void mostrarTurnos() {
		Locale locale = new Locale ( "es" , "ES" );
		Date hoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		for(int x = 0; x<c.getListaTurnos().size();x++) {
			if(c.getListaTurnos().get(x).getFecha().after(hoy)&&c.getListaTurnos().get(x).getIdPrestacion()==0) { //
			String f = new SimpleDateFormat("EEEE dd/MM/yyyy", locale).format(c.getListaTurnos().get(x).getFecha());
			System.out.println(c.getListaTurnos().get(x).getId() + "\t" + c.getListaTurnos().get(x).getDniPaciente() + "\t" + f
					+ "\t" + c.getListaTurnos().get(x).getHoraInicio() + "\t" + c.getListaTurnos().get(x).getHoraFin());
		}}
	}
	
	
	
	public static void turnosDelDia(Date buscar, int e) {
		Locale locale = new Locale ( "es" , "ES" );
		String b = new SimpleDateFormat("dd/MM/yyyy", locale).format(buscar);
		for(Turno t : c.getListaTurnos()) {
			String f = new SimpleDateFormat("dd/MM/yyyy", locale).format(t.fecha);
			if(b.equals(f)&&t.idEspecialidad==e&&t.idPrestacion==0) {
				System.out.println("Turno nro:" + t.id + "\t DNI: " + t.dniPaciente + "\t Dia: " + f + "\t Horario:" + t.horaInicio + "\t" + t.horaFin);
			}
			
		}
		
	}
	
	public static boolean sobreTurno(Date buscar, String desde, String hasta, Especialidad e) {
		boolean r = false;
		Locale locale = new Locale ( "es" , "ES" );
		String b = new SimpleDateFormat("dd/MM/yyyy", locale).format(buscar);
		for(Turno t : c.getListaTurnos()) {
			String f = new SimpleDateFormat("dd/MM/yyyy", locale).format(t.fecha);
			if(b.equals(f)&&t.idEspecialidad==e.getId()) {
				r = horarioSuperpuesto(desde, hasta, t.horaInicio, t.horaFin);
				if(r) System.out.println("Especialidad con superposicion de horarios: " + desde + " - " + hasta + " con turno: " + t.horaInicio + " - " + t.horaFin);
			}
		}
		return r;
	}
	
	public static boolean sobreTurno(Date buscar, String desde, String hasta, int dni) {
		boolean r = false;
		Locale locale = new Locale ( "es" , "ES" );
		String b = new SimpleDateFormat("dd/MM/yyyy", locale).format(buscar);
		for(Turno t : c.getListaTurnos()) {
			String f = new SimpleDateFormat("dd/MM/yyyy", locale).format(t.fecha);
			if(b.equals(f)&&t.dniPaciente==dni) {
				r = horarioSuperpuesto(desde, hasta, t.horaInicio, t.horaFin);
				if(r) System.out.println("Paciente con superposicion de horarios: " + desde + " - " + hasta + " con turno: " + t.horaInicio + " - " + t.horaFin);
			}
		}
		return r;
	}
	

	
	
	public static boolean horarioSuperpuesto(String desde, String hasta, String tdesde, String thasta) {
		boolean r = false;
		int hd = Integer.parseInt(desde.substring(0, 2))*60 + Integer.parseInt(desde.substring(3, 5));		//entero + minutos hora desde turno a solicitar
		int hh = Integer.parseInt(hasta.substring(0, 2))*60 + Integer.parseInt(hasta.substring(3, 5)) - 1;		// entero hora hasta turno a solicitar
		int hda = Integer.parseInt(tdesde.substring(0, 2))*60 + Integer.parseInt(tdesde.substring(3, 5));		// entero hora desde el turno anterio
		int hha = Integer.parseInt(thasta.substring(0, 2))*60 + Integer.parseInt(thasta.substring(3, 5)) - 1;		// entero turno hasta el turno anterior
		
		// chequeo de horas si estan en rango
		// obtengo valores enteros multiplicando la hora * 60 y sumandole los minutos, al final de cada turno le resto 1
		// para que no se pise con el otro turno, ejemplo un turno de 10:00 a 10:30, queda hasta 10:29
		// Logica aplicada
		//verdadero - hora inicio es mayor o igual a la hora del turno anterior y es menor igual hora fin de turno anterior
		//verdadero - hora fin es mayor o igual al inicio turno anterior y es menor o igual al fin del turno anterior
		//verdadero - hora de inicio del turno anterior es mayor o igual al inicio del turno pero menor al fin del turno
		
		if(hd>=hda&&hd<=hha||hh>=hda&&hh<=hha||hda>=hd&&hda<=hh) {
			r = true;
		}
		
		return r;
	}
	

}
