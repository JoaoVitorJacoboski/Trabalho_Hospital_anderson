package hospital.dto;

import java.time.LocalDateTime;

public class  ConsultaRequestDTO {
    private Integer idPaciente;
    private Integer idMedico;
    private LocalDateTime dtHora;

    // Getters e Setters
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }
    public LocalDateTime getdtHora() { return dtHora; }
    public void setdtHora(LocalDateTime dtHora) { this.dtHora = dtHora; }
}
