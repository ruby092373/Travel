package controller.customer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import model.Customer;
import service.impl.LoginServiceImpl;

public class RegisterUI extends JFrame {
	
	/**
     * Launch the application for testing purposes.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterUI frame = new RegisterUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginServiceImpl loginService = new LoginServiceImpl();

    public RegisterUI() {
        setTitle("註冊新會員");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 150, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("建立新帳號");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        lblTitle.setBounds(10, 20, 414, 30);
        contentPane.add(lblTitle);

        JLabel lblName = new JLabel("您的姓名:");
        lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblName.setBounds(60, 80, 80, 25);
        contentPane.add(lblName);
        nameField = new JTextField();
        nameField.setBounds(150, 80, 180, 25);
        contentPane.add(nameField);
        nameField.setColumns(10);

        JLabel lblUsername = new JLabel("自訂帳號:");
        lblUsername.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblUsername.setBounds(60, 120, 80, 25);
        contentPane.add(lblUsername);
        usernameField = new JTextField();
        usernameField.setBounds(150, 120, 180, 25);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("設定密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblPassword.setBounds(60, 160, 80, 25);
        contentPane.add(lblPassword);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 160, 180, 25);
        contentPane.add(passwordField);

        JButton btnRegister = new JButton("確認註冊");
        btnRegister.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnRegister.setBounds(80, 220, 120, 40);
        contentPane.add(btnRegister);
        
        JButton btnBack = new JButton("返回登入");
        btnBack.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnBack.setBounds(240, 225, 120, 30);
        contentPane.add(btnBack);

        /***************** event *****************/
        
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "所有欄位都必須填寫！");
                    return;
                }

                if (loginService.isCustomerUsernameTaken(username)) {
                    JOptionPane.showMessageDialog(null, "此帳號已被使用，請更換一個！", "註冊失敗", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Customer newCustomer = new Customer(name, username, password);
                Customer createdCustomer = loginService.registerCustomer(newCustomer);

                if (createdCustomer != null) {
                    JOptionPane.showMessageDialog(null, "註冊成功！請用新帳號重新登入。");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "發生未知錯誤，註冊失敗。", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }
}
