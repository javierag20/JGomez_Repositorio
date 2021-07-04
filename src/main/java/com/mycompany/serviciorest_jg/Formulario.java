/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serviciorest_jg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Image;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 *
 * @author Javier Gomez
 */
public class Formulario extends javax.swing.JFrame {

     /*JGomez: Se declaran las variables de uso para la clase */
     private final HttpClient httpClient  = HttpClient.newBuilder()
             .version(HttpClient.Version.HTTP_2).build();
     /*Objeto de tipo arreglo para contener los campos del servicio REST*/
     private final Object[] Colum = new Object[] {"month", "num", "link", "year", "news", "safetitle", "transcript", "alt", "title", "day", "img"};
     private final DefaultTableModel model = new DefaultTableModel(Colum,0);
     Image image = null;
     URL urlimage;
     public String url;
     int cont;
    /**
     * Creates new form Formulario
     */
    public Formulario() {
        initComponents();
        
          /*JGomez: Se inicia contador en 1 que se utiliza para armar el URL del servicio REST */
          cont = 1;
          url = "https://xkcd.com/"+cont+"/info.0.json";
          /*JGomez: Se llama al método MapearRespuestaRest que se encarga de cosumir el servicio y mapear la respuesta del mismo */
          MapearRespuestaRest();
         
        
    }
    
    private void LimpiarCampos() 
    { 
              /*JGomez: método para limpiar los campos de texto del formulario */
             this.txtmonth.setText(" ");
             this.txtnum.setText(" ");
             this.txtlink.setText(" ");
             this.txtyear.setText(" ");
             this.txtnews.setText(" ");
             this.txtsafetitle.setText(" ");
             this.txttranscript.setText(" ");
             this.txtalt.setText(" ");
             this.txttitle.setText(" ");
             this.txtday.setText(" ");
    }
    private void MapearRespuestaRest() 
            
       {     
           try { 
                  /*JGomez: Se costruye la petición tipo GET con el URL del servicio REST */
                 HttpRequest requestPosts = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
      
                 /*JGomez: Se captura la respuesta JSON de la peticion al servicio REST 
                 luego se almacena en una lista, se creó la clase PostRestMillicom 
                 para manejar los valores de los campos de la respuesta del servicio
                 y el método convertirObjeto se creó para mapear la respuesta
                 del servicio, para esta funcionalidad se importo las librerias de
                 jackson Databind*/
                HttpResponse<String> response =httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
                List<PostRestMillicom> posts = convertirObjeto (response.body(), new TypeReference<List<PostRestMillicom>>() {});
                
                /*Se invoca al método que limpia los campos de texto del formulario*/
                LimpiarCampos();
                
                /*JGomez: proceso para cargar en una variable model cada valor de respuesta del servicio*/
                posts.stream().forEach(item -> {
                   model.addRow(new Object[ ] {item.getMonth(), item.getNum() ,item.getLink(), item.getYear(), item.getNews(), item.getSafe_title(), item.getTranscript(), item.getAlt(), item.getTitle(), item.getDay(), item.getImg()} );
                });
            
            
                /* JGomez: se cargan los campos de texto del formulario con los valores
                devueltos por el servicio haciendo uso de la variable model
                */
                this.txtmonth.setText(model.getValueAt(0, 0).toString());
                this.txtnum.setText(model.getValueAt(0, 1).toString());
                this.txtlink.setText(model.getValueAt(0, 2).toString());
                this.txtyear.setText(model.getValueAt(0, 3).toString());
                this.txtnews.setText(model.getValueAt(0, 4).toString());
                this.txtsafetitle.setText(model.getValueAt(0, 5).toString());
                this.txttranscript.setText(model.getValueAt(0, 6).toString());
                this.txtalt.setText(model.getValueAt(0, 7).toString());
                this.txttitle.setText(model.getValueAt(0, 8).toString());
                this.txtday.setText(model.getValueAt(0, 9).toString());
                
                /*JGomez Se captura la URL de la imagen que viene en el servicio
                 para luego asignarla a la etiqueta lblimg del formulario,
                esta etiqueta se agrego dentro de un JPanel en el formulario.
                */
                urlimage = new URL(model.getValueAt(0, 10).toString());
                image = ImageIO.read(urlimage).getScaledInstance(lblimg.getWidth(),
                lblimg.getHeight(),Image.SCALE_SMOOTH);
             
                lblimg.setIcon(new ImageIcon(image));
             
                posts.stream().close();
                model.removeRow(0);
             
             
         } catch (IOException | InterruptedException ex) {
             Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
         } 
            
            
    }        
            
    
    
