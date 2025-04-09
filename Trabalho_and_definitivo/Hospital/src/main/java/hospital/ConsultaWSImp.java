package hospital;

import hospital.exceptions.BusinessException;
import hospital.domain.Consulta;
import hospital.dto.ConsultaRequestDTO;
import hospital.interfaces.ConsultaWS;
import hospital.services.ConsultaService;

import jakarta.jws.WebService;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "hospital.interfaces.ConsultaWS")
public class ConsultaWSImp implements ConsultaWS {

    private ConsultaService service = new ConsultaService();

    @Override
    public Consulta agendar(ConsultaRequestDTO consultaDTO) throws SQLException, NamingException, BusinessException {
        Consulta consulta = new Consulta();
        consulta.setIdPaciente(consultaDTO.getIdPaciente());
        consulta.setIdMedico(consultaDTO.getIdMedico());
        consulta.setdtHora(consultaDTO.getdtHora());
        return service.agendar(consulta);
    }

    @Override
    public List<Consulta> mostrar_Lista() throws SQLException, NamingException {
        return service.mostrar_Lista();
    }

    @Override
    public boolean cancelar(Integer id, String motivo) throws SQLException, NamingException, BusinessException {
        return service.cancelar(id, motivo);
    }
}
