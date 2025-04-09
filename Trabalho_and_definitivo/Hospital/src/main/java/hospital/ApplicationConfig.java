package hospital;

import jakarta.xml.ws.Endpoint;

public class ApplicationConfig {
    public static void main(String[] args) {
        // Publica os endpoints SOAP diretamente
        String baseUrl = "http://0.0.0.0:8080/hospital/";

        Endpoint.publish(baseUrl + "Medico", new MedicoWSImp());



        Endpoint.publish(baseUrl + "Paciente", new PacienteWSImp());



        Endpoint.publish(baseUrl + "Consultas", new ConsultaWSImp());




        System.out.println("Serviços dentro do  SOAP Lançados com total sucesso!");
    }
}
