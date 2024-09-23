
```
GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID --workdir //home/gradle/schema-jobs/shared/schema gretl -PtopicName=agi_plz_ortschaften -PschemaDirName=schema createSchema configureSchema
```

```
GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID gretl --project-dir=agi_plz_ortschaften_import
```

******************************

```
GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID --workdir //home/gradle/schema-jobs/shared/schema gretl -PtopicName=agi_plz_ortschaften -PschemaDirName=schema createSchema configureSchema
```

Pub-Modell noch nicht in Schema-Jobs. Zum Entwickeln von Händschen angelegt und importiert:

```
java -jar /Users/stefan/apps/ili2pg-5.1.1/ili2pg-5.1.1.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --nameByTopic --defaultSrsCode 2056 --createGeomIdx --strokeArcs --dbschema agi_hoheitsgrenzen_pub --models SO_Hoheitsgrenzen_Publikation_20170626 --schemaimport
```

```
java -jar /Users/stefan/apps/ili2pg-5.1.1/ili2pg-5.1.1.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --disableValidation --dbschema agi_hoheitsgrenzen_pub --models SO_Hoheitsgrenzen_Publikation_20170626 --import ilidata:ch.so.agi.av.hoheitsgrenzen
```

******************************

```
GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID --workdir //home/gradle/schema-jobs/shared/schema gretl -PtopicName=agi_dmav_plz_ortschaften_export -PschemaDirName=schema createSchema configureSchema
```

```
GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID --workdir //home/gradle/schema-jobs/shared/schema gretl -PtopicName=agi_dmav_plz_ortschaften_export -PschemaDirName=schema dropSchema
```

(move to gretl-job, liefert basket und dataset records):

java -jar /Users/stefan/apps/ili2pg-5.1.1/ili2pg-5.1.1.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --disableValidation --dbschema agi_dmav_plz_ortschaften_export_v1 --models DMAV_PLZ_Ortschaft_V1_0 --import DMAV_PLZ_Ortschaft_V1_0_empty.xtf

******************************

GRETL_IMAGE_TAG=2.4 docker compose run --rm -u $UID gretl --project-dir=agi_dmav_plz_ortschaft_export

