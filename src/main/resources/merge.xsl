<?xml version="1.0"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS">

    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:param name="fosnr" select="'XXXX'"/>

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
    <xsl:variable name="DMAV_FixpunkteAVKategorie3_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_FixpunkteAVKategorie3_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_FixpunkteAVKategorie3_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Bodenbedeckung_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Bodenbedeckung_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Bodenbedeckung_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Einzelobjekte_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Einzelobjekte_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Einzelobjekte_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Nomenklatur_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Nomenklatur_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Nomenklatur_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Grundstuecke_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Grundstuecke_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Grundstuecke_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Rohrleitungen_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Rohrleitungen_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Rohrleitungen_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_HoheitsgrenzenLV_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_HoheitsgrenzenLV_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_HoheitsgrenzenLV_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_HoheitsgrenzenAV_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_HoheitsgrenzenAV_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_HoheitsgrenzenAV_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Toleranzstufen_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Toleranzstufen_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Toleranzstufen_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_DauerndeBodenverschiebungen_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_DauerndeBodenverschiebungen_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_DauerndeBodenverschiebungen_V1_0.', $fosnr, '.xtf'))"/>
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
    <xsl:variable name="DMAV_Gebaeudeadressen_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Gebaeudeadressen_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Gebaeudeadressen_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAVSUP_UntereinheitGrundbuch_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAVSUP_UntereinheitGrundbuch_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAVSUP_UntereinheitGrundbuch_V1_0.', $fosnr, '.xtf'))"/>
            </xsl:when>
            <xsl:otherwise>
                <empty/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>
    <xsl:variable name="DMAV_Dienstbarkeitsgrenzen_V1_0">
        <xsl:choose>
            <xsl:when test="doc-available(concat('DMAV_Dienstbarkeitsgrenzen_V1_0.', $fosnr, '.xtf'))">
                <xsl:copy-of select="document(concat('DMAV_Dienstbarkeitsgrenzen_V1_0.', $fosnr, '.xtf'))"/>
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
                <xsl:copy-of select="$DMAV_FixpunkteAVKategorie3_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Bodenbedeckung_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Einzelobjekte_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Nomenklatur_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Grundstuecke_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Rohrleitungen_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_HoheitsgrenzenLV_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_HoheitsgrenzenAV_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Toleranzstufen_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_DauerndeBodenverschiebungen_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_PLZ_Ortschaft_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Gebaeudeadressen_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAVSUP_UntereinheitGrundbuch_V1_0/ili:transfer/ili:datasection/*"/>
                <xsl:copy-of select="$DMAV_Dienstbarkeitsgrenzen_V1_0/ili:transfer/ili:datasection/*"/>
            </ili:datasection>
        </ili:transfer>
    </xsl:template>

</xsl:stylesheet>
