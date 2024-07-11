package semestralka2024final;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class PridajDialog2 extends JDialog {
		
		private final JPanel contentPanel = new JPanel();
		private JTextField textMeno;
		private JTextField textPriezvisko;
		private JTextField textSpolocnost;
		private JTextField textAdresa;
		private JTextField textTelCislo;
		private JComboBox comboBoxTitul;
//		private JComboBox comboBoxSab;
		private DefaultTableModel tableModel;
		private JFormattedTextField formattedTextEmail;
		private JFormattedTextField formattedTextWeb;
		private JButton okButton;
		private JPanel buttonPane;

		/**
		 * Create the dialog.
		 */
		public PridajDialog2() {
			setModal(true);
			setBounds(100, 100, 478, 345);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblMeno = new JLabel("Meno");
				contentPanel.add(lblMeno);
			}
			{
				textMeno = new JTextField();
				textMeno.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				contentPanel.add(textMeno);
				textMeno.setColumns(10);
			}
			{
				JLabel lblPriezvisko = new JLabel("Priezvisko");
				contentPanel.add(lblPriezvisko);
			}
			{
				textPriezvisko = new JTextField();
				contentPanel.add(textPriezvisko);
				textPriezvisko.setColumns(10);
			}
			{
				JLabel lblTitul = new JLabel("Titul");
				contentPanel.add(lblTitul);
			}
			{
				comboBoxTitul = new JComboBox();
				comboBoxTitul.setModel(new DefaultComboBoxModel(new String[] {"Bc.", "Mgr.", "Ing.", "MVDr.", "MUDr.", "MDDr. ", "ThDr.", "PaedDr.", "JUDr.", "PharmDr.", "RNDr.", "PhDr.", "PhD.", "DrSc.", "doc.", "prof."}));
				comboBoxTitul.setSelectedIndex(2);
				contentPanel.add(comboBoxTitul);
			}
			{
				JLabel lblSpolocnost = new JLabel("Spolocnost");
				contentPanel.add(lblSpolocnost);
			}
			{
				textSpolocnost = new JTextField();
				contentPanel.add(textSpolocnost);
				textSpolocnost.setColumns(10);
			}
			{
				JLabel lblAdresa = new JLabel("Adresa");
				lblAdresa.setHorizontalAlignment(SwingConstants.LEFT);
				contentPanel.add(lblAdresa);
			}
			{
				textAdresa = new JTextField();
				contentPanel.add(textAdresa);
				textAdresa.setColumns(10);
			}
			{
				JLabel lblTelCislo = new JLabel("tel.cislo");
				contentPanel.add(lblTelCislo);
			}
			{
				textTelCislo = new JTextField();
				contentPanel.add(textTelCislo);
				textTelCislo.setColumns(10);
			}
			{
				JLabel lblEmail = new JLabel("e-mail");
				contentPanel.add(lblEmail);
			}
			{
				formattedTextEmail = new JFormattedTextField();
				contentPanel.add(formattedTextEmail);
			}
			{
				JLabel lblWeb = new JLabel("Web");
				contentPanel.add(lblWeb);
			}
			{
				formattedTextWeb = new JFormattedTextField();
				contentPanel.add(formattedTextWeb);
			}
//			{
//				JLabel lblSablona = new JLabel("Sablona");
//				contentPanel.add(lblSablona);
//			}
//			{
//				
////				comboBoxSab = new JComboBox();
////
////				comboBoxSab.setModel(new DefaultComboBoxModel(new String[] {"sablona1", "sablona2"}));
////				contentPanel.add(comboBoxSab);
//			}
			{
				buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String meno = textMeno.getText();
							String priezvisko = textPriezvisko.getText();
							String titul = (String) comboBoxTitul.getSelectedItem();
							String spolocnost = textSpolocnost.getText();
							String adresa = textAdresa.getText();
							String telefon = textTelCislo.getText();
							String email  =  formattedTextEmail.getText();
							String web = formattedTextWeb.getText();
//							String vybranyTitul = (String) comboBoxSab.getSelectedItem();
//							System.out.println(vybranyTitul);
							
							//Sablona sablonaPanel = new Sablona(meno + " " + priezvisko + " " + titul, telefon, adresa, email, web, spolocnost);
							
							if (tableModel != null) { 
								String[] columnNames = {"Meno", "Priezvisko", "Titul", "Spoločnosť", "Adresa", "Telefón", "Email", "Web"};

								tableModel.addRow(new Object[]{meno, priezvisko, titul, spolocnost, adresa, telefon, email, web});
							}
//							if (vybranyTitul != null) {
//								if(vybranyTitul == "sablona1") {
//									new Sablona();
//								}
//								else if(vybranyTitul == "sablona2") {
//									new Sablona2();
//								}
//								else {
//									System.out.println("nsepravna hodnota");
//								}
//								
//							}
							
							
							setVisible(false);
							clearFields();
							}
							});
							
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
			
		}
		
		
		
		
		public JTextField getTextPriezvisko() {
			return textPriezvisko;
		}
		public JTextField getTextSpolocnost() {
			return textSpolocnost;
		}
		public JTextField getTextAdresa() {
			return textAdresa;
		}
		public JTextField getTextTelCislo() {
			return textTelCislo;
		}
		public JComboBox getComboBoxTitul() {
			return comboBoxTitul;
		}
		public DefaultTableModel getTableModel() {
			return tableModel;
		}
		public JFormattedTextField getFormattedTextEmail() {
			return formattedTextEmail;
		}
		public JFormattedTextField getFormattedTextWeb() {
			return formattedTextWeb;
		}
		public void setTableModel(DefaultTableModel tableModel) {
			this.tableModel = tableModel;
			}
		private void clearFields() {
			textMeno.setText("");
			textPriezvisko.setText("");
			comboBoxTitul.setToolTipText("");
			textSpolocnost.setText("");
			textAdresa.setText("");
			textTelCislo.setText("");
			formattedTextEmail.setText("");
			formattedTextWeb.setText("");
			
			
			
			}
		public String[] getFields() {
			String meno = textMeno.getText();
			String priezvisko = textPriezvisko.getText();
			String titul = comboBoxTitul.getToolTipText();
			String spolocnost = textSpolocnost.getText();
			String adresa = textAdresa.getText();
			String tel = textTelCislo.getText();
			String email = formattedTextEmail.getText();
			String web = formattedTextWeb.getText();

			return new String[] {meno, priezvisko, titul, spolocnost, adresa, tel, email, web};
			}

	}








