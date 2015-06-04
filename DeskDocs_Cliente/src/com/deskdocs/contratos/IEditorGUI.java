package com.deskdocs.contratos;

import com.deskdocs.entidades.AlteracaoDocumento;
import com.deskdocs.entidades.Documento;
import com.deskdocs.entidades.Usuario;
import java.util.List;

public interface IEditorGUI
{
	public Documento getDocumento();

	public void setDocumento(Documento documento);

	public void atualizarUsuariosEditando(List<Usuario> usuarios);

	public void aplicarAlteracoes(List<AlteracaoDocumento> alteracoes);

	public void documentoExcluido();
}
