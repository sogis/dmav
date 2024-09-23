# dmav-merger


## merge
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf fosnr=449
```

```
java -jar /Users/stefan/apps/ilivalidator-1.14.3/ilivalidator-1.14.3.jar DMAV_merged.xtf
```

**Problematik Namespaces:** Wahrscheinlich darf man nicht im "ili:transfer" Element die Namespaces harcodiert reinschreiben. Die XTF können andere Abkürzungen verwenden. Es funktioniert zwar korrekt, es wird aber der Namespace im Basket-Element neu definiert. Dann kann man es auch gleich sein lassen (im ili:transfer), dann werden einfach alle Namespaces im Basket-Element definiert. Vernachlässigbar im Vergleich zur sonstigen Grösse des XML/XTF.

## split
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:/Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf -xsl:split.xsl -o:DMAV_logging.xtf
```

**Problematik Namespaces:** Die sind ja beliebig (?). Falls dem so ist, müsste man konsequenterweise nur über den local-name gehen, falls das funktioniert mit den match und apply-template.
