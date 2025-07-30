/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.inam.glizmo.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppIconUtil {
    
    private static Image appIcon;
    
    static {
        try {
            URL iconPath = AppIconUtil.class.getResource("/lk/inam/glizmo/img/logo-G.png");
            ImageIcon icon = new ImageIcon(iconPath);
            AppIconUtil.appIcon = icon.getImage();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Invalid icon path...");
        }
    }

  

    public static void applyIcon(JFrame frame) {
        if (frame != null) {
            frame.setIconImage(appIcon);
        }
    }
}
