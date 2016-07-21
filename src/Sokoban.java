import Model.FloorElement;
import Model.WallElement;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by DDcreo on 2016-04-13.
 */

public class Sokoban {

	public static void main(String[] args) {

		// Zmienilem troche maina dziala bez zarzutu
		JFrame frame = new View.MainFrame();
		Image image = new ImageIcon("pic//backLogin.jpg").getImage();
		frame.setIconImage(image);
		frame.setBackground(Color.GREEN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sokoban");
		
		
	}
}
