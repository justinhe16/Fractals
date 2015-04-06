import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Mandelbrot {
	static ComplexNumber first = new ComplexNumber(-1, -5);
	static ComplexNumber second = new ComplexNumber(0, 0);

	public static void main (String[] args){
		JFrame window = new JFrame("one");
		window.setVisible(true);
		window.setTitle("MandelBrot Set");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

