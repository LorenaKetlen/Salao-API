package com.cefet.salao.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 6544624391764025672L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotEmpty(message = "Nome não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	private String nome;

	@Column(name = "email", nullable = false)
	@NotEmpty(message = "Email não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Email deve conter entre 3 e 200 caracteres.")
	private String email;

	@Column(name = "telefone", nullable = false)
	@NotEmpty(message = "Telefone não pode ser vazio.")
	@Length(min = 11, max = 11, message = "Telefone deve conter 11 caracteres.")
	private String telefone;

	@Column(name = "endereco", nullable = false)
	@NotEmpty(message = "Endereço não pode ser vazio")
	@Length(min = 10, max = 254, message = "Email deve conter entre 10 e 254 caracteres.")
	private String endereco;

	@Column(name = "senha", nullable = false)
	@NotEmpty(message = "Senha não pode ser vazia.")
	@Length(min = 3, max = 200, message = "Senha deve conter entre 3 e 200 caracteres.")
	private String senha;

	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;
}
