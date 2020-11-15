package Menues;

public class menuReportes extends Menu {
	
	public static void display() {
        int opcion;
        String mensajeOpciones = "**Reportes** - \nReportes: ->1) Prestaciones por medico ->2) Estudios Realizados ->0) salir";
        while (true) {
        	System.out.println(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 1:
            		c.getListaMedicos();
            		System.out.print("Ingrese Nro Medico: "); int n = scanner.nextInt();
            		c.mostrarPrestacionesporMedico(n);
            		break;
                case 2: 
                	c.listadoEstudiosRealizados();
                	break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");   
        }}
    }


}
