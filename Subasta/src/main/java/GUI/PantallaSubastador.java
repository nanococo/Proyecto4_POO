/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import App.Product;
import App.Accounts.AccountTypes;
import Messages.AuctionsInfo;
import Networking.ClientSide.Client;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Fernando Alvarez
 */
    

public class PantallaSubastador extends javax.swing.JFrame implements SubastaFrame{

    /**
     * Creates new form PantallaSubastador
     */
    private AuctionsInfo currentAuctionInfo;

    private final Client client;
    private final String nickName;
    private ArrayList<AuctionsInfo> auctionsInfos = new ArrayList<>();
    private final String id = UUID.randomUUID().toString();
    String path = System.getProperty("user.dir")+"/src/main/resources/";

    PantallaSubastador(String nickName) {
        initComponents();
        this.nickName = nickName;
        this.client = new Client("127.0.0.1", 7800, AccountTypes.AUCTIONEER, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevaSubasta = new javax.swing.JButton();
        btnCerrarSubasta = new javax.swing.JButton();
        btnCancelarSubasta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblImagen = new javax.swing.JLabel();
        btnMisSubastas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNuevaSubasta.setText("Nueva Subasta");
        btnNuevaSubasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaSubastaActionPerformed(evt);
            }
        });

        btnCerrarSubasta.setText("Cerrar Subasta");
        btnCerrarSubasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSubastaActionPerformed(evt);
            }
        });

        btnCancelarSubasta.setText("Cancelar Subasta");
        btnCancelarSubasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSubastaActionPerformed(evt);
            }
        });

        txtDescription.setEditable(false);
        txtDescription.setColumns(5);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        btnMisSubastas.setText("Mis Subastas");
        btnMisSubastas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisSubastasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevaSubasta, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSubasta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarSubasta)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMisSubastas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnMisSubastas)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevaSubasta)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelarSubasta)
                                .addComponent(btnCerrarSubasta)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ArrayList<AuctionsInfo> getAuctionsInfos() {
        return auctionsInfos;
    }

    public void setAuctionsInfos(ArrayList<AuctionsInfo> auctionsInfos) {
        this.auctionsInfos = auctionsInfos;
    }

    public AuctionsInfo getCurrentAuctionInfo() {
        return currentAuctionInfo;
    }

    public String getNickName() {
        return nickName;
    }

    public String getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    private void btnCerrarSubastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSubastaActionPerformed
        // Se cierra la subasta y se entrega al que dio el tope mas alto
        if(currentAuctionInfo !=null){
            client.closeAuction(currentAuctionInfo.getId());
            showNotification("Auction Closed!");

            if(currentAuctionInfo.getHighestBidderID().isEmpty()){
                showNotification("No hubo ganador en la subasta");
            } else {
                new PantallaEvioDeMensaje(this);

            }

        } else {
            showNotification("You have to choose an auction to close!");
        }

    }//GEN-LAST:event_btnCerrarSubastaActionPerformed

    private void btnNuevaSubastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaSubastaActionPerformed
        PantallaCreacionSubasta pantalla = new PantallaCreacionSubasta(this);
        pantalla.setVisible(true);
    }//GEN-LAST:event_btnNuevaSubastaActionPerformed

    private void btnCancelarSubastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSubastaActionPerformed
        // Las subasta se cierra y termina sin nadie llevandose el objeto

        if(currentAuctionInfo !=null){
            client.cancelCurrentAuction(currentAuctionInfo.getId());
            showNotification("Auction Cancelled! :c");
        } else {
            showNotification("You have to choose an auction to cancel!");
        }


    }//GEN-LAST:event_btnCancelarSubastaActionPerformed

    private void btnMisSubastasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisSubastasActionPerformed

        client.getAuctioneerAuctions(nickName);
        try{Thread.sleep(1000);}catch (Exception e){ e.printStackTrace(); }

        PantallaSubastas pantallaSubasta = new PantallaSubastas(this,"Mis Subastas");
        pantallaSubasta.setVisible(true);
    }//GEN-LAST:event_btnMisSubastasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarSubasta;
    private javax.swing.JButton btnCerrarSubasta;
    private javax.swing.JButton btnMisSubastas;
    private javax.swing.JButton btnNuevaSubasta;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables

    public void creandoSubasta(Product product) {
        //Se envia el articulo al servidor para que se cree una subasta con el
        client.createAuction(product);
    }
    
    @Override
    public void showAuction(AuctionsInfo subasta){
        this.currentAuctionInfo = subasta;
        this.txtDescription.setText(subasta.toString());
        cargarImagen(subasta.getProduct().getImagePath());
    }
    
    public void getNotificationFromNewBid(String valor, String id, String buyerId){
        String[] options = {"Aceptar", "Rechazar"};
        int x = JOptionPane.showOptionDialog(null,
                "Nueva oferta para subasta#"+id+" por "+valor,
                "Nueva Oferta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
        );


        if(x==0){
            System.out.println("Aceptado");
            client.approveOffer(id, valor, buyerId);
        } else {
            System.out.println("Denegado");
        }
    }

    public void showNotification(String string){
        JOptionPane.showMessageDialog(this, string);
    }
    
    
    public void cargarImagen(String filename){
            File file = new File(path+filename);
        try {
            this.lblImagen.setIcon(new ImageIcon(ImageIO.read(file)));
        } catch (IOException ex) {
            Logger.getLogger(PantallaOferente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
