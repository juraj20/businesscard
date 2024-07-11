package semestralka2024final;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import semestralka2024final.*;


public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
			private Image image;


			public ImagePanel(Image image) {
				this.image=image;

				
				

			}
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, this);
				g.setColor(Color.BLACK);
				g.setFont(new Font("Serif", Font.BOLD,30));

			}

		}

	


