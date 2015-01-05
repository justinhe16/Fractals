public class Mandelbrot {
	static ComplexNumber first = new ComplexNumber(1, -5);
	static ComplexNumber second = new ComplexNumber(2, 3);
	
	public static void main (String[] args){
		System.out.println(first.multiply(second));
		StdDraw.setXscale(-2, 2);
		StdDraw.setYscale(-1.5, 1.5);
	}
}
