package br.com.prog2.tf.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;



import br.com.prog2.tf.model.Cliente;

public class ClienteDAO {
	public void cadastrarCliente(Cliente cliente) throws ExceptionDAO{
		String sql = "insert into cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) value (?,?,?,?,?,?,?,?)";
		PreparedStatement pStatement = null;
		java.sql.Connection connection = null;
		
		try {
			connection = new ConnectionMVC().getConnection();
			pStatement = connection.prepareStatement(sql);
			//pStatement.setInt(1, cliente.getCodCliente());
			pStatement.setString(1, cliente.getNomeCliente());
			pStatement.setString(2, cliente.getRgCliente());
			pStatement.setString(3, cliente.getEnderecoCliente());
			pStatement.setString(4, cliente.getBairroCliente());
			pStatement.setString(5, cliente.getCidadeCliente());
			pStatement.setString(6, cliente.getEstadoCliente());
			pStatement.setString(7, cliente.getCEPCliente());
			pStatement.setDate(8, (java.sql.Date) new Date(cliente.getNascimentoCliente().getTime()));
			pStatement.execute();
		}catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar cliente: " + e);
		}finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o Statement: " + e);
			}try {
				if(connection != null) {
					connection.close();
				}
			}
				catch (SQLException e) {
					throw new ExceptionDAO("Erro ao fechar a conex�o: " + e);
				}
		}
	}
	
	
}
