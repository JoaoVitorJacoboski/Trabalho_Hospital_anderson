package hospital;

import hospital.exceptions.BusinessException;
import hospital.domain.Paciente;
import hospital.dto.PacienteRequestDTO;
import hospital.interfaces.PacienteWS;
import hospital.services.PacienteService;

import jakarta.jws.WebService;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "hospital.interfaces.PacienteWS")
public class PacienteWSImp implements PacienteWS {

    private PacienteService service = new PacienteService();

    @Override
    public Paciente inserir(PacienteRequestDTO pacienteDTO) throws SQLException, NamingException, BusinessException {
        Paciente paciente = new Paciente(pacienteDTO);
        return service.inserir(paciente);
    }

    @Override
    public List<Paciente> mostrar_Lista() throws SQLException, NamingException {
        return service.mostrar_Lista();
    }

    @Override
    public void atualizar(Paciente paciente) throws SQLException, NamingException, BusinessException {
        service.atualizar(paciente);
    }

    @Override
    public void desligar(Integer id) throws SQLException, NamingException {
        service.desligar(id);
    }
}
