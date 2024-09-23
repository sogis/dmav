<?xml version="1.0"?>
<xsl:stylesheet version="3.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS"

    xmlns:DMAV_FixpunkteLV_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteLV_V1_0"
    xmlns:DMAV_FixpunkteAVKategorie2_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteAVKategorie2_V1_0" 
    xmlns:DMAV_FixpunkteAVKategorie3_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_FixpunkteAVKategorie3_V1_0"
    xmlns:DMAV_Bodenbedeckung_V1_0="http://www.interlis.ch/xtf/2.4/DMAV_Bodenbedeckung_V1_0"

    >

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="fosnr" select="'XXXX'"/>
    <!--
    <xsl:variable name="file1" select="document(concat('DMAV_FixpunkteAVKategorie2_V1_0.', $fosnr, '.xtf'))"/>
    <xsl:variable name="file2" select="document(concat('DMAV_PLZ_Ortschaft_V1_0.', $fosnr, '.xtf'))"/>
    -->

    <xsl:template match="/ili:transfer/ili:datasection">
        <models>

        <!--DMAV_FixpunkteLV_V1_0:FixpunkteLV-->
        <xsl:apply-templates select="*[local-name() = 'FixpunkteLV']
            | DMAV_FixpunkteAVKategorie2_V1_0:FixpunkteAVKategorie2 
            | DMAV_FixpunkteAVKategorie3_V1_0:FixpunkteAVKategorie3
            | DMAV_Bodenbedeckung_V1_0:Bodenbedeckung" 
            />

        </models>
    </xsl:template>

    <!--DMAV_FixpunkteLV_V1_0:FixpunkteLV-->
    <xsl:template match="*[local-name() = 'FixpunkteLV']
        | DMAV_FixpunkteAVKategorie2_V1_0:FixpunkteAVKategorie2
        | DMAV_FixpunkteAVKategorie3_V1_0:FixpunkteAVKategorie3
        | DMAV_Bodenbedeckung_V1_0:Bodenbedeckung" 
        >

        <!--<xsl:message>Hallo Welt</xsl:message>-->
        <xsl:message><xsl:value-of select="$fosnr" /></xsl:message>
        <!-- Sollte robust genug sein, da ich die Namespaces selber definiere. Sonst halt choose-Orgie o.ä. -->
        <!-- Es braucht einen lookup-Table, wenn wir ohne Namespace arbeiten.-->
        <xsl:variable name="modelName" select="substring-after(namespace-uri(.), '/2.4/')"/>

        <xsl:result-document method="xml" href="{$modelName}.{$fosnr}.xtf">

            <ili:transfer xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">
            <ili:headersection>
                <ili:models>
                <ili:model>DMAV_FixpunkteLV_V1_0</ili:model>
                <ili:model>DMAV_FixpunkteAVKategorie2_V1_0</ili:model>
                <ili:model>DMAV_FixpunkteAVKategorie3_V1_0</ili:model>
                <ili:model>DMAV_Bodenbedeckung_V1_0</ili:model>
                </ili:models>
                <ili:sender>DMAVSplitter</ili:sender>
            </ili:headersection>
            <ili:datasection>
            <xsl:copy-of select="."/>
            </ili:datasection>
            </ili:transfer>
        </xsl:result-document>

        <model><xsl:value-of select="$modelName"/></model>

    </xsl:template>


</xsl:stylesheet>
