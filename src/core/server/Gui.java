import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class Gui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -744737727656894210L;
	private Server moderador;
	private Quadro quadro1,quadro2;

	public Gui(Frame owner) {
		super();
		initComponents();
	}

	public Gui(Dialog owner) {
		super();
		initComponents();
	}

	
	
	public Gui(Quadro quadro1, Quadro quadro2 , Server moderador) {
		this.moderador = moderador;
		this.quadro1 = quadro1;
		this.quadro2 = quadro2;
		
		
//		this.setResizable(false);
		initComponents();
		Dimension dimensionSize = new Dimension(800, 600);
		this.setPreferredSize(dimensionSize);
		Container contentPane = getContentPane();
		Dimension preferredSize = new Dimension();
		preferredSize.width = 800;
		preferredSize.height = 480;
		quadro1.setMinimumSize(preferredSize);
		quadro1.setPreferredSize(preferredSize);
		quadro2.setMinimumSize(preferredSize);
		quadro2.setPreferredSize(preferredSize);
		CellConstraints cc = new CellConstraints();
		contentPane.add(quadro1, cc.xywh(1, 8, 7, 1));
		contentPane.add(quadro2, cc.xywh(8, 8, 7, 1));
		pack();
		setLocationRelativeTo(getOwner());
		quadro1.clear();
		quadro2.clear();
	}


	private void buttonExecutarActionPerformed(ActionEvent e) {
		quadro1.clear();
		quadro2.clear();
		moderador.setTruckPosition(xisValue.getText(),ypsolonValue.getText(),anguloValue.getText());
		moderador.drive(Double.valueOf(stepValue.getText()).doubleValue());
	}


	private void initComponents() {
		label2 = new JLabel();
		xisValue = new JTextField();
		label1 = new JLabel();
		stepValue = new JTextField();
		label4 = new JLabel();
		anguloValue = new JTextField();
		label3 = new JLabel();
		ypsolonValue = new JTextField();
		buttonExecutar = new JButton();
		separator1 = new JSeparator();
		CellConstraints cc = new CellConstraints();

		xisValue.setText("0.2");
		ypsolonValue.setText("0.2");
		anguloValue.setText("30");
		stepValue.setText("1");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(18)),
				new RowSpec(RowSpec.TOP, Sizes.dluY(600), FormSpec.NO_GROW),
				new RowSpec(Sizes.DLUY1),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY1),
				FormFactory.DEFAULT_ROWSPEC
			}));

		//---- label1 ----
		label1.setText("Passo(0-1):");
		contentPane.add(label1, cc.xywh(3, 3,2,1));
		contentPane.add(stepValue, cc.xywh(5, 3,2,1));
		
		//---- label2 ----
		label2.setText("Insira o x(0-1):");
		contentPane.add(label2, cc.xywh(7, 3,2,1));
		contentPane.add(xisValue, cc.xywh(9, 3,2,1));

		//---- label4 ----
		label4.setText("Insira o \u00e2ngulo:");
		contentPane.add(label4, cc.xywh(3, 5,2,1));
		contentPane.add(anguloValue, cc.xywh(5, 5,2,1));

		//---- label3 ----
		label3.setText("Insira o y(0-1):");
		contentPane.add(label3, cc.xywh(7, 5,2,1));
		contentPane.add(ypsolonValue, cc.xywh(9, 5,2,1));

		//---- buttonExecutar ----
		buttonExecutar.setText("Iniciar/Reiniciar");
		buttonExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonExecutarActionPerformed(e);
			}
		});
		contentPane.add(buttonExecutar, cc.xywh(3, 7,3,1));
		contentPane.add(separator1, cc.xywh(3, 12, 3, 1));
		setSize(725, 725);
		setLocationRelativeTo(getOwner());
	}

	private JLabel label1;
	private JTextField stepValue;
	private JLabel label2;
	private JTextField xisValue;
	private JLabel label4;
	private JTextField anguloValue;
	private JLabel label3;
	private JTextField ypsolonValue;
	private JButton buttonExecutar;
	private JSeparator separator1;
}
