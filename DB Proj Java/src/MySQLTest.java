//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
package testPanel;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridBagLayout;
import java.sql.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class testPanel {
    JFrame guiFrame;
    CardLayout cards;
    JPanel cardPanel;
    JButton BookSearch;
    JPanel firstCard,secondCard,thirdCard;
    private JTextField tbook_id;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_11;
    private JTextField textField_12;
    JScrollPane scrollPane;
    JTextPane textPane ;
    
    
 // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/Library";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";


    public static void main(String[] args) {
     
    	
    	   

         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new testPanel();         
             }
         });
              
    }
    
    public testPanel()
    { 
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("LIBRARY MANANGEMENT SYSTEM");
        guiFrame.setSize(469,380);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.getContentPane().setLayout(new BorderLayout());
        
        //creating a border to highlight the JPanel areas
        Border outline = BorderFactory.createLineBorder(Color.black);
        
        JPanel tabsPanel = new JPanel();
        tabsPanel.setBorder(outline);
              
        BookSearch = new JButton("BOOK SEARCH");
        
        BookSearch.setActionCommand("BOOK SEARCH");
        BookSearch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                
                	cards.show(cardPanel, "BOOK SEARCH");
            }
        });
        tabsPanel.add(BookSearch);
        
		
		JButton BookLoans = new JButton("BOOK LOANS");
		
		BookLoans.setActionCommand("BOOK LOANS");
		BookLoans.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	cards.show(cardPanel, "BOOK LOANS");
            }
        });
        tabsPanel.add(BookLoans);
        
		
		JButton BorrowerManagement = new JButton("BORROWER MANAGEMENT");
		
		BorrowerManagement.setActionCommand("Switch Card");
		BorrowerManagement.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	cards.show(cardPanel,"BORROW MANAGEMENT");
            }
        });
        tabsPanel.add(BorrowerManagement);
        
        
        guiFrame.getContentPane().add(tabsPanel,BorderLayout.NORTH);
        
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Book Search");
        
        firstCard = new JPanel();
        firstCard.setBackground(Color.ORANGE);
        
                
        secondCard = new JPanel();
        secondCard.setBackground(Color.ORANGE);
                
        thirdCard = new JPanel();
        thirdCard.setBackground(Color.ORANGE);
        
        
        
        cardPanel.add(firstCard, "BOOK SEARCH");
        
        JLabel lblBookId = new JLabel("BOOK ID :");
        lblBookId.setBounds(10, 22, 69, 14);
        
        tbook_id = new JTextField();
        tbook_id.setBounds(104, 19, 123, 20);
        tbook_id.setColumns(10);
        
        JLabel lblTitle = new JLabel("TITLE :");
        lblTitle.setBounds(267, 22, 54, 14);
        
        JLabel lblAuthorName = new JLabel("AUTHOR NAME :");
        lblAuthorName.setBounds(10, 60, 109, 14);
        
        JButton btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(10, 103, 92, 23);
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		Connection conn = null;
        		PreparedStatement prepStmt = null;
        		ResultSet rs = null;
        		try {
        			Class.forName("com.mysql.jdbc.Driver").newInstance();
        			String connectionUrl = "jdbc:mysql://localhost/Library";
        			String connectionUser = "root";
        			String connectionPassword = "";
        			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        			String sqlStmt = "SELECT * FROM LIBRARY.book;";
        	
        			prepStmt = conn.prepareStatement(sqlStmt);
        			
        			prepStmt.setString(1, tbook_id.getText());
        			//prepStmt.setString(2, "%The Selfish Gene%");
        			System.out.println("Prepared Statement after bind variables set:\n\t" + prepStmt.toString());
        			rs = prepStmt.executeQuery();
        			String ans="";
        			while (rs.next()) {
        				ans=ans.concat("\n"+rs.getString("book_id")+"\t"+rs.getString("title"));
        			}
        			textPane.setText(ans);
        		} catch (Exception e) {
        			e.printStackTrace();
        		} 
        		
         	   }
         	  
        });
        
        JButton btnBack = new JButton("RESET");
        btnBack.setBounds(126, 103, 86, 23);
        
        textField_1 = new JTextField();
        textField_1.setBounds(342, 19, 101, 20);
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setBounds(150, 57, 109, 20);
        textField_2.setColumns(10);
        firstCard.setLayout(null);
        firstCard.add(lblBookId);
        firstCard.add(tbook_id);
        firstCard.add(lblTitle);
        firstCard.add(textField_1);
        firstCard.add(lblAuthorName);
        firstCard.add(textField_2);
        firstCard.add(btnSearch);
        firstCard.add(btnBack);
        
        textPane = new JTextPane();
        textPane.setBounds(20, 137, 397, 144);
        firstCard.add(textPane);
        
        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(20, 137, 397, 144);
        firstCard.add(scrollPane);
        cardPanel.add(secondCard, "BOOK LOANS");
        
        JLabel lblCheckingInBook = new JLabel("CHECKING IN BOOK");
        
        JLabel lblCheckingOutBook = new JLabel("CHECKING OUT BOOK");
        
        JLabel lblBookId_1 = new JLabel("BOOK ID");
        
        JLabel lblCardNo = new JLabel("CARD NO.");
        
        JLabel lblDateIn = new JLabel("BORROWER NAME");
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        
        JLabel lblBookId_2 = new JLabel("BOOK ID");
        
        JLabel lblCardNo_1 = new JLabel("CARD NO.");
        
        JLabel lblBranchNo = new JLabel("BRANCH NO.");
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        GroupLayout gl_secondCard = new GroupLayout(secondCard);
        gl_secondCard.setHorizontalGroup(
        	gl_secondCard.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_secondCard.createSequentialGroup()
        			.addGap(21)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_secondCard.createSequentialGroup()
        					.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblBookId_1)
        						.addComponent(lblCardNo)
        						.addComponent(lblDateIn))
        					.addGap(25)
        					.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(lblCheckingInBook))
        			.addGap(71)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblCheckingOutBook)
        				.addGroup(gl_secondCard.createSequentialGroup()
        					.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblBookId_2)
        						.addComponent(lblBranchNo)
        						.addComponent(lblCardNo_1))
        					.addGap(18)
        					.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
        );
        gl_secondCard.setVerticalGroup(
        	gl_secondCard.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_secondCard.createSequentialGroup()
        			.addGap(23)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCheckingOutBook)
        				.addComponent(lblCheckingInBook))
        			.addGap(18)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblBookId_1)
        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblBookId_2)
        				.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCardNo)
        				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCardNo_1)
        				.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(gl_secondCard.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblDateIn)
        				.addGroup(gl_secondCard.createParallelGroup(Alignment.BASELINE)
        					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblBranchNo)
        					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(155, Short.MAX_VALUE))
        );
        secondCard.setLayout(gl_secondCard);
        cardPanel.add(thirdCard, "BORROW MANAGEMENT");
        
        JLabel lblNameOfBorrower = new JLabel("NAME OF BORROWER");
        
        textField_11 = new JTextField();
        textField_11.setColumns(10);
        
        JLabel lblAddressOfBorrower = new JLabel("ADDRESS OF BORROWER");
        
        textField_12 = new JTextField();
        textField_12.setColumns(10);
        GroupLayout gl_thirdCard = new GroupLayout(thirdCard);
        gl_thirdCard.setHorizontalGroup(
        	gl_thirdCard.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_thirdCard.createSequentialGroup()
        			.addGap(28)
        			.addGroup(gl_thirdCard.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_thirdCard.createSequentialGroup()
        					.addComponent(lblAddressOfBorrower)
        					.addGap(29)
        					.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_thirdCard.createSequentialGroup()
        					.addComponent(lblNameOfBorrower)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(185, Short.MAX_VALUE))
        );
        gl_thirdCard.setVerticalGroup(
        	gl_thirdCard.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_thirdCard.createSequentialGroup()
        			.addGap(36)
        			.addGroup(gl_thirdCard.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNameOfBorrower)
        				.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(44)
        			.addGroup(gl_thirdCard.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblAddressOfBorrower)
        				.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(192, Short.MAX_VALUE))
        );
        thirdCard.setLayout(gl_thirdCard);
        
        guiFrame.getContentPane().add(tabsPanel,BorderLayout.NORTH);
        guiFrame.getContentPane().add(cardPanel,BorderLayout.CENTER);
        guiFrame.setVisible(true);
    }
}
