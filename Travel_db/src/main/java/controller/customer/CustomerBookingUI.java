package controller.customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import model.Customer;
import model.TripBooking;
import model.TripProduct;
import service.TripBookingService;
import service.TripProductService;
import service.impl.TripBookingServiceImpl;
import service.impl.TripProductServiceImpl;

public class CustomerBookingUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cityBox;
    private JComboBox<TripProduct> itineraryBox;
    private JTextField travelersField;
    private JDateChooser dateChooser;
    
    private TripProductService productService = new TripProductServiceImpl();
    private TripBookingService bookingService = new TripBookingServiceImpl();
    
    private Map<String, List<TripProduct>> cityProductMap;
    private Customer currentCustomer;

    public CustomerBookingUI(Customer customer) {
        this.currentCustomer = customer;
        loadProductData();
        
        setTitle("歡迎，" + currentCustomer.getName() + "！請開始預訂行程");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(10, 10, 484, 361);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblTitle = new JLabel("旅遊行程預訂");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        lblTitle.setBounds(170, 20, 150, 30);
        panel.add(lblTitle);

        JLabel lblDate = new JLabel("旅遊日期:");
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblDate.setBounds(40, 80, 80, 25);
        panel.add(lblDate);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(130, 80, 180, 25);
        panel.add(dateChooser);

        JLabel lblCity = new JLabel("選擇縣市:");
        lblCity.setForeground(Color.WHITE);
        lblCity.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblCity.setBounds(40, 130, 80, 25);
        panel.add(lblCity);

        String[] allCities = {
            "基隆市", "台北市", "新北市", "桃園市", "新竹市", "新竹縣", "苗栗縣", 
            "台中市", "彰化縣", "南投縣", "雲林縣", "嘉義市", "嘉義縣", "台南市", 
            "高雄市", "屏東縣", "宜蘭縣", "花蓮縣", "台東縣", "澎湖縣", "金門縣", "連江縣"
        };
        cityBox = new JComboBox<>(allCities);
        cityBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        cityBox.setBounds(130, 130, 180, 25);
        panel.add(cityBox);

        JLabel lblItinerary = new JLabel("選擇行程:");
        lblItinerary.setForeground(Color.WHITE);
        lblItinerary.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblItinerary.setBounds(40, 180, 80, 25);
        panel.add(lblItinerary);

        itineraryBox = new JComboBox<>();
        itineraryBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        itineraryBox.setBounds(130, 180, 320, 25);
        panel.add(itineraryBox);

        JLabel lblTravelers = new JLabel("旅遊人數:");
        lblTravelers.setForeground(Color.WHITE);
        lblTravelers.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        lblTravelers.setBounds(40, 230, 80, 25);
        panel.add(lblTravelers);

        travelersField = new JTextField();
        travelersField.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        travelersField.setBounds(130, 230, 180, 25);
        panel.add(travelersField);
        travelersField.setColumns(10);

        JButton btnConfirm = new JButton("確認訂單");
        btnConfirm.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnConfirm.setBounds(130, 290, 120, 40);
        panel.add(btnConfirm);
        
        JButton btnReset = new JButton("修改行程");
        btnReset.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        btnReset.setBounds(290, 295, 120, 30);
        panel.add(btnReset);

        updateItineraryBox();

        cityBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateItineraryBox();
                }
            }
        });

        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Date selectedDate = dateChooser.getDate();
                TripProduct selectedProduct = (TripProduct) itineraryBox.getSelectedItem();
                String travelersText = travelersField.getText().trim();

                if (selectedDate == null || selectedProduct == null || travelersText.isEmpty()) {
                    JOptionPane.showMessageDialog(CustomerBookingUI.this, "所有欄位都必須填寫！", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int numberOfTravelers = Integer.parseInt(travelersText);
                    
                    if (numberOfTravelers <= 0) {
                        JOptionPane.showMessageDialog(CustomerBookingUI.this, "旅遊人數必須是正數！", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    TripBooking bookingData = new TripBooking(currentCustomer.getId(), selectedProduct.getId(), selectedDate, numberOfTravelers);
                    TripBooking createdBooking = bookingService.addBooking(bookingData);
                    
                    if (createdBooking != null) {
                        new BookingSuccessUI(createdBooking, currentCustomer, selectedProduct).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(CustomerBookingUI.this, "訂單建立失敗，請稍後再試或聯繫客服。", "操作失敗", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomerBookingUI.this, "旅遊人數請輸入有效的數字！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CustomerBookingUI.this, "發生未知系統錯誤: " + ex.getMessage(), "系統錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dateChooser.setDate(null);
                travelersField.setText("");
                cityBox.setSelectedIndex(0);
            }
        });
    }

    private void loadProductData() {
        cityProductMap = new HashMap<>();
        List<TripProduct> allProducts = productService.getAllProducts(); 
        for (TripProduct p : allProducts) {
            String city = p.getDestination();
            cityProductMap.computeIfAbsent(city, k -> new ArrayList<>()).add(p);
        }
    }

    private void updateItineraryBox() {
        String selectedCity = (String) cityBox.getSelectedItem();
        List<TripProduct> productsInCity = cityProductMap.get(selectedCity);
        
        itineraryBox.removeAllItems();
        if (productsInCity != null) {
            for (TripProduct p : productsInCity) {
                itineraryBox.addItem(p);
            }
        }
    }
}
