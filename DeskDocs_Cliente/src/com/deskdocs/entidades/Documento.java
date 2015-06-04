package com.deskdocs.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class Documento implements Serializable
{
	private static final long serialVersionUID = 2L;

	private String nome;
	private Document document;
	private Usuario proprietario;
	private List<Usuario> usuariosCompartilhando;
	private List<Usuario> usuariosEditando;
	private List<AlteracaoDocumento> alteracoes;

	public Documento() {
		document = new PlainDocument();
		usuariosCompartilhando = new ArrayList<Usuario>();
		usuariosEditando = new ArrayList<Usuario>();
		alteracoes = new ArrayList<AlteracaoDocumento>();
	}

	public Documento(String nome, Usuario proprietario) {
		this();
		this.nome = nome;
		this.proprietario = proprietario;
		usuariosCompartilhando.add(this.proprietario);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public List<Usuario> getUsuariosCompartilhando() {
		return usuariosCompartilhando;
	}

	public List<Usuario> getUsuariosEditando() {
		return usuariosEditando;
	}

	public List<AlteracaoDocumento> getAlteracoes(Usuario usuario) {
		List<AlteracaoDocumento> lista = new ArrayList<AlteracaoDocumento>();
		for (AlteracaoDocumento ad : alteracoes) {
			if (ad.ehUsuarioPendente(usuario)) {
				ad.removerUsuarioPendente(usuario);
				lista.add(ad);
//				if (!ad.temUsuariosPendentes()) {
//					alteracoes.remove(ad);
//				}
			}
		}
		for (AlteracaoDocumento ad : lista) {
			if (!ad.temUsuariosPendentes()) {
				alteracoes.remove(ad);
			}
		}
		return lista;
	}

	public void setAlteracoes(List<AlteracaoDocumento> alteracoes) {
		this.alteracoes = alteracoes;
	}

	public String compartilhar(Usuario usuario) {
		if (ehUsuarioCompartilhando(usuario)) {
			return "FALHA [compartilhar]: Documento '" + nome + "' já está compartilhado com o Usuário '" + usuario.toString() + "'.";
		}
		String resposta = usuario.compartilharDocumento(this);
		if (resposta != null) {
			return resposta;
		}
		usuariosCompartilhando.add(usuario);
		return null;
	}

	public String descompartilhar(Usuario usuario) {
		if (usuario.equals(proprietario)) {
			return "FALHA [descompartilhar]: Documento '" + nome + "' é de propriedade do Usuário '" + usuario.toString() + "'.";
		} else if (!ehUsuarioCompartilhando(usuario)) {
			return "FALHA [descompartilhar]: Documento '" + nome + "' já não era compartilhado com o Usuário '" + usuario.toString() + "'.";
		}
		String resposta = usuario.descompartilharDocumento(this);
		if (resposta != null) {
			return resposta;
		}
		usuariosCompartilhando.remove(usuario);
		return null;
	}

	public String adicionarUsuarioEditando(Usuario usuario) {
		if (!ehUsuarioCompartilhando(usuario)) {
			return "FALHA [adicionarUsuarioEditando]: Documento '" + nome + "' não é compartilhado com o Usuário '" + usuario.toString() + "'.";
		} else if (usuariosEditando.contains(usuario)) {
			return "FALHA [adicionarUsuarioEditando]: Documento '" + nome + "' já está sendo editado pelo Usuário '" + usuario.toString() + "'.";
		}
		usuariosEditando.add(usuario);
		return null;
	}

	public String removerUsuarioEditando(Usuario usuario) {
		if (!ehUsuarioCompartilhando(usuario)) {
			return "FALHA [removerUsuarioEditando]: Documento '" + nome + "' não é compartilhado com o Usuário '" + usuario.toString() + "'.";
		} else if (!usuariosEditando.contains(usuario)) {
			return "FALHA [removerUsuarioEditando]: Documento '" + nome + "' já não estava sendo editado pelo Usuário '" + usuario.toString() + "'.";
		}
		usuariosEditando.remove(usuario);
		return null;
	}

	public void adicionarAlteracao(AlteracaoDocumento alteracao) {
		if (alteracao.getTipo() == AlteracaoDocumento.INSERCAO) {
			try {
				document.insertString(alteracao.getOffset(), alteracao.getTexto(), null);
			} catch (BadLocationException ex) {
				Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			try {
				document.remove(alteracao.getOffset(), alteracao.getLength());
			} catch (BadLocationException ex) {
				Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (Usuario u : usuariosCompartilhando) {
			if (!u.equals(alteracao.getProprietario())) {
				alteracao.adicionarUsuarioPendente(u);
			}
		}
		if (alteracao.temUsuariosPendentes()) {
			alteracoes.add(alteracao);
		}
	}

	private boolean ehUsuarioCompartilhando(Usuario usuario) {
		for (Usuario u : usuariosCompartilhando) {
			if (u.equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Documento) {
			Documento d = (Documento) obj;
			return (nome.equals(d.nome) && proprietario.equals(d.proprietario));
		}
		return false;
	}

	@Override
	public String toString() {
		return nome;
	}
}
