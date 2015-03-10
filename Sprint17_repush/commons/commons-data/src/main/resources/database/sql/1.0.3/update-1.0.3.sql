-----nc

SELECT setval('"nc"."nc_list_id_seq"', 254, true);

SELECT setval('"nc"."nomenclature_id_seq"', 675608, true);



--ppm

ALTER TABLE "ppm"."ref_module" ALTER COLUMN "help_page" TYPE varchar(255) COLLATE "default";

ALTER TABLE "ppm"."ref_mot_cle" DROP CONSTRAINT "fk_mot_cle_nomenclature";

ALTER TABLE "ppm"."ref_mot_cle" ADD CONSTRAINT "fk_mot_cle_nomenclature1" FOREIGN KEY ("nc_mot_cle") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

--public

SELECT setval('"public"."hibernate_sequence"', 13538, true);


--bac

--curssus

SELECT setval('"cursus"."affectation_groupe_pedagogique_id_seq"', 1076, true);

SELECT setval('"cursus"."association_groupe_pedagogique_id_seq"', 470, true);

SELECT setval('"cursus"."attestation_fin_etude_id_seq"', 116, true);

SELECT setval('"cursus"."diplome_fin_etude_id_seq"', 505, true);

SELECT setval('"cursus"."groupe_pedagogique_id_seq"', 146, true);

ALTER TABLE "cursus"."diplome_fin_etude" DROP CONSTRAINT "fk_diplome_fe_dossier_inscription";

ALTER TABLE "cursus"."diplome_fin_etude" ALTER COLUMN "id_bilan_session" TYPE int8;

ALTER TABLE "cursus"."diplome_fin_etude" ADD COLUMN "id_cycle" int4;

ALTER TABLE "cursus"."diplome_fin_etude" ADD COLUMN "est_valide" bool;

ALTER TABLE "cursus"."diplome_fin_etude" DROP COLUMN "id_dossier_inscription_administrative";

ALTER TABLE "cursus"."diplome_fin_etude" DROP COLUMN "id_session";

ALTER TABLE "cursus"."diplome_fin_etude" DROP COLUMN "ref_code_diplome";

ALTER TABLE "cursus"."diplome_fin_etude" DROP COLUMN "ref_code_mention";

ALTER TABLE "cursus"."diplome_fin_etude" DROP COLUMN "nc_mention";

