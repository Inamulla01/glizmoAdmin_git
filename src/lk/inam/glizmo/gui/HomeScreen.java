/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lk.inam.glizmo.gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connection.MySQL;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import lk.inam.glizmo.dialog.AddAdminDialog;
import lk.inam.glizmo.dialog.ProfileDialog;
import lk.inam.glizmo.panel.DashboardPanel;
import lk.inam.glizmo.panel.OrderPanel;
import lk.inam.glizmo.panel.ProductPanel;
import lk.inam.glizmo.panel.StockPanel;
import lk.inam.glizmo.panel.UsersPanel;
import lk.inam.glizmo.panel.WarrantiesPanel;
import lk.inam.glizmo.session.Session;
import lk.inam.glizmo.util.AppIconUtil;

public class HomeScreen extends javax.swing.JFrame {

    private DashboardPanel dashboardPanel;
    private ProductPanel productPanel;
    private OrderPanel orderPanel;
    private WarrantiesPanel warrantiesPanel;
    private StockPanel stockPanel;
    private UsersPanel usersPanel;
    private CardLayout contentPanelLayout;

    private final HashMap<JButton, String> iconPaths;
    private final HashMap<JButton, FlatSVGIcon> coloredIcons = new HashMap<>();
    private final HashMap<JButton, FlatSVGIcon> whiteIcons = new HashMap<>();

    public HomeScreen() {

        this.iconPaths = new HashMap<>();
        initComponents();
        String loggedInEmail = Session.getInstance().getEmail();

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `admin` WHERE `email` = '" + loggedInEmail + "'");
            if (rs.next()) {
                helloLable.setText("Hello! " + rs.getString("name"));
            } else {
                JOptionPane.showMessageDialog(this, "No admin found with email: " + loggedInEmail, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
            
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        init();
        loadPanels();
    }

    private void init() {

        AppIconUtil.applyIcon(this);

        contentPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        logo.setIcon(new FlatSVGIcon("lk/inam/glizmo/img/logo-glizmo.svg", 200, 235));

        setupButtonIcon(dashboardBtn, "lk/inam/glizmo/img/dashboard.svg");
        setupButtonIcon(productBtn, "lk/inam/glizmo/img/product.svg");
        setupButtonIcon(stockBtn, "lk/inam/glizmo/img/stock.svg");
        setupButtonIcon(warrantiesBtn, "lk/inam/glizmo/img/warranty.svg");
        setupButtonIcon(ordersBtn, "lk/inam/glizmo/img/sales.svg");
        setupButtonIcon(usersBtn, "lk/inam/glizmo/img/users-1.svg");

        profileBtn.setIcon(createWhiteIcon("lk/inam/glizmo/img/profile.svg"));
        addAdminBtn.setIcon(createWhiteIcon("lk/inam/glizmo/img/add-admin.svg"));

        Color defaultColor = new Color(242, 242, 242);
        Color activeColor = new Color(30, 144, 255);
        Color defaultTextColor = Color.BLACK;
        Color activeTextColor = Color.WHITE;

        JButton[] navBtn = {
            dashboardBtn, productBtn, stockBtn, warrantiesBtn,
            ordersBtn, usersBtn
        };

        for (JButton btn : navBtn) {
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(true);
            btn.setOpaque(true);
            btn.setBorderPainted(false);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (JButton b : navBtn) {
                        b.setBackground(defaultColor);
                        b.setForeground(defaultTextColor);
                        b.setIcon(coloredIcons.get(b));
                    }
                    btn.setBackground(activeColor);
                    btn.setForeground(activeTextColor);
                    btn.setIcon(whiteIcons.get(btn));
                }
            });
        }

