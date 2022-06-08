/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttcs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import conn.connection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class FormSinhVien extends javax.swing.JFrame {
    private Connection conn = null;
    private int sltRow=0;
    private int sltSv = -1;
    private String themsua="";
    /**
     * Creates new form FormSinhVien
     */
    public FormSinhVien() throws ClassNotFoundException, SQLException {
        conn = new connection().getConnect(); 
        DefaultTableModel mod = new DefaultTableModel();
        this.tableLop = new JTable(mod);
        initComponents();
        try {
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery("SELECT * FROM LOP");
              int cnt=1;
              while (rs.next()){
                  if(cnt==1){
                      String malop = rs.getString("MALOP");
                      System.out.println(malop);
                      try {
                            Statement st1 = conn.createStatement();
                            ResultSet rs1 = st1.executeQuery("SELECT * FROM SINHVIEN WHERE MALOP='"+malop+"'");
                            int tmp=1;
                            while (rs1.next()){
                                if(tmp==1){
                                    txtID.setText(rs1.getString("MASV"));
                                    txtClassID.setText(rs1.getString("MALOP"));
                                    txtDate.setText(rs1.getString("NGAYSINH"));
                                    txtLocate.setText(rs1.getString("DIACHI"));
                                    txtFName.setText(rs1.getString("HO"));
                                    txtLName.setText(rs1.getString("TEN"));
                                    txtGender.setSelectedIndex(rs1.getInt("PHAI"));
                                    chkboxStillStudy.setSelected(rs1.getBoolean("DANGHIHOC"));
                                }
                                DefaultTableModel model = (DefaultTableModel) tableSV.getModel();
                                model.addRow(new Object[]{rs1.getString("MASV"),rs1.getString("HO"),rs1.getString("TEN"),rs1.getString("PHAI"),
                                rs1.getString("NGAYSINH"),rs1.getString("DIACHI"),malop,rs1.getString("DANGHIHOC")});   
//                                System.out.println(rs1.getString("MASV")+rs1.getString("HO")+rs1.getString("TEN"));
                                tmp++;
                             }
              
                        } catch (SQLException ex) {
                            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
                     }
                  }
                  DefaultTableModel model = (DefaultTableModel) tableLop.getModel();
                  model.addRow(new Object[]{rs.getString("MALOP"),rs.getString("TENLOP"),rs.getString("KHOAHOC"),rs.getString("MAKHOA")});
                  System.out.println(rs.getString("MALOP")+"///"+rs.getString("TENLOP")+"///"+rs.getString("KHOAHOC")+"///"+rs.getString("MAKHOA"));
                  cnt++;
              }
              tableLop.setRowSelectionInterval(0, 0);
              tableSV.setRowSelectionInterval(0, 0);
              
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean validateSV(){
        if(txtID.getText().equals("")){lbWarning.setText("Không được để trống mã sinh viên!");return false;}
        if(txtDate.getText().equals("")) {lbWarning.setText("Không được để trống ngày sinh!");return false;}
        if(txtFName.getText().equals("")){lbWarning.setText("Không được để trống tên!"); return false;}
        if(txtLName.getText().equals("")) {lbWarning.setText("Không được để trống tên!");return false;}
        if(txtLocate.getText().equals("")){lbWarning.setText("Không được để trống địa chỉ!"); return false;}
 //gioi tinh khoi check       if(txtxGender.getSelectedIndex()==-1) return false;
        return true;
    }
    public void resetAdd(String malop,int pos){
        System.out.println(malop);
        tableSV.setEnabled(true);
        txtID.setEditable(false);
        
        txtFName.setEditable(false);
        
        txtLName.setEditable(false);
        
        txtDate.setEditable(false);
        
        txtGender.setEnabled(false);
        txtLocate.setEditable(false);
        
        chkboxStillStudy.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) tableSV.getModel();
        for(int i=model.getRowCount()-1;i>=0;i--){
                    model.removeRow(i);
            }
        Statement st=null;
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM SINHVIEN WHERE MALOP='"+malop+"'");
            System.out.println("SELECT * FROM SINHVIEN WHERE MALOP='"+malop+"'");
            int cnt=0;
            while (rs.next()){
                model.addRow(new Object[]{rs.getString("MASV"),rs.getString("MALOP"),rs.getString("NGAYSINH"),
                rs.getString("DIACHI"),rs.getString("HO"),rs.getString("TEN"),rs.getString("PHAI"),rs.getString("DANGHIHOC"),});
                if(cnt==pos){
                    txtID.setText(rs.getString("MASV"));
                    txtClassID.setText(rs.getString("MALOP"));
                    txtDate.setText(rs.getString("NGAYSINH"));
                    txtLocate.setText(rs.getString("DIACHI"));
                    txtFName.setText(rs.getString("HO"));
                    txtLName.setText(rs.getString("TEN"));
                    txtGender.setSelectedIndex(rs.getInt("PHAI"));
                    chkboxStillStudy.setSelected(rs.getBoolean("DANGHIHOC"));
                }
                cnt++;
            }
            tableSV.setRowSelectionInterval(pos, pos);
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableLop.setEnabled(true);
        btnBack.setEnabled(true);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);
        btnBack.setEnabled(false);
        btnAdd.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableLop = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSV = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtLName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGender = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtClassID = new javax.swing.JTextField();
        chkboxStillStudy = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLocate = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lbWarning = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1500, 800));

        tableLop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã lớp", "Tên lớp", "Khóa học", "Mã khoa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLop.setGridColor(new java.awt.Color(153, 255, 255));
        tableLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLopMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLop);

        tableSV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SV", "Họ", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã lớp", "Nghỉ học"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSV);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Mã sinh viên");
        jLabel1.setPreferredSize(new java.awt.Dimension(70, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Họ");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 20));

        txtID.setEditable(false);
        txtID.setPreferredSize(new java.awt.Dimension(100, 30));

        txtFName.setEditable(false);
        txtFName.setPreferredSize(new java.awt.Dimension(100, 30));
        txtFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Tên");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Địa chỉ");

        txtLName.setEditable(false);
        txtLName.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Giới tính");
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 20));

        txtGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        txtGender.setEnabled(false);
        txtGender.setPreferredSize(new java.awt.Dimension(100, 30));
        txtGender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtGenderItemStateChanged(evt);
            }
        });
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Ngày sinh");
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 20));

        txtDate.setEditable(false);
        txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/M/yyyy"))));
        txtDate.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Mã lớp");
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 20));

        txtClassID.setEditable(false);
        txtClassID.setPreferredSize(new java.awt.Dimension(100, 30));

        chkboxStillStudy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkboxStillStudy.setText("Nghỉ học");
        chkboxStillStudy.setEnabled(false);
        chkboxStillStudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxStillStudyActionPerformed(evt);
            }
        });

        txtLocate.setEditable(false);
        txtLocate.setColumns(20);
        txtLocate.setRows(5);
        jScrollPane3.setViewportView(txtLocate);

        btnAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setBorder(new javax.swing.border.MatteBorder(null));
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setBorder(new javax.swing.border.MatteBorder(null));
        btnEdit.setPreferredSize(new java.awt.Dimension(100, 40));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/floppy-disk.png"))); // NOI18N
        btnSave.setText("Ghi");
        btnSave.setBorder(new javax.swing.border.MatteBorder(null));
        btnSave.setEnabled(false);
        btnSave.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setBorder(new javax.swing.border.MatteBorder(null));
        btnDelete.setPreferredSize(new java.awt.Dimension(100, 40));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/previous.png"))); // NOI18N
        btnBack.setText("Trở lại");
        btnBack.setBorder(new javax.swing.border.MatteBorder(null));
        btnBack.setEnabled(false);
        btnBack.setPreferredSize(new java.awt.Dimension(100, 40));

        lbWarning.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbWarning.setForeground(new java.awt.Color(255, 51, 0));

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remove.png"))); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.setBorder(new javax.swing.border.MatteBorder(null));
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Định dạng dd/mm/yyyy, ví dụ: \"21/03/2001\"");

        btnHome.setBackground(new java.awt.Color(255, 255, 255));
        btnHome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnHome.setText("Trang chủ");
        btnHome.setBorder(new javax.swing.border.MatteBorder(null));
        btnHome.setPreferredSize(new java.awt.Dimension(100, 40));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkboxStillStudy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFName, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                    .addComponent(txtLName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtClassID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(5, 5, 5)))
                        .addComponent(jLabel8)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClassID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(chkboxStillStudy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFNameActionPerformed

    private void chkboxStillStudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxStillStudyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkboxStillStudyActionPerformed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void tableLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLopMouseClicked
        lbWarning.setText("");
        if(tableLop.getSelectedRow()!=sltRow){
            sltRow = tableLop.getSelectedRow();
            sltSv=-1;
            String malop = String.valueOf(tableLop.getValueAt(sltRow, 0));
            Statement st=null;
            try {
                st = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ResultSet rs1 = st.executeQuery("SELECT * FROM SINHVIEN WHERE MALOP='"+malop+"'");
                DefaultTableModel model = (DefaultTableModel) tableSV.getModel();
                for(int i=model.getRowCount()-1;i>=0;i--){
                    model.removeRow(i);
                }
                int cnt=1;
                while(rs1.next()){
                    if(cnt==1){
                         txtID.setText(rs1.getString("MASV"));
                         txtClassID.setText(rs1.getString("MALOP"));
                         txtDate.setText(rs1.getString("NGAYSINH"));
                         txtLocate.setText(rs1.getString("DIACHI"));
                         txtFName.setText(rs1.getString("HO"));
                         txtLName.setText(rs1.getString("TEN"));
                         txtGender.setSelectedIndex(rs1.getInt("PHAI"));
                         chkboxStillStudy.setSelected(rs1.getBoolean("DANGHIHOC"));
                    }
                    model.addRow(new Object[]{rs1.getString("MASV"),rs1.getString("HO"),rs1.getString("TEN"),rs1.getString("PHAI"),
                    rs1.getString("NGAYSINH"),rs1.getString("DIACHI"),malop,rs1.getString("DANGHIHOC")});   
                    System.out.println(rs1.getString("MASV")+"  "+rs1.getString("HO")+" "+rs1.getString("TEN"));
                    cnt++;
                }
                tableSV.setRowSelectionInterval(0, 0);
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableLopMouseClicked

    private void tableSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSVMouseClicked
        lbWarning.setText("");
        if(tableSV.getSelectedRow()!=sltSv && tableSV.isEnabled()==true){
            sltSv = tableSV.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tableSV.getModel();
            String masv = String.valueOf(tableSV.getValueAt(sltSv,0 ));
            Statement st=null;
            try {
                st = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ResultSet rs = st.executeQuery("SELECT * FROM SINHVIEN WHERE MASV='"+masv+"'");
                while (rs.next()){
                    txtID.setText(rs.getString("MASV"));
                    txtClassID.setText(rs.getString("MALOP"));
                    txtDate.setText(rs.getString("NGAYSINH"));
                    txtLocate.setText(rs.getString("DIACHI"));
                    txtFName.setText(rs.getString("HO"));
                    txtLName.setText(rs.getString("TEN"));
                    txtGender.setSelectedIndex(rs.getInt("PHAI"));
                    chkboxStillStudy.setSelected(rs.getBoolean("DANGHIHOC"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_tableSVMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        themsua="them";
        lbWarning.setText("");
        tableSV.setEnabled(false);
        tableLop.setEnabled(false);
        btnBack.setEnabled(false);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnBack.setEnabled(true);
        txtID.setEditable(true);
        txtID.setText("");
        txtFName.setEditable(true);
        txtFName.setText("");
        txtLName.setEditable(true);
        txtLName.setText("");
        txtDate.setEditable(true);
        txtDate.setText("");
        txtGender.setEnabled(true);
        txtLocate.setEditable(true);
        txtLocate.setText("");
        chkboxStillStudy.setEnabled(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        themsua="";
        lbWarning.setText("");
        tableSV.setEnabled(true);
        txtID.setEditable(false);
        
        txtFName.setEditable(false);
        
        txtLName.setEditable(false);
        
        txtDate.setEditable(false);
        
        txtGender.setEnabled(false);
        txtLocate.setEditable(false);
        
        chkboxStillStudy.setEnabled(false);
        if( tableSV.isEnabled()==true){
            sltSv = tableSV.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tableSV.getModel();
            String masv = String.valueOf(tableSV.getValueAt(sltSv,0 ));
            Statement st=null;
            try {
                st = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ResultSet rs = st.executeQuery("SELECT * FROM SINHVIEN WHERE MASV='"+masv+"'");
                while (rs.next()){
                    txtID.setText(rs.getString("MASV"));
                    txtClassID.setText(rs.getString("MALOP"));
                    txtDate.setText(rs.getString("NGAYSINH"));
                    txtLocate.setText(rs.getString("DIACHI"));
                    txtFName.setText(rs.getString("HO"));
                    txtLName.setText(rs.getString("TEN"));
                    txtGender.setSelectedIndex(rs.getInt("PHAI"));
                    chkboxStillStudy.setSelected(rs.getBoolean("DANGHIHOC"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableLop.setEnabled(true);
        btnAdd.setEnabled(true);
        btnBack.setEnabled(true);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);
        btnBack.setEnabled(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
               //lbWarning.setText("Không được để trống mã sinh viên!");
       if(validateSV()){
            String masv = txtID.getText();
            Statement st=null;
           try {
               st = conn.createStatement();
           } catch (SQLException ex) {
               Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               ResultSet rs = st.executeQuery("SELECT MASV,NGAYSINH FROM SINHVIEN WHERE MASV='"+masv+"'");
               if(rs.next()!=false){
                   if(themsua.equals("sua")){
                        Date d = rs.getDate("NGAYSINH");
                        Date date1=null;
                        String patern = "dd/MM/yyyy";
                        SimpleDateFormat simpleDate = new SimpleDateFormat(patern);
                        String date = txtDate.getText();
                        try {
                            if(d.toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(date)).toString())){
                                date = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(date)).toString();      
                            }  
                       } catch (Exception e) {
                           System.out.println("khac nhau");
                            try {
                                date1 = simpleDate.parse(date);
                                date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                            } catch (ParseException ex) {
                                Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       }
                        System.out.println(date);
                        PreparedStatement ps = conn.prepareStatement("EXEC sp_updateSV ?,?,?,?,?,?,?");
                        ps.setString(1, txtID.getText());
                        ps.setString(2, txtFName.getText());
                        ps.setString(3, txtLName.getText());
//                        ps.setString(4, txtClassID.getText());
                        ps.setInt(4, txtGender.getSelectedIndex());
                        ps.setString(5, date);
                        ps.setString(6, txtLocate.getText());
                        ps.setBoolean(7, chkboxStillStudy.isSelected());
                        if(ps.executeUpdate()>0){
                            lbWarning.setText("Sửa thông tin sinh viên thành công!");
                        }
                        System.out.println(tableSV.getSelectedRow());
                        resetAdd(txtClassID.getText(),tableSV.getSelectedRow());
                   }else{
                    lbWarning.setText("Mã sinh viên đã tồn tại");
                   }
                   
               }else{
                   Date date1=null;
                   String patern = "dd/MM/yyyy";
                   SimpleDateFormat simpleDate = new SimpleDateFormat(patern);
                   String date = txtDate.getText();
//                   System.out.println(date);
                    try {
                     date1 = simpleDate.parse(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   System.out.println(date1);
                   date = new SimpleDateFormat("yyyy/MM/dd").format(date1);
                   PreparedStatement ps = conn.prepareStatement("INSERT INTO SINHVIEN(MASV,HO,TEN,MALOP,PHAI,NGAYSINH,DIACHI,DANGHIHOC) VALUES(?,?,?,?,?,?,?,?)");
                   ps.setString(1, txtID.getText().trim());
                   ps.setString(2, txtFName.getText().trim());
                   ps.setString(3, txtLName.getText().trim());
                   ps.setString(4, txtClassID.getText().trim());
                   ps.setInt(5, txtGender.getSelectedIndex());
                   ps.setString(6, date);
                   ps.setString(7, txtLocate.getText().trim());
                   ps.setBoolean(8, chkboxStillStudy.isSelected());
                   if(ps.executeUpdate()>0){
                       lbWarning.setText("Thêm sinh viên thành công");
                   }
                   resetAdd(txtClassID.getText(),0);
               }
           } catch (SQLException ex) {
               Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else{
            return;
       }
       themsua="";
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String masv = txtID.getText();
        String malop = txtClassID.getText();
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = st.executeQuery("SELECT MASV FROM SINHVIEN WHERE MASV='"+masv+"'");
            if(rs.next()!=false){
                ResultSet rs1 = st.executeQuery("SELECT * FROM DANGKY WHERE MASV = '"+masv+"' AND HUYDANGKY=0");
                if(rs1.next()!=false){
                    lbWarning.setText("Không thể xóa vì sinh viên đã đăng kí lớp tín chỉ");
                }else{
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM SINHVIEN WHERE MASV = '"+masv+"'");
                    boolean a = ps.executeUpdate()>0;
                    resetAdd(malop,0);
                    lbWarning.setText("Xóa thành công!");
                }
            }else{
                lbWarning.setText("Mã sinh viên không tồn tại!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        themsua="sua";
        btnEdit.setEnabled(true);
        btnAdd.setEnabled(false);
        btnSave.setEnabled(true);
        btnBack.setEnabled(true);
        btnDelete.setEnabled(false);
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtDate.setEditable(true);
        txtGender.setEnabled(true);
        chkboxStillStudy.setEnabled(true);
        txtLocate.setEditable(true);
        tableSV.setEnabled(false);
        tableLop.setEnabled(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new FormMain().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void txtGenderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtGenderItemStateChanged
        System.out.println(txtGender.toString());
    }//GEN-LAST:event_txtGenderItemStateChanged

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
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormSinhVien().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FormSinhVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkboxStillStudy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbWarning;
    private javax.swing.JTable tableLop;
    private javax.swing.JTable tableSV;
    private javax.swing.JTextField txtClassID;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtFName;
    private javax.swing.JComboBox<String> txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextArea txtLocate;
    // End of variables declaration//GEN-END:variables
}
