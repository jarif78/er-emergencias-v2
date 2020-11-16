package Menues;

import BaseClinica.Auxiliar;
import BaseClinica.Medico;

public class menuReportes extends Menu {
	
	public static void display() {
		
        int opcion;
        String mensajeOpciones = "Menu Reportes: \n\n1) Prestaciones por medico \n2) Estudios Realizados \n0) Salir\n\n";
        String[] array = {"1 - Prestaciones por medico", "2 - Estudios Realizados", "0 - Salir"};

        while (true) {
        	Object o = Auxiliar.menuo(mensajeOpciones, "Reportes - Director", array);
            opcion = Auxiliar.n(o);
            switch (opcion) {
            	case 1:
            		o = Auxiliar.menuo("Seleccione el medico", "Reportes - Prestaciones por Medico", Medico.arrayMedicos());
            		int n = Auxiliar.n(o);
            		Auxiliar.advertencia("Reporte prestaciones realizadas por: " + Medico.nombreMedicoPorID(n) + "\n\n" + c.mostrarPrestacionesporMedico(n), "Reportes - Prestaciones por medico");
            		break;
            	case 2:
            		Auxiliar.advertencia("Reporte estudios realizados \n\n" + c.listadoEstudiosRealizados() , "Reportes - Estudios realizados");
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }


}
