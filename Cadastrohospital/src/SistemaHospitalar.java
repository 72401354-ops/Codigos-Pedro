import java.util.ArrayList;

public class SistemaHospitalar {

    private ArrayList<Paciente> pacientes;
    private ArrayList<String> doencasCadastradas;

    public SistemaHospitalar(){
        pacientes = new ArrayList<>();
        doencasCadastradas = new ArrayList<>();

    }

    public void cadastrarPacientes(String nome, int idade, String doenca){
        Paciente novoPaciente = new Paciente (nome, idade, doenca);
        pacientes.add(novoPaciente);
        
        if(!doencasCadastradas.contains(doenca)){
            doencasCadastradas.add(doenca);
        }
        
        System.out.println("Paciente Cadastrado com sucesso!");
    }
    
    
    
    public void consultarPaciente(String nome){
        boolean encontrado = false;
        for(Paciente p : pacientes){
            if(p.getNome().equals(nome)){
                System.out.println(p);
                encontrado = true;
                break;
            }
        }
        
        
        if(!encontrado){
            System.out.println("Paciente nao encontrado");
        }
    }
    
    
    public void listarPacientes(){
        if(doencasCadastradas.isEmpty()){
            System.out.println("nenhuma doença cadastrada ainda.");
        }else{
            System.out.println("=== DOENÇAS CADASTRADAS ===");
            for(int i = 0; i < doencasCadastradas.size(); i++){
                System.out.println((i + 1) + ". " + doencasCadastradas.get(i));
                
                
            }
        }
    }
    
    
    public void exibirNumeroPacientes(){
        System.out.println("Numero total de pacientes cadastrados: " + Paciente.getTotalPacientes());
    }

    public void listarDoencas() {
        
    }
}



