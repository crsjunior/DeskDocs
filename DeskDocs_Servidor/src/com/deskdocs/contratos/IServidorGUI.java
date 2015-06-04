package com.deskdocs.contratos;

public interface IServidorGUI
{
	public void enviarMensagemOk(String mensagem);

	public void enviarMensagemOk(String mensagem, String metodo);

	public void enviarMensagemErro(String mensagem);

	public void enviarMensagemErro(String mensagem, String metodo);

	public void setUsuariosCadastrados(int valor);

	public void setUsuariosConectados(int valor);

	public void setDocumentosCriados(int valor);

	public void setDocumentosAbertos(int valor);
}
