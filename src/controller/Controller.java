package controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.*;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Corre el sistema mediante la consola 
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Comparendo resp = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			switch(option){
			case 1:
				//					view.printMessage("--------- \nCrear Arreglo \nDar dar ruta del archivo: ");
				//				    String ruta = lector.next();
				modelo = new Modelo(); 
				modelo.cargarDatos("./data/comparendos_dei_2018_small.geojson");
				view.printMessage("Comparendos cargados");
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				break;

			case 2:

				view.printMessage("Ingrese nombre de la localidad:");
				String l = lector.next();
				view.printComparendo(modelo.unoA(l));						
				break;

			case 3:
				view.printMessage("Ingrese fecha en formato <yyyy/mm/dd>");
				String f = lector.next();
				try
				{
					Date fecha = parser.parse(f);
					System.out.println("Fecha es "+fecha);
					ListaEncadenada dosA = modelo.dosA(fecha);
					view.printLista(dosA);
					//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				}
				catch(Exception e)
				{
					System.out.println("No ingresó el formato correcto");
				}
				break;

			case 4:
				view.printMessage("Dar primera fecha a buscar en formato <yyyy/mm/dd>: ");
				String f2 = lector.next();
				view.printMessage("Dar segunda fecha a buscar en formato <yyyy/mm/dd>: ");
				String f3 = lector.next();
				try
				{
					Date fecha2 = parser.parse(f2);
					Date fecha3 = parser.parse(f3);
					Cola col = modelo.tresA(fecha2, fecha3);
					view.printResultFechas(col, f2, f3);
				}
				catch(Exception e)
				{
					System.out.println("No ingresó el formato correcto");
				}
				break;

			case 5: 
				view.printMessage("Dar localidad a buscar (Con palabras separadas por guión): ");
				String[] locs = lector.next().split("-");
				String loc;
				if(locs.length>1)
				{
					loc = locs[0]+" "+locs[1];
				}
				else
				{
					loc = locs[0];
				}
				view.printMessage("Dar fecha inferior a buscar en formato <yyyy/mm/dd>: ");
				String f4 = lector.next();
				view.printMessage("Dar fecha superior a buscar en formato <yyyy/mm/dd>: ");
				String f5 = lector.next();
				try
				{
					Date fecha4= parser.parse(f4);
					Date fecha5 = parser.parse(f5);
					Cola col2 = modelo.unoC(loc,fecha4, fecha5);
					view.printMessage("Comparación de comparendos en "+loc+" del "+f4+" al "+f5);
					view.printResultLoc(col2);
				}
				catch(Exception e)
				{
					System.out.println("No ingresó el formato correcto");
				}
				break;

			case 6:

				view.printMessage("Dar numero de infracciones a registrar: ");
				int infr = lector.nextInt();
				view.printMessage("Dar fecha inferior a buscar en formato <yyyy/mm/dd>: ");
				String f6 = lector.next();
				view.printMessage("Dar fecha superior a buscar en formato <yyyy/mm/dd>: ");
				String f7 = lector.next();
				try
				{
					Date fecha6= parser.parse(f6);
					Date fecha7 = parser.parse(f7);
					Cola col3 = modelo.dosC(infr, fecha6, fecha7);
					view.printMessage("Ranking de las "+infr+" mayores infracciones del "+f6+" al "+f7);
					view.printResultLoc(col3);
				}
				catch(Exception e)
				{
					System.out.println("No ingresó el formato correcto");
				}
				break;

			case 7:

				view.printHist(modelo.tresC());
				break;

			case 8: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			case 9:

				break;

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
