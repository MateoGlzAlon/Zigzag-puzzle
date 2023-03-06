    package puzzlezigzag;
    
    import java.util.ArrayList;
    import java.util.Scanner;
    
    
    public class MainZigzag {

    	private static int filas;
    	private static int columnas;
    	private static boolean[][] posValidas;
    	private static int min;
    	private static int max;
    	private static ArrayList<String> soluciones;
    
    	    
    	public static void main(String[] args) {
    		
    		filas = 0;
    		columnas = 0;
    		min = 0;
    		max = 0;
    		soluciones = new ArrayList<>();
    		
    		ArrayList<Casilla> camino2 = new ArrayList<>();

    		
    		Scanner sc = new Scanner(System.in);
    		    		
    		//Creo la primera matriz para meter las lineas
    		String [] temp = null;
    		
    		//Creo una nueva tabla con huecos entre sí para colocar símbolos
    		String[][] tablaAmpliada = null;
    		
    		try {
    			//Creo la tabla inicial que tiene un tamaño maximo de 10
        		temp = new String[10];
        		//Lo meto en un array de una dimensión
        		crearTabla2(temp, sc);
    			
        		tablaAmpliada = new String[filas*2 - 1][columnas*2 - 1];
        		crearTabla(sc, tablaAmpliada, temp);
        		
        		//Busco el máximo y el mínimo en la tabla
        		min = buscarMin(tablaAmpliada);
        		max = buscarMax(tablaAmpliada);
        		
        	//Si la entrada está mal formada, atrapa la excepcion y muestra Entrada incorrecta.
    		} catch (Exception e) {
				System.out.print("Entrada incorrecta.");
				return;
			}
    		
    		//Si un elemento es menor de 1 o mayor de 10, la entrada es incorrecta
    		if(elementoIncorrecto(tablaAmpliada)) {
				System.out.print("Entrada incorrecta.");
				return;
    		}
    		
    		//Si todos los elementos son iguales y no es un solo elemento(1x1), no tiene soluciones
    		if(todosSonMismoElemento(tablaAmpliada) && ( filas != 1 && columnas != 1)) {
    			System.out.print("0");
    			return;
    		}
    		
    		
    		//Creo una tabla de booleans para descartar las posiciones que ya han sido utilizadas
    		posValidas = new boolean[filas*2-1][columnas*2-1];
    
    		posValidas[0][0] = true;				
    
    		
    		//Comprueba que no todos los elementos sean iguales

    		
    		//Construye el puzzle
    		construirPuzzle(tablaAmpliada, 0, 0, camino2);
    
    		limpiarTabla(tablaAmpliada);
    		
    		
    		
    //		System.out.println();
    //		
    //		for (int i = 0; i < soluciones.size();i++) {
    //			System.out.println("Solucion nº" + (i+1) + ": " + soluciones.get(i));
    //		}
    
    		
    		
    		
    		
    		//A PARTIR DE AQUI PARA VPL
    		
    		if (soluciones.size() == 0) {
    			System.out.print(soluciones.size()  + "\n");
    		} else {
        		System.out.print(soluciones.size() + "\n");
    		}
    		
    		for (int j = 0; j< soluciones.size();j++) {
    			dibujarCamino(tablaAmpliada, j);
    			mostrarTabla2D(tablaAmpliada);
    			limpiarTabla(tablaAmpliada);
       			if (j < soluciones.size()-1)
    			System.out.print("\n");
    		}
    		
    		
    		
    		
    		sc.close();
    	}
    	
    	
    	
    	//Meto todas las lineas en un vector de strings para saber el numero de columnas y filas
    	public static void crearTabla2(String [] temp, Scanner sc) {
    		
    		int i = 0;
    
    		while (sc.hasNextLine() && filas < 10) {
    			temp [i] = sc.nextLine();
    			i++;
    			filas++;
    		}
    		
    		if (temp[0] != null) {
    			String[] primeraLinealine =  temp[0].split(" ");
    			columnas= primeraLinealine.length;
    		}
    		
    	}
    	
    	//Meto los numeros que le he pasado al programa en la tablaAmpliada desde el array temp 
    	public static void crearTabla(Scanner sc, String[][] tablaAmpliada, String[] temp) throws Exception  {
    		
    		for (int z = 0 ; z < filas;z++) {	
    			String aux = temp[z];
    			String[] line =  aux.split(" ");
    			
    			if (line.length > 10) {
    				throw new Exception();
    			}
    
    			for (int j = 0; j < columnas ; j++){
    				tablaAmpliada[z*2][j*2] = line[j];
    			}			
    		}
    		
    		for (int i = 0; i < filas*2-1 ; i++) {
    			for (int j = 0; j < columnas*2-1 ; j++){
    				if (tablaAmpliada[i][j] == null) tablaAmpliada[i][j] = " ";
    			}
    		}
    		
    	}
    	
    	@SuppressWarnings("unchecked")
    	//Método que encuentra las soluciones y las guarda en un ArrayList de String que luego el metodo dibujarCamino() dibuja 
    	public static void construirPuzzle(String[][] tablaAmpliada, int fila, int columna, ArrayList<Casilla> camino){
    
    		for(int j = 1; j <=8 ; j++) {
    			
    			if(contarTrues() == contarElementos() && esquinaDerecha(fila, columna)) {
    					camino.add(new Casilla(fila,columna));
    					String aux = "";
    					for (int k = 0; k< camino.size();k++) {
    						aux+= camino.get(k) + ":";
    					}
    					soluciones.add(aux);
//    					System.out.println(aux);
    					camino.clear();
//    		    		mostrarTabla2Dboolean(posValidas);
//    		    		System.out.println();
    					return;
    								
    			} else if (contarTrues() == contarElementos()) {
    				camino.add(new Casilla(fila,columna));
    				camino.clear();
    				return;
    				
    			}
    			
    			switch(j) {
    			 
    			case 1:
    				//Casilla de arriba a la izquierda
    				if (casillaExistente(fila-2, columna-2) && !haySimbolo(fila-1,columna-1) &&  ((siguienteNumero(fila, columna, fila - 2,columna - 2, tablaAmpliada) || deMaxAMin(fila, columna,fila - 2,columna - 2, tablaAmpliada))&& !casillaUsada(fila - 2 , columna - 2))) {
    					posValidas[fila - 2][columna - 2] = true;
    					posValidas[fila - 1][columna - 1] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila - 2, columna - 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    				}
    				break;
    			case 2:
    				//Casilla de arriba
    				if (casillaExistente(fila-2, columna) && !haySimbolo(fila-1,columna) && ((siguienteNumero(fila, columna, fila - 2, columna, tablaAmpliada) || deMaxAMin(fila, columna, fila - 2, columna, tablaAmpliada)) && !casillaUsada(fila - 2, columna))) {
    					posValidas[fila - 2][columna] = true;
    					posValidas[fila - 1][columna] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila - 2, columna, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    				}
    				break;
    			case 3:
    				//Casilla de arriba a la derecha
    				if (casillaExistente(fila-2, columna+2) && !haySimbolo(fila-1,columna+1) && ((siguienteNumero(fila, columna, fila - 2,columna + 2, tablaAmpliada) || deMaxAMin(fila, columna,fila - 2,columna + 2, tablaAmpliada)) && !casillaUsada(fila - 2 , columna + 2))) {
    					posValidas[fila - 2][columna + 2] = true;
    					posValidas[fila - 1][columna + 1] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila - 2, columna + 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    					
    				} 
    				break;
    			case 4:
    				//Casilla de la izquierda		
    				if ( (casillaExistente(fila, columna-2)) && !haySimbolo(fila,columna-1) && (siguienteNumero(fila, columna, fila, columna - 2, tablaAmpliada) || deMaxAMin(fila, columna, fila, columna - 2, tablaAmpliada)) && !casillaUsada(fila, columna - 2)) {
    					posValidas[fila][columna - 2] = true;
    					posValidas[fila][columna - 1] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila, columna - 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    				}
    				break;
    			case 5:	
    				//Casilla de la derecha
    				if (casillaExistente(fila, columna+2) && !haySimbolo(fila,columna+1) && ((siguienteNumero(fila, columna, fila, columna + 2, tablaAmpliada) || deMaxAMin(fila, columna, fila, columna + 2, tablaAmpliada)) && !casillaUsada(fila, columna+2))) {
    					posValidas[fila][columna + 2] = true;
    					posValidas[fila][columna + 1] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila, columna + 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    
    				}
    				break;
    			case 6:
    				//Casilla de abajo a la izquierda
    				if (casillaExistente(fila + 2, columna-2) && !haySimbolo(fila+1,columna-1) && ((siguienteNumero(fila, columna, fila + 2, columna - 2, tablaAmpliada) ||  deMaxAMin(fila, columna, fila + 2, columna - 2, tablaAmpliada)) && !casillaUsada(fila + 2, columna - 2))) {
    					posValidas[fila + 2][columna - 2] = true;
    					posValidas[fila + 1][columna - 1] = true;

    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila + 2, columna - 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    				}
    				break;
    			case 7:
    				//Casilla de abajo
    				if (casillaExistente(fila + 2, columna) && !haySimbolo(fila+1,columna) && ((siguienteNumero(fila, columna, fila + 2, columna, tablaAmpliada) || deMaxAMin(fila, columna, fila + 2, columna, tablaAmpliada)) && !casillaUsada(fila + 2, columna))) {
    					posValidas[fila + 2][columna] = true;
    					posValidas[fila + 1][columna] = true;

    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila + 2, columna, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    
    				}	
    				break;
    			case 8:	
    				//Casilla de abajo a la derecha
    				if (casillaExistente(fila + 2, columna + 2) && !haySimbolo(fila+1,columna+1) &&  ((siguienteNumero(fila, columna, fila + 2,columna + 2, tablaAmpliada) || deMaxAMin(fila, columna,fila + 2,columna + 2, tablaAmpliada)) && !casillaUsada(fila + 2 , columna + 2))) {
    					posValidas[fila + 2][columna + 2] = true;
    					posValidas[fila + 1][columna + 1] = true;
    					ArrayList<Casilla> caminoclon = (ArrayList<Casilla>) camino.clone();
    					caminoclon.add(new Casilla(fila,columna));
    					construirPuzzle(tablaAmpliada, fila + 2, columna + 2, caminoclon);
    					resetearTabla(tablaAmpliada, caminoclon);
    				}
    				break;
    			
    			
    			}
    		
    		}
        
    		
    	}
    	
    	//Comprueba que el movimiento no se salga del array
    	public static boolean casillaValida(int filaPrev, int columnaPrev) {
    		if (posValidas[filaPrev][columnaPrev]  == false ) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	//Busca el elemento máximo
    	public static int buscarMax(String tabla[][]) {
    		int max = 0;
    		for (int i = 0; i < filas*2-1; i+=2) {
    			for (int j = 0; j < columnas*2-1; j+=2) {
    				if (Integer.valueOf(tabla[i][j]) > max) max = Integer.valueOf(tabla[i][j]);
    			}
    		}
    		return max;
    	}
    	
    	//Busca el elemento minimo
    	public static int buscarMin(String tabla[][]) {
    		int min = 10;
    		for (int i = 0; i < filas*2-1; i+=2) {
    			for (int j = 0; j < columnas*2-1; j+=2) {
    				if (Integer.valueOf(tabla[i][j]) < min) min = Integer.valueOf(tabla[i][j]);
    			}
    		}
    		return min;
    	}
    	
    	//Comprueba si se puede realizar el salto de casilla (si pasa de maximo a minimo)
    	public static boolean deMaxAMin(int filaprev, int columprev, int nuevafila, int nuevacolum, String[][] tabla) {
    		if (Integer.valueOf(tabla[filaprev][columprev]) == max && Integer.valueOf(tabla[nuevafila][nuevacolum]) == min) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	//Comprueba si se puede realizar el salto de casilla (si el numero al que salta es el anterior + 1)
    	public static boolean siguienteNumero(int filaprev, int columprev, int nuevafila, int nuevacolum, String[][] tabla) {
    		if (Integer.valueOf(tabla[filaprev][columprev]) + 1 == Integer.valueOf(tabla[nuevafila][nuevacolum])) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	//Comprueba que no se haya pasado por la casilla 
    	public static boolean casillaUsada(int fila, int colum) {
    		if (posValidas[fila][colum] == false) {
    			return false;
    		} else {
    			return true;
    		}
    	}
    	
    	//Cuenta el numero de elementos por los que ha pasado
    	public static int contarTrues() {
    		int contador = 0;
    		for (int i = 0; i < filas*2-1; i+=2) {
    			for (int j = 0; j < columnas*2-1; j+=2) {
    				if (posValidas[i][j] == true) contador++; 
    			}
    		}	
    		return contador;
    	}
    	
    	//Cuenta el numero de elementos
    	public static int contarElementos() {
    		return filas*columnas;
    	}
    	
    	//Resetea la tabla para continuar con el resto de caminos (tiene en cuenta por los elementos por los que habia pasado previamente)
    	public static void resetearTabla(String [][] tabla, ArrayList<Casilla> camino) {
    		for (int i = 1; i < filas*2-1 ; i+=2) {
    			for (int j = 0; j < columnas*2-1 ; j++){
    				tabla[i][j] = " ";
    			}
    		}
    		
    		
    		for (int i = 0; i < filas*2-1 ; i++) {
    			for (int j = 1; j < columnas*2-1 ; j+=2){
    				tabla[i][j] = " ";
    			}
    		}
    		
    		for (int i = 0; i < filas*2-1 ; i++) {
    			for (int j = 0; j < columnas*2-1 ; j++){
    				posValidas[i][j] = false;
    			}
    		}
    		
    		for(int i = 0; i < camino.size() ;i++) {
    			posValidas[camino.get(i).i][camino.get(i).j] = true;
    		}

    		for(int i = 0; i < camino.size()-1 ;i++) {
    			posValidas[camino.get(i).i + (camino.get(i+1).i - camino.get(i).i)/2][camino.get(i).j + (camino.get(i+1).j - camino.get(i).j)/2] = true;
    		}
    		
    		
    		posValidas[0][0] = true;
    	}
    	
    	//Comprueba si la casilla es la de la derecha abajo
    	public static boolean esquinaDerecha(int fila, int columna) {
    		
    		if (fila == filas*2-2 && columna == columnas*2-2) {
    			return true;
    		} else {
    			return false;
    		}		
    	}
    	
    	//Comprueba que la casilla no se salga del array
    	public static boolean casillaExistente(int fila, int columna) {
    		if ((fila >= 0 && fila <= filas*2-1) && (columna >= 0 && columna <= columnas*2-1) ) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	//Dibuja las lineas que representan el camino
    	public static void dibujarCamino(String [][] tablaAmpliada, int j) {
    		
    		String[] line = soluciones.get(j).split(":");
    		
    		Casilla[] recorrido = new Casilla[line.length];
    		
    		for ( int z = 0; z < recorrido.length; z++) {
    			String[] linecomma = line[z].split(",");
    			recorrido[z] = new Casilla( Integer.valueOf(linecomma[0]), Integer.valueOf(linecomma[1]));
    		}
    				
    		
    		
    		for (int k = 0; k < recorrido.length - 1; k++) {
    						
    			
    			//Diagonal de abajo a la derecha  \ 
    			if (recorrido[k].i == recorrido[k + 1].i  - 2 && recorrido[k].j == recorrido[k + 1].j - 2) {
    				tablaAmpliada[recorrido[k].i + 1][recorrido[k].j + 1] = "\\";
    			}
    			//Casilla de abajo  |
    			if (recorrido[k].i == recorrido[k + 1].i - 2 && recorrido[k].j == recorrido[k + 1].j) {
    				tablaAmpliada[recorrido[k].i + 1][recorrido[k].j] = "|";
    			}
    			//Casilla de abajo a la izquierda  /
    			if (recorrido[k].i == recorrido[k + 1].i - 2 && recorrido[k].j == recorrido[k + 1].j + 2) {
    				tablaAmpliada[recorrido[k].i + 1][recorrido[k].j - 1] = "/";
    			}
    			//Casilla de la derecha  -
    			if (recorrido[k].i == recorrido[k + 1].i && recorrido[k].j == recorrido[k + 1].j - 2) {
    				tablaAmpliada[recorrido[k].i][recorrido[k].j + 1] = "-"; 
    			}
    			//Casilla de la izquierda  -
    			if (recorrido[k].i == recorrido[k + 1].i && recorrido[k].j == recorrido[k + 1].j + 2) {
    				tablaAmpliada[recorrido[k].i][recorrido[k].j - 1] = "-";
    			}
    			//Casilla de arriba a la derecha  /
    			if (recorrido[k].i == recorrido[k + 1].i + 2 && recorrido[k].j == recorrido[k + 1].j - 2) {
    				tablaAmpliada[recorrido[k].i - 1][recorrido[k].j + 1] = "/";
    			}
    			//Casilla de arriba  |
    			if (recorrido[k].i == recorrido[k + 1].i + 2 && recorrido[k].j == recorrido[k + 1].j) {
    				tablaAmpliada[recorrido[k].i - 1][recorrido[k].j] = "|";
    			}
    			//Casilla de arriba a la izquierda \
    			if (recorrido[k].i == recorrido[k + 1].i + 2 && recorrido[k].j == recorrido[k + 1].j + 2) {
    				tablaAmpliada[recorrido[k].i - 1][recorrido[k].j - 1] = "\\";
    			}	
    		}				
    	}
    	
    	//Quita los simbolos de la tabla para poder redibujarla
    	public static void limpiarTabla(String [][] tabla) {
    		for (int i = 1; i < filas*2-1 ; i+=2) {
    			for (int j = 0; j < columnas*2-1 ; j++){
    				tabla[i][j] = " ";
    			}
    		}
    		
    		for (int i = 0; i < filas*2-1 ; i++) {
    			for (int j = 1; j < columnas*2-1 ; j+=2){
    				tabla[i][j] = " ";
    			}
    		}
    	}
    	
    	//Comprueba si todos los elementos de la matriz son iguales
    	public static boolean todosSonMismoElemento(String[][] tablaAmpliada) {
    		
    		for(int i = 0; i < filas*2-1; i+=2) {
    			for(int j = 0; j < columnas*2-1; j+=2) {
    				if(Integer.valueOf(tablaAmpliada[i][j]) != Integer.valueOf(tablaAmpliada[0][0])){
    					return false;
    				}
    			}	
    		}
    			
			return true;
    		
    	}
    	
    	//Comprueba si en la casilla hay un simbolo, para no pisar el anterior camino
    	public static boolean haySimbolo(int fila, int columna) {
    		
    		if(posValidas[fila][columna] == true) return true;
			return false;

    	}
    	
    	
    	
    	
    	//METODOS DE PRINT
    	
    	
    	public static String imprimirCamino(ArrayList<Casilla> camino) {
    		String aux = "";
    		for (int i = 0; i< camino.size(); i++) {
    			if (camino.get(i) != null) aux+= i+1 + "º" + camino.get(i).toString() + " ";
    		}
    		return aux;
    	}
    	
    	
    	public static void mostrarTabla2D(String[][] tabla) {
    		for (int i = 0; i < filas*2-1; i++) {
    			for (int j = 0; j < columnas*2-1; j++) {
    				System.out.print(tabla[i][j] + "");
    			}
    			System.out.print("\n");
    		}
    	}
    	
    	public static void mostrarTabla2Dboolean(boolean[][] tabla) {
    		for (int i = 0; i < filas*2-1; i++) {
    			for (int j = 0; j < columnas*2-1; j++) {
    				if (tabla[i][j] == false) {
    					System.out.print("X ");
    				} else {
    					System.out.print("T ");
    				}
    			}
    			System.out.println();
    		}
    	}
    	
    	public static void mostrarTabla1D(String[] tabla) {
    		for (int i = 0; i < tabla.length; i++) {
    			if (tabla[i] != null) System.out.println(tabla[i]);
    		}
    		System.out.println();
    	}
    	    	
    	public static boolean elementoIncorrecto(String[][] tablaAmpliada) {
    		
    		for (int i = 0; i < filas*2-1 ; i+=2) {
    			for (int j = 0; j < columnas*2-1; j+=2) {
    				if (Integer.valueOf(tablaAmpliada[i][j]) < 1 || Integer.valueOf(tablaAmpliada[i][j]) > 9){
    					return true;
    				}
    			}
    		}
    		return false;
    		
    	}
    	
    }
