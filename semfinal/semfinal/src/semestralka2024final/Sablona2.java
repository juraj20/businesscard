package semestralka2024final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Sablona2 extends JPanel {

	public Sablona2(String name, String spolocnost, String adresa, String telefon, String email, String web) {
		setBounds(100, 100, 400, 300);
		setLayout(new BorderLayout());
		//setBorder(new EmptyBorder(5, 5, 5, 5));

		
		removeAll();
		//layeredPane.add(panel);
		Image image3= null;
		try {
			image3 = ImageIO.read(new File("C:\\Users\\juraj\\eclipse-workspace2\\semfinal\\src\\semestralka2024final\\sab2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String text = "Váš Textvrdsfresfer";
		
		Graphics g=image3.getGraphics();
				g.setFont(g.getFont().deriveFont(25f));
				g.drawString(name, 80, 100);
				g.drawString(spolocnost, 120, 150);
				g.drawString(telefon, 80, 200);
				g.drawString(adresa, 80, 250);
				g.drawString(email, 80, 300);
				g.drawString(web, 80, 350);
				g.dispose();
				
		
		ImagePanel panel = new ImagePanel(image3);
		add(panel); // Pridanie ImagePanel do layeredPane
		repaint();
		revalidate();
		
		
		// Načítanie a zmenšenie obrázka
//		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/semestralka2024/sab2.png"));
//		Image resizedImage = originalIcon.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
//		ImageIcon resizedIcon = new ImageIcon(resizedImage);
//
//		// Pridanie JLabel s obrázkom do panelu
//		JLabel lblNewLabel = new JLabel("", resizedIcon, SwingConstants.CENTER);
//		add(lblNewLabel, BorderLayout.CENTER);
		
		class ImagePanel extends JPanel {
		private Image image;

		public ImagePanel(Image image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		}
//
		@Override
		protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Vykreslenie obrázka
		g.drawImage(image, 0, 0, this);
//
//		// Nastavenie farby a fontu pre text
		g.setColor(Color.RED);
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
//
		// Vykreslenie textu
		g.drawString(text, 10, 20);
		}
		}
		
		}
		
	
	public JPanel getPanel() {
		return this;
	}

}

