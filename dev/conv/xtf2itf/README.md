```
java -jar /Users/stefan/apps/ili2h2gis-5.1.1/ili2h2gis-5.1.1.jar --dbfile dmavconv --dbschema dmav --defaultSrsCode 2056 --nameByTopic --disableValidation --createBasketCol --models "DMAV_Bodenbedeckung_V1_0;DMAV_DauerndeBodenverschiebungen_V1_0;DMAV_Dienstbarkeitsgrenzen_V1_0;DMAV_Einzelobjekte_V1_0;DMAV_FixpunkteAVKategorie2_V1_0;DMAV_FixpunkteAVKategorie3_V1_0;DMAV_FixpunkteLV_V1_0;DMAV_Gebaeudeadressen_V1_0;DMAV_Grundstuecke_V1_0;DMAV_HoheitsgrenzenAV_V1_0;DMAV_HoheitsgrenzenLV_V1_0;DMAV_Nomenklatur_V1_0;DMAV_PLZ_Ortschaft_V1_0;DMAV_Rohrleitungen_V1_0;DMAV_Toleranzstufen_V1_0;DMAVSUP_UntereinheitGrundbuch_V1_0" --schemaimport
```

```
java -jar /Users/stefan/apps/ili2h2gis-5.1.1/ili2h2gis-5.1.1.jar --dbfile dmavconv --dbschema dmav --defaultSrsCode 2056 --disableValidation --models "DMAV_Bodenbedeckung_V1_0;DMAV_DauerndeBodenverschiebungen_V1_0;DMAV_Dienstbarkeitsgrenzen_V1_0;DMAV_Einzelobjekte_V1_0;DMAV_FixpunkteAVKategorie2_V1_0;DMAV_FixpunkteAVKategorie3_V1_0;DMAV_FixpunkteLV_V1_0;DMAV_Gebaeudeadressen_V1_0;DMAV_Grundstuecke_V1_0;DMAV_HoheitsgrenzenAV_V1_0;DMAV_HoheitsgrenzenLV_V1_0;DMAV_Nomenklatur_V1_0;DMAV_PLZ_Ortschaft_V1_0;DMAV_Rohrleitungen_V1_0;DMAV_Toleranzstufen_V1_0;DMAVSUP_UntereinheitGrundbuch_V1_0" --import /Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf
```


```
java -jar /Users/stefan/apps/ili2h2gis-5.1.1/ili2h2gis-5.1.1.jar --dbfile dmavconv --dbschema dm01 --defaultSrsCode 2056 --nameByTopic --disableValidation --idSeqMin 10000000 --disableValidation --models DM01AVCH24LV95D --schemaimport 
```

```
java -jar /Users/stefan/apps/ili2h2gis-5.1.1/ili2h2gis-5.1.1.jar --dbfile dmavconv --dbschema dm01 --defaultSrsCode 2056 --nameByTopic --disableValidation --models DM01AVCH24LV95D --export fubar.itf 
```




```
java -jar /Users/stefan/apps/ili2gpkg-5.1.1/ili2gpkg-5.1.1.jar --dbfile dmavconv.gpkg --defaultSrsCode 2056 --nameByTopic --disableValidation --createBasketCol --idSeqMin 10000000 --models "DMAV_Bodenbedeckung_V1_0;DMAV_DauerndeBodenverschiebungen_V1_0;DMAV_Dienstbarkeitsgrenzen_V1_0;DMAV_Einzelobjekte_V1_0;DMAV_FixpunkteAVKategorie2_V1_0;DMAV_FixpunkteAVKategorie3_V1_0;DMAV_FixpunkteLV_V1_0;DMAV_Gebaeudeadressen_V1_0;DMAV_Grundstuecke_V1_0;DMAV_HoheitsgrenzenAV_V1_0;DMAV_HoheitsgrenzenLV_V1_0;DMAV_Nomenklatur_V1_0;DMAV_PLZ_Ortschaft_V1_0;DMAV_Rohrleitungen_V1_0;DMAV_Toleranzstufen_V1_0;DMAVSUP_UntereinheitGrundbuch_V1_0" --schemaimport
```


```
java -jar /Users/stefan/apps/ili2gpkg-5.1.1/ili2gpkg-5.1.1.jar --dbfile dmavconv.gpkg --defaultSrsCode 2056 --nameByTopic --disableValidation --createBasketCol --models "DMAV_Bodenbedeckung_V1_0;DMAV_DauerndeBodenverschiebungen_V1_0;DMAV_Dienstbarkeitsgrenzen_V1_0;DMAV_Einzelobjekte_V1_0;DMAV_FixpunkteAVKategorie2_V1_0;DMAV_FixpunkteAVKategorie3_V1_0;DMAV_FixpunkteLV_V1_0;DMAV_Gebaeudeadressen_V1_0;DMAV_Grundstuecke_V1_0;DMAV_HoheitsgrenzenAV_V1_0;DMAV_HoheitsgrenzenLV_V1_0;DMAV_Nomenklatur_V1_0;DMAV_PLZ_Ortschaft_V1_0;DMAV_Rohrleitungen_V1_0;DMAV_Toleranzstufen_V1_0;DMAVSUP_UntereinheitGrundbuch_V1_0" --import /Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf
```

```
java -jar /Users/stefan/apps/ili2gpkg-5.1.1/ili2gpkg-5.1.1.jar --dbfile dmavconv.gpkg --defaultSrsCode 2056 --nameByTopic --disableValidation --models DM01AVCH24LV95D --schemaimport 
```

