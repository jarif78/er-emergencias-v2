package Menues;

public class menuAyuda extends Menu {
	
	public static void display() {
        int opcion;
        ayudagral();
        System.out.println("\n\n*****Ayuda*****\nMenu Ayuda: 0) Salir \nEscriba opcion:");

        while (true) {
            //System.out.print(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");
            }}
    }

	public static void ayudagral() {
		System.out.println("Bienvenido TP Clinica Unpaz.\nEl programa incializa una instancia tipo Clinica en el main, tiene precargado en la clase AuxArmadoBase tres areas medicas, varias especialidades, pacientes"
				+ " turnos, prestaciones, medicos. \nSi desea inicializar una clinica desde cero puede cancelar en el main la linea 21 - Auxiliar.altas(unpaz) de esta manera el programa inicia sin ningun"
				+ " dato.\nPara el manejo se utiliza la informacion y toma de datos desde console.\nEl orden para inicializar es: \n1 - Area Medica \n2 - Medico \n3 - Especialidad\n4 - Paciente\n\nLuego de "
				+ " esto se encuentra en condiciones de asignar turnos, prestaciones al/los pacientes cargado/s");
	}

}
