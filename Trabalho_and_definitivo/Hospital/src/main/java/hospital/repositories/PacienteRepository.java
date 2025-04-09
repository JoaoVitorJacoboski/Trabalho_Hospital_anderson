package hospital.repositories;

import hospital.domain.Paciente;
import hospital.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {
    private static final String INSERT = "INSERT INTO paciente (nome, telefone, email, cpf, logradouro, numero, bairro, complemento, cidade, uf, cep, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM paciente WHERE ativo = true ORDER BY nome";
    private static final String UPDATE = "UPDATE paciente SET nome = ?, telefone = ?, logradouro = ?, numero = ?, bairro = ?, complemento = ?, cidade = ?, uf = ?, cep = ? WHERE id = ?";
    private static final String SOFT_DELETE = "UPDATE paciente SET ativo = false WHERE id = ?";

    public Paciente inserir(Paciente paciente) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getEmail());
            pstmt.setString(4, paciente.getCpf());
            pstmt.setString(5, paciente.getLogradouro());
            pstmt.setObject(6, paciente.getNumero(), Types.INTEGER);
            pstmt.setString(7, paciente.getBairro());
            pstmt.setString(8, paciente.getComplemento());
            pstmt.setString(9, paciente.getCidade());
            pstmt.setString(10, paciente.getUf());
            pstmt.setString(11, paciente.getCep());
            pstmt.setBoolean(12, paciente.isAtivo());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
            return paciente;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Paciente> mostrar_Lista() throws SQLException, NamingException {
        List<Paciente> pacientes = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setLogradouro(rs.getString("logradouro"));
                paciente.setNumero(rs.getInt("numero"));
                paciente.setBairro(rs.getString("bairro"));
                paciente.setComplemento(rs.getString("complemento"));
                paciente.setCidade(rs.getString("cidade"));
                paciente.setUf(rs.getString("uf"));
                paciente.setCep(rs.getString("cep"));
                paciente.setAtivo(rs.getBoolean("ativo"));

                pacientes.add(paciente);
            }
        }
        return pacientes;
    }

    public void atualizar(Paciente paciente) throws SQLException, NamingException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getLogradouro());
            pstmt.setInt(4, paciente.getNumero());
            pstmt.setString(5, paciente.getBairro());
            pstmt.setString(6, paciente.getComplemento());
            pstmt.setString(7, paciente.getCidade());
            pstmt.setString(8, paciente.getUf());
            pstmt.setString(9, paciente.getCep());
            pstmt.setInt(10, paciente.getId());

            pstmt.executeUpdate();
        }
    }

    public void desligar(Integer id) throws SQLException, NamingException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SOFT_DELETE)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
