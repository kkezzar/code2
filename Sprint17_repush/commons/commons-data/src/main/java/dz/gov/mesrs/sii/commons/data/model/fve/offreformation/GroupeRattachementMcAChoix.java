package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Permet de regrouper des rattachements de matières constitutives au sien d'une
 * même unité d'enseignement, faisant l'objet d'un choix au plus un nombre
 * maximum et au moins un nombre minimum; exemple : choisir une et une seule MC
 * entre une liste de MCs rattachées, au ou bien choisir au maximum 2 matière et
 * au minimum 2, ...etc.
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Entity
@Table(name = "groupe_rattachement_mc_a_choix", schema = "lmd")
public class GroupeRattachementMcAChoix implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;
	private Integer nombreMax = 1;
	private Integer nombreMin = 1;
	
	private Set<RattachementMc> rattachementMcs = new HashSet<RattachementMc>(0);


	public GroupeRattachementMcAChoix() {
	}

	/**
	 * Obtient l'identifiant du groupe des rattachements à choix 
	 * 
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * MAJ l'identifiant du groupe des rattachements à choix 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtient le nombre maximum à choisir parmis la lsite des Mcs rattachées.
	 * 
	 * @return : le nombre maximum de choix
	 */
	@Column(name = "nombre_max")
	public Integer getNombreMax() {
		return nombreMax;
	}

	/**
	 * MAJ le nombre maximum à choisir parmis la lsite des Mcs rattachées.
	 * 
	 * @param nombreMax : le nouveau nombre maximum de choix à MAJ
	 */
	public void setNombreMax(Integer nombreMax) {
		this.nombreMax = nombreMax;
	}

	/**
	 * Obtient le nombre minmuml à choisir parmis la lsite des Mcs rattachées.
	 * 
	 * @return : le nombre minimum de choix
	 */
	@Column(name = "nombre_min")
	public Integer getNombreMin() {
		return nombreMin;
	}


	/**
	 * MAJ le nombre minimum à choisir parmis la lsite des Mcs rattachées.
	 * 
	 * @param nombreMin : le nouveau nombre maximum de choix à MAJ.
	 */
	public void setNombreMin(Integer nombreMin) {
		this.nombreMin = nombreMin;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupeRattachementMcAChoix")
	public Set<RattachementMc> getRattachementMcs() {
		return rattachementMcs;
	}

	public void setRattachementMcs(Set<RattachementMc> rattachementMcs) {
		this.rattachementMcs = rattachementMcs;
	}


}
