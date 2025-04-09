package hospital.domain;

import hospital.dto.MedicoRequestDTO;

public class Medico {
    private Integer id;
    private Integer crm;
    private String nome;
    private String telefone;
    private String email;
    private String especialidade;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private boolean ativo = true;

    public Medico() {}

    public Medico(MedicoRequestDTO dto) {
        this.crm = dto.getCrm();
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.email = dto.getEmail();
        this.especialidade = dto.getEspecialidade();
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.bairro = dto.getBairro();
        this.complemento = dto.getComplemento();
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getCrm() { return crm; }
    public void setCrm(Integer crm) { this.crm = crm; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
