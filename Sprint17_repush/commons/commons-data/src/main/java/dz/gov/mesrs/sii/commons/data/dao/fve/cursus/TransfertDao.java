package dz.gov.mesrs.sii.commons.data.dao.fve.cursus;

import java.util.List;

import dz.gov.mesrs.sii.commons.data.model.fve.cursus.Transfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertExterneSortanteBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterne;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertInterneBachelier;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiques;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertStatistiquesTypeTransfert;
import dz.gov.mesrs.sii.commons.data.model.fve.cursus.TransfertTypeTransfert;

/**
 *
 * @author Mounir.MESSAOUDI
 */
public interface TransfertDao {

    public Transfert findById(int id);

    public List<Transfert> findByQuery(String query);

    public List<Transfert> findByRefCodeEtablissement(String refCodeEtab);

    
    public List<TransfertInterne> findTransfertInterneByQuery(String query);
    public List<TransfertInterne> findTransfertInterneByRefCodeEtab(String refCodeEtab);
    public List<TransfertInterne> findTransfertInterneByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertExterne> findTransfertExterneByQuery(String query);
    public List<TransfertExterne> findTransfertExterneByRefCodeEtab(String refCodeEtab);
    public List<TransfertExterne> findTransfertExterneByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    
    // transferts par type bachelier noveau/ancien
    public List<TransfertInterneBachelier> findTransfertInterneBachelierByQuery(String query);
    public List<TransfertInterneBachelier> findTransfertInterneBachelierByRefCodeEtab(String refCodeEtab);
    public List<TransfertInterneBachelier> findTransfertInterneBachelierByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertExterneBachelier> findTransfertExterneBachelierByQuery(String query);
    public List<TransfertExterneBachelier> findTransfertExterneBachelierByRefCodeEtab(String refCodeEtab);
    public List<TransfertExterneBachelier> findTransfertExterneBachelierByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    

    // transferts par type transfert
    public List<TransfertTypeTransfert> findTransfertTypeTransfertByQuery(String query);
    public List<TransfertTypeTransfert> findTransfertTypeTransfertyRefCodeEtab(String refCodeEtab);
    public List<TransfertTypeTransfert> findTransfertTypeTransfertByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    public List<TransfertTypeTransfert> findTransfertTypeTransfertByIdAnnAcadRefCodeEtabRefCodeTypeTransfert(Integer idAnneeAcademique, String refCodeEtab, String refCodeTypeTransdfert);

    // transferts sortantes
    public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByQuery(String query);
    public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByRefCodeEtab(String refCodeEtab);
    public List<TransfertExterneSortanteBachelier> findTransfertExterneSortanteByIdAnnAcadRefCodeEtab(Integer idAnneeAcademique, String refCodeEtab);
    
    /**
     * Recherche par annnee academique et code etablissement
     * @author Mounir.MESSAOUDI on : 31 ao�t 2014 12:54:57 
     * @param idAnneeAcademique
     * @param refCodeEtab
     * @return
     */
    public List<Transfert> findByIdAnneeAcademiqueRefCodeEtablissement(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<Transfert> findAll();
    
    public List<TransfertStatistiques> findStatsByIdAnneeAcademiqueRefCodeEtablissement(Integer idAnneeAcademique, String refCodeEtab);
    
    public List<TransfertStatistiquesTypeTransfert> findStatsByIdAnneeAcademiqueRefCodeEtablissementTypeTransfert(Integer idAnneeAcademique, String refCodeEtab, String refCodeTypeTransfert);
     
}
