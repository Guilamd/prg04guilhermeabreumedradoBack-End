package fintech.backend.dto;

import java.time.LocalDateTime;

// Padrao de resposta usado quando a API precisa retornar algum erro.
public class ErroResponseDTO {

    private int status;

    private String erro;

    private String mensagem;

    private String caminho;

    private LocalDateTime dataHora;

    public ErroResponseDTO() {
    }

    public ErroResponseDTO(int status, String erro, String mensagem, String caminho, LocalDateTime dataHora) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
        this.dataHora = dataHora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
