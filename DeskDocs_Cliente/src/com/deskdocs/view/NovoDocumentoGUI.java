package com.deskdocs.view;

import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class NovoDocumentoGUI extends javax.swing.JDialog
{
	private String nomeDocumento;

	public NovoDocumentoGUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		SwingUtilities.getRootPane(this).setDefaultButton(btnCriarDocumento);
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      lblNomeDocumento = new javax.swing.JLabel();
      txtNomeDocumento = new javax.swing.JTextField();
      btnCriarDocumento = new javax.swing.JButton();
      lblLabelTitulo = new javax.swing.JLabel();
      btnCancelar = new javax.swing.JButton();
      jSeparator1 = new javax.swing.JSeparator();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("DeskDocs - Novo Documento");
      setResizable(false);

      lblNomeDocumento.setText("Nome do documento:");

      btnCriarDocumento.setText("Criar");
      btnCriarDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCriarDocumentoActionPerformed(evt);
         }
      });

      lblLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
      lblLabelTitulo.setForeground(new java.awt.Color(153, 153, 153));
      lblLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblLabelTitulo.setText("Crie um novo documento");

      btnCancelar.setText("Cancelar");
      btnCancelar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelarActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jSeparator1)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnCriarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(lblLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(lblNomeDocumento)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(txtNomeDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblNomeDocumento)
               .addComponent(txtNomeDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnCriarDocumento)
               .addComponent(btnCancelar))
            .addContainerGap(25, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	public String showDialog() {
		setLocationRelativeTo(getParent());
		setVisible(true);
		return nomeDocumento;
	}

   private void btnCriarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarDocumentoActionPerformed
		nomeDocumento = txtNomeDocumento.getText();
		dispose();
   }//GEN-LAST:event_btnCriarDocumentoActionPerformed

   private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
		dispose();
   }//GEN-LAST:event_btnCancelarActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCancelar;
   private javax.swing.JButton btnCriarDocumento;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JLabel lblLabelTitulo;
   private javax.swing.JLabel lblNomeDocumento;
   private javax.swing.JTextField txtNomeDocumento;
   // End of variables declaration//GEN-END:variables
}
