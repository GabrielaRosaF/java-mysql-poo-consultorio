import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    //a classe connexion apenas conecta ao banco
public class Connexion {
    //atributo que verifica o estado da conexão
    //static - atributo que é acessado sem criar classe, se usa direto
    //se estiver nulo vai procurar a conexão (caminho do banco), se não tiver caminho não retorna nada
    private static Connection connection = null;

    public static Connection connect(){
        //SGBD - sistema gerenciador de banco de dados
        //JDBC - java data base connector
        //Exception (excessão) - tem que fazer o tratamento do código com try e catch
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //forname - é o tipo de gerenciador de banco de dados - SGBD
            //endereço do banco para a conexão
            //root é um usuário padrão
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3312/consultorio", "root", "");
            return connection;
        }catch (SQLException | ClassNotFoundException e){
            //SQLException - é para ver erros de SQL
            //ClassNotFoundException - erros quanto as classes (classe não foi achada)
            throw new RuntimeException(e);
            //throw(lançar/mostrar) - lança/mostra o tipo de erro encontrado e em que linha ele está
        }
    }
}
