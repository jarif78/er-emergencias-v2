package Menues;

import BaseClinica.AreaMedica;
import BaseClinica.Auxiliar;
import BaseClinica.Medico;

public class MenuArea extends Menu{

	public static void menuArea() {
		
        int opcion;
        String mensajeOpciones = "Menu Area Medica: \n\n1) Alta \n2) Baja \n3) Listado Area \n0) Salir\n";
        String[] array = {"1 - Alta Area", "2 - Baja Area", "3 - Listado Area", "0 - Salir"};

        while (true) {
        	Object o = Auxiliar.menuo(mensajeOpciones, "Area Medica", array);
        	opcion = Auxiliar.n(o);
            switch (opcion) {
            	case 1: 
            		String n = Auxiliar.menus("Ingrese nombre del Area:", "Area Clinica");
            		o = Auxiliar.menuo("Seleccione el medico Coordinador del area", "Area Medica: " + n, Medico.arrayMedicos());
            		int id = Auxiliar.n(o);
            		new AreaMedica(n, id);
            		break;
                case 2: 
                	o = Auxiliar.menuo("Seleccione el Area medica a borrar", "Area Medica - Borrar", c.mostrarAreas());
                	id = Auxiliar.n(o);
                	AreaMedica.borrarArea(id);
                	break;
                case 3:
                	Auxiliar.advertencia("Listado de Areas Medicas:\n\n" + AreaMedica.stringListadoAreasMedicas(), "Reporte - Area medica");
                	
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
	}
}
