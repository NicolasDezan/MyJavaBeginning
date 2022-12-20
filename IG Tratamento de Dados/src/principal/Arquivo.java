package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Arquivo {
	public Arquivo() {}
	
	//Abre procurador de arquivos e retorna um arquivo ou diretório selecionado (File) - Pode-se determinar um diretorio inicial para abrir o explorador de arquivos
	public static File escolherArquivo(File f_diretorio_inicial){                                                      		
		JFileChooser chooser = new JFileChooser();	
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos .txt", "txt");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(filtro);
		
		chooser.setCurrentDirectory(f_diretorio_inicial);		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retorno = chooser.showOpenDialog(chooser);
		
		if(retorno == JFileChooser.APPROVE_OPTION) {
		File arq = chooser.getSelectedFile();				
		return arq;	
		}
		else {			
			return null;
		}
		
	}
	
	// Recebe arquivo .txt (File) e retorna uma lista String
	public static List<String> lerArquivo(File arq){             
		List<String> arquivo_lido = new ArrayList<String>();
		String linha = new String(); 
		
				
		
		try {
			FileReader leitorDeArquivo = new FileReader(arq);
			BufferedReader buffer = new BufferedReader(leitorDeArquivo);
						
			while(true) {
				linha = buffer.readLine();
				arquivo_lido.add (linha);
				if(linha == null) break;
			}
			
		}catch (Exception e){}	
		
		arquivo_lido.remove(arquivo_lido.size()-1);

		return arquivo_lido;
}
	
	
	public static void escreverArquivo(String texto, File _caminho) {
		// https://youtu.be/Kj5ibAHhv3M
		Path p_caminho = _caminho.toPath();						
		
		byte[] textoEmByte = texto.getBytes();	
		try {
		Files.write(p_caminho, textoEmByte);
		}catch(Exception erro) {}
				
	}
	
}
