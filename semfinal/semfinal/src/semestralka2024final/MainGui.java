package semestralka2024final;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.File;
import semestralka2024final.*;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class MainGui extends JFrame {

	private JFrame frame;
	private final PridajDialog2 pridajDialog;
	private JTable table;
	private DefaultTableModel tableModel;
    JPanel cardPanel;
    CardLayout cardLayout;
    private Image image=null;
    private JPanel sablona_jpanel = null;
    private JButton btnZapis;
    private JButton btnNacitaj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	MainGui() {
		frame = new JFrame();
		frame.setTitle("Vizitkar");
		frame.setBounds(100, 100, 955, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Image icon = new ImageIcon(this.getClass().getResource("/semestralka2024final/icon.png")).getImage();
			frame.setIconImage(icon);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel panelNorth = new JPanel();
		frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		//tableModel = new DefaultTableModel(new Object[]{"Meno", "Priezvisko", "Titul", "Spoločnosť", "Adresa", "Telefón", "Email", "Web"}, 0);
		String[] columnNames = {"Meno", "Priezvisko", "Titul", "Spoločnosť", "Adresa", "Telefón", "Email", "Web"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		pridajDialog = new PridajDialog2();
		TableRowSorter<TableModel>sorter= new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);


		JButton btnPridaj = new JButton("Pridaj");
		btnPridaj.addActionListener(e -> {
		pridajDialog.setTableModel(tableModel);
		pridajDialog.setVisible(true);
		});
		panelNorth.add(btnPridaj);
		
		
		JPanel panelWest = new JPanel();
		frame.getContentPane().add(panelWest, BorderLayout.WEST);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelWest.add(scrollPane);
		
		
		JPanel panelSouth = new JPanel();
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnUloz = new JButton("Uloz");
		btnUloz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sablona_jpanel == null)
					return;
				uloz();
			}
		});
		
		btnNacitaj = new JButton("Nacitaj");
		btnNacitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				try (BufferedReader br = new BufferedReader(new FileReader("polozky.txt"))) {
				    String line;
				    while ((line = br.readLine()) != null) {
				        String[] values = line.split(",");
				        model.addRow(values);
				    }
				    JOptionPane.showInternalMessageDialog(null,"data boli nacitane");
				} catch (IOException e) {
		            e.printStackTrace();
		        }
			}
		});
		panelSouth.add(btnNacitaj);
		
		btnZapis = new JButton("Zapis");
		btnZapis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
		        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("polozky.txt")), 8192);
		        		Writer writer = new PrintWriter(outputStream)) {
		            for (Vector riadok : model.getDataVector()) {
		            	StringBuilder builder = new StringBuilder();
		            	
		            	for (Object bunka : riadok) {
		            		if (bunka != null)
		            			builder.append((String) bunka);
		            		builder.append(",");
		            	}
		            	writer.append(builder.toString());
	                	writer.append("\n");
	                	
		            }
		            JOptionPane.showInternalMessageDialog(null,"data boli zapisane");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
		});
		panelSouth.add(btnZapis);
		
		panelSouth.add(btnUloz);
		
		JButton btnPrint = new JButton("Tlac");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sablona_jpanel == null)
					return;
				
				Vytlac vytlac = new Vytlac(sablona_jpanel);
				
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(vytlac);
				if (job.printDialog())
					try {
						job.print();
						JOptionPane.showInternalMessageDialog(null,"vytlacenie suboru");
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		panelSouth.add(btnPrint);

		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		JPanel panel_1 = new JPanel();
		layeredPane.add(panel_1, "sablona2");
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "sablona2");
		//PridajDialog sablonaPanel = new PridajDialog(meno + " " + priezvisko + " " + titul, telefon, adresa, email, web, spolocnost);
		//panel_1.add(new Sablona(), "Panel1");
		//panel_1.add(sablonaPanel, "menoPanelu");
