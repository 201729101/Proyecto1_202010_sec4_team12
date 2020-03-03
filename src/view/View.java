package view;

import java.util.Date;

import model.data_structures.Cola;
import model.data_structures.Comparendo;

import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    /**
	     *Imprime el menú 
	     */
		public void printMenu()
		{
			System.out.println("1. Cargar Comaprendos");
			System.out.println("2. 1A");
			System.out.println("3. 2A");
			System.out.println("4. 3A");
			System.out.println("5. 1C");
			System.out.println("6. 2C");
			System.out.println("7. 3C");
			System.out.println("8. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		/**
		 * Imprime un mensaje recibido por parámetro
		 * @param mensaje mensaje a imprimir
		 */
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printComparendo(Comparendo inf)
		{
			System.out.println("[");
			System.out.println("ID: "+inf.getId());
			System.out.println("Fecha: "+inf.getFecha());
			System.out.println("Medio de detección: " + inf.getMedio());
			System.out.println("Clase de vehículo: "+inf.getClase());
			System.out.println("Tipo de servicio: "+inf.getTipo());
			System.out.println("Infracción: "+inf.getInfr());
			System.out.println("Descripción: "+inf.getDesc());
			System.out.println("Localidad: "+inf.getLocalidad());
			System.out.println("Coordenadas: "+inf.getLatitud()+" , "+inf.getLongitud());
			System.out.println("]");
		}
		
		/**
		 * Imprime todo un modelo recibido por parámetro
		 * @param modelo Modelo a imprimir
		 */
		public void printLista(ListaEncadenada lista)
		{
			System.out.println("Comparendos buscados: {");
			for(Nodo n = lista.darPrimero() ; n!=null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				printComparendo(inf);
			}
			System.out.println("}");
		}
		
		public void printResultFechas(Cola col, String fecha1 , String fecha2)
		{
			System.out.println("Infraccion  |  "+fecha1+"   |  "+fecha2+"  |");
			while(col.darTamano()>0)
			{
				String[] men = ((String) col.eliminar()).split(",");
				
				if(men[1].equals("1"))
				{
					String[] otro = ((String) col.eliminar()).split(",");
					System.out.println(men[0]+"       |"+men[2]+"        |"+otro[2]);
				}
				else
				{
					String[] otro = ((String) col.eliminar()).split(",");
					System.out.println(men[0]+"       |"+otro[2]+"        |"+men[2]);
				}
			}
		}
		
		public void printResultLoc(Cola col)
		{
			System.out.println("Infraccion  |  # Comparendos  |");
			while(col.darTamano()>0)
			{
				String[] men = ((String) col.eliminar()).split(",");
				System.out.println(men[0]+"         |"+men[1]);
			}
		}
		
		public void printHist(Cola col)
		{
			System.out.println("Aproximación del número de comparendos por localidad.");
			while(col.darTamano()>0)
			{
				String[] men = ((String) col.eliminar()).split(",");
				int N = (int) Integer.parseInt(men[1])/50;
				String ast = "";
				for(int i = 0 ; i< N ; i++)
				{
					ast = ast + "*";
				}
				System.out.println(men[0]+"-----|"+ast);
			}
		}
}
