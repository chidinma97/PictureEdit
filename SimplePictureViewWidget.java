package a7;

import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimplePictureViewWidget extends JPanel implements MouseListener {

	private PictureView picture_view;
	JLabel x_point = new JLabel("X: ");
	JLabel y_point = new JLabel("Y: ");

	public SimplePictureViewWidget(Picture picture, String title) {
		setLayout(new BorderLayout());

		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);

		JLabel title_label = new JLabel(title);
		add(title_label, BorderLayout.SOUTH);

		JPanel subPanel = new JPanel();

		subPanel.add(x_point);
		subPanel.add(y_point);
		subPanel.add(new JLabel("Red: "));
		subPanel.add(new JLabel("Blue: "));
		subPanel.add(new JLabel("Green: "));
		subPanel.add(new JLabel("Brightness: "));

		add(subPanel, BorderLayout.WEST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		
		x_point.setText("X: " + e.getX());
		y_point.setText("Y: " + e.getY());
		
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
