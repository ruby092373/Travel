package controller.customer;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.LoginUI;
import model.Customer;
import model.TripBooking;
import model.TripProduct;

public class BookingSuccessUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public BookingSuccessUI(TripBooking booking, Customer customer, TripProduct product) {
        setTitle("訂單建立成功");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 500, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("訂單建立成功！");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        lblTitle.setBounds(10, 10, 464, 35);
        contentPane.add(lblTitle);

        JTextArea outputArea = new JTextArea();
        outputArea.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        outputArea.setBounds(20, 55, 444, 250);
        outputArea.setEditable(false);
        contentPane.add(outputArea);

        JButton btnBack = new JButton("返回登入首頁");
        btnBack.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnBack.setBounds(150, 320, 180, 40);
        contentPane.add(btnBack);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = sdf.format(booking.getOrderDate());
        int totalPrice = product.getPrice() * booking.getNumberOfTravelers();

        String details = "=== 您的訂單資訊 ===\n\n" +
                         "訂單編號: " + booking.getId() + "\n" +
                         "顧客姓名: " + customer.getName() + "\n\n" +
                         "旅遊產品: " + product.getName() + "\n" +
                         "旅遊日期: " + dateString + "\n" +
                         "旅遊人數: " + booking.getNumberOfTravelers() + " 人\n\n" +
                         "---------------------------------\n" +
                         "總金額: NT$ " + totalPrice + " 元\n\n" +
                         "感謝您的訂購！";
        outputArea.setText(details);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginUI().setVisible(true);
                dispose();
            }
        });
    }
}
