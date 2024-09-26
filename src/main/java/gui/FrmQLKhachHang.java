package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.QLKhachHang;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrmQLKhachHang extends JFrame {

    private JTable tblKhachHang;  
    private JButton btDocFile, btGhiFile;
    private DefaultTableModel model;
    private JTextField txtMax, txtMin, txtTB;
    private JCheckBox chkSapXep;

    private static final String FILE_NHAP = "input.txt";
    private static final String FILE_XUAT = "output.txt";
    private QLKhachHang qlKhachHang = new QLKhachHang();

    public FrmQLKhachHang(String title) {
        super(title);
        createGUI();   
        processEvent();
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createGUI() {

        // Tạo JTable hiển thị danh sách khách hàng
        String[] columnNames = {"Mã số", "Họ tên", "Loại hình", "Chỉ số cũ (kWh)", "Chỉ số mới (kWh)", "Tiêu thụ (kWh)", "Tiền trả (đồng)"};
        model = new DefaultTableModel(null, columnNames);
        tblKhachHang = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(tblKhachHang);

        // Tạo các nút chức năng
        JPanel p1 = new JPanel();
        p1.add(btDocFile = new JButton("Nhập dữ liệu khách hàng"));
        p1.add(btGhiFile = new JButton("Xuất hóa đơn thanh toán"));

        // Tạo các JTextField hiển thị thống kê
        JPanel p2 = new JPanel();
        p2.add(new JLabel("Mức tiêu thụ thấp nhất:"));
        p2.add(txtMin = new JTextField(10));
        p2.add(new JLabel("Mức tiêu thụ cao nhất:"));
        p2.add(txtMax = new JTextField(10));
        p2.add(new JLabel("Mức tiêu thụ trung bình:"));
        p2.add(txtTB = new JTextField(10));

        // Tạo checkbox để sắp xếp theo loại hình
        p2.add(chkSapXep = new JCheckBox("Sắp xếp theo loại hình"));

        // Thêm các thành phần vào cửa sổ
        add(p1, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
    }

    private void processEvent() {
        btDocFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qlKhachHang.DocKhachHang(FILE_NHAP);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi đọc file: " + ex.getMessage());
                }
            }
        });
        btGhiFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qlKhachHang.DocKhachHang(FILE_XUAT);
                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi ghi file: " + ex.getMessage());
                }
            }
        });

        // Xử lý sự kiện khi chọn checkbox "Sắp xếp theo loại hình"
        chkSapXep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkSapXep.isSelected()) {
                    qlKhachHang.sapXepTheoLoaiHinh();
                }
            }
        });
    }
}