//		panel.add(new Sablona2(), "Panel2");
		
		
		JButton btnSablona = new JButton("Sablona");
		btnSablona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow(); 
				if(rowIndex != -1) { 
					DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				layeredPane.removeAll();
				String meno=(String) model.getValueAt(rowIndex, 2)+ " " + (String) model.getValueAt(rowIndex, 1) +" "+ (String) model.getValueAt(rowIndex, 0);
				String spolocnost= (String) model.getValueAt(rowIndex, 3);
				String adresa= (String) model.getValueAt(rowIndex, 4);
				String email= (String) model.getValueAt(rowIndex, 6);
				String telefon= (String) model.getValueAt(rowIndex, 5);
				String web= (String) model.getValueAt(rowIndex, 7);
				sablona_jpanel = new Sablona(meno, spolocnost,adresa,telefon,email,web);
				layeredPane.add(sablona_jpanel, "Panel2");
				
				layeredPane.repaint();
				layeredPane.revalidate();
				layeredPane.repaint();
				layeredPane.revalidate();
				}
			}
		});	
				
				JButton btnOdstran = new JButton("Odstran");
				btnOdstran.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rowIndex = table.getSelectedRow(); 
						if(rowIndex != -1) { 
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(rowIndex); 
						JOptionPane.showInternalMessageDialog(null,"riadok bol odstraneny");
						}
					}
				});
				panelNorth.add(btnOdstran);
		
				panelNorth.add(btnSablona);
		
		JButton btnSablona2 = new JButton("Sablona 2");
		btnSablona2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				layeredPane.removeAll();
//				layeredPane.add(panel);
//				Image image = Toolkit.getDefaultToolkit().getImage("/semestralka2024/icon.png");
//				String text = "Váš Textvrdsfresfer";
//				
//				ImagePanel panel = new ImagePanel(image, text );
//				layeredPane.add(panel, "sablona2"); // Pridanie ImagePanel do layeredPane
//				layeredPane.repaint();
//				layeredPane.revalidate();
//				layeredPane.repaint();
//				layeredPane.revalidate();
				
				int rowIndex = table.getSelectedRow(); 
				if(rowIndex != -1) { 
					DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				layeredPane.removeAll();
				String meno=(String) model.getValueAt(rowIndex, 2)+ " " + (String) model.getValueAt(rowIndex, 0) +" "+ (String) model.getValueAt(rowIndex, 1);
				String spolocnost= (String) model.getValueAt(rowIndex, 3);
				String adresa= (String) model.getValueAt(rowIndex, 4);
				String email= (String) model.getValueAt(rowIndex, 6);
				String telefon= (String) model.getValueAt(rowIndex, 5);
				String web= (String) model.getValueAt(rowIndex, 7);
				sablona_jpanel = new Sablona2(meno, spolocnost,adresa, telefon, email, web);
				layeredPane.add(sablona_jpanel, "Panel2");
				
				layeredPane.repaint();
				layeredPane.revalidate();
				layeredPane.repaint();
				layeredPane.revalidate();
				}
				

			}
		});
		panelNorth.add(btnSablona2);
//		JButton btnSablona3 = new JButton("Sablona 3");
//		btnSablona3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				layeredPane.removeAll();
//				//layeredPane.add(panel);
//				Image image3= null;
//				try {
//					image3 = ImageIO.read(new File("C:\\Users\\juraj\\eclipse-workspace2\\semestralka2024\\src\\semestralka2024\\sab2.png"));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				String text = "Váš Textvrdsfresfer";
//				
//				Graphics g=image3.getGraphics();
//						g.setFont(g.getFont().deriveFont(25f));
//						g.drawString("ahoj", 80, 100);
//						g.drawString("telefon", 80, 200);
//						g.drawString("adresa", 80, 250);
//						g.drawString("mail", 80, 300);
//						g.drawString("telefon", 80, 350);
//						g.dispose();
//						
//				
//				ImagePanel panel = new ImagePanel(image3);
//				layeredPane.add(panel, "sablona3"); // Pridanie ImagePanel do layeredPane
//				layeredPane.repaint();
//				layeredPane.revalidate();
//			}
//		});
//		panelNorth.add(btnSablona3);
		
}
	



	protected void uloz() {
		BufferedImage bImg = new BufferedImage(sablona_jpanel.getWidth(), sablona_jpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D cg = bImg.createGraphics();
		sablona_jpanel.paintAll(cg);

		
		try {
			ImageIO.write( bImg, "png", new File("vizitka.png"));
			JOptionPane.showInternalMessageDialog(null,"subor bol ulozeny");
		} catch (IOException e) {
					e.printStackTrace();
		}
		
	}



	public void vyberSablony(){
		
	}
	

        
		
	}