        dashboardBtn.setBackground(activeColor);
        dashboardBtn.setForeground(activeTextColor);
        dashboardBtn.setIcon(whiteIcons.get(dashboardBtn));
    }

    private void setupButtonIcon(JButton button, String path) {
        iconPaths.put(button, path);
        FlatSVGIcon normalIcon = new FlatSVGIcon(path, 20, 20);
        FlatSVGIcon whiteIcon = new FlatSVGIcon(path, 20, 20);
        whiteIcon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });

        coloredIcons.put(button, normalIcon);
        whiteIcons.put(button, whiteIcon);
        button.setIcon(normalIcon);
    }

    private FlatSVGIcon createWhiteIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 20, 20);
        icon.setColorFilter(new FlatSVGIcon.ColorFilter() {
            @Override
            public Color filter(Color color) {
                return Color.white;
            }
        });
        return icon;
    }

    private void loadPanels() {
        if (contentPanelLayout == null && contentPanel.getLayout() instanceof CardLayout) {
            this.contentPanelLayout = (CardLayout) contentPanel.getLayout();
        }

        this.dashboardPanel = new DashboardPanel();
        this.orderPanel = new OrderPanel();
        this.productPanel = new ProductPanel();
        this.warrantiesPanel = new WarrantiesPanel();
        this.stockPanel = new StockPanel();
        this.usersPanel = new UsersPanel();

        this.dashboardPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.orderPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.productPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.warrantiesPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.stockPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.usersPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");

        this.contentPanel.add(dashboardPanel, "dashboard_panel");
        this.contentPanel.add(orderPanel, "order_panel");
        this.contentPanel.add(productPanel, "product_panel");
        this.contentPanel.add(warrantiesPanel, "warranties_panel");
        this.contentPanel.add(stockPanel, "stock_panel");
        this.contentPanel.add(usersPanel, "users_panel");

        SwingUtilities.updateComponentTreeUI(contentPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuItemPanel = new javax.swing.JPanel();
        productBtn = new javax.swing.JButton();
        stockBtn = new javax.swing.JButton();
        dashboardBtn = new javax.swing.JButton();
        warrantiesBtn = new javax.swing.JButton();
        ordersBtn = new javax.swing.JButton();
        usersBtn = new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        headerPanel = new javax.swing.JPanel();
        addAdminBtn = new javax.swing.JButton();
        helloLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glizmo - Home");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        menuItemPanel.setBackground(new java.awt.Color(255, 255, 255));

        productBtn.setBackground(new java.awt.Color(242, 242, 242));
        productBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        productBtn.setText("Products");
        productBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productBtn.setIconTextGap(10);
        productBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productBtnActionPerformed(evt);
            }
        });

        stockBtn.setBackground(new java.awt.Color(242, 242, 242));
        stockBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        stockBtn.setText("Stocks");
        stockBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stockBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stockBtn.setIconTextGap(10);
        stockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockBtnActionPerformed(evt);
            }
        });

        dashboardBtn.setBackground(new java.awt.Color(242, 242, 242));
        dashboardBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardBtn.setIconTextGap(10);
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        warrantiesBtn.setBackground(new java.awt.Color(242, 242, 242));
        warrantiesBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        warrantiesBtn.setText("Warranties");
        warrantiesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        warrantiesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        warrantiesBtn.setIconTextGap(10);
        warrantiesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warrantiesBtnActionPerformed(evt);
            }
        });

        ordersBtn.setBackground(new java.awt.Color(242, 242, 242));
        ordersBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        ordersBtn.setText("Orders & Sales");
        ordersBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ordersBtn.setIconTextGap(10);
        ordersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersBtnActionPerformed(evt);
            }
        });

        usersBtn.setBackground(new java.awt.Color(242, 242, 242));
        usersBtn.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        usersBtn.setText("Users");
        usersBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usersBtn.setIconTextGap(10);
        usersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersBtnActionPerformed(evt);
            }
        });

        profileBtn.setBackground(new java.awt.Color(30, 144, 255));
        profileBtn.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        profileBtn.setForeground(new java.awt.Color(255, 255, 255));
        profileBtn.setText("Profile");
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuItemPanelLayout = new javax.swing.GroupLayout(menuItemPanel);
        menuItemPanel.setLayout(menuItemPanelLayout);
        menuItemPanelLayout.setHorizontalGroup(
            menuItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuItemPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(menuItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ordersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warrantiesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        menuItemPanelLayout.setVerticalGroup(
            menuItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuItemPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warrantiesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ordersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));

        addAdminBtn.setBackground(new java.awt.Color(30, 144, 255));
        addAdminBtn.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        addAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAdminBtn.setText("Add Admin");
        addAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminBtnActionPerformed(evt);
            }
        });

        helloLable.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 36)); // NOI18N
        helloLable.setText("Hello,");
        helloLable.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(helloLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(411, 411, 411)
                .addComponent(addAdminBtn)
                .addGap(16, 16, 16))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(helloLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane1.setViewportView(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
            .addComponent(menuItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "product_panel");
    }//GEN-LAST:event_productBtnActionPerformed

    private void stockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "stock_panel");
    }//GEN-LAST:event_stockBtnActionPerformed

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "dashboard_panel");
    }//GEN-LAST:event_dashboardBtnActionPerformed

    private void warrantiesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warrantiesBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "warranties_panel");
    }//GEN-LAST:event_warrantiesBtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void ordersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "order_panel");
    }//GEN-LAST:event_ordersBtnActionPerformed

    private void usersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersBtnActionPerformed
        this.contentPanelLayout.show(contentPanel, "users_panel");
    }//GEN-LAST:event_usersBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed
        ProfileDialog dialog = new ProfileDialog(this, true); // true = modal
        dialog.setLocationRelativeTo(this); // optional: center the dialog
    dialog.setVisible(true);    }//GEN-LAST:event_profileBtnActionPerformed

    private void addAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminBtnActionPerformed
        AddAdminDialog dialog = new AddAdminDialog(this, true); // true = modal
        dialog.setLocationRelativeTo(this); // optional: center the dialog
        dialog.setVisible(true);
    }//GEN-LAST:event_addAdminBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAdminBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel helloLable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel menuItemPanel;
    private javax.swing.JButton ordersBtn;
    private javax.swing.JButton productBtn;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton stockBtn;
    private javax.swing.JButton usersBtn;
    private javax.swing.JButton warrantiesBtn;
    // End of variables declaration//GEN-END:variables
}
