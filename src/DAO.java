import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    //DAO - Data Access Object
    //envia os dados para o banco (insert)
    public boolean cadastrar(Paciente p){
        Connection connection = Connexion.connect();
        try {
            //Exception pode ser lido como um erro
            //Statement faz as operações do bd
            Statement statement = connection.createStatement();
            String sql =
            //torna o cadastro/insert dinâmico no bd
                    "INSERT INTO paciente VALUES(0,'"+p.getNome()+"','"+p.getCidade()+"',"+p.getTelefone()+")";
            //manda o Statemente manipular os dados para o bd
            statement.execute(sql); //executa/envia os dados para o bd - é como se fosse o enter do teclado
            statement.close();
            connection.close();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> listar(){
        Connection connection = Connexion.connect();
        List<Paciente> lista = new ArrayList<>(); //o list é um tipo vetor(array)
        //vetor - é uma variavel
        //vetor - armazena vários dados do mesmo tipo - com posição começando sempre em 0
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM paciente"; //comando sql para listar dados
            ResultSet resultSet = statement.executeQuery(sql);
            //o resultset recebe tudo oq foi cadastrado no bd
            //executeQuery é para pegar os dados cadastrados
            while (resultSet.next()){
                //while - enquanto houver dados ele vai rodar
                Paciente p = new Paciente();
                p.setId(resultSet.getInt("id"));
                p.setNome(resultSet.getString("nome"));
                p.setCidade(resultSet.getString("cidade"));
                p.setTelefone(resultSet.getInt("Telefone"));
                lista.add(p);
            }
            connection.close();
            statement.close();
            return lista;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public Paciente buscarPorId(int id){
        Connection connection = Connexion.connect();
        Paciente paciente = new Paciente();
        try{
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM paciente WHERE id = "+id;
            ResultSet resultSet = statement.executeQuery(sql); //resultSet é o retorno do select
            while (resultSet.next()){
                paciente.setId(resultSet.getInt("id"));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCidade(resultSet.getString("cidade"));
                paciente.setTelefone(resultSet.getInt("telefone"));
            }
            statement.close();
            connection.close();
            return paciente;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean atualizar(Paciente paciente){
        Connection connection = Connexion.connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE paciente SET nome='"
                    +paciente.getNome()+ "',cidade='"
                    +paciente.getCidade()+"',telefone="
                    +paciente.getTelefone()+" WHERE id="
                    +paciente.getId();
            statement.execute(sql);
            statement.close();
            connection.close();
            return true;
        }catch (SQLException e){
        throw new RuntimeException(e);
        }
    }

    public boolean excluir(int id){
        Connection connection = Connexion.connect();
        try{
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM paciente WHERE id="+id;
            statement.execute(sql);
            statement.close();
            connection.close();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
