package hospital.services;

import hospital.exceptions.BusinessException;
import hospital.domain.Consulta;
import hospital.domain.Medico;
import hospital.repositories.ConsultaRepository;
import hospital.repositories.MedicoRepository;
import hospital.repositories.PacienteRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaService {
    private ConsultaRepository consultaRepository = new ConsultaRepository();
    private MedicoRepository medicoRepository = new MedicoRepository();
    private PacienteRepository pacienteRepository = new PacienteRepository();

    public Consulta agendar(Consulta consulta) throws BusinessException, SQLException, NamingException {
        validarHorarioClinica(consulta.getdtHora());
        validarAntecedencia(consulta.getdtHora());
        validarPacienteAtivo(consulta.getIdPaciente());

        if (consulta.getIdMedico() != null) {
            validarMedicoAtivo(consulta.getIdMedico());
            validarDisponibilidadeMedico(consulta.getIdMedico(), consulta.getdtHora());
        } else {
            consulta.setIdMedico(selecionarMedicoDisponivel(consulta.getdtHora()));
        }

        averiguarConsultaDuplicadaPaciente(consulta.getIdPaciente(), consulta.getdtHora());

        return consultaRepository.inserir(consulta);
    }

    public List<Consulta> mostrar_Lista() throws SQLException, NamingException {
        return consultaRepository.mostrar_Lista();
    }

    public boolean cancelar(Integer id, String motivo) throws BusinessException, SQLException, NamingException {
        if (motivo == null || motivo.isEmpty()) {
            throw new BusinessException("Razao de cancelamento obrigatoria");
        }
        return consultaRepository.cancelar(id, motivo);
    }

    private void validarHorarioClinica(LocalDateTime dtHora) throws BusinessException {
        DayOfWeek dia = dtHora.getDayOfWeek();
        int hora = dtHora.getHour();

        if (dia == DayOfWeek.SUNDAY || hora < 7 || hora >= 19) {
            throw new BusinessException("Hospital fechado ");
        }
    }

    private void validarAntecedencia(LocalDateTime dtHora) throws BusinessException {
        if (dtHora.isBefore(LocalDateTime.now().plusMinutes(30))) {
            throw new BusinessException("Agendamento somente com 30 minutos de antecedencia");
        }
    }

    private void validarPacienteAtivo(Integer idPaciente) throws BusinessException, SQLException, NamingException {
        if (!pacienteRepository.mostrar_Lista().stream().anyMatch(p -> p.getId().equals(idPaciente) && p.isAtivo())) {
            throw new BusinessException("Paciente não foi encontrado ou inesistente");
        }
    }

    private void validarMedicoAtivo(Integer idMedico) throws BusinessException, SQLException, NamingException {
        if (!medicoRepository.mostrar_Lista().stream().anyMatch(m -> m.getId().equals(idMedico) && m.isAtivo())) {
            throw new BusinessException("Médico não foi encontrado ou inesistente");
        }
    }

    private void validarDisponibilidadeMedico(Integer idMedico, LocalDateTime dtHora) throws BusinessException, SQLException, NamingException {
        if (!consultaRepository.averiguarDisponibilidadeMedico(idMedico, dtHora)) {
            throw new BusinessException("Médico Com agendamento programado para esse horario");
        }
    }

    private Integer selecionarMedicoDisponivel(LocalDateTime dtHora) throws SQLException, NamingException, BusinessException {
        List<Medico> medicosAtivos = medicoRepository.mostrar_Lista();
        for (Medico medico : medicosAtivos) {
            if (consultaRepository.averiguarDisponibilidadeMedico(medico.getId(), dtHora)) {
                return medico.getId();
            }
        }
        throw new BusinessException("Nenhum médico disponível neste horário");
    }

    private void averiguarConsultaDuplicadaPaciente(Integer idPaciente, LocalDateTime dataHora) throws SQLException, NamingException, BusinessException {
        List<Consulta> consultas = consultaRepository.mostrar_Lista();
        if (consultas.stream().anyMatch(c ->
                c.getIdPaciente().equals(idPaciente) &&
                        c.getdtHora().toLocalDate().equals(dataHora.toLocalDate()) &&
                        !c.isCancelada())) {
            throw new BusinessException("Paciente com consulta já agendade  ");
        }
    }
}
