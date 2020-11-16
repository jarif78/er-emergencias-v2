package BaseClinica;

import java.util.Date;

import Prestaciones.Turno;

public class Paciente {
	protected static Clinica c;
	protected int id;
	protected String nombre;
	protected int dni;
	protected String telefono;
	private String coberturaPac;
	
	public static void setC(Clinica cli) {
		c = cli;
	}

	public Paciente() {};
	
	
	public Paciente(String nombrePac, int dniPac, String telefonoPac, String coberturaPac) {		// Pedimos la clinica para verificar si existe ya el paciente
		setid();
		this.nombre = nombrePac;
		this.dni = dniPac;
		this.telefono = telefonoPac;
		this.coberturaPac = coberturaPac;
		agregarPaciente();
	}

	public static void turnosSinAsistir(int dni) {
		Date hoy = new Date();
		boolean alerta = false;
		int x = 0;
		for(Turno t : c.getListaTurnos()) {
			if(t.getFecha().before(hoy)&&t.getDniPaciente()==dni) {
				x = x +1;
				alerta =  true;
			}
		} if(alerta) Auxiliar.advertencia("El paciente presenta " + x + " turnos sin asistir", "Ingreso Turno - Control de asistencia de paciente");
		
	}
	
	public static boolean existeDNIPaciente(int dni) {
		boolean r = false;
        for(int x = 0; x < c.getListaPacientes().size(); x++ ) {
        	if(c.getListaPacientes().get(x).getDni()==dni) {
        		r = true;
        	}
        }
        return r;
	}
	
	public static String nombrePacientexDni(int dni) {
		String r = "";
		for(Paciente p : c.getListaPacientes()) {
			if(p.dni==dni) r = p.nombre;
		}
		return r;
	}
	
	public static void BorrarPaciente(int dni) {
        for(int x = 0; x < c.getListaPacientes().size(); x++ ) {
        	if(c.getListaPacientes().get(x).getDni()==dni) {
        		c.getListaPacientes().remove(x);
        	}
        }
	}
	

	
	public void agregarPaciente() {
		if(existeDNIPaciente(dni)){System.out.println("Error DNI ya ingresado");}
		else {c.getListaPacientes().add(this);}
	}

	public int getid() {
		return id;
	}


	public void setid() {
		int i = 0;
		for(int x = 0; x < c.getListaPacientes().size(); x++) {
			if(id<c.getListaPacientes().get(x).getid()) {
				i = c.getListaPacientes().get(x).getid();
			}
		}
		id = i + 1;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombrePac) {
		nombre = nombrePac;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dniPac) {
		dni = dniPac;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefonoPac) {
		telefono = telefonoPac;
	}


	public String getCoberturaPac() {
		return coberturaPac;
	}


	public void setCoberturaPac(String coberturaPac) {
		this.coberturaPac = coberturaPac;
	}
	
	
	
	

}
