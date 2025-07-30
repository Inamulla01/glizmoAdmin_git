/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.inam.glizmo.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author moham
 */
public class WarrantiesPanel extends javax.swing.JPanel {

    /**
     * Creates new form WarrantiesPanel
     */
    public WarrantiesPanel() {
        initComponents();
        init();
        loadUserTable();
    }

    private void loadUserTable() {
        JTableHeader tableHeader = warrantyTable.getTableHeader();
        tableHeader.setFont(new java.awt.Font("Nunito ExtraBold", java.awt.Font.BOLD, 16));
        tableHeader.setForeground(new java.awt.Color(3, 4, 94));
        tableHeader.setBackground(new java.awt.Color(173, 232, 244));
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM view_user_warranty_details");
            DefaultTableModel dtm = (DefaultTableModel) warrantyTable.getModel();
            dtm.setRowCount(0);
            int count = 0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("warranty_id"));
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("warranty_start"));
                v.add(rs.getString("warranty_end"));
                v.add(rs.getString("claim_date"));
                v.add(rs.getString("claim_description"));
                v.add(rs.getString("users_id"));
                v.add(rs.getString("email"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("status_type"));

                dtm.addRow(v);
            }
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            warrantyTable.setDefaultRenderer(Object.class, centerRenderer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFilteredTable(String status) {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM view_user_warranty_details WHERE status_type='" + status + "'");
            DefaultTableModel dtm = (DefaultTableModel) warrantyTable.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("warranty_id"));
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("warranty_start"));
                v.add(rs.getString("warranty_end"));
                v.add(rs.getString("claim_date"));
                v.add(rs.getString("claim_description"));
                v.add(rs.getString("users_id"));
                v.add(rs.getString("email"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("status_type"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {

        FlatSVGIcon warrantyReportIcon = new FlatSVGIcon("lk/inam/glizmo/img/report.svg", 15, 15);
        warrantyReportIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        warrantyReport.setIcon(warrantyReportIcon);
        buttonGroup1.add(active);
        buttonGroup1.add(expired);
        buttonGroup1.add(claimed);
        buttonGroup1.add(reqected);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        warrantyTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        warrantyReport = new javax.swing.JButton();
        active = new javax.swing.JRadioButton();
        claimed = new javax.swing.JRadioButton();
        reqected = new javax.swing.JRadioButton();
        expired = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));

        warrantyTable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        warrantyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Product ID", "Product Name", "Warranty Start", "Warranty End", "Claim Date", "Claim Description", "User ID", "User Email", "Order ID", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        warrantyTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(warrantyTable);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jLabel1.setText("Search By Status :");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        jLabel3.setText("Warranty Management");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        warrantyReport.setBackground(new java.awt.Color(30, 144, 255));
        warrantyReport.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        warrantyReport.setForeground(new java.awt.Color(255, 255, 255));
        warrantyReport.setText("Report");
        warrantyReport.setIconTextGap(8);
        warrantyReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warrantyReportActionPerformed(evt);
            }
        });

        active.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        active.setText("Active");
        active.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeActionPerformed(evt);
            }
        });

        claimed.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        claimed.setText("Claimed");
        claimed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claimedActionPerformed(evt);
            }
        });

        reqected.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        reqected.setText("Rejected");
        reqected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqectedActionPerformed(evt);
            }
        });

        expired.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        expired.setText("Expired");
        expired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expiredActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(active)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expired)
                                .addGap(12, 12, 12)
                                .addComponent(claimed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reqected)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(warrantyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warrantyReport, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(active)
                    .addComponent(claimed)
                    .addComponent(reqected)
                    .addComponent(expired))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void warrantyReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warrantyReportActionPerformed
        try {

            InputStream filePath = getClass().getClassLoader().getResourceAsStream("lk/inam/glizmo/reports/warranty_report.jasper");

            if (filePath == null) {
                JOptionPane.showMessageDialog(this, "Report file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HashMap<String, Object> parameters = new HashMap<>();
            Connection connection = MySQL.getConnection();

            JasperPrint fillReport = JasperFillManager.fillReport(filePath, parameters, connection);
            JasperViewer.viewReport(fillReport, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating report:\n" + e.getMessage(),
                    "Report Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_warrantyReportActionPerformed

    private void activeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeActionPerformed
        loadFilteredTable("Active");
    }//GEN-LAST:event_activeActionPerformed

    private void expiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expiredActionPerformed
        loadFilteredTable("Expired");
    }//GEN-LAST:event_expiredActionPerformed

    private void claimedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claimedActionPerformed
        loadFilteredTable("Climed");
    }//GEN-LAST:event_claimedActionPerformed

    private void reqectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqectedActionPerformed
        loadFilteredTable("Rejected");
    }//GEN-LAST:event_reqectedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton active;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton claimed;
    private javax.swing.JRadioButton expired;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JRadioButton reqected;
    private javax.swing.JButton warrantyReport;
    private javax.swing.JTable warrantyTable;
    // End of variables declaration//GEN-END:variables
}
