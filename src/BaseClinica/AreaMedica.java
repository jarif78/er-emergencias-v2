package BaseClinica;

public class AreaMedica {
	
	protected int id;
	protected String nombre;
	protected int idMedico;
	protected static Clinica c;
	
	public static void setC(Clinica clinica) {
		 c = clinica;
	}




	public AreaMedica() {}
	
	public AreaMedica(String nombreAreaMedica, int idMedicoCoordinador) {
		setIdAreaMedica();
		this.nombre = nombreAreaMedica;
		this.idMedico = idMedicoCoordinador;
		c.getListaArea().add(this);
	}
	
	
	public boolean existeArea() {
		boolean r = false;
		for(int x = 0; x<c.getListaArea().size() ; x++) {
			if(nombre.equals(c.getListaArea().get(x).getNombre())) {
				Auxiliar.advertencia("Error el Area " + nombre + " ya se encuentra ingresada.\n\nEl area medica no se ingresa.", "Area Medica - Error ingreso");
				r = true;
			}
		}
		return r;
	}
	
	public static String stringListadoAreasMedicas() {
		String r = "";
		for(AreaMedica a : c.getListaArea()) {
			r = r + "Id: " + a.id + "\t - Nombre: " + a.nombre + "\t Medico coordinador: " + Medico.nombreMedicoPorID(a.idMedico) + "\n";
		}
		return r;
	}
	
	public static void borrarArea(int id) {
		for(int x = 0; x<c.getListaArea().size() ; x++) {
			if(c.getListaArea().get(x).getId()==id) {
				c.getListaArea().remove(x);
			}
		}
	}
	
	public void setIdAreaMedica() {
		int i = 0;
		for(int x = 0; x < c.getListaArea().size(); x++) {
			if(id<c.getListaArea().get(x).getId()) {
				i = c.getListaArea().get(x).getId();
			}
		}
		id = i + 1;
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public int getIdMedico() {
		return idMedico;
	}
	
	public static String nombreAreaPorID(int id) {
		String r = "";
		for(int x = 0; x<c.getListaArea().size(); x++) {
			if(id==c.getListaArea().get(x).getId()) {
				r = c.getListaArea().get(x).getNombre();
			}
		}
		return r;
	}
	
	
	

}
