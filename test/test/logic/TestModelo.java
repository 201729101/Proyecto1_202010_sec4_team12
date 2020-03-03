package test.logic;

import static org.junit.Assert.*;


import model.logic.Modelo;
import model.data_structures.Comparendo;
import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	
	@Before
	public void setUp1() 
	{
		modelo= new Modelo();
	}
	
	public void setUp2() 
	{
		modelo.cargarDatos("comparendos_dei_2018_small.geojson");
	}
	
	public void setUp3()
	{
		modelo.agregarFinal(new Comparendo(0, null, null, null, null, null, null, null, 0, 0));
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		setUp2();
		assertEquals(20,modelo.darTamano());
		setUp3();
		assertEquals(21,modelo.darTamano());
	}

	@Test
	public void testAgregarFinal() {
		setUp3();
		assertEquals(1,modelo.darTamano());
		assertNotNull(modelo.buscar(0));
	}
	
	@Test
	public void testAgregarInicio(){
		Comparendo inf = new Comparendo(0, null, null, null, null, null, null, null, 0, 0);
		modelo.agregarInicio(inf);
		assertEquals(1, modelo.darTamano());
		assertEquals(0,modelo.darLista().darPrimero().darElemento().compareTo(inf));
	}

	@Test
	public void testBuscar() {
		setUp3();
		assertNotNull(modelo.buscar(0));
		assertTrue(modelo.buscar(0).getLatitud()==0.0);
	}

	@Test
	public void testEliminar() {
		setUp3();
		Comparendo inf = modelo.eliminar(0);
		assertNull(modelo.buscar(0));
	}

}
