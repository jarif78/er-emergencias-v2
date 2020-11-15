package BaseClinica;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import Prestaciones.Prestacion;
import Prestaciones.Turno;

public class Clinica {
	private LinkedList <AreaMedica> listaArea = new LinkedList <AreaMedica>();
	private LinkedList <Paciente> listaPacientes = new LinkedList <Paciente>();
	private LinkedList <Especialidad>listaEspecialidad = new LinkedList <Especialidad>();
	private LinkedList <Medico> listaMedicos = new LinkedList <Medico>();
	private LinkedList <Turno> listaTurnos = new LinkedList <Turno>();
	private LinkedList <Prestacion> listaPrestacion = new LinkedList <Prestacion>();
	
	
	public LinkedList<Prestacion> getListaPrestacion() {
		return listaPrestacion;
	}

	public LinkedList<Medico> getListaMedicos() {
		return listaMedicos;
	}

	public LinkedList<AreaMedica> getListaArea() {
		return listaArea;
	}

	public LinkedList<Paciente> getListaPacientes() {
		return listaPacientes;
	}

	public LinkedList<Especialidad> getListaEspecialidad() {
		return listaEspecialidad;
	}

	public LinkedList<Turno> getListaTurnos() {
		return listaTurnos;
	}
	
	
	
	public void mostrarMedicos() {
        for(int i = 0; i < listaMedicos.size(); i++){
        		System.out.println(listaMedicos.get(i).getid() + "\t - " + listaMedicos.get(i).getEspecialidadMed() + "\t\t - " + listaMedicos.get(i).getNombre());
        }
    }

	public void mostrarPacientes() {
        for(int i = 0; i < listaPacientes.size(); i++){
        		System.out.println(listaPacientes.get(i).getid() + "\t - " + listaPacientes.get(i).getDni() + " - " + listaPacientes.get(i).getNombre() + " - " +listaPacientes.get(i).getTelefono()+ " - " +listaPacientes.get(i).getCoberturaPac());
        }
    }

	public void mostrarAreas() {
        for(int i = 0; i < listaArea.size(); i++){
        		System.out.println(listaArea.get(i).getId() + "\t - " + listaArea.get(i).getNombre());
    }}
	
	public String mostrarEspecialidades() {
		String r = "";
        for(int i = 0; i < listaEspecialidad.size(); i++){
        		r = r + (listaEspecialidad.get(i).getId() + "\t - " + listaEspecialidad.get(i).getNombre()) + "\n";
        }
        return r;
	}
	
		
	

	public void mostrarPrestaciones() {
		Locale locale = new Locale ( "es" , "ES" );
		for(Prestacion p : listaPrestacion) {
			String f = new SimpleDateFormat("EEEE dd/MM/yyyy", locale).format(p.getFecha());
			System.out.println(p.getId() + "\t" + p.getDniPaciente() + "\t" + f
					+ "\t" + p.getTratamiento());

	}}
		
		public void mostrarPrestacionesporMedico(int id) {
			for(Especialidad e : listaEspecialidad) {
				if(e.idMedico==id) {
					for(Prestacion p : listaPrestacion) {
						if(p.getIdEspecialidad()==e.id) {
							System.out.println(p.getId()+"\t"+p.getDniPaciente()+"\t"+p.getFecha());
						}
					}
		}}}
		
		public void listadoEstudiosRealizados () {
			for(Prestacion p : listaPrestacion) {
				if(p.isEstudio()) {
					System.out.println(p.getId()+"\t"+p.getDniPaciente()+"\t"+p.getFecha());
				}
			}
		}
		
		

}
