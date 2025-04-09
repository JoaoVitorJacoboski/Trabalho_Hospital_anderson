package hospital.repositories;

import hospital.domain.Consulta;
import hospital.infra.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {
    private static final String INSERT = "INSERT INTO consulta (id_paciente, id_medico, dataAgendada, cancelada, motivo_cancelamento) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM consulta WHERE cancelada = false ORDER BY data_hora";
    private static final String CANCELAR = "UPDATE consulta SET cancelada = true, motivo_cancelamento = ? WHERE id = ? AND data_hora > ?";
    private static final String AVERIGUAR_DISPONIBILIDADE = "SELECT COUNT(*) FROM consulta WHERE id_medico = ? AND data_hora = ? AND cancelada = false";

    public Consulta inserir(Consulta consulta) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //conectar a banco senha postgres
        try {
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, consulta.getIdPaciente());
            pstmt.setObject(2, consulta.getIdMedico(), Types.INTEGER);
            pstmt.setTimestamp(3, Timestamp.valueOf(consulta.getdtHora()));
            pstmt.setBoolean(4, consulta.isCancelada());
            pstmt.setString(5, consulta.getrazaoCancelamento());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                consulta.setId(rs.getInt(1));
            }
            return consulta;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<Consulta> mostrar_Lista() throws SQLException, NamingException {
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setIdPaciente(rs.getInt("id_paciente"));
                consulta.setIdMedico(rs.getInt("id_medico"));
                consulta.setdtHora(rs.getTimestamp("data_hora").toLocalDateTime());
                consulta.setCancelada(rs.getBoolean("cancelada"));
                consulta.setrazaoCancelamento(rs.getString("motivo_cancelamento"));

                consultas.add(consulta);
            }
        }
        return consultas;
    }

    public boolean cancelar(Integer id, String motivo) throws SQLException, NamingException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(CANCELAR)) {

            pstmt.setString(1, motivo);
            pstmt.setInt(2, id);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().plusHours(24)));

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean averiguarDisponibilidadeMedico(Integer idMedico, LocalDateTime dtHora) throws SQLException, NamingException {
        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(AVERIGUAR_DISPONIBILIDADE)) {

            pstmt.setInt(1, idMedico);
            pstmt.setTimestamp(2, Timestamp.valueOf(dtHora));

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) == 0;
            }
        }
    }
}
