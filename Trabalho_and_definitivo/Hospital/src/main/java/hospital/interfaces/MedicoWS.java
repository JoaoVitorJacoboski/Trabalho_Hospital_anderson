package hospital.interfaces;

import hospital.exceptions.BusinessException;
import hospital.domain.Medico;
import hospital.dto.MedicoRequestDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService
public interface MedicoWS {
    @WebMethod
    Medico inserir(MedicoRequestDTO medico) throws SQLException, NamingException, BusinessException;

    @WebMethod
    List<Medico> mostrar_Lista() throws SQLException, NamingException;

    @WebMethod
    void atualizar(Medico medico) throws SQLException, NamingException, BusinessException;

    @WebMethod
    void desligar(Integer id) throws SQLException, NamingException;
}
