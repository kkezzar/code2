/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto.java] 
 * @author rlaib on : 25 mars 2014  09:57:57
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

/**
 * @author rlaib  on : 25 mars 2014  09:57:57
 */
public class OffreFormationEquipeDto  implements java.io.Serializable {
	
			/**
			 * serialVersionUID 
			 * @author rlaib  on : 25 mars 2014  10:45:18
			 */
			private static final long serialVersionUID = 1L;
			
			private int id;
			private String code;
			private String libelleFr;
			private String libelleAr;
			private Date dateCreation;
			private Date dateDerniereModif;
			private String libelleCourtFr;
			private String libelleCourtAr;
	
			// Offre de formation
			private int offreFormationId;
			private String offreFormationCode;
			private String offreFormationLibelleFr;
			
		    public OffreFormationEquipeDto() {
			}

			public OffreFormationEquipeDto(int id, String libelleFr, String libelleAr) {
				this.id = id;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationEquipeDto.id] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the id
			 */
			public int getId() {
				return id;
			}

			/**
			 * [OffreFormationEquipeDto.id] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param id the id to set
			 */
			public void setId(int id) {
				this.id = id;
			}

			/**
			 * [OffreFormationEquipeDto.code] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the code
			 */
			public String getCode() {
				return code;
			}

			/**
			 * [OffreFormationEquipeDto.code] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param code the code to set
			 */
			public void setCode(String code) {
				this.code = code;
			}

			/**
			 * [OffreFormationEquipeDto.libelleFr] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the libelleFr
			 */
			public String getLibelleFr() {
				return libelleFr;
			}

			/**
			 * [OffreFormationEquipeDto.libelleFr] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}

			/**
			 * [OffreFormationEquipeDto.libelleAr] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the libelleAr
			 */
			public String getLibelleAr() {
				return libelleAr;
			}

			/**
			 * [OffreFormationEquipeDto.libelleAr] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationEquipeDto.dateCreation] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the dateCreation
			 */
			public Date getDateCreation() {
				return dateCreation;
			}

			/**
			 * [OffreFormationEquipeDto.dateCreation] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}

			/**
			 * [OffreFormationEquipeDto.dateDerniereModif] Getter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @return the dateDerniereModif
			 */
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeDto.dateDerniereModif] Setter 
			 * @author rlaib on : 25 mars 2014  10:00:58
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeDto.libelleCourtFr] Getter 
			 * @author rlaib on : 25 mars 2014  15:17:21
			 * @return the libelleCourtFr
			 */
			public String getLibelleCourtFr() {
				return libelleCourtFr;
			}
			

			/**
			 * [OffreFormationEquipeDto.libelleCourtFr] Setter 
			 * @author rlaib on : 25 mars 2014  15:17:21
			 * @param libelleCourtFr the libelleCourtFr to set
			 */
			public void setLibelleCourtFr(String libelleCourtFr) {
				this.libelleCourtFr = libelleCourtFr;
			}
			

			/**
			 * [OffreFormationEquipeDto.libelleCourtAr] Getter 
			 * @author rlaib on : 25 mars 2014  15:17:21
			 * @return the libelleCourtAr
			 */
			public String getLibelleCourtAr() {
				return libelleCourtAr;
			}
			

			/**
			 * [OffreFormationEquipeDto.libelleCourtAr] Setter 
			 * @author rlaib on : 25 mars 2014  15:17:21
			 * @param libelleCourtAr the libelleCourtAr to set
			 */
			public void setLibelleCourtAr(String libelleCourtAr) {
				this.libelleCourtAr = libelleCourtAr;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationId] Getter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @return the offreFormationId
			 */
			public int getOffreFormationId() {
				return offreFormationId;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationId] Setter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @param offreFormationId the offreFormationId to set
			 */
			public void setOffreFormationId(int offreFormationId) {
				this.offreFormationId = offreFormationId;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationCode] Getter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @return the offreFormationCode
			 */
			public String getOffreFormationCode() {
				return offreFormationCode;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationCode] Setter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @param offreFormationCode the offreFormationCode to set
			 */
			public void setOffreFormationCode(String offreFormationCode) {
				this.offreFormationCode = offreFormationCode;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationLibelleFr] Getter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @return the offreFormationLibelleFr
			 */
			public String getOffreFormationLibelleFr() {
				return offreFormationLibelleFr;
			}

			/**
			 * [OffreFormationEquipeDto.offreFormationLibelleFr] Setter 
			 * @author rlaib on : 3 déc. 2014  15:44:09
			 * @param offreFormationLibelleFr the offreFormationLibelleFr to set
			 */
			public void setOffreFormationLibelleFr(String offreFormationLibelleFr) {
				this.offreFormationLibelleFr = offreFormationLibelleFr;
			}
		
			
}
