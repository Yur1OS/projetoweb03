package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class UsuarioRepository {
	public void create (Usuario usuario) throws Exception {
		//abrindo conexão com o BD
		Connection connection = ConnectionFactory.getConnection();
		
		//Executando rotinas sql
		PreparedStatement statement = connection.prepareStatement("insert into usuario(nome, email, senha) values(?,?,?)");
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.execute();
		
		//Fechando a conexão
		connection.close();
	}
	
	//Método para atualizar senha do usuário
	public void updateSenha(Integer idUsuario, String novaSenha) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("update usuario set senha = ? where idusuario = ?");
		statement.setString(1, novaSenha);
		statement.setInt(2, idUsuario);
		statement.execute();
		
		connection.close();
	}
	
	//método para consultar 1 usuário no BD
	public Usuario findByEmail(String email) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from usuario where email = ?");
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();
		
		Usuario usuario = null;
		
		//VRf se algum registro foi encontrado
		if(resultSet.next()) {
			
			usuario = new Usuario();
			
			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		
		//Fechando conexão
		connection.close();
		return usuario;
	}
	
	//método para consultar 1 usuário no BD através do email e senha
	public Usuario findByEmailAndSenha(String email, String senha) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from usuario where email = ? and senha= ?");
		statement.setString(1, email);
		statement.setString(2, senha);
		ResultSet resultSet = statement.executeQuery();
		
		Usuario usuario = null;
		
		//VRf se algum registro foi encontrado
		if(resultSet.next()) {
			
			usuario = new Usuario();
			
			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		
		//Fechando conexão
		connection.close();
		return usuario;
	}

}