    final ObjectMapper mapper = new ObjectMapper();
    
    
    public <T> T convertirObjeto(final String json, final TypeReference<T> reference)
         {
         try {
             mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
           return this.mapper.readValue(json, reference);
                     } catch (JsonProcessingException ex) {
             Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
         }   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmonth = new javax.swing.JTextField();
        txtnum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlink = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtyear = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtnews = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtsafetitle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtalt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttitle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtday = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttranscript = new javax.swing.JTextField();
        Panel_Imagen = new javax.swing.JPanel();
        lblimg = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        siguiente = new javax.swing.JButton();
        Inicio = new javax.swing.JButton();
        Anterior = new javax.swing.JButton();
        Fin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Month");

        jLabel2.setText("Num");

        txtmonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmonthActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Prueba de Millicom (Javier Gómez)");

        jLabel4.setText("Link");

        jLabel5.setText("Year");

        jLabel6.setText("News");

        jLabel7.setText("Safe_title");

        jLabel8.setText("Alt");

        jLabel9.setText("Title");

        jLabel10.setText("Day");

        txtday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdayActionPerformed(evt);
            }
        });

        jLabel11.setText("Transcript");

        txttranscript.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttranscriptActionPerformed(evt);
            }
        });

        Panel_Imagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Panel_Imagen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Panel_Imagen.add(lblimg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 291, 285));

        jLabel12.setText("Img");

        siguiente.setText("Siguiente >");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        Inicio.setText("<< Inicio");
        Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioActionPerformed(evt);
            }
        });

        Anterior.setText("< Anterior");
        Anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnteriorActionPerformed(evt);
            }
        });

        Fin.setText("Fin >>");
        Fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26)
                                .addComponent(txtnews))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtalt)
                                    .addComponent(txtsafetitle)
                                    .addComponent(txttitle)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtday, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel2))
                                            .addGap(27, 27, 27)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtlink, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(30, 30, 30)
                                            .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(171, 171, 171)))
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel10)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Panel_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 747, Short.MAX_VALUE))
                            .addComponent(txttranscript))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(Inicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Anterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Fin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtlink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtnews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtsafetitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txttitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttranscript, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Panel_Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siguiente)
                    .addComponent(Inicio)
                    .addComponent(Anterior)
                    .addComponent(Fin))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    private void txtmonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmonthActionPerformed

    private void txtdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdayActionPerformed

    private void txttranscriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttranscriptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttranscriptActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
     
         cont++;
        if (cont>2484){
            cont=2484;
        }
        url = "https://xkcd.com/" + cont + "/info.0.json";
        MapearRespuestaRest();
    }//GEN-LAST:event_siguienteActionPerformed

    private void InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioActionPerformed
          cont = 1;
          url = "https://xkcd.com/"+cont+"/info.0.json";
          MapearRespuestaRest();
    }//GEN-LAST:event_InicioActionPerformed

    private void AnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnteriorActionPerformed
       cont--;
        if (cont<1){
            cont=1;
        }
        url = "https://xkcd.com/" + cont + "/info.0.json";
        MapearRespuestaRest();
    }//GEN-LAST:event_AnteriorActionPerformed

    private void FinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinActionPerformed
         cont = 2484;
        url = "https://xkcd.com/" + cont + "/info.0.json";
        MapearRespuestaRest();
    }//GEN-LAST:event_FinActionPerformed

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
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Anterior;
    private javax.swing.JButton Fin;
    private javax.swing.JButton Inicio;
    private javax.swing.JPanel Panel_Imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblimg;
    private javax.swing.JButton siguiente;
    private javax.swing.JTextField txtalt;
    private javax.swing.JTextField txtday;
    private javax.swing.JTextField txtlink;
    private javax.swing.JTextField txtmonth;
    private javax.swing.JTextField txtnews;
    private javax.swing.JTextField txtnum;
    private javax.swing.JTextField txtsafetitle;
    private javax.swing.JTextField txttitle;
    private javax.swing.JTextField txttranscript;
    private javax.swing.JTextField txtyear;
    // End of variables declaration//GEN-END:variables
}



