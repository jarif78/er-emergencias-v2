package Menues;
import BaseClinica.Auxiliar;
import BaseClinica.Paciente;

public class menuPaciente extends Menu {
	
	public static void display() {
        int opcion;
        String mensajeOpciones = "Menu Pacientes\n\n1) Alta Paciente \n2) Baja Paciente \n3) Listado Pacientes \n0) Salir \n\nSeleccione opcion:";
        String[] array = {"1 - Alta Paciente", "2 - Baja Paciente", "3 - Listado Pacientes", "0 - Salir"};
        while (true) {

        	Object o = Auxiliar.menuo(mensajeOpciones, "Menu Pacientes", array);
    		opcion = Auxiliar.n(o);
    		
            switch (opcion) {
            	case 1: 
            		boolean r = true;
            		String n = Auxiliar.menus("Ingrese nombre del paciente:", "ej. Pedro Perez");
            		int dni = 0;
            		while (r) {
            			String dnis = Auxiliar.menus("Ingrese DNI:", "12345678");
            			try {
            				dni = Integer.parseInt(dnis);
            				if(Paciente.existeDNIPaciente(dni)) {
            					Auxiliar.advertencia("El DNI: " + dni +"\n\n Ya se encuentra ingresado, nombre: " + Paciente.nombrePacientexDni(dni), "Alta paciente - Error DNI Existente");
            				} else r = false;
            			} catch (Exception e) {
            				Auxiliar.advertencia("Formato de DNI invalido, el numero no debe contener . (punto) ni , (coma)\nIngresar unicamente numero.", "Alta paciente - Error en DNI");
            				r = true;
            			}
            			
            		}
            		String t = Auxiliar.menus("Ingrese Numero de telefono:", "ej. 15-4444-5555");
            		String cob = Auxiliar.menus("Ingrese Numero de telefono:", "Obra social");
            		new Paciente(n, dni, t, cob);
            		break;
                case 2: 
                	r = true;
                	dni = 0;
            		while(r) {
            			dni = Integer.parseInt(Auxiliar.menus("Ingrese numero de DNI del paciente.\n\nDebe ingresarse el numero entero sin .(punto) ni , (coma):", "12345678"));
            			r = !Paciente.existeDNIPaciente(dni);
            			if(r)Auxiliar.advertencia("El DNI no es correcto, intente nuevamente", "Ingreso turno - Error DNI no dado de alta");
            		}
                	Paciente.BorrarPaciente(dni);
                	break;
                case 3:
                	Auxiliar.advertencia("Listado de paciente\n\n" + c.mostrarPacientes(), "Reporte - Listado de pacientes");
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }


}
