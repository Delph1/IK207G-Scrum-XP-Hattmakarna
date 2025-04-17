
package hattmakarna;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import panels.*;

public class ModuleWindow extends javax.swing.JFrame {
    
    private JPanel panel;
    
    public ModuleWindow() {
        initComponents();
    }
    
    public void specialOrder() {
        showPanel(new ModuleSpecialOrder(this));
    }
    
    // Intern metod för att visa ett panelobjekt i vår panel
    private void showPanel(JPanel newPanel) {
        // Rensa pnlModule från tidigare tillagda paneler
        this.panel = newPanel;
        // Lägg till den nya panelen
        this.setLayout(new BorderLayout());
        this.add(newPanel, BorderLayout.CENTER);

        // Målar den nya panelen
        redrawCurrentPanel();
    }
    
    // Intern metod för att "måla" en panel
    private void redrawCurrentPanel() {
        panel.revalidate();
        panel.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
