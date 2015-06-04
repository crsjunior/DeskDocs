package com.deskdocs.view;

import com.deskdocs.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class CompartilharDocumentoGUI extends javax.swing.JDialog
{
	private boolean retornoDialogo;

	// lista de usuario nao compartilhados com o documento:
	private List<Usuario> naoCompartilhados;
	// lista de usuario compartilhados com o documento:
	private List<Usuario> compartilhados;
	// lista de nomes dos usuario nao compartilhados com o documento:
	private List<String> strNaoCompartilhados;
	// lista de nomes dos usuario compartilhados com o documento:
	private List<String> strCompartilhados;

	public CompartilharDocumentoGUI(java.awt.Frame parent, boolean modal, String nomeDocumento, List<Usuario> naoCompartilhados, List<Usuario> compartihados) {
		super(parent, modal);
		initComponents();

		SwingUtilities.getRootPane(this).setDefaultButton(btnConfirmar);

		this.retornoDialogo = false;

		lblNomeDocumento.setText(nomeDocumento);

		this.naoCompartilhados = naoCompartilhados;
		this.compartilhados = compartihados;

		this.strNaoCompartilhados = new ArrayList<String>();
		this.strCompartilhados = new ArrayList<String>();

		// populando as listas de nomes dos usuarios, para serem exibidos nas jLists:
		for (Usuario u : this.naoCompartilhados) {
			this.strNaoCompartilhados.add(u.getNome());
		}
		for (Usuario u : this.compartilhados) {
			this.strCompartilhados.add(u.getNome());
		}

		atualizarListas();
	}

	public boolean showDialog() {
		setLocationRelativeTo(getParent());
		setVisible(true);
		return retornoDialogo;
	}

	private void atualizarListas() {
		// atualizando lista de usuarios descompartilhados do documento:
		listDescompartilhados.setModel(new AbstractListModel()
		{
			String[] strings = strNaoCompartilhados.toArray(new String[strNaoCompartilhados.size()]);

			@Override
			public int getSize() {
				return strings.length;
			}

			@Override
			public Object getElementAt(int index) {
				return strings[index];
			}
		});

		// atualizando lista de usuarios compartilhados ao documento:
		listCompartilhados.setModel(new AbstractListModel()
		{
			String[] strings = strCompartilhados.toArray(new String[strCompartilhados.size()]);

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

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jScrollPane1 = new javax.swing.JScrollPane();
      listDescompartilhados = new javax.swing.JList();
      jScrollPane2 = new javax.swing.JScrollPane();
      listCompartilhados = new javax.swing.JList();
      btnCompartilhar = new javax.swing.JButton();
      btnDescompartilhar = new javax.swing.JButton();
      btnConfirmar = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      lblLabelTitulo = new javax.swing.JLabel();
      jSeparator1 = new javax.swing.JSeparator();
      lblLabelNomeDocumento = new javax.swing.JLabel();
      lblNomeDocumento = new javax.swing.JLabel();
      btnCancelar = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("DeskDocs - Compartilhar Documento");
      setResizable(false);

      listDescompartilhados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      listDescompartilhados.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            listDescompartilhadosMouseClicked(evt);
         }
      });
      jScrollPane1.setViewportView(listDescompartilhados);

      listCompartilhados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      listCompartilhados.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            listCompartilhadosMouseClicked(evt);
         }
      });
      jScrollPane2.setViewportView(listCompartilhados);

      btnCompartilhar.setText(">");
      btnCompartilhar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCompartilharActionPerformed(evt);
         }
      });

      btnDescompartilhar.setText("<");
      btnDescompartilhar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDescompartilharActionPerformed(evt);
         }
      });

      btnConfirmar.setText("Confirmar");
      btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnConfirmarActionPerformed(evt);
         }
      });

      jLabel1.setText("Não compartilhando:");

      jLabel2.setText("Compartilhando:");

      lblLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
      lblLabelTitulo.setForeground(new java.awt.Color(153, 153, 153));
      lblLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblLabelTitulo.setText("Compartilhamento de documento");

      lblLabelNomeDocumento.setText("Nome do documento:");

      lblNomeDocumento.setText("Nenhum");

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
               .addComponent(lblLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jSeparator1)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                     .addGap(10, 10, 10)
                     .addComponent(lblLabelNomeDocumento)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                     .addComponent(lblNomeDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addGroup(layout.createSequentialGroup()
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                           .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGap(18, 18, 18)
                           .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(btnCompartilhar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(btnDescompartilhar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                           .addGap(8, 8, 8)
                           .addComponent(jLabel1)))
                     .addGap(18, 18, 18)
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                           .addComponent(jLabel2)
                           .addGap(73, 73, 73)))
                     .addGap(0, 0, Short.MAX_VALUE))))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelNomeDocumento)
               .addComponent(lblNomeDocumento))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addComponent(btnCompartilhar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(btnDescompartilhar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(21, 21, 21))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel2)
                     .addComponent(jLabel1))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnConfirmar)
               .addComponent(btnCancelar))
            .addContainerGap(26, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnCompartilharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompartilharActionPerformed
		int indice = listDescompartilhados.getSelectedIndex();
		if (indice != -1) {
			// atualizando listas de usuarios:
			compartilhados.add(naoCompartilhados.get(indice));
			naoCompartilhados.remove(indice);
			// atualizando lista de nomes de usuarios:
			strCompartilhados.add(strNaoCompartilhados.get(indice));
			strNaoCompartilhados.remove(indice);
			atualizarListas();
		}
   }//GEN-LAST:event_btnCompartilharActionPerformed

   private void btnDescompartilharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescompartilharActionPerformed
		int indice = listCompartilhados.getSelectedIndex();
		if (indice != -1) {
			// atualizando listas de usuarios:
			naoCompartilhados.add(compartilhados.get(indice));
			compartilhados.remove(indice);
			// atualizando lista de nomes de usuarios:
			strNaoCompartilhados.add(strCompartilhados.get(indice));
			strCompartilhados.remove(indice);
			atualizarListas();
		}
   }//GEN-LAST:event_btnDescompartilharActionPerformed

   private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
		retornoDialogo = true;
		dispose();
   }//GEN-LAST:event_btnConfirmarActionPerformed

   private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
		dispose();
   }//GEN-LAST:event_btnCancelarActionPerformed

   private void listDescompartilhadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDescompartilhadosMouseClicked
		if (evt.getClickCount() == 2) {
			int indice = listDescompartilhados.locationToIndex(evt.getPoint());
			if (indice != -1) {
				// atualizando listas de usuarios:
				compartilhados.add(naoCompartilhados.get(indice));
				naoCompartilhados.remove(indice);
				// atualizando lista de nomes de usuarios:
				strCompartilhados.add(strNaoCompartilhados.get(indice));
				strNaoCompartilhados.remove(indice);
				atualizarListas();
			}
		}
   }//GEN-LAST:event_listDescompartilhadosMouseClicked

   private void listCompartilhadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCompartilhadosMouseClicked
		if (evt.getClickCount() == 2) {
			int indice = listCompartilhados.locationToIndex(evt.getPoint());
			if (indice != -1) {
				// atualizando listas de usuarios:
				naoCompartilhados.add(compartilhados.get(indice));
				compartilhados.remove(indice);
				// atualizando lista de nomes de usuarios:
				strNaoCompartilhados.add(strCompartilhados.get(indice));
				strCompartilhados.remove(indice);
				atualizarListas();
			}
		}
   }//GEN-LAST:event_listCompartilhadosMouseClicked

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCancelar;
   private javax.swing.JButton btnCompartilhar;
   private javax.swing.JButton btnConfirmar;
   private javax.swing.JButton btnDescompartilhar;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JLabel lblLabelNomeDocumento;
   private javax.swing.JLabel lblLabelTitulo;
   private javax.swing.JLabel lblNomeDocumento;
   private javax.swing.JList listCompartilhados;
   private javax.swing.JList listDescompartilhados;
   // End of variables declaration//GEN-END:variables
}
