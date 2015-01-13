//************************************************************************************************
// ComplexNumber.java       Author: Justin He
//
// Represents a complex number with a real number section and a imaginary number section.
//************************************************************************************************
public class ComplexNumber implements Comparable<Object>{
	private double a, b;

	/** 
	 *  @author Justin He
	 *  @version Jan 1st, 2015
	 */  


	/** CONSTRUCTOR: ComplexNumber (double realparam, double imagparam)
	 * This constructor takes in two double variables, realparam and imagparam, that
	 * become the class's real and imaginary double variables, respectively. 
	 * @param realparam the real number that the user wants his/her ComplexNumber to have
	 * @param imagparam the imaginary number that the user wants his/her ComplexNumber to have
	 */
	public ComplexNumber (double realparam, double imagparam){
		a = realparam;
		b = imagparam;
	}

	/** CONSTRUCTOR: ComplexNumber (ComplexNumber c1)
	 * This is a copy constructor that takes in a ComplexNumber and adapts its real and
	 * imaginary aspects as its own.
	 * @param c1 this is a parameter that should be a ComplexNumber, that the user wants its
	 * aspects to be copied into a new Complex Number. 
	 */
	public ComplexNumber (ComplexNumber c1){
		a = c1.a;
		b = c1.b;
	}

	/** CONSTRUCTOR: ComplexNumber ()
	 * This is a default constructor. It has no parameters, and creates a ComplexNumber class
	 * with its real variable as 1 and its imaginary variable as 1.
	 */
	public ComplexNumber (){
		a = 1;
		b = 1;
	}

	/** UTILITY: toString()
	 *  This is a necessary utility method that returns a String; this basically allows proper
	 *  printing representation of the ComplexNumber class in the format a + bi.
	 */
	public String toString() {
		if (b != 0 && a != 0){
			if (b > 0){
				return a + " + " + b + "i";
			}
			else
				return a + " - " + -1*b + "i";
		}
		else if (b == 0){
			return a + "";
		}
		else {
			return b + "i";
		}
	}

	/** UTILITY: equals(Object o)
	 * 
	 * 
	 */
	public boolean equals(Object o){
		if (a == ((ComplexNumber) o).getReal() && b == ((ComplexNumber) o).getImaginary()){
			return true;
		}
		else
			return false;
	}

	/** ACCESS METHOD: getReal()
	 *  This is an access method. When called, it returns the real variable of the respective
	 *  ComplexNumber.
	 * @return real the real variable of the respective ComplexNumber being called
	 */
	public double getReal() { return a; }

	/** ACCESS METHOD: getImaginary()
	 *  This is an access method. When called, it returns the imaginary variable of the respect-
	 *  ive ComplexNumber.
	 * @return imaginary the imaginary variable of the respective ComplexNumber being called
	 */
	public double getImaginary() { return b; }

	/** CORE OPERATION: magnitude
	 * 
	 * 
	 */
	public double magnitude(){
		double aandbsquared = (a*a)+(b*b);
		if (aandbsquared < 0){
			aandbsquared = -1*aandbsquared;
		}
		double magn = Math.sqrt(aandbsquared);
		return magn;
	}

	/**
	 * 
	 * 
	 * @param o
	 * @return
	 */
	public int compareTo(Object o) {
		if (this.magnitude() == ((ComplexNumber) o).magnitude())
			return 0;
		else if ((this.magnitude()) > ((ComplexNumber) o).magnitude())
			return 1;
		else
			return -1;
	}

	/** CORE OPERATION: getConjugate()
	 * This is a Core Operation method. When called, it creates and returns the conjugate of the
	 * ComplexNumber.
	 * @return conjugate a new ComplexNumber that has a changed imaginary section by multiplying it
	 * by -1.
	 */
	public ComplexNumber getConjugate(){
		ComplexNumber conjugate = new ComplexNumber(this.a, -1*this.b);
		return conjugate;
	}


