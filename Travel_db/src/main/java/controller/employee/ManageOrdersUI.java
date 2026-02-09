package controller.employee;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import service.impl.TripBookingServiceImpl;
import vo.ViewBooking;

public class ManageOrdersUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField deleteIdField;
    private JTextArea outputArea;
    private TripBookingServiceImpl bookingService = new TripBookingServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageOrdersUI frame = new ManageOrdersUI();
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
	public ManageOrdersUI() {
        setTitle("訂單管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 850, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 814, 250);
        contentPane.add(scrollPane);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        scrollPane.setViewportView(outputArea);

        JButton btnQuery = new JButton("查詢全部訂單");
        btnQuery.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnQuery.setBounds(10, 10, 140, 30);
        contentPane.add(btnQuery);

        JPanel panelDelete = new JPanel();
        panelDelete.setBackground(new Color(220, 20, 60));
        panelDelete.setBounds(10, 310, 814, 80);
        contentPane.add(panelDelete);
        panelDelete.setLayout(null);

        JLabel lblDeleteId = new JLabel("輸入要刪除的訂單 ID:");
        lblDeleteId.setForeground(Color.WHITE);
        lblDeleteId.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        lblDeleteId.setBounds(20, 30, 160, 20);
        panelDelete.add(lblDeleteId);
        
        deleteIdField = new JTextField();
        deleteIdField.setBounds(190, 30, 100, 20);
        panelDelete.add(deleteIdField);

        JButton btnDelete = new JButton("確認刪除");
        btnDelete.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnDelete.setBounds(320, 25, 120, 30);
        panelDelete.add(btnDelete);
        
        showAllBookings();

        /***************** event *****************/

        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showAllBookings();
            }
        });
        
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    bookingService.deleteBookingById(Integer.parseInt(deleteIdField.getText()));
                    showAllBookings();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID 請輸入有效數字！");
                }
            }
        });
    }
    
    private void showAllBookings() {
        List<ViewBooking> bookings = bookingService.getAllBookingsForView();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-12s %-25s %-12s %-5s %-10s\n", 
            "ID", "顧客姓名", "預訂產品", "訂購日期", "人數", "總價"));
        sb.append("=================================================================================\n");
        for (ViewBooking b : bookings) {
            sb.append(String.format("%-5d %-12s %-25s %-12s %-5d %-10d\n",
                b.getBookingId(),
                b.getCustomerName(),
                b.getProductName(),
                b.getOrderDate(),
                b.getNumberOfTravelers(),
                b.getTotalPrice()));
        }
        outputArea.setText(sb.toString());
    }
}