CREATE TABLE "cursus"."signature_diplome_fin_etude" (
"id" int8 NOT NULL,
"date_signature" date,
"id_compte" int4,
"nom" varchar COLLATE "default",
"prenom" varchar COLLATE "default",
"fonction" varchar COLLATE "default",
"id_diplome_fin_etude" int8,
"est_attestation" bool,
CONSTRAINT "pkey_signature_diplome_fin_etude" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

DROP INDEX "cursus"."fki_diplome_fe_bilan_session";

CREATE INDEX "fki_diplome_fe_bilan_session" ON "cursus"."diplome_fin_etude" USING btree (id_bilan_session);

CREATE INDEX "fki_id_cycle" ON "cursus"."diplome_fin_etude" USING btree (id_cycle);

CREATE INDEX "fki_signature_diplome_fin_etude_compte" ON "cursus"."signature_diplome_fin_etude" USING btree (id_compte);

CREATE INDEX "fki_signature_diplome_fin_etude_diplome" ON "cursus"."signature_diplome_fin_etude" USING btree (id_diplome_fin_etude);

--ALTER TABLE "cursus.diplome_fin_etude" DROP CONSTRAINT "uq_id_bilan_session";
--DROP  INDEX "cursus"."uq_id_bilan_session";

--CREATE UNIQUE INDEX "uq_id_bilan_session" ON "cursus"."diplome_fin_etude" USING btree (id_bilan_session);

--DROP INDEX "cursus"."fki_diplome_fe_dossier_inscription";

--DROP INDEX "cursus"."fki_diplome_fe_session";

ALTER TABLE "cursus"."diplome_fin_etude" ADD CONSTRAINT "id_cycle" FOREIGN KEY ("id_cycle") REFERENCES "lmd"."cycle" ("id") ON DELETE NO ACTION 

ON UPDATE NO ACTION;

ALTER TABLE "cursus"."signature_diplome_fin_etude" ADD CONSTRAINT "fk_signature_diplome_fin_etude_compte" FOREIGN KEY ("id_compte") REFERENCES 

"ppm"."ref_compte" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "cursus"."signature_diplome_fin_etude" ADD CONSTRAINT "fk_signature_diplome_fin_etude_diplome" FOREIGN KEY ("id_diplome_fin_etude") 

REFERENCES "cursus"."diplome_fin_etude" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;


--fve examen

SELECT setval('"fve_examen"."bilan_controle_continu_id_seq"', 983, true);

SELECT setval('"fve_examen"."bilan_mc_id_seq"', 21927, true);

SELECT setval('"fve_examen"."bilan_session_id_seq"', 4700, true);

SELECT setval('"fve_examen"."bilan_ue_id_seq"', 12801, true);

SELECT setval('"fve_examen"."coefficient_examen_id_seq"', 16, true);

SELECT setval('"fve_examen"."deliberation_session_id_seq"', 56, true);

SELECT setval('"fve_examen"."evaluation_controle_continu_id_seq"', 89, true);

SELECT setval('"fve_examen"."examen_session_id_seq"', 247, true);

SELECT setval('"fve_examen"."inscription_examen_id_seq"', 5339, true);

SELECT setval('"fve_examen"."note_evaluation_controle_continu_id_seq"', 1519, true);

SELECT setval('"fve_examen"."planning_session_id_seq"', 67, true);

SELECT setval('"fve_examen"."responsable_examen_id_seq"', 76, true);

SELECT setval('"fve_examen"."salle_examen_id_seq"', 308, true);


--lmd


SELECT setval('"lmd"."cycle_diplome_id_seq"', 22, true);

SELECT setval('"lmd"."cycle_id_seq"', 52, true);

SELECT setval('"lmd"."rattachement_mc_id_seq"', 340, true);

SELECT setval('"lmd"."repartition_ue_id_seq"', 43840, true);

ALTER TABLE "lmd"."rattachement_mc" DROP COLUMN "taux_examen";

ALTER TABLE "lmd"."rattachement_mc" DROP COLUMN "taux_controle_continu";

ALTER TABLE "lmd"."rattachement_mc" DROP COLUMN "taux_controle_intermediaire";

-- grh 

SELECT setval('"grh"."dossier_employe_id_employe_seq"', 298, true);

CREATE SEQUENCE "grh"."affectation_emploi_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."affectation_emploi" (
"id" int4 DEFAULT nextval('"grh".affectation_emploi_id_seq'::regclass) NOT NULL,
"dossier_employe" int8 NOT NULL,
"poste_emploi" int8 NOT NULL,
"date_debut_affectation" date NOT NULL,
"date_fin_affectation" date,
CONSTRAINT "pk_affection_emploi_id" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."affectation_emploi_id_seq" OWNED BY "grh"."affectation_emploi"."id";

CREATE SEQUENCE "grh"."besoin_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."besoin_formation" (
"id" int4 DEFAULT nextval('"grh".besoin_formation_id_seq'::regclass) NOT NULL,
"date_debut" date,
"date_fin" date,
"objectif" varchar COLLATE "default",
"type_besoin" int4,
"date_evaluation" date,
"objectif_atteint" varchar COLLATE "default",
"situation" int4,
"structure" int4,
"groupe" int4,
"employe" int4,
"etablissement" int4,
CONSTRAINT "pk_besoin_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."besoin_formation_id_seq" OWNED BY "grh"."besoin_formation"."id";

CREATE SEQUENCE "grh"."catalogue_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."catalogue_formation" (
"id" int4 DEFAULT nextval('"grh".catalogue_formation_id_seq'::regclass) NOT NULL,
"date_creation" date,
"objet" varchar COLLATE "default",
"statut" bool,
CONSTRAINT "pk_grh_catalogue_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."catalogue_formation_id_seq" OWNED BY "grh"."catalogue_formation"."id";

ALTER TABLE "grh"."connaissance_employe" ADD COLUMN "description" varchar COLLATE "default";

CREATE SEQUENCE "grh"."connaissance_poste_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."connaissance_poste" (
"id" int4 DEFAULT nextval('"grh".connaissance_poste_id_seq'::regclass) NOT NULL,
"mots_cles" varchar(255) COLLATE "default",
"description" varchar(255) COLLATE "default",
"type_connaissance" int4 NOT NULL,
"poste_emploi" int8 NOT NULL,
CONSTRAINT "pk_connaissance_poste_id" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."connaissance_poste_id_seq" OWNED BY "grh"."connaissance_poste"."id";

CREATE SEQUENCE "grh"."cycle_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

SELECT setval('"grh"."cycle_formation_id_seq"', 3, true);

CREATE TABLE "grh"."cycle_formation" (
"id" int4 DEFAULT nextval('"grh".cycle_formation_id_seq'::regclass) NOT NULL,
"intitule" varchar COLLATE "default",
"date_debut" date,
"date_fin" date,
"objectif" varchar COLLATE "default",
"code" varchar COLLATE "default",
"situation" int4,
"programme_formation" int4,
CONSTRAINT "pk_grh_cycle_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."cycle_formation_id_seq" OWNED BY "grh"."cycle_formation"."id";

CREATE SEQUENCE "grh"."detail_besoin_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."detail_besoin_formation" (
"id" int4 DEFAULT nextval('"grh".detail_besoin_formation_id_seq'::regclass) NOT NULL,
"formation" int4,
"corps" int4,
"grade" int4,
"nb_candidat" int4,
"nb_forme" int4,
"effectue" bool,
"besoin_formation" int4,
CONSTRAINT "pk_detail_besoin_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."detail_besoin_formation_id_seq" OWNED BY "grh"."detail_besoin_formation"."id";

ALTER TABLE "grh"."dossier_employe" ADD COLUMN "niveau_competence" int8;

ALTER TABLE "grh"."dossier_employe" ADD COLUMN "niveau_qualification" int8;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "libelle" int4;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "date_survenue" date;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "observation" varchar COLLATE "default";

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "long_duree" bool;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "antecedent" bool;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "allergie" bool;

ALTER TABLE "grh"."dossier_medical" ADD COLUMN "pathologie" bool;

ALTER TABLE "grh"."dossier_medical" DROP COLUMN "date_creation";

CREATE SEQUENCE "grh"."evaluation_detail_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."evaluation_detail" (
"id" int4 DEFAULT nextval('"grh".evaluation_detail_id_seq'::regclass) NOT NULL,
"critere" int8 NOT NULL,
"evaluation_employe" int8 NOT NULL,
"niveau" int8,
"appreciation" varchar(255) COLLATE "default",
CONSTRAINT "pk_evaluation_detail" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."evaluation_detail_id_seq" OWNED BY "grh"."evaluation_detail"."id";

CREATE SEQUENCE "grh"."evaluation_employe_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."evaluation_employe" (
"id" int4 DEFAULT nextval('"grh".evaluation_employe_id_seq'::regclass) NOT NULL,
"appreciation" varchar(255) COLLATE "default",
"note" float8,
"date_contestation" date,
"date_commission" date,
"objet_contestation" varchar(255) COLLATE "default",
"resultat" bool,
"motif" varchar(255) COLLATE "default",
"evaluation_periode" int8 NOT NULL,
"dossier_employe" int8 NOT NULL,
"note_revise" float8,
CONSTRAINT "pk_evaluation_employe" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."evaluation_employe_id_seq" OWNED BY "grh"."evaluation_employe"."id";

CREATE SEQUENCE "grh"."evaluation_periode_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."evaluation_periode" (
"id" int4 DEFAULT nextval('"grh".evaluation_periode_id_seq'::regclass) NOT NULL,
"date_evaluation" date NOT NULL,
"date_debut_periode" date NOT NULL,
"date_fin_periode" date NOT NULL,
"situation" int8 NOT NULL,
"ref_etablissement" int8 NOT NULL,
CONSTRAINT "pk_evaluation_periode" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."evaluation_periode_id_seq" OWNED BY "grh"."evaluation_periode"."id";

CREATE SEQUENCE "grh"."evaluation_session_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."evaluation_session_formation" (
"id" int4 DEFAULT nextval('"grh".evaluation_session_formation_id_seq'::regclass) NOT NULL,
"session_formation" int4,
"observation" varchar COLLATE "default",
"chapitre" int4,
"critere" int4,
"appreciation" int4,
CONSTRAINT "pk_grh_evaluation_session_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."evaluation_session_formation_id_seq" OWNED BY "grh"."evaluation_session_formation"."id";

ALTER TABLE "grh"."examen_medical" ADD COLUMN "libelle" int4;

ALTER TABLE "grh"."examen_medical" ADD COLUMN "visite_medicale" int4;

ALTER TABLE "grh"."examen_medical" ADD COLUMN "date_examen" date;

ALTER TABLE "grh"."examen_medical" ADD COLUMN "resume" varchar COLLATE "default";

ALTER TABLE "grh"."examen_medical" ADD CONSTRAINT "pk_grh_examen_medical" PRIMARY KEY ("id");

CREATE SEQUENCE "grh"."formateur_session_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 13
 CACHE 1;

SELECT setval('"grh"."formateur_session_formation_id_seq"', 13, true);

CREATE TABLE "grh"."formateur_session_formation" (
"id" int4 DEFAULT nextval('"grh".formateur_session_formation_id_seq'::regclass) NOT NULL,
"individu" int4,
"session_formation" int4,
CONSTRAINT "pk_grh_formateur" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."formateur_session_formation_id_seq" OWNED BY "grh"."formateur_session_formation"."id";

CREATE SEQUENCE "grh"."formation_catalogue_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."formation_catalogue" (
"id" int4 DEFAULT nextval('"grh".formation_catalogue_id_seq'::regclass) NOT NULL,
"code" varchar COLLATE "default",
"intitule" varchar COLLATE "default",
"objectif" varchar COLLATE "default",
"theme" int4,
"methode" int4,
"duree" int4,
"maximun_participant" int4,
"catalogue" int4,
CONSTRAINT "pk_grh_formation_catalogue" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."formation_catalogue_id_seq" OWNED BY "grh"."formation_catalogue"."id";

CREATE SEQUENCE "grh"."grade_cycle_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;

SELECT setval('"grh"."grade_cycle_formation_id_seq"', 2, true);

CREATE TABLE "grh"."grade_cycle_formation" (
"id" int4 DEFAULT nextval('"grh".grade_cycle_formation_id_seq'::regclass) NOT NULL,
"grade" int4,
"nb_candidat" int4,
"observation" varchar COLLATE "default",
"cycle_formation" int4,
CONSTRAINT "pk_grh_grade_cycle_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."grade_cycle_formation_id_seq" OWNED BY "grh"."grade_cycle_formation"."id";

CREATE SEQUENCE "grh"."habilete_poste_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."habilete_poste" (
"id" int4 DEFAULT nextval('"grh".habilete_poste_id_seq'::regclass) NOT NULL,
"mots_cles" varchar(255) COLLATE "default",
"description" varchar(255) COLLATE "default",
"type_habilete" int4 NOT NULL,
"niveau_habilete" int4 NOT NULL,
"poste_emploi" int4 NOT NULL,
CONSTRAINT "pk_habilete_poste_id" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."habilete_poste_id_seq" OWNED BY "grh"."habilete_poste"."id";

CREATE SEQUENCE "grh"."inscription_session_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."inscription_session_formation" (
"id" int4 DEFAULT nextval('"grh".inscription_session_formation_id_seq'::regclass) NOT NULL,
"session_formation" int4,
"employe" int4,
"date_demande" date,
"date_inscription" date,
"resultat" bool,
"motif" varchar COLLATE "default",
"present" bool,
CONSTRAINT "pk_grh_inscription_session_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."inscription_session_formation_id_seq" OWNED BY "grh"."inscription_session_formation"."id";

ALTER TABLE "grh"."medicament" ADD COLUMN "libelle" int4;

ALTER TABLE "grh"."medicament" ADD COLUMN "posologie" varchar COLLATE "default";

ALTER TABLE "grh"."medicament" ADD COLUMN "nb_jour" int4;

ALTER TABLE "grh"."medicament" ADD COLUMN "visite_medicale" int4;

ALTER TABLE "grh"."medicament" ADD CONSTRAINT "pk_grh_medicament" PRIMARY KEY ("id");

CREATE SEQUENCE "grh"."poste_emploi_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "grh"."poste_emploi" (
"id" int4 DEFAULT nextval('"grh".poste_emploi_id_seq'::regclass) NOT NULL,
"code" varchar(50) COLLATE "default" NOT NULL,
"resultat" varchar(250) COLLATE "default",
"date_creation" timestamp(6) NOT NULL,
"statut" bool,
"niveau_competence" int4 NOT NULL,
"niveau_qualification" int4 NOT NULL,
"ref_etablissement" int8 NOT NULL,
"objet" varchar(200) COLLATE "default" NOT NULL,
CONSTRAINT "pk_poste_emploi_id" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."poste_emploi_id_seq" OWNED BY "grh"."poste_emploi"."id";

CREATE SEQUENCE "grh"."programme_formation_grh_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

SELECT setval('"grh"."programme_formation_grh_id_seq"', 1, true);

CREATE TABLE "grh"."programme_formation_grh" (
"id" int4 DEFAULT nextval('"grh".programme_formation_grh_id_seq'::regclass) NOT NULL,
"intitule" varchar COLLATE "default",
"objectif" varchar COLLATE "default",
"date_debut" date,
"date_fin" date,
"code" varchar COLLATE "default",
CONSTRAINT "pk_grh_programme_formation_grh" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."programme_formation_grh_id_seq" OWNED BY "grh"."programme_formation_grh"."id";

CREATE SEQUENCE "grh"."session_formation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;

SELECT setval('"grh"."session_formation_id_seq"', 4, true);

CREATE TABLE "grh"."session_formation" (
"id" int4 DEFAULT nextval('"grh".session_formation_id_seq'::regclass) NOT NULL,
"code" varchar COLLATE "default",
"intitule" varchar COLLATE "default",
"date_debut" date,
"date_fin" date,
"cycle_formation" int4,
"externe" bool,
"lieu_externe" varchar COLLATE "default",
"organisme_formation" int4,
"lieu_interne" int4,
"nb_participant" int4,
"date_evaluation" date,
"satisfaisant" bool,
"observation" varchar COLLATE "default",
"situation" int4,
"formation_catalogue" int4,
CONSTRAINT "pk_grh_session_formation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "grh"."session_formation_id_seq" OWNED BY "grh"."session_formation"."id";

--ALTER TABLE "grh"."vaccin" DROP CONSTRAINT "fk_grh_vaccin_dossier_medical";

ALTER TABLE "grh"."vaccin" ADD COLUMN "employe" int4;

ALTER TABLE "grh"."vaccin" DROP COLUMN "dossier_medical";

ALTER TABLE "grh"."visite_medicale" DROP CONSTRAINT "fk_grh_visite_medic_dossier_medic";

ALTER TABLE "grh"."visite_medicale" ADD COLUMN "employe" int4;

ALTER TABLE "grh"."visite_medicale" ADD COLUMN "avec_examen" bool;

ALTER TABLE "grh"."visite_medicale" ADD COLUMN "fin_visite" bool;

ALTER TABLE "grh"."visite_medicale" ADD COLUMN "fin_examen" bool;

ALTER TABLE "grh"."visite_medicale" DROP COLUMN "dossier_medical";

DROP TABLE "grh"."allergie";

DROP TABLE "grh"."antecedant";

DROP TABLE "grh"."pathologie";

CREATE INDEX "fki__grh_detail_besoin_formation" ON "grh"."detail_besoin_formation" USING btree (besoin_formation);

CREATE INDEX "fki_affectation_poste_dossier_employe" ON "grh"."affectation_emploi" USING btree (dossier_employe);

CREATE INDEX "fki_affectation_poste_poste_emploi" ON "grh"."affectation_emploi" USING btree (poste_emploi);

CREATE INDEX "fki_besoin_formation_etablissment" ON "grh"."besoin_formation" USING btree (etablissement);

CREATE INDEX "fki_besoin_formt_groupe" ON "grh"."besoin_formation" USING btree (groupe);

CREATE INDEX "fki_connaissance_poste_nc_type_connaissance" ON "grh"."connaissance_poste" USING btree (type_connaissance);

CREATE INDEX "fki_connaissance_poste_poste_emploi" ON "grh"."connaissance_poste" USING btree (poste_emploi);

CREATE INDEX "fki_detail_besoin_formation_catalogue" ON "grh"."detail_besoin_formation" USING btree (formation);

CREATE INDEX "fki_detail_besoin_formation_corps" ON "grh"."detail_besoin_formation" USING btree (corps);

CREATE INDEX "fki_eval_session_format_critere" ON "grh"."evaluation_session_formation" USING btree (critere);

CREATE INDEX "fki_evaluation_detail_nc_niveau" ON "grh"."evaluation_detail" USING btree (niveau);

CREATE INDEX "fki_evaluation_detail_nomenclature" ON "grh"."evaluation_detail" USING btree (critere);

CREATE INDEX "fki_evaluation_employe_dossier_employe" ON "grh"."evaluation_employe" USING btree (dossier_employe);

CREATE INDEX "fki_evaluation_employe_evaluation_periode" ON "grh"."evaluation_employe" USING btree (evaluation_periode);

CREATE INDEX "fki_evaluation_periode_ref_etablissement" ON "grh"."evaluation_periode" USING btree (ref_etablissement);

CREATE INDEX "fki_evaluation_periode_situation_entite" ON "grh"."evaluation_periode" USING btree (situation);

CREATE INDEX "fki_evalution_detail_evaluation_employe" ON "grh"."evaluation_detail" USING btree (evaluation_employe);

CREATE INDEX "fki_grh_besoin_form_employe" ON "grh"."besoin_formation" USING btree (employe);

CREATE INDEX "fki_grh_besoin_form_structure" ON "grh"."besoin_formation" USING btree (structure);

CREATE INDEX "fki_grh_besoin_formation_situation" ON "grh"."besoin_formation" USING btree (situation);

CREATE INDEX "fki_grh_besoin_formation_type" ON "grh"."besoin_formation" USING btree (type_besoin);

CREATE INDEX "fki_grh_cycle_form_programme" ON "grh"."cycle_formation" USING btree (programme_formation);

CREATE INDEX "fki_grh_cycle_formation_situation" ON "grh"."cycle_formation" USING btree (situation);

CREATE INDEX "fki_grh_detail_besoin_formation_grade" ON "grh"."detail_besoin_formation" USING btree (grade);

CREATE INDEX "fki_grh_doss_niveau_comptence_nc_nomen" ON "grh"."dossier_employe" USING btree (niveau_competence);

CREATE INDEX "fki_grh_doss_niveau_qualification_nc_nomen" ON "grh"."dossier_employe" USING btree (niveau_qualification);

CREATE INDEX "fki_grh_eval_session_format_appreciation" ON "grh"."evaluation_session_formation" USING btree (appreciation);

CREATE INDEX "fki_grh_eval_session_format_chapitre" ON "grh"."evaluation_session_formation" USING btree (chapitre);

CREATE INDEX "fki_grh_evlal_session_formation" ON "grh"."evaluation_session_formation" USING btree (session_formation);

CREATE INDEX "fki_grh_examen_medical_nomenclature" ON "grh"."examen_medical" USING btree (libelle);

CREATE INDEX "fki_grh_examen_medical_visite" ON "grh"."examen_medical" USING btree (visite_medicale);

CREATE INDEX "fki_grh_formateur_ref_individu" ON "grh"."formateur_session_formation" USING btree (individu);

CREATE INDEX "fki_grh_formateur_session" ON "grh"."formateur_session_formation" USING btree (session_formation);

CREATE INDEX "fki_grh_formation_catalogue" ON "grh"."formation_catalogue" USING btree (catalogue);

CREATE INDEX "fki_grh_formation_methode" ON "grh"."formation_catalogue" USING btree (methode);

CREATE INDEX "fki_grh_formation_theme" ON "grh"."formation_catalogue" USING btree (theme);

CREATE INDEX "fki_grh_grade_cycle_formation_cycle_formation" ON "grh"."grade_cycle_formation" USING btree (cycle_formation);

CREATE INDEX "fki_grh_grade_cycle_formation_grade" ON "grh"."grade_cycle_formation" USING btree (grade);

CREATE INDEX "fki_grh_inscription_session" ON "grh"."inscription_session_formation" USING btree (session_formation);

CREATE INDEX "fki_grh_inscription_session_employe" ON "grh"."inscription_session_formation" USING btree (employe);

CREATE INDEX "fki_grh_medicament_nomenclature" ON "grh"."medicament" USING btree (libelle);

CREATE INDEX "fki_grh_medicament_visite" ON "grh"."medicament" USING btree (visite_medicale);

CREATE INDEX "fki_grh_session_cycle_formation" ON "grh"."session_formation" USING btree (cycle_formation);

CREATE INDEX "fki_grh_session_formation_catalogue" ON "grh"."session_formation" USING btree (formation_catalogue);

CREATE INDEX "fki_grh_session_formation_lieu_interne" ON "grh"."session_formation" USING btree (lieu_interne);

CREATE INDEX "fki_grh_session_formation_organisme" ON "grh"."session_formation" USING btree (organisme_formation);

CREATE INDEX "fki_grh_session_formation_situation" ON "grh"."session_formation" USING btree (situation);

CREATE INDEX "fki_grh_vaccin_employe" ON "grh"."vaccin" USING btree (employe);

CREATE INDEX "fki_grh_vaccin_nomenclature" ON "grh"."vaccin" USING btree (libelle);

CREATE INDEX "fki_grh_visite_medicale_employe" ON "grh"."visite_medicale" USING btree (employe);

CREATE INDEX "fki_habilete_poste_nc_niveau_habilete" ON "grh"."habilete_poste" USING btree (niveau_habilete);

CREATE INDEX "fki_habilete_poste_nc_type_habilite" ON "grh"."habilete_poste" USING btree (type_habilete);

CREATE INDEX "fki_habilete_poste_poste_emploi" ON "grh"."habilete_poste" USING btree (poste_emploi);

CREATE INDEX "fki_poste_emploi_nc_niveau_competence" ON "grh"."poste_emploi" USING btree (niveau_competence);

CREATE INDEX "fki_poste_emploi_nc_niveau_qualification" ON "grh"."poste_emploi" USING btree (niveau_qualification);

CREATE INDEX "fki_poste_emploi_ref_etablissement" ON "grh"."poste_emploi" USING btree (ref_etablissement);

--DROP INDEX "grh"."fki_grh_allergie_dossier_medical";

--DROP INDEX "grh"."fki_grh_antecedant_dossier_medical";

--DROP INDEX "grh"."fki_grh_pathologie_dossier_medical";


--DROP INDEX "grh"."fki_grh_vaccin_dossier_medical";

--DROP INDEX "grh"."fki_grh_visite_medic_dossier_medic";

--DROP SEQUENCE "grh"."pathologie_id_seq";

--DROP SEQUENCE "grh"."antecedant_id_seq";

--DROP SEQUENCE "grh"."allergie_id_seq";

ALTER TABLE "grh"."affectation_emploi" ADD CONSTRAINT "fk_affectation_poste_dossier_employe" FOREIGN KEY ("dossier_employe") REFERENCES 

"grh"."dossier_employe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."affectation_emploi" ADD CONSTRAINT "fk_affectation_poste_poste_emploi" FOREIGN KEY ("poste_emploi") REFERENCES 

"grh"."poste_emploi" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_grh_besoin_formation_situation" FOREIGN KEY ("situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_besoin_formt_groupe" FOREIGN KEY ("groupe") REFERENCES "ppm"."ref_groupe" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_grh_besoin_formation_type" FOREIGN KEY ("type_besoin") REFERENCES "nc"."nomenclature" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_grh_besoin_form_structure" FOREIGN KEY ("structure") REFERENCES "ppm"."ref_structure" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_grh_besoin_form_employe" FOREIGN KEY ("employe") REFERENCES "grh"."dossier_employe" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."besoin_formation" ADD CONSTRAINT "fk_besoin_formation_etablissment" FOREIGN KEY ("etablissement") REFERENCES 

"ppm"."ref_etablissement" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."connaissance_poste" ADD CONSTRAINT "fk_connaissance_poste_poste_emploi" FOREIGN KEY ("poste_emploi") REFERENCES 

"grh"."poste_emploi" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."connaissance_poste" ADD CONSTRAINT "fk_connaissance_poste_nc_type_connaissance" FOREIGN KEY ("type_connaissance") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."cycle_formation" ADD CONSTRAINT "fk_grh_cycle_formation_programme" FOREIGN KEY ("programme_formation") REFERENCES 

"grh"."programme_formation_grh" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."cycle_formation" ADD CONSTRAINT "fk_grh_cycle_formation_situation" FOREIGN KEY ("situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."detail_besoin_formation" ADD CONSTRAINT "fk_detail_besoin_formation_corps" FOREIGN KEY ("corps") REFERENCES "grh"."corps" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."detail_besoin_formation" ADD CONSTRAINT "fk_detail_besoin_formation_catalogue" FOREIGN KEY ("formation") REFERENCES 

"grh"."formation_catalogue" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."detail_besoin_formation" ADD CONSTRAINT "fk_grh_detail_besoin_formation_grade" FOREIGN KEY ("grade") REFERENCES "grh"."grade" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."detail_besoin_formation" ADD CONSTRAINT "fk__grh_detail_besoin_formation" FOREIGN KEY ("besoin_formation") REFERENCES 

"grh"."besoin_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."dossier_employe" ADD CONSTRAINT "fk_grh_doss_niveau_qualification_nc_nomen" FOREIGN KEY ("niveau_qualification") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."dossier_employe" ADD CONSTRAINT "fk_grh_doss_niveau_comptence_nc_nomen" FOREIGN KEY ("niveau_competence") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_detail" ADD CONSTRAINT "fk_evalution_detail_evaluation_employe" FOREIGN KEY ("evaluation_employe") REFERENCES 

"grh"."evaluation_employe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_detail" ADD CONSTRAINT "fk_evaluation_detail_nomenclature" FOREIGN KEY ("critere") REFERENCES "nc"."nomenclature" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_detail" ADD CONSTRAINT "fk_evaluation_detail_nc_niveau" FOREIGN KEY ("niveau") REFERENCES "nc"."nomenclature" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_employe" ADD CONSTRAINT "fk_evaluation_employe_evaluation_periode" FOREIGN KEY ("evaluation_periode") REFERENCES 

"grh"."evaluation_periode" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_employe" ADD CONSTRAINT "fk_evaluation_employe_dossier_employe" FOREIGN KEY ("dossier_employe") REFERENCES 

"grh"."dossier_employe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_periode" ADD CONSTRAINT "fk_evaluation_periode_situation_entite" FOREIGN KEY ("situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_periode" ADD CONSTRAINT "fk_evaluation_periode_ref_etablissement" FOREIGN KEY ("ref_etablissement") REFERENCES 

"ppm"."ref_etablissement" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_session_formation" ADD CONSTRAINT "fk_grh_eval_session_format_appreciation" FOREIGN KEY ("appreciation") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_session_formation" ADD CONSTRAINT "fk_grh_eval_session_format_critere" FOREIGN KEY ("critere") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_session_formation" ADD CONSTRAINT "fk_grh_eval_session_format_chapitre" FOREIGN KEY ("chapitre") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."evaluation_session_formation" ADD CONSTRAINT "fk_grh_evlal_session_formation" FOREIGN KEY ("session_formation") REFERENCES 

"grh"."session_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."examen_medical" ADD CONSTRAINT "fk_grh_examen_medical_nomenclature" FOREIGN KEY ("libelle") REFERENCES "nc"."nomenclature" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."examen_medical" ADD CONSTRAINT "fk_grh_examen_medical_visite" FOREIGN KEY ("visite_medicale") REFERENCES 

"grh"."visite_medicale" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."formateur_session_formation" ADD CONSTRAINT "fk_grh_formateur_ref_individu" FOREIGN KEY ("individu") REFERENCES 

"ppm"."ref_individu" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."formateur_session_formation" ADD CONSTRAINT "fk_grh_formateur_session" FOREIGN KEY ("session_formation") REFERENCES 

"grh"."session_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."formation_catalogue" ADD CONSTRAINT "fk_grh_formation_methode" FOREIGN KEY ("methode") REFERENCES "nc"."nomenclature" ("id") 

ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."formation_catalogue" ADD CONSTRAINT "fk_grh_formation_theme" FOREIGN KEY ("theme") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."formation_catalogue" ADD CONSTRAINT "fk_grh_formation_catalogue" FOREIGN KEY ("catalogue") REFERENCES 

"grh"."catalogue_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."grade_cycle_formation" ADD CONSTRAINT "fk_grh_grade_cycle_formation_cycle_formation" FOREIGN KEY ("cycle_formation") 

REFERENCES "grh"."cycle_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."grade_cycle_formation" ADD CONSTRAINT "fk_grh_grade_cycle_formation_grade" FOREIGN KEY ("grade") REFERENCES "grh"."grade" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."habilete_poste" ADD CONSTRAINT "fk_habilete_poste_nc_niveau_habilete" FOREIGN KEY ("niveau_habilete") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."habilete_poste" ADD CONSTRAINT "fk_habilete_poste_nc_type_habilite" FOREIGN KEY ("type_habilete") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."habilete_poste" ADD CONSTRAINT "fk_habilete_poste_poste_emploi" FOREIGN KEY ("poste_emploi") REFERENCES "grh"."poste_emploi" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."inscription_session_formation" ADD CONSTRAINT "fk_grh_inscription_session" FOREIGN KEY ("session_formation") REFERENCES 

"grh"."session_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."inscription_session_formation" ADD CONSTRAINT "fk_grh_inscription_session_employe" FOREIGN KEY ("employe") REFERENCES 

"grh"."dossier_employe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."medicament" ADD CONSTRAINT "fk_grh_medicament_nomenclature" FOREIGN KEY ("libelle") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."medicament" ADD CONSTRAINT "fk_grh_medicament_visite" FOREIGN KEY ("visite_medicale") REFERENCES "grh"."visite_medicale" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."poste_emploi" ADD CONSTRAINT "fk_poste_emploi_nc_niveau_qualification" FOREIGN KEY ("niveau_qualification") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."poste_emploi" ADD CONSTRAINT "fk_poste_emploi_nc_niveau_competence" FOREIGN KEY ("niveau_competence") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."poste_emploi" ADD CONSTRAINT "fk_poste_emploi_ref_etablissement" FOREIGN KEY ("ref_etablissement") REFERENCES 

"ppm"."ref_etablissement" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."session_formation" ADD CONSTRAINT "fk_grh_session_formation_situation" FOREIGN KEY ("situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."session_formation" ADD CONSTRAINT "fk_grh_session_cycle_formation" FOREIGN KEY ("cycle_formation") REFERENCES 

"grh"."cycle_formation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."session_formation" ADD CONSTRAINT "fk_grh_session_formation_catalogue" FOREIGN KEY ("formation_catalogue") REFERENCES 

"grh"."formation_catalogue" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."session_formation" ADD CONSTRAINT "fk_grh_session_formation_lieu_interne" FOREIGN KEY ("lieu_interne") REFERENCES 

"ppm"."ref_lieu" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."session_formation" ADD CONSTRAINT "fk_grh_session_formation_organisme" FOREIGN KEY ("organisme_formation") REFERENCES 

"ppm"."ref_structure" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."vaccin" ADD CONSTRAINT "fk_grh_vaccin_nomenclature" FOREIGN KEY ("libelle") REFERENCES "nc"."nomenclature" ("id") ON DELETE NO 

ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."vaccin" ADD CONSTRAINT "fk_grh_vaccin_employe" FOREIGN KEY ("employe") REFERENCES "grh"."dossier_employe" ("id") ON DELETE NO 

ACTION ON UPDATE NO ACTION;

ALTER TABLE "grh"."visite_medicale" ADD CONSTRAINT "fk_grh_visite_medicale_employe" FOREIGN KEY ("employe") REFERENCES "grh"."dossier_employe" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;



-- gfc

SELECT setval('"gfc"."affectation_etab_chapitre_article_id_seq"', 49, true);

CREATE SEQUENCE "gfc"."detail_engagement_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 9
 CACHE 1;

SELECT setval('"gfc"."detail_engagement_marche_id_seq"', 9, true);

CREATE SEQUENCE "gfc"."detail_realisation_equipement_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

SELECT setval('"gfc"."detail_realisation_equipement_id_seq"', 3, true);

CREATE SEQUENCE "gfc"."detail_realisation_prestation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;

SELECT setval('"gfc"."detail_realisation_prestation_id_seq"', 2, true);

CREATE SEQUENCE "gfc"."detail_realisation_produit_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE SEQUENCE "gfc"."document_realisation_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;

SELECT setval('"gfc"."document_realisation_marche_id_seq"', 4, true);

CREATE SEQUENCE "gfc"."dossier_mission_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE SEQUENCE "gfc"."dossier_paiement_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE SEQUENCE "gfc"."engagement_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 22
 CACHE 1;

SELECT setval('"gfc"."engagement_marche_id_seq"', 22, true);

CREATE SEQUENCE "gfc"."equipement_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 18
 CACHE 1;

SELECT setval('"gfc"."equipement_marche_id_seq"', 18, true);

CREATE SEQUENCE "gfc"."evenement_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 23
 CACHE 1;

SELECT setval('"gfc"."evenement_marche_id_seq"', 23, true);

CREATE SEQUENCE "gfc"."marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;

SELECT setval('"gfc"."marche_id_seq"', 14, true);

CREATE SEQUENCE "gfc"."mission_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE SEQUENCE "gfc"."prestation_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;

SELECT setval('"gfc"."prestation_marche_id_seq"', 14, true);

CREATE SEQUENCE "gfc"."produit_marche_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

SELECT setval('"gfc"."programme_national_id_seq"', 24, true);

CREATE SEQUENCE "gfc"."tarif_mission_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "gfc"."detail_engagement_marche" (
"id" int4 DEFAULT nextval(('gfc.detail_engagement_marche_id_seq'::text)::regclass) NOT NULL,
"id_demande_engagement" int4 NOT NULL,
"id_chapitre" int4 NOT NULL,
"id_article" int4 NOT NULL,
"montant" numeric(10,2) NOT NULL,
"observation" varchar(200) COLLATE "default",
"id_structure" int4 NOT NULL,
"id_situation" int4,
CONSTRAINT "pk_detail_engagement_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."detail_realisation_equipement" (
"id" int4 DEFAULT nextval(('gfc.detail_realisation_equipement_id_seq'::text)::regclass) NOT NULL,
"id_equipement_marche" int4 NOT NULL,
"id_document_realisation" int4 NOT NULL,
"quantite" int4 NOT NULL,
"montant_realise" numeric(10,2) NOT NULL,
"observation" varchar(200) COLLATE "default",
CONSTRAINT "pk_detail_realisation_equipement" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."detail_realisation_prestation" (
"id" int4 DEFAULT nextval(('gfc.detail_realisation_prestation_id_seq'::text)::regclass) NOT NULL,
"id_prestation_marche" int4 NOT NULL,
"id_document_realisation" int4 NOT NULL,
"quantite" int4 NOT NULL,
"montant_realise" numeric(10,2) NOT NULL,
"observation" varchar(200) COLLATE "default",
CONSTRAINT "pk_detail_realisation_prestation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."detail_realisation_produit" (
"id" int4 DEFAULT nextval(('gfc.detail_realisation_produit_id_seq'::text)::regclass) NOT NULL,
"id_produit_marche" int4 NOT NULL,
"id_document_realisation" int4 NOT NULL,
"quantite" numeric(10,2) NOT NULL,
"montant_realise" numeric(10,2) NOT NULL,
"id_bon_livraison" int4,
"reference_bon_livraison" varchar(50) COLLATE "default" NOT NULL,
"livre_par" varchar(200) COLLATE "default" NOT NULL,
"observation" varchar(200) COLLATE "default",
CONSTRAINT "pk_detail_realisation_produit" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."document_realisation_marche" (
"id" int4 DEFAULT nextval(('gfc.document_realisation_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4 NOT NULL,
"id_type_document" int4 NOT NULL,
"montant_ht" numeric(10,2) NOT NULL,
"montant_tva" numeric(10,2) NOT NULL,
"montant_ttc" numeric(10,2) NOT NULL,
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
"id_dossier_paiement" int4,
"date_document" date NOT NULL,
CONSTRAINT "pk_document_realisation_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."dossier_mission" (
"id" int4 DEFAULT nextval(('gfc.dossier_mission_id_seq'::text)::regclass) NOT NULL,
"id_mission" int4 NOT NULL,
"id_employe" int4 NOT NULL,
"date_debut" timestamp(6) NOT NULL,
"date_fin" timestamp(6) NOT NULL,
"id_tarif_mission" int4 NOT NULL,
"nombre_resauration" int4,
"frais_restauration" numeric(10,2),
"nombre_hebergement" int4,
"frais_hebergement" numeric(10,2),
"frais_voyage" numeric(10,2),
"montant_total" numeric(10,2) NOT NULL,
"montant_avance" numeric(10,2) NOT NULL,
"date_signature" timestamp(6),
"nombre_resauration_reel" int4,
"frais_restauration_reel" numeric(10,2),
"nombre_hebergement_reel" int4,
"frais_hebergement_reel" numeric(10,2),
"frais_voyage_reel" numeric(10,2),
"date_debut_reel" timestamp(6),
"date_fin_reel" timestamp(6),
"reste_a_payer" numeric(10,2),
"montant_a_rembourser" numeric(10,2),
"id_situation" int4,
CONSTRAINT "pk_dossier_mission" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."dossier_paiement" (
"id" int4 DEFAULT nextval(('gfc.dossier_paiement_id_seq'::text)::regclass) NOT NULL,
"id_document_realisation_marche" int4,
"objet" varchar(200) COLLATE "default" NOT NULL,
"date_dossier" timestamp(6) NOT NULL,
"id_mode_paiement" int4 NOT NULL,
"reference_bancaire" varchar(50) COLLATE "default" NOT NULL,
"id_situation" int4,
"id_dossier_mission" int4,
CONSTRAINT "pk_dossier_payment" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."engagement_marche" (
"id" int4 DEFAULT nextval(('gfc.engagement_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4 NOT NULL,
"numero" varchar(50) COLLATE "default" NOT NULL,
"date_demande" timestamp(6) NOT NULL,
"objet" varchar(200) COLLATE "default" NOT NULL,
"montant_a_engager" numeric(10,2) NOT NULL,
"id_type_engagement" int4 NOT NULL,
"numero_fiche" varchar(50) COLLATE "default",
"date_signature" timestamp(6),
"numero_visa" varchar(50) COLLATE "default",
"date_visa" timestamp(6),
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
"id_nature_engagement" int4 NOT NULL,
CONSTRAINT "pk_engagement_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."equipement_marche" (
"id" int4 DEFAULT nextval(('gfc.equipement_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4,
"id_equipement" int4 NOT NULL,
"quantite" int4 NOT NULL,
"prix_unitaire" numeric(10,2) NOT NULL,
"taux_tva" numeric(10,2),
"montant_ttc" numeric(10,2) NOT NULL,
CONSTRAINT "pk_equipement_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."evenement_marche" (
"id" int4 DEFAULT nextval(('gfc.evenement_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4 NOT NULL,
"date_evenement" timestamp(6) NOT NULL,
"id_type_evenement" int4 NOT NULL,
"description" varchar(200) COLLATE "default",
"impact" varchar(200) COLLATE "default",
"mesure_prise" varchar(200) COLLATE "default",
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
CONSTRAINT "pk_evenement_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."marche" (
"id" int4 DEFAULT nextval(('gfc.marche_id_seq'::text)::regclass) NOT NULL,
"id_contrat" int4 NOT NULL,
"intitule_fr" varchar(100) COLLATE "default" NOT NULL,
"intitule_ar" varchar(100) COLLATE "default",
"id_type_marche" int4 NOT NULL,
"id_etablissement" int4 NOT NULL,
"id_structure" int4,
"id_contact" int4 NOT NULL,
"montant" numeric(10,2),
"montant_engage" numeric(10,2),
"id_mode_passation" int4 NOT NULL,
"date_approbation" timestamp(6) NOT NULL,
"date_publication" timestamp(6) NOT NULL,
"date_signature" timestamp(6),
"prestation" bool,
"equipement" bool,
"produit" bool,
"date_ods" timestamp(6),
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
"id_type_cloture" int4,
"date_cloture" timestamp(6),
"date_provisoire" timestamp(6),
"date_reception_definitive" timestamp(6),
"date_debut_garantie" timestamp(6),
"date_fin_garantie" timestamp(6),
CONSTRAINT "pk_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."mission" (
"id" int4 DEFAULT nextval(('mission_id_seq'::text)::regclass) NOT NULL,
"numero" varchar(20) COLLATE "default" NOT NULL,
"date_demande" timestamp(6) NOT NULL,
"objet" varchar(200) COLLATE "default" NOT NULL,
"date_debut" timestamp(6) NOT NULL,
"date_fin" timestamp(6) NOT NULL,
"id_type_mission" int4 NOT NULL,
"id_nature_mission" int4 NOT NULL,
"id_pays" int4 NOT NULL,
"id_wilaya" int4,
"ville" varchar(100) COLLATE "default",
"id_etablissement" int4,
"id_structure" int4,
"etablissement" varchar(200) COLLATE "default",
"structure" varchar(200) COLLATE "default",
"adresse" varchar(200) COLLATE "default",
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
CONSTRAINT "pk_mission" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."prestation_marche" (
"id" int4 DEFAULT nextval(('gfc.prestation_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4 NOT NULL,
"description" varchar(200) COLLATE "default" NOT NULL,
"id_type_prestation" int4 NOT NULL,
"quantite" numeric(10,2) NOT NULL,
"prix_unitaire" numeric(10,2) NOT NULL,
"taux_tva" numeric(10,2),
"montant_ttc" numeric(10,2) NOT NULL,
CONSTRAINT "pk_presetation_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE TABLE "gfc"."produit_marche" (
"id" int4 DEFAULT nextval(('gfc.produit_marche_id_seq'::text)::regclass) NOT NULL,
"id_marche" int4 NOT NULL,
"id_produit" int4 NOT NULL,
"quantite" numeric(10,2) NOT NULL,
"id_unite" int4 NOT NULL,
"prix_unitaire" numeric(10,2),
"taux_tva" numeric(10,2),
"montant_ttc" numeric(10,2) NOT NULL,
CONSTRAINT "pk_produit_marche" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER TABLE "gfc"."programme_national" ALTER COLUMN "code" SET NOT NULL;

ALTER TABLE "gfc"."programme_national" ADD CONSTRAINT "programme_national_code_key" UNIQUE ("code");

CREATE TABLE "gfc"."tarif_mission" (
"id" int4 DEFAULT nextval(('gfc.tarif_mission_id_seq'::text)::regclass) NOT NULL,
"id_type_tarif" int4 NOT NULL,
"frais_restauration" numeric(10,2) NOT NULL,
"frais_hebergement" numeric(10,2) NOT NULL,
"total_journalier" numeric(10,2) NOT NULL,
"id_categorie_min" int4,
"id_categorie_max" int4,
"fonction_superieure" bool,
"mission_etrange" bool,
"actif" bool DEFAULT true NOT NULL,
"observation" varchar(200) COLLATE "default",
"id_situation" int4,
CONSTRAINT "pk_tarif_mission" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE UNIQUE INDEX "programme__national_code_key" ON "gfc"."programme_national" USING btree (code);

ALTER TABLE "gfc"."detail_engagement_marche" ADD CONSTRAINT "fk_detail_engagement_marche_id_article" FOREIGN KEY ("id_article") REFERENCES 

"gfc"."article" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_engagement_marche" ADD CONSTRAINT "detail_engagement_marche_id_structure_fkey" FOREIGN KEY ("id_structure") REFERENCES 

"ppm"."ref_structure" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_engagement_marche" ADD CONSTRAINT "detail_engagement_marche_id_situation_fkey" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_engagement_marche" ADD CONSTRAINT "fk_detail_eng_eng_marche" FOREIGN KEY ("id_demande_engagement") REFERENCES 

"gfc"."engagement_marche" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_engagement_marche" ADD CONSTRAINT "fk_detail_engagement_marche_id_chapitre" FOREIGN KEY ("id_chapitre") REFERENCES 

"gfc"."chapitre" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_equipement" ADD CONSTRAINT "fk_detail_real_equip_doc_realisation" FOREIGN KEY ("id_document_realisation") 

REFERENCES "gfc"."document_realisation_marche" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_equipement" ADD CONSTRAINT "fk_detail_real_equip_equip_marche" FOREIGN KEY ("id_equipement_marche") 

REFERENCES "gfc"."equipement_marche" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_prestation" ADD CONSTRAINT "fk_detail_real_pres_pres_marche" FOREIGN KEY ("id_prestation_marche") 

REFERENCES "gfc"."prestation_marche" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_prestation" ADD CONSTRAINT "fk_detail_real_pres_doc_realisation" FOREIGN KEY ("id_document_realisation") 

REFERENCES "gfc"."document_realisation_marche" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_produit" ADD CONSTRAINT "fk_detail_real_prod_doc_realisation" FOREIGN KEY ("id_document_realisation") 

REFERENCES "gfc"."document_realisation_marche" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."detail_realisation_produit" ADD CONSTRAINT "fk_detail_real_prod_prod_marche" FOREIGN KEY ("id_produit_marche") REFERENCES 

"gfc"."produit_marche" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."document_realisation_marche" ADD CONSTRAINT "document_realisation_marche_id_dossier_paiement_fkey" FOREIGN KEY 

("id_dossier_paiement") REFERENCES "gfc"."dossier_paiement" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."document_realisation_marche" ADD CONSTRAINT "fk_document_realisation_marche_id_type_document" FOREIGN KEY ("id_type_document") 

REFERENCES "nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."document_realisation_marche" ADD CONSTRAINT "fk_document_realisation_marche_id_situation" FOREIGN KEY ("id_situation") 

REFERENCES "bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."document_realisation_marche" ADD CONSTRAINT "fk_doc_real_march_march" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" 

("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_mission" ADD CONSTRAINT "dossier_mission_id_employe_fkey" FOREIGN KEY ("id_employe") REFERENCES 

"grh"."dossier_employe" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_mission" ADD CONSTRAINT "fk_dossier_mission_mission" FOREIGN KEY ("id_mission") REFERENCES "gfc"."mission" ("id") ON 

DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_mission" ADD CONSTRAINT "fk_dossier_mission_tarif_mission" FOREIGN KEY ("id_tarif_mission") REFERENCES 

"gfc"."tarif_mission" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_mission" ADD CONSTRAINT "dossier_mission_id_situation_fkey" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_paiement" ADD CONSTRAINT "fk_dossier_paiement_dossier_mission" FOREIGN KEY ("id_dossier_mission") REFERENCES 

"gfc"."dossier_mission" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "gfc"."dossier_paiement" ADD CONSTRAINT "fk_dossier_payment_id_situation" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_paiement" ADD CONSTRAINT "fk_dossier_payment_id_mode_payment" FOREIGN KEY ("id_mode_paiement") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."dossier_paiement" ADD CONSTRAINT "fk_dossier_payment_doc_rea_marche" FOREIGN KEY ("id_document_realisation_marche") REFERENCES 

"gfc"."document_realisation_marche" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."engagement_marche" ADD CONSTRAINT "fk_engagement_marche_marche" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" ("id") ON 

DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."engagement_marche" ADD CONSTRAINT "engagement_marche_id_nature_engagement_fkey" FOREIGN KEY ("id_nature_engagement") 

REFERENCES "nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE "gfc"."engagement_marche" ADD CONSTRAINT "fk_engagement_marche_id_type_engagement" FOREIGN KEY ("id_type_engagement") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."engagement_marche" ADD CONSTRAINT "fk_engagement_marche_id_situation" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."equipement_marche" ADD CONSTRAINT "fk_equipement_marche_marche" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" ("id") ON 

DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."equipement_marche" ADD CONSTRAINT "fk_equipement_marche_id_equipement" FOREIGN KEY ("id_equipement") REFERENCES 

"ppm"."ref_equipement" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."evenement_marche" ADD CONSTRAINT "fk_evenement_marche_id_situation" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."evenement_marche" ADD CONSTRAINT "fk_evenement_marche_id_type_evenement" FOREIGN KEY ("id_type_evenement") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."evenement_marche" ADD CONSTRAINT "fk_evenement_marche_marche" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" ("id") ON 

DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_contact" FOREIGN KEY ("id_contact") REFERENCES "ppm"."ref_individu" ("id") ON DELETE 

RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_type_marche" FOREIGN KEY ("id_type_marche") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_type_cloture" FOREIGN KEY ("id_type_cloture") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_structure" FOREIGN KEY ("id_structure") REFERENCES "ppm"."ref_structure" ("id") ON DELETE 

RESTRICT ON UPDATE RESTRICT;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_situation" FOREIGN KEY ("id_situation") REFERENCES "bpm"."situation_entite" ("id") ON 

DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_mode_passation" FOREIGN KEY ("id_mode_passation") REFERENCES "nc"."nomenclature" ("id") 

ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_etablissement" FOREIGN KEY ("id_etablissement") REFERENCES "ppm"."ref_etablissement" 

("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."marche" ADD CONSTRAINT "fk_marche_id_contrat" FOREIGN KEY ("id_contrat") REFERENCES "ppm"."ref_contrat" ("id") ON DELETE 

RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_pays_fkey" FOREIGN KEY ("id_pays") REFERENCES "nc"."nomenclature" ("id") ON DELETE 

RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_etablissement_fkey" FOREIGN KEY ("id_etablissement") REFERENCES "ppm"."ref_etablissement" 

("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_wilaya_fkey" FOREIGN KEY ("id_wilaya") REFERENCES "nc"."nomenclature" ("id") ON DELETE 

RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_type_mission_fkey" FOREIGN KEY ("id_type_mission") REFERENCES "nc"."nomenclature" ("id") 

ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_structure_fkey" FOREIGN KEY ("id_structure") REFERENCES "ppm"."ref_structure" ("id") ON 

DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_situation_fkey" FOREIGN KEY ("id_situation") REFERENCES "bpm"."situation_i18n" ("id") ON 

DELETE SET NULL ON UPDATE RESTRICT;

ALTER TABLE "gfc"."mission" ADD CONSTRAINT "mission_id_nature_mission_fkey" FOREIGN KEY ("id_nature_mission") REFERENCES "nc"."nomenclature" 

("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."prestation_marche" ADD CONSTRAINT "fk_prestation_marche_id_type_prestation" FOREIGN KEY ("id_type_prestation") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."prestation_marche" ADD CONSTRAINT "prestation_marche_id_marche_fkey" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" 

("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."produit_marche" ADD CONSTRAINT "fk_produit_marche_id_unite" FOREIGN KEY ("id_unite") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."produit_marche" ADD CONSTRAINT "fk_produit_marche_marche" FOREIGN KEY ("id_marche") REFERENCES "gfc"."marche" ("id") ON DELETE 

CASCADE ON UPDATE CASCADE;

ALTER TABLE "gfc"."tarif_mission" ADD CONSTRAINT "tarif_mission_id_type_tarif_fkey" FOREIGN KEY ("id_type_tarif") REFERENCES "nc"."nomenclature" 

("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."tarif_mission" ADD CONSTRAINT "tarif_mission_id_situation_fkey" FOREIGN KEY ("id_situation") REFERENCES 

"bpm"."situation_entite" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "gfc"."tarif_mission" ADD CONSTRAINT "tarif_mission_id_categorie_max_fkey" FOREIGN KEY ("id_categorie_max") REFERENCES 

"grh"."categorie_professionnelle" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "gfc"."tarif_mission" ADD CONSTRAINT "tarif_mission_id_categorie_min_fkey" FOREIGN KEY ("id_categorie_min") REFERENCES 

"grh"."categorie_professionnelle" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;


--recherche

SELECT setval('"recherche"."axe_id_seq"', 30, true);

SELECT setval('"recherche"."demande_activite_id_seq"', 14, true);

SELECT setval('"recherche"."demande_budget_id_seq"', 14, true);

SELECT setval('"recherche"."demande_credit_id_seq"', 7, true);

SELECT setval('"recherche"."demande_equipement_id_seq"', 16, true);

SELECT setval('"recherche"."demande_operation_id_seq"', 6, true);

SELECT setval('"recherche"."demande_resultat_id_seq"', 5, true);

SELECT setval('"recherche"."groupe_id_seq"', 45, true);

CREATE SEQUENCE "recherche"."mcle_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 20
 CACHE 1;

SELECT setval('"recherche"."mcle_id_seq"', 20, true);

SELECT setval('"recherche"."partenaire_id_seq"', 29, true);

SELECT setval('"recherche"."programme_id_seq"', 16, true);

SELECT setval('"recherche"."structure_id_seq"', 31, true);

SELECT setval('"recherche"."theme_id_seq"', 19, true);

ALTER TABLE "recherche"."activite_budget" ADD COLUMN "id_nc_activite" int4 NOT NULL;

ALTER TABLE "recherche"."activite_budget" ADD CONSTRAINT "unique_activite_budget_annee_groupe" UNIQUE ("id_demande_budget", "annee", "id_groupe", 

"id_nc_activite");

CREATE SEQUENCE "recherche"."activite_proj_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "recherche"."activite_proj" (
"numero" int4,
"description" varchar COLLATE "default",
"date_debut_prev" date,
"date_fin_prev" date,
"date_fin_reel" date,
"resultat_attendu" varchar COLLATE "default",
"id_nc_activite" int4,
"id_projet" int8,
"id" int4 DEFAULT nextval('"recherche".activite_proj_id_seq'::regclass) NOT NULL,
"resultat_atteint" varchar COLLATE "default",
CONSTRAINT "pk_activite_projet" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."activite_proj_id_seq" OWNED BY "recherche"."activite_proj"."id";

CREATE SEQUENCE "recherche"."activite_proje_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;

SELECT setval('"recherche"."activite_proje_id_seq"', 10, true);

CREATE TABLE "recherche"."activite_proje" (
"id" int4 DEFAULT nextval('"recherche".activite_proje_id_seq'::regclass) NOT NULL,
CONSTRAINT "pk_activite_proje" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."activite_proje_id_seq" OWNED BY "recherche"."activite_proje"."id";

CREATE SEQUENCE "recherche"."activite_projet_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 59
 CACHE 1;

SELECT setval('"recherche"."activite_projet_id_seq"', 59, true);

CREATE TABLE "recherche"."activite_projet" (
"numero" int4,
"description" varchar COLLATE "default",
"date_debut_prev" date,
"date_fin_prev" date,
"date_fin_reel" date,
"resultat_attendu" varchar COLLATE "default",
"id_nc_activite" int4,
"id_projet" int8,
"id" int4 DEFAULT nextval('"recherche".activite_projet_id_seq'::regclass) NOT NULL,
"resultat_atteint" varchar COLLATE "default",
CONSTRAINT "activite_projet_pkey" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."activite_projet_id_seq" OWNED BY "recherche"."activite_projet"."id";

ALTER TABLE "recherche"."demande_credit" DROP CONSTRAINT "fk_demande_credit_nc_article";

ALTER TABLE "recherche"."demande_credit" DROP CONSTRAINT "fk_demande_credit_nc_chapitre";

ALTER TABLE "recherche"."demande_credit" ADD CONSTRAINT "unique_demande_budget_chapitre_article_annee" UNIQUE ("id_demande_budget", 

"id_nc_chapitre", "id_nc_article", "annee");

ALTER TABLE "recherche"."demande_equipement" ADD CONSTRAINT "unique_demande_budget_famille_sous_famille_annee" UNIQUE ("id_demande_budget", 

"id_famille_equipement", "id_sous_famille_equipement", "annee");

ALTER TABLE "recherche"."demande_operation" ADD CONSTRAINT "unique_demande_budget_operation__annee" UNIQUE ("id_demande_budget", 

"id_nc_operation", "annee");

CREATE TABLE "recherche"."etape_circuit" (
"id" int8 NOT NULL,
"numero_etape" int4,
"nom_etape" varchar COLLATE "default",
"ref_groupe" int4,
CONSTRAINT "pk_etape_circuit" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

CREATE SEQUENCE "recherche"."etape_validation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 60
 CACHE 1;

SELECT setval('"recherche"."etape_validation_id_seq"', 60, true);

CREATE TABLE "recherche"."etape_validation" (
"id_etape_circuit" int8,
"date_reponse" date,
"observation" varchar COLLATE "default",
"id_reponse" int4,
"id_projet" int8,
"id_demande" int8,
"id" int4 DEFAULT nextval('"recherche".etape_validation_id_seq'::regclass) NOT NULL,
"reponce" varchar COLLATE "default",
CONSTRAINT "pk_etape-validation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."etape_validation_id_seq" OWNED BY "recherche"."etape_validation"."id";

CREATE SEQUENCE "recherche"."evaluation_chercheur_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "recherche"."evaluation_chercheur" (
"id" int4 DEFAULT nextval('"recherche".evaluation_chercheur_id_seq'::regclass) NOT NULL,
"id_individu" int8 NOT NULL,
"date_evaluation" timestamp(6) NOT NULL,
"date_debut_periode" timestamp(6) NOT NULL,
"date_fin_periode" timestamp(6),
"appreciation" varchar(1000) COLLATE "default" NOT NULL,
"id_grille_evaluation" int8 NOT NULL,
CONSTRAINT "pk_evaluation_chercheur" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."evaluation_chercheur_id_seq" OWNED BY "recherche"."evaluation_chercheur"."id";

CREATE SEQUENCE "recherche"."evaluation_indicateur_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 11
 CACHE 1;

SELECT setval('"recherche"."evaluation_indicateur_id_seq"', 11, true);

CREATE TABLE "recherche"."evaluation_indicateur" (
"id" int4 DEFAULT nextval('"recherche".evaluation_indicateur_id_seq'::regclass) NOT NULL,
"id_type_appreciation" int4,
"valeur" float8,
"observation" varchar COLLATE "default",
"id_evaluation_projet" int8,
"id_ind_evaluation" int8,
CONSTRAINT "pk_evaluation_indicateur" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."evaluation_indicateur_id_seq" OWNED BY "recherche"."evaluation_indicateur"."id";

CREATE SEQUENCE "recherche"."evaluation_projet_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;

SELECT setval('"recherche"."evaluation_projet_id_seq"', 2, true);

CREATE TABLE "recherche"."evaluation_projet" (
"id" int4 DEFAULT nextval('"recherche".evaluation_projet_id_seq'::regclass) NOT NULL,
"date_evaluation" date,
"date_fin_periode" date,
"description" varchar COLLATE "default",
"appreciation_globale" varchar COLLATE "default",
"nb_brevet" varchar COLLATE "default",
"nb_publication" varchar COLLATE "default",
"resultat_atteint" varchar COLLATE "default",
"taux_realisation" float8,
"id_projet" int8,
CONSTRAINT "pk_evaluation_projet" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."evaluation_projet_id_seq" OWNED BY "recherche"."evaluation_projet"."id";

CREATE SEQUENCE "recherche"."evaluation_valeur_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "recherche"."evaluation_valeur" (
"id_evaluation_chercheur" int8 NOT NULL,
"id_grille_evaluation_detail" int8 NOT NULL,
"id_nc_appreciation" int4 NOT NULL,
"note" float8 NOT NULL,
"observation" varchar(1000) COLLATE "default",
"id" int4 DEFAULT nextval('"recherche".evaluation_valeur_id_seq'::regclass) NOT NULL,
CONSTRAINT "pk_evaluation_valeur" PRIMARY KEY ("id"),
CONSTRAINT "unique_evaluation__valeur" UNIQUE ("id", "id_evaluation_chercheur", "id_grille_evaluation_detail", "id_nc_appreciation")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."evaluation_valeur_id_seq" OWNED BY "recherche"."evaluation_valeur"."id";

CREATE SEQUENCE "recherche"."grille_evaluation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;

SELECT setval('"recherche"."grille_evaluation_id_seq"', 2, true);

CREATE TABLE "recherche"."grille_evaluation" (
"id" int4 DEFAULT nextval('"recherche".grille_evaluation_id_seq'::regclass) NOT NULL,
"code" varchar(50) COLLATE "default" NOT NULL,
"intitule_fr" varchar(500) COLLATE "default" NOT NULL,
"intitule_ar" varchar(500) COLLATE "default",
CONSTRAINT "pk_grille_evaluation" PRIMARY KEY ("id"),
CONSTRAINT "unique_code__grille_evaluation" UNIQUE ("code")
)
WITH (OIDS=FALSE);

COMMENT ON TABLE "recherche"."grille_evaluation" IS 'Table contenant les grilles d''évaluation de la recherche';

COMMENT ON COLUMN "recherche"."grille_evaluation"."id" IS 'Clé primaire auto-incrémentée.';

COMMENT ON COLUMN "recherche"."grille_evaluation"."code" IS 'Code de la grille d''évaluation';

COMMENT ON COLUMN "recherche"."grille_evaluation"."intitule_fr" IS 'Intitulé fr de la grille d''évaluation';

ALTER SEQUENCE "recherche"."grille_evaluation_id_seq" OWNED BY "recherche"."grille_evaluation"."id";

CREATE SEQUENCE "recherche"."grille_evaluation_detail_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 12
 CACHE 1;

SELECT setval('"recherche"."grille_evaluation_detail_id_seq"', 12, true);

CREATE TABLE "recherche"."grille_evaluation_detail" (
"id" int4 DEFAULT nextval('"recherche".grille_evaluation_detail_id_seq'::regclass) NOT NULL,
"id_grille_evaluation" int8,
"id_nc_chapitre" int4,
"id_nc_critere" int4,
"numero" int4 NOT NULL,
"id_nc_appreciation" int4 NOT NULL,
CONSTRAINT "pk_grille_evaluation_detail" PRIMARY KEY ("id"),
CONSTRAINT "unique_grille_evaluation__detail" UNIQUE ("id", "id_grille_evaluation", "id_nc_chapitre", "id_nc_critere"),
CONSTRAINT "unique_grille_evaluation_detail__numero" UNIQUE ("id", "id_grille_evaluation", "id_nc_chapitre", "id_nc_critere", "numero")
)
WITH (OIDS=FALSE);

COMMENT ON TABLE "recherche"."grille_evaluation_detail" IS 'Table contenant le détail de la grille d''évaluation des chercheurs.';

ALTER SEQUENCE "recherche"."grille_evaluation_detail_id_seq" OWNED BY "recherche"."grille_evaluation_detail"."id";

ALTER TABLE "recherche"."groupe" ALTER COLUMN "id_structure" TYPE int8;

CREATE SEQUENCE "recherche"."ind_evaluation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 17
 CACHE 1;

SELECT setval('"recherche"."ind_evaluation_id_seq"', 17, true);

CREATE TABLE "recherche"."ind_evaluation" (
"id" int4 DEFAULT nextval('"recherche".ind_evaluation_id_seq'::regclass) NOT NULL,
"numero" int4,
"id_nc_indicateur" int4,
"id_projet" int8,
"valeur_prev" float8,
"type_appreciation" int4,
CONSTRAINT "pk_ind_evaluation" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."ind_evaluation_id_seq" OWNED BY "recherche"."ind_evaluation"."id";

CREATE SEQUENCE "recherche"."mot_cle_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

CREATE TABLE "recherche"."mot_cle" (
"id" int4 DEFAULT nextval('"recherche".mot_cle_id_seq'::regclass) NOT NULL,
"id_nc_mcle" int4,
"id_projet" int8,
CONSTRAINT "pk_mot_cle" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."mot_cle_id_seq" OWNED BY "recherche"."mot_cle"."id";

ALTER TABLE "recherche"."partenaire" ADD COLUMN "id_projet" int8;

ALTER TABLE "recherche"."programme" ALTER COLUMN "date_debut" TYPE date;

ALTER TABLE "recherche"."programme" ALTER COLUMN "date_fin" TYPE date;

CREATE SEQUENCE "recherche"."projet_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 25
 CACHE 1;

SELECT setval('"recherche"."projet_id_seq"', 25, true);

CREATE TABLE "recherche"."projet" (
"id" int4 DEFAULT nextval('"recherche".projet_id_seq'::regclass) NOT NULL,
"code" varchar(50) COLLATE "default",
"intitule_fr" varchar(250) COLLATE "default",
"intitule_ar" varchar(250) COLLATE "default",
"date_debut_reel" date,
"date_fin_reel" date,
"date_debut_prev" date,
"date_fin_prev" date,
"objectif" varchar COLLATE "default",
"justification" varchar COLLATE "default",
"date_cloture" date,
"observation" varchar COLLATE "default",
"date_identification" date,
"id_situation" int4,
"id_type_cloture" int4,
"id_programe" int8,
"id_etablissement" int4,
"id_structure" int8,
"id_theme" int8,
"id_groupe" int8,
CONSTRAINT "pk_projet" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."projet_id_seq" OWNED BY "recherche"."projet"."id";

CREATE SEQUENCE "recherche"."projet_partenaire_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 20
 CACHE 1;

SELECT setval('"recherche"."projet_partenaire_id_seq"', 20, true);

CREATE TABLE "recherche"."projet_partenaire" (
"id_projet" int8,
"id_partenaire" int8,
"id" int4 DEFAULT nextval('"recherche".projet_partenaire_id_seq'::regclass) NOT NULL,
"description" varchar COLLATE "default",
CONSTRAINT "pk_projet_partenaire" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "recherche"."projet_partenaire_id_seq" OWNED BY "recherche"."projet_partenaire"."id";

ALTER TABLE "recherche"."theme" ALTER COLUMN "id_structure" TYPE int8;

ALTER TABLE "recherche"."theme" ALTER COLUMN "id_groupe" TYPE int8;

CREATE UNIQUE INDEX "unique_activite_budget_annee__groupe" ON "recherche"."activite_budget" USING btree (id_demande_budget, annee, id_groupe, 

id_nc_activite);

CREATE UNIQUE INDEX "unique_code_grille_evaluation" ON "recherche"."grille_evaluation" USING btree (code);

CREATE UNIQUE INDEX "unique_demande_budget_chapitre__article__annee" ON "recherche"."demande_credit" USING btree (id_demande_budget, 

id_nc_chapitre, id_nc_article, annee);

CREATE UNIQUE INDEX "unique_demande_budget_famille_sous_famille__annee" ON "recherche"."demande_equipement" USING btree (id_demande_budget, 

id_famille_equipement, id_sous_famille_equipement, annee);

CREATE UNIQUE INDEX "unique_demande_budget_operation_annee" ON "recherche"."demande_operation" USING btree (id_demande_budget, id_nc_operation, 

annee);

CREATE UNIQUE INDEX "unique_evaluation_valeur" ON "recherche"."evaluation_valeur" USING btree (id, id_evaluation_chercheur, 

id_grille_evaluation_detail, id_nc_appreciation);

CREATE UNIQUE INDEX "unique_grille_evaluation_detail" ON "recherche"."grille_evaluation_detail" USING btree (id, id_grille_evaluation, 

id_nc_chapitre, id_nc_critere);

CREATE UNIQUE INDEX "unique_grille_evaluation_detail_numero" ON "recherche"."grille_evaluation_detail" USING btree (id, id_grille_evaluation, 

id_nc_chapitre, id_nc_critere, numero);

ALTER TABLE "recherche"."activite_budget" ADD CONSTRAINT "fk_demande_activite_nc_activite" FOREIGN KEY ("id_nc_activite") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."activite_proj" ADD CONSTRAINT "fk_nc_activite_proj_activite" FOREIGN KEY ("id_nc_activite") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."activite_proj" ADD CONSTRAINT "fk_nc_activite_proj" FOREIGN KEY ("id_projet") REFERENCES "recherche"."projet" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."activite_projet" ADD CONSTRAINT "fk_nc_activite_projet_activite" FOREIGN KEY ("id_nc_activite") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."activite_projet" ADD CONSTRAINT "fk_nc_activite_projet" FOREIGN KEY ("id_projet") REFERENCES "recherche"."projet" ("id") 

ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."demande_credit" ADD CONSTRAINT "fk_demande_budget_chapitre" FOREIGN KEY ("id_nc_chapitre") REFERENCES "gfc"."chapitre" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."demande_credit" ADD CONSTRAINT "fk_demande_budget_article" FOREIGN KEY ("id_nc_article") REFERENCES "gfc"."article" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."etape_circuit" ADD CONSTRAINT "fk_ref_group_etape_circuit" FOREIGN KEY ("ref_groupe") REFERENCES "ppm"."ref_groupe" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;



----------------------------------bpm
ALTER TABLE "bpm"."action" ADD COLUMN "id_entite" int4;

ALTER TABLE "bpm"."action" ADD CONSTRAINT "unique_action_entite_code" UNIQUE ("code", "id_entite");

CREATE SEQUENCE "bpm"."action_entite_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;

SELECT setval('"bpm"."action_entite_id_seq"', 6, true);

CREATE TABLE "bpm"."action_entite" (
"id" int4 DEFAULT nextval('"bpm".action_entite_id_seq'::regclass) NOT NULL,
"id_action" int4 NOT NULL,
"id_entite" int4 NOT NULL,
CONSTRAINT "pk_action_entite" PRIMARY KEY ("id"),
CONSTRAINT "unique_action_entite" UNIQUE ("id_action", "id_entite")
)
WITH (OIDS=FALSE);

ALTER SEQUENCE "bpm"."action_entite_id_seq" OWNED BY "bpm"."action_entite"."id";

----------------------------------

ALTER TABLE "recherche"."etape_validation" ADD CONSTRAINT "fk_action_entity_etape_validation" FOREIGN KEY ("id_reponse") REFERENCES 

"bpm"."action_entite" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."etape_validation" ADD CONSTRAINT "fk_demande_budget_etape_validation" FOREIGN KEY ("id_demande") REFERENCES 

"recherche"."demande_budget" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."etape_validation" ADD CONSTRAINT "fk_etape_etape_validation" FOREIGN KEY ("id_etape_circuit") REFERENCES "bpm"."etape" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."etape_validation" ADD CONSTRAINT "fk_projet_etape_validation" FOREIGN KEY ("id_projet") REFERENCES "recherche"."projet" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_chercheur" ADD CONSTRAINT "fk_evaluation_chercheur_grille_evaluation" FOREIGN KEY ("id_grille_evaluation") 

REFERENCES "recherche"."grille_evaluation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_chercheur" ADD CONSTRAINT "fk_evaluation_chercheur_ref_individu" FOREIGN KEY ("id_individu") REFERENCES 

"ppm"."ref_individu" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_indicateur" ADD CONSTRAINT "fk_evaluation_indicateur_evaluation_projet" FOREIGN KEY ("id_evaluation_projet") 

REFERENCES "recherche"."evaluation_projet" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_indicateur" ADD CONSTRAINT "fk_evaluation_indicateur_ind_evaluation" FOREIGN KEY ("id_ind_evaluation") 

REFERENCES "recherche"."ind_evaluation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_projet" ADD CONSTRAINT "fk_evaluation_projet_projet" FOREIGN KEY ("id_projet") REFERENCES 

"recherche"."projet" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_valeur" ADD CONSTRAINT "fk_evaluation_valeur_evaluation_chercheur" FOREIGN KEY ("id_evaluation_chercheur") 

REFERENCES "recherche"."evaluation_chercheur" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_valeur" ADD CONSTRAINT "fk_evaluation_valeur_grille_evaluation_detail" FOREIGN KEY 

("id_grille_evaluation_detail") REFERENCES "recherche"."grille_evaluation_detail" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."evaluation_valeur" ADD CONSTRAINT "fk_evaluation_valeur_nc_appreciation" FOREIGN KEY ("id_nc_appreciation") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."grille_evaluation_detail" ADD CONSTRAINT "fk_grille_evaluation_detail" FOREIGN KEY ("id_grille_evaluation") REFERENCES 

"recherche"."grille_evaluation" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."grille_evaluation_detail" ADD CONSTRAINT "fk_grille_evaluation_detail_nc_appreciation" FOREIGN KEY 

("id_nc_appreciation") REFERENCES "nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."grille_evaluation_detail" ADD CONSTRAINT "fk_grille_evaluation_detail_nc_chapitre" FOREIGN KEY ("id_nc_chapitre") 

REFERENCES "nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."grille_evaluation_detail" ADD CONSTRAINT "fk_grille_evaluation_detail_nc_critere" FOREIGN KEY ("id_nc_critere") 

REFERENCES "nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."ind_evaluation" ADD CONSTRAINT "fk_nc_ind_evaluation" FOREIGN KEY ("id_nc_indicateur") REFERENCES "nc"."nomenclature" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."ind_evaluation" ADD CONSTRAINT "fk_projet_nomenclature_appreciation" FOREIGN KEY ("type_appreciation") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."ind_evaluation" ADD CONSTRAINT "fk_projet_ind_evaluation" FOREIGN KEY ("id_projet") REFERENCES "recherche"."projet" 

("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."mot_cle" ADD CONSTRAINT "fk_mot_cle_nomenclature" FOREIGN KEY ("id_nc_mcle") REFERENCES "nc"."nomenclature" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."mot_cle" ADD CONSTRAINT "fk_mot_cle_projet" FOREIGN KEY ("id_projet") REFERENCES "recherche"."projet" ("id") ON DELETE 

NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."partenaire" ADD CONSTRAINT "fk_projet_partenaire" FOREIGN KEY ("id_projet") REFERENCES "recherche"."partenaire" ("id") 

ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_projet_situation" FOREIGN KEY ("id_situation") REFERENCES "bpm"."situation" ("id") ON DELETE 

NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_groupe_projet" FOREIGN KEY ("id_groupe") REFERENCES "recherche"."groupe" ("id") ON DELETE NO 

ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_projet_programme" FOREIGN KEY ("id_programe") REFERENCES "recherche"."programme" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_theme_projet" FOREIGN KEY ("id_theme") REFERENCES "recherche"."theme" ("id") ON DELETE NO 

ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_structure_projet" FOREIGN KEY ("id_structure") REFERENCES "recherche"."structure" ("id") ON 

DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_ref_etablissement_projet" FOREIGN KEY ("id_etablissement") REFERENCES 

"ppm"."ref_etablissement" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet" ADD CONSTRAINT "fk_projet_nomenclature_type_cloture" FOREIGN KEY ("id_type_cloture") REFERENCES 

"nc"."nomenclature" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet_partenaire" ADD CONSTRAINT "fk_partenaire_projet_partenaire" FOREIGN KEY ("id_partenaire") REFERENCES 

"recherche"."partenaire" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "recherche"."projet_partenaire" ADD CONSTRAINT "fk_projet_projet_partenaire" FOREIGN KEY ("id_projet") REFERENCES 

"recherche"."projet" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

--bpm

SELECT setval('"bpm"."etape_id_seq"', 74, true);

SELECT setval('"bpm"."processus_id_seq"', 37, true);

SELECT setval('"bpm"."situation_entite_id_seq"', 65, true);

SELECT setval('"bpm"."situation_entite_occurrence_id_seq"', 4075, true);


ALTER TABLE "bpm"."etape" DROP CONSTRAINT "fk_etape_action";

CREATE UNIQUE INDEX "unique_action__entite" ON "bpm"."action_entite" USING btree (id_action, id_entite);

CREATE UNIQUE INDEX "unique_action_entite__code" ON "bpm"."action" USING btree (code, id_entite);

ALTER TABLE "bpm"."action" ADD CONSTRAINT "fk_action_entite" FOREIGN KEY ("id_entite") REFERENCES "bpm"."entite" ("id") ON DELETE NO ACTION ON 

UPDATE NO ACTION;

ALTER TABLE "bpm"."action_entite" ADD CONSTRAINT "fk_action_entite_entite" FOREIGN KEY ("id_entite") REFERENCES "bpm"."entite" ("id") ON DELETE 

NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "bpm"."action_entite" ADD CONSTRAINT "fk_action_entite_action" FOREIGN KEY ("id_action") REFERENCES "bpm"."action" ("id") ON DELETE 

NO ACTION ON UPDATE NO ACTION;
INSERT INTO bpm.action_entite(id, id_action, id_entite)   VALUES (2, 1, 1);

ALTER TABLE "bpm"."etape" ADD CONSTRAINT "fk_etape_action" FOREIGN KEY ("id_action") REFERENCES "bpm"."action_entite" ("id") ON DELETE NO ACTION 

ON UPDATE NO ACTION;







