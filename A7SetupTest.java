package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A7SetupTest {
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://classroomclipart.com/TN_viking-with-sword-and-wooden-shield-clipart.jpg");
		PixelInspector pixel_inspector = new PixelInspector(p, "Fight");
		ImageAdjuster image_adjuster = new ImageAdjuster(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Simple Picture View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(pixel_inspector, BorderLayout.CENTER);
		top_panel.add(image_adjuster, BorderLayout.CENTER);
	
		main_frame.setContentPane(top_panel);
		


		main_frame.pack();
		main_frame.setVisible(true);
	}
}
