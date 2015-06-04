package com.deskdocs.view;

import com.deskdocs.contratos.IDeskDocs;
import com.deskdocs.contratos.IServidorGUI;
import com.deskdocs.servidor.DeskDocs;
import com.deskdocs.utilidades.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class ServidorGUI extends javax.swing.JFrame implements IServidorGUI
{
	private IDeskDocs deskDocs;
	private boolean executando;

	public ServidorGUI() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      panelTitulo = new javax.swing.JPanel();
      lblTitulo = new javax.swing.JLabel();
      lblAutor = new javax.swing.JLabel();
      btnIniciar = new javax.swing.JButton();
      lblStatusServidor = new javax.swing.JLabel();
      jLabel1 = new javax.swing.JLabel();
      lblUsuariosCadastrados = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      lblUsuariosConectados = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      lblDocumentosCriados = new javax.swing.JLabel();
      jLabel7 = new javax.swing.JLabel();
      lblDocumentosAbertos = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      txaSaida = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DeskDocs - Servidor");

      lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      lblTitulo.setText("Servidor do DeskDocs");

      lblAutor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      lblAutor.setText("Autor: Carlos R. Schwarz");

      javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
      panelTitulo.setLayout(panelTituloLayout);
      panelTituloLayout.setHorizontalGroup(
         panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(panelTituloLayout.createSequentialGroup()
            .addComponent(lblTitulo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
      );
      panelTituloLayout.setVerticalGroup(
         panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(lblTitulo)
            .addComponent(lblAutor))
      );

      btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      btnIniciar.setText("Iniciar servidor");
      btnIniciar.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnIniciarActionPerformed(evt);
         }
      });

      lblStatusServidor.setText("Servidor parado");

      jLabel1.setText("Usuários cadastrados:");

      lblUsuariosCadastrados.setText("0");

      jLabel3.setText("Usuários conectados:");

      lblUsuariosConectados.setText("0");

      jLabel5.setText("Documentos criados:");

      lblDocumentosCriados.setText("0");

      jLabel7.setText("Documentos abertos:");

      lblDocumentosAbertos.setText("0");

      txaSaida.setColumns(20);
      txaSaida.setRows(5);
      jScrollPane1.setViewportView(txaSaida);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblStatusServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addComponent(jScrollPane1))
            .addContainerGap())
         .addGroup(layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel1)
               .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lblUsuariosCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(lblDocumentosCriados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(68, 68, 68)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel7)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblDocumentosAbertos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jLabel3)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblUsuariosConectados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnIniciar)
               .addComponent(lblStatusServidor))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(lblUsuariosCadastrados)
               .addComponent(jLabel3)
               .addComponent(lblUsuariosConectados))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel5)
               .addComponent(lblDocumentosCriados)
               .addComponent(lblDocumentosAbertos)
               .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	@Override
	public void enviarMensagemOk(String mensagem) {
		txaSaida.append("[  OK  ]: " + mensagem + "\n");
	}

	@Override
	public void enviarMensagemOk(String mensagem, String metodo) {
		txaSaida.append("[  OK  ][" + metodo + "]: " + mensagem + "\n");
	}

	@Override
	public void enviarMensagemErro(String mensagem) {
		txaSaida.append("[ ERRO ]: " + mensagem + "\n");
	}

	@Override
	public void enviarMensagemErro(String mensagem, String metodo) {
		txaSaida.append("[  ERRO  ][" + metodo + "]: " + mensagem + "\n");
	}

	@Override
	public void setUsuariosCadastrados(int valor) {
		lblUsuariosCadastrados.setText(Integer.toString(valor));
	}

	@Override
	public void setUsuariosConectados(int valor) {
		lblUsuariosConectados.setText(Integer.toString(valor));
	}

	@Override
	public void setDocumentosCriados(int valor) {
		lblDocumentosCriados.setText(Integer.toString(valor));
	}

	@Override
	public void setDocumentosAbertos(int valor) {
		lblDocumentosAbertos.setText(Integer.toString(valor));
	}

	private void iniciarServidor() {
		executando = false;
		String mensagemErro = null;

		try {
			deskDocs = new DeskDocs(this);
			LocateRegistry.createRegistry(Constantes.SERVIDOR_PORTA);
			Naming.rebind(Constantes.SERVIDOR_NOME, deskDocs);
			executando = true;
		} catch (RemoteException ex) {
			mensagemErro = ex.getMessage();
			Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MalformedURLException ex) {
			mensagemErro = ex.getMessage();
			Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (executando) {
			btnIniciar.setText("Parar servidor");
			lblStatusServidor.setText("Servidor executando");
			enviarMensagemOk("Servidor iniciado com sucesso...");
		} else {
			btnIniciar.setText("Iniciar servidor");
			lblStatusServidor.setText("Servidor parado");
			enviarMensagemErro("Não foi possível iniciar o servidor!");
			if (mensagemErro != null) {
				enviarMensagemErro(mensagemErro);
			}
		}
	}

	private void pararServidor() {
		String mensagemErro = null;

		try {
			Naming.unbind(Constantes.SERVIDOR_NOME);
			deskDocs = null;
			executando = false;
		} catch (RemoteException ex) {
			Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NotBoundException ex) {
			Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MalformedURLException ex) {
			Logger.getLogger(ServidorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (!executando) {
			btnIniciar.setText("Iniciar servidor");
			lblStatusServidor.setText("Servidor parado");
			enviarMensagemOk("Servidor parado com sucesso...");
		} else {
			btnIniciar.setText("Parar servidor");
			lblStatusServidor.setText("Servidor executando");
			enviarMensagemErro("Não foi possível parar o servidor!");
			if (mensagemErro != null) {
				enviarMensagemErro(mensagemErro);
			}
		}
	}

   private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
		if (executando) {
			pararServidor();
		} else {
			iniciarServidor();
		}
   }//GEN-LAST:event_btnIniciarActionPerformed

	// <editor-fold defaultstate="collapsed" desc="Generated Code">     
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
			java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ServidorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				new ServidorGUI().setVisible(true);
			}
		});
	}// </editor-fold> 

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnIniciar;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JLabel lblAutor;
   private javax.swing.JLabel lblDocumentosAbertos;
   private javax.swing.JLabel lblDocumentosCriados;
   private javax.swing.JLabel lblStatusServidor;
   private javax.swing.JLabel lblTitulo;
   private javax.swing.JLabel lblUsuariosCadastrados;
   private javax.swing.JLabel lblUsuariosConectados;
   private javax.swing.JPanel panelTitulo;
   private javax.swing.JTextArea txaSaida;
   // End of variables declaration//GEN-END:variables
}
