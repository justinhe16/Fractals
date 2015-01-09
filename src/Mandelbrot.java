import javax.swing.*;

public class Mandelbrot {
	static ComplexNumber first = new ComplexNumber(-1, -5);
	static ComplexNumber second = new ComplexNumber(-1, 3);

	public static void main (String[] args){
		System.out.println(first.divide(second));
		JFrame window = new JFrame("one");
		window.setVisible(true);
		window.setTitle("MandelBrot Set");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
	}

}
