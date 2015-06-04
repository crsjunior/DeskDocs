package com.deskdocs.view;

import com.deskdocs.cliente.Cliente;
import com.deskdocs.cliente.EditorThread;
import com.deskdocs.contratos.IEditorGUI;
import com.deskdocs.entidades.AlteracaoDocumento;
import com.deskdocs.entidades.Documento;
import com.deskdocs.entidades.Pacote;
import com.deskdocs.entidades.Usuario;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Carlos R. Schwarz <schwarz.junior@gmail.com>
 */
public class EditorGUI extends javax.swing.JFrame implements IEditorGUI
{
	private Cliente cliente;
	private Documento documento;
	private EditorThread editorThread;
	private boolean modoAtualizacao = false;

	public EditorGUI() {
		initComponents();

		cliente = Cliente.getInstance();
		editorThread = new EditorThread(this);
		inicializarDocumentListener();
		setTitle("DeskDocs - Bem-Vindo, " + cliente.getUsuario().getNome());
		lblNomeUsuario.setText("Bem-Vindo, " + cliente.getUsuario().getNome());
		atualizarControlesGUI();
	}

	/**
	 * Inicializa o DocumentListener para o JTextArea (txaDocumento), que ira capturar
	 * as alteracoes efetuadas pelo usuario.
	 */
	private void inicializarDocumentListener() {
		txaDocumento.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (modoAtualizacao) {
					return;
				}
				try {
					String texto = txaDocumento.getDocument().getText(e.getOffset(), e.getLength());
					cliente.getDeskDocs().inserirTexto(cliente.getUsuario(), documento, texto, e.getOffset());
				} catch (BadLocationException ex) {
					Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
				} catch (RemoteException ex) {
					Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (modoAtualizacao) {
					return;
				}
				try {
					cliente.getDeskDocs().removerTexto(cliente.getUsuario(), documento, e.getOffset(), e.getLength());
				} catch (RemoteException ex) {
					Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      panelDocumentoInfo = new javax.swing.JPanel();
      lblLabelDocumentoNome = new javax.swing.JLabel();
      lblDocumentoNome = new javax.swing.JLabel();
      lblLabelUsuariosEditandoDocumento = new javax.swing.JLabel();
      lblUsuariosEditandoDocumento = new javax.swing.JLabel();
      btnCompartilhamento = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      txaDocumento = new javax.swing.JTextArea();
      lblNomeUsuario = new javax.swing.JLabel();
      jMenuBar1 = new javax.swing.JMenuBar();
      mnuArquivo = new javax.swing.JMenu();
      mniNovoDocumento = new javax.swing.JMenuItem();
      mniAbrirDocumento = new javax.swing.JMenuItem();
      mniFecharDocumento = new javax.swing.JMenuItem();
      jSeparator1 = new javax.swing.JPopupMenu.Separator();
      mniExcluirDocumento = new javax.swing.JMenuItem();
      jSeparator2 = new javax.swing.JPopupMenu.Separator();
      mniSair = new javax.swing.JMenuItem();
      mnuSobre = new javax.swing.JMenu();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DeskDocs - Bem-Vindo");
      addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
            formWindowClosing(evt);
         }
      });

      lblLabelDocumentoNome.setText("Documento:");

      lblDocumentoNome.setText("Nenhum");

      lblLabelUsuariosEditandoDocumento.setText("Usuários editando:");

      lblUsuariosEditandoDocumento.setText("Nenhum");

