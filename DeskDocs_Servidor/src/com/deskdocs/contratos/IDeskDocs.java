package com.deskdocs.contratos;

import com.deskdocs.entidades.AlteracaoDocumento;
import com.deskdocs.entidades.Documento;
import com.deskdocs.entidades.Pacote;
import com.deskdocs.entidades.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDeskDocs extends Remote
{
	public Pacote<List<Usuario>> getTodosUsuarios() throws RemoteException;

	public Pacote<List<Documento>> getTodosDocumentos() throws RemoteException;

	public Pacote<Usuario> cadastrarUsuario(Usuario usuario) throws RemoteException;

	public Pacote<Usuario> conectarUsuario(Usuario usuario) throws RemoteException;

	public void desconectarUsuario(Usuario usuario) throws RemoteException;

	public Pacote<Documento> criarDocumento(Documento documento) throws RemoteException;

	public void excluirDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public Pacote<Documento> abrirDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public void fecharDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public void compartilharDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public void descompartilharDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public Pacote<Documento> atualizarDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public Pacote<List<Usuario>> getUsuariosEditandoDocumento(Documento documento, Usuario usuario) throws RemoteException;

	public Pacote<List<Usuario>> getUsuariosCompartilhando(Documento documento) throws RemoteException;

	public Pacote<List<Documento>> getDocumentosCompartilhados(Usuario usuario) throws RemoteException;

	public Pacote<List<AlteracaoDocumento>> getAlteracoes(Documento documento, Usuario usuario) throws RemoteException;

	public void inserirTexto(Usuario usuario, Documento documento, String texto, int offset) throws RemoteException;

	public void removerTexto(Usuario usuario, Documento documento, int offset, int length) throws RemoteException;
}