	/** CORE OPERATION: add(ComplexNumber c)
	 *  This core operation method adds the 'this' ComplexNumber with ComplexNumber c.
	 * @param c the ComplexNumber to be added to 'this' ComplexNumber.
	 * @return addedComplexNumber a ComplexNumber that is the sum of 'this' ComplexNumber and c ComplexNumber.
	 */
	public ComplexNumber add (ComplexNumber c){
		double RealCombo = a + c.getReal();
		double ImaginaryCombo = b + c.getImaginary();
		ComplexNumber addedComplexNumber = new ComplexNumber (RealCombo, ImaginaryCombo);
		return addedComplexNumber;
	}

	/** CORE OPERATION: subtract(ComplexNumber c)
	 * This is a core operation method where ComplexNumber c is subtracted from 'this' ComplexNumber.
	 * @param c the ComplexNumber to be subtracted from 'this' ComplexNumber.
	 * @return subtractedComplexNumber a ComplexNumber that is the difference of 'this' ComplexNumber and c ComplexNumber.
	 */
	public ComplexNumber subtract (ComplexNumber c){
		double RealCombo = a - c.getReal();
		double ImaginaryCombo = b - c.getImaginary();
		ComplexNumber subtractedComplexNumber = new ComplexNumber (RealCombo, ImaginaryCombo);
		return subtractedComplexNumber;
	}

	/** CORE OPERATION: multiply(ComplexNumber c)
	 * This is a core operation method where 'this' ComplexNumber is multiplied with ComplexNumber c.
	 * @param c the ComplexNumber to be multiplied with 'this' ComplexNumber.
	 * @return multipledComplexNumber the product of 'this' ComplexNumber and ComplexNumber c.
	 */
	public ComplexNumber multiply (ComplexNumber c){
		double F = a * c.getReal();
		double O = a * c.getImaginary();
		double I = b * c.getReal();
		double imaginaryOI = O + I; 
		double L = b * c.getImaginary() * -1;
		double realFL = F + L;
		ComplexNumber multipliedComplexNumber = new ComplexNumber(realFL, imaginaryOI);
		return multipliedComplexNumber;
	}

	/** CORE OPERATION: divide(ComplexNumber c)
	 * This is a core operation method where 'this' ComplexNumber is divided with ComplexNumber c.
	 * @param c the ComplexNumber to be divided with 'this' ComplexNumber.
	 * @return dividedComplexNumber the quotient of 'this' ComplexNumber and ComplexNumber c.
	 * 
	 * FLAG: divide by 0?
	 */
	public ComplexNumber divide (ComplexNumber c){
		if (c.equals(new ComplexNumber(0,0))){
			throw new ArithmeticException("Cannot divide by zero!");
		}
		else{
			ComplexNumber Numerator = new ComplexNumber(this.multiply(c.getConjugate()));
			ComplexNumber Denominator = new ComplexNumber(c.multiply(c.getConjugate()));
			double finalreal = (Numerator.getReal()/Denominator.getReal());
			double finalimaginary = (Numerator.getImaginary()/Denominator.getReal());
			ComplexNumber dividedComplexNumber = new ComplexNumber(finalreal,finalimaginary);
			return dividedComplexNumber; 
		}
	}

	/** CORE OPERATION: power(int a)
	 * FLAG: necessary for a > 0
	 * 
	 */
	public ComplexNumber power(int a){
		if (a < 0){
			throw new ArithmeticException("For this program, you cannot enter powers less than 0.");
		}
		ComplexNumber poweredComplexNumber = this;
		for (int i = 0; i < a; i++){
			poweredComplexNumber = this.multiply(this);
		}
		return poweredComplexNumber;
	}

	/** CORE OPERATION: square()
	 * 
	 * 
	 */
	public ComplexNumber square(){
		ComplexNumber squareComplexNumber = this.multiply(this);
		return squareComplexNumber;
	}
}
