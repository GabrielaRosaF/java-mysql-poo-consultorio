import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


public class App extends JFrame{
    private JPanel panel;
    private JPanel buttons;
    private JButton btnCadastrar;
    private JButton btnListar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JTable tabela;
    private JScrollPane scroll;
    List<Paciente> lista;//criando a lista do tipo list - declarar
    private int id; //variável q vai pegar o ID

    public App(){
        setContentPane(panel);
        setTitle("App");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        showData(); //chamando o metodo showdata para exibir os dados

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cadastro();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            new Atualizacao(id);
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAO d = new DAO();
                int opcao = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir?");
                if(opcao == JOptionPane.YES_OPTION){
                    if(d.excluir(id)){
                        JOptionPane.showMessageDialog(null,"Dados excluidos com sucesso");
                    }else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir");
                    }
                }
            }
        });

        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow(); //pega o indice da linha
                //passa o valor da linha e retorna o valor da coluna 0 (ID) dessa coluna
                id = Integer.parseInt(tabela.getModel().getValueAt(linha,0).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

    }

    public void showData(){
        //tudo isso é metodo
        DAO d = new DAO();
        lista = d.listar();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //DefaultTableModel é uma classe padrão para tabela, ela que é manipulada
        model.setColumnIdentifiers(new Object[]{"Id", "Nome", "Cidade", "Telefone"});
        //mostra os nomes que vão aparecer nos campos da tabela
        model.setRowCount(0);
        for (int i=0; i<lista.size(); i++){
            model.insertRow(i,new Object[]{
                    lista.get(i).getId(),
                    lista.get(i).getNome(),
                    lista.get(i).getCidade(),
                    lista.get(i).getTelefone()
            });
        }
    }
}
