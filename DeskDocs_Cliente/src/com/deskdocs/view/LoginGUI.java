package com.deskdocs.view;

import com.deskdocs.cliente.Cliente;
import com.deskdocs.entidades.Pacote;
import com.deskdocs.entidades.Usuario;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class LoginGUI extends javax.swing.JFrame
{
	private Cliente cliente = Cliente.getInstance();

	public LoginGUI() {
		initComponents();

		setLocationRelativeTo(null);
		SwingUtilities.getRootPane(this).setDefaultButton(btnEntrar);
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      txtLogin = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      txtSenha = new javax.swing.JPasswordField();
      btnEntrar = new javax.swing.JButton();
      btnCadastrar = new javax.swing.JButton();
      lblLabelTitulo = new javax.swing.JLabel();
      jSeparator1 = new javax.swing.JSeparator();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DeskDocs - Bem-Vindo");
      setResizable(false);

      jLabel1.setText("Usuário:");

      txtLogin.setText("carlos");

      jLabel2.setText("Senha:");

      txtSenha.setText("123");

      btnEntrar.setText("Entrar");
      btnEntrar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEntrarActionPerformed(evt);
         }
      });

      btnCadastrar.setText("Cadastrar-se");
      btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCadastrarActionPerformed(evt);
         }
      });

      lblLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
      lblLabelTitulo.setForeground(new java.awt.Color(153, 153, 153));
      lblLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblLabelTitulo.setText("Login de usuário");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lblLabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jSeparator1)
                     .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(jLabel2)
                           .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(txtLogin)
                           .addComponent(txtSenha)))
                     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
               .addComponent(jLabel1)
               .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel2)
               .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnEntrar)
               .addComponent(btnCadastrar))
            .addContainerGap(28, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	private boolean conectarServidor() {
		if (!cliente.isConectado()) {
			JOptionPane.showMessageDialog(this, "Não foi possível conectar ao servidor.", "DeskDocs - Alerta!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

   private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
		if (!conectarServidor()) {
			return;
		}

		Usuario usuario = new Usuario();
		usuario.setLogin(txtLogin.getText());
		usuario.setSenha(new String(txtSenha.getPassword()).trim());

		Pacote<Usuario> pUsuario = null;
		try {
			pUsuario = cliente.getDeskDocs().conectarUsuario(usuario);
//			usuario = cliente.getDeskDocs().conectarUsuario(usuario);
		} catch (RemoteException ex) {
			Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (pUsuario != null && !pUsuario.isSucesso()) {
			JOptionPane.showMessageDialog(this, pUsuario.getMensagem(), "DeskDocs - Alerta!", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

//		if (usuario == null) {
//			JOptionPane.showMessageDialog(this, "Usuário não foi encontrado. Tente novamente.", "DeskDocs - Alerta!", JOptionPane.INFORMATION_MESSAGE);
//			return;
//		}

		// setando o usuario logado:
		cliente.setUsuario(pUsuario.getObjeto());

		// abrindo o editor:
		EditorGUI editorGUI = new EditorGUI();
		editorGUI.setLocationRelativeTo(this);
		editorGUI.setVisible(true);
		dispose();
   }//GEN-LAST:event_btnEntrarActionPerformed

   private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
		if (!conectarServidor()) {
			return;
		}

		CadastroUsuarioGUI cadastroUsuarioGUI = new CadastroUsuarioGUI(this, true);
		Usuario usuario = cadastroUsuarioGUI.showDialog();

		Pacote<Usuario> pUsuario = null;
		if (usuario != null) {
			try {
				pUsuario = cliente.getDeskDocs().cadastrarUsuario(usuario);
//				usuario = cliente.getDeskDocs().cadastrarUsuario(usuario);
			} catch (RemoteException ex) {
				Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
			}

			if (pUsuario.isSucesso()) {
				JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.", "DeskDocs - Alerta!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, pUsuario.getMensagem(), "DeskDocs - Alerta!", JOptionPane.ERROR_MESSAGE);
			}

//			if (usuario == null) {
//				JOptionPane.showMessageDialog(this, "Erro ao cadastrar o usuário.", "DeskDocs - Alerta!", JOptionPane.ERROR_MESSAGE);
//			} else {
//				JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.", "DeskDocs - Alerta!", JOptionPane.INFORMATION_MESSAGE);
//			}
		}
   }//GEN-LAST:event_btnCadastrarActionPerformed

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
			java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				new LoginGUI().setVisible(true);
			}
		});
	}

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCadastrar;
   private javax.swing.JButton btnEntrar;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JLabel lblLabelTitulo;
   private javax.swing.JTextField txtLogin;
   private javax.swing.JPasswordField txtSenha;
   // End of variables declaration//GEN-END:variables
}
