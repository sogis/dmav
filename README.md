# dmav

Werkzeugkasten für das Handling mit DMAV-Transferdateien:

- merge: Erzeugt aus mehreren Transferdateien (eins pro Datenmodell) eine einzelne Transferdatei
- split: Trennt eine DMAV-Transferdatei in mehrere auf (eine pro Datenmodell)

Snapshot-Versionen: https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/sogis/dmav/ -> X.Y.Z-SNAPSHOT -> dmav-X.Y.Z-Datum.Uhrzeit-all.jar

Release-Versionen: ...

## Anforderungen

Java 11 oder grösser.

## Benutzung

Die verschiedenen Funktionalitäten sind mittels Subkommandos aufrufbar. 

### Dateien mergen

```
java -jar dmav.jar merge --config myconfig.ini" --fosnr 449 --out /path/to/directory
```

| Name | Beschreibung | Required |
|-----|-----|-----|
| `--config` | Pfad zu Konfig-Datei (siehe unten). | ja |
| `--fosnr` | BFS-Nummer mit der die Platzhalter in der Konfig-Datei ersetzt werden. | ja |
| `--out` | Zielverzeichnis, in das die resultierende Transferdatei mit dem Namen _DMAV.fosnr.xtf_ gespeichert wird. | ja |

Konfig-Datei:
```
DMAV_FixpunkteAVKategorie2_V1_0=src/test/data/merger/DMAV_FixpunkteAVKategorie2_V1_0.${fosnr}.xtf
DMAV_PLZ_Ortschaft_V1_0=http://localhost:8181/DMAV_PLZ_Ortschaft_V1_0.${fosnr}.xtf
```

Die Keys müssen dem Datenmodellnamen entsprechen. Jedes Modell kann nur einmal vorkommen. Die Values sind der Pfad der Einzeldatei. Entweder lokal oder als HTTP-Url. Die Datei darf _nicht_ gezippt sein. Der Platzhalter `${fosnr}` ist nicht zwingend notwendig.

### Datei aufsplitten

```
java -jar dmav.jar split --input path/to/dmav.xtf" --fosnr 449 --out /path/to/directory
```

| Name | Beschreibung | Required |
|-----|-----|-----|
| `--input` | Pfad zur Input-Transferdatei mit allen Themen. | ja |
| `--fosnr` | BFS-Nummer. Neue Daten werden mit diesem Suffix versehen. | ja |
| `--out` | Zielverzeichnis, in das die resultierenden Transferdateien gespeichert werden. | ja |

## Interne Struktur

### Merge
Die eigentliche Arbeit wird mit XSLT gemacht. In der XSL-Datei sind die Dateinamen (bis auf die BFS-Nummer) hardcodiert. Aus diesem Grund muss der Key aus der Konfig-Datei mit dem Namensrumpf der zu lesenden Transferdateien übereinstimmen. In einem Zwischenschritt werden sämtliche referenzierten Dateien in der Konfig-Datei in das gleiche Verzeichnis wie die XSL-Datei kopiert und entsprechend umbenannt. Somit können die Namen in der XSL-Datei hardcodiert (bis auf die BFS-Nummer) bleiben.

Es wäre möglich ohne Konfig-Datei zu arbeiten. Dann müssten die Dateinamen aber bereits korrekt benannt (nach Modell) werden. Die XSL-Transformation ist so aufgebaut, dass sie eine bekannten Zahl von Dateien bearbeitet.

### Split
XSLT übernimmt ebenfalls beim Splitten die Hauptaufgabe. Pro Modell und Topic wird eine neue Datei erzeugt. In den Datenmodell gibt es jeweils nur ein Topic pro Modell. Sollte sich das ändern, müsste man die XSL-Transformation noch anpassen.

## Develop

### merge
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

### split
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:/Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf -xsl:split.xsl -o:DMAV_logging.xtf fosnr=449
```


