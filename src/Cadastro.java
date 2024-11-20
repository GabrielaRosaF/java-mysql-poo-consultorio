import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JFrame{
    //atributo +- = variavel
    private JPanel panel;
    private JTextField txtCidade;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnCadastrar;

    public Cadastro(){
        //metodo +- = função
        //metodos para montar a tela
        setContentPane(panel);
        setTitle("Cadastro");
        setSize(400,200);
        setLocationRelativeTo(null);
        setVisible(true);

        btnCadastrar.addActionListener(new ActionListener() {
            //ActionListener - é uma Interface
            //Interface - class pré-pronta
            //Override - "sobreescrever" o código, mudar o código
            @Override
            public void actionPerformed(ActionEvent e) {
                //1º - validação dos campos vazios
                if (txtNome.getText().isEmpty() || txtCidade.getText().isEmpty() || txtTelefone.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Existem campos vazios");
                }else{
                    try{
                        int n = Integer.parseInt(txtTelefone.getText()); //tenta converter para int e guardar o valor em n
                        DAO dao = new DAO();
                        Paciente paciente = new Paciente();
                        paciente.setNome(txtNome.getText());
                        paciente.setCidade(txtCidade.getText());
                        paciente.setTelefone(n);
                        if (dao.cadastrar(paciente)){
                            JOptionPane.showMessageDialog(null, "Sucasso ao cadastrar");
                            txtNome.setText("");
                            txtCidade.setText("");
                            txtTelefone.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
                        }
                    }catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(null, "No campo telefone digite apenas números");
                    }
                }
            }
        });
    }
}
