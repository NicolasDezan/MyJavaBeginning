/*
 * IG Tratamento de Dados v 2.3
 * 14.12.2022
 * 
 * IFES Vila Velha
 * Nícolas Dezan dos Santos
 * 
 * Adições a fazer: permitir a escolha de Z-Score ou T-Student para qualquer nível de confiança
 */

package principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.SwingConstants;


public class Principal {
	private JFrame frame;
	private JTextField mediaMostrar;
	private JLabel media;
	private JTextField medianaMostrar;
	private JTextField desvpadMostrar;
	private JTextField arquivoMostrar2;
	private JLabel textoSalvoEm;
	private JTextField caminhoMostrar;
	private JTextField icMostrar;
	private JLabel aviso;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Principal() {
		initialize();
	}
	
	private void initialize() {
		//iniciar as preferências do usuário
		String s_prefDiretorioDados = null;
		String s_prefDiretorioRelatorio = null;
		
		try {
			File diretorioPref = new File(new java.io.File(".").getPath()+"\\.config\\config.txt");
			List<String> pref = Arquivo.lerArquivo(diretorioPref);	
				s_prefDiretorioDados = new File(pref.get(1)).getPath();
				s_prefDiretorioRelatorio = new File(pref.get(2)).getPath();			
			}catch(Exception semConfig) {}		
		File prefDiretorioDados = new File(s_prefDiretorioDados);
		File prefDiretorioRelatorio = new File(s_prefDiretorioRelatorio);	
		
		JMenuItem botaoMenuPref = new JMenuItem("Preferências");
		frame = new JFrame();
		frame.setBounds(100, 100, 416, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JRadioButton selecionar_99 = new JRadioButton("99%");
		selecionar_99.setBounds(86, 123, 57, 23);
		JRadioButton selecionar_95 = new JRadioButton("95%");
		selecionar_95.setBounds(27, 124, 57, 23);
		JLabel n_dados_retorno = new JLabel("n = ");
		n_dados_retorno.setBounds(213, 170, 46, 14);
		JLabel texto_arquivo = new JLabel("Arquivo:");
		texto_arquivo.setBounds(27, 26, 46, 14);
		JLabel desvpad = new JLabel("Desvio Padrão");
		desvpad.setBounds(27, 103, 98, 13);
		JLabel mediana = new JLabel("Mediana");
		mediana.setBounds(27, 79, 57, 13);
		textoSalvoEm = new JLabel("Salvo em:");
		textoSalvoEm.setBounds(27, 195, 57, 14);
		JLabel ic_texto = new JLabel("Intervalo de Confiança");
		ic_texto.setBounds(27, 156, 142, 13);
		JRadioButton selecionar_X = new JRadioButton("X");
		selecionar_X.setBounds(143, 123, 57, 23);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuOpcoes = new JMenu("Opções");
		JMenuItem botaoMenuSelecionarArquivo = new JMenuItem("Selecionar arquivo");
		botaoMenuSelecionarArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenuItem botaoMenuGerarRelatorio = new JMenuItem("Gerar relatorio");
		botaoMenuGerarRelatorio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

	frame.getContentPane().setLayout(null);
	mediaMostrar = new JTextField();
	mediaMostrar.setBounds(118, 51, 76, 19);
	frame.getContentPane().add(mediaMostrar);
	mediaMostrar.setColumns(10);	
	media = new JLabel("Média");
	media.setBounds(27, 55, 40, 13);
	frame.getContentPane().add(media);
	frame.getContentPane().add(mediana);	
	medianaMostrar = new JTextField();
	medianaMostrar.setBounds(118, 75, 76, 19);
	medianaMostrar.setColumns(10);
	frame.getContentPane().add(medianaMostrar);
	frame.getContentPane().add(desvpad);	
	desvpadMostrar = new JTextField();
	desvpadMostrar.setBounds(118, 99, 76, 19);
	desvpadMostrar.setColumns(10);
	frame.getContentPane().add(desvpadMostrar);
	frame.getContentPane().add(texto_arquivo);	
	arquivoMostrar2 = new JTextField();
	arquivoMostrar2.setBounds(78, 25, 255, 17);
	arquivoMostrar2.setText("(nenhum arquivo selecionado)");
	arquivoMostrar2.setFont(new Font("Tahoma", Font.PLAIN, 9));
	frame.getContentPane().add(arquivoMostrar2);
	arquivoMostrar2.setColumns(10);
	frame.getContentPane().add(textoSalvoEm);	
	selecionar_X.setFont(new Font("Tahoma", Font.PLAIN, 8));
	caminhoMostrar = new JTextField();
	caminhoMostrar.setBounds(91, 194, 255, 17);
	caminhoMostrar.setFont(new Font("Tahoma", Font.PLAIN, 9));
	caminhoMostrar.setColumns(10);
	frame.getContentPane().add(caminhoMostrar);
	frame.getContentPane().add(ic_texto);	
	icMostrar = new JTextField();
	icMostrar.setBounds(157, 154, 76, 19);
	icMostrar.setColumns(10);
	frame.getContentPane().add(icMostrar);	
	aviso = new JLabel("T_Student - 95%");
	aviso.setBounds(81, 170, 88, 13);
	aviso.setFont(new Font("Tahoma", Font.PLAIN, 9));
	frame.getContentPane().add(aviso);
	selecionar_95.setSelected(true);
	selecionar_95.setFont(new Font("Tahoma", Font.PLAIN, 8));
	frame.getContentPane().add(selecionar_95);
	selecionar_99.setFont(new Font("Tahoma", Font.PLAIN, 8));
	frame.getContentPane().add(selecionar_99);	
	n_dados_retorno.setFont(new Font("Tahoma", Font.PLAIN, 8));
	frame.getContentPane().add(n_dados_retorno);
	frame.getContentPane().add(selecionar_X);
	frame.setJMenuBar(menuBar);	
	botaoMenuGerarRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
	menuArquivo.add(botaoMenuSelecionarArquivo);
	menuArquivo.add(botaoMenuGerarRelatorio);	
	menuBar.add(menuArquivo);
	menuBar.add(menuOpcoes);	
	botaoMenuPref.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
	menuOpcoes.add(botaoMenuPref);
		
	
		botaoMenuSelecionarArquivo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
						
			File arq = Arquivo.escolherArquivo(prefDiretorioDados);
			
			if(arq != null) {try {
				
			List<String> s_dados = Arquivo.lerArquivo(arq);	
			List<Float> dados = new ArrayList<Float>();
			
			
			for(Integer n = 0; n < s_dados.size();) {

				dados.add(Float.parseFloat(s_dados.get(n).replace(',', '.')));
				n += 1;
			}
											
			
			arquivoMostrar2.setText(String.valueOf(arq));
							
			n_dados_retorno.setText("n = "+ String.valueOf(s_dados.size()));
			Integer n_dados = s_dados.size();
			
			Float media = Calculo.calcularMedia(dados);
			Float mediana = Calculo.calcularMediana(dados);
			Float desvpad = Calculo.calcularDesvioPadrao(dados, media);
			Float ic = (float) 0.1;
			
			
			if(selecionar_95.isSelected()) {
				ic = Calculo.calcularIntervaloDeConfianca_95(n_dados, desvpad);
			}
			if(selecionar_99.isSelected()) {
				ic = Calculo.calcularIntervaloDeConfianca_99(n_dados, desvpad);
			}
			
			mediaMostrar.setText(String.valueOf(media).replace('.', ','));
			medianaMostrar.setText(String.valueOf(mediana).replace('.', ','));
			desvpadMostrar.setText(String.valueOf(desvpad).replace('.', ','));
			icMostrar.setText(String.valueOf(ic).replace('.', ','));
			
			}catch(Exception ee) {
				arquivoMostrar2.setText("O arquivo selecionado não pode ser lido.");
			}
			
			}
			
		}
	});
		
		botaoMenuGerarRelatorio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ic = icMostrar.getText();
												
						File caminho = Arquivo.escolherArquivo(prefDiretorioRelatorio);												
						
						String media = mediaMostrar.getText();
						String mediana = medianaMostrar.getText();
						String desvpad = desvpadMostrar.getText();
						String confianca = null;
						Float intervalo_menor = 0f;
						Float intervalo_maior = 0f;

						if(selecionar_95.isSelected()) {
							confianca = "95%"; 
						}
						if(selecionar_99.isSelected()) {
							confianca = "99%"; 
						}		
						String n_dados = n_dados_retorno.getText().substring(4);				
						
						
						try {
						intervalo_menor = Float.parseFloat(media.replace(',','.'))-Float.parseFloat(ic.replace(',', '.'));
						intervalo_maior = Float.parseFloat(media.replace(',','.'))+Float.parseFloat(ic.replace(',', '.'));
						
						
						String texto = 
								"A média dos " + n_dados + " dados fornecidos está entre " + String.valueOf(intervalo_menor) + " e " + String.valueOf(intervalo_maior) + " com " + confianca + " de confiança." + "\r\n" +
								"\r\n"	+
								"Resultado = ( " + media + " ± " + ic + " )" + "\r\n" + "\r\n" +
								"...................................." + "\r\n" +
								"\r\n"	+
								"Média aritimética = " + media + "\r\n" +
								"Desvio padrão = " + desvpad + "\r\n" +
								"Mediana = " + mediana + "\r\n";
						
						String _caminho = caminho+".txt";
						File path = new File(_caminho);
						caminhoMostrar.setText(String.valueOf(caminho)+".txt");

						Arquivo.escreverArquivo(texto, path);
					
					}catch(Exception erro3){
						caminhoMostrar.setText("Não foi possível gerar o relatório.");
					}
					
						
				}
				});
				
		selecionar_95.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			aviso.setText("T_Student - 95%");
			selecionar_99.setSelected(false);
			selecionar_X.setSelected(false);

			
			try {
			Float ic = null;
			Float desvpad = Float.parseFloat(desvpadMostrar.getText().replace(',', '.'));
			String n_dados_string = n_dados_retorno.getText();
			Integer n_dados = Integer.parseInt(n_dados_string.substring(4)); // "n = "
			
			ic = Calculo.calcularIntervaloDeConfianca_95(n_dados, desvpad);
			icMostrar.setText(String.valueOf(ic).replace('.', ','));
			}catch(Exception erro) {}
			
		}
		
		
	});
		
		selecionar_99.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aviso.setText("T_Student - 99%");
				selecionar_95.setSelected(false);
				selecionar_X.setSelected(false);

				try {
				Float ic = null;
				Float desvpad = Float.parseFloat(desvpadMostrar.getText().replace(',', '.'));
				String n_dados_string = n_dados_retorno.getText();
				Integer n_dados = Integer.parseInt(n_dados_string.substring(4)); // "n = "
				
				ic = Calculo.calcularIntervaloDeConfianca_99(n_dados, desvpad);
				icMostrar.setText(String.valueOf(ic).replace('.', ','));
				}catch(Exception erro) {}
				
				
			}
		});
		
		selecionar_X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aviso.setText("T_Student - X");
				selecionar_99.setSelected(false);
				selecionar_95.setSelected(false);
				
				try {
				Float ic = null;
				Float desvpad = Float.parseFloat(desvpadMostrar.getText().replace(',', '.'));
				String n_dados_string = n_dados_retorno.getText();
				Integer n_dados = Integer.parseInt(n_dados_string.substring(4)); // "n = "
				
				ic = Calculo.calcularIntervaloDeConfianca_X(n_dados, desvpad);
				icMostrar.setText(String.valueOf(ic).replace('.', ','));
				}catch(Exception erro) {}
				
				
			}
		});
		
		botaoMenuPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		
		
		
		
	}
}
	
