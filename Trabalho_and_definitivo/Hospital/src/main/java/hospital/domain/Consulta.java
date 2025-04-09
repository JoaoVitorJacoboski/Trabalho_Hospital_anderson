package hospital.domain;

import java.time.LocalDateTime;

public class Consulta {
    private Integer id;
    private Integer idPaciente;
    private Integer idMedico;
    private LocalDateTime dtHora;
    private boolean cancelada = false;
    private String razaoCancelamento;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }
    public LocalDateTime getdtHora() { return dtHora; }
    public void setdtHora(LocalDateTime dtHora) { this.dtHora = dtHora; }
    public boolean isCancelada() { return cancelada; }
    public void setCancelada(boolean cancelada) { this.cancelada = cancelada; }
    public String getrazaoCancelamento() { return razaoCancelamento; }
    public void setrazaoCancelamento(String razaoCancelamento) { this.razaoCancelamento = razaoCancelamento; }
}
