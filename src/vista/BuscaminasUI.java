package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controlador.JPanelBackgroundImage;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ImageIcon;

public class BuscaminasUI extends JFrame {

	private int heightScreen = (int) super.getToolkit().getScreenSize().getHeight();
	private int widthScreen = (int) super.getToolkit().getScreenSize().getWidth();
	protected JPanelBackgroundImage contentPane;
	protected Casillero casillero;
	protected JLabel txtCantidadMinas;
	protected JButton btnReiniciar;
	protected JPanel panelCentral;
	protected JLabel txtCantidadCasillas;
	private JPanel panelTiempo;
	protected JLabel txtTiempo;
	protected JPanel panelRecord;
	protected JLabel txtRecord;
	private JLabel lblNewLabel;
	private JPanel panelDificultad;
	protected JButton btnFacil;
	protected JButton btnMedio;
	protected JButton btnDificil;
	private JPanel panelTop;
	private JPanel panelTitulo;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public BuscaminasUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscaminasUI.class.getResource("/assets/iconGame.png")));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1406, 1056);
		setTitle("Buscaminas");
		setExtendedState(MAXIMIZED_BOTH);
		setMinimumSize(new Dimension((int) (this.widthScreen / 2.3), (int) (this.heightScreen / 1.2)));

		contentPane = new JPanelBackgroundImage("/assets/bg.jpg");
		contentPane.setOpaque(false);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));

		panelTop = new JPanel();
		panelTop.setOpaque(false);
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));

		panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.DARK_GRAY);
		panelTop.add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new BorderLayout(0, 0));

		JLabel lblTitulo = new JLabel("BUSCAMINAS");
		lblTitulo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelTitulo.add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setPreferredSize(new Dimension(widthScreen, (int) (heightScreen * 0.1f))); // ALTURA DEL TITULO
		lblTitulo.setBorder(new EmptyBorder(0, 20, 0, 0));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(Color.DARK_GRAY);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 60));

		lblNewLabel_1 = new JLabel("Desarrollado por Jos\u00E9 Emilio Guti\u00E9rrez");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBorder(new EmptyBorder(5, 0, 5, 10));
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBackground(new Color(80, 80, 80));
		panelTitulo.add(lblNewLabel_1, BorderLayout.SOUTH);

		JPanel panelOpciones = new JPanel();
		panelTop.add(panelOpciones, BorderLayout.WEST);
		panelOpciones.setBorder(null);
		panelOpciones.setOpaque(false);
		panelOpciones.setLayout(new BorderLayout(0, 0));

		panelDificultad = new JPanel();
		panelOpciones.add(panelDificultad, BorderLayout.WEST);
		panelDificultad.setOpaque(false);
		panelDificultad.setBorder(null);
		panelDificultad.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		btnFacil = new JButton("Facil");
		btnFacil.setVerticalAlignment(SwingConstants.TOP);
		btnFacil.setForeground(Color.WHITE);
		btnFacil.setFocusPainted(false);
		btnFacil.setName("15");
		btnFacil.setBackground(new Color(80, 80, 80));
		btnFacil.setBorder(new EmptyBorder(5, 30, 5, 30));
		btnFacil.setFont(new Font("Arial", Font.PLAIN, 12));
		panelDificultad.add(btnFacil);

		btnMedio = new JButton("Medio");
		btnMedio.setVerticalAlignment(SwingConstants.TOP);
		btnMedio.setForeground(Color.WHITE);
		btnMedio.setFocusPainted(false);
		btnMedio.setName("20");
		btnMedio.setBackground(new Color(80, 80, 80));
		btnMedio.setBorder(new EmptyBorder(5, 30, 5, 30));
		btnMedio.setFont(new Font("Arial", Font.PLAIN, 12));
		panelDificultad.add(btnMedio);

		btnDificil = new JButton("Dificil");
		btnDificil.setVerticalAlignment(SwingConstants.TOP);
		btnDificil.setForeground(Color.WHITE);
		btnDificil.setFocusPainted(false);
		btnDificil.setName("25");
		btnDificil.setBackground(new Color(80, 80, 80));
		btnDificil.setBorder(new EmptyBorder(5, 30, 5, 30));
		btnDificil.setFont(new Font("Arial", Font.PLAIN, 12));
		panelDificultad.add(btnDificil);

		JPanel panelReiniciar = new JPanel();
		panelReiniciar.setOpaque(false);
		panelReiniciar.setBorder(new EmptyBorder(0, 10, 0, 0));
		panelOpciones.add(panelReiniciar, BorderLayout.EAST);
		panelReiniciar.setLayout(new BorderLayout(0, 0));

		btnReiniciar = new JButton("Reiniciar");
		panelReiniciar.add(btnReiniciar, BorderLayout.NORTH);
		btnReiniciar.setForeground(Color.WHITE);
		btnReiniciar.setHorizontalAlignment(SwingConstants.LEFT);
		btnReiniciar.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnReiniciar.setBorder(new EmptyBorder(5, 10, 5, 10));
		btnReiniciar.setBackground(new Color(0, 167, 144));
		btnReiniciar.setFocusPainted(false);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setBackground(new Color(220, 220, 220));
		contentPane.add(panelPrincipal, BorderLayout.CENTER);

		panelCentral = new JPanel();
		panelCentral.setOpaque(false);
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setPreferredSize(new Dimension((int) (widthScreen / 3.5f), (int) (heightScreen / 1.85f)));
		panelCentral.setLayout(new BorderLayout(5, 0));

		casillero = new Casillero();
		casillero.setBackground(Color.WHITE);
		panelCentral.add(casillero, BorderLayout.CENTER);
		casillero.setBorder(null);
		casillero.setPreferredSize(new Dimension((int) panelCentral.getWidth(), (int) panelCentral.getWidth()));
		casillero.setLayout(new GridLayout(20, 20, 1, 1));
		panelPrincipal.add(panelCentral);

		JPanel panelInformacion = new JPanel();
		panelInformacion.setBorder(null);
		panelCentral.add(panelInformacion, BorderLayout.NORTH);
		panelInformacion.setOpaque(false);
		panelInformacion.setLayout(new BorderLayout(0, 0));

		JPanel panelContadores = new JPanel();
		panelContadores.setOpaque(false);
		panelInformacion.add(panelContadores, BorderLayout.EAST);
		panelContadores.setLayout(new GridLayout(0, 2, 5, 0));

		txtCantidadMinas = new JLabel();
		txtCantidadMinas.setIcon(new ImageIcon(new ImageIcon(BuscaminasUI.class.getResource("/assets/bandera.png"))
				.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		panelContadores.add(txtCantidadMinas);
		txtCantidadMinas.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidadMinas.setPreferredSize(new Dimension(70, 35));
		txtCantidadMinas.setBorder(new EmptyBorder(5, 5, 5, 5));
		txtCantidadMinas.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtCantidadMinas.setBounds(new Rectangle(0, 0, 50, 50));

		txtCantidadCasillas = new JLabel("");
		panelContadores.add(txtCantidadCasillas);
		txtCantidadCasillas.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidadCasillas.setPreferredSize(new Dimension(60, 35));
		txtCantidadCasillas.setOpaque(true);
		txtCantidadCasillas.setBorder(new EmptyBorder(5, 10, 5, 10));
		txtCantidadCasillas.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtCantidadCasillas.setBackground(getCasillero().getColorCasillaDesvelada());

		panelRecord = new JPanel();
		panelRecord.setVisible(false);
		panelRecord.setOpaque(false);
		panelInformacion.add(panelRecord, BorderLayout.CENTER);
		panelRecord.setLayout(new BoxLayout(panelRecord, BoxLayout.X_AXIS));

		lblNewLabel = new JLabel("Tiempo record:");
		lblNewLabel.setBorder(new EmptyBorder(0, 8, 0, 0));
		lblNewLabel.setForeground(new Color(227, 217, 166));
		lblNewLabel.setFont(new Font("SansSerif", Font.ITALIC, 15));
		panelRecord.add(lblNewLabel);

		txtRecord = new JLabel("New label");
		txtRecord.setBorder(new EmptyBorder(0, 8, 0, 0));
		txtRecord.setFont(new Font("SansSerif", Font.PLAIN, 16));
		panelRecord.add(txtRecord);

		panelTiempo = new JPanel();
		panelTiempo.setOpaque(false);
		panelInformacion.add(panelTiempo, BorderLayout.WEST);
		panelTiempo.setLayout(new BorderLayout(0, 0));

		txtTiempo = new JLabel("0");
		txtTiempo.setOpaque(true);
		txtTiempo.setBackground(Color.WHITE);
		txtTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTiempo.setPreferredSize(new Dimension(70, 35));
		txtTiempo.setForeground(Color.DARK_GRAY);
		txtTiempo.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtTiempo.setBorder(new EmptyBorder(5, 10, 5, 10));
		panelTiempo.add(txtTiempo);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));
	}

	public Casillero getCasillero() {
		return casillero;
	}

	public JButton getBtnReiniciar() {
		return btnReiniciar;
	}
}
