package a7;

public interface Pixel {

	public double getRed();
	public double getBlue();
	public double getGreen();
	public double getIntensity();
	public char getChar();
	Pixel lighten(double factor);
	Pixel darken(double factor);
	Pixel blend(Pixel p, double weight);	
}
