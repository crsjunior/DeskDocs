package com.deskdocs.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;
	private String login;
	private String senha;
	private List<Documento> documentos;

	public Usuario() {
		documentos = new ArrayList<Documento>();
	}

	public Usuario(String nome, String login, String senha) {
		this();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public String compartilharDocumento(Documento documento) {
		if (ehDocumentoCompartilhado(documento)) {
			return "FALHA [compartilharDocumento]: Documento '" + documento.toString() + "' já é compartilhado com o Usuário '" + nome + "'.";
		}
		documentos.add(documento);
		return null;
	}

	public String descompartilharDocumento(Documento documento) {
		if (documento.getProprietario().equals(this)) {
			return "FALHA [descompartilharDocumento]: Documento '" + documento.toString() + "' é de propriedade do Usuário '" + nome + "'.";
		} else if (!ehDocumentoCompartilhado(documento)) {
			return "FALHA [descompartilharDocumento]: Documento '" + documento.toString() + "' não era compartilhado com o Usuário '" + nome + "'.";
		}
		documentos.remove(documento);
		return null;
	}

	/**
	 * Forca o descompartilhamento do documento com o usuario, mesmo que o usuario seja o seu proprietario.
	 * @param documento O documento a ser descompartilhado.
	 */
	public void descompartilharDocumentoProprietario(Documento documento) {
		if (ehDocumentoCompartilhado(documento)) {
			documentos.remove(documento);
		}
	}

	private boolean ehDocumentoCompartilhado(Documento documento) {
		for (Documento d : documentos) {
			if (d.equals(documento)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario u = (Usuario) obj;
			return (nome.equals(u.nome) && login.equals(u.login) && senha.equals(u.senha));
		}
		return false;
	}

	@Override
	public String toString() {
		return nome;
	}
}
