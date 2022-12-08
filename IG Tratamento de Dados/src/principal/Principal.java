/*
 * IG Tratamento de Dados v 2.2
 * 04.12.2022
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;


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
		frame = new JFrame();
		frame.setBounds(100, 100, 536, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JRadioButton selecionar_99 = new JRadioButton("99%");
		JRadioButton selecionar_95 = new JRadioButton("95%");
		JButton botaoEscolherArquivo = new JButton("Escolher arquivo");
		JLabel n_dados_retorno = new JLabel("n = ");

		
		botaoEscolherArquivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				File arq = null;
				List<Float> dados = new ArrayList();
				
				arq = escolherArquivo(arq);				
				lerArquivo(arq, dados);	
				
				arquivoMostrar2.setText(String.valueOf(arq));
								
				n_dados_retorno.setText("n = "+ String.valueOf(dados.size()));
				Integer n_dados = dados.size();
				
				Float media = calcularMedia(dados);
				Float mediana = calcularMediana(dados);
				Float desvpad = calcularDesvioPadrao(dados, media);
				Float ic = (float) 0.1;
				
				if(selecionar_95.isSelected()) {
					ic = calcularIntervaloDeConfianca_95(n_dados, desvpad);
				}
				if(selecionar_99.isSelected()) {
					ic = calcularIntervaloDeConfianca_99(n_dados, desvpad);
				}
				
				mediaMostrar.setText(String.valueOf(media).replace('.', ','));
				medianaMostrar.setText(String.valueOf(mediana).replace('.', ','));
				desvpadMostrar.setText(String.valueOf(desvpad).replace('.', ','));
				icMostrar.setText(String.valueOf(ic).replace('.', ','));
				
			}
		});
			
		botaoEscolherArquivo.setBounds(27, 10, 167, 34);
		frame.getContentPane().add(botaoEscolherArquivo);
		
		mediaMostrar = new JTextField();
		mediaMostrar.setBounds(118, 51, 76, 19);
		frame.getContentPane().add(mediaMostrar);
		mediaMostrar.setColumns(10);
		
		media = new JLabel("Média");
		media.setBounds(27, 55, 40, 13);
		frame.getContentPane().add(media);
		
		JLabel mediana = new JLabel("Mediana");
		mediana.setBounds(27, 79, 57, 13);
		frame.getContentPane().add(mediana);
		
		medianaMostrar = new JTextField();
		medianaMostrar.setColumns(10);
		medianaMostrar.setBounds(118, 75, 76, 19);
		frame.getContentPane().add(medianaMostrar);
		
		JLabel desvpad = new JLabel("Desvio Padrão");
		desvpad.setBounds(27, 103, 98, 13);
		frame.getContentPane().add(desvpad);
		
		desvpadMostrar = new JTextField();
		desvpadMostrar.setColumns(10);
		desvpadMostrar.setBounds(118, 99, 76, 19);
		frame.getContentPane().add(desvpadMostrar);
		
		JLabel arquivo = new JLabel("Arquivo:");
		arquivo.setBounds(204, 28, 46, 14);
		frame.getContentPane().add(arquivo);
		
		arquivoMostrar2 = new JTextField();
		arquivoMostrar2.setText("(nenhum arquivo selecionado)");
		arquivoMostrar2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		arquivoMostrar2.setBounds(255, 27, 255, 17);
		frame.getContentPane().add(arquivoMostrar2);
		arquivoMostrar2.setColumns(10);
		
		JButton botaoGerarRelatorio = new JButton("Gerar relatorio");
		botaoGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String caminho = null;
				caminho = selecionarCaminho(caminho);
				caminhoMostrar.setText(String.valueOf(caminho));
				String media = mediaMostrar.getText();
				String mediana = medianaMostrar.getText();
				String desvpad = desvpadMostrar.getText();
				String ic = icMostrar.getText();
				String confianca = null;
				if(selecionar_95.isSelected()) {
					confianca = "95%"; 
				}
				if(selecionar_99.isSelected()) {
					confianca = "99%"; 
				}		
				String n_dados = n_dados_retorno.getText().substring(4);

				escreverArquivo(caminho, media, mediana, desvpad, ic, confianca, n_dados);
				
				
			}
		});
		botaoGerarRelatorio.setBounds(27, 142, 167, 34);
		frame.getContentPane().add(botaoGerarRelatorio);
		
		textoSalvoEm = new JLabel("Salvo em:");
		textoSalvoEm.setBounds(27, 188, 57, 14);
		frame.getContentPane().add(textoSalvoEm);
		
		caminhoMostrar = new JTextField();
		caminhoMostrar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		caminhoMostrar.setColumns(10);
		caminhoMostrar.setBounds(91, 187, 255, 17);
		frame.getContentPane().add(caminhoMostrar);
		
		JLabel ic_texto = new JLabel("Intervalo de Confiança");
		ic_texto.setBounds(204, 101, 142, 13);
		frame.getContentPane().add(ic_texto);
		
		icMostrar = new JTextField();
		icMostrar.setColumns(10);
		icMostrar.setBounds(334, 99, 76, 19);
		frame.getContentPane().add(icMostrar);
		
		aviso = new JLabel("T_Student - 95%");
		aviso.setFont(new Font("Tahoma", Font.PLAIN, 9));
		aviso.setBounds(258, 115, 88, 13);
		frame.getContentPane().add(aviso);
		selecionar_95.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aviso.setText("T_Student - 95%");
				selecionar_99.setSelected(false);
				
				try {
				Float ic = null;
				Float desvpad = Float.parseFloat(desvpadMostrar.getText().replace(',', '.'));
				String n_dados_string = n_dados_retorno.getText();
				Integer n_dados = Integer.parseInt(n_dados_string.substring(4)); // "n = "
				
				ic = calcularIntervaloDeConfianca_95(n_dados, desvpad);
				icMostrar.setText(String.valueOf(ic).replace('.', ','));
				}catch(Exception erro) {}
				
			}
			
			
		});
		selecionar_95.setSelected(true);
		selecionar_95.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionar_95.setBounds(237, 75, 57, 23);
		frame.getContentPane().add(selecionar_95);
		

		selecionar_99.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aviso.setText("T_Student - 99%");
				selecionar_95.setSelected(false);
				try {
				Float ic = null;
				Float desvpad = Float.parseFloat(desvpadMostrar.getText().replace(',', '.'));
				String n_dados_string = n_dados_retorno.getText();
				Integer n_dados = Integer.parseInt(n_dados_string.substring(4)); // "n = "
				
				ic = calcularIntervaloDeConfianca_99(n_dados, desvpad);
				icMostrar.setText(String.valueOf(ic).replace('.', ','));
				}catch(Exception erro) {}
				
				
			}
		});
		selecionar_99.setFont(new Font("Tahoma", Font.PLAIN, 8));
		selecionar_99.setBounds(296, 74, 57, 23);
		frame.getContentPane().add(selecionar_99);
		
		n_dados_retorno.setFont(new Font("Tahoma", Font.PLAIN, 8));
		n_dados_retorno.setBounds(390, 115, 46, 14);
		frame.getContentPane().add(n_dados_retorno);
		
	}
	
	public static File escolherArquivo(File arq){                                                      //importar arquivo do computador
        // importar dados numéricos de um arquivo .txt -> https://www.youtube.com/watch?v=YHV44ZVgab8&t=34s		
		
		JFileChooser chooser = new JFileChooser();			                                                                  //escolher arquivo: https://www.youtube.com/watch?v=1bE0vmWqd94
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione um arquivo txt","txt");                                      //opcional: determinar o tipo de arquivo 
		chooser.setFileFilter(filter);                                                                                                      //setar o filtro				
		int retorno = chooser.showOpenDialog(null);                                                                             //mandando abrir a janela, o arquivo que for selecionado, irá para a variavel "retorno"

		if(retorno==JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, chooser.getSelectedFile().getAbsolutePath());
		}
		
		arq = chooser.getSelectedFile();
		return arq;
		
	}
	
	public static List<Float> lerArquivo(File arq ,List<Float> dados){             
		// Recebe arquivo .txt (File) e retorna uma lista de dados

		String linha = new String();                                                                                 //vai receber o conjunto de dados em forma de texto-String
		try {
			FileReader leitorDeArquivo = new FileReader(arq);
			BufferedReader buffer = new BufferedReader(leitorDeArquivo);
			
			
			while(true) {
				linha = buffer.readLine();
				linha = linha.replace(',', '.');               // Converte dados com "," para os tornar compatíveis com float
				dados.add (Float.parseFloat(linha));
				if(linha == null) {break;}
			}
			
		}catch (Exception e){
			
					
		}			

		return dados;
}
	
	public static float calcularMedia(List<Float> dados) {		
		float r = 0.0f;
	    for (Float dado : dados) {
	    	r += dado;
	    }
	    float media = r/dados.size();
	    return media;
	}
	
	public static float calcularMediana(List<Float> dados) {
	    Collections.sort(dados);
	    int n = dados.size();		
	    int p1 = n/2;
	    if (n % 2 == 0) {                       //dados.size é um numero par		    	
	    	int p2 = n/2+1;
	    	Float mediana_0 = dados.get(p1-1) + dados.get(p2-1);	
	    	Float mediana_par = mediana_0/2;
	    	return mediana_par;
	    }
	    else{                                    //dados.size é impar
	    	Float mediana_impar = dados.get(p1);
	    	return mediana_impar;
	    }
	}
	
	public static float calcularDesvioPadrao(List<Float> dados, float media) {
	    float d = 0.0f;
	    for (Float n : dados) {
	    	d += Math.pow(n-media, 2);
	    }
	    float var = d/(dados.size()-1);
	    float desvpad = (float) Math.sqrt(var);
	    
	    return desvpad;	   	    
	}
	
	public static String selecionarCaminho(String caminho) {
		// https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java
		
		JFileChooser chooser = new JFileChooser();	
	    chooser.setDialogTitle("Salvar em ");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);	    	    	    		                                                                            
	
	  chooser.showOpenDialog(null);	 				
		
		caminho = chooser.getSelectedFile().getAbsolutePath();
		return caminho;	                                                                 		
	}

	public static void escreverArquivo(String caminho, String media, String mediana, String desvpad, String ic, String confianca, String n_dados) {
		// https://youtu.be/Kj5ibAHhv3M
		
		String nome = JOptionPane.showInputDialog("Insira o nome do arquivo txt");
		String nome_txt = "\\"+nome+".txt";
		
		Float intervalo_menor = Float.parseFloat(media.replace(',','.'))-Float.parseFloat(ic.replace(',', '.'));
		Float intervalo_maior = Float.parseFloat(media.replace(',','.'))+Float.parseFloat(ic.replace(',', '.'));

		Path _caminho = Paths.get(caminho+nome_txt);

		String texto = //".\r\n"
		"A média dos " + n_dados + " dados fornecidos está entre " + String.valueOf(intervalo_menor) + " e " + String.valueOf(intervalo_maior) + " com " + confianca + " de confiança." + "\r\n" +
		"\r\n"	+
		"Resultado = ( " + media + " ± " + ic + " )" + "\r\n" + "\r\n" +
		"...................................." + "\r\n" +
		"\r\n"	+
		"Média aritimética = " + media + "\r\n" +
		"Desvio padrão = " + desvpad + "\r\n" +
		"Mediana = " + mediana + "\r\n";
		
		byte[] textoEmByte = texto.getBytes();	
		try {
		Files.write(_caminho, textoEmByte);
		}catch(Exception erro) {}
				
	}
	
	public static Float calcularIntervaloDeConfianca_95(Integer n_dados, Float desvpad) {
		/* Calcular intervalo de confiança
		* Calculo de intervalo de confiança https://www.youtube.com/watch?v=j10HOYBXWrE
		* t_table https://www.sjsu.edu/faculty/gerstman/StatPrimer/t-table.pdf
		* 
		*/ 
		
		Integer v = n_dados-2;
		
		Float[] t_95 = new Float[29];
			t_95[0] = 12.71f; 
			t_95[1] = 4.303f; 
			t_95[2] = 3.182f; 
			t_95[3] = 2.776f; 
			t_95[4] = 2.571f; 
			t_95[5] = 2.447f; 
			t_95[6] = 2.365f; 
			t_95[7] = 2.306f; 
			t_95[8] = 2.262f; 
			t_95[9] = 2.228f; 
			t_95[10] = 2.201f; 
			t_95[11] = 2.179f; 
			t_95[12] = 2.160f; 
			t_95[13] = 2.145f;
			t_95[14] = 2.131f; 
			t_95[15] = 2.120f; 
			t_95[16] = 2.110f; 
			t_95[17] = 2.101f; 
			t_95[18] = 2.093f; 
			t_95[19] = 2.086f; 
			t_95[20] = 2.080f; 
			t_95[21] = 2.074f; 
			t_95[22] = 2.069f; 
			t_95[23] = 2.064f; 
			t_95[24] = 2.060f; 
			t_95[25] = 2.056f; 
			t_95[26] = 2.052f; 
			t_95[27] = 2.048f; 
			t_95[28] = 2.045f;
			
					
					
		Float ic = (float) (t_95[v] * desvpad/Math.sqrt(n_dados));
				
		return ic;
	
	}
	
	public static Float calcularIntervaloDeConfianca_99(Integer n_dados, Float desvpad) {
		/* Calcular intervalo de confiança 99%
		* t_table https://www.sjsu.edu/faculty/gerstman/StatPrimer/t-table.pdf
		*/ 
		
		Integer v = n_dados-2;
		
		Float[] t_99 = new Float[29];
			t_99[0] = (float) 63.66;
			t_99[1] = (float) 9.925;
			t_99[2] = (float) 5.841;
			t_99[3] = (float) 4.604;		
			t_99[4] = (float) 4.032;
			t_99[5] = (float) 3.707;
			t_99[6] = (float) 3.499;
			t_99[7] = (float) 3.355;
			t_99[8] = (float) 3.250;
			t_99[9] = (float) 3.169;
			t_99[10] = (float) 3.106;
			t_99[11] = (float) 3.055;
			t_99[12] = (float) 3.012;
			t_99[13] = (float) 2.977;
			t_99[14] = (float) 2.947;
			t_99[15] = (float) 2.921;
			t_99[16] = (float) 2.898;
			t_99[17] = (float) 2.878;
			t_99[18] = (float) 2.861;
			t_99[19] = (float) 2.845;
			t_99[20] = (float) 2.831;
			t_99[21] = (float) 2.819;
			t_99[22] = (float) 2.807;
			t_99[23] = (float) 2.797;
			t_99[24] = (float) 2.787;
			t_99[25] = (float) 2.779;
			t_99[26] = (float) 2.771;
			t_99[27] = (float) 2.763;
			t_99[28] = (float) 2.756;
					
		Float ic = (float) (t_99[v] * desvpad/Math.sqrt(n_dados));
				
		return ic;
	
	}
}
	
