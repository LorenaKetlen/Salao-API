package com.cefet.salao.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contas")
public class Conta implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "horario", nullable = false)
	private String horario;

	@Column(name = "formapgt", nullable = false)
	private String formapgt;

	@Column(name = "dataAgendamento", nullable = false)
	private Date dataAgendamento;

	@ManyToOne()
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne()
	@JoinColumn(name = "tipo_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Tipo tipo;
}
