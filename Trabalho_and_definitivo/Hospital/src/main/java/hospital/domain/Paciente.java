package hospital.domain;

import hospital.dto.PacienteRequestDTO;

public class Paciente {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
    private String cep;
    private boolean ativo = true;

    public Paciente() {}

    public Paciente(PacienteRequestDTO dto) {
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
        this.logradouro = dto.getLogradouro();
        this.numero = dto.getNumero();
        this.bairro = dto.getBairro();
        this.complemento = dto.getComplemento();
        this.cidade = dto.getCidade();
        this.uf = dto.getUf();
        this.cep = dto.getCep();
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
