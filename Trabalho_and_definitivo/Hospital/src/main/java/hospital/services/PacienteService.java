package hospital.services;

import hospital.exceptions.BusinessException;
import hospital.domain.Paciente;
import hospital.repositories.PacienteRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class PacienteService {
    private PacienteRepository repository = new PacienteRepository();

    public Paciente inserir(Paciente paciente) throws BusinessException, SQLException, NamingException {
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new BusinessException("Nome do paciente é de obrigatoriedade");
        }
        if (paciente.getNome().length() >= 100) {
            throw new BusinessException("Nome deve ter  (100) caracteres no máximo");
        }
        if (paciente.getCpf() == null || paciente.getCpf().isEmpty()) {
            throw new BusinessException("CPF é de obrigatoriedade");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new BusinessException("E-mail é de obrigatoriedade");
        }
        if (paciente.getLogradouro() == null || paciente.getLogradouro().isEmpty()) {
            throw new BusinessException("Logradouro é obrigatório");
        }
        if (paciente.getBairro() == null || paciente.getBairro().isEmpty()) {
            throw new BusinessException("Bairro é de obrigatoriedade");
        }
        if (paciente.getCidade() == null || paciente.getCidade().isEmpty()) {
            throw new BusinessException("Cidade é de obrigatoriedade");
        }
        if (paciente.getUf() == null || paciente.getUf().isEmpty()) {
            throw new BusinessException("UF é de obrigatoriedade");
        }
        if (paciente.getCep() == null || paciente.getCep().isEmpty()) {
            throw new BusinessException("CEP é de obrigatoriedade");
        }
        return repository.inserir(paciente);
    }

    public List<Paciente> mostrar_Lista() throws SQLException, NamingException {
        return repository.mostrar_Lista();
    }

    public void atualizar(Paciente paciente) throws BusinessException, SQLException, NamingException {
        if (paciente.getId() == null) {
            throw new BusinessException("ID do paciente é de obrigatoriedade para atualização");
        }
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new BusinessException("Nome do paciente é de obrigatoriedade");
        }
        repository.atualizar(paciente);
    }

    public void desligar(Integer id) throws SQLException, NamingException {
        repository.desligar(id);
    }
}
