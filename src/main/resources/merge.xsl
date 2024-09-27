<?xml version="1.0"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="fosnr" select="'449'"/>

    <xsl:variable name="DMAV_FixpunkteLV_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_FixpunkteLV_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_FixpunkteLV_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
                <!-- truly empty node: 
                <xsl:sequence select="()"/> -->
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_FixpunkteAVKategorie2_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_FixpunkteAVKategorie2_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_FixpunkteAVKategorie2_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>


    <xsl:variable name="DMAV_PLZ_Ortschaft_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_PLZ_Ortschaft_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_PLZ_Ortschaft_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>

    <xsl:template match="/ili:transfer">
        <!-- <xsl:message><xsl:value-of select="$fosnr"/></xsl:message> -->
        <!-- <xsl:message>Hallo Welt</xsl:message> -->
        
        <ili:transfer xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">
            <xsl:copy-of select="ili:headersection"/>
            <ili:datasection>
                <xsl:copy-of select="$DMAV_FixpunkteLV_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_FixpunkteAVKategorie2_V1_0/ili:transfer/ili:datasection/*"/>

                <xsl:copy-of select="$DMAV_PLZ_Ortschaft_V1_0/ili:transfer/ili:datasection/*"/>
            </ili:datasection>
        </ili:transfer>
    </xsl:template>

</xsl:stylesheet>
