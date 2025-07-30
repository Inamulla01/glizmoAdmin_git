/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lk.inam.glizmo.gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import lk.inam.glizmo.util.AppIconUtil;



public class SplashScreen extends javax.swing.JFrame {

    public SplashScreen() {
        initComponents();
        loadingAnimation();
        init();
    }
    private void init() {
        AppIconUtil.applyIcon(this);
    }
    private void loadingAnimation() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    jProgressBar1.setValue(i);
                    if (i < 30) {
                        jLabel1.setText("Loading... " + i + "%");
                        
                    } else if (i > 30 && i < 50) {
                        jLabel1.setText("Starting modules... " + i + "%");
                        
                    }else if (i >50 && i < 67) {
                        jLabel1.setText("Database connection established... " + i + "%");
                        
                    } else if (i > 67) {
                        jLabel1.setText("Done... " + i + "%");
                        jLabel1.setForeground(Color.white);
                    }
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {

                    }
                }
                new LoginScreen().setVisible(true);
                splashScreen.dispose();
            }
        });
        t.start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glizmo");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel1.setText("Loading...");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 490, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/inam/glizmo/img/splash_screen.gif"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 350));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private static SplashScreen splashScreen;
    
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();

                splashScreen = new SplashScreen();
                splashScreen.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}
