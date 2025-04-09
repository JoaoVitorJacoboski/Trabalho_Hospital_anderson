package hospital.services;

import hospital.exceptions.BusinessException;
import hospital.domain.Medico;
import hospital.repositories.MedicoRepository;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MedicoService {
    private MedicoRepository repository = new MedicoRepository();

    public Medico inserir(Medico medico) throws BusinessException, SQLException, NamingException {
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new BusinessException("Nome do médico é obrigatório");
        }
        if (medico.getNome().length() > 100) {
            throw new BusinessException("Nome deve ter no máximo 100 caracteres");
        }
        if (medico.getCrm() == null) {
            throw new BusinessException("CRM é obrigatório");
        }
        if (medico.getEmail() == null || medico.getEmail().isEmpty()) {
            throw new BusinessException("E-mail é obrigatório");
        }
        if (medico.getEspecialidade() == null || medico.getEspecialidade().isEmpty()) {
            throw new BusinessException("Especialidade é obrigatória");
        }

        List<String> especialidadesValidas = Arrays.asList("Ortopedia", "Cardiologia", "Ginecologia", "Dermatologia");
        if (!especialidadesValidas.contains(medico.getEspecialidade())) {
            throw new BusinessException("Especialidade INcorreta. tem que  ser: Ortopedia, Cardiologia, Ginecologia ou Dermatologia");
        }

        if (medico.getLogradouro() == null || medico.getLogradouro().isEmpty()) {
            throw new BusinessException("Logradouro de obrigatoriedade");
        }
        if (medico.getBairro() == null || medico.getBairro().isEmpty()) {
            throw new BusinessException("Bairro de obrigatoriedade");
        }

        return repository.inserir(medico);
    }

    public List<Medico> mostrar_Lista() throws SQLException, NamingException {
        return repository.mostrar_Lista();
    }

    public void atualizar(Medico medico) throws BusinessException, SQLException, NamingException {
        if (medico.getId() == null) {
            throw new BusinessException("ID do médico de obrigatoriedade para atualização");
        }
        if (medico.getNome() == null || medico.getNome().isEmpty()) {
            throw new BusinessException("Nome do médico é de obrigatoriedade");
        }
        repository.atualizar(medico);
    }

    public void desligar(Integer id) throws SQLException, NamingException {
        repository.desligar(id);
    }
}
