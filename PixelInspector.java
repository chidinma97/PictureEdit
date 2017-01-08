package a7;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspector extends JPanel implements MouseListener {

	private PictureView picture_view;
	JLabel x_point = new JLabel("X: ");
	JLabel y_point = new JLabel("Y: ");
	JLabel red_value = new JLabel("Red: ");
	JLabel blue_value = new JLabel("Blue: ");
	JLabel green_value = new JLabel("Green: ");
	JLabel brightness_value = new JLabel("Brightness: ");
	double red;
	String redS;
	double blue;
	String blueS;
	double green;
	String greenS;
	double brightness;
	String brightnessS;
	Picture current_pic;
	
	public PixelInspector(Picture picture, String title) {
		current_pic = picture;
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);

		JLabel title_label = new JLabel(title);
		add(title_label, BorderLayout.SOUTH);

		JPanel subPanel = new JPanel();

		subPanel.add(x_point);
		subPanel.add(y_point);
		subPanel.add(red_value);
		subPanel.add(blue_value);
		subPanel.add(green_value);
		subPanel.add(brightness_value);

		add(subPanel, BorderLayout.WEST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		
		x_point.setText("X: " + e.getX());
		y_point.setText("Y: " + e.getY());
		
		//gets the intensity value of the pixel
		red = current_pic.getPixel(e.getX(), e.getY()).getRed();
		blue = current_pic.getPixel(e.getX(), e.getY()).getBlue();
		green = current_pic.getPixel(e.getX(), e.getY()).getGreen();
		brightness = current_pic.getPixel(e.getX(), e.getY()).getIntensity();
		
		//sets values to 2 decimals
		redS = String.format("%.2f", red);
		blueS = String.format("%.2f", blue);
		greenS = String.format("%.2f", green);
		brightnessS = String.format("%.2f", brightness);
		
		
		red_value.setText("Red: " + redS);
		blue_value.setText("Blue: " + blueS);
		green_value.setText("Green: " + greenS);
		brightness_value.setText("Brightness: " + brightnessS); 
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}