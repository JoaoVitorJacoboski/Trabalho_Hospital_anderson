package hospital;

import hospital.exceptions.BusinessException;
import hospital.domain.Medico;
import hospital.dto.MedicoRequestDTO;
import hospital.interfaces.MedicoWS;
import hospital.services.MedicoService;

import jakarta.jws.WebService;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "hospital.interfaces.MedicoWS")
public class MedicoWSImp implements MedicoWS {

    private MedicoService service = new MedicoService();

    @Override
    public Medico inserir(MedicoRequestDTO medicoDTO) throws SQLException, NamingException, BusinessException {
        Medico medico = new Medico(medicoDTO);
        return service.inserir(medico);
    }

    @Override
    public List<Medico> mostrar_Lista() throws SQLException, NamingException {
        return service.mostrar_Lista();
    }

    @Override
    public void atualizar(Medico medico) throws SQLException, NamingException, BusinessException {
        service.atualizar(medico);
    }

    @Override
    public void desligar(Integer id) throws SQLException, NamingException {
        service.desligar(id);
    }
}
