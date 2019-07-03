import java.util.Stack;
import java.util.*; 

public class Puzzle
{	
	private Stack<Nodo> visitados;
	private Nodo nodo;
	private int [] posX;
	private int [] costos;
	private int N  = 4;	
	private int costo;

	public Puzzle()
	{
		posX 		=  new int[2];
		visitados   =  new Stack<Nodo>();
		costos 		=  new int[4];
		costo = 0;
	}

	public Stack<Nodo> getVisitados()
	{
		return visitados;
	}

	public void inicializaCostos()
	{
		for (int i = 0 ;i<4 ;i++ ) {
				costos[i] = costo;
		}
	}

	public void printMatrix(int [][] mat) 
	{ 
	    for (int i = 0; i < N; i++) 
	    { 
	        for (int j = 0; j < N; j++) 
	            System.out.print(" "+mat[i][j]); 	        
	        System.out.println(""); 	        
	    } 
	}//fin printMatrix 
	public int calculaCosto(int [][]matriz)
	{
		int  elemento,rF,cF,d;	
		costo = 0;		
		for (int i = 0; i < N; ++i)
		{
			for (int j = 0; j < N; ++j)
			{	
				elemento = matriz[i][j];//ENCONTRAMOS LA POSICION INICIAL DE DE UN ELEMENTO Y EL ELEMENTO	
				if (elemento == 0)///GUARDAMOS LA POSICION DE X
				{
					posX[0] = i;
					posX[1] = j;
					d = Math.abs(3 - i) + Math.abs(3 - j);//CALCULAMOS LA DISTANCIA ENTRE EL PUNTO FINAL Y EL PUNTO INICIAL
				}
				else
				{
					rF = Math.abs((elemento - 1 ) / 4);//CALCULAMOS COORDENADA RENGLON OBJETIVO DE UN ELEMENTO	
					cF = Math.abs((elemento - 1 ) %  4);//CALCULAMOS COORDENADA COLUMNA OBJETIVO DE UN ELEMENTO, TODO EN VALOR ABSOLUTO
					d  = Math.abs(rF - i) + Math.abs(cF - j);//CALCULAMOS LA DISTANCIA ENTRE EL PUNTO FINAL Y EL PUNTO INICIAL	
				}	
			costo = costo + d;	
			}//fin 2 for
		}//fin 1 for	
		return costo;
	}//fin calculaCosto	

	public int calculaMenorCosto()
	{
		costo = costos[0];
		int pos = 0;
		for (int i=0;i<4 ;i++){
			if (costos[i] <= costo){
				costo = costos[i];
				pos = i;
			}
		}
		return pos;
	}//fin calculaMenorCosto

