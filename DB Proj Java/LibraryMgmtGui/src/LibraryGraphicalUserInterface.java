import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class LibraryGraphicalUserInterface {

	private JFrame frmLibraryGui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryGraphicalUserInterface window = new LibraryGraphicalUserInterface();
					window.frmLibraryGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibraryGraphicalUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibraryGui = new JFrame();
		frmLibraryGui.setTitle("LIBRARY GUI");
		frmLibraryGui.setBounds(100, 100, 450, 300);
		frmLibraryGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBookSearch = new JButton("BOOK SEARCH");
		
		JButton btnBookLoans = new JButton("BOOK LOANS");
		
		JButton btnBorrowerManagement = new JButton("BORROWER MANAGEMENT");
		GroupLayout groupLayout = new GroupLayout(frmLibraryGui.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(btnBookSearch)
					.addGap(29)
					.addComponent(btnBookLoans)
					.addGap(32)
					.addComponent(btnBorrowerManagement)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBookSearch)
						.addComponent(btnBookLoans)
						.addComponent(btnBorrowerManagement))
					.addContainerGap(217, Short.MAX_VALUE))
		);
		frmLibraryGui.getContentPane().setLayout(groupLayout);
	}
}
