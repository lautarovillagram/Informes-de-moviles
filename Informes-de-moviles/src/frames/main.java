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
import java.util.List;
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

		for (Observacion observacion : getMovilActualizandose().getObsSinSolucionar()) {
			comboQuitarObservacion.addItem(observacion);
		}
	}

	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSistema(new Sistema());
		this.setLocationRelativeTo(null);
		cargarMoviles();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		textKm = new JTextField();
		textAgregarMovil = new JTextField();
		textAgregarMovil.setBounds(22, 11, 86, 20);
		contentPane.add(textAgregarMovil);
		textAgregarMovil.setColumns(10);

		JButton btnAgregarMovil = new JButton("agregar movil");
		btnAgregarMovil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarMovil) {

					String dominio = textAgregarMovil.getText();

					Movil movilAAgregar = new Movil(dominio);
					getSistema().getMoviles().add(movilAAgregar);
					JOptionPane.showMessageDialog(null, "Movil " + dominio + " agregado");
					textAgregarMovil.setText("");
					cargarMoviles();
				}

			}
		});
		btnAgregarMovil.setBounds(127, 10, 149, 20);
		contentPane.add(btnAgregarMovil);

		JLabel lblDominio = new JLabel("");
		comboVerObservaciones.setBounds(22, 67, 180, 20);
		contentPane.add(comboVerObservaciones);

		JScrollPane scrollMes1 = new JScrollPane();
		scrollMes1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes1.setBounds(22, 112, 390, 351);
		contentPane.add(scrollMes1);

		JTextArea textArea = new JTextArea();
		scrollMes1.setViewportView(textArea);

		JButton btnVerObservaciones = new JButton("ver observaciones");
		btnVerObservaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerObservaciones) {
					Movil movilSeleccionado = (Movil) comboVerObservaciones.getSelectedItem();
					textArea.setText("");

					for (Observacion observacion : movilSeleccionado.getObsSinSolucionar()) {
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
		lblModificarMovil.setBounds(520, 10, 149, 18);
		contentPane.add(lblModificarMovil);

		comboVerMovil.setBounds(444, 40, 140, 20);
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
					textKm.setText(movilSeleccionado.getKilometraje());
					lblDominio.setText("Movil " + movilSeleccionado.toString());
					cargarObservaciones();

				}
			}
		});
		btnVerMovil.setBounds(627, 39, 89, 23);
		contentPane.add(btnVerMovil);

		JButton btnActualizarMovil = new JButton("actualizar");
		btnActualizarMovil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnActualizarMovil) {
					String dominio = getMovilActualizandose().getDominio();
					String kilometros = textKm.getText();
					boolean necesitaTaller = checkNecesitaTaller.isSelected();
					boolean enElTaller = checkEnElTaller.isSelected();
					boolean matafuego = checkMatafuego.isSelected();
					boolean criquet = checkCriquet.isSelected();

					for (Movil movil : getSistema().getMoviles()) {
						if (movil.toString() == dominio) {
							movil.setKilometraje(kilometros);
							movil.setNecesitaTaller(necesitaTaller);
							movil.setEnElTaller(enElTaller);
							movil.setTieneMatafuego(matafuego);
							movil.setTieneCriquet(criquet);
							break;
						}
					}

					JOptionPane.showMessageDialog(null, "Movil " + dominio + " actualizado");

				}
			}
		});
		btnActualizarMovil.setBounds(536, 324, 111, 23);
		contentPane.add(btnActualizarMovil);

		comboQuitarObservacion.setBounds(422, 262, 325, 20);
		contentPane.add(comboQuitarObservacion);

		JButton btnQuitarObservacion = new JButton("quitar observacion");
		btnQuitarObservacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnQuitarObservacion) {
					String observacionSeleccionada = (String) comboQuitarObservacion.getSelectedItem().toString();
					for (Observacion observacion : getMovilActualizandose().getObsSinSolucionar()) {
						if (observacion.toString() == observacionSeleccionada) {
							observacion.setFueSolucionado(true);
						}
					}
					JOptionPane.showMessageDialog(null, "observacion solucionada");
					cargarObservaciones();

				}
			}
		});
		btnQuitarObservacion.setBounds(422, 293, 149, 20);
		contentPane.add(btnQuitarObservacion);

		JButton btnLimpiarTodas = new JButton("limpiar todas");
		btnLimpiarTodas.setBounds(622, 293, 125, 20);
		contentPane.add(btnLimpiarTodas);

		checkEnElTaller.setBounds(484, 133, 97, 23);
		contentPane.add(checkEnElTaller);

		checkNecesitaTaller.setBounds(484, 159, 125, 23);
		contentPane.add(checkNecesitaTaller);

		textAgregarObs = new JTextField();
		textAgregarObs.setBounds(422, 200, 325, 20);
		contentPane.add(textAgregarObs);
		textAgregarObs.setColumns(10);

		JButton btnAgregarObs = new JButton("agregar observacion");
		btnAgregarObs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarObs) {
					Observacion obs = new Observacion(textAgregarObs.getText(), getMovilActualizandose());
					getMovilActualizandose().getObservaciones().add(0, obs);
					JOptionPane.showMessageDialog(null, "observacion agregada");
					textAgregarObs.setText("");
					cargarObservaciones();
				}
			}
		});
		btnAgregarObs.setBounds(501, 231, 168, 20);
		contentPane.add(btnAgregarObs);

		JButton btnVerSolucionadas = new JButton("ver solucionadas");
		btnVerSolucionadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerSolucionadas) {
					Movil movilSeleccionado = (Movil) comboVerObservaciones.getSelectedItem();
					textArea.setText("");

					for (Observacion observacion : movilSeleccionado.getObsSolucionadas()) {
						textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
								+ observacion.getFecha().getMonthValue() + "\n");
					}

				}
			}
		});
		btnVerSolucionadas.setBounds(127, 90, 147, 21);
		contentPane.add(btnVerSolucionadas);

		textKm.setBounds(598, 106, 86, 20);
		contentPane.add(textKm);
		textKm.setColumns(10);

		checkCriquet.setBounds(630, 133, 97, 23);
		contentPane.add(checkCriquet);

		checkMatafuego.setBounds(630, 159, 97, 23);
		contentPane.add(checkMatafuego);

		lblDominio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDominio.setBounds(501, 69, 156, 18);
		contentPane.add(lblDominio);
		
		JLabel lblNewLabel = new JLabel("Kilometros");
		lblNewLabel.setBounds(515, 109, 64, 14);
		contentPane.add(lblNewLabel);
	}
}
