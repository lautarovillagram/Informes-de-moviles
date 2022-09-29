package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Movil;
import clases.Observacion;
import clases.Sistema;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField textAgregarMovil;
	private JTextField textAgregarObs;
	private JTextField textKm;
	JComboBox comboVerObservaciones = new JComboBox();
	JComboBox comboVerMovil = new JComboBox();
	JComboBox comboQuitarObservacion = new JComboBox();
	JCheckBox checkMatafuego = new JCheckBox("matafuego");
	JCheckBox checkCriquet = new JCheckBox("criquet");
	JCheckBox checkNecesitaTaller = new JCheckBox("necesita taller");
	JCheckBox checkEnElTaller = new JCheckBox("en el taller");
	private Sistema sistema;

	private Movil movilActualizandose;

	public Movil getMovilActualizandose() {
		return movilActualizandose;
	}

	public void setMovilActualizandose(Movil movilActualizandose) {
		this.movilActualizandose = movilActualizandose;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

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

	public void cargarMoviles() {
		comboVerMovil.removeAllItems();
		comboQuitarObservacion.removeAllItems();

		for (Movil movil : getSistema().getMoviles()) {
			comboVerMovil.addItem(movil);
			comboVerObservaciones.addItem(movil);

		}

	}

		
	public void cargarObservaciones() {
		comboQuitarObservacion.removeAllItems();
		Movil movilSeleccionado = (Movil) comboVerMovil.getSelectedItem();
		for (Observacion observacion : movilSeleccionado.getObservaciones()) {
			comboQuitarObservacion.addItem(observacion);
		}
	}
	
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1240, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSistema(new Sistema());
		cargarMoviles();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textAgregarMovil = new JTextField();
		textAgregarMovil.setBounds(22, 11, 86, 20);
		contentPane.add(textAgregarMovil);
		textAgregarMovil.setColumns(10);

		JButton btnAgregarMovil = new JButton("agregar movil");
		btnAgregarMovil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarMovil) {

					Movil movilAAgregar = new Movil(textAgregarMovil.getText());
					getSistema().getMoviles().add(movilAAgregar);
					JOptionPane.showMessageDialog(null, "Movil agregado");
					textAgregarMovil.setText("");
					cargarMoviles();
				}

			}
		});
		btnAgregarMovil.setBounds(127, 10, 149, 20);
		contentPane.add(btnAgregarMovil);

		comboVerObservaciones.setBounds(22, 67, 180, 20);
		contentPane.add(comboVerObservaciones);

		JScrollPane scrollMes1 = new JScrollPane();
		scrollMes1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes1.setBounds(22, 112, 538, 351);
		contentPane.add(scrollMes1);

		JTextArea textArea = new JTextArea();
		scrollMes1.setViewportView(textArea);

		JButton btnVerObservaciones = new JButton("ver observaciones");
		btnVerObservaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerObservaciones) {
					Movil movilSeleccionado = (Movil) comboVerObservaciones.getSelectedItem();
					textArea.setText("");

					for (Observacion observacion : movilSeleccionado.getObservaciones()) {
						textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
								+ observacion.getFecha().getMonthValue() + "\n");
					}

				}
			}
		});
		btnVerObservaciones.setBounds(208, 67, 191, 21);
		contentPane.add(btnVerObservaciones);

		JLabel lblModificarMovil = new JLabel("Modificar Movil");
		lblModificarMovil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModificarMovil.setBounds(787, 10, 149, 18);
		contentPane.add(lblModificarMovil);

		comboVerMovil.setBounds(697, 54, 140, 20);
		contentPane.add(comboVerMovil);

		JButton btnVerMovil = new JButton("ver");
		btnVerMovil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerMovil) {
					Movil movilSeleccionado = (Movil) comboVerMovil.getSelectedItem();
					movilActualizandose = movilSeleccionado;
					checkCriquet.setSelected(movilSeleccionado.isTieneCriquet());
					checkMatafuego.setSelected(movilSeleccionado.isTieneMatafuego());
					checkNecesitaTaller.setSelected(movilSeleccionado.isNecesitaTaller());
					checkEnElTaller.setSelected(movilSeleccionado.isEnElTaller());
					cargarObservaciones();
					
					
				}
			}
		});
		btnVerMovil.setBounds(899, 53, 89, 23);
		contentPane.add(btnVerMovil);

		JButton btnActualizarMovil = new JButton("actualizar");
		btnActualizarMovil.setBounds(825, 206, 111, 23);
		contentPane.add(btnActualizarMovil);

		comboQuitarObservacion.setBounds(595, 175, 325, 20);
		contentPane.add(comboQuitarObservacion);

		JButton btnQuitarObservacion = new JButton("quitar observacion");
		btnQuitarObservacion.setBounds(930, 175, 149, 20);
		contentPane.add(btnQuitarObservacion);

		JButton btnLimpiarTodas = new JButton("limpiar todas");
		btnLimpiarTodas.setBounds(1089, 175, 125, 20);
		contentPane.add(btnLimpiarTodas);

		checkEnElTaller.setBounds(665, 83, 97, 23);
		contentPane.add(checkEnElTaller);

		checkNecesitaTaller.setBounds(764, 83, 125, 23);
		contentPane.add(checkNecesitaTaller);

		textAgregarObs = new JTextField();
		textAgregarObs.setBounds(595, 144, 325, 20);
		contentPane.add(textAgregarObs);
		textAgregarObs.setColumns(10);

		JButton btnAgregarObs = new JButton("agregar observacion");
		btnAgregarObs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAgregarObs.setBounds(930, 144, 168, 20);
		contentPane.add(btnAgregarObs);

		JButton btnVerSolucionadas = new JButton("ver solucionadas");
		btnVerSolucionadas.setBounds(409, 67, 147, 21);
		contentPane.add(btnVerSolucionadas);

		textKm = new JTextField();
		textKm.setBounds(595, 113, 86, 20);
		contentPane.add(textKm);
		textKm.setColumns(10);

		JButton btnActualizarKm = new JButton("actualizar kilometros");
		btnActualizarKm.setBounds(697, 113, 180, 20);
		contentPane.add(btnActualizarKm);

		checkCriquet.setBounds(899, 83, 97, 23);
		contentPane.add(checkCriquet);

		checkMatafuego.setBounds(998, 83, 97, 23);
		contentPane.add(checkMatafuego);

		JLabel lblInfoMovil = new JLabel("");
		lblInfoMovil.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfoMovil.setBounds(32, 92, 516, 20);
		contentPane.add(lblInfoMovil);
	}
}
