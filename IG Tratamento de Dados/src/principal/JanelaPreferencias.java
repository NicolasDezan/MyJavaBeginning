package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.Font;

public class JanelaPreferencias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField diretorioEscolha2;

	public static void janelaPreferencias(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPreferencias frame = new JanelaPreferencias();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public JanelaPreferencias() {

		
		
		int linguagemAtual = 0;
		String s_prefDiretorioDados = null;
		String s_prefDiretorioRelatorio = null;		
		try {
			File diretorioPref = new File(new java.io.File(".").getPath()+"\\.config\\config.txt");
			List<String> pref = Arquivo.lerArquivo(diretorioPref);
				linguagemAtual = Integer.valueOf(pref.get(0));
				s_prefDiretorioDados = new File(pref.get(1)).getPath();
				s_prefDiretorioRelatorio = new File(pref.get(2)).getPath();			
			}catch(Exception semConfig) {}	

		JButton botaoSelecionarEscolher = new JButton("Selecionar");
		JButton botaoSelecionarRelatorio = new JButton("Selecionar");
		JTextField diretorioRelatorio = new JTextField();
		diretorioRelatorio.setEditable(false);
		setTitle("Preferências");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		botaoSelecionarEscolher.setBounds(246, 27, 97, 23);
		contentPane.add(botaoSelecionarEscolher);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		botaoSelecionarRelatorio.setBounds(246, 76, 97, 23);
		contentPane.add(botaoSelecionarRelatorio);		
		diretorioRelatorio.setColumns(10);
		diretorioRelatorio.setBounds(10, 77, 226, 20);
		contentPane.add(diretorioRelatorio);		
		JLabel txtEscolher = new JLabel("Diretório padrão para escolha de arquivos");
		txtEscolher.setBounds(10, 11, 292, 14);
		contentPane.add(txtEscolher);		
		JLabel txtRelatorio = new JLabel("Diretório padrão para salvar relatórios");
		txtRelatorio.setBounds(10, 61, 292, 14);
		contentPane.add(txtRelatorio);		
		JCheckBox linguaEN = new JCheckBox("English");

		linguaEN.setBounds(10, 189, 97, 23);
		contentPane.add(linguaEN);		
		JCheckBox linguaPT = new JCheckBox("Português");
		
		if(linguagemAtual == 1) {
		linguaPT.setSelected(true);}
		if(linguagemAtual == 2) {
		linguaEN.setSelected(true);}
		
		linguaPT.setBounds(10, 163, 97, 23);
		
		
		contentPane.add(linguaPT);		
		JButton botaoAplicar = new JButton("Aplicar");
		botaoAplicar.setFont(botaoAplicar.getFont().deriveFont(botaoAplicar.getFont().getStyle() | Font.BOLD));
		botaoAplicar.setBounds(254, 189, 89, 23);
		contentPane.add(botaoAplicar);		
		diretorioEscolha2 = new JTextField();
		diretorioEscolha2.setEditable(false);
		diretorioEscolha2.setColumns(10);
		diretorioEscolha2.setBounds(10, 28, 226, 20);
		contentPane.add(diretorioEscolha2);
		
		diretorioEscolha2.setText(s_prefDiretorioDados);
		diretorioRelatorio.setText(s_prefDiretorioRelatorio);
		
		JLabel labelIdioma = new JLabel("Idioma");
		labelIdioma.setBounds(10, 147, 72, 14);
		contentPane.add(labelIdioma);
		
		JCheckBox botaoDesabilitar = new JCheckBox("Desabilitar mensagem inicial");
		botaoDesabilitar.setBounds(10, 115, 268, 23);
		contentPane.add(botaoDesabilitar);
		
		if(linguagemAtual==2) {
			botaoSelecionarEscolher.setText("Select");
			botaoSelecionarRelatorio.setText("Select");
			setTitle("Preferences");
			txtEscolher.setText("Default directory to choose files");
			txtRelatorio.setText("Default directory to save reports");
			botaoAplicar.setText("Apply");
			labelIdioma.setText("Language");
			botaoDesabilitar.setText("Disable splash message");
		}
		
		int abrirJanelaFuncoes = Config.janelaInicial();
		if(abrirJanelaFuncoes == 2) {
				botaoDesabilitar.setSelected(true);
		}
		
		botaoSelecionarEscolher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				diretorioEscolha2.setText(Arquivo.escolherDiretorio().getPath());}catch(Exception e1) {}
				
			}
		});
				
		botaoSelecionarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				diretorioRelatorio.setText(Arquivo.escolherDiretorio().getPath());}catch(Exception e1) {}
			}
		});
		
		botaoAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File config = new File(new java.io.File(".").getPath()+"\\.config\\config.txt");
				int linguagemAtual = Config.idioma();				
				
				// PT = 1 ___ EN = 2
				int linguagem = 0;
					if(linguaPT.isSelected()) {
					linguagem = 1; 
					}
					if(linguaEN.isSelected()) {
					linguagem = 2; 
					}	
				
				String msgInicial = "1";
				 if(botaoDesabilitar.isSelected()) {
					 msgInicial = "2";
				 }
				
					
				String novaConfig = 
						linguagem + "\r\n"
						+ diretorioEscolha2.getText() + "\r\n"
						+ diretorioRelatorio.getText() + "\r\n"
						+ msgInicial;
						;			
				
				try {
				Arquivo.escreverArquivo(novaConfig, config);
				dispose();
				
				if(linguagemAtual == 1 && linguagem == 1) {
				    JOptionPane.showMessageDialog(null, "Novas preferências salvas com sucesso.");				    
				}
				
				if(linguagemAtual == 1 && linguagem == 2) {
				    JOptionPane.showMessageDialog(null, "New preferences have been saved. Restart the program to change the language.");				    
				}
				
				if(linguagemAtual == 2 && linguagem == 1) {
				    JOptionPane.showMessageDialog(null, "Novas preferências salvas. Reinicie o programa para alterar o idioma.");				    
				}
				
				if(linguagemAtual == 2 && linguagem == 2) {
				    JOptionPane.showMessageDialog(null, "The new preferences have been successfully saved.");
				}
				
				}catch(Exception e1) {
					if(linguagem == 1) {
					    JOptionPane.showMessageDialog(null, "Erro ocorrido.");
					}
					if(linguagem == 2) {
					    JOptionPane.showMessageDialog(null, "Error.");
					}
					
					
				}
				
				
			}
			
		});
		
		linguaEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linguaPT.setSelected(false);
				int linguagemAtual = Config.idioma();	
				
				if(linguagemAtual == 1) {
					//Aplique as alterações e reinicie o programa para trocar a linguagem
				    JOptionPane.showMessageDialog(null, "Apply the changes and restart the program to change the language.");
					
				}
				
			}
		});
		
		linguaPT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				linguaEN.setSelected(false);
				int linguagemAtual = Config.idioma();		
				
				if(linguagemAtual == 2) {
				    JOptionPane.showMessageDialog(null, "Aplique as mudanças e reinicie o programa para mudar a linguagem.");				
				}
				
			}
		});
		
	}
}
