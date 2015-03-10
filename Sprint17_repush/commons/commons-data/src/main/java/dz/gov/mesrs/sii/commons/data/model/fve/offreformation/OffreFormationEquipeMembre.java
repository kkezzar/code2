		/**
		 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationDetail.java] 
		 * @author rlaib on : 6 fevr. 2014  12:22:02
		 */
		package dz.gov.mesrs.sii.commons.data.model.fve.offreformation;
		
		import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
				
		/**
		 * @author rlaib  on : 25 mars 2014  09:51:17
		 */
		@Entity
		@Table(name = "offre_formation_equipe_membre", schema = "lmd")
		public class OffreFormationEquipeMembre  implements java.io.Serializable{
			
			private static final long serialVersionUID = 1L;
			
			private int id;
			private String refCodeMembre;
			private String libelleFr;
			private String libelleAr;
			private OffreFormationEquipe offreFormationEquipe;
			private Date dateCreation;
			private Date dateDerniereModif;
			private String grade;
			private String roleMembre;
			private String libelleRoleMembre;
			
			public OffreFormationEquipeMembre() {
			}
		
			public OffreFormationEquipeMembre(int id, String refCodeMembre, String libelleFr, String libelleAr) {
				this.id = id;
				this.refCodeMembre = refCodeMembre;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}
			
			@SequenceGenerator(name="offre_formation_equipe_membre_id_seq_gen", sequenceName="lmd.offre_formation_equipe_membre_id_seq")
			@Id @GeneratedValue(generator="offre_formation_equipe_membre_id_seq_gen")
			@Column(name = "id")
			public int getId() {
				return id;
			}
		
			public void setId(int id) {
				this.id = id;
			}

			/**
			 * [OffreFormationEquipeMembres.refCodeMembre] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the refCodeMembre
			 */
			@Column(name = "ref_code_membre", nullable = false, length = 50)
			public String getRefCodeMembre() {
				return refCodeMembre;
			}

			/**
			 * [OffreFormationEquipeMembres.refCodeMembre] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param refCodeMembre the refCodeMembre to set
			 */
			public void setRefCodeMembre(String refCodeMembre) {
				this.refCodeMembre = refCodeMembre;
			}

			/**
			 * [OffreFormationEquipeMembres.libelleFr] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the libelleFr
			 */
			@Column(name = "libelle_fr")
			public String getLibelleFr() {
				return libelleFr;
			}

			/**
			 * [OffreFormationEquipeMembres.libelleFr] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}

			/**
			 * [OffreFormationEquipeMembres.libelleAr] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the libelleAr
			 */
			@Column(name = "libelle_ar")
			public String getLibelleAr() {
				return libelleAr;
			}

			/**
			 * [OffreFormationEquipeMembres.libelleAr] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationEquipeMembres.offreFormationEquipe] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the offreFormationEquipe
			 */
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "  id_offre_formation_equipe")
			public OffreFormationEquipe getOffreFormationEquipe() {
				return offreFormationEquipe;
			}

			/**
			 * [OffreFormationEquipeMembres.offreFormationEquipe] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param offreFormationEquipe the offreFormationEquipe to set
			 */
			public void setOffreFormationEquipe(OffreFormationEquipe offreFormationEquipe) {
				this.offreFormationEquipe = offreFormationEquipe;
			}

			/**
			 * [OffreFormationEquipeMembres.dateCreation] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the dateCreation
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "date_creation", length = 29)
			public Date getDateCreation() {
				return dateCreation;
			}

			/**
			 * [OffreFormationEquipeMembres.dateCreation] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}

			/**
			 * [OffreFormationEquipeMembres.dateDerniereModif] Getter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @return the dateDerniereModif
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "date_derniere_modif", length = 29)
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeMembres.dateDerniereModif] Setter 
			 * @author rlaib on : 25 mars 2014  10:14:54
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeMembre.grade] Getter 
			 * @author rlaib on : 25 mars 2014  15:47:54
			 * @return the grade
			 */
			@Column(name = "grade",length=50)
			public String getGrade() {
				return grade;
			}

			/**
			 * [OffreFormationEquipeMembre.grade] Setter 
			 * @author rlaib on : 25 mars 2014  15:47:54
			 * @param grade the grade to set
			 */
			public void setGrade(String grade) {
				this.grade = grade;
			}
			

			/**
			 * [OffreFormationEquipeMembre.roleMembre] Getter 
			 * @author rlaib on : 30 mars 2014  11:17:24
			 * @return the roleMembre
			 */
			@Column(name = "role_membre",length=50)
			public String getRoleMembre() {
				return roleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembre.roleMembre] Setter 
			 * @author rlaib on : 30 mars 2014  11:17:24
			 * @param roleMembre the roleMembre to set
			 */
			public void setRoleMembre(String roleMembre) {
				this.roleMembre = roleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembre.libelleRoleMembre] Getter 
			 * @author rlaib on : 30 mars 2014  11:17:24
			 * @return the libelleRoleMembre
			 */
			@Column(name = "libelle_role_membre",length=50)
			public String getLibelleRoleMembre() {
				return libelleRoleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembre.libelleRoleMembre] Setter 
			 * @author rlaib on : 30 mars 2014  11:17:24
			 * @param libelleRoleMembre the libelleRoleMembre to set
			 */
			public void setLibelleRoleMembre(String libelleRoleMembre) {
				this.libelleRoleMembre = libelleRoleMembre;
			}
			
			/**
			 * [OffreFormation.toString] Method 
			 * @author rlaib  on : 1 avr. 2014  10:12:47
			 * @return
			 */
			@Override
			public String toString() {
			
				return this.libelleFr;
			}

			
			/**
			 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipeMembre.hashCode] Method 
			 * overridden By :  @author  Rafik LAIB  
			 * On : 5 avr. 2014  20:47:02
			 * @return
			 */
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime
						* result
						+ ((refCodeMembre == null) ? 0 : refCodeMembre
								.hashCode());
				return result;
			}
			

			/**
			 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationEquipeMembre.equals] Method 
			 * overridden By :  @author  Rafik LAIB  
			 * On : 5 avr. 2014  20:47:02
			 * @param obj
			 * @return
			 */
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				OffreFormationEquipeMembre other = (OffreFormationEquipeMembre) obj;
				if (refCodeMembre == null) {
					if (other.refCodeMembre != null)
						return false;
				} else if (!refCodeMembre.equals(other.refCodeMembre))
					return false;
				return true;
			}

			
			
		}