      btnCompartilhamento.setText("Compartilhamento");
      btnCompartilhamento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCompartilhamentoActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout panelDocumentoInfoLayout = new javax.swing.GroupLayout(panelDocumentoInfo);
      panelDocumentoInfo.setLayout(panelDocumentoInfoLayout);
      panelDocumentoInfoLayout.setHorizontalGroup(
         panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(panelDocumentoInfoLayout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addGroup(panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(lblLabelUsuariosEditandoDocumento)
               .addComponent(lblLabelDocumentoNome))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(lblUsuariosEditandoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
               .addGroup(panelDocumentoInfoLayout.createSequentialGroup()
                  .addComponent(lblDocumentoNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnCompartilhamento))))
      );
      panelDocumentoInfoLayout.setVerticalGroup(
         panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(panelDocumentoInfoLayout.createSequentialGroup()
            .addGroup(panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelDocumentoNome)
               .addComponent(lblDocumentoNome)
               .addComponent(btnCompartilhamento))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelDocumentoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblLabelUsuariosEditandoDocumento)
               .addComponent(lblUsuariosEditandoDocumento)))
      );

      txaDocumento.setColumns(20);
      txaDocumento.setRows(5);
      jScrollPane1.setViewportView(txaDocumento);

      lblNomeUsuario.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
      lblNomeUsuario.setForeground(new java.awt.Color(102, 102, 102));
      lblNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      lblNomeUsuario.setText("Nenhum");
      lblNomeUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20));

      mnuArquivo.setText("Arquivo");

      mniNovoDocumento.setText("Novo documento");
      mniNovoDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniNovoDocumentoActionPerformed(evt);
         }
      });
      mnuArquivo.add(mniNovoDocumento);

      mniAbrirDocumento.setText("Abrir documento");
      mniAbrirDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniAbrirDocumentoActionPerformed(evt);
         }
      });
      mnuArquivo.add(mniAbrirDocumento);

      mniFecharDocumento.setText("Fechar documento");
      mniFecharDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniFecharDocumentoActionPerformed(evt);
         }
      });
      mnuArquivo.add(mniFecharDocumento);
      mnuArquivo.add(jSeparator1);

      mniExcluirDocumento.setText("Excluir documento");
      mniExcluirDocumento.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniExcluirDocumentoActionPerformed(evt);
         }
      });
      mnuArquivo.add(mniExcluirDocumento);
      mnuArquivo.add(jSeparator2);

      mniSair.setText("Sair");
      mniSair.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            mniSairActionPerformed(evt);
         }
      });
      mnuArquivo.add(mniSair);

      jMenuBar1.add(mnuArquivo);

      mnuSobre.setText("Sobre");
      mnuSobre.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            mnuSobreMouseClicked(evt);
         }
      });
      jMenuBar1.add(mnuSobre);

      setJMenuBar(jMenuBar1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
               .addComponent(lblNomeUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(panelDocumentoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNomeUsuario)
            .addGap(10, 10, 10)
            .addComponent(panelDocumentoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	@Override
	public Documento getDocumento() {
		return documento;
	}

	@Override
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public void atualizarUsuariosEditando(List<Usuario> usuarios) {
		if (usuarios == null) {
			lblUsuariosEditandoDocumento.setText("");
		} else {
			StringBuilder sb = new StringBuilder();
			for (Usuario u : usuarios) {
				sb.append(u.getNome()).append(", ");
			}
			String mens = sb.toString();
			mens = mens.substring(0, mens.length() - 2);
			lblUsuariosEditandoDocumento.setText(mens);
		}
	}

	@Override
	public void aplicarAlteracoes(List<AlteracaoDocumento> alteracoes) {
		// setando a flag para o modo de atualizacao, impedindo que o documento envie as
		// alteracoes para o servidor:
		modoAtualizacao = true;
		for (AlteracaoDocumento ad : alteracoes) {
			try {
				if (ad.getTipo() == AlteracaoDocumento.INSERCAO) {
					txaDocumento.getDocument().insertString(ad.getOffset(), ad.getTexto(), null);
					if (txaDocumento.getCaretPosition() > ad.getOffset()) {
						txaDocumento.setCaretPosition(txaDocumento.getCaretPosition() + ad.getTexto().length());
					}
				} else {
					int cp = txaDocumento.getCaretPosition();
					int ln = txaDocumento.getText().length();
					boolean b = (cp != ln) && (ln - cp <= ad.getLength());
					txaDocumento.getDocument().remove(ad.getOffset(), ad.getLength());
					if (cp > ad.getOffset() && b) {
						txaDocumento.setCaretPosition(txaDocumento.getCaretPosition() - 1);
					}
				}
			} catch (BadLocationException ex) {
				Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		modoAtualizacao = false;
	}

	@Override
	public void documentoExcluido() {
		txaDocumento.setText(null);
		documento = null;
		atualizarControlesGUI();
		JOptionPane.showMessageDialog(this, "Este documento não é mais compartilhado com você.\nOu foi excluído pelo seu proprietário.", "DeskDocs - Alerta!", JOptionPane.ERROR_MESSAGE);
	}

	private void atualizarControlesGUI() {
		// verificando se existe um documento sendo editado:
		if (documento == null) {
			editorThread.parar();
			lblDocumentoNome.setText("");
			lblUsuariosEditandoDocumento.setText("");
			mniNovoDocumento.setEnabled(true);
			mniAbrirDocumento.setEnabled(true);
			mniFecharDocumento.setEnabled(false);
			mniExcluirDocumento.setEnabled(false);
			btnCompartilhamento.setEnabled(false);
			mniSair.setEnabled(true);
			txaDocumento.setEnabled(false);
		} else {
			lblDocumentoNome.setText(documento.getNome());
			mniNovoDocumento.setEnabled(false);
			mniAbrirDocumento.setEnabled(false);
			mniFecharDocumento.setEnabled(true);
			mniExcluirDocumento.setEnabled(cliente.getUsuario().equals(documento.getProprietario()));
			btnCompartilhamento.setEnabled(cliente.getUsuario().equals(documento.getProprietario()));
			mniSair.setEnabled(false);
			txaDocumento.setEnabled(true);
			// inicinado a thread:
			editorThread.iniciar();
		}
	}

//	private String getUsuariosEditandoDocumento() {
//		List<Usuario> usuariosEditando = null;
//		try {
//			cliente.getDeskDocs().getUsuariosEditandoDocumento(documento, cliente.getUsuario());
//		} catch (RemoteException ex) {
//			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//		if (usuariosEditando != null) {
//			StringBuilder sb = new StringBuilder();
//			for (Usuario u : usuariosEditando) {
//				sb.append(u.getNome()).append(", ");
//			}
//			return sb.toString();
//		}
//		return "";
//	}
	private void preencherComDocumentoAberto() {
		modoAtualizacao = true;
		try {
			txaDocumento.setText(documento.getDocument().getText(0, documento.getDocument().getLength()));
		} catch (BadLocationException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		modoAtualizacao = false;
	}

	private void encerrarSair() {
		// desconectando o usuario do servidor:
		try {
			// verificando se existe documento sendo editado:
			if (documento != null) {
				cliente.getDeskDocs().fecharDocumento(documento, cliente.getUsuario());
				txaDocumento.setText(null);
				documento = null;
				atualizarControlesGUI();
			}
			cliente.getDeskDocs().desconectarUsuario(cliente.getUsuario());
		} catch (RemoteException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

   private void mniNovoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNovoDocumentoActionPerformed
		NovoDocumentoGUI novoDocumentoGUI = new NovoDocumentoGUI(this, true);
		String nomeDocumento = novoDocumentoGUI.showDialog();

		if (nomeDocumento != null && !nomeDocumento.isEmpty()) {
			Documento documentoTrabalho = new Documento(nomeDocumento, cliente.getUsuario());
			Pacote<Documento> pDocumento = null;
			try {
				pDocumento = cliente.getDeskDocs().criarDocumento(documentoTrabalho);
				if (pDocumento != null && pDocumento.isSucesso()) {
					documento = pDocumento.getObjeto();
					cliente.getUsuario().compartilharDocumento(documento);
				}

//				documento = cliente.getDeskDocs().criarDocumento(documentoTrabalho);
//				cliente.getUsuario().compartilharDocumento(documento);
				preencherComDocumentoAberto();
				atualizarControlesGUI();
			} catch (RemoteException ex) {
				Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
   }//GEN-LAST:event_mniNovoDocumentoActionPerformed

   private void mniAbrirDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAbrirDocumentoActionPerformed
//		List<Documento> lista = null;
		Pacote<List<Documento>> pLista = null;
		try {
			pLista = cliente.getDeskDocs().getDocumentosCompartilhados(cliente.getUsuario());
//			lista = cliente.getDeskDocs().getDocumentosCompartilhados(cliente.getUsuario());
		} catch (RemoteException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (pLista != null && !pLista.isSucesso()) {
			return;
		}

//		if (lista == null) {
//			return;
//		}

//		AbrirDocumentoGUI abrirDocumentoGUI = new AbrirDocumentoGUI(this, true, lista);
		AbrirDocumentoGUI abrirDocumentoGUI = new AbrirDocumentoGUI(this, true, pLista.getObjeto());
		int indice = abrirDocumentoGUI.showDialog();

		if (indice == -1) {
			return;
		}

		Pacote<Documento> pDocumento = null;
		try {
			pDocumento = cliente.getDeskDocs().abrirDocumento(pLista.getObjeto().get(indice), cliente.getUsuario());
//			documento = cliente.getDeskDocs().abrirDocumento(lista.get(indice), cliente.getUsuario());
			if (pDocumento != null && pDocumento.isSucesso()) {
				documento = pDocumento.getObjeto();
				preencherComDocumentoAberto();
				atualizarControlesGUI();
			}
		} catch (RemoteException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
   }//GEN-LAST:event_mniAbrirDocumentoActionPerformed

   private void mniFecharDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFecharDocumentoActionPerformed
		try {
			cliente.getDeskDocs().fecharDocumento(documento, cliente.getUsuario());
			txaDocumento.setText(null);
			documento = null;
			atualizarControlesGUI();
		} catch (RemoteException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
   }//GEN-LAST:event_mniFecharDocumentoActionPerformed

   private void mniExcluirDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExcluirDocumentoActionPerformed
		int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluír o documento atual?", "DeskDocs - Excluír documento", JOptionPane.OK_CANCEL_OPTION);
		if (opcao == JOptionPane.OK_OPTION) {
			try {
				cliente.getDeskDocs().fecharDocumento(documento, cliente.getUsuario());
				txaDocumento.setText(null);
				cliente.getDeskDocs().excluirDocumento(documento, cliente.getUsuario());
				documento = null;
				atualizarControlesGUI();
			} catch (RemoteException ex) {
				Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
   }//GEN-LAST:event_mniExcluirDocumentoActionPerformed

   private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
		int opcao = JOptionPane.showConfirmDialog(this, "Deseja encerrar a sessão e sair?", "DeskDocs", JOptionPane.OK_CANCEL_OPTION);
		if (opcao == JOptionPane.OK_OPTION) {
			encerrarSair();
			dispose();
		}
   }//GEN-LAST:event_mniSairActionPerformed

   private void mnuSobreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSobreMouseClicked

   }//GEN-LAST:event_mnuSobreMouseClicked

   private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		encerrarSair();
   }//GEN-LAST:event_formWindowClosing

   private void btnCompartilhamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompartilhamentoActionPerformed
		// lista de usuarios nao compartilhados com o documento:
		List<Usuario> naoCompartilhadosOriginal = null;
		List<Usuario> naoCompartilhadosTrabalho = null;

		Pacote<List<Usuario>> pNaoCompartilhadosOriginal = null;
		Pacote<List<Usuario>> pCompartilhadosOriginal = null;

		// lista de usuarios compartilhados com o documento:
		List<Usuario> compartilhadosOriginal = null;
		List<Usuario> compartilhadosTrabalho = null;

		try {
			pNaoCompartilhadosOriginal = cliente.getDeskDocs().getTodosUsuarios();
			pCompartilhadosOriginal = cliente.getDeskDocs().getUsuariosCompartilhando(documento);
//			naoCompartilhadosOriginal = cliente.getDeskDocs().getTodosUsuarios();
//			compartilhadosOriginal = cliente.getDeskDocs().getUsuariosCompartilhando(documento);
		} catch (RemoteException ex) {
			Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (pNaoCompartilhadosOriginal != null && pCompartilhadosOriginal != null && pNaoCompartilhadosOriginal.isSucesso() && pCompartilhadosOriginal.isSucesso()) {
			naoCompartilhadosTrabalho = new ArrayList<Usuario>(pNaoCompartilhadosOriginal.getObjeto());
			compartilhadosTrabalho = new ArrayList<Usuario>(pCompartilhadosOriginal.getObjeto());

			// removendo o usuario proprietario do documento de ambas as listas:
			naoCompartilhadosTrabalho.remove(documento.getProprietario());
			compartilhadosTrabalho.remove(documento.getProprietario());

			// removendo usuarios compartilhando documento da lista de usuarios nao compartilhando:
			for (Usuario u : compartilhadosTrabalho) {
				naoCompartilhadosTrabalho.remove(u);
			}

			CompartilharDocumentoGUI compGUI = new CompartilharDocumentoGUI(this, true, documento.getNome(), naoCompartilhadosTrabalho, compartilhadosTrabalho);
			boolean resultado = compGUI.showDialog();

			if (resultado) {
				// compartilhando:
				for (Usuario u : compartilhadosTrabalho) {
					if (pNaoCompartilhadosOriginal.getObjeto().contains(u) && !u.equals(documento.getProprietario())) {
						try {
							cliente.getDeskDocs().compartilharDocumento(documento, u);
							documento.compartilhar(u);
						} catch (RemoteException ex) {
							Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}

				// descompartilhando:
				for (Usuario u : naoCompartilhadosTrabalho) {
					if (pCompartilhadosOriginal.getObjeto().contains(u) && !u.equals(documento.getProprietario())) {
						try {
							cliente.getDeskDocs().descompartilharDocumento(documento, u);
							documento.descompartilhar(u);
						} catch (RemoteException ex) {
							Logger.getLogger(EditorGUI.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		}
   }//GEN-LAST:event_btnCompartilhamentoActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCompartilhamento;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JPopupMenu.Separator jSeparator1;
   private javax.swing.JPopupMenu.Separator jSeparator2;
   private javax.swing.JLabel lblDocumentoNome;
   private javax.swing.JLabel lblLabelDocumentoNome;
   private javax.swing.JLabel lblLabelUsuariosEditandoDocumento;
   private javax.swing.JLabel lblNomeUsuario;
   private javax.swing.JLabel lblUsuariosEditandoDocumento;
   private javax.swing.JMenuItem mniAbrirDocumento;
   private javax.swing.JMenuItem mniExcluirDocumento;
   private javax.swing.JMenuItem mniFecharDocumento;
   private javax.swing.JMenuItem mniNovoDocumento;
   private javax.swing.JMenuItem mniSair;
   private javax.swing.JMenu mnuArquivo;
   private javax.swing.JMenu mnuSobre;
   private javax.swing.JPanel panelDocumentoInfo;
   private javax.swing.JTextArea txaDocumento;
   // End of variables declaration//GEN-END:variables
}
