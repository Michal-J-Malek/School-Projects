package CS116_Malek_Michal_FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class ChooseNextOption extends JFrame {

	private JPanel contentPane;
	private JButton btnCreate;
	private JButton btnNewButton;
	private JButton btnDeleteOrders;
	private JButton btnDisplayCustomerList;
	private JButton btnInputAFile;
	private int orderID = 654321;
	
	private ArrayList<String[]> everythingImported = new ArrayList<String[]>(); 
	private ArrayList<String[]> orders = new ArrayList<String[]>();
	private JLabel lblFileName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	
	private DefaultListModel<String> dlm;
	
	private JScrollPane menuScrollPane;
	
	private JList<String> list;
	
	private boolean loadedFile = false;
	
	static ChooseNextOption c = new ChooseNextOption();
	private JTextField textField_9;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    c.pack();
	    c.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ChooseNextOption(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 993);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCreate = new JButton("Finish");														//complete
		btnCreate.addActionListener((new ActionListener() {										//closes program
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "";
				for(int i=0;i<orders.size();i++) {
					String[] grabbable = orders.get(i);
					for(int j=0;j<grabbable.length-1;j++) {
						str = str + grabbable[j] + ", ";
					}
					str = str + grabbable[grabbable.length-1]+"\n";
				}
				try (PrintWriter out = new PrintWriter("InventoryList.txt")){
					out.print(str);
				}catch (IOException e1){
				}
				c.dispose(); 
			}
		}));
		btnCreate.setBounds(608, 920, 101, 23);
		contentPane.add(btnCreate);
		
		btnNewButton = new JButton("Add Order(s)");												//TODO maybe complete now
		btnNewButton.addActionListener((new ActionListener() {									//this will add orders 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loadedFile == true) {
					String orderNumberSTR = Integer.toString(orderID);
					String selected = orderNumberSTR+", " + list.getSelectedValue().toString();
					String delims = "[,]";
					String [] convert = selected.split(delims);
					orders.add(convert);
					dlm.remove(list.getSelectedIndex());
					orderID++;
				}else {
					JOptionPane.showMessageDialog(null, "Import Orders First"); 
				}
			}
		}));
		btnNewButton.setBounds(170, 277, 391, 37);
		contentPane.add(btnNewButton);
		
		btnDeleteOrders = new JButton("Delete An Order");										//complete 
		btnDeleteOrders.addActionListener((new ActionListener() {								//will delete orders
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loadedFile == true) {
					int orderNumber = Integer.parseInt(textField_1.getText());
					deleteOrderFromList(orderNumber);
				}else {
					JOptionPane.showMessageDialog(null, "Import Orders First"); 
				}
			}
		}));
		btnDeleteOrders.setBounds(170, 747, 391, 37);
		contentPane.add(btnDeleteOrders);
		
		btnDisplayCustomerList = new JButton("Display A Customer's Orders");					//add sorter if possible
		btnDisplayCustomerList.addActionListener(new ActionListener() {							//displays a customers orders
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loadedFile == true) { 
					//sort array list by increasing date
					String str = "";
					for(int i=0;i<orders.size();i++) {
						String[] grabbable = orders.get(i);
						if(grabbable[2].equals(textField_9.getText())) {
							for(int j=0;j<grabbable.length;j++) {
								str = str + grabbable[j]+"\n";
							}
						}
					}
					JTextArea textArea1 = new JTextArea(5, 20);
					textArea1.setText(str);
					textArea1.setEnabled(true);
					JOptionPane.showMessageDialog(null, textArea1); 
				}else {
					JOptionPane.showMessageDialog(null, "Import Orders First"); 
				}
			}
		});
		btnDisplayCustomerList.setBounds(170, 869, 391, 37);
		contentPane.add(btnDisplayCustomerList);
		
		btnInputAFile = new JButton("Import File");												//complete
		btnInputAFile.addActionListener((new ActionListener() {									//imports file
			@Override
			public void actionPerformed(ActionEvent e) {
				Scanner file = null;
				try{
					File inputFile = new File (textField.getText());
					file = new Scanner (inputFile);
					while (file.hasNextLine()){
						String cont= file.nextLine();
						String delims = "[,]";
						String [] orderListFromFile = cont.split(delims);
						everythingImported.add(orderListFromFile);
						loadedFile = true;
					}
					JOptionPane.showMessageDialog(null, "File Data Found"); 
				}
				catch ( FileNotFoundException fnfe ){
					System.out.println("Error: No File Found");
					JOptionPane.showMessageDialog(null, "File Not Found");
				}
				finally{
				file.close();
				}
			}
		}));
		btnInputAFile.setBounds(170, 52, 391, 37);
		contentPane.add(btnInputAFile);
		
		lblFileName = new JLabel("File Name: ");
		lblFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileName.setBounds(51, 14, 114, 14);
		contentPane.add(lblFileName);
		
		textField = new JTextField();
		textField.setBounds(170, 11, 391, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 708, 391, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblProductId = new JLabel("Product ID:");
		lblProductId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductId.setBounds(82, 711, 83, 14);
		contentPane.add(lblProductId);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 805, 699, 14);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 683, 699, 14);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 109, 699, 14);
		contentPane.add(separator_1_1);
		
		JLabel lblNewLabel = new JLabel("Type of Order:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(51, 341, 114, 14);
		contentPane.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(170, 338, 391, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(170, 376, 391, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(170, 418, 391, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(170, 464, 391, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(170, 505, 391, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(170, 548, 391, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(170, 592, 391, 20);
		contentPane.add(textField_8);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(51, 379, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(51, 421, 114, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(51, 467, 114, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Order Amount:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(51, 508, 114, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Period(if applicable):");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(51, 551, 114, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("End Date(if applicable):");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(51, 595, 114, 14);
		contentPane.add(lblNewLabel_6);
		
		list = new JList<String>();
		list.setVisibleRowCount(4);
		menuScrollPane = new JScrollPane(list);
		menuScrollPane.setBounds(170, 134, 391, 111);
		contentPane.add(menuScrollPane);
		
		JButton btnLoadOrders = new JButton("Load Orders");									//complete 
		btnLoadOrders.addActionListener((new ActionListener() {								//order load to see on list
			@Override
			public void actionPerformed(ActionEvent e) {
				if(loadedFile == true) {
					dlm = new DefaultListModel<String>();
					for(int i=0; i<everythingImported.size(); i++) {
						String[] grabHold = everythingImported.get(i);
						String str = "";
						for(int j=0; j<grabHold.length-1; j++) {
							str = str + grabHold[j] + ", ";
						}
						str = str + grabHold[grabHold.length-1];
						dlm.add(i, str);
					}
					list.setModel(dlm);
				}else {
					JOptionPane.showMessageDialog(null, "Import Orders First"); 
				}
			}
		}));
		btnLoadOrders.setBounds(28, 180, 109, 23);
		contentPane.add(btnLoadOrders);
		
		JLabel lblSelectOrdersTo = new JLabel("Select Orders to Add");
		lblSelectOrdersTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectOrdersTo.setBounds(309, 256, 114, 14);
		contentPane.add(lblSelectOrdersTo);
		
		JButton btnAddOrder = new JButton("Add Manual Input Order");							//complete
		btnAddOrder.addActionListener((new ActionListener() {									//manual add
			@Override
			public void actionPerformed(ActionEvent e) {			
				if(loadedFile == true) {
					if(textField_7.getText().equals("") || textField_7.getText().equals(null) || textField_8.getText().equals("") || textField_8.getText().equals(null)){
						String ordNum = Integer.toString(orderID);
						String[] array = {ordNum, textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), textField_6.getText()};
						orderID++;
						orders.add(array);
					}else {
						String ordNum = Integer.toString(orderID);
						String[] array2 = {ordNum, textField_2.getText(), textField_3.getText(), textField_4.getText(), textField_5.getText(), textField_6.getText(), textField_7.getText(), textField_8.getText()};
						orderID++;
						orders.add(array2);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Import Orders First"); 
				}
			}
		}));
		btnAddOrder.setBounds(170, 635, 391, 37);
		contentPane.add(btnAddOrder);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(170, 830, 391, 20);
		contentPane.add(textField_9);
		
		JLabel lblCustomerId = new JLabel("Customer ID:");
		lblCustomerId.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomerId.setBounds(82, 833, 83, 14);
		contentPane.add(lblCustomerId);
	}
	
	public void deleteOrderFromList(int OrderID) {//deletes an order 
		for(int i=0; i<orders.size(); i++) {
			String[] array = orders.get(i);
			String check = array[0];
			int checkNum = Integer.parseInt(check);
			if(OrderID == checkNum) {
				orders.remove(i);
			}
		}
	}
}