/**
 * [dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique.FicheVoeuxComponent.java] 
 * @author rlaib on : 14 oct. 2014  09:31:10
 */
package dz.gov.mesrs.sii.fve.web.jsf.bean.scolarite.chargepedagogique;

import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantFicheVoeuxDto;
import dz.gov.mesrs.sii.fve.business.model.dto.scolarite.EnseignantVoeuDto;
import dz.gov.mesrs.sii.fve.business.service.scolarite.EnseignantFicheVoeuxService;

/**
 * @author rlaib  on : 14 oct. 2014  09:31:10
 */
@FacesComponent(value="ficheVoeuxComponent") 
public class FicheVoeuxComponent extends UINamingContainer { 

					
			private EnseignantFicheVoeuxService enseignantFicheVoeuxService;
			private EnseignantFicheVoeuxDto fiche;
			private List<EnseignantVoeuDto> voeux;
			private Integer idFiche;
			
			@Override
		    public String getFamily() {
		        return UINamingContainer.COMPONENT_FAMILY;
		    }
			
			@SuppressWarnings("unused")
			private boolean editMode;

			/**
			 * [FicheVoeuxComponent.model] Getter 
			 * @author rlaib on : 14 oct. 2014  10:58:31
			 * @return the fiche
			 */
			public EnseignantFicheVoeuxDto getFiche() {
				
				 return fiche;
			}

			/**
			 * [FicheVoeuxComponent.model] Setter 
			 * @author rlaib on : 14 oct. 2014  10:58:31
			 * @param fiche the fiche to set
			 */
			public void setFiche(EnseignantFicheVoeuxDto fiche) {
				this.fiche = fiche;
			}

			/**
			 * [FicheVoeuxComponent.voeux] Getter 
			 * @author rlaib on : 14 oct. 2014  14:55:43
			 * @return the voeux
			 */
			public List<EnseignantVoeuDto> getVoeux() {
			
				return voeux;
			}

			/**
			 * [FicheVoeuxComponent.voeux] Setter 
			 * @author rlaib on : 14 oct. 2014  14:55:43
			 * @param voeux the voeux to set
			 */
			public void setVoeux(List<EnseignantVoeuDto> voeux) {
				this.voeux = voeux;
			}

			/**
			 * [FicheVoeuxComponent.editMode] Getter 
			 * @author rlaib on : 14 oct. 2014  11:02:08
			 * @return the editMode
			 */
			public boolean isEditMode() {
				return (boolean) getAttributes().get("toEdit");
			}

			/**
			 * [FicheVoeuxComponent.editMode] Setter 
			 * @author rlaib on : 14 oct. 2014  11:02:08
			 * @param editMode the editMode to set
			 */
			public void setEditMode(boolean editMode) {
				this.editMode = editMode;
			}

			/**
			 * [FicheVoeuxComponent.enseignantFicheVoeuxService] Getter 
			 * @author rlaib on : 14 oct. 2014  13:13:48
			 * @return the enseignantFicheVoeuxService
			 */
			public EnseignantFicheVoeuxService getEnseignantFicheVoeuxService() {
				enseignantFicheVoeuxService = (EnseignantFicheVoeuxService) getAttributes().get("service");
				return enseignantFicheVoeuxService;
			}

			/**
			 * [FicheVoeuxComponent.enseignantFicheVoeuxService] Setter 
			 * @author rlaib on : 14 oct. 2014  13:13:48
			 * @param enseignantFicheVoeuxService the enseignantFicheVoeuxService to set
			 */
			public void setEnseignantFicheVoeuxService(EnseignantFicheVoeuxService enseignantFicheVoeuxService) {
				this.enseignantFicheVoeuxService = enseignantFicheVoeuxService;
			}

			/**
			 * [FicheVoeuxComponent.idFiche] Getter 
			 * @author rlaib on : 14 oct. 2014  13:28:41
			 * @return the idFiche
			 */
			public Integer getIdFiche() {
				return idFiche;
			}

			/**
			 * [FicheVoeuxComponent.idFiche] Setter 
			 * @author rlaib on : 14 oct. 2014  13:28:41
			 * @param idFiche the idFiche to set
			 */
			public void setIdFiche(Integer idFiche) {
				this.idFiche = idFiche;
			}
			
			public void addVoeu() {
				try {
							if(canAddVoeu()
									&&
									(idFiche != null && idFiche > 0)) {
								
								EnseignantVoeuDto dto = new EnseignantVoeuDto();
								voeux.add(dto);
						
							}
							else {
								
							}
								
				}
				 catch(Exception e){
					 e.printStackTrace();
				 }
		}

			/**
			 * [FicheVoeuxComponent.canAddVoeu] Method 
			 * @author rlaib  on : 14 oct. 2014  16:10:54
			 * @return
			 */
			private boolean canAddVoeu() {
				// TODO Auto-generated method stub
				return true;
			}
			
			public void init() {
				
				idFiche= (Integer) getAttributes().get("idFiche");
				fiche = new EnseignantFicheVoeuxDto();
				if(idFiche != null && idFiche > 0) {
					fiche = this.getEnseignantFicheVoeuxService().findById(idFiche);
					voeux = this.getEnseignantFicheVoeuxService().findVoeuxByIdFiche(idFiche);
				}
			}
			
}
