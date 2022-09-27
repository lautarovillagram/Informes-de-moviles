package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(22, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("agregar movil");
		btnNewButton.setBounds(127, 10, 149, 20);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(74, 221, 180, 20);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 581, 834, -295);
		contentPane.add(scrollPane);
		
		JScrollPane scrollMes1 = new JScrollPane();
		scrollMes1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes1.setBounds(22, 258, 538, 365);
		contentPane.add(scrollMes1);
		
		JTextArea textArea = new JTextArea();
		scrollMes1.setViewportView(textArea);
		
		JButton btnNewButton_1 = new JButton("ver observaciones");
		btnNewButton_1.setBounds(264, 221, 191, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Modificar Movil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(1021, 10, 149, 18);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(899, 54, 140, 20);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("ver");
		btnNewButton_2.setBounds(1103, 53, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("actualizar");
		btnNewButton_3.setBounds(1021, 332, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(807, 301, 325, 20);
		contentPane.add(comboBox_2);
		
		JButton btnNewButton_4 = new JButton("quitar observacion");
		btnNewButton_4.setBounds(1142, 300, 149, 20);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("limpiar todas");
		btnNewButton_5.setBounds(1301, 301, 125, 20);
		contentPane.add(btnNewButton_5);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("en el taller");
		chckbxNewCheckBox.setBounds(960, 122, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("necesita taller");
		chckbxNewCheckBox_1.setBounds(1083, 122, 97, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(807, 181, 325, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_4_1 = new JButton("agregar observacion");
		btnNewButton_4_1.setBounds(1142, 180, 149, 20);
		contentPane.add(btnNewButton_4_1);
	}
}
