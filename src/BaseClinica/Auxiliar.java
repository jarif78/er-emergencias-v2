package BaseClinica;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BaseClinica.*;
import Menues.Menu;
import Menues.MyIcon;
import Prestaciones.Prestacion;
import Prestaciones.Turno;

public class Auxiliar {
	
	public static void altas(Clinica c) {
		
		new Paciente("a perez", 46658340, "011-15-4444-1111", "Particular");
		new Paciente("b perez", 46658341, "011-15-4444-2222", "Obra Social");
		new Paciente("c perez", 46658342, "011-15-4444-3333", "Prepaga");
		new Paciente("d perez", 46658343, "011-15-4444-4444", "Particular");
		new Paciente("e perez", 46658344, "011-15-4444-5555", "Obra Social");
		new Paciente("Ricardo gonzalez", 46658349, "011-156-877-87", "Obra Social");
		
		new Medico("Rene Favaloro", "Cirujano", 16658349, "02320-111111");
		new Medico("maria Clinico", "Clinico", 26658340, "02320-222222");
		new Medico("maria Traumatologo", "Traumatologo", 26658341, "02320-111111");
		new Medico("maria Oftalmologo", "Oftalmologo", 26658342, "02320-222222");
		new Medico("Rocio Gonzalez", "Radiologo", 26658343, "02320-333333");
		
	    ArrayList<Integer> a = new ArrayList<>();
	    Integer[] array = { 1, 3, 4, 5, 6 , 7 };
	    Collections.addAll(a, array);

	    ArrayList<Integer> b = new ArrayList<>();
	    Integer[] arrayb = { 3, 4, 5 };
	    Collections.addAll(b, arrayb);

		new Especialidad("Clinico", 3, 2, a, "08:00", "18:00");
		new Especialidad("Traumatologia", 3, 3, a, "08:00", "16:00");
		new Especialidad("Cirugia General", 1, 1, b, "08:00", "14:00");
		new Especialidad("Radiologia", 2, 5, a, "08:00", "20:00");
		new Especialidad("Analisis Clinicos", 2, 5, a, "06:00", "18:00");
		new Especialidad("Tomografia", 2, 5, a, "12:00", "18:00");
		new Especialidad("Oftalmologia", 3, 4, a,  "08:00", "16:00");
		
		new AreaMedica("Area Quirurgica", 1);
		new AreaMedica("Estudios", 5);
		new AreaMedica("Area Clinica", 2);
		
		Date fecha1 = new Date();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date f1 = new Date();
		Date f2 = new Date();
		try {
			f1 = format.parse("05/11/2020");
			f2 = format.parse("02/11/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Turno t1 = new Turno(46658340, 3, f1, "08:00", "08:30");
		Turno t2 = new Turno(46658341, 1, f1, "08:00", "08:30");
		Turno t3 = new Turno(46658342, 4, f1, "12:00", "12:30");
		Turno t4 = new Turno(46658343, 6, f2, "12:30", "12:40");
		Turno t5 = new Turno(46658344, 2, f2, "13:30", "14:00");
		Turno t6 = new Turno(46658340, 4, f2, "09:00", "09:30");
		
		new Turno(46658341, 2, fecha1, "10:00", "10:30");
		new Turno(46658342, 3, fecha1, "11:00", "11:30");
		new Turno(46658343, 1, fecha1, "12:00", "12:30");
		new Turno(46658343, 3, fecha1, "13:00", "13:30");
		new Turno(46658342, 2, fecha1, "14:00", "14:30");
		new Turno(46658343, 3, f1, "15:00", "15:30");
	
		new Prestacion (t5);
		new Prestacion (t6);
		new Prestacion (t3);
		new Prestacion (t4);
		new Prestacion (t1, "Realizar analisis de sangre");
		new Prestacion (t2, "Angina - Amoxicilina");
	}

	public static Object menuo(String texto, String titulo, String [] array) {
		MyIcon icon = new MyIcon();
		Object o = JOptionPane.showInputDialog(null, texto, titulo + "  - Unpaz - POO", JOptionPane.QUESTION_MESSAGE,icon, array, array[0]);
		return o;
	}
	
	public static int n (Object o){ 
		int n = 0;
    	String a = (String) o;
    	if(a==null) {
    		Menu.display();
    	}
    	n = Integer.parseInt(a.substring(0, a.indexOf(" ")));
		return n;
	} 
	
	public static String menus(String texto, String pordefecto) {
		MyIcon icon = new MyIcon();
		
		Object o = JOptionPane.showInputDialog(null, texto , "TPOO Clinica - Grupo 6" , JOptionPane.INFORMATION_MESSAGE,icon, null, pordefecto);        
		
		if(o==null) Menu.display();
		
		String r = o.toString();
		
		return r;
		//return JOptionPane.showInputDialog(texto, pordefecto, icon);
	}

	public static String hoyString() {
		LocalDate hoyd = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return hoyd.format(formatter);
	}
	
	public static void advertencia(String texto, String titulo) {
		MyIcon icon = new MyIcon();
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE, icon);
	}
	
}


