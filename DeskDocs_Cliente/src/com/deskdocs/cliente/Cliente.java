package com.deskdocs.cliente;

import com.deskdocs.contratos.IDeskDocs;
import com.deskdocs.entidades.Usuario;
import com.deskdocs.utilidades.Constantes;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente
{
	private static Cliente instance = null;
	private IDeskDocs deskDocs = null;
	private Usuario usuario = null;
	private boolean conectado = false;

	private Cliente() {
	}

	public static synchronized Cliente getInstance() {
		if (instance == null) {
			instance = new Cliente();
		}
		return instance;
	}

	public synchronized IDeskDocs getDeskDocs() {
		return deskDocs;
	}

	public synchronized void setDeskDocs(IDeskDocs deskDocs) {
		this.deskDocs = deskDocs;
	}

	public synchronized Usuario getUsuario() {
		return usuario;
	}

	public synchronized void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public synchronized boolean isConectado() {
		if (!conectado) {
			try {
				deskDocs = (IDeskDocs) Naming.lookup(Constantes.SERVIDOR_NOME);
				conectado = true;
			} catch (NotBoundException ex) {
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
			} catch (MalformedURLException ex) {
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
			} catch (RemoteException ex) {
				Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return conectado;
	}
}
