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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
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
	private JTextField numeroSoma1;
	private JTextField numeroSoma2;
	
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
		frame.setBounds(100, 100, 593, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JRadioButton selecionar_99 = new JRadioButton("99%");
		JRadioButton selecionar_95 = new JRadioButton("95%");
		JButton botaoEscolherArquivo = new JButton("Escolher arquivo");
		JLabel n_dados_retorno = new JLabel("n = ");
		JLabel somaTexto = new JLabel("Resultado: ");
		JLabel lblNewLabel = new JLabel("+");
		JButton botaoGerarRelatorio = new JButton("Gerar relatorio");
		JLabel arquivo = new JLabel("Arquivo:");
		JLabel desvpad = new JLabel("Desvio Padrão");
		JLabel mediana = new JLabel("Mediana");
		textoSalvoEm = new JLabel("Salvo em:");
		JLabel ic_texto = new JLabel("Intervalo de Confiança");
		JButton botaoSoma = new JButton("Somar");


		
	botaoEscolherArquivo.setBounds(27, 10, 167, 34);
	frame.getContentPane().add(botaoEscolherArquivo);	
	mediaMostrar = new JTextField();
	mediaMostrar.setBounds(118, 51, 76, 19);
	frame.getContentPane().add(mediaMostrar);
	mediaMostrar.setColumns(10);	
	media = new JLabel("Média");
	media.setBounds(27, 55, 40, 13);
	frame.getContentPane().add(media);	
	mediana.setBounds(27, 79, 57, 13);
	frame.getContentPane().add(mediana);	
	medianaMostrar = new JTextField();
	medianaMostrar.setColumns(10);
	medianaMostrar.setBounds(118, 75, 76, 19);
	frame.getContentPane().add(medianaMostrar);	
	desvpad.setBounds(27, 103, 98, 13);
	frame.getContentPane().add(desvpad);	
	desvpadMostrar = new JTextField();
	desvpadMostrar.setColumns(10);
	desvpadMostrar.setBounds(118, 99, 76, 19);
	frame.getContentPane().add(desvpadMostrar);		
	arquivo.setBounds(204, 28, 46, 14);
	frame.getContentPane().add(arquivo);	
	arquivoMostrar2 = new JTextField();
	arquivoMostrar2.setText("(nenhum arquivo selecionado)");
	arquivoMostrar2.setFont(new Font("Tahoma", Font.PLAIN, 9));
	arquivoMostrar2.setBounds(255, 27, 255, 17);
	frame.getContentPane().add(arquivoMostrar2);
	arquivoMostrar2.setColumns(10);
	frame.getContentPane().add(botaoGerarRelatorio);	
	textoSalvoEm.setBounds(27, 188, 57, 14);
	frame.getContentPane().add(textoSalvoEm);	
	caminhoMostrar = new JTextField();
	caminhoMostrar.setFont(new Font("Tahoma", Font.PLAIN, 9));
	caminhoMostrar.setColumns(10);
	caminhoMostrar.setBounds(91, 187, 255, 17);
	frame.getContentPane().add(caminhoMostrar);	
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
	selecionar_95.setSelected(true);
	selecionar_95.setFont(new Font("Tahoma", Font.PLAIN, 8));
	selecionar_95.setBounds(237, 75, 57, 23);
	frame.getContentPane().add(selecionar_95);
	botaoGerarRelatorio.setBounds(27, 142, 167, 34);
	selecionar_99.setFont(new Font("Tahoma", Font.PLAIN, 8));
	selecionar_99.setBounds(296, 74, 57, 23);
	frame.getContentPane().add(selecionar_99);	
	n_dados_retorno.setFont(new Font("Tahoma", Font.PLAIN, 8));
	n_dados_retorno.setBounds(390, 115, 46, 14);
	frame.getContentPane().add(n_dados_retorno);
	botaoSoma.setBounds(417, 184, 104, 23);
	frame.getContentPane().add(botaoSoma);	
	numeroSoma1 = new JTextField();
	numeroSoma1.setBounds(417, 218, 41, 20);
	frame.getContentPane().add(numeroSoma1);
	numeroSoma1.setColumns(10);	
	numeroSoma2 = new JTextField();
	numeroSoma2.setColumns(10);
	numeroSoma2.setBounds(480, 218, 41, 20);
	frame.getContentPane().add(numeroSoma2);	
	lblNewLabel.setBounds(464, 221, 21, 14);
	frame.getContentPane().add(lblNewLabel);	
	somaTexto.setBounds(417, 249, 119, 14);
	frame.getContentPane().add(somaTexto);
			
		botaoEscolherArquivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				File arq = null;
				List<Float> dados = new ArrayList();
				
				arq = Arquivo.escolherArquivo(arq);				
				Arquivo.lerArquivo(arq, dados);	
				
				arquivoMostrar2.setText(String.valueOf(arq));
								
				n_dados_retorno.setText("n = "+ String.valueOf(dados.size()));
				Integer n_dados = dados.size();
				
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
				
			}
		});
		
		botaoGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String caminho = Arquivo.selecionarCaminho();
				
				
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
				
				
				Float intervalo_menor = Float.parseFloat(media.replace(',','.'))-Float.parseFloat(ic.replace(',', '.'));
				Float intervalo_maior = Float.parseFloat(media.replace(',','.'))+Float.parseFloat(ic.replace(',', '.'));
				
				String texto = 
						"A média dos " + n_dados + " dados fornecidos está entre " + String.valueOf(intervalo_menor) + " e " + String.valueOf(intervalo_maior) + " com " + confianca + " de confiança." + "\r\n" +
						"\r\n"	+
						"Resultado = ( " + media + " ± " + ic + " )" + "\r\n" + "\r\n" +
						"...................................." + "\r\n" +
						"\r\n"	+
						"Média aritimética = " + media + "\r\n" +
						"Desvio padrão = " + desvpad + "\r\n" +
						"Mediana = " + mediana + "\r\n";
				
				Arquivo.escreverArquivo(texto, Paths.get(caminho));
			}
		});
		
		selecionar_95.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			aviso.setText("T_Student - 95%");
			selecionar_99.setSelected(false);
			
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
				
		botaoSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				somaTexto.setText(
						"Resultado : " +
						String.valueOf(Calculo.somar(Float.parseFloat(numeroSoma1.getText()), Float.parseFloat(numeroSoma2.getText())))		
						);
				
				
			}
		});

		
	}		
	



	
}
	
