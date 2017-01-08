package a7;

public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	private static final double RED_INTENSITY_FACTOR = 0.299;
	private static final double GREEN_INTENSITY_FACTOR = 0.587;
	private static final double BLUE_INTENSITY_FACTOR = 0.114;

	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' '};
	
	public ColorPixel(double r, double g, double b) {
		if (r > 1.0 || r < 0.0) {
			throw new IllegalArgumentException("Red out of bounds");
		}
		if (g > 1.0 || g < 0.0) {
			throw new IllegalArgumentException("Green out of bounds");
		}
		if (b > 1.0 || b < 0.0) {
			throw new IllegalArgumentException("Blue out of bounds");
		}
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getIntensity() {
		return RED_INTENSITY_FACTOR*getRed() + 
				GREEN_INTENSITY_FACTOR*getGreen() + 
				BLUE_INTENSITY_FACTOR*getBlue();
	}
	
	@Override
	public char getChar() {
		int char_idx = (int) (getIntensity()*10.0);
		return PIXEL_CHAR_MAP[char_idx];
	}
	
	@Override
	public Pixel blend(Pixel p, double weight) {
		double newRed = weight * this.red + (1- weight) * p.getRed();
		double newGreen = weight * this.green + (1- weight) * p.getGreen();
		double newBlue = weight * this.blue + (1- weight) * p.getBlue();
		return new ColorPixel(newRed, newGreen, newBlue);
	}
	
	@Override
	public Pixel lighten(double factor) {
		return this.blend(new ColorPixel(1.0, 1.0, 1.0), 1- factor);
	}
	//darken
	@Override
	public Pixel darken(double factor) {
		return this.blend(new ColorPixel(0.0, 0.0, 0.0), 1-factor);
	}
}
