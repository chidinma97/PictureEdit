package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjuster extends JPanel implements ChangeListener {

	JSlider blur_slider;
	JSlider brightness_slider;
	JSlider saturation_slider;
	int blur;
	int brightness;
	int saturationFactor;
	Picture current_pic;
	PictureView view;

	public ImageAdjuster(Picture picture) {
		setLayout(new BorderLayout());

		current_pic = picture;
		view = new PictureView(current_pic.createObservable());
		add(view, BorderLayout.CENTER);

		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(3, 1));

		blur_slider = new JSlider(0, 5, blur);
		brightness_slider = new JSlider(-100, 100, 0);
		saturation_slider = new JSlider(-100, 100, 0);

		blur_slider.setMajorTickSpacing(1);
		blur_slider.setMinorTickSpacing(1);
		blur_slider.setPaintTicks(true);
		blur_slider.setSnapToTicks(true);
		blur_slider.addChangeListener(this);

		brightness_slider.setMajorTickSpacing(20);
		brightness_slider.setMinorTickSpacing(10);
		brightness_slider.setPaintTicks(true);
		brightness_slider.addChangeListener(this);

		saturation_slider.setMajorTickSpacing(20);
		saturation_slider.setMinorTickSpacing(10);
		saturation_slider.setPaintTicks(true);
		saturation_slider.addChangeListener(this);

		//slider_panel.add(blur_slider);
		slider_panel.add(brightness_slider);
		slider_panel.add(saturation_slider);

		add(slider_panel, BorderLayout.SOUTH);

	}

	public Pixel Blur(Pixel p, int blur) {
		// should i return a picture?'

		double total_red = 0;
		double total_blue = 0;
		double total_green = 0;
		double average_r = 0;
		double average_b = 0;
		double average_g = 0;
		double pixelsize = ((blur * 2) + 1) * ((blur * 2) + 1);

		for (int x = 0; x < current_pic.getWidth() - 1; x++) {
			for (int y = 0; y < current_pic.getHeight() - 1; y++) {
				total_red += current_pic.getPixel(x, y).getRed();
				total_blue += current_pic.getPixel(x, y).getBlue();
				total_green += current_pic.getPixel(x, y).getGreen();

				for (int ix = x - blur; ix <= x + blur; ix++) {// loop
																// surrounding
																// pixels
					if (ix < 0 || ix == x) {
						ix++;
					}
					for (int iy = y - blur; iy <= y + blur; iy++) {
						if (iy < 0 || iy == y) {
							iy++;// makes sure that pixel is in picture
						}
						total_red += current_pic.getPixel(ix, iy).getRed();
						total_blue += current_pic.getPixel(ix, iy).getBlue();
						total_green += current_pic.getPixel(ix, iy).getGreen();
					}
				}
				average_r = total_red / pixelsize;
				average_b = total_blue / pixelsize;
				average_g = total_green / pixelsize;

				ColorPixel new_pixel = new ColorPixel(average_r, average_g, average_b);
				return new_pixel;
			}

		}
		return p;

	}

	public Pixel Brighten(Pixel p, int brightness) {

		if (brightness > 0) {
			Pixel newpixel = p.lighten(brightness / 100.0);
			return newpixel;

		} else if (brightness < 0) {
			Pixel newpixel = p.darken(Math.abs(brightness / 100.0));
			return newpixel;
		} else {
			return p;
		}

	}

	public Pixel Saturate(Pixel p, int saturationFactor) {
		if (saturationFactor < 0) {
			double newblue = p.getBlue() * (1.0 + (saturationFactor / 100.0))
					- (p.getIntensity() * saturationFactor / 100.0);

			double newred = p.getRed() * (1.0 + (saturationFactor / 100.0))
					- (p.getIntensity() * saturationFactor / 100.0);

			double newgreen = p.getGreen() * (1.0 + (saturationFactor / 100.0))
					- (p.getIntensity() * saturationFactor / 100.0);

			ColorPixel newpixel = new ColorPixel(newred, newgreen, newblue);
			return newpixel;

		} else if (saturationFactor > 0) {
			double maxGB = Math.max(p.getBlue(), p.getGreen());
			double a = Math.max(maxGB, p.getRed());

			if (a == 0) {
				return p;

			} else {
				double newblue = p.getBlue() * ((a + ((1.0 - a) * (saturationFactor / 100.0))) / a);
				double newred = p.getRed() * ((a + ((1.0 - a) * (saturationFactor / 100.0))) / a);
				double newgreen = p.getGreen() * ((a + ((1.0 - a) * (saturationFactor / 100.0))) / a);

				ColorPixel newpixel = new ColorPixel(newred, newgreen, newblue);
				return newpixel;
			}

		} else {
			return p;
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

		PictureImpl temp = new PictureImpl(current_pic.getWidth(), current_pic.getHeight());

		for (int x = 0; x < current_pic.getWidth() - 1; x++) {
			for (int y = 0; y < current_pic.getHeight() - 1; y++) {
				Pixel temppix = current_pic.getPixel(x, y);

				temppix = Saturate(temppix, saturation_slider.getValue());
				temppix = Brighten(temppix, brightness_slider.getValue());

				temp.setPixel(x, y, temppix);
			}
		}

		view.setPicture(temp.createObservable());
	}
}
