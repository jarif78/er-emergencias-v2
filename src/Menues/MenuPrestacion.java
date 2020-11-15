package Menues;

import java.util.LinkedList;

import BaseClinica.Especialidad;
import BaseClinica.Paciente;
import Prestaciones.Prestacion;
import Prestaciones.Turno;

public class MenuPrestacion extends Menu {
	
	@SuppressWarnings({ "unused", "unchecked" })
	public static void display() {
		Turno t = new Turno();
        int opcion;
        String mensajeOpciones = "\n*****Prestaciones*****\nMenu Prestaciones: ->1) Prestacion ->2) Lista Prestaciones 3) Pago Prestaciones ->0) Salir \nEscriba opcion:";
        
        while (true) {
            System.out.print(mensajeOpciones);
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 1:
            		c.mostrarEspecialidades();
            		boolean r = true;
            		int ide = 0;
            		while(r) {
                		System.out.print("ID Especialidad: "); ide = scanner.nextInt();
            			r = !Especialidad.existeIdEspecialidad(ide);
            		}
            		LinkedList <Integer> idturnos = Prestacion.mostrarPrestacionesHoy(ide);
            		System.out.print("Ingrese nro Turno: "); int idt = scanner.nextInt();
            		if(idturnos.contains(idt)) {
            			t = t.cargarTurnoconId(idt);
            			if(t.isEstudio()) {
            				System.out.print("Confirma Prestacion s-n: "); 
            				char sn = scanner.next().charAt(0);
            				if(sn=='s') new Prestacion (t);
            			} else {
            				System.out.print("Ingresar tratamiento/estudio/receta: "); 
            				String trat = datos.nextLine();
            				new Prestacion(t, trat);
            		}} else { System.out.println("No es valido el nro de turno ingresado");}
            			
            		break;
            	case 2:
            		c.mostrarPrestaciones();
        		break;
                case 3: 
            		r = true;
            		int dni = 0;
            		while(r) {
                		System.out.print("Ingrese DNI: "); dni = scanner.nextInt();
            			r = !Paciente.existeDNIPaciente(dni);
            		}
            			Prestacion.prestacionImpagaPorDni(dni);
            			System.out.print("Ingrese Nro Prestacion a abonar: "); int nro = datos.nextInt();
            			Prestacion p = new Prestacion();
            			p = p.prestacionPorId(nro);
            			System.out.println(p.getTratamiento());
            			//datos;
            			System.out.print("Ingrese forma de pago (Efectivo o Tarjeta): "); String pago = datos.nextLine();
            			p.setFormaPago(pago);
            			break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");
            }}


	}
}

