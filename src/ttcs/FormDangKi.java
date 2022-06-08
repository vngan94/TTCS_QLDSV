/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttcs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import conn.connection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormDangKi extends javax.swing.JFrame {
    Connection conn = null;
    private String MA = "N19DCCN122";
    /**
     * Creates new form FormDangKi
     */
    public FormDangKi() {
        try {
            this.conn = new connection().getConnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        try {
            getInit();
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         // Determine the new location of the window
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the window
        this.setLocation(x, y);
    }
    public void setMA(String s){
        this.MA=s;
    }
    public String getMA(){
        return this.MA;
    }
    public void getInit() throws SQLException{
        Statement st = conn.createStatement();
        try {
            ResultSet rs = st.executeQuery("EXEC GetAllNienKhoa");
            int cnt=0;
            while(rs.next()){
                String nk = rs.getString("NIENKHOA");
                Object k = nk;
                cbxNienKhoa.insertItemAt(nk, cnt);
                if(cnt==0){
                    cbxNienKhoa.setSelectedIndex(0);
                    int tmp=0;
                    Statement st1 = conn.createStatement();
                    ResultSet rs1 = st1.executeQuery("exec GetAllHocKy '"+nk+"'");
                    while(rs1.next()){
                        cbxHocKy.insertItemAt(rs1.getString("HOCKY"), tmp);
                        tmp++;
                    }
                   cbxHocKy.setSelectedIndex(0);
                }
                cnt++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tableTTSV.getModel();
        ResultSet rs = st.executeQuery("SP_getInfoSVDKI '"+this.MA+"'");
        while(rs.next()){
            model.addRow(new Object[]{rs.getString("MASV"),rs.getString("HO"),rs.getString("TEN"),rs.getString("MALOP")});
        }
        tableTTSV.setRowSelectionInterval(0, 0);
        txtMaSV.setText(MA);
        
        DefaultTableModel model1 = new DefaultTableModel();
        model1 = (DefaultTableModel) tableHUYLTC.getModel();
        ResultSet rs1 = st.executeQuery("SP_LIST_SVHUYDANGKY '"+this.MA+"'");
        while(rs1.next()){
            model1.addRow(new Object[]{rs1.getString("MALTC"),rs1.getString("NIENKHOA"),rs1.getString("HOCKY"),rs1.getString("TENMH"),
            rs1.getString("HOTENGV"),rs1.getString("NHOM")});
        }
    }
    public void resetTable(){
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tableHUYLTC.getModel();
        for(int i=model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
        Statement st=null;
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = st.executeQuery("exec SP_LIST_SVHUYDANGKY '"+MA+"'");
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("MALTC"),rs.getString("NIENKHOA"),rs.getString("HOCKY"),rs.getString("TENMH"),
            rs.getString("HOTENGV"),rs.getString("NHOM")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
         String nienkhoa = cbxNienKhoa.getItemAt(cbxNienKhoa.getSelectedIndex());
        String hocky = cbxHocKy.getItemAt(cbxHocKy.getSelectedIndex());
        try {
            ResultSet rs = st.executeQuery("exec SP_InDanhSachLopTinChi '"+nienkhoa+"',"+hocky);
            DefaultTableModel model1 = new DefaultTableModel();
            model1 = (DefaultTableModel) tableTTLTC.getModel();
            for(int i=model1.getRowCount()-1;i>=0;i--){
                model1.removeRow(i);
            }
           
            while(rs.next()){
                model1.addRow(new Object[]{rs.getString("MALTC"),rs.getString("TENMH"),rs.getString("NHOM"),
                    rs.getString("HOTEN"),rs.getString("SOSVTOITHIEU"),rs.getString("AMOUNT")});
              
                  
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        btnDongY = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        bntCloseDialog = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        dialog3TXT = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        btnDongY1 = new javax.swing.JButton();
        btnDongY2 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnHuyDK = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxNienKhoa = new javax.swing.JComboBox<>();
        cbxHocKy = new javax.swing.JComboBox<>();
        btnSearchLTC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTTLTC = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTTSV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHUYLTC = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnDangKyLTC = new javax.swing.JButton();
        txtMaSV = new javax.swing.JTextField();
        txtMaLTC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jDialog1.setAlwaysOnTop(true);

        btnDongY.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDongY.setText("ĐỒNG Ý");
        btnDongY.setPreferredSize(new java.awt.Dimension(100, 30));
        btnDongY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Bạn muốn đăng ký môn học này");

        bntCloseDialog.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bntCloseDialog.setText("THOÁT");
        bntCloseDialog.setPreferredSize(new java.awt.Dimension(100, 30));
        bntCloseDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCloseDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(bntCloseDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel10)
                .addGap(47, 47, 47)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntCloseDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jDialog2.setAlwaysOnTop(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Bạn đã đăng ký môn học này rồi!");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("THOÁT");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel11)
                .addGap(41, 41, 41)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        dialog3TXT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dialog3TXT.setForeground(new java.awt.Color(255, 0, 0));
        dialog3TXT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dialog3TXT.setText("ĐĂNG KÝ THÀNH CÔNG!");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("THOÁT");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialog3TXT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(dialog3TXT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Bạn muốn hủy đăng ký môn học này");

        btnDongY1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDongY1.setText("THOÁT");
        btnDongY1.setPreferredSize(new java.awt.Dimension(100, 30));
        btnDongY1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongY1ActionPerformed(evt);
            }
        });

        btnDongY2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDongY2.setText("ĐỒNG Ý");
        btnDongY2.setPreferredSize(new java.awt.Dimension(100, 30));
        btnDongY2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongY2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnDongY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnDongY1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog4Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(53, 53, 53)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDongY1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDongY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 900));

        btnHome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnHome.setText("TRANG CHỦ");
        btnHome.setPreferredSize(new java.awt.Dimension(150, 50));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        btnHuyDK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHuyDK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove.png"))); // NOI18N
        btnHuyDK.setText("HỦY ĐĂNG KÝ");
        btnHuyDK.setPreferredSize(new java.awt.Dimension(150, 50));
        btnHuyDK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDKActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        btnRefresh.setText("LÀM MỚI");
        btnRefresh.setPreferredSize(new java.awt.Dimension(150, 50));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/log-out.png"))); // NOI18N
        btnThoat.setText("THOÁT");
        btnThoat.setPreferredSize(new java.awt.Dimension(150, 50));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(102, 255, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐĂNG KÝ LỚP TÍN CHỈ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbxNienKhoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNienKhoaItemStateChanged(evt);
            }
        });

        btnSearchLTC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSearchLTC.setText("TÌM");
        btnSearchLTC.setPreferredSize(new java.awt.Dimension(140, 70));
        btnSearchLTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLTCActionPerformed(evt);
            }
        });

        tableTTLTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp tín chỉ", "Môn học", "Nhóm", "Giảng viên", "SV tối thiểu", "SV đã đăng ký"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTTLTC.setPreferredSize(new java.awt.Dimension(1000, 200));
        tableTTLTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTTLTCMouseClicked(evt);
            }
        });
        tableTTLTC.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tableTTLTCInputMethodTextChanged(evt);
            }
        });
        tableTTLTC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableTTLTCPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tableTTLTC);

        tableTTSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Họ", "Tên", "Mã lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTTSV.setPreferredSize(new java.awt.Dimension(600, 200));
        jScrollPane2.setViewportView(tableTTSV);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THÔNG TIN SINH VIÊN");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN LỚP TÍN CHỈ");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setBackground(new java.awt.Color(51, 255, 51));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DANH SÁCH LỚP TÍN CHỈ SINH VIÊN CÓ THỂ HỦY");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableHUYLTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã LTC", "Niên khóa", "Học kỳ", "Môn học", "Giảng viên", "Nhóm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableHUYLTC);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("THÔNG TIN ĐĂNG KÝ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Mã sinh viên");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Mã lớp tín chỉ");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 30));

        btnDangKyLTC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDangKyLTC.setText("ĐĂNG KÝ");
        btnDangKyLTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangKyLTCActionPerformed(evt);
            }
        });

        txtMaSV.setEditable(false);
        txtMaSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSV.setPreferredSize(new java.awt.Dimension(160, 30));
        txtMaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSVActionPerformed(evt);
            }
        });

        txtMaLTC.setEditable(false);
        txtMaLTC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaLTC.setPreferredSize(new java.awt.Dimension(160, 30));

        jLabel8.setText("Niên khóa");

        jLabel9.setText("Học kỳ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnHuyDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDangKyLTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxNienKhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSearchLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyDK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxNienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(cbxHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnSearchLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnDangKyLTC, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaLTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchLTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLTCActionPerformed
        String nienkhoa = cbxNienKhoa.getItemAt(cbxNienKhoa.getSelectedIndex());
        String hocky = cbxHocKy.getItemAt(cbxHocKy.getSelectedIndex());
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("exec SP_InDanhSachLopTinChi '"+nienkhoa+"',"+hocky);
            DefaultTableModel model = new DefaultTableModel();
            model = (DefaultTableModel) tableTTLTC.getModel();
            for(int i=model.getRowCount()-1;i>=0;i--){
                model.removeRow(i);
            }
           
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("MALTC"),rs.getString("TENMH"),rs.getString("NHOM"),
                    rs.getString("HOTEN"),rs.getString("SOSVTOITHIEU"),rs.getString("AMOUNT")});
              
                  
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchLTCActionPerformed

    private void txtMaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSVActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FormMain f = new FormMain();
        f.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         // Determine the new location of the window
        int w = f.getSize().width;
        int h = f.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the window
        f.setLocation(x, y);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FormMain f = new FormMain();
        f.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         // Determine the new location of the window
        int w = f.getSize().width;
        int h = f.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the window
        f.setLocation(x, y);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbxNienKhoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNienKhoaItemStateChanged
        try {
            cbxHocKy.removeAllItems();
            String nk = cbxNienKhoa.getItemAt(cbxNienKhoa.getSelectedIndex());
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("exec GetAllHocKy '"+nk+"'");
            while(rs.next()){
                cbxHocKy.addItem(rs.getString("HOCKY"));
            }
            cbxHocKy.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxNienKhoaItemStateChanged

    private void tableTTLTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTTLTCMouseClicked
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tableTTLTC.getModel();
       try{
            String MALTC = (String) model.getValueAt(tableTTLTC.getSelectedRow(), 0);
            txtMaLTC.setText(MALTC);
       }catch(Exception e){
           
       }
       
    }//GEN-LAST:event_tableTTLTCMouseClicked

    private void tableTTLTCInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tableTTLTCInputMethodTextChanged
        
    }//GEN-LAST:event_tableTTLTCInputMethodTextChanged

    private void tableTTLTCPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableTTLTCPropertyChange
        System.out.println("changeeeeeeee");
    }//GEN-LAST:event_tableTTLTCPropertyChange

    private void btnDangKyLTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangKyLTCActionPerformed
        if(txtMaLTC.getText().equals("")) return;
        jDialog1.setVisible(true);
        jDialog1.setSize(400, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         // Determine the new location of the window
        int w = jDialog1.getSize().width;
        int h = jDialog1.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the window
        jDialog1.setLocation(x, y);
    }//GEN-LAST:event_btnDangKyLTCActionPerformed

    private void btnDongYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYActionPerformed
        try {
            String MALTC = txtMaLTC.getText();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM DANGKY WHERE MASV='"+MA+"' AND MALTC="+MALTC);
            if(rs.next()!=false){
                boolean b = rs.getBoolean("HUYDANGKY");
                if(b==false){
                    jDialog1.setVisible(false);
                    jDialog2.setVisible(true);
                    jDialog2.setSize(400, 300);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    // Determine the new location of the window
                    int w = jDialog2.getSize().width;
                    int h = jDialog2.getSize().height;
                    int x = (dim.width-w)/2;
                    int y = (dim.height-h)/2;
                    // Move the window
                    jDialog2.setLocation(x, y);
                }else{
                    PreparedStatement ps = conn.prepareStatement("UPDATE DANGKY SET HUYDANGKY=0 WHERE MALTC="+txtMaLTC.getText()+" AND MASV='"+MA+"'");
                    ps.execute();
                    jDialog1.setVisible(false);
                }
                
            }else{
                PreparedStatement ps = conn.prepareStatement("exec SP_DangKiTC "+txtMaLTC.getText()+",'"+MA+"'");
                ps.execute();
                //System.out.println("exec SP_DangKiTC "+txtMaLTC.getText()+",'"+MA+"',0");
                jDialog1.setVisible(false);
                jDialog3.setVisible(true);
                jDialog3.setSize(400, 300);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                // Determine the new location of the window
                int w = jDialog3.getSize().width;
                int h = jDialog3.getSize().height;
                int x = (dim.width-w)/2;
                int y = (dim.height-h)/2;
                // Move the window
                jDialog3.setLocation(x, y);
                resetTable();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDongYActionPerformed

    private void bntCloseDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCloseDialogActionPerformed
        jDialog1.setVisible(false);
    }//GEN-LAST:event_bntCloseDialogActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jDialog2.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHuyDKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDKActionPerformed
        // TODO add your handling code here:
        try{
            String MALTC = (String) tableHUYLTC.getValueAt(tableHUYLTC.getSelectedRow(), 0);
            jDialog4.setVisible(true);
            jDialog4.setSize(400, 300);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            // Determine the new location of the window
            int w = jDialog4.getSize().width;
            int h = jDialog4.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;
                // Move the window
            jDialog4.setLocation(x, y);
            
        }
        catch(Exception e){}
    }//GEN-LAST:event_btnHuyDKActionPerformed

    private void btnDongY1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongY1ActionPerformed
        jDialog4.setVisible(false);
    }//GEN-LAST:event_btnDongY1ActionPerformed

    private void btnDongY2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongY2ActionPerformed
        try {
            String MALTC = (String) tableHUYLTC.getValueAt(tableHUYLTC.getSelectedRow(), 0);
            PreparedStatement ps = conn.prepareStatement("UPDATE DANGKY SET HUYDANGKY=1 WHERE MALTC="+MALTC+" AND MASV='"+MA+"'");
            ps.execute();
            jDialog4.setVisible(false);
            jDialog3.setVisible(true);
            jDialog3.setSize(400, 300);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            // Determine the new location of the window
            int w = jDialog3.getSize().width;
            int h = jDialog3.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;
                // Move the window
            jDialog3.setLocation(x, y);
            dialog3TXT.setText("HỦY ĐĂNG KÝ THÀNH CÔNG!");
            resetTable();
        } catch (SQLException ex) {
            Logger.getLogger(FormDangKi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnDongY2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jDialog3.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        resetTable();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tableTTLTC.getModel();
        for(int i=model.getRowCount()-1;i>=0;i--){
            model.removeRow(i);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDangKi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDangKi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCloseDialog;
    private javax.swing.JButton btnDangKyLTC;
    private javax.swing.JButton btnDongY;
    private javax.swing.JButton btnDongY1;
    private javax.swing.JButton btnDongY2;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHuyDK;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchLTC;
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbxHocKy;
    private javax.swing.JComboBox<String> cbxNienKhoa;
    private javax.swing.JLabel dialog3TXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableHUYLTC;
    private javax.swing.JTable tableTTLTC;
    private javax.swing.JTable tableTTSV;
    private javax.swing.JTextField txtMaLTC;
    private javax.swing.JTextField txtMaSV;
    // End of variables declaration//GEN-END:variables
}
