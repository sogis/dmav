<?xml version="1.0"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="fosnr" select="'449'"/>
    <xsl:variable name="file1" select="document(concat('DMAV_FixpunkteAVKategorie2_V1_0.', $fosnr, '.xtf'))"/>
    <xsl:variable name="file2" select="document(concat('DMAV_PLZ_Ortschaft_V1_0.', $fosnr, '.xtf'))"/>

    <xsl:template match="/ili:transfer">
        <xsl:message>Hallo Welt</xsl:message>
        <xsl:message><xsl:value-of select="$fosnr"/></xsl:message>
        <ili:transfer xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">
            <xsl:copy-of select="ili:headersection"/>
            <ili:datasection>
                <xsl:copy-of select="$file1/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$file2/ili:transfer/ili:datasection/*"/>
            </ili:datasection>
        </ili:transfer>
    </xsl:template>

</xsl:stylesheet>
