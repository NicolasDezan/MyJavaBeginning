package principal;

import java.io.File;
import java.util.List;

public class Config {

	public Config() {}
	
	
	// 1 = PT ___ 2 = EN
	public static Integer idioma() {
		Integer linguagemAtual = 1;
		try {
			File diretorioPref = new File(new java.io.File(".").getPath()+"\\config\\config.txt");
			List<String> pref = Arquivo.lerArquivo(diretorioPref);
			linguagemAtual = Integer.valueOf(pref.get(0));		
			}catch(Exception semConfig) {}	
				
		return linguagemAtual;
	}
	
	// 1 = Abre janela sempre que o programa iniciar __ 2 = Não abre
	public static Integer janelaInicial() {
		Integer janelaInicial = 1;
		try {
			File diretorioPref = new File(new java.io.File(".").getPath()+"\\config\\config.txt");
			List<String> pref = Arquivo.lerArquivo(diretorioPref);
			janelaInicial = Integer.valueOf(pref.get(3));		
			}catch(Exception semConfig) {}	
				
		return janelaInicial;
	}
}
