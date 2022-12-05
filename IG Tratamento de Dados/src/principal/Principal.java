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

/*
 * IG Tratamento de Dados v 2.1
 * 04.12.2022
 * 
 * OBS: Intervalo de confiança em fase "Beta"
 * 
 * IFES Vila Velha
 * Nícolas Dezan dos Santos
 */

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
		
		JButton botaoEscolherArquivo = new JButton("Escolher arquivo");		
		
		botaoEscolherArquivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				File arq = null;
				List<Float> dados = new ArrayList();
				
				arq = escolherArquivo(arq);				
				lerArquivo(arq, dados);	
				
				arquivoMostrar2.setText(String.valueOf(arq));

				Float media = calcularMedia(dados);
				Float mediana = calcularMediana(dados);
				Float desvpad = calcularDesvioPadrao(dados, media);
				Float ic = calcularIntervaloDeConfianca(dados, desvpad);
				
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
			
				escreverArquivo(caminho, media, mediana, desvpad);
				
				
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
	
	public static List<Float> lerArquivo(File arq ,List<Float> dados){                                                      //importar arquivo do computador

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
	   // chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Salvar em ");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);	    	    
	    		                                                                            
	
	  chooser.showOpenDialog(null);
	  //if(retorno==JFileChooser.APPROVE_OPTION) {
	  //JOptionPane.showMessageDialog(null, chooser.getSelectedFile().getAbsolutePath());}
	  	
	 				
		
		caminho = chooser.getSelectedFile().getAbsolutePath();
		return caminho;	                                                                 		
	}

	public static void escreverArquivo(String caminho, String media, String mediana, String desvpad) {
		// https://youtu.be/Kj5ibAHhv3M
		
		String nome = JOptionPane.showInputDialog("Insira o nome do arquivo txt");
		String nome_txt = "\\"+nome+".txt";
		
		Path _caminho = Paths.get(caminho+nome_txt);

		String texto = 
		"A média dos valores é " + media + ".\r\n"
		+ "A mediana dos valores é " + mediana + ".\r\n"
		+ "O desvio padrão é " + desvpad + ".\r\n";
		
		byte[] textoEmByte = texto.getBytes();	
		try {
		Files.write(_caminho, textoEmByte);
		}catch(Exception erro) {}
				
	}
	
	public static Float calcularIntervaloDeConfianca(List<Float> dados, Float desvpad) {
		/* Calcular intervalo de confiança
		* Calculo de intervalo de confiança https://www.youtube.com/watch?v=j10HOYBXWrE
		* t_table https://www.sjsu.edu/faculty/gerstman/StatPrimer/t-table.pdf
		* 
		*/ 
		
		Integer v = dados.size()-2;
		
		Float[] t_95 = new Float[24];
			t_95[0] = 12.71f; t_95[1] = 4.303f; t_95[2] = 3.182f; t_95[3] = 2.776f; t_95[4] = 2.571f; t_95[5] = 2.447f; t_95[23]= 2.064f;
					
		Float ic = (float) (t_95[v] * desvpad/Math.sqrt(dados.size()));
				
		return ic;
	
	}
}
	
