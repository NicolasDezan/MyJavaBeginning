package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculo {
	
	public Calculo() {}
		
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
	
	public static Float calcularIntervaloDeConfianca_X(Integer n_dados, Float desvpad, File endereco) {
		List<Float> score = new ArrayList<Float>();		
		
		Integer v = n_dados-2;

		String linha = new String();    
		
		try {
			FileReader leitorDeArquivo = new FileReader(endereco);
			try (BufferedReader buffer = new BufferedReader(leitorDeArquivo)) {
				while(true) {
					linha = buffer.readLine();
					linha.replace(',', '.');
					score.add(Float.parseFloat(linha));
				}
			}			
		}catch (Exception e){}	
		
		Float ic = (float) (score.get(v) * desvpad/Math.sqrt(n_dados));
				
		return ic;
	}
}
