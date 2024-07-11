package semestralka2024final;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

public class Vytlac implements Printable {
	JPanel sablona_jpanel;
	
	Vytlac(JPanel sablona_jpanel) {
		this.sablona_jpanel = sablona_jpanel;
	}

	  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
	        if (pageIndex > 0) {
	            return 1;
	        } else {
	    		sablona_jpanel.paintAll(graphics);

	            Graphics2D g2d = (Graphics2D)graphics;
	            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	            return 0;
	        }
	    }
	}


