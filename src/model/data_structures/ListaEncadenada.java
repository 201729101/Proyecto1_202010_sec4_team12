package model.data_structures;

import model.data_structures.Nodo;

/**
 * Clase de la estructura de datos lista encadenada
 */
public class ListaEncadenada<E extends Comparable<E>> 
{
	/**
	 * Referencia al primer elemento de la lista
	 */
	private Nodo<E> primero;
	
	/**
	 * Referencia al último elemento de la lista
	 */
	private Nodo<E> ultimo;
	
	/**
	 * Numero de elemeentos en la lista
	 */
	private int numElem;
	
	/**
	 * Constructor de la lista
	 */
	public ListaEncadenada()
	{
		primero = null;
		ultimo = null;
		numElem = 0;
	}
	
	/**
	 * Devuelve el número de elementos en l alista
	 * @return número de elementos en el la lista
	 */
	public int darTamano()
	{
		return numElem;
	}
	
	/**
	 * Devuelve el primer nodo de la lista
	 * @return primer nodo de la lista
	 */
	public Nodo<E> darPrimero()
	{
		return primero;
	}
	
	/**
	 * Devuelve el último nodo de la lista
	 * @return último nodo de la lista
	 */
	public Nodo<E> darUltimo()
	{
		return ultimo;
	}
	
	/**
	 * Busca un el ellemento recibido por parametro en l alista
	 * @param elemento elemento a buscar, elemento != null
	 * @return el elemento que se buscó, null si no está en la lista
	 */
	public E buscar(E elemento)
	{	
		if(primero==null)
		{
			return null;
		}
		else
		{
			for(Nodo<E> n = primero ; n!=null ; n = n.darSiguiente())
			{
				if(n.darElemento().compareTo(elemento)==0)
				{
					return n.darElemento();
				}
			}
			return null;
		}
	}
	
	/**
	 * Busca un elemento en la lista con el indice recibido por tamaño
	 * @param i indice a buscar, i>=0
	 * @return elemento en el indice recibido 
	 */
	public E buscar(int i)
	{
		E elemento = null;
		Nodo<E> item = primero;
		int cont = 0;
		while(item!=null)
		{
			if(cont==i)
			{
				elemento = item.darElemento();
				break;
			}
			item = (Nodo<E>) item.darSiguiente();
			cont ++;
		}
		
		return elemento;
	}
	
	/**
	 * Agrega un elemento recibido por parámatro al final de la lista
	 * @param elemento elelemnto a agregar en la lista, elemento != null
	 */
	public void agregarFinal(E elemento)
	{
		Nodo<E> nodo = new Nodo<E>( elemento );
		if(primero == null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			ultimo.cambiarSiguiente(nodo);
			ultimo = nodo;
		}
		numElem++;
	}
	
	/**
	 * Agrega un elemento recibido por parámatero al inicio de la lista
	 * @param elemento elelemtno a agregar en la lista, elelemtno != null
	 */
	public void agregarInicio(E elemento)
	{
		Nodo<E> nodo = new Nodo<E>(elemento);
		
		if(primero==null)
		{
			primero = nodo;
			ultimo = nodo;
		}
		else
		{
			nodo.cambiarSiguiente(primero);
			primero = nodo;
		}
		numElem++;
	}
	
	/**
	 * Elimina y retorna un elemento recibido por parámetro
	 * @param elemento elelemento a eliminar, elemento != null
	 * @return elelemtno eliminado, null si no está el elemento en la lista
	 */
	public E eliminar(E elemento)
	{
		if(primero!=null && primero.darElemento().compareTo(elemento)!=0)
		{
			Nodo<E> anterior = primero;
			for(Nodo<E> n = primero.darSiguiente() ; n!=null ; n = n.darSiguiente())
			{
				if(n.darElemento().compareTo(elemento)==0)
				{
					anterior.cambiarSiguiente(n.darSiguiente());
					n.cambiarSiguiente(null);
					return n.darElemento();
				}
				anterior = n;
			}
		}
		else if(primero.darElemento().compareTo(elemento)==0)
		{
			Nodo<E> nuevo = primero;
			primero = primero.darSiguiente();
			nuevo.cambiarSiguiente(null);
			return nuevo.darElemento();
		}
		return null;
	}
	
	/**
	 * Elimina un elelemtno en el indice recibido por parámetro
	 * @param i indice del elemento a eliminar, i>=0
	 * @return
	 */
	public E eliminar(int i)
	{
		E elemento = null;
		Nodo<E> anterior = primero;
		Nodo<E> item = primero.darSiguiente();
		int cont = 1;
		if(i!=0)
		{
			while(item!=null)
			{
				if(cont==i)
				{
					anterior.cambiarSiguiente(item.darSiguiente());
					elemento = item.darElemento();
					item.cambiarSiguiente(null);
					break;
				}
				anterior = item;
				item = item.darSiguiente();
				cont ++;
			}
		}
		else
		{
			Nodo<E> temp = primero;
			primero = primero.darSiguiente();
			temp.cambiarSiguiente(null);
			elemento = temp.darElemento();
		}
		
		return elemento;
	}
}