	public int generameCosto(int [][]matriz, int etapa)
	{
		int aux, pos;
		//llendo hacia arriba
		if( (posX[0]-1) >= 0 )
		{ 
			//System.out.println("Entra al if posX[0]-1 >= 0: " +(posX[0]-1) );
				if(posX[0]-1 != visitados.peek().getX()  )				//se supone que posX tiene la ultima pos..entonces no debe de ser igual
			    {	
			//System.out.println("Entra al if posX[0]-1 != visitados.peek().getX(): " +visitados.peek().getX() );    				
					aux = matriz[posX[0]-1][posX[1]];	//copiamos el valor a donde iremos	
					matriz[posX[0]-1][posX[1]]   = 0;	//movemos el espacio(0)
					matriz[posX[0]][posX[1]] = aux;  	//colocamos el valor que movimos a donde estaba el espacio
					costos[0] = calculaCosto(matriz);
					System.out.println("\nArriba\nNuevo Costo: " + costos[0] );
					printMatrix(matriz);
						aux = matriz[posX[0]+ 1][posX[1]];	//Por si acaso, vuelvo a tomar el elemento a donde nos movimos(8)
						matriz[posX[0]+1][posX[1]]  = 0;	//A donde nos movemos colocamos cero
						matriz[posX[0]][posX[1]] = aux;		//Y de donde venimos colocamos el elemento(8)
						posX[0] = posX[0] + 1;				//Regresamos a la posicion inicial
						posX[1] = posX[1] ;					//Es inecesaria, pero por li acaso, lo hacemos en j, tambien
					//System.out.println("\nArriba\nReestructurada" );
					//printMatrix(matriz);	
					//Despues de calcular el costo, regresamos la matriz a la como estaba
			    }//fin if Posicion		    
		}//fin if arriba

		//llendo hacia abajo
		if( (posX[0]+1) < N )
		{ 
				if(posX[0]+1 != visitados.peek().getX() )//se supone que posX tiene la ultima pos..entonces no debe de ser igual
			    {	
					aux = matriz[posX[0] + 1][posX[1]];	 	 //copiamos el valor a donde iremos				 				
	 				matriz[posX[0] + 1][posX[1]]   	= 0;	 //movemos el espacio(0)
	 				matriz[posX[0]][posX[1]] 		= aux; 	 //colocamos el valor que movimos a donde estaba el espacio	costos[1] = calculaCosto(matriz);
	 				costos[1] = calculaCosto(matriz);
			       	System.out.println("\nAbajo\nNuevo Costo: " + costos[1] );
					printMatrix(matriz);
						aux = matriz[posX[0]-1][posX[1]];	//Por si acaso, buscamos ese valor, que movimos, y lo guardamos, para regresar todo a la matriz inicial
						matriz[posX[0]-1][posX[1]] 	= 0;	//Regresamos el valor a la posicion de la matriz, antes del cambio
						matriz[posX[0]][posX[1]] 	= aux;	//lo mismo con el elemento(2)
						posX[0] = posX[0] - 1;				//La posicion vuelve a su estado anterior([0] = 1)
						posX[1] = posX[1] ;					//Esta es por si acaso
			    }//fin if Posicion		    
		}//fin if abajo

		//llendo hacia izquierda
		if( posX[1]-1 >= 0)
		{ 
			System.out.println("posX[1]-1 != visitados.peek().getY(): "+ (posX[1]-1) +"!="+ visitados.peek().getY());
				if(posX[1]-1 != visitados.peek().getY() )//se supone que posX tiene la ultima pos..entonces no debe de ser igual
			    {	
					aux = matriz[posX[0]][posX[1]-1];	//copiamos el valor a donde iremos				 				
	 				matriz[posX[0]][posX[1]-1]   = 0;	//movemos el espacio(0)
	 				matriz[posX[0]][posX[1]] 	 = aux; //colocamos el valor que movimos a donde estaba el espacio
	 				costos[2] = calculaCosto(matriz);	//Calculamos este costo nuevo y lo ponemos en el array	
			       	System.out.println("\nIzquierda\nNuevo Costo: " + costos[2] );
					printMatrix(matriz);
						aux = matriz[posX[0]][posX[1] +1];
						matriz[posX[0]][posX[1]+1] = 0;
						matriz[posX[0]][posX[1]] = aux;
						posX[0] = posX[0];
						posX[1] = posX[1] + 1 ;
			    }//fin if Posicion		    
		}//fin if izquierda

		//llendo hacia derecha
		if( posX[1] + 1  < N)
		{ 
				if(posX[1]+1 != visitados.peek().getY() )//se supone que posX tiene la ultima pos..entonces no debe de ser igual
			    {				    	
					aux = matriz[posX[0]][posX[1] + 1];//copiamos el valor a donde iremos				 				
	 				matriz[posX[0]][posX[1] + 1] = 0;//movemos el espacio(0)
	 				matriz[posX[0]][posX[1]] = aux;  //colocamos el valor que movimos a donde estaba el espacio	 				
					costos[3] = calculaCosto(matriz);
			       	System.out.println("\nDerecha\nNuevo Costo: " + costos[3] );
					printMatrix(matriz);
						aux = matriz[posX[0]][posX[1] -1];
						matriz[posX[0]][posX[1]-1] = 0;
						matriz[posX[0]][posX[1]] = aux;
						posX[0] = posX[0];
						posX[1] = posX[1] - 1 ;
			    }//fin if Posicion		    
		}//fin if derecha
	
		pos = calculaMenorCosto();
		return pos;
	}//fin generaCosto

