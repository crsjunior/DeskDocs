package com.deskdocs.view;

import com.deskdocs.entidades.Documento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class AbrirDocumentoGUI extends javax.swing.JDialog
{
	private int indice = -1;

	public AbrirDocumentoGUI(java.awt.Frame parent, boolean modal, List<Documento> documentos) {
		super(parent, modal);
		initComponents();

		SwingUtilities.getRootPane(this).setDefaultButton(btnAbrirDocumento);

		List<String> l = new ArrayList<String>();
		for (Documento d : documentos) {
			l.add(d.toString() + " (" + d.getProprietario().toString() + ")");
		}

		listDocumento.setModel(new AbstractListModel()
		{
			String[] strings = l.toArray(new String[l.size()]);

			@Override
			public int getSize() {
				return strings.length;
			}

			@Override
			public Object getElementAt(int index) {
				return strings[index];
			}
		});
	}

	public int showDialog() {
		setLocationRelativeTo(getParent());
		setVisible(true);
		return indice;
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jScrollPane1 = new javax.swing.JScrollPane();
      listDocumento = new javax.swing.JList();
      lblLabelDocumento = new javax.swing.JLabel();
      btnAbrirDocumento = new javax.swing.JButton();
      lblLabelTitulo = new javax.swing.JLabel();
      jSeparator1 = new javax.swing.JSeparator();
      btnCancelar = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("DeskDocs - Abrir Documento");
      setResizable(false);

      listDocumento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      listDocumento.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            listDocumentoMouseClicked(evt);
         }
      });
      jScrollPane1.setViewportView(listDocumento);

      lblLabelDocumento.setText("Selecione o documento:");

      btnAbrirDocumento.setText("Abrir");
      btnAbrirDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAbrirDocumentoActionPerformed(evt);
         }
      });

      lblLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
      lblLabelTitulo.setForeground(new java.awt.Color(153, 153, 153));
      lblLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblLabelTitulo.setText("Abrir documento");

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
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
               .addComponent(lblLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(lblLabelDocumento)
                  .addGap(0, 0, Short.MAX_VALUE))
               .addComponent(jSeparator1)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnAbrirDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(lblLabelDocumento)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnAbrirDocumento)
               .addComponent(btnCancelar))
            .addContainerGap(27, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnAbrirDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirDocumentoActionPerformed
		indice = listDocumento.getSelectedIndex();
		dispose();
   }//GEN-LAST:event_btnAbrirDocumentoActionPerformed

   private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
		dispose();
   }//GEN-LAST:event_btnCancelarActionPerformed

   private void listDocumentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDocumentoMouseClicked
		if (evt.getClickCount() == 2) {
			indice = listDocumento.locationToIndex(evt.getPoint());
			dispose();
		}
   }//GEN-LAST:event_listDocumentoMouseClicked

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnAbrirDocumento;
   private javax.swing.JButton btnCancelar;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JLabel lblLabelDocumento;
   private javax.swing.JLabel lblLabelTitulo;
   private javax.swing.JList listDocumento;
   // End of variables declaration//GEN-END:variables
}
