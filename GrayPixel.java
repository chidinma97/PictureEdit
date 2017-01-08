package a7;

public class GrayPixel implements Pixel {

	private double red, blue, green, intensity;

	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' '};


	public GrayPixel(double intensity) {
		if (intensity < 0.0 || intensity > 1.0) {
			throw new IllegalArgumentException("Intensity of gray pixel is out of bounds.");
		}
		this.intensity = intensity;
		this.red= intensity;
		this.blue = intensity;
		this.green = intensity;
	}

	@Override
	public double getRed() {
		return getIntensity();
	}

	@Override
	public double getBlue() {
		return getIntensity();
	}

	@Override
	public double getGreen() {
		return getIntensity();
	}

	@Override
	public double getIntensity() {
		return intensity;
	}

	@Override
	public char getChar() {
		return PIXEL_CHAR_MAP[(int) (getIntensity()*10.0)];
	}

	//Blend
		@Override
		public Pixel blend(Pixel p, double weight) {
			double newRed = weight * this.red + (1- weight) * p.getRed();
			double newGreen = weight * this.green + (1- weight) * p.getGreen();
			double newBlue = weight * this.blue + (1- weight) * p.getBlue();
			return new ColorPixel(newRed, newGreen, newBlue);
		}
	//Lighten
		@Override
		public Pixel lighten(double factor) {
			return this.blend(new ColorPixel(0.0, 0.0, 0.0), 1 - factor);
		}
	//Darken
		@Override
		public Pixel darken(double factor) {
			return this.blend(new ColorPixel(1.0, 1.0, 1.0), 1 - factor);
		}	
}
