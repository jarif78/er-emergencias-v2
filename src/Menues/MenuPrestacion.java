package Menues;

import javax.swing.JOptionPane;

import BaseClinica.Auxiliar;
import BaseClinica.Especialidad;
import BaseClinica.Paciente;
import Prestaciones.Prestacion;
import Prestaciones.Turno;

public class MenuPrestacion extends Menu {
	
	@SuppressWarnings({ })
	public static void display() {
		Turno t = new Turno();
        int opcion;
        String mensajeOpciones = "Menu Prestacion\n\n1) Prestacion \n2) Lista Prestaciones \n3) Pago Prestaciones \n0) Salir \n\nEscriba opcion:";
        String[] array = {"1 - Prestacion", "2 - Lista Prestaciones", "3 - Pago Prestaciones", "0 - Salir"};
        
        while (true) {
    		
        	Object o = Auxiliar.menuo(mensajeOpciones, "Menu Prestaciones", array);
    		opcion = Auxiliar.n(o);
            
    		switch (opcion) {
            	case 1:
            		boolean r = true;
            		int ide = 0;
            		o = Auxiliar.menuo("Atencion de pacientes\n\nSeleccione la especialidad a atender:                    ", "Prestaciones - Atencion a pacientes", c.mostrarEspecialidades());
                	ide = Auxiliar.n(o);
            		//LinkedList <Integer> idturnos = Prestacion.mostrarPrestacionesHoy(ide);
            		o = Auxiliar.menuo("Atencion " + Especialidad.nombreAreaPorID(ide) + "\n\nSeleccione el turno a atender: ", "Prestaciones - Atencion a pacientes" , Prestacion.mostrarPrestacionesHoy(ide));
            		int idt = Auxiliar.n(o);
            		t = t.cargarTurnoconId(idt);
            			if(t.isEstudio()) {
                			ide = JOptionPane.showOptionDialog(null,"Confirma ", "Ingreso Turno - Autorizacion Sobreturno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                			if(ide==0) new Prestacion (t);
            			} else {
            				System.out.print("Ingresar tratamiento/estudio/receta: "); 
            				String trat = datos.nextLine();
            				new Prestacion(t, trat);}
            			
            		break;
            	case 2:
            		Auxiliar.advertencia("Listado de Prestaciones realizadas\n\n" + c.mostrarPrestaciones(), "Prestaciones - Reportes");
        		break;
                case 3: 
            		r = true;
            		int dni = 0;
            		while(r) {
            			dni = Integer.parseInt(Auxiliar.menus("Prestaciones pendientes de abonar\n\nIngrese numero de DNI del paciente.\n\nDebe ingresarse el numero entero sin .(punto) ni , (coma):", "12345678"));
            			r = !Paciente.existeDNIPaciente(dni);
            			if(r)Auxiliar.advertencia("El DNI no es correcto, intente nuevamente", "Ingreso turno - Error DNI no dado de alta");
            		}
            			String [] resultado = Prestacion.prestacionImpagaPorDni(dni);
            			if(resultado.length==0) {
            				Auxiliar.advertencia("El paciente no tiene prestaciones impagas", "Prestaciones - Error paciente sin prestaciones impagas");
            			} else {            			
            			o = Auxiliar.menuo("Paciente : " + Paciente.nombrePacientexDni(dni) + "\n\nPrestaciones pendientes de pago del paciente:", "Prestaciones - Pago" , Prestacion.prestacionImpagaPorDni(dni));
            			int nro = Auxiliar.n(o); 
            			Prestacion p = new Prestacion();
            			p = p.prestacionPorId(nro);
            			String [] opciones = {"Efectivo", "Tarjeta de credito"};
            			nro = JOptionPane.showOptionDialog(null, "Paciente : " + Paciente.nombrePacientexDni(dni) + "\n\nSeleccione la forma de pago", "Prestaciones - Seleccion de pago", JOptionPane.DEFAULT_OPTION,
            					JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            			String pago = "";
            			if(nro == 0) pago = "Efectivo";
            			else pago = "Tarjeta de credito";
            			p.setFormaPago(pago);
            			}
            			break;
                case 0: Menu.display();
                default: System.out.println("Opcion no válida");
            }}


	}
}

