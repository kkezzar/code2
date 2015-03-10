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
		@Table(name = "offre_formation_partenaire", schema = "lmd")
		public class OffreFormationPartenaire  implements java.io.Serializable{
			
			private static final long serialVersionUID = 1L;
			
			private int id;
			private String refCodePartenaire;
			private String typePartenaire;
			private String libelleFr;
			private String libelleAr;
			private OffreFormationContrat offreFormationContrat;
			private Date dateCreation;
			private Date dateDerniereModif;
			
			public OffreFormationPartenaire() {
			}
		
			public OffreFormationPartenaire(int id, String refCodePartenaire, String libelleFr, String libelleAr) {
				this.id = id;
				this.refCodePartenaire = refCodePartenaire;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationPartenaire.id] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the id
			 */
			@SequenceGenerator(name="offre_formation_partenaire_id_seq_gen", sequenceName="lmd.offre_formation_partenaire_id_seq")
			@Id @GeneratedValue(generator="offre_formation_partenaire_id_seq_gen")
			@Column(name = "id")
			public int getId() {
				return id;
			}

			/**
			 * [OffreFormationPartenaire.id] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param id the id to set
			 */
			public void setId(int id) {
				this.id = id;
			}

			/**
			 * [OffreFormationPartenaire.refCodePartenaire] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the refCodePartenaire
			 */
			@Column(name = "ref_code_partenaire", nullable = false, length = 50)
			public String getRefCodePartenaire() {
				return refCodePartenaire;
			}

			/**
			 * [OffreFormationPartenaire.refCodePartenaire] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param refCodePartenaire the refCodePartenaire to set
			 */
			public void setRefCodePartenaire(String refCodePartenaire) {
				this.refCodePartenaire = refCodePartenaire;
			}

			/**
			 * [OffreFormationPartenaire.typePartenaire] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the typePartenaire
			 */
			@Column(name = "type_partenaire")
			public String getTypePartenaire() {
				return typePartenaire;
			}

			/**
			 * [OffreFormationPartenaire.typePartenaire] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param typePartenaire the typePartenaire to set
			 */
			public void setTypePartenaire(String typePartenaire) {
				this.typePartenaire = typePartenaire;
			}

			/**
			 * [OffreFormationPartenaire.libelleFr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the libelleFr
			 */
			@Column(name = "libelle_fr")
			public String getLibelleFr() {
				return libelleFr;
			}

			/**
			 * [OffreFormationPartenaire.libelleFr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}

			/**
			 * [OffreFormationPartenaire.libelleAr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the libelleAr
			 */
			@Column(name = "libelle_ar")
			public String getLibelleAr() {
				return libelleAr;
			}

			/**
			 * [OffreFormationPartenaire.libelleAr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationPartenaire.offreFormationContrat] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the offreFormationContrat
			 */
			@ManyToOne(fetch = FetchType.EAGER)
			@JoinColumn(name = "  id_contrat")
			public OffreFormationContrat getOffreFormationContrat() {
				return offreFormationContrat;
			}

			/**
			 * [OffreFormationPartenaire.offreFormationContrat] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param offreFormationContrat the offreFormationContrat to set
			 */
			public void setOffreFormationContrat(OffreFormationContrat offreFormationContrat) {
				this.offreFormationContrat = offreFormationContrat;
			}

			/**
			 * [OffreFormationPartenaire.dateCreation] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the dateCreation
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "date_creation", length = 29)
			public Date getDateCreation() {
				return dateCreation;
			}

			/**
			 * [OffreFormationPartenaire.dateCreation] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}

			/**
			 * [OffreFormationPartenaire.dateDerniereModif] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @return the dateDerniereModif
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "date_derniere_modif", length = 29)
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}

			/**
			 * [OffreFormationPartenaire.dateDerniereModif] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:30:08
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}

			/**
			 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationPartenaire.toString] Method 
			 * overridden By :  @author  Rafik LAIB  
			 * On : 5 avr. 2014  20:33:57
			 * @return
			 */
			@Override
			public String toString() {
				return "OffreFormationPartenaire [refCodePartenaire="
						+ refCodePartenaire + ", libelleFr=" + libelleFr + "]";
			}

			/**
			 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationPartenaire.hashCode] Method 
			 * overridden By :  @author  Rafik LAIB  
			 * On : 5 avr. 2014  20:34:28
			 * @return
			 */
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime
						* result
						+ ((refCodePartenaire == null) ? 0 : refCodePartenaire
								.hashCode());
				return result;
			}

			/**
			 * [dz.gov.mesrs.sii.fve.business.model.bo.offreformation.OffreFormationPartenaire.equals] Method 
			 * overridden By :  @author  Rafik LAIB  
			 * On : 5 avr. 2014  20:34:28
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
				OffreFormationPartenaire other = (OffreFormationPartenaire) obj;
				if (refCodePartenaire == null) {
					if (other.refCodePartenaire != null)
						return false;
				} else if (!refCodePartenaire.equals(other.refCodePartenaire))
					return false;
				return true;
			}
			
			
		}
