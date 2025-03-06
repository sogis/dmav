# dmav

Werkzeugkasten für das Handling mit DMAV-Transferdateien:

- merge: Erzeugt aus mehreren Transferdateien (eins pro Datenmodell) eine einzelne Transferdatei
- split: Trennt eine DMAV-Transferdatei in mehrere auf (eine pro Datenmodell)

Snapshot-Versionen: 
- https://s01.oss.sonatype.org/content/repositories/snapshots/io/github/sogis/dmav/ -> X.Y.Z-SNAPSHOT -> dmav-X.Y.Z-Datum.Uhrzeit-all.jar

Release-Versionen: 
- https://s01.oss.sonatype.org/content/repositories/releases/io/github/sogis/dmav/ 
- https://github.com/edigonzales/dmav/releases

## Anforderungen

Java 11 oder grösser.

## Benutzung

Die verschiedenen Funktionalitäten sind mittels Subkommandos aufrufbar. 

### Dateien mergen

```
java -jar dmav.jar merge --config myconfig.ini --fosnr 449 --out /path/to/directory
```

| Name | Beschreibung | Required |
|-----|-----|-----|
| `--config` | Pfad zu Konfig-Datei (siehe unten). | ja |
| `--fosnr` | BFS-Nummer mit der die Platzhalter in der Konfig-Datei ersetzt werden. | ja |
| `--out` | Zielverzeichnis, in das die resultierende Transferdatei mit dem Namen _DMAV.fosnr.xtf_ gespeichert wird. | ja |

Konfig-Datei:
```
DMAV_FixpunkteAVKategorie3_V1_0=src/test/data/merger/DMAV_FixpunkteAVKategorie3_V1_0.${fosnr}.xtf
KGKCGC_FPDS2_V1_1=https://geodienste.ch/downloads/interlis/fixpunkte/SO/fixpunkte_v1_1_SO_lv95.zip
```

Die Keys müssen dem Datenmodellnamen entsprechen. Jedes Modell kann nur einmal vorkommen. Die Values sind der Pfad der Einzeldatei. Entweder lokal oder als HTTP-Url. Die Datei darf _nicht_ gezippt sein. Der Platzhalter `${fosnr}` ist nicht zwingend notwendig.

Es wird die erste XTF-Datei innerhalb der ZIP-Datei verwendet.

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
Die eigentliche Arbeit wird mit XSLT gemacht. In der XSL-Datei sind die Dateinamen (bis auf die BFS-Nummer) hardcodiert. Aus diesem Grund muss der Key aus der Konfig-Datei mit dem Namensrumpf der zu lesenden Transferdateien übereinstimmen. In einem Zwischenschritt werden sämtliche referenzierten Dateien in der Konfig-Datei in das gleiche Verzeichnis wie die XSL-Datei kopiert und entsprechend umbenannt. Somit können die Namen resp der Pfad der Datei in der XSL-Datei hardcodiert (bis auf die BFS-Nummer) bleiben.

Es wäre möglich ohne Konfig-Datei zu arbeiten. Dann müssten die Dateinamen aber bereits korrekt benannt (nach Modell) werden. Die XSL-Transformation ist so aufgebaut, dass sie eine bekannten Zahl von Dateien bearbeitet.

### Split
XSLT übernimmt ebenfalls beim Splitten die Hauptaufgabe. Pro Modell und Topic wird eine neue Datei erzeugt. In den Datenmodell gibt es jeweils nur ein Topic pro Modell. Sollte sich das ändern, müsste man die XSL-Transformation noch anpassen.

## Release

Es gibt die zwei Branches _main_ und _stable_. Entwickelt wird im _main_-Branch. Für einen Release müssen die Änderungen in den _stable_-Branch gemerged werden:

```
git checkout stable
git rebase main
```

## Develop

### merge
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:DMAV_Skeleton.xtf -xsl:merge.xsl -o:DMAV_merged.xtf fosnr=449
```

```
java -jar /Users/stefan/apps/ilivalidator-1.14.3/ilivalidator-1.14.3.jar DMAV_merged.xtf
```

### split
```
java -jar /Users/stefan/apps/SaxonHE12-5J/saxon-he-12.5.jar -s:/Users/stefan/Downloads/DMAVTYM_Alles_V1_0.xtf -xsl:split.xsl -o:DMAV_logging.xtf fosnr=449
```


