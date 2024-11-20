public class Paciente {
    private int id;
    private String nome;
    private String cidade;
    private int telefone;

    public Paciente(){}

    public Paciente(int id, String nome, String cidade, int telefone) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
