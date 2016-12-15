import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.Box;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton selectSource = new JButton("Select bronlocatie");
		selectSource.setBounds(10, 25, 138, 23);
		selectSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(selectSource);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 59, 138, 23);
		contentPane.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 93, 89, 23);
		contentPane.add(comboBox);
		
		JMenu mnNewMenu = new JMenu("New menu");
		mnNewMenu.setBounds(10, 142, 107, 22);
		contentPane.add(mnNewMenu);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(10, 130, 138, 1);
		contentPane.add(horizontalStrut);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 129, 131, 2);
		contentPane.add(separator);
	}
}
