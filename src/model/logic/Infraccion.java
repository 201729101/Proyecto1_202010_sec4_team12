package model.logic;

import java.util.Date;

/**
 * Clase de una infracci�n de transito
 */
public class Infraccion implements Comparable
{
	/**
	 * ID del comparendo
	 */
	private int id;
	
	/**
	 * Fecha del comparendo en String
	 */
	private String fecha;
	
	/**
	 * Medio de detecci�n del comparendo
	 */
	private String medio;
	
	/**
	 * Clase de veh�culo comparentado
	 */
	private String clase;
	
	/**
	 * Tipo de servicio del veh�culo comparentado
	 */
	private String tipo;
	
	/**
	 * C�digo de la infracci�n colocada
	 */
	private String infr;
	
	/**
	 * Descripci�n del comparendo
	 */
	private String desc;
	
	/**
	 * Localidad en que se coloc� el comparendo
	 */
	private String localidad;
	
	/**
	 * Latitud en que se coloc� el comparendo
	 */
	private double latitud;
	
	/**
	 * Longitud en que se coloc� el comparendo
	 */
	private double longitud;
	
	/**
	 * Contructor
	 * @param pId ID del comparendo
	 * @param pFecha Fecha del comparendo
	 * @param pMedio Medio de detecci�n del comparendo
	 * @param pClase Clase de veh�culo comparentado
	 * @param pTipo Tipo de servicio del vah�culo comparentado
	 * @param pInfr C�digo de la infracci�n colocada
	 * @param pDesc Descripcion del comparendo
	 * @param pLoc Localidad donde se puso el comparendo
	 * @param pLat Latitud donde se puso el comparendo
	 * @param pLong Longitud donde se puso el comparendo
	 */
	public Infraccion(int pId, String pFecha, String pMedio, String pClase, String pTipo, String pInfr, String pDesc, String pLoc, double pLat, double pLong)
	{
		id = pId;
		fecha = pFecha;
		medio = pMedio;
		clase = pClase;
		tipo = pTipo;
		infr = pInfr;
		desc = pDesc;
		localidad = pLoc;
		latitud = pLat;
		longitud = pLong;
	}
	
	/**
	 * Reotna el tipo de servicio
	 * @return tipo de servicio
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Retorna la latidud 
	 * @return latitud
	 */
	public double getLatitud() {
		return latitud;
	}
	
	/**
	 * Retorna la longitud
	 * @return longitud
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Retorna la descripci�n
	 * @return descripci�n
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Retorna la fecha
	 * @return fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/** 
	 * Retorna el id
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Retorna la infracci�n
	 * @return infracci�n
	 */
	public String getInfr() {
		return infr;
	}
	
	/**
	 * Retorna el medio
	 * @return medio
	 */
	public String getMedio() {
		return medio;
	}
	
	/**
	 * Retorna la clase
	 * @return clase
	 */
	public String getClase() {
		return clase;
	}
	
	/**
	 * Retorna la localidad
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}
	
	
	@Override
	/**
	 * Compara dos infracciones por su ID, 
	 * @return 0 si son iguales, -1 si no 
	 */
	public int compareTo(Object o) {
		try
		{
			Infraccion inf = (Infraccion) o;
			if(inf.id==this.id)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

}
