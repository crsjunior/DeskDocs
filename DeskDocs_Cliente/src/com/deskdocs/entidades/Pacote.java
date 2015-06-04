package com.deskdocs.entidades;

import java.io.Serializable;

public class Pacote<T> implements Serializable
{
	private static final long serialVersionUID = 4L;

	private T objeto;
	private boolean sucesso;
	private String mensagem;

	public Pacote() {
		this.objeto = null;
		this.sucesso = true;
		this.mensagem = null;
	}

	/**
	 * Constroi um pacote como sendo resultado de sucesso.
	 */
	public Pacote(T objeto) {
		this.objeto = objeto;
		this.sucesso = true;
		this.mensagem = null;
	}

	/**
	 * Constroi um pacote como sendo resultado de erro.
	 */
	public Pacote(T objeto, String mensagem) {
		this.objeto = objeto;
		this.sucesso = false;
		this.mensagem = mensagem;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
