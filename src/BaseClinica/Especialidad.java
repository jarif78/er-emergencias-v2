package BaseClinica;

import java.util.ArrayList;

import Menues.MenuTurno;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Especialidad extends AreaMedica{

	private int IdAreaMedica;
	private ArrayList <Integer> dias = new ArrayList(7);
	private String horaInicio;
	private String horaFin;
	
	public Especialidad() {
		super();
	}
	
	public Especialidad(String nomEsp, int idAreaMedica, int idMed, ArrayList dias, String horaInicio, String horaFin) {
		super();
		setId();
		nombre = nomEsp;
		IdAreaMedica = idAreaMedica;
		idMedico = idMed;
		this.dias = dias;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		
		agregarEspecialidad();
	}
	
	public void setIdAreaMedica(int idAreaMedica) {
		IdAreaMedica = idAreaMedica;
	}
	public void setDias(ArrayList dias) {
		this.dias = dias;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public int getIdAreaMedica() {
		return IdAreaMedica;
	}
	public ArrayList getDias() {
		return dias;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}

	
	public void agregarEspecialidad() {
		if(existeEspecialidad()){System.out.println("Error Especialidad ya ingresado");}
		else if(controlHoras()){c.getListaEspecialidad().add(this);}
	}
	
	public boolean existeEspecialidad() {
		boolean r = false;
        for(int x = 0; x < c.getListaEspecialidad().size(); x++ ) {
        	if(c.getListaEspecialidad().get(x).getNombre().equals(this.nombre)) {
        		r = true;
        		x = c.getListaEspecialidad().size();
        	}
        }
        return r;
	}

	
	public static boolean existeIdEspecialidad(int i) {
		boolean r = false;
        for(int x = 0; x < c.getListaEspecialidad().size(); x++ ) {
        	if(c.getListaEspecialidad().get(x).getId()==i) {
        		r = true;
        		x = c.getListaEspecialidad().size();
        	}
        }
        return r;
	}

	public void setId() {
		int i = 0;
		for(int x = 0; x < c.getListaEspecialidad().size(); x++) {
			if(id<c.getListaEspecialidad().get(x).getId()) {
				i = c.getListaEspecialidad().get(x).getId();
			}
		}
		id = i + 1;
	}
	
	public static void BorrarEspecialidad(int id) {
        for(int x = 0; x < c.getListaEspecialidad().size(); x++ ) {
        	if(c.getListaEspecialidad().get(x).getId()==id) {
        		c.getListaEspecialidad().remove(x);
        	}
        }
	}
	
	public void cargarEspecialidadPorID(int id) {
		for(int x = 0; x<c.getListaEspecialidad().size(); x++) {
			if(id==c.getListaEspecialidad().get(x).getId()) {
				this.id = id;
				nombre = c.getListaEspecialidad().get(x).getNombre();
				IdAreaMedica = c.getListaEspecialidad().get(x).getIdAreaMedica();
				idMedico = c.getListaEspecialidad().get(x).getIdMedico();
				dias = c.getListaEspecialidad().get(x).getDias();
				horaInicio = c.getListaEspecialidad().get(x).getHoraInicio();
				horaFin = c.getListaEspecialidad().get(x).getHoraFin();
				x = c.getListaEspecialidad().size();
			}
		}
	}

	
	public void infoEspecialidad(int id) {
		String n = nombre;
		String m = Medico.nombreMedicoPorID(idMedico);
		String a = AreaMedica.nombreAreaPorID(IdAreaMedica);
		ArrayList d = dias;
		String i = horaInicio;
		String f = horaFin;
		System.out.println("Especialidad: "+n+"\nArea: "+a+"\nMedico: "+m+"\nDias: "+d+"\nHorario: "+i+" hasta: "+f);
		
	}
	
	public boolean controlHoras() {
		boolean r = false;
		int hd = Integer.parseInt(horaInicio.substring(0, 2));
		int hh = Integer.parseInt(horaFin.substring(0, 2));
		int md = Integer.parseInt(horaInicio.substring(3, 5));
		int mh = Integer.parseInt(horaFin.substring(3, 5));
		if(hd<hh) r = true;
		if(hd==hh&&md<mh) r = true;
		if(r==false) System.out.println("Error el horario de inicio no puede ser inferior al horario de final - No se ingresa especialidad");
		return r;
	}
	
	public boolean controlHoras(String hora) {
		boolean r = false;
		int ha = Integer.parseInt(hora.substring(0, 2));		// hora a analizar
		int ma = Integer.parseInt(hora.substring(3, 5));		// minuto a analizar
		int hd = Integer.parseInt(horaInicio.substring(0, 2));	// hora inicio
		int hh = Integer.parseInt(horaFin.substring(0, 2));		// hora fin
		int mh = Integer.parseInt(horaFin.substring(3, 5));		// minuto final
		if(ha>=hd&&ha<hh) r = true;
		if(ha==hh&&ma<mh) r = true;
		if(r==false) Auxiliar.advertencia("No se encuentra dentro del horario de atencion\n\nReintentar", "Ingreso Turno - Error fuera de rango horario");
		return r;
	}

}
