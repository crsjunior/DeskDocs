package com.deskdocs.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlteracaoDocumento implements Serializable
{
	private static final long serialVersionUID = 3L;

	public static final int INSERCAO = 1;
	public static final int REMOCAO = 2;

	private Usuario proprietario;
	private int tipo;
	private String texto;
	private int offset;
	private int length;
	private List<Usuario> usuariosPendentes;

	public AlteracaoDocumento(Usuario proprietario, int tipo, String texto, int offset, int length) {
		this.proprietario = proprietario;
		this.tipo = tipo;
		this.texto = texto;
		this.offset = offset;
		this.length = length;
		this.usuariosPendentes = new ArrayList<Usuario>();
	}

	/**
	 * Constroi em modo de insercao.
	 */
	public AlteracaoDocumento(Usuario proprietario, String texto, int offset) {
		this(proprietario, AlteracaoDocumento.INSERCAO, texto, offset, 0);
	}

	/**
	 * Constroi em modo de remocao.
	 */
	public AlteracaoDocumento(Usuario proprietario, int offset, int length) {
		this(proprietario, AlteracaoDocumento.REMOCAO, null, offset, length);
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public int getTipo() {
		return tipo;
	}

	public String getTexto() {
		return texto;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public List<Usuario> getUsuariosPendentes() {
		return usuariosPendentes;
	}

	public void adicionarUsuarioPendente(Usuario usuario) {
		usuariosPendentes.add(usuario);
	}

	public void removerUsuarioPendente(Usuario usuario) {
		usuariosPendentes.remove(usuario);
	}

	public boolean temUsuariosPendentes() {
		return !usuariosPendentes.isEmpty();
	}

	public boolean ehUsuarioPendente(Usuario usuario) {
		for (Usuario u : usuariosPendentes) {
			if (u.equals(usuario)) {
				return true;
			}
		}
		return false;
	}
}
