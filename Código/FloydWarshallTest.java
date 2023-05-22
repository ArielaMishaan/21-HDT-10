import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author MAAG
 *
 */
class FloydWarshallTest {

	/**
	 * Test method for {@link edu.uvg.graphs.FloydWarshall#CalcularRutas()}.
	 */
	@Test
	void testCalcularRutas() {
		int[][] matriz_distancias = {{0,4,8,10000,10000},
				{4,0,1,2,10000},
				{8,10000,0,4,2},
				{10000,2,4,0,7},
				{10000,10000,2,7,0}};
		
		String[][] matriz_recorridos = 
			{{"A","B","C","D","E"},
					{"A","B","C","D","E"},
					{"A","B","C","D","E"},
					{"A","B","C","D","E"},
					{"A","B","C","D","E"}};
		
		FloydWarshall algoritmoFW = new FloydWarshall(matriz_distancias, matriz_recorridos, 5);

		algoritmoFW.CalcularRutas();
		
		int[][] matriz_distancias_resultante = 
			{{0,4,5,6,7},
				{4,0,1,2,3},
				{8,6,0,4,2},
				{6,2,3,0,5},
				{10,8,2,6,0}};
		
		
		String[][] matriz_recorridos_resultante = 
			{{"A","B","B","B","C"},
					{"A","B","C","D","C"},
					{"A","D","C","D","E"},
					{"B","B","B","D","C"},
					{"C","D","C","C","E"}};
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertEquals(matriz_distancias_resultante[i][j], 
						algoritmoFW.getDistancias()[i][j]);
			}
		}
		
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertEquals(matriz_recorridos_resultante[i][j], 
						algoritmoFW.getRecorridos()[i][j]);
			}
		}
	}

	@Test
	void testArchivo() throws Exception{

		Controladora controladora = new Controladora(4);

		ArrayList<String> lineasDatos = new ArrayList<String>();
		lineasDatos.add("BuenosAires SaoPaulo 10 15 20 50");
		lineasDatos.add("BuenosAires Lima 15 20 30 70");
		lineasDatos.add("Lima Quito 10 12 15 20");

		controladora.leerArchivo(lineasDatos);
		
		int[][] matriz_distancias_normales = {{0,10,15,1000000000},
				{1000000000,0,1000000000,1000000000},
				{1000000000,1000000000,0,10},
				{1000000000,1000000000,1000000000,0}};

		int[][] matriz_distancias_lluvia = {{0,15,20,1000000000},
				{1000000000,0,1000000000,1000000000},
				{1000000000,1000000000,0,12},
				{1000000000,1000000000,1000000000,0}};
		
		int[][] matriz_distancias_nieve = {{0,20,30,1000000000},
				{1000000000,0,1000000000,1000000000},
				{1000000000,1000000000,0,15},
				{1000000000,1000000000,1000000000,0}};
		
		int[][] matriz_distancias_tormenta = {{0,50,70,1000000000},
				{1000000000,0,1000000000,1000000000},
				{1000000000,1000000000,0,20},
				{1000000000,1000000000,1000000000,0}};

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(matriz_distancias_normales[i][j], 
						controladora.getDistanciasNormal()[i][j]);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(matriz_distancias_lluvia[i][j], 
						controladora.getDistanciasLluvia()[i][j]);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(matriz_distancias_nieve[i][j], 
						controladora.getDistanciasNieve()[i][j]);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(matriz_distancias_tormenta[i][j], 
						controladora.getDistanciasTormenta()[i][j]);
			}
		}
	}

}
