package hospital.interfaces;

import hospital.exceptions.BusinessException;
import hospital.domain.Consulta;
import hospital.dto.ConsultaRequestDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService
public interface ConsultaWS {
    @WebMethod
    Consulta agendar(ConsultaRequestDTO consulta) throws SQLException, NamingException, BusinessException;
    //consultas e agendas




    @WebMethod
    List<Consulta> mostrar_Lista() throws SQLException, NamingException;

    @WebMethod
    boolean cancelar(Integer id, String motivo) throws SQLException, NamingException, BusinessException;
}
