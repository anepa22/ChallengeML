package com.anepanet.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class DiaSistemaSolar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer dia;
	@Column
	private Integer corPolP1;
	@Column
	private Integer disPolP1;
	@Column
	private Double corRecXP1;
	@Column
	private Double corRecYP1;

	@Column
	private Integer corPolP2;
	@Column
	private Integer disPolP2;
	@Column
	private Double corRecXP2;
	@Column
	private Double corRecYP2;
	
	@Column
	private Integer corPolP3;
	@Column
	private Integer disPolP3;
	@Column
	private Double corRecXP3;
	@Column
	private Double corRecYP3;
	
	@Column
	private Double distP1eP2;
	@Column
	private Double distP2eP3;
	@Column
	private Double distP3eP1;
	
	@Column
	private Double pendEP1P2;
	@Column
	private Double pendEP2P3;
	@Column
	private Double pendEP3P1;
	
	@Column
	private Double pendEP1Sol;
	@Column
	private Double pendEP2Sol;
	@Column
	private Double pendEP3Sol;
	
	@Column
	private boolean solDentroDelTriangulo;
}
