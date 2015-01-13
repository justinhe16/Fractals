import javax.swing.*;

public class Mandelbrot {
	static ComplexNumber first = new ComplexNumber(-1, -5);
	static ComplexNumber second = new ComplexNumber(0, 0);

	public static void main (String[] args){
		try{
			System.out.println(first.divide(second));
		}
		catch(ArithmeticException ex){
			System.err.println(ex.getMessage());
		}
		
		try{
			System.out.println(first.power(-1));
		}
		catch(ArithmeticException ex1){
			System.err.println(ex1.getMessage());
		}
		JFrame window = new JFrame("one");
		window.setVisible(true);
		window.setTitle("MandelBrot Set");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
