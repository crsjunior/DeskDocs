package com.deskdocs.servidor;

import com.deskdocs.contratos.IDeskDocs;
import com.deskdocs.contratos.IServidorGUI;
import com.deskdocs.entidades.AlteracaoDocumento;
import com.deskdocs.entidades.Documento;
import com.deskdocs.entidades.Pacote;
import com.deskdocs.entidades.Usuario;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DeskDocs extends UnicastRemoteObject implements IDeskDocs
{
	// lista de todos os usuarios cadastrados:
	private List<Usuario> usuarios;
	// lista de todos os documento criados:
	private List<Documento> documentos;
	// lista de todos os usuarios conectados:
	private List<Usuario> usuariosConectados;
	// lista de todos os documento sendo editados:
	private List<Documento> documentosAbertos;
	private IServidorGUI gui;

	public DeskDocs(IServidorGUI gui) throws RemoteException {
		usuarios = new ArrayList<Usuario>();
		documentos = new ArrayList<Documento>();
		usuariosConectados = new ArrayList<Usuario>();
		documentosAbertos = new ArrayList<Documento>();
		this.gui = gui;
	}

	private void atualizarGUI() {
		gui.setUsuariosCadastrados(usuarios.size());
		gui.setUsuariosConectados(usuariosConectados.size());
		gui.setDocumentosCriados(documentos.size());
		gui.setDocumentosAbertos(documentosAbertos.size());
	}

	@Override
	public Pacote<List<Usuario>> getTodosUsuarios() throws RemoteException {
		return new Pacote<List<Usuario>>(usuarios);
	}

	@Override
	public Pacote<List<Documento>> getTodosDocumentos() throws RemoteException {
		return new Pacote<List<Documento>>(documentos);
	}

	@Override
	public Pacote<Usuario> cadastrarUsuario(Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - nao pode ter um usuario de mesmo nome cadastrado.
		// - nao pode ter um usuario de mesmo login cadastrado.
		// - nome deve ter no minimo 2 caracteres.
		// - login deve ter no minimo 2 caracteres.
		// - senha deve ter no minimo 3 caracteres.
		String metodo = "cadastrarUusario";

		// procurando usuario de mesmo nome ou login cadastrado:
		for (Usuario u : usuarios) {
			if (u.getNome().equals(usuario.getNome())) {
				gui.enviarMensagemErro("Um Usuário de nome '" + u.getNome() + "' já existe cadastrado!", metodo);
				return new Pacote<Usuario>(null, "Um Usuário de nome '" + u.getNome() + "' já existe cadastrado!");
			}
			if (u.getLogin().equals(usuario.getLogin())) {
				gui.enviarMensagemErro("Um Usuário de login '" + u.getLogin() + "' já existe cadastrado!", metodo);
				return new Pacote<Usuario>(null, "Um Usuário de login '" + u.getLogin() + "' já existe cadastrado!");
			}
		}

		// validando tamanhos minimos dos campos:
		List<String> validandoTamMinimo = new ArrayList<String>();
		if (usuario.getNome().length() < 2) {
			validandoTamMinimo.add("Nome do Usuário deve ter no mínimo 2 caracteres!");
		}
		if (usuario.getLogin().length() < 2) {
			validandoTamMinimo.add("Login do Usuário deve ter no mínimo 2 caracteres!");
		}
		if (usuario.getSenha().length() < 3) {
			validandoTamMinimo.add("Senha do Usuário deve ter no mínimo 3 caracteres!");
		}
		if (!validandoTamMinimo.isEmpty()) {
			String mensagem = validandoTamMinimo.get(0);
			validandoTamMinimo.remove(0);
			for (String s : validandoTamMinimo) {
				mensagem += "\n" + s;
			}
			gui.enviarMensagemErro(mensagem.replaceAll("\n", " "), metodo);
			return new Pacote<Usuario>(null, mensagem);
		}

		// cadastrando usuario:
		Usuario usuarioTrabalho = new Usuario(usuario.getNome(), usuario.getLogin(), usuario.getSenha());
		usuarios.add(usuarioTrabalho);
		gui.enviarMensagemOk("Usuário '" + usuarioTrabalho.getNome() + "' foi cadastrado com sucesso!", metodo);
		atualizarGUI();
		return new Pacote<Usuario>(usuarioTrabalho);
	}

	@Override
	public Pacote<Usuario> conectarUsuario(Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - login deve ter no minimo 2 caracteres.
		// - senha deve ter no minimo 3 caracteres.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - usuario precisa nao existir na lista de usuarios conectados.
		String metodo = "conectarUsuario";

		// validando tamanhos minimos dos campos:
		if (usuario.getLogin().length() < 2) {
			gui.enviarMensagemErro("Login do Usuário deve ter no mínimo 2 caracteres!", metodo);
			return new Pacote<Usuario>(null, "Login do Usuário deve ter no mínimo 2 caracteres!");
		}
		if (usuario.getSenha().length() < 3) {
			gui.enviarMensagemErro("Senha do Usuário deve ter no mínimo 3 caracteres!", metodo);
			return new Pacote<Usuario>(null, "Senha do Usuário deve ter no mínimo 3 caracteres!");
		}

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = null;
		for (Usuario u : usuarios) {
			if (u.getLogin().equals(usuario.getLogin()) && u.getSenha().equals(usuario.getSenha())) {
				usuarioTrabalho = u;
				break;
			}
		}
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário de login '" + usuario.getLogin() + "' não foi conectado. Falha de autenticação!", metodo);
			return new Pacote<Usuario>(null, "Usuário de login '" + usuario.getLogin() + "' não foi conectado.\nSenha incorreta?\nTente novamente!");
		}

		// CHEGOU AQUI: usuario existe!

		// procurando usuario conectado:
		if (usuarioConectado(usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário de login '" + usuario.getLogin() + "' ´já está conectado.", metodo);
			return new Pacote<Usuario>(null, "Usuário de login '" + usuario.getLogin() + "' ´já está conectado.");
		}

		// CHEGOU AQUI: usuario nao esta conectado!

		// conectando usuario:
		usuariosConectados.add(usuarioTrabalho);
		gui.enviarMensagemOk("Usuário '" + usuarioTrabalho.toString() + "' se conectou com sucesso!", metodo);
		atualizarGUI();
		return new Pacote<Usuario>(usuarioTrabalho);
	}

	@Override
	public void desconectarUsuario(Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - usuario precisa existir na lista de usuarios conectados.
		String metodo = "desconectarUsuario";

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário " + usuario.toString() + "' que tentou se descontectar não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// procurando usuario conectado:
		if (!usuarioConectado(usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário " + usuario.toString() + "' não estava conectado!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario esta conectado!

		// desconectando usuario de todos os documentos:
//		List<Documento> documentosFechados = new ArrayList<Documento>();
		for (Documento d : documentosAbertos) {
			if (usuarioEditandoDocumento(d, usuarioTrabalho)) {
				d.removerUsuarioEditando(usuarioTrabalho);
				if (d.getUsuariosEditando().isEmpty()) {
//					documentosFechados.add(d);
					documentosAbertos.remove(d);
				}
			}
		}
//		for (Documento d : documentosFechados) {
//			documentosAbertos.remove(d);
//		}

		// desconectando usuario:
		usuariosConectados.remove(usuarioTrabalho);
		gui.enviarMensagemOk("Usuário " + usuarioTrabalho.toString() + "' se descontectou.", metodo);
		atualizarGUI();
	}

	@Override
	public Pacote<Documento> criarDocumento(Documento documento) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - usuario proprietario do documento precisa existir na lista de usuarios cadastrados.
		// - usuario proprietario do documento precisa existir na lista de usuarios conectados.
		// - documento de mesmo nome precisa nao existir na lista de documentos do usuario proprietario.
		// + adicionar o usuario proprietario na lista de edicao do documento.
		String metodo = "criarDocumento";

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(documento.getProprietario());
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário " + documento.getProprietario().toString() + "' que tentou o Documento '" + documento.toString() + "' não existe!", metodo);
			return new Pacote<Documento>(null, "Usuário " + documento.getProprietario().toString() + "' que tentou o Documento '" + documento.toString() + "' não existe!");
		}

		// CHEGOU AQUI: usuario proprietario do documento existe!

		// procurando usuario proprietario do documento conectado:
		if (!usuarioConectado(usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário " + usuarioTrabalho.toString() + "' que tentou o Documento '" + documento.toString() + "' não está conectado!", metodo);
			return new Pacote<Documento>(null, "Usuário " + usuarioTrabalho.toString() + "' que tentou o Documento '" + documento.toString() + "' não está conectado!");
		}

		// CHEGOU AQUI: usuario proprietario do documento esta conectado!

		// procurando documento de mesmo nome na lista de documentos do usuario proprietario:
		for (Documento d : usuarioTrabalho.getDocumentos()) {
			if (d.equals(documento)) {
				gui.enviarMensagemErro("Usuário '" + usuarioTrabalho.toString() + "' já possui um Documento de nome '" + documento.getNome() + "'!", metodo);
				return new Pacote<Documento>(null, "Usuário '" + usuarioTrabalho.toString() + "' já possui um Documento de nome '" + documento.getNome() + "'!");
			}
		}

		// CHEGOU AQUI: usuario proprietario do documento nao possui um documento de mesmo nome!

		// criando documento:
		Documento documentoTrabalho = new Documento(documento.getNome(), usuarioTrabalho);
		documentos.add(documentoTrabalho);

		// adicionando usuario proprietario na lista de edicao do documento:
		usuarioTrabalho.compartilharDocumento(documentoTrabalho);
		documentosAbertos.add(documentoTrabalho);
		documentoTrabalho.adicionarUsuarioEditando(usuarioTrabalho);
		gui.enviarMensagemOk("Usuário '" + usuarioTrabalho.toString() + "' criou o Documento de nome '" + documentoTrabalho.toString() + "'.", metodo);
		atualizarGUI();
		return new Pacote<Documento>(documentoTrabalho);
	}

	@Override
	public void excluirDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - usuario precisa existir na lista de usuarios conectados.
		// - usuario precisa ser proprietario do documento.
		// + remover todos os usuarios editando o documento.
		// - remover documento da lista de documentos abertos.
		// - necessario descompartilhar o documento de todos os usuarios.
		String metodo = "excluirDocumento";

		// procurando o documento:
		Documento documentoTrabalho = null;
		for (Documento d : documentos) {
			if (d.equals(documento)) {
				// encontrou documento na lista de documentos.
				documentoTrabalho = d;
				break;
			}
		}
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.getNome() + "' tentou excluír o documento '" + documento.getNome() + "' que não existe.", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' que tentou excluír o Documento '" + documento.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// procurando usuario conectado:
		if (!usuarioConectado(usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' que tentou excluír o Documento '" + documento.toString() + "' não está conectado!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario conectado!

		// verificando se o usuario eh o proprietario do documento:
		if (!documentoTrabalho.getProprietario().equals(usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' que tentou excluír o Documento '" + documento.toString() + "' não é o proprietário do documento!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario eh o proprietario do documento!

		// removendo todos os usuarios da lista de usuarios editando o documento:
		List<Usuario> usuariosEditando = new ArrayList<Usuario>(documentoTrabalho.getUsuariosEditando());
		for (Usuario u : usuariosEditando) {
			documentoTrabalho.removerUsuarioEditando(u);
		}

		// CHEGOU AQUI: removidos todos os usuarios da lista de usuarios editando o documento!

		// descompartilhando o documento de todos os usuarios:
		for (Usuario u : documentoTrabalho.getUsuariosCompartilhando()) {
			u.descompartilharDocumento(documentoTrabalho);
			documentoTrabalho.descompartilhar(u);
		}
		usuarioTrabalho.descompartilharDocumentoProprietario(documentoTrabalho);

		// removendo o documento:
		documentosAbertos.remove(documentoTrabalho);
		documentos.remove(documentoTrabalho);
		gui.enviarMensagemOk("Documento '" + documentoTrabalho.getNome() + "' foi excluído pelo Usuário '" + usuarioTrabalho.getNome() + "' com sucesso!", metodo);
		atualizarGUI();
	}

	@Override
	public Pacote<Documento> abrirDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - usuario precisa existir na lista de usuarios conectados.
		// - documento precisa estar sendo compartilhado com o usuario.
		String metodo = "abrirDocumento";

		// procurando o documento:
		Documento documentoTrabalho = null;
		for (Documento d : documentos) {
			if (d.equals(documento)) {
				// encontrou documento na lista de documentos.
				documentoTrabalho = d;
				break;
			}
		}
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.getNome() + "' tentou abrir o documento '" + documento.getNome() + "' que não foi encontrado.", metodo);
			return new Pacote<Documento>(null, "Usuário '" + usuario.getNome() + "' tentou abrir o documento '" + documento.getNome() + "' que não foi encontrado.");
		}

		// CHEGOU AQUI: documento existe!

		// verificando se o documento eh compartilhado com o usuario:
		for (Usuario u : documentoTrabalho.getUsuariosCompartilhando()) {
			if (u.equals(usuario)) {
				// usuario esta compartilhando o documento, e pode abri-lo.
				if (!documentosAbertos.contains(documentoTrabalho)) {
					documentosAbertos.add(documentoTrabalho);
				}
				documentoTrabalho.adicionarUsuarioEditando(u);
				gui.enviarMensagemOk("Usuário '" + usuario.getNome() + "' abriu o documento '" + documento.getNome() + "'.", metodo);
				atualizarGUI();
				return new Pacote<Documento>(documentoTrabalho);
			}
		}

		gui.enviarMensagemErro("Doucmento '" + documentoTrabalho.getNome() + "' não é compartilhado com o Usuário '" + usuario.getNome() + "'.", metodo);
		return new Pacote<Documento>(null, "Doucmento '" + documentoTrabalho.getNome() + "' não é compartilhado com o Usuário '" + usuario.getNome() + "'.");
	}

	@Override
	public void fecharDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "fecharDocumento";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' que o Usuário '" + usuario.toString() + "' tentou fechar não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' que tentou fechar o Documento '" + documento.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// fechando documento para o usuario:
		documentoTrabalho.removerUsuarioEditando(usuarioTrabalho);
		gui.enviarMensagemOk("Documento '" + documento.toString() + "' foi fechado pelo Usuário '" + usuario.toString() + "'!", metodo);

		// verificando se ainda existe algum usuario editando o documento:
		if (documentoTrabalho.getUsuariosEditando().isEmpty()) {
			documentosAbertos.remove(documentoTrabalho);
		}
		atualizarGUI();
	}

	@Override
	public void compartilharDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa não estar sendo compartilhado com o usuario.
		String metodo = "compartilharDocumento";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' que o Usuário '" + documento.getProprietario().toString() + "' tentou compartilhar com o Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + documento.getProprietario().toString() + "' tentou compartilhar o Documento '" + documento.toString() + "' com o Usuário '" + usuario.toString() + "', que não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento nao esta sendo compartilhado com o usuario:
		if (usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário '" + documento.getProprietario().toString() + "' tentou compartilhar o Documento '" + documento.toString() + "' que já está sendo compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento nao eh compartilhado com o usuario!

		// compartilhando o documento com o usuario:
		documentoTrabalho.compartilhar(usuarioTrabalho);
		usuarioTrabalho.compartilharDocumento(documentoTrabalho);
		gui.enviarMensagemOk("Usuário '" + documento.getProprietario().toString() + "' compartilhou o Documento '" + documento.toString() + "' com o Usuário '" + usuario.toString() + "'!", metodo);
		atualizarGUI();
	}

	@Override
	public void descompartilharDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		String metodo = "descompartilharDocumento";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' que o Usuário '" + documento.getProprietario().toString() + "' tentou descompartilhar com o Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + documento.getProprietario().toString() + "' tentou descompartilhar o Documento '" + documento.toString() + "' com o Usuário '" + usuario.toString() + "', que não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Usuário '" + documento.getProprietario().toString() + "' tentou descompartilhar o Documento '" + documento.toString() + "' que já não era compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento esta sendo compartilhado com o usuario!

		// descompartilhando o documento com o usuario:
		documentoTrabalho.descompartilhar(usuarioTrabalho);
		usuarioTrabalho.descompartilharDocumento(documentoTrabalho);
		gui.enviarMensagemOk("Usuário '" + documento.getProprietario().toString() + "' descompartilhou o Documento '" + documento.toString() + "' com o Usuário '" + usuario.toString() + "'!", metodo);
		atualizarGUI();
	}

	@Override
	public Pacote<Documento> atualizarDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "atualizarDocumento";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<Documento>(null, "Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<Documento>(null, "Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<Documento>(null, "Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<Documento>(null, "Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// retornando alteracoes do documento referentes ao usuario:
		atualizarGUI();
		return new Pacote<Documento>(documentoTrabalho);
	}

	@Override
	public Pacote<List<Usuario>> getUsuariosEditandoDocumento(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "getUsuariosEditandoDocumento";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não existe!", metodo);
			return new Pacote<List<Usuario>>(null, "Documento '" + documento.toString() + "' não existe!");
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<List<Usuario>>(null, "Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<List<Usuario>>(null, "Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<List<Usuario>>(null, "Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// retornando lista de usuarios editando documento:
		atualizarGUI();
		return new Pacote<List<Usuario>>(documentoTrabalho.getUsuariosEditando());
	}

	@Override
	public Pacote<List<Usuario>> getUsuariosCompartilhando(Documento documento) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		String metodo = "getUsuariosCompartilhando";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' de propriedade do Usuário '" + documento.getProprietario().toString() + "' não existe!", metodo);
			return new Pacote<List<Usuario>>(null, "Documento '" + documento.toString() + "' de propriedade do Usuário '" + documento.getProprietario().toString() + "' não existe!");
		}

		// CHEGOU AQUI: documento existe!

		return new Pacote<List<Usuario>>(documentoTrabalho.getUsuariosCompartilhando());
	}

	@Override
	public Pacote<List<Documento>> getDocumentosCompartilhados(Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - usuario precisa existir na lista de usuarios cadastrados.
		String metodo = "getDocumentosCompartilhados";

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<List<Documento>>(null, "Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: usuario existe!

		return new Pacote<List<Documento>>(usuarioTrabalho.getDocumentos());
	}

	@Override
	public Pacote<List<AlteracaoDocumento>> getAlteracoes(Documento documento, Usuario usuario) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "getAlteracoes";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<List<AlteracaoDocumento>>(null, "Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return new Pacote<List<AlteracaoDocumento>>(null, "Usuário '" + usuario.toString() + "' não existe!");
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<List<AlteracaoDocumento>>(null, "Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return new Pacote<List<AlteracaoDocumento>>(null, "Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!");
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// retornando alteracoes do documento referentes ao usuario:
		return new Pacote<List<AlteracaoDocumento>>(documentoTrabalho.getAlteracoes(usuarioTrabalho));
	}

	@Override
	public void inserirTexto(Usuario usuario, Documento documento, String texto, int offset) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "inserirTexto";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// inserindo alteracao do documento feita pelo usuario:
		AlteracaoDocumento ad = new AlteracaoDocumento(usuarioTrabalho, texto, offset);
		documentoTrabalho.adicionarAlteracao(ad);
	}

	@Override
	public void removerTexto(Usuario usuario, Documento documento, int offset, int length) throws RemoteException {
		// REGRAS DE NEGOCIO:
		// - documento precisa existir na lista de documentos.
		// - usuario precisa existir na lista de usuarios cadastrados.
		// - documento precisa estar sendo compartilhado com o usuario.
		// - documento precisa estar sendo editado pelo usuario.
		String metodo = "removerTexto";

		// procurando o documento:
		Documento documentoTrabalho = documentoExiste(documento);
		if (documentoTrabalho == null) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' solicitado pelo Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: documento existe!

		// procurando usuario cadastrado:
		Usuario usuarioTrabalho = usuarioCadastrado(usuario);
		if (usuarioTrabalho == null) {
			gui.enviarMensagemErro("Usuário '" + usuario.toString() + "' não existe!", metodo);
			return;
		}

		// CHEGOU AQUI: usuario existe!

		// verificando se documento esta sendo compartilhado com o usuario:
		if (!usuarioCompartilhandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não é compartilhado com o Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento eh compartilhado com o usuario!

		// verificando se o usuario esta editando o documento:
		if (!usuarioEditandoDocumento(documentoTrabalho, usuarioTrabalho)) {
			gui.enviarMensagemErro("Documento '" + documento.toString() + "' não está sendo editado pelo Usuário '" + usuario.toString() + "'!", metodo);
			return;
		}

		// CHEGOU AQUI: documento esta sendo editado pelo usuario!

		// inserindo alteracao do documento feita pelo usuario:
		AlteracaoDocumento ad = new AlteracaoDocumento(usuarioTrabalho, offset, length);
		documentoTrabalho.adicionarAlteracao(ad);
	}

	/**
	 * Verifica se um usuario existe na lista de usuarios cadastrados.
	 * @param usuario O usuario a ser procurado.
	 * @return Se o usuario existe, retorna o usuario, caso contrario, retorna null.
	 */
	private Usuario usuarioCadastrado(Usuario usuario) {
		for (Usuario u : usuarios) {
			if (u.equals(usuario)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * Verifica se um usuario esta conectado.
	 * @param usuario O usuario a ser verificado.
	 * @return True se o usuario esta conectado, caso contrario, false.
	 */
	private boolean usuarioConectado(Usuario usuario) {
		for (Usuario u : usuariosConectados) {
			if (u.equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se um documento existe na lista de documentos.
	 * @param documento O documeno a ser procurado.
	 * @return Se o documento existe, retorna o documento, caso contrario, retorna null.
	 */
	private Documento documentoExiste(Documento documento) {
		for (Documento d : documentos) {
			if (d.equals(documento)) {
				return d;
			}
		}
		return null;
	}

	/**
	 * Verifica se um documento esta sendo compartilhado com um usuario.
	 * @param documento O documento a ser verificado.
	 * @param usuario O usuario a ser verificado.
	 * @return True se o documento estiver sendo compartilhado com o usuario, caso contrario, false.
	 */
	private boolean usuarioCompartilhandoDocumento(Documento documento, Usuario usuario) {
		for (Usuario u : documento.getUsuariosCompartilhando()) {
			if (u.equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se um usuario esta editando um documento.
	 * @param documento O documento a ser verificado.
	 * @param usuario O usuario a ser verificado.
	 * @return True se o usuario estiver editando o documento, caso contrario, false.
	 */
	private boolean usuarioEditandoDocumento(Documento documento, Usuario usuario) {
		for (Usuario u : documento.getUsuariosEditando()) {
			if (u.equals(usuario)) {
				return true;
			}
		}
		return false;
	}
}
