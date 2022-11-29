package principal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class Principal {
	private JFrame frame;
	private JTextField mediaMostrar;
	private JLabel media;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton botaoEscolherArquivo = new JButton("Escolher arquivo");		
		
		botaoEscolherArquivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				File arq = null;
				List<Float> dados = new ArrayList();
				Float media = null;
				Float mediana_par = null;
				Float mediana_impar = null;
				Float mediana = null;

				
				arq = escolherArquivo(arq);
				lerArquivo(arq, dados);	
				media = calcularMedia(dados);
				mediana = calcularMediana(dados, mediana_par, mediana_impar);
				
				System.out.println(mediana_impar);
				System.out.println(mediana_par);
				System.out.println(mediana);

				

			}
		});
			
		botaoEscolherArquivo.setBounds(10, 10, 143, 34);
		frame.getContentPane().add(botaoEscolherArquivo);
		
		mediaMostrar = new JTextField();
		mediaMostrar.setBounds(77, 54, 76, 19);
		frame.getContentPane().add(mediaMostrar);
		mediaMostrar.setColumns(10);
		
		media = new JLabel("Média");
		media.setBounds(30, 57, 34, 13);
		frame.getContentPane().add(media);
		
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
				dados.add (Float.parseFloat(linha));
				if(linha == null) {break;}
				}}catch (Exception e){}			

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
	public static float calcularMediana(List<Float> dados, Float mediana_impar, Float mediana_par) {
	    Collections.sort(dados);
	    int n = dados.size();		
	    int p1 = n/2;
	    if (n % 2 == 0) {                       //dados.size é um numero par		    	
	    	int p2 = n/2+1;
	    	Float mediana_0 = dados.get(p1-1) + dados.get(p2-1);	
	    	mediana_par = mediana_0/2;
	    	return mediana_par;
	    }
	    else{                                    //dados.size é impar
	    	mediana_impar = dados.get(p1);
	    	return mediana_impar;
	    }
	}


}
