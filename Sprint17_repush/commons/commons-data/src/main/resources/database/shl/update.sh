#!/bin/sh

#----------
#--- func : declaration des fonctions
#----------

#-- Fonction de chargement et verification du fichier de configuration et des variables exportees 
function  VerifVar
{
    if [ -z "$ADM_USER" ]
    then
        echo "une des variables d'environnement est mal positionnee (ADM_USER)\n"
        echo "Fin du script $0 en erreur : `date`.\n"
        exit 1
    fi

    if [ -z "$ADM_PASSWD" ]
    then
        echo "une des variables d'environnement est mal positionnee (ADM_PASSWD)\n" 
        echo "Fin du script $0 en erreur : `date`.\n"   
        exit 1
    fi


    if [ -z "$REP_LOG" ]
    then
        echo "une des variables d'environnement est mal positionnee (REP_LOG)\n" 
        echo "Fin du script $0 en erreur : `date`.\n"
        exit 1
    fi
    
    if [ -z "$BASE_IP" ]
    then
        echo "une des variables d'environnement est mal positionnee (BASE_IP)\n" 
        echo "Fin du script $0 en erreur : `date`.\n"
        exit 1
    fi
	
	if [ -z "$BASE_NAME" ]
    then
        echo "une des variables d'environnement est mal positionnee (BASE_NAME)\n" 
        echo "Fin du script $0 en erreur : `date`.\n"
        exit 1
    fi
	
} #-- fin function VerifVar


#-- Fonction de verification de la presence du repertoire de LOG 
function  VerifDir
{
    #creation du repertoire de log
    if [ ! -d ${REP_LOG} ]
    then
        echo "Le repertoire de log ${REP_LOG} n'existe pas, Creation \n"
        mkdir -p ${REP_LOG}
        ret=$?
	if [ $ret -ne 0 ]
        then
                echo "Le repertoire de log ${REP_LOG} n'a pas pu être cree\n"
                echo "#--- Fin du script $0 en Erreur (exit 1)\n"
                exit 1
        fi
    fi

    # Repertoire des traces
    [ -r "$REP_LOG" ] || { echo "Erreur: Repertoire des traces '$REP_LOG' interdit en lecture.\n"; exit 1; }
    [ -x "$REP_LOG" ] || { echo "Erreur: Repertoire des traces '$REP_LOG' interdit en execution.\n"; exit 1; }
    [ -w "$REP_LOG" ] || { echo "Erreur: Repertoire des traces '$REP_LOG' interdit en ecriture.\n"; exit 1; }

} #-- Fin function VerifDir

#--------------
#--- Fin func : Fin de declaration des fonctions
#--------------

#-----------
#--- const : Definition des constantes
#-----------

#SHELL_DIR=`dirname $0`
pushd `dirname $0` > /dev/null
SHELL_DIR=`pwd`
popd > /dev/null
RELEASE_VERSION=${project.version}
REP_SQL=$SHELL_DIR/../sql/$RELEASE_VERSION


LOGDATE=`date +'%Y%m%d%H%M%S'`

#-------------
#--- Fin const
#-------------

#----------
#--- main :  Debut du script de mise à jour des schemas
#----------

#-- Verification du nombre d'argument
if [ $# -ne 0 ]
then
    echo "\n----------------------------------------------------"       
    echo "--- Usage : update.sh"    
    echo "---"
    echo "--------- Ce shell ne prend aucun parametre en entree."
    echo "---------"    
    echo "------------------------------------------------------\n"
    exit 1
fi

#-- Verification des variables d'environnement

VerifVar

trap "echo \"Arret demande par l'utilisateur\"; exit 1" 2

VerifDir 

#-- Debut du script

LOG=${REP_LOG}/UPDATE_${LOGDATE}.log

echo "\n#--- Debut du script $0 : `date`\n" | tee -a $LOG


PROCESS_RUNNING=`\ps -e | grep "update-RELEASE_VERSION.sql" | grep -v grep | wc -l`
RETOUR=$?

if [ $PROCESS_RUNNING -ne 0 ]
then
        echo "Erreur : execution du binaire en cours."                                  | tee -a $LOG
        echo "Veuillez attendre la fin de l execution precedente."                      | tee -a $LOG
        DateFinTraitement=`date "+%Y/%m/%d %H:%M:%S"`
        echo "\n#--- Fin du script de $0 : $DateFinTraitement \n"                      | tee -a $LOG
        exit $NOK
fi


#-- Verification de l'existence des fichiers sql

#if [ ! -f $REP_SQL/update-${RELEASE_VERSION}.sql ]
#then
 #   echo "\nLe fichier $REP_SQL/update.sql est introuvable" | tee -a $LOG
 #   echo "\n#--- Fin du script $0 en erreur : `date` - Fichier de log $LOG \n"
  #  exit 1
#fi

#-- Test de connexion a la base 

echo "Test de connexion a la base \n" >> $LOG

rm -f TESTLOG

%FIN%


cd ${REP_SQL}


#-- Lancement du script sql

echo "Connexion pour suppression de la mise a jour." | tee -a $LOG
psql -d ${BASE_NAME} -U ${ADM_USER} -h ${BASE_IP} -f ${REP_SQL}/update-${RELEASE_VERSION}.sql<< %FIN% > TESTLOG


Retour=$?
echo "Retour de plsql : $Retour\n" | tee -a $LOG

#-- Test du code retour renvoye par le script sql
if  [ $Retour  -ne '0' ]
then
    echo "Probleme lors de l'execution du script $0 dans la base '$BASE'" | tee -a $LOG
    echo "\n#--- Fin du script $0 en erreur : `date`\n"
    echo "Consulter le fichier log $LOG\n"
    exit 1
fi

echo "Base de données Progres mise à jour avec succes"
echo "\n#--- Fin normale du script $0 : `date`\n" | tee -a $LOG
echo "Consulter le fichier log $LOG\n"
exit 0

#---------------
#--- Fin du main
#--------------- 
