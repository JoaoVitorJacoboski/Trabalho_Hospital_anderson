package hospital.interfaces;

import hospital.exceptions.BusinessException;
import hospital.domain.Paciente;
import hospital.dto.PacienteRequestDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService
public interface PacienteWS {
    @WebMethod
    Paciente inserir(PacienteRequestDTO paciente) throws SQLException, NamingException, BusinessException;

    @WebMethod
    List<Paciente> mostrar_Lista() throws SQLException, NamingException;

    @WebMethod
    void atualizar(Paciente paciente) throws SQLException, NamingException, BusinessException;

    @WebMethod
    void desligar(Integer id) throws SQLException, NamingException;
}
