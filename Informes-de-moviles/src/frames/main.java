package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Movil;
import clases.Observacion;
import clases.Sistema;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;

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
	ButtonGroup group = new ButtonGroup();

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
		comboVerObservaciones.removeAllItems();

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
		setBounds(100, 100, 769, 472);
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

					if (textAgregarMovil.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, "no se asign? dominio");

					} else {
						String dominio = textAgregarMovil.getText();

						Movil movilAAgregar = new Movil(dominio);
						getSistema().getMoviles().add(movilAAgregar);
						JOptionPane.showMessageDialog(null, "Movil " + dominio + " agregado");
						textAgregarMovil.setText("");
						cargarMoviles();
					}
				}

			}
		});
		btnAgregarMovil.setBounds(127, 10, 149, 20);
		contentPane.add(btnAgregarMovil);

		JLabel lblDominio = new JLabel("");
		comboVerObservaciones.setBounds(22, 42, 140, 22);
		contentPane.add(comboVerObservaciones);

		JScrollPane scrollMes1 = new JScrollPane();
		scrollMes1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMes1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollMes1.setBounds(22, 75, 390, 326);
		contentPane.add(scrollMes1);

		JTextArea textArea = new JTextArea();
		scrollMes1.setViewportView(textArea);

		JButton btnVerObservaciones = new JButton("ver observaciones");
		btnVerObservaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerObservaciones) {
					Movil movilSeleccionado = (Movil) comboVerObservaciones.getSelectedItem();
					if (movilSeleccionado == null) {
						JOptionPane.showMessageDialog(null, "no hay un movil seleccionado");
					} else {
						textArea.setText("");
						
						List<Observacion> obsMecanicas = movilSeleccionado.getObsSinSolucionar().stream().filter(o -> o.getTipo() == "Mecanica").toList();
						List<Observacion> obsEsteticas = movilSeleccionado.getObsSinSolucionar().stream().filter(o -> o.getTipo() == "Estetica").toList();
						
						textArea.append("Observaciones Mecanicas:"+ "\n");

						for (Observacion observacion : obsMecanicas) {
							textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
									+ observacion.getFecha().getMonthValue() + "\n");
						}
						textArea.append(""+ "\n");
						textArea.append(""+ "\n");
						textArea.append("________________________________________"+ "\n");
						textArea.append(""+ "\n");
						textArea.append("Observaciones Esteticas:"+ "\n");

						for (Observacion observacion : obsEsteticas) {
							textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
									+ observacion.getFecha().getMonthValue() + "\n");
						}
					}

				}
			}
		});
		btnVerObservaciones.setBounds(166, 41, 140, 23);
		contentPane.add(btnVerObservaciones);

		JLabel lblModificarMovil = new JLabel("Modificar Movil");
		lblModificarMovil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModificarMovil.setBounds(520, 10, 149, 18);
		contentPane.add(lblModificarMovil);

		comboVerMovil.setBounds(466, 43, 140, 20);
		contentPane.add(comboVerMovil);

		JButton btnVerMovil = new JButton("ver");
		btnVerMovil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerMovil) {
					Movil movilSeleccionado = (Movil) comboVerMovil.getSelectedItem();
					if (movilSeleccionado == null) {
						JOptionPane.showMessageDialog(null, "No hay movil seleccionado");
					} else {
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
			}
		});
		btnVerMovil.setBounds(616, 42, 89, 23);
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
		btnActualizarMovil.setBounds(532, 296, 111, 23);
		contentPane.add(btnActualizarMovil);

		JRadioButton rdMecanica = new JRadioButton("Mecanica");
		rdMecanica.setBounds(422, 202, 86, 23);
		contentPane.add(rdMecanica);
		group.add(rdMecanica);

		JRadioButton rdEstetica = new JRadioButton("Estetica");
		rdEstetica.setBounds(504, 202, 86, 23);
		contentPane.add(rdEstetica);
		group.add(rdEstetica);

		comboQuitarObservacion.setBounds(422, 234, 325, 20);
		contentPane.add(comboQuitarObservacion);

		JButton btnQuitarObservacion = new JButton("quitar observacion");
		btnQuitarObservacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnQuitarObservacion) {

					if (comboQuitarObservacion.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "No hay observacion seleccionada");
					} else {
						String observacionSeleccionada = comboQuitarObservacion.getSelectedItem().toString();
						for (Observacion observacion : getMovilActualizandose().getObsSinSolucionar()) {
							if (observacion.toString() == observacionSeleccionada) {
								observacion.setFueSolucionado(true);
							}
						}
						JOptionPane.showMessageDialog(null, "observacion solucionada");
						cargarObservaciones();

					}
				}

			}
		});
		btnQuitarObservacion.setBounds(422, 265, 149, 20);
		contentPane.add(btnQuitarObservacion);

		JButton btnLimpiarTodas = new JButton("limpiar todas");
		btnLimpiarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnLimpiarTodas) {
					JOptionPane.showConfirmDialog(null, "?sacar todas las observaciones?");
					if (getMovilActualizandose().getObservaciones().isEmpty() || getMovilActualizandose() == null) {
						JOptionPane.showMessageDialog(null, "No hay observaciones para limpiar");
					} else {
						getMovilActualizandose().getObsSinSolucionar().stream().forEach(o -> o.setFueSolucionado(true));
						cargarObservaciones();
					}

				}
			}
		});
		btnLimpiarTodas.setBounds(598, 265, 125, 20);
		contentPane.add(btnLimpiarTodas);

		checkEnElTaller.setBounds(480, 125, 97, 23);
		contentPane.add(checkEnElTaller);

		checkNecesitaTaller.setBounds(480, 151, 125, 23);
		contentPane.add(checkNecesitaTaller);

		textAgregarObs = new JTextField();
		textAgregarObs.setBounds(422, 175, 325, 20);
		contentPane.add(textAgregarObs);
		textAgregarObs.setColumns(10);

		JButton btnAgregarObs = new JButton("agregar observacion");
		btnAgregarObs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAgregarObs) {

					if (textAgregarObs.getText() == "" || !(rdEstetica.isSelected() || rdMecanica.isSelected())) {
						JOptionPane.showMessageDialog(null,
								"no se selecciono el tipo de observacion, o no se escribio ninguna");
					} else {
						String tipo;
						if (rdEstetica.isSelected()) {
							tipo = "Estetica";
						} else {
							tipo = "Mecanica";
						}

						Observacion obs = new Observacion(textAgregarObs.getText(), getMovilActualizandose(), tipo);
						getMovilActualizandose().getObservaciones().add(0, obs);
						JOptionPane.showMessageDialog(null, "observacion agregada");
						textAgregarObs.setText("");
						group.clearSelection();
						cargarObservaciones();
					}
				}
			}
		});
		btnAgregarObs.setBounds(591, 203, 156, 20);
		contentPane.add(btnAgregarObs);

		JButton btnVerSolucionadas = new JButton("ver solucionadas");
		btnVerSolucionadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnVerSolucionadas) {
					Movil movilSeleccionado = (Movil) comboVerObservaciones.getSelectedItem();
					if (movilSeleccionado == null) {
						JOptionPane.showMessageDialog(null, "No hay movil seleccionado");
					} else {
						textArea.setText("");
						
						List<Observacion> obsMecanicas = movilSeleccionado.getObsSolucionadas().stream().filter(o -> o.getTipo() == "Mecanica").toList();
						List<Observacion> obsEsteticas = movilSeleccionado.getObsSolucionadas().stream().filter(o -> o.getTipo() == "Estetica").toList();
						
						textArea.append("Observaciones Mecanicas:"+ "\n");

						for (Observacion observacion : obsMecanicas) {
							textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
									+ observacion.getFecha().getMonthValue() + "\n");
						}
						textArea.append(""+ "\n");
						textArea.append(""+ "\n");
						textArea.append("________________________________________"+ "\n");
						textArea.append(""+ "\n");
						textArea.append("Observaciones Esteticas:"+ "\n");

						for (Observacion observacion : obsEsteticas) {
							textArea.append(observacion.getObs() + " | " + observacion.getFecha().getDayOfMonth() + "/"
									+ observacion.getFecha().getMonthValue() + "\n");
						}
					}

				}
			}
		});
		btnVerSolucionadas.setBounds(316, 42, 140, 23);
		contentPane.add(btnVerSolucionadas);

		textKm.setBounds(587, 98, 86, 20);
		contentPane.add(textKm);
		textKm.setColumns(10);

		checkCriquet.setBounds(626, 125, 97, 23);
		contentPane.add(checkCriquet);

		checkMatafuego.setBounds(626, 151, 97, 23);
		contentPane.add(checkMatafuego);

		lblDominio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDominio.setBounds(549, 69, 156, 18);
		contentPane.add(lblDominio);

		JLabel lblNewLabel = new JLabel("Kilometros");
		lblNewLabel.setBounds(504, 101, 64, 14);
		contentPane.add(lblNewLabel);

	}
}
