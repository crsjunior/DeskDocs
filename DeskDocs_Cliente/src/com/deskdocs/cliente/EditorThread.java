package com.deskdocs.cliente;

import com.deskdocs.contratos.IEditorGUI;
import com.deskdocs.entidades.AlteracaoDocumento;
import com.deskdocs.entidades.Pacote;
import com.deskdocs.entidades.Usuario;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorThread implements Runnable
{
	private IEditorGUI gui;
	private Cliente cliente = null;
	private boolean executando = false;

	public EditorThread(IEditorGUI gui) {
		this.gui = gui;
	}

	@Override
	public void run() {
		cliente = Cliente.getInstance();
		Pacote<List<AlteracaoDocumento>> pAlt = null;
		Pacote<List<Usuario>> pUsuariosEditando = null;
//		List<AlteracaoDocumento> alt = null;
//		List<Usuario> usuariosEditando = null;

		while (executando) {
			try {
				// obtendo alteracoes no documento referentes ao usuario:
				pAlt = cliente.getDeskDocs().getAlteracoes(gui.getDocumento(), cliente.getUsuario());
//				alt = cliente.getDeskDocs().getAlteracoes(gui.getDocumento(), cliente.getUsuario());
				pUsuariosEditando = cliente.getDeskDocs().getUsuariosEditandoDocumento(gui.getDocumento(), cliente.getUsuario());
//				usuariosEditando = cliente.getDeskDocs().getUsuariosEditandoDocumento(gui.getDocumento(), cliente.getUsuario());
			} catch (RemoteException ex) {
				Logger.getLogger(EditorThread.class.getName()).log(Level.SEVERE, null, ex);
			}

			// aplicando alteracoes no documento:
			if (pAlt.getObjeto() != null) {
				gui.aplicarAlteracoes(pAlt.getObjeto());
			} else {
				gui.documentoExcluido();
			}
			
//			if (alt != null) {
//				gui.aplicarAlteracoes(alt);
//			} else {
//				gui.documentoExcluido();
//			}

			if (pUsuariosEditando.getObjeto() != null) {
				gui.atualizarUsuariosEditando(pUsuariosEditando.getObjeto());
			}
			
//			if (usuariosEditando != null) {
//				gui.atualizarUsuariosEditando(usuariosEditando);
//			}

			pAlt = null;
//			alt = null;

			try {
				// dorme primeiro:
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(EditorThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public boolean isExecutando() {
		return executando;
	}

	public void iniciar() {
		// somente eh permitido uma thread em execucao!
		// verificando se ela ja esta em execucao:
		if (!executando) {
			executando = true;
			new Thread(this).start();
		}
	}

	public void parar() {
		executando = false;
	}
}
