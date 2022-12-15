package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Arquivo {
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
	
	public static void escreverArquivo(String texto, Path _caminho) {
		// https://youtu.be/Kj5ibAHhv3M
				
		byte[] textoEmByte = texto.getBytes();	
		try {
		Files.write(_caminho, textoEmByte);
		}catch(Exception erro) {}
				
	}
	
	public static String selecionarCaminho() {
		// https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java
		
		JFileChooser chooser = new JFileChooser();	
	    chooser.setDialogTitle("Salvar em ");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);	    	    	    		                                                                            
	
	  chooser.showOpenDialog(null);	 				
		String nome = JOptionPane.showInputDialog("Insira o nome do arquivo txt");
		//String nome_txt = 
		String caminho = chooser.getSelectedFile().getAbsolutePath()+"\\"+nome+".txt";;
		
		
		return caminho;	                                                                 		
	}
	
}
