package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Endereco {
	
	private String rua;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String numero;
	private String complemento;
	
	public Endereco() {
		
	}
	
	
	public Endereco(DadosEndereco dados) {
		this.rua = dados.rua();
		this.bairro = dados.bairro();
		this.cidade = dados.cidade();
		this.uf = dados.uf();
		this.numero = dados.numero();
		this.cep = dados.cep();
		this.complemento = dados.complemento();
	}

	public void atualizarInformacoes(DadosEndereco dados) {
		if (dados.rua() != null) {
			this.rua = dados.rua();
		}
		if (dados.bairro() != null) {
			this.bairro = dados.bairro();
		}
		if (dados.cidade() != null) {
			this.cidade = dados.cidade();
		}
		if (dados.uf() != null) {
			this.uf = dados.uf();
		}
		if (dados.cep() != null) {
			this.cep = dados.cep();
		}
		if (dados.numero() != null) {
			this.numero = dados.numero();
		}
		if (dados.numero() != null) {
			this.complemento = dados.complemento();
		}		
		
	}


	public String getRua() {
		return rua;
	}


	public String getBairro() {
		return bairro;
	}


	public String getCep() {
		return cep;
	}


	public String getCidade() {
		return cidade;
	}


	public String getUf() {
		return uf;
	}


	public String getNumero() {
		return numero;
	}


	public String getComplemento() {
		return complemento;
	}
	
	
	
	

}
