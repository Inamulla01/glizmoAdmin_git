/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author moham
 */
public class UsersPanel extends javax.swing.JPanel {

    /**
     * Creates new form UsersPanel
     */
    public UsersPanel() {
        initComponents();
        init();
        loadUserTable();
    }

    private void loadUserTable() {
                JTableHeader tableHeader = userTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM view_user_details");
            DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("users_id"));
                v.add(rs.getString("fname")+ "" + rs.getString("lname"));
                v.add(rs.getString("email"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("create_at"));
                v.add(rs.getString("gender_type"));
                if(rs.getString("status_id").equals("1")){
                    v.add("Active");
                } else {
                    v.add("Inactive");
                }
                dtm.addRow(v);
            }
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            userTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void init() {
        FlatSVGIcon userReportIcon = new FlatSVGIcon("lk/inam/glizmo/img/report.svg", 15, 15);
        userReportIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        userReport.setIcon(userReportIcon);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        userReport = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        userTable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Full Name", "Email", "Contact", "Gender", "Create at", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(userTable);

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel3.setText("User Management");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        userReport.setBackground(new java.awt.Color(30, 144, 255));
        userReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        userReport.setForeground(new java.awt.Color(255, 255, 255));
        userReport.setText("Report");
        userReport.setIconTextGap(8);
        userReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(userReport, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userReport, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userReportActionPerformed
        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/user_report.jasper");

            if (filePath == null) {
                JOptionPane.showMessageDialog(this, "Report file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HashMap<String, Object> parameters = new HashMap<>();
            Connection connection = MySQL.getConnection();

            JasperPrint fillReport = JasperFillManager.fillReport(filePath, parameters, connection);
            JasperViewer.viewReport(fillReport, false);

        } catch (HeadlessException | JRException e) {
            JOptionPane.showMessageDialog(this, "Error generating report:\n" + e.getMessage(),
                    "Report Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_userReportActionPerformed

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown

    }//GEN-LAST:event_jPanel1ComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton userReport;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
