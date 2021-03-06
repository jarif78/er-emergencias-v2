package Menues;

import BaseClinica.Auxiliar;
import BaseClinica.Medico;

public class menuMedico extends Menu {
	
	@SuppressWarnings("resource")
	public static void display() {
        int opcion;
        String mensajeOpciones = "Menu Medicos\n\n1) Alta Medico \n2) Baja Medico \n3) Listado Medico\n0) Salir \n\nSeleccione opcion:";
        String[] array = {"1 - Alta Medico", "2 - Baja Medico", "3 - Listado Medicos", "0 - Salir"};
        while (true) {

        	Object o = Auxiliar.menuo(mensajeOpciones, "Menu Medicos", array);
    		opcion = Auxiliar.n(o);
    		
            switch (opcion) {
            	case 1: 
            		boolean r = true;
            		String n = Auxiliar.menus("Ingrese nombre del Medico:", "ej. Pedro Perez");
            		int dni = 0;
            		while (r) {
            			String dnis = Auxiliar.menus("Ingrese DNI:", "12345678");
            			try {
            				dni = Integer.parseInt(dnis);
            				Medico m = new Medico();
            				m.setDni(dni);
            				if(m.existeDNIMedico()) {
            					Auxiliar.advertencia("El DNI: " + dni +"\n\n Ya se encuentra ingresado, nombre: " + Medico.nombrePacientexDni(dni), "Alta Medico - Error DNI Existente");
            				} else r = false;
            				m=null;
            			} catch (Exception e) {
            				Auxiliar.advertencia("Formato de DNI invalido, el numero no debe contener . (punto) ni , (coma)\nIngresar unicamente numero.", "Alta paciente - Error en DNI");
            				r = true;
            			}
            			
            		}
            		String esp = Auxiliar.menus("Ingrese Especialidad del medico:", "ej. Traumatologo");
            		String tel = Auxiliar.menus("Ingrese Numero de telefono:", "ej. 15-4444-5555");
            		new Medico(n, esp, dni, tel);
            		break;
                case 2: 
                	r = true;
                	dni = 0;
            		while(r) {
            			String dnis = Auxiliar.menus("Ingrese numero de DNI del Medico.\n\nDebe ingresarse el numero entero sin .(punto) ni , (coma):", "12345678");
            			if(dnis == null) {
            				Menu.display();
            			}
            			dni = Integer.parseInt(dnis);
            			r = !Medico.existeDNIPaciente(dni);
            			if(r)Auxiliar.advertencia("El DNI no es correcto, intente nuevamente", "Ingreso turno - Error DNI no dado de alta");
            		}
                	Medico.borrarMedico(dni);
                	break;
                case 3:
                	Auxiliar.advertencia("Listado de Medicos\n\n" + c.mostrarMedicos(), "Reporte - Listado de pacientes");
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no v�lida");   
        }}
    }


}
