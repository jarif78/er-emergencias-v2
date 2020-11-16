package BaseClinica;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import Prestaciones.Prestacion;
import Prestaciones.Turno;

@SuppressWarnings("rawtypes")
public class Clinica {
	private LinkedList<AreaMedica> listaArea = new LinkedList<AreaMedica>();
	private LinkedList<Paciente> listaPacientes = new LinkedList<Paciente>();
	private LinkedList<Especialidad> listaEspecialidad = new LinkedList<Especialidad>();
	private LinkedList<Medico> listaMedicos = new LinkedList<Medico>();
	private LinkedList<Turno> listaTurnos = new LinkedList<Turno>();
	private LinkedList<Prestacion> listaPrestacion = new LinkedList<Prestacion>();

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

	public String mostrarMedicos() {
		String r = "";
		for (int i = 0; i < listaMedicos.size(); i++) {
			r = r + listaMedicos.get(i).getid() + "  -  " + listaMedicos.get(i).getEspecialidadMed()
					+ "  -  " + listaMedicos.get(i).getNombre()+"\n";
		}
		return r;
	}

	public String mostrarPacientes() {
		String r = "";
		for (int i = 0; i < listaPacientes.size(); i++) {
			r = r + listaPacientes.get(i).getid() + " -  " + listaPacientes.get(i).getDni() + "  -  "
					+ listaPacientes.get(i).getNombre() + "  -  " + listaPacientes.get(i).getTelefono() + "  -  "
					+ listaPacientes.get(i).getCoberturaPac() + "\n";
		}
		return r;
	}

	public String [] mostrarAreas() {
		int x = listaArea.size();
		String r[] = new String[x];
		x = 0;
		for (int i = 0; i < listaArea.size(); i++) {
			r[x] = listaArea.get(i).getId() + "  -  " + listaArea.get(i).getNombre();
			x++;
		}
		return r;
	}

	public String[] mostrarEspecialidades() {
		String r[] = new String[listaEspecialidad.size()];
		int x = 0;
		for (Especialidad e : listaEspecialidad) {
			r[x] = e.id + " - " + e.nombre;
			x++;
		}

		return r;
	}

	public String mostrarPrestaciones() {
		String r = "";
		Locale locale = new Locale("es", "ES");
		for (Prestacion p : listaPrestacion) {
			String f = new SimpleDateFormat("EEEE dd/MM/yyyy", locale).format(p.getFecha());
			r = r + p.getId() + "\t" + p.getDniPaciente() + "  -  " + f + "  -  " + p.getTratamiento() + "\n";
		}
		return r;
	}

	public String mostrarPrestacionesporMedico(int id) {
		String r = "";
		for (Especialidad e : listaEspecialidad) {
			if (e.idMedico == id) {
				for (Prestacion p : listaPrestacion) {
					if (p.getIdEspecialidad() == e.id) {
						r = r + "Id:" + p.getId() + "  -  DNI Paciente:" + p.getDniPaciente() + "  -  Fecha Prestacion:" + p.getFecha() + "\n";
					}
				}
			}
		}
		return r;
	}

	public String listadoEstudiosRealizados() {
		String r = "";
		for (Prestacion p : listaPrestacion) {
			if (p.isEstudio()) {
				r = r + "Id: " + p.getId() + "  -  Paciente DNI: " + p.getDniPaciente() + "  - Fecha: " + p.getFecha() + "\n";
			}
		}
		return r;
		
	}

}
