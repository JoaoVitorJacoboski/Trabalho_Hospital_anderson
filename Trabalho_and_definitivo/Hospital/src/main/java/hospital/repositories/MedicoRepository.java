package hospital.repositories;

import hospital.domain.Medico;
import hospital.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    private static final String INSERT = "INSERT INTO medico (crm, nome, telefone, email, especialidade, logradouro, numero, bairro, complemento, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM medico WHERE ativo = true ORDER BY nome";
    private static final String UPDATE = "UPDATE medico SET nome = ?, telefone = ?, logradouro = ?, numero = ?, bairro = ?, complemento = ? WHERE id = ?";
    private static final String SOFT_DELETE = "UPDATE medico SET ativo = false WHERE id = ?";

    public Medico inserir(Medico medico) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, medico.getCrm());
            pstmt.setString(2, medico.getNome());
            pstmt.setString(3, medico.getTelefone());
            pstmt.setString(4, medico.getEmail());
            pstmt.setString(5, medico.getEspecialidade());
            pstmt.setString(6, medico.getLogradouro());
            pstmt.setObject(7, medico.getNumero(), Types.INTEGER);
            pstmt.setString(8, medico.getBairro());
            pstmt.setString(9, medico.getComplemento());
            pstmt.setBoolean(10, medico.isAtivo());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                medico.setId(rs.getInt(1));
            }
            return medico;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Medico> mostrar_Lista() throws SQLException, NamingException {
        List<Medico> medicos = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setCrm(rs.getInt("crm"));
                medico.setNome(rs.getString("nome"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEmail(rs.getString("email"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setLogradouro(rs.getString("logradouro"));
                medico.setNumero(rs.getInt("numero"));
                medico.setBairro(rs.getString("bairro"));
                medico.setComplemento(rs.getString("complemento"));
                medico.setAtivo(rs.getBoolean("ativo"));

                medicos.add(medico);
            }
        }
        return medicos;
    }

    public void atualizar(Medico medico) throws SQLException, NamingException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE)) {

            pstmt.setString(1, medico.getNome());
            pstmt.setString(2, medico.getTelefone());
            pstmt.setString(3, medico.getLogradouro());
            pstmt.setInt(4, medico.getNumero());
            pstmt.setString(5, medico.getBairro());
            pstmt.setString(6, medico.getComplemento());
            pstmt.setInt(7, medico.getId());

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
