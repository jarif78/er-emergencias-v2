package BaseClinica;

public class Medico extends Paciente{
	
	private String especialidadMed;


	public String getEspecialidadMed() {
		return especialidadMed;
	}
	
	public void setId() {
		int i = 0;
		for(int x = 0; x < c.getListaMedicos().size(); x++) {
			if(id<c.getListaMedicos().get(x).getid()) {
				i = c.getListaMedicos().get(x).getid();
			}
		}
		id = i + 1;
	}

	public void agregarMedico() {
		if(existeDNIMedico()){System.out.println("Error DNI ya ingresado");}
		else {c.getListaMedicos().add(this);}
	}
	
	public boolean existeDNIMedico() {
		boolean r = false;
        for(int x = 0; x < c.getListaMedicos().size(); x++ ) {
        	if(c.getListaMedicos().get(x).getDni()==dni) {
        		r = true;
        		x = c.getListaMedicos().size();
        	}
        }
        return r;
	}
	
	public static void borrarMedico(int dni) {
        for(int x = 0; x < c.getListaMedicos().size(); x++ ) {
        	if(c.getListaMedicos().get(x).getDni()==dni) {
        		c.getListaMedicos().remove(x);
        		x = c.getListaMedicos().size();
        	}
        }
	}

	public Medico(String nombreMedico, String especialidadMed, int dniMedico, String telMedico) {		// Pedimos la clinica para verificar si existe ya el medico
		super();
		nombre = nombreMedico;
		dni = dniMedico;
		telefono = telMedico;
		id = c.getListaMedicos().size() + 1;
		this.especialidadMed = especialidadMed;
		agregarMedico();
	}
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	public static String nombreMedicoPorID(int id) {
		String r = null;
		for(int x = 0; x<c.getListaMedicos().size(); x++) {
			if(id==c.getListaMedicos().get(x).getid()) {
				r = c.getListaMedicos().get(x).getNombre();
				x = c.getListaMedicos().size();
			}
		}
		return r;
	}
	
	public static String [] arrayMedicos() {
		String [] r = new String [c.getListaMedicos().size()];
		for (int i = 0; i < c.getListaMedicos().size(); i++) {
			r[i] = c.getListaMedicos().get(i).getid() + "  -  " + c.getListaMedicos().get(i).getEspecialidadMed()
					+ "  -  " + c.getListaMedicos().get(i).getNombre();
		}
		return r;
	}


}