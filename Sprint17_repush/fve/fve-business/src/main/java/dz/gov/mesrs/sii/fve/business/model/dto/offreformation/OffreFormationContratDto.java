/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto.java] 
 * @author rlaib on : 25 mars 2014  09:57:57
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormation;


/**
 * @author  Rafik LAIB  on : 5 avr. 2014  20:50:08
 */

public class OffreFormationContratDto  implements java.io.Serializable {
	
			/**
			 * serialVersionUID 
			 * @author rlaib  on : 25 mars 2014  10:45:18
			 */
			private static final long serialVersionUID = 1L;
			
			private int id;
			private String refCodeContrat;
			private String libelleFr;
			private String libelleAr;
			private OffreFormation offreFormation;
			private Date dateCreation;
			private Date dateDerniereModif;
			private String reference_contrat;
			private Set<OffreFormationPartenaireDto> offreFormationPartenaireDtos = new HashSet<OffreFormationPartenaireDto>(0);

		    public OffreFormationContratDto() {
		    	
			}

			public OffreFormationContratDto(int id, String libelleFr, String libelleAr) {
				this.id = id;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}

			
			/**
			 * [OffreFormationContratDto.id] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the id
			 */
			public int getId() {
				return id;
			}
			

			/**
			 * [OffreFormationContratDto.id] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param id the id to set
			 */
			public void setId(int id) {
				this.id = id;
			}
			

			/**
			 * [OffreFormationContratDto.refCodeContrat] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the refCodeContrat
			 */
			public String getRefCodeContrat() {
				return refCodeContrat;
			}
			

			/**
			 * [OffreFormationContratDto.refCodeContrat] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param refCodeContrat the refCodeContrat to set
			 */
			public void setRefCodeContrat(String refCodeContrat) {
				this.refCodeContrat = refCodeContrat;
			}
			

			/**
			 * [OffreFormationContratDto.libelleFr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the libelleFr
			 */
			public String getLibelleFr() {
				return libelleFr;
			}
			

			/**
			 * [OffreFormationContratDto.libelleFr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}
			

			/**
			 * [OffreFormationContratDto.libelleAr] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the libelleAr
			 */
			public String getLibelleAr() {
				return libelleAr;
			}
			

			/**
			 * [OffreFormationContratDto.libelleAr] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}
			

			/**
			 * [OffreFormationContratDto.offreFormation] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the offreFormation
			 */
			public OffreFormation getOffreFormation() {
				return offreFormation;
			}
			

			/**
			 * [OffreFormationContratDto.offreFormation] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param offreFormation the offreFormation to set
			 */
			public void setOffreFormation(OffreFormation offreFormation) {
				this.offreFormation = offreFormation;
			}
			

			/**
			 * [OffreFormationContratDto.dateCreation] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the dateCreation
			 */
			public Date getDateCreation() {
				return dateCreation;
			}
			

			/**
			 * [OffreFormationContratDto.dateCreation] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}
			

			/**
			 * [OffreFormationContratDto.dateDerniereModif] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the dateDerniereModif
			 */
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}
			

			/**
			 * [OffreFormationContratDto.dateDerniereModif] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}
			

			/**
			 * [OffreFormationContratDto.reference_contrat] Getter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @return the reference_contrat
			 */
			public String getReference_contrat() {
				return reference_contrat;
			}
			

			/**
			 * [OffreFormationContratDto.reference_contrat] Setter 
			 * @author  Rafik LAIB on : 5 avr. 2014  20:50:58
			 * @param reference_contrat the reference_contrat to set
			 */
			public void setReference_contrat(String reference_contrat) {
				this.reference_contrat = reference_contrat;
			}

		
			/**
			 * [OffreFormationContratDto.offreFormationPartenaireDtos] Getter 
			 * @author rlaib on : 8 avr. 2014  10:08:36
			 * @return the offreFormationPartenaireDtos
			 */
			public Set<OffreFormationPartenaireDto> getOffreFormationPartenaireDtos() {
				return offreFormationPartenaireDtos;
			}
			

			/**
			 * [OffreFormationContratDto.offreFormationPartenaireDtos] Setter 
			 * @author rlaib on : 8 avr. 2014  10:08:36
			 * @param offreFormationPartenaireDtos the offreFormationPartenaireDtos to set
			 */
			public void setOffreFormationPartenaireDtos(
					Set<OffreFormationPartenaireDto> offreFormationPartenaireDtos) {
				this.offreFormationPartenaireDtos = offreFormationPartenaireDtos;
			}
			

			
}
