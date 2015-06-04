package com.deskdocs.view;

import com.deskdocs.entidades.Usuario;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class CadastroUsuarioGUI extends javax.swing.JDialog
{
	private Usuario usuario = null;

	public CadastroUsuarioGUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();

		SwingUtilities.getRootPane(this).setDefaultButton(btnCadastrar);
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      txtNome = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      txtLogin = new javax.swing.JTextField();
      txtSenha = new javax.swing.JPasswordField();
      btnCadastrar = new javax.swing.JButton();
      lblLabelTitulo = new javax.swing.JLabel();
      jSeparator1 = new javax.swing.JSeparator();
      btnCancelar = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("DeskDocs - Cadastro de Usuário");
      setResizable(false);

      jLabel1.setText("Nome:");

      jLabel2.setText("Login:");

      jLabel3.setText("Senha:");

      btnCadastrar.setText("Cadastrar");
      btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCadastrarActionPerformed(evt);
         }
      });

      lblLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
      lblLabelTitulo.setForeground(new java.awt.Color(153, 153, 153));
      lblLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblLabelTitulo.setText("Cadastro de usuário");

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
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(0, 0, Short.MAX_VALUE)
                  .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addComponent(jSeparator1)
               .addComponent(lblLabelTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel1)
                     .addComponent(jLabel2)
                     .addComponent(jLabel3))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                     .addComponent(txtSenha)
                     .addComponent(txtNome))
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
               .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel1))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel3))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnCadastrar)
               .addComponent(btnCancelar))
            .addContainerGap(26, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	public Usuario showDialog() {
		setLocationRelativeTo(getParent());
		setVisible(true);
		return usuario;
	}

   private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
		usuario = new Usuario(txtNome.getText(), txtLogin.getText(), new String(txtSenha.getPassword()).trim());
		dispose();
   }//GEN-LAST:event_btnCadastrarActionPerformed

   private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
		dispose();
   }//GEN-LAST:event_btnCancelarActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCadastrar;
   private javax.swing.JButton btnCancelar;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JLabel lblLabelTitulo;
   private javax.swing.JTextField txtLogin;
   private javax.swing.JTextField txtNome;
   private javax.swing.JPasswordField txtSenha;
   // End of variables declaration//GEN-END:variables
}
