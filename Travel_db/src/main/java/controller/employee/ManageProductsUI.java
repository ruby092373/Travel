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
import model.TripProduct;
import service.impl.TripProductServiceImpl;

public class ManageProductsUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField nameField;
    private JTextField destField;
    private JTextField priceField;
    private JTextField descField;
    private JTextField idField;
    private JTextArea outputArea;
    private TripProductServiceImpl productService = new TripProductServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProductsUI frame = new ManageProductsUI();
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
	public ManageProductsUI() {
        setTitle("旅遊產品管理 (CRUD)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 750, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 714, 200);
        contentPane.add(scrollPane);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        scrollPane.setViewportView(outputArea);
        
        JButton btnQuery = new JButton("查詢全部產品");
        btnQuery.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnQuery.setBounds(10, 10, 140, 30);
        contentPane.add(btnQuery);

        JPanel panelActions = new JPanel();
        panelActions.setBackground(new Color(176, 196, 222));
        panelActions.setBounds(10, 260, 714, 290);
        contentPane.add(panelActions);
        panelActions.setLayout(null);
        
        JLabel lblName = new JLabel("產品名稱:");
        lblName.setBounds(20, 20, 70, 20);
        panelActions.add(lblName);
        nameField = new JTextField();
        nameField.setBounds(90, 20, 150, 20);
        panelActions.add(nameField);

        JLabel lblDest = new JLabel("目的地:");
        lblDest.setBounds(260, 20, 60, 20);
        panelActions.add(lblDest);
        destField = new JTextField();
        destField.setBounds(320, 20, 100, 20);
        panelActions.add(destField);
        
        JLabel lblPrice = new JLabel("價格:");
        lblPrice.setBounds(440, 20, 40, 20);
        panelActions.add(lblPrice);
        priceField = new JTextField();
        priceField.setBounds(480, 20, 80, 20);
        panelActions.add(priceField);
        
        JLabel lblDesc = new JLabel("描述:");
        lblDesc.setBounds(20, 60, 70, 20);
        panelActions.add(lblDesc);
        descField = new JTextField();
        descField.setBounds(90, 60, 470, 20);
        panelActions.add(descField);

        JButton btnAdd = new JButton("新增產品");
        btnAdd.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        btnAdd.setBounds(280, 100, 120, 30);
        panelActions.add(btnAdd);

        JLabel lblId = new JLabel("操作目標 ID:");
        lblId.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        lblId.setBounds(20, 160, 100, 20);
        panelActions.add(lblId);
        idField = new JTextField();
        idField.setBounds(120, 160, 80, 20);
        panelActions.add(idField);
        
        JLabel lblHint = new JLabel("（要修改或刪除，請先輸入 ID，要修改請一併填寫上方欄位）");
        lblHint.setBounds(210, 160, 400, 20);
        panelActions.add(lblHint);

        JButton btnUpdate = new JButton("確認修改");
        btnUpdate.setBounds(20, 230, 100, 30);
        panelActions.add(btnUpdate);

        JButton btnDelete = new JButton("確認刪除");
        btnDelete.setBackground(new Color(255, 182, 193));
        btnDelete.setBounds(140, 230, 100, 30);
        panelActions.add(btnDelete);

        showAllProducts();

        /***************** event *****************/

        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showAllProducts();
            }
        });

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    TripProduct p = new TripProduct(
                        nameField.getText(),
                        destField.getText(),
                        Integer.parseInt(priceField.getText()),
                        descField.getText()
                    );
                    productService.addProduct(p);
                    showAllProducts();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "價格請輸入有效數字！");
                }
            }
        });

        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    TripProduct p = new TripProduct(
                        nameField.getText(),
                        destField.getText(),
                        Integer.parseInt(priceField.getText()),
                        descField.getText()
                    );
                    p.setId(Integer.parseInt(idField.getText()));
                    productService.updateProduct(p);
                    showAllProducts();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID 和價格請輸入有效數字！");
                }
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    productService.deleteProductById(Integer.parseInt(idField.getText()));
                    showAllProducts();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID 請輸入有效數字！");
                }
            }
        });
    }

    private void showAllProducts() {
        List<TripProduct> products = productService.getAllProducts();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-10s %-10s %-25s\n", "ID", "產品名稱", "目的地", "價格", "描述"));
        sb.append("=========================================================================\n");
        for (TripProduct p : products) {
            sb.append(String.format("%-5d %-20s %-10s %-10d %-25s\n",
                p.getId(),
                p.getName(),
                p.getDestination(),
                p.getPrice(),
                p.getDescription()));
        }
        outputArea.setText(sb.toString());
    }
}
