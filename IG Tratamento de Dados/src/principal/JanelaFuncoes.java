package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class JanelaFuncoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void janelaFuncoes(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaFuncoes frame = new JanelaFuncoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaFuncoes() {
		setTitle("Funções");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textoFuncoes = new JTextPane();
		textoFuncoes.setEditable(false);
		int linguagemInicial = Config.idioma();
		if(linguagemInicial == 1) {		
			textoFuncoes.setText("\r\nOlá! O programa 'Tratamento de Dados' é utilizado para realizar tratamentos estatísticos básicos e gerar relatórios automáticos partindo de um arquivo '.txt' de entrada.\r\n\r\n> Selecionar arquivo (CTRL + E):\r\n       Selecione o arquivo que deseja ler e confirme.\r\n       O arquivo selecionado só pode conter caracteres numéricos, “.” e “,”. Os valores no arquivo devem estar ordenados de forma que haja um valor por linha.\r\n       Só utilize pontos e vírgulas para indicar casas decimais. Não insira valores na formatação dos tipos: “1.234,56”  e “1,234.56”.\t\r\n\r\n> Gerar relatório (CTRL + R):\r\n       Selecione o diretório/pasta que deseja salvar o relatório ‘.txt’.\r\n       Caso ocorra algum problema para gerar o relatório, será indicado na caixa “Salvo em:”\t\r\n\r\n");
		}
		if(linguagemInicial == 2 ) {
			textoFuncoes.setText("\r\nHello! The 'Data Processing' program is used to carry out basic statistical treatments and generate automatic reports starting from an input '.txt' file. \r\n\r\n> Select file (CTRL + E):\r\n   	Select the file and confirm.\r\n  The selected file can only contain numeric characters, ‘.’ and ‘,’. Values ​​in the file must contain only one value per line.\r\n  Only use semicolons to indicate decimal places. Do not enter values ​​in the formatting of types: ‘1,234.56’ and ‘1,234.56’.\t\r\n\r\n> Generate report (CTRL + R):\r\n        Select the directory/folder you want to save the '.txt' report.\r\n If there is any problem generating the report, it will be indicated in the box ‘Saved in:’\t\r\n\r\n");
		}
		
		textoFuncoes.setBounds(10, 11, 428, 289);
		contentPane.add(textoFuncoes);
		
		
		
		
		
		
	}
}
