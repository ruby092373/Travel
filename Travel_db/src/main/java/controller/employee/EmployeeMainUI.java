package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.LoginUI;


public class EmployeeMainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMainUI frame = new EmployeeMainUI();
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
	 public EmployeeMainUI() {
	        setTitle("員工後台管理系統");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 450, 320);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(70, 130, 180));
	        panel.setBounds(10, 10, 414, 261);
	        contentPane.add(panel);
	        panel.setLayout(null);

	        JLabel lblTitle = new JLabel("後台管理主選單");
	        lblTitle.setForeground(Color.WHITE);
	        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
	        lblTitle.setBounds(10, 20, 394, 40);
	        panel.add(lblTitle);

	        JButton btnManageProducts = new JButton("管理旅遊產品");
	        btnManageProducts.setFont(new Font("微軟正黑體", Font.BOLD, 16));
	        btnManageProducts.setBounds(132, 80, 150, 40);
	        panel.add(btnManageProducts);
	        
	        JButton btnManageOrders = new JButton("管理訂單");
	        btnManageOrders.setFont(new Font("微軟正黑體", Font.BOLD, 16));
	        btnManageOrders.setBounds(132, 140, 150, 40);
	        panel.add(btnManageOrders);
	        
	        JButton btnLogout = new JButton("登出");
	        btnLogout.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
	        btnLogout.setBounds(317, 228, 87, 23);
	        panel.add(btnLogout);

	        /***************** event *****************/

	        btnManageProducts.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                new ManageProductsUI().setVisible(true);
	            }
	        });

	        btnManageOrders.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                new ManageOrdersUI().setVisible(true);
	            }
	        });

	        btnLogout.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                new LoginUI().setVisible(true);
	                dispose();
	            }
	        });
	    }
	}