	public void guardaCamino(int[][]matriz, int posMenorCosto, int etapa)
	{
		int aux;		
		Nodo n = new Nodo();
		System.out.println("PosMenorCosto: " + posMenorCosto);
		//Arriba		
		if (posMenorCosto == 0){
			aux = matriz[posX[0]-1][posX[1]];		//copiamos el valor a donde iremos	
			matriz[posX[0]-1][posX[1]]   	= 0;	//movemos el espacio(0)
			matriz[posX[0]][posX[1]] 		= aux;  //colocamos el valor que movimos a donde estaba el espacio
			costo = calculaCosto(matriz);
			//Agregamos la posicion a un nuevo nodo
			n.setX(posX[0]);
			n.setY(posX[1]);
			visitados.push(n);//Ese nodo se agrega a la pila(visitados)
		}
		//Abajo
		if (posMenorCosto == 1){
			aux = matriz[posX[0] + 1][posX[1]];		//copiamos el valor a donde iremos				 				
			matriz[posX[0] + 1][posX[1]]   	= 0;	//movemos el espacio(0)
			matriz[posX[0]][posX[1]] 		= aux;  //colocamos el valor que movimos a donde estaba el espacio				costos[1] = calculaCosto(matriz);
			costo = calculaCosto(matriz);		//Calculamos el costo, con este movimiento.n.setX(posX[0]);
			//Agregamos la posicion a un nuevo nodo
			n.setX(posX[0]);
			n.setY(posX[1]);
			visitados.push(n);//Ese nodo se agrega a la pila(visitados)
		}
		//Izquierda
		if (posMenorCosto == 2){
			aux = matriz[posX[0]][posX[1]-1];		//copiamos el valor a donde iremos				 				
			matriz[posX[0]][posX[1]-1]   	= 0;	//movemos el espacio(0)
			matriz[posX[0]][posX[1]] 	    = aux;  //colocamos el valor que movimos a donde estaba el espacio
			costo = calculaCosto(matriz);		//Calculamos el costo, con este movimiento.n.setX(posX[0]);
			//Agregamos la posicion a un nuevo nodo
			n.setX(posX[0]);
			n.setY(posX[1]);
			visitados.push(n);//Ese nodo se agrega a la pila(visitados)
		}
		//Derecha
		if (posMenorCosto == 3){
			aux = matriz[posX[0]][posX[1] + 1];	//copiamos el valor a donde iremos				 				
			matriz[posX[0]][posX[1] + 1] = 0;	//movemos el espacio(0)
			matriz[posX[0]][posX[1]] = aux;  	//colocamos el valor que movimos a donde estaba el espacio	 				
			costo = calculaCosto(matriz);		//Calculamos el costo, con este movimiento.n.setX(posX[0]);
			//Agregamos la posicion a un nuevo nodo
			n.setX(posX[0]);
			n.setY(posX[1-1]);
			visitados.push(n);//Ese nodo se agrega a la pila(visitados)
		}
	}

	public Boolean back(int [][]matriz, int etapa)
	{
		Boolean exito = false;
		int posMenorCosto;
		do
		{
			if(calculaCosto(matriz) == 0)//Matriz Resuelta
			{
				 exito = true;
			}
			else//No esta resuelto
			{
				//System.out.println("Entra al else de back");
				//checamos que ninguna opccion se halla repetido o que se pueda realizar el movimiento indicado
				posMenorCosto = generameCosto(matriz, etapa);
				guardaCamino(matriz,posMenorCosto,etapa);				
				back(matriz,etapa + 1);
				exito = false;
			}
			
		}while(exito != true);
		return exito;
	}//fin back

	public void imprimeVisitados()
	{
		 while (!visitados.isEmpty()) {   // mostrar pila completa
            visitados.pop().info();      // extrae un elemento de la pila
            System.out.println(" ");
        }
	}
/*
	public static void main(String[] args)
	{
		Puzzle p 	=  new Puzzle();
		Nodo n1 	= new Nodo();
		int etapa = 0 ;
		int [][] matrizFinal =
		{ 
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{13,14,15,0} 
		};		
		int [][] matrizInicial =
		{ 
			{15,10,8,12},
			{5,3,0,7},
			{6,9,2,11},
			{4,13,14,1}
		};				
			System.out.println("Costo Inicial: " + p.calculaCosto(matrizInicial) + "\nPosicion de Cero:[" + p.posX[0] + "," + p.posX[1] + "]" );			
			p.printMatrix(matrizInicial);
			p.calculaCosto(matrizInicial);
			//Al nodo creado le agregamos [posX,posY]
			n1.setX(p.posX[0]);
			n1.setY(p.posX[1]);
			//Ese nodo que creamos, lo agregamos a la pila
			p.visitados.push(n1);
			//hasta ahora nuestra pila(visitados), tiene un solo nodo(n1)
			//System.out.println("Ultimo nodo agregado:" + p.getVisitados().peek().getX() );
			//System.out.println("Visitados");
			//p.imprimeVisitados();
			p.inicializaCostos();
			
			Boolean valor = p.back(matrizInicial,etapa);			

			System.out.println("Hola mundo");
	}//FIN MAIN
*/
}//FIN CLASE PUZZLE