/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto.java] 
 * @author rlaib on : 25 mars 2014  09:57:57
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationContrat;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  20:50:08
 */

public class OffreFormationPartenaireDto  implements java.io.Serializable {
	
			/**
			 * serialVersionUID 
			 * @author rlaib  on : 25 mars 2014  10:45:18
			 */
			private static final long serialVersionUID = 1L;
			
			private int id;
			private String refCodePartenaire;
			private String typePartenaire;
			private String libelleFr;
			private String libelleAr;
			private OffreFormationContrat offreFormationContrat;
			private Date dateCreation;
			private Date dateDerniereModif;
			
		    public OffreFormationPartenaireDto() {
		    	
			}

			public OffreFormationPartenaireDto(int id, String libelleFr, String libelleAr) {
				this.id = id;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}

		
			/**
			 * [OffreFormationPartenaireDto.id] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the id
			 */
			public int getId() {
				return id;
			}
			

			/**
			 * [OffreFormationPartenaireDto.id] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param id the id to set
			 */
			public void setId(int id) {
				this.id = id;
			}
			

			/**
			 * [OffreFormationPartenaireDto.refCodePartenaire] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the refCodePartenaire
			 */
			public String getRefCodePartenaire() {
				return refCodePartenaire;
			}
			

			/**
			 * [OffreFormationPartenaireDto.refCodePartenaire] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param refCodePartenaire the refCodePartenaire to set
			 */
			public void setRefCodePartenaire(String refCodePartenaire) {
				this.refCodePartenaire = refCodePartenaire;
			}
			

			/**
			 * [OffreFormationPartenaireDto.typePartenaire] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the typePartenaire
			 */
			public String getTypePartenaire() {
				return typePartenaire;
			}
			

			/**
			 * [OffreFormationPartenaireDto.typePartenaire] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param typePartenaire the typePartenaire to set
			 */
			public void setTypePartenaire(String typePartenaire) {
				this.typePartenaire = typePartenaire;
			}
			

			/**
			 * [OffreFormationPartenaireDto.libelleFr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the libelleFr
			 */
			public String getLibelleFr() {
				return libelleFr;
			}
			

			/**
			 * [OffreFormationPartenaireDto.libelleFr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}
			

			/**
			 * [OffreFormationPartenaireDto.libelleAr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the libelleAr
			 */
			public String getLibelleAr() {
				
				return libelleAr;
			}
			

			/**
			 * [OffreFormationPartenaireDto.libelleAr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}
			

			/**
			 * [OffreFormationPartenaireDto.offreFormationContrat] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the offreFormationContrat
			 */
			public OffreFormationContrat getOffreFormationContrat() {
				return offreFormationContrat;
			}
			

			/**
			 * [OffreFormationPartenaireDto.offreFormationContrat] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param offreFormationContrat the offreFormationContrat to set
			 */
			public void setOffreFormationContrat(OffreFormationContrat offreFormationContrat) {
				this.offreFormationContrat = offreFormationContrat;
			}
			

			/**
			 * [OffreFormationPartenaireDto.dateCreation] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the dateCreation
			 */
			public Date getDateCreation() {
				return dateCreation;
			}
			

			/**
			 * [OffreFormationPartenaireDto.dateCreation] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}
			

			/**
			 * [OffreFormationPartenaireDto.dateDerniereModif] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @return the dateDerniereModif
			 */
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}
			

			/**
			 * [OffreFormationPartenaireDto.dateDerniereModif] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:53:03
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}

			
}
