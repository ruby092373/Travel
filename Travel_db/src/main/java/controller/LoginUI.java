package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.customer.CustomerBookingUI;
import controller.customer.RegisterUI;
import controller.employee.EmployeeMainUI;
import model.Customer;
import model.Employee;
import service.impl.LoginServiceImpl;

public class LoginUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginServiceImpl loginService = new LoginServiceImpl();
    private JRadioButton customerRadio;
    private JRadioButton employeeRadio;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginUI frame = new LoginUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginUI() {
        setTitle("旅遊預訂系統 - 登入");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("旅遊預訂系統");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        lblTitle.setBounds(10, 20, 414, 40);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("帳號:");
        lblUsername.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblUsername.setBounds(80, 110, 50, 25);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(140, 110, 180, 25);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        lblPassword.setBounds(80, 150, 50, 25);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 150, 180, 25);
        contentPane.add(passwordField);

        customerRadio = new JRadioButton("顧客");
        customerRadio.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        customerRadio.setBackground(new Color(240, 248, 255));
        customerRadio.setSelected(true);
        customerRadio.setBounds(130, 70, 70, 23);
        contentPane.add(customerRadio);

        employeeRadio = new JRadioButton("員工");
        employeeRadio.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        employeeRadio.setBackground(new Color(240, 248, 255));
        employeeRadio.setBounds(240, 70, 70, 23);
        contentPane.add(employeeRadio);

        ButtonGroup group = new ButtonGroup();
        group.add(customerRadio);
        group.add(employeeRadio);

        JButton btnLogin = new JButton("登入");
        btnLogin.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnLogin.setBounds(160, 190, 120, 40);
        contentPane.add(btnLogin);

        JButton btnGoRegister = new JButton("尚未加入會員？點此註冊");
        btnGoRegister.setForeground(Color.BLUE);
        btnGoRegister.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
        btnGoRegister.setBounds(140, 250, 170, 23);
        btnGoRegister.setBorderPainted(false);
        btnGoRegister.setContentAreaFilled(false);
        contentPane.add(btnGoRegister);

        /***************** event *****************/

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "帳號和密碼不能為空！");
                    return;
                }

                if (customerRadio.isSelected()) {
                    Customer customer = loginService.customerLogin(username, password);
                    if (customer != null) {
                        JOptionPane.showMessageDialog(null, "顧客 " + customer.getName() + "，歡迎光臨！");
                        new CustomerBookingUI(customer).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "帳號不存在或密碼錯誤！\n尚未加入會員？請點擊下方按鈕註冊。", "登入失敗", JOptionPane.ERROR_MESSAGE);
                    }
                } else { // employeeRadio is selected
                    Employee employee = loginService.employeeLogin(username, password);
                    if (employee != null) {
                        JOptionPane.showMessageDialog(null, "員工 " + employee.getName() + "，歡迎回來！");
                        new EmployeeMainUI().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "員工帳號或密碼錯誤！", "登入失敗", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnGoRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterUI().setVisible(true);
            }
        });
    }
}
