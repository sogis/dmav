# dmav


## merge
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf fosnr=449
```

```
java -jar /Users/stefan/apps/ilivalidator-1.14.3/ilivalidator-1.14.3.jar DMAV_merged.xtf
```


- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_FixpunkteLV_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_FixpunkteAVKategorie2_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_FixpunkteAVKategorie3_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Bodenbedeckung_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Einzelobjekte_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Nomenklatur_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Grundstuecke_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Rohrleitungen_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_HoheitsgrenzenLV_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_HoheitsgrenzenAV_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Toleranzstufen_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_DauerndeBodenverschiebungen_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_PLZ_Ortschaft_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Gebaeudeadressen_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAVSUP_UntereinheitGrundbuch_V1_0.449.xtf
- https://sos-ch-dk-2.exo.io/ch.so.agi.fubar1/DMAV_Dienstbarkeitsgrenzen_V1_0.449.xtf


**Problematik Namespaces:** Wahrscheinlich darf man nicht im "ili:transfer" Element die Namespaces harcodiert reinschreiben. Die XTF können andere Abkürzungen verwenden. Es funktioniert zwar korrekt, es wird aber der Namespace im Basket-Element neu definiert. Dann kann man es auch gleich sein lassen (im ili:transfer), dann werden einfach alle Namespaces im Basket-Element definiert. Vernachlässigbar im Vergleich zur sonstigen Grösse des XML/XTF.

## split
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:/Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf -xsl:split.xsl -o:DMAV_logging.xtf fosnr=449
```


