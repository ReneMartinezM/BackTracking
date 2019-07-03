
public class Nodo{
	//Atributos
	private int x;
	private int y;
	//Constructores
	public Nodo()
	{
		x = 0;
		y = 0;
	}
	public Nodo(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	//Method´s
	public void info()
	{
		System.out.println("[X,Y]: " +"["+ x +","+ y + "]");
	}
	//Gette´s And Setter´s
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public static void main(String[] args) {
        
    }
}//Fin class Nodo