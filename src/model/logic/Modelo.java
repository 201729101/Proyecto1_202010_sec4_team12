package model.logic;

import java.util.Date;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import model.data_structures.Cola;
import model.data_structures.Comparendo;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.Pila;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Estrutura de datos que tendrá los comparendos
	 */
	private ListaEncadenada lista;

	private Cola cola;

	private Pila pila;

	/**
	 * Constructor
	 */
	public Modelo ()
	{
		lista = new ListaEncadenada();
	}

	/**
	 * Inicia la lectura del archivo JSON y rellena la lista
	 * @param path, ruta del archivo a leer
	 */
	public ListaEncadenada<Comparendo> cargarDatos(String PATH) {

		//TODO Cambiar la clase del contenedor de datos por la Estructura de Datos propia adecuada para resolver el requerimiento 
		lista = new ListaEncadenada<Comparendo>();

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, longitud, latitud);
				lista.agregarFinal(c);;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return lista;	
	}

	/**
	 * Busca una infracción en la lista con un ID recibido por parámetro
	 * @param pId ID de la infracción a buscar
	 * @return Infracción buscada, null si no es encontrada
	 */
	public Comparendo buscar(int pId)
	{
		Comparendo buscada = null;
		for(Nodo e = lista.darPrimero() ; e!=null ; e = e.darSiguiente())
		{
			Comparendo actual = (Comparendo) e.darElemento();

			if(actual.getId()==pId)
			{
				return actual;
			}
		}
		return null;
	}

	/**
	 * Elimina y retorna una infracción con un id recibido por parámetro
	 * @param pId ID de la infraccion a eliminar
	 * @return infraccion eliminada, null si no está la infraccion
	 */
	public Comparendo eliminar(int pId)
	{
		Comparendo inf = buscar(pId);
		return (Comparendo) lista.eliminar(inf);
	}

	/**
	 * Retona el tamaño de la lista
	 * @return tamaño de la lista
	 */
	public int darTamano()
	{
		return lista.darTamano();
	}

	/**
	 * Agrega una infracción recibida por parámetro al final de la lista
	 * @param pInf infracción a agregar
	 */
	public void agregarFinal(Comparendo pInf)
	{
		lista.agregarFinal(pInf);
	}

	/**
	 * Agrega una infracción rebida por parámetro al inicio de la lista
	 * @param pInf nfracción a agregar
	 */
	public void agregarInicio(Comparendo pInf)
	{
		lista.agregarInicio(pInf);
	}

	/**
	 * retorna la lista encadenada
	 * @return lista encadenada
	 */
	public ListaEncadenada darLista() {
		return lista;
	}

	public Comparendo unoA(String pLoc)
	{
		try
		{
			for(Nodo n = lista.darPrimero() ; n!=null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getLocalidad().equals(pLoc))
				{
					return inf;
				}
			}
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ListaEncadenada dosA(Date pFecha)
	{
		ListaEncadenada alist = new ListaEncadenada();

		try
		{
			for(Nodo n = lista.darPrimero() ; n!= null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getFecha().equals(pFecha))
				{
					if(alist.darTamano()!=0)
						if(((Comparendo) alist.darPrimero().darElemento()).compareTo(inf)<0)
						{
							alist.agregarInicio(inf);
						}
						else if(((Comparendo) alist.darUltimo().darElemento()).compareTo(inf)>0)
						{
							alist.agregarFinal(inf);
						}
						else
						{
							for(Nodo n2 = alist.darPrimero() ; n!=null ; n2 = n2.darSiguiente())
							{
								Comparendo siguiente = (Comparendo) n2.darSiguiente().darElemento();

								if(siguiente.compareTo(inf)<=0)
								{
									Nodo nuevo = new Nodo(inf);
									nuevo.cambiarSiguiente(n2.darSiguiente());
									n2.cambiarSiguiente(nuevo);
									break;
								}
							}
						}
					else
					{
						alist.agregarFinal(inf);
					}
				}
			}
			return alist;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return alist;
		}
	}

	public Cola tresA(Date pFecha1 , Date pFecha2)
	{
		Cola retorno = new Cola();
		ListaEncadenada alist1 = dosA(pFecha1);
		ListaEncadenada alist2 = dosA(pFecha2);
		int cont1 = 0;
		int cont2 = 0;
		String mens = "";
		Cola info = new Cola();
		Nodo n1 = alist1.darPrimero();
		Nodo n2 = alist2.darPrimero();
		String actual = ((Comparendo) n1.darElemento()).getInfr();
		boolean fin1 = false;
		boolean fin2 = false;

		try
		{
			while(n1 != null && n2 != null)
			{
				Comparendo comp1 = (Comparendo) n1.darElemento();
				Comparendo comp2 = (Comparendo) n2.darElemento();
				if(actual.equals(comp1.getInfr()) && !fin1)
				{
					cont1 ++;
					n1 = n1.darSiguiente();
				}
				else 
				{
					fin1 = true;
					retorno.agregar(actual+",1,"+cont1);
					cont1 = 0;
				}

				if(actual.equals(comp2.getInfr()) && !fin2)
				{
					cont2 ++;
					n2 =  n2.darSiguiente();
				}
				else
				{
					fin2 = true;
					retorno.agregar(actual+",2,"+cont2);
					cont2 = 0;
				}

				if(comp1.getInfr().equals(comp2.getInfr()))
				{
					fin1 = false;
					fin2 = false;
					actual = comp1.getInfr();
				}
			}

			if(n1 == null && n2==null && cont1 != 0 && cont2 != 0)
			{
				retorno.agregar(actual+",1,"+cont1);
				retorno.agregar(actual+",2,"+cont2);
			}
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return retorno;
		}
	}


	public Cola unoC(String pLoc , Date pFecha1 , Date pFecha2)
	{
		ListaEncadenada alist = new ListaEncadenada();
		try
		{
			for(Nodo n = lista.darPrimero() ; n!= null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getFecha().compareTo(pFecha2)<= 0 && inf.getFecha().compareTo(pFecha1)>=0 && inf.getLocalidad().equals(pLoc))
				{
					if(alist.darTamano()>0)
					{
						if(((Comparendo) alist.darPrimero().darElemento()).getInfr().compareTo(inf.getInfr())>=0)
						{
							alist.agregarInicio(inf);
						}
						else if(((Comparendo) alist.darUltimo().darElemento()).getInfr().compareTo(inf.getInfr())<0)
						{
							alist.agregarFinal(inf);
						}
						else
						{
							for(Nodo n2 = alist.darPrimero() ; n!=null ; n2 = n2.darSiguiente())
							{
								Comparendo siguiente = (Comparendo) n2.darSiguiente().darElemento();

								if(siguiente.getInfr().compareTo(inf.getInfr())>=0)
								{
									Nodo nuevo = new Nodo(inf);
									nuevo.cambiarSiguiente(n2.darSiguiente());
									n2.cambiarSiguiente(nuevo);
									break;
								}
							}
						}
					}
					else
					{
						alist.agregarFinal(inf);
					}
				}
			}

			int cont = 1;
			String actual = ((Comparendo) alist.darPrimero().darElemento()).getInfr();
			Cola retorno = new Cola();

			for(Nodo n = alist.darPrimero().darSiguiente() ; n!= null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getInfr().equals(actual))
				{
					cont ++;
				}
				else
				{
					retorno.agregar(actual + "," + cont);
					cont = 1;
					actual = inf.getInfr();
				}

				if(n.darSiguiente()==null)
				{
					retorno.agregar(actual+","+cont);
				}
			}


			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Cola dosC(int pN , Date pFecha1 , Date pFecha2)
	{
		ListaEncadenada alist = new ListaEncadenada();
		try
		{
			for(Nodo n = lista.darPrimero() ; n!= null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getFecha().compareTo(pFecha2)<= 0 && inf.getFecha().compareTo(pFecha1)>=0)
				{
					if(alist.darTamano()>0)
					{
						if(((Comparendo) alist.darPrimero().darElemento()).getInfr().compareTo(inf.getInfr())>0)
						{
							alist.agregarInicio(inf);
						}
						else if(((Comparendo) alist.darUltimo().darElemento()).getInfr().compareTo(inf.getInfr())<0)
						{
							alist.agregarFinal(inf);
						}
						else
						{
							for(Nodo n2 = alist.darPrimero() ; n!=null ; n2 = n2.darSiguiente())
							{
								Comparendo actual = (Comparendo) n2.darElemento();
								Comparendo siguiente = (Comparendo) n2.darSiguiente().darElemento();

								if(siguiente.getInfr().compareTo(inf.getInfr())>=0)
								{
									Nodo nuevo = new Nodo(inf);
									nuevo.cambiarSiguiente(n2.darSiguiente());
									n2.cambiarSiguiente(nuevo);
									break;
								}
							}
						}
					}
					else
					{
						alist.agregarFinal(inf);
					}
				}
			}

			int cont = 1;
			String actual = ((Comparendo) alist.darPrimero().darElemento()).getInfr();
			ListaEncadenada retorno = new ListaEncadenada();

			for(Nodo n = alist.darPrimero().darSiguiente() ; n!= null ; n = n.darSiguiente())
			{
				Comparendo inf = (Comparendo) n.darElemento();
				if(inf.getInfr().equals(actual))
				{
					cont ++;
				}
				else if(retorno.darTamano()>0)
				{
					if(((String) retorno.darPrimero().darElemento()).split(",")[1].compareTo(""+cont)<=0)
					{
						retorno.agregarInicio(actual + "," + cont);
					}
					else if(((String) retorno.darUltimo().darElemento()).split(",")[1].compareTo(""+cont)>0)
					{
						retorno.agregarFinal(actual + "," + cont);
					}
					else
					{
						for(Nodo n2 = retorno.darPrimero() ; n!=null ; n2 = n2.darSiguiente())
						{
							String siguiente = (String) n2.darSiguiente().darElemento();

							if(siguiente.split(",")[1].compareTo(""+cont)<=0)
							{
								Nodo nuevo = new Nodo(actual+","+cont);
								nuevo.cambiarSiguiente(n2.darSiguiente());
								n2.cambiarSiguiente(nuevo);
								break;
							}
						}
					}
					cont = 1;
					actual = inf.getInfr();
				}
				else
				{
					retorno.agregarFinal(actual+","+cont);
				}
			}

			Cola r = new Cola();

			int i = 1;
			Nodo n = retorno.darPrimero();
			while(i<=pN && n!=null)
			{
				String a = (String) n.darElemento();
				r.agregar(a);
				n = n.darSiguiente();
				i++;
			}

			return r;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void exch(Comparable[] datos, int i, int j) 
	{ 
		Comparable t = datos[i]; datos[i] = datos[j]; datos[j] = t; 
	}

	public void merge(Comparable[] datos, Comparable[] aux, int lo, int mid, int hi)
	{ 
		int i = lo, j = mid+1; 
		for (int k = lo; k <= hi; k++) 
		{ 
			if (i > mid) aux[k] = datos[j++]; 
			else if (j > hi) aux[k] = datos[i++]; 
			else if (less(datos[j], datos[i])) aux[k] = datos[j++]; 
			else aux[k] = datos[i++]; 
		} 
	}

	public void mergeSort(Comparable[] datos, Comparable[] aux, int lo, int hi) 
	{ 
		if (hi <= lo) return; 
		int mid = lo + (hi - lo) / 2; 
		mergeSort (aux, datos, lo, mid); 
		mergeSort (aux, datos, mid+1, hi); 
		merge(datos, aux, lo, mid, hi); 
	}

	public boolean less(Comparable v, Comparable w) 
	{ 
		Comparendo V = (Comparendo) v;
		Comparendo W = (Comparendo) w;
		return V.getLocalidad().compareTo(W.getLocalidad()) < 0; 
	}

	public Comparable[] copiarComparendos()
	{
		Comparable[] retorno = new Comparable[lista.darTamano()];
		int i = 0;
		for(Nodo n = lista.darPrimero() ; n!= null ; n = n.darSiguiente())
		{
			retorno[i] = (Comparendo) n.darElemento();
			i++;
		}

		return retorno;
	}

	public Cola tresC()
	{
		Comparable[] copia = copiarComparendos();
		Comparable[] aux = copiarComparendos();
		mergeSort(copia,aux,0,copia.length -1);

		int cont = 1;
		String actual = ((Comparendo) aux[0]).getLocalidad();
		Cola retorno = new Cola();
		try
		{
			for(int i = 1 ; i<copia.length ; i++)
			{
				Comparendo inf = (Comparendo) aux[i];
				if(inf.getLocalidad().equals(actual))
				{
					cont ++;
				}
				else
				{
					retorno.agregar(actual + "," + cont);
					cont = 1;
					actual = inf.getLocalidad();
				}

				if(i==copia.length -1)
				{
					retorno.agregar(actual+","+cont);
				}
			}
			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
