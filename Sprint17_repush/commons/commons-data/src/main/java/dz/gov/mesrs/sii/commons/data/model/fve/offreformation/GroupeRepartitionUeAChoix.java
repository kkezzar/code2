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
 * Permet de regrouper des répratitions d'unités d'enseignement au sien d'un
 * même parcours d'une offre de formation, faisant l'objet d'un choix au plus un nombre
 * maximum et au moins un nombre minimum; exemple : choisir une et une seule UE
 * entre une liste d'UEs répartiées, au ou bien choisir au maximum 2 unités et
 * au minimum 2, ...etc.
 * 
 * @author Kheireddine OMRANI
 * 
 */
@Entity
@Table(name = "groupe_repartition_ue_a_choix", schema = "lmd")
public class GroupeRepartitionUeAChoix implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3624725482676563906L;

	private int id;
	private Integer nombreMax = 1;
	private Integer nombreMin = 1;
	
	private Set<RepartitionUe> repartitionUes = new HashSet<RepartitionUe>(0);


	public GroupeRepartitionUeAChoix() {
	}

	/**
	 * Obtient l'identifiant du groupe des répartitions UEs à choix 
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
	 * MAJ l'identifiant du groupe des répartitions UEs à choix 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtient le nombre maximum à choisir parmis la lsite des Ues répartiées.
	 * 
	 * @return : le nombre maximum de choix
	 */
	@Column(name = "nombre_max")
	public Integer getNombreMax() {
		return nombreMax;
	}

	/**
	 * MAJ le nombre maximum à choisir parmis la lsite des Ues répartiées.
	 * 
	 * @param nombreMax : le nouveau nombre maximum de choix à MAJ
	 */
	public void setNombreMax(Integer nombreMax) {
		this.nombreMax = nombreMax;
	}

	/**
	 * Obtient le nombre minmuml à choisir parmis la lsite des Ues répartiées.
	 * 
	 * @return : le nombre minimum de choix
	 */
	@Column(name = "nombre_min")
	public Integer getNombreMin() {
		return nombreMin;
	}


	/**
	 * MAJ le nombre minimum à choisir parmis la lsite des Ues répartiées.
	 * 
	 * @param nombreMin : le nouveau nombre maximum de choix à MAJ.
	 */
	public void setNombreMin(Integer nombreMin) {
		this.nombreMin = nombreMin;
	}
	
	/**
	 * Obtient la listes des répartitions UEs du groupe à choix
	 * 
	 * @return : la listes des répartition UEs du groupe à choix
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupeRepartitionUeAChoix")
	public Set<RepartitionUe> getRepartitionUes() {
		return repartitionUes;
	}

	/**
	 * MAJ la listes des répartitions UEs du groupe à choix
	 * 
	 * @param repartitionUes : la nouvelle liste des répartitions UEs du groupe à choix.
	 */
	public void setRepartitionUes(Set<RepartitionUe> repartitionUes) {
		this.repartitionUes = repartitionUes;
	}

}
