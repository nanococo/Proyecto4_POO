/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import App.Auction;
import Messages.AuctionsInfo;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fernando Alvarez
 */
public class PantallaSubastas extends javax.swing.JFrame implements ListSelectionListener{

    
    private ArrayList<AuctionsInfo> auctionsInfos;
    SubastaFrame pantalla;
    
    public PantallaSubastas(SubastaFrame pantalla, String title){
        initComponents();
        this.auctionsInfos = ((PantallaOferente) pantalla).getAuctionsInfos();
        this.pantalla = pantalla;
        this.setTitle(title);


        setListInfo();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        listSubastas = new javax.swing.JList<>();
        lblText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(listSubastas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblText;
    private javax.swing.JList<String> listSubastas;
    // End of variables declaration//GEN-END:variables

    public void setListInfo(){
        String info[] = new String[auctionsInfos.size()];
        for (int i = 0; i< auctionsInfos.size(); i++) {
            AuctionsInfo subasta = auctionsInfos.get(i);
            info[i] = subasta.getProduct().getName()+subasta.getPujaMasAlta()+" Estado: "+subasta.getEstado();
        }
            listSubastas.setListData(info);
            listSubastas.addListSelectionListener(this);
    }
    
    public void askForList(){
        //Llena la lista y se guarda el nombre y el id de todas las subastas
        //Llama el metodo selecionar
    }
    public void selectSubasta(int index){
        //Con los id guardados manda a llamar al servidor con el id selecionado
        //Y los datos recibidos los muestra en la pantalla principal
        pantalla.mostrarSubasta(auctionsInfos.get(index));
        System.out.println(index);
        dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {            
            selectSubasta(listSubastas.getSelectedIndex());   
        }
    }

}
