<?xml version="1.0"?>
<xsl:stylesheet version="3.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS"

    xmlns:DMAV_FixpunkteLV_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteLV_V1_0"
    xmlns:DMAV_FixpunkteAVKategorie2_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteAVKategorie2_V1_0" 
    xmlns:DMAV_FixpunkteAVKategorie3_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteAVKategorie3_V1_0"
    xmlns:DMAV_Bodenbedeckung_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Bodenbedeckung_V1_0"
    xmlns:DMAV_Einzelobjekte_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Einzelobjekte_V1_0" 
    xmlns:DMAV_Nomenklatur_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Nomenklatur_V1_0" 
    xmlns:DMAV_Grundstuecke_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Grundstuecke_V1_0" 
    xmlns:DMAV_Rohrleitungen_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Rohrleitungen_V1_0" 
    xmlns:DMAV_HoheitsgrenzenLV_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_HoheitsgrenzenLV_V1_0" 
    xmlns:DMAV_HoheitsgrenzenAV_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_HoheitsgrenzenAV_V1_0" 
    xmlns:DMAV_Toleranzstufen_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Toleranzstufen_V1_0" 
    xmlns:DMAV_DauerndeBodenverschiebungen_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_DauerndeBodenverschiebungen_V1_0" 
    xmlns:DMAV_PLZ_Ortschaft_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_PLZ_Ortschaft_V1_0" 
    xmlns:DMAV_Gebaeudeadressen_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Gebaeudeadressen_V1_0" 
    xmlns:DMAVSUP_UntereinheitGrundbuch_V1_0="http://www.interlis.ch/xtf/2.4/DMAVSUP_UntereinheitGrundbuch_V1_0" 
    xmlns:DMAV_Dienstbarkeitsgrenzen_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Dienstbarkeitsgrenzen_V1_0"
    >

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="fosnr" select="'XXXX'"/>

    <xsl:template match="/ili:transfer/ili:datasection">
        <models>
        <xsl:apply-templates select="DMAV_FixpunkteLV_V1_0:FixpunkteLV
            | DMAV_FixpunkteAVKategorie2_V1_0:FixpunkteAVKategorie2 
            | DMAV_FixpunkteAVKategorie3_V1_0:FixpunkteAVKategorie3
            | DMAV_Bodenbedeckung_V1_0:Bodenbedeckung
            | DMAV_Einzelobjekte_V1_0:Einzelobjekte
            | DMAV_Nomenklatur_V1_0:Nomenklatur
            | DMAV_Grundstuecke_V1_0:Grundstuecke
            | DMAV_Rohrleitungen_V1_0:Rohrleitungen
            | DMAV_HoheitsgrenzenLV_V1_0:HoheitsgrenzenLV
            | DMAV_HoheitsgrenzenAV_V1_0:HoheitsgrenzenAV
            | DMAV_Toleranzstufen_V1_0:Toleranzstufen
            | DMAV_DauerndeBodenverschiebungen_V1_0:DauerndeBodenverschiebungen
            | DMAV_PLZ_Ortschaft_V1_0:PLZ_Ortschaft
            | DMAV_Gebaeudeadressen_V1_0:Gebaeudeadressen
            | DMAVSUP_UntereinheitGrundbuch_V1_0:UntereinheitGrundbuch
            | DMAV_Dienstbarkeitsgrenzen_V1_0:Dienstbarkeitsgrenzen" 
            />
        </models>
    </xsl:template>

    <xsl:template match="DMAV_FixpunkteLV_V1_0:FixpunkteLV
        | DMAV_FixpunkteAVKategorie2_V1_0:FixpunkteAVKategorie2
        | DMAV_FixpunkteAVKategorie3_V1_0:FixpunkteAVKategorie3
        | DMAV_Bodenbedeckung_V1_0:Bodenbedeckung
        | DMAV_Einzelobjekte_V1_0:Einzelobjekte
        | DMAV_Nomenklatur_V1_0:Nomenklatur
        | DMAV_Grundstuecke_V1_0:Grundstuecke
        | DMAV_Rohrleitungen_V1_0:Rohrleitungen
        | DMAV_HoheitsgrenzenLV_V1_0:HoheitsgrenzenLV
        | DMAV_HoheitsgrenzenAV_V1_0:HoheitsgrenzenAV
        | DMAV_Toleranzstufen_V1_0:Toleranzstufen
        | DMAV_DauerndeBodenverschiebungen_V1_0:DauerndeBodenverschiebungen
        | DMAV_PLZ_Ortschaft_V1_0:PLZ_Ortschaft
        | DMAV_Gebaeudeadressen_V1_0:Gebaeudeadressen
        | DMAVSUP_UntereinheitGrundbuch_V1_0:UntereinheitGrundbuch
        | DMAV_Dienstbarkeitsgrenzen_V1_0:Dienstbarkeitsgrenzen" 
        >

        <xsl:message>Processing: <xsl:value-of select="name()" /></xsl:message>
        <!-- Die Namespaces sind pro Modell klar (implizit) definiert. Vorgehen ist robust. -->
        <xsl:variable name="modelName" select="substring-after(namespace-uri(.), '/2.4/')"/>

        <xsl:result-document method="xml" href="{$modelName}.{$fosnr}.xtf">
            <ili:transfer xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">
            <ili:headersection>
                <ili:models>
                    <ili:model>DMAV_FixpunkteLV_V1_0</ili:model>
                    <ili:model>DMAV_FixpunkteAVKategorie2_V1_0</ili:model>
                    <ili:model>DMAV_FixpunkteAVKategorie3_V1_0</ili:model>
                    <ili:model>DMAV_Bodenbedeckung_V1_0</ili:model>
                    <ili:model>DMAV_Einzelobjekte_V1_0</ili:model>
                    <ili:model>DMAV_Nomenklatur_V1_0</ili:model>
                    <ili:model>DMAV_Grundstuecke_V1_0</ili:model>
                    <ili:model>DMAV_Rohrleitungen_V1_0</ili:model>
                    <ili:model>DMAV_HoheitsgrenzenLV_V1_0</ili:model>
                    <ili:model>DMAV_HoheitsgrenzenAV_V1_0</ili:model>
                    <ili:model>DMAV_Toleranzstufen_V1_0</ili:model>
                    <ili:model>DMAV_DauerndeBodenverschiebungen_V1_0</ili:model>
                    <ili:model>DMAV_PLZ_Ortschaft_V1_0</ili:model>
                    <ili:model>DMAV_Gebaeudeadressen_V1_0</ili:model>  
                    <ili:model>DMAVSUP_UntereinheitGrundbuch_V1_0</ili:model>
                    <ili:model>DMAV_Dienstbarkeitsgrenzen_V1_0</ili:model>
                </ili:models>
                <ili:sender>DMAVMerger</ili:sender>
            </ili:headersection>
            <ili:datasection>
            <xsl:copy-of select="."/>
            </ili:datasection>
            </ili:transfer>
        </xsl:result-document>

        <!-- Output file is our logging file.-->
        <model><xsl:value-of select="$modelName"/></model>
    </xsl:template>
    
</xsl:stylesheet>
