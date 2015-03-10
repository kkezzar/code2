/**
 * [dz.gov.mesrs.sii.fve.business.model.dto.offreformation.OffreFormationEquipeDto.java] 
 * @author rlaib on : 25 mars 2014  09:57:57
 */
package dz.gov.mesrs.sii.fve.business.model.dto.offreformation;

import java.util.Date;

import dz.gov.mesrs.sii.commons.data.model.fve.offreformation.OffreFormationEquipe;

/**
 * @author rlaib  on : 25 mars 2014  09:57:57
 */
public class OffreFormationEquipeMembreDto  implements java.io.Serializable {
	
			private int id;
			private String refCodeMembre;
			private String libelleFr;
			private String libelleAr;
			private Date dateCreation;
			private Date dateDerniereModif;
			private String grade;
			private String roleMembre;
			private String libelleRoleMembre;
			
			// Equipe de formation
			private int offreFormationEquipeId;
			private String offreFormationEquipeCode;
			private String offreFormationEquipeLibelle;
			
		    public OffreFormationEquipeMembreDto() {
			}

			public OffreFormationEquipeMembreDto(int id, String libelleFr, String libelleAr) {
				this.id = id;
				this.libelleFr = libelleFr;
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationEquipeMembreDto.id] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:57
			 * @return the id
			 */
			public int getId() {
				return id;
			}

			/**
			 * [OffreFormationEquipeMembreDto.id] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:57
			 * @param id the id to set
			 */
			public void setId(int id) {
				this.id = id;
			}

			/**
			 * [OffreFormationEquipeMembreDto.refCodeMembre] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:57
			 * @return the refCodeMembre
			 */
			public String getRefCodeMembre() {
				return refCodeMembre;
			}

			/**
			 * [OffreFormationEquipeMembreDto.refCodeMembre] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:57
			 * @param refCodeMembre the refCodeMembre to set
			 */
			public void setRefCodeMembre(String refCodeMembre) {
				this.refCodeMembre = refCodeMembre;
			}

			/**
			 * [OffreFormationEquipeMembreDto.libelleFr] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @return the libelleFr
			 */
			public String getLibelleFr() {
				return libelleFr;
			}

			/**
			 * [OffreFormationEquipeMembreDto.libelleFr] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @param libelleFr the libelleFr to set
			 */
			public void setLibelleFr(String libelleFr) {
				this.libelleFr = libelleFr;
			}

			/**
			 * [OffreFormationEquipeMembreDto.libelleAr] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @return the libelleAr
			 */
			public String getLibelleAr() {
				return libelleAr;
			}

			/**
			 * [OffreFormationEquipeMembreDto.libelleAr] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @param libelleAr the libelleAr to set
			 */
			public void setLibelleAr(String libelleAr) {
				this.libelleAr = libelleAr;
			}

			/**
			 * [OffreFormationEquipeMembreDto.dateCreation] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @return the dateCreation
			 */
			public Date getDateCreation() {
				return dateCreation;
			}

			/**
			 * [OffreFormationEquipeMembreDto.dateCreation] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @param dateCreation the dateCreation to set
			 */
			public void setDateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}

			/**
			 * [OffreFormationEquipeMembreDto.dateDerniereModif] Getter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @return the dateDerniereModif
			 */
			public Date getDateDerniereModif() {
				return dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeMembreDto.dateDerniereModif] Setter 
			 * @author rlaib on : 25 mars 2014  10:43:58
			 * @param dateDerniereModif the dateDerniereModif to set
			 */
			public void setDateDerniereModif(Date dateDerniereModif) {
				this.dateDerniereModif = dateDerniereModif;
			}

			/**
			 * [OffreFormationEquipeMembreDto.grade] Getter 
			 * @author rlaib on : 25 mars 2014  15:49:19
			 * @return the grade
			 */
			public String getGrade() {
				return grade;
			}

			/**
			 * [OffreFormationEquipeMembreDto.grade] Setter 
			 * @author rlaib on : 25 mars 2014  15:49:19
			 * @param grade the grade to set
			 */
			public void setGrade(String grade) {
				this.grade = grade;
			}
			

			/**
			 * [OffreFormationEquipeMembreDto.roleMembre] Getter 
			 * @author rlaib on : 30 mars 2014  11:19:05
			 * @return the roleMembre
			 */
			public String getRoleMembre() {
				return roleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembreDto.roleMembre] Setter 
			 * @author rlaib on : 30 mars 2014  11:19:05
			 * @param roleMembre the roleMembre to set
			 */
			public void setRoleMembre(String roleMembre) {
				this.roleMembre = roleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembreDto.libelleRoleMembre] Getter 
			 * @author rlaib on : 30 mars 2014  11:19:05
			 * @return the libelleRoleMembre
			 */
			public String getLibelleRoleMembre() {
				return libelleRoleMembre;
			}
			

			/**
			 * [OffreFormationEquipeMembreDto.libelleRoleMembre] Setter 
			 * @author rlaib on : 30 mars 2014  11:19:05
			 * @param libelleRoleMembre the libelleRoleMembre to set
			 */
			public void setLibelleRoleMembre(String libelleRoleMembre) {
				this.libelleRoleMembre = libelleRoleMembre;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeId] Getter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @return the offreFormationEquipeId
			 */
			public int getOffreFormationEquipeId() {
				return offreFormationEquipeId;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeId] Setter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @param offreFormationEquipeId the offreFormationEquipeId to set
			 */
			public void setOffreFormationEquipeId(int offreFormationEquipeId) {
				this.offreFormationEquipeId = offreFormationEquipeId;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeCode] Getter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @return the offreFormationEquipeCode
			 */
			public String getOffreFormationEquipeCode() {
				return offreFormationEquipeCode;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeCode] Setter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @param offreFormationEquipeCode the offreFormationEquipeCode to set
			 */
			public void setOffreFormationEquipeCode(String offreFormationEquipeCode) {
				this.offreFormationEquipeCode = offreFormationEquipeCode;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeLibelle] Getter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @return the offreFormationEquipeLibelle
			 */
			public String getOffreFormationEquipeLibelle() {
				return offreFormationEquipeLibelle;
			}

			/**
			 * [OffreFormationEquipeMembreDto.offreFormationEquipeLibelle] Setter 
			 * @author rlaib on : 3 déc. 2014  16:26:52
			 * @param offreFormationEquipeLibelle the offreFormationEquipeLibelle to set
			 */
			public void setOffreFormationEquipeLibelle(String offreFormationEquipeLibelle) {
				this.offreFormationEquipeLibelle = offreFormationEquipeLibelle;
			}
		
}
