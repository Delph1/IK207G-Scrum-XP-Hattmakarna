
package hattmakarna;

import java.awt.Component;
import javax.swing.JPanel;
import panels.ModuleSpecialOrder;

public class ModuleWindow extends javax.swing.JFrame {

    public ModuleWindow() {
        initComponents();
    }
    
    public void specialOrder() {
        showPanel(new ModuleSpecialOrder(this));
    }
    
    // Intern metod för att visa ett panelobjekt i vår panel
    private void showPanel(JPanel newPanel) {
        // Rensa mainPanel från tidigare tillagda paneler
        pnlModule.removeAll();

        // Lägg till den nya panelen
        pnlModule.add(newPanel);

        // Målar den nya panelen
        redrawCurrentPanel();
    }
    
    // Intern metod för att "måla" en panel
    private void redrawCurrentPanel() {
        Component currentPanel = pnlModule.getComponent(0);
        currentPanel.setPreferredSize(pnlModule.getSize());
        currentPanel.revalidate();
        currentPanel.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlModule = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout pnlModuleLayout = new javax.swing.GroupLayout(pnlModule);
        pnlModule.setLayout(pnlModuleLayout);
        pnlModuleLayout.setHorizontalGroup(
            pnlModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        pnlModuleLayout.setVerticalGroup(
            pnlModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlModule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlModule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlModule;
    // End of variables declaration//GEN-END:variables
}
