<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
		<!-- GLOBALS -->

<xsl:variable name="root" select="/bom" />

<xsl:template match="/">
	<html>
		<head>
			<title>BOM</title>
			<style>
				.dayName { text-align: center; }
				.s1 {text-align: center; font-size: 14px; }
				.s2 {text-align: center; font-size: 25px; }

				.time {font-weight: bold; }

  thead { display:table-header-group }
  tfoot { display:table-footer-group }

				td { font-family: Tahoma; font-size: 12px; padding: 2px; page-break-inside:avoid; page-break-after:auto; white-space: nowrap }
			  tr    { page-break-inside:avoid; page-break-before: auto ; page-break-after:auto; white-space: nowrap }
				th, .t1 { font-family: Tahoma; font-size: 12px; font-weight: bold; text-align: left; padding: 2px;}
				table { border-left: 1px solid black; border-right: 1px solid black; border-bottom: 1px solid black; border-collapse: collapse;  page-break-after:auto; white-space: nowrap }
				.lastLine td { padding-bottom: 0px; }
				.tableHeader { text-align: center; background-color: lightgray; font-weight: bold; font-size: 15px;cursor: default; border-top: 4px solid black; }
				.sHead {cursor: default; font-size: 10px; font-weight: bold; border-left: 1px solid black; border-bottom: 1px solid black; text-align: center;}
			</style>
			<style type="text/css" media="print">
				.pdfImg { display: none; }
			</style>
			<script lang="JavaScript">
				var newColor="yellow";
				var permColor = "lightgray";
				var permNewColor = "#999999";
				var objs = new Array();
				var perm = new Array();

				function chBg(obj) {
					objs[obj]= obj.style.backgroundColor;
					if (objs[obj] == permColor)
						nc = permNewColor;
					else
						nc = newColor;
					obj.style.backgroundColor = nc;
					}
					function permChBg(obj) { 
					if ( objs[obj] == permColor)
						color = "";
					else
						color = permColor;
					obj.style.backgroundColor = permNewColor;
					objs[obj] = color;	
				}
				function chBgBack(obj) {
					obj.style.backgroundColor = objs[obj];
					}
			</script>
		</head>
		<body>
			<xsl:variable name="obj" select="$root/object"/>
			<xsl:variable name="mainItemId" select="$obj/itemid"/>
			<xsl:variable name="mainItemRev" select="$obj/itemrev"/>
			<xsl:variable name="mainItemRevObj" select="/bom/itemrevisions/itemrevision[@id=$mainItemId][@rev=$mainItemRev]"/>
			<xsl:variable name="mainProjectId" select="$obj/projectid"/>
			<xsl:variable name="mainDate" select="$obj/date"/>
			<xsl:variable name="mainTime" select="$obj/time"/>
			<xsl:variable name="mainRevRule" select="$obj/revisionrule"/>
			<table>
				<thead>
				<tr>
					<td colspan="4" rowspan="2" style="border-right: 1px solid black; border-bottom: 1px solid black; padding: 0; border-top: 1px solid black;">
						<img src="I:\MALLAR\Logotyp\Hardtech.jpg" height="70"/>
					</td>
					<td colspan="1" rowspan="2" style="cursor: default; font-size: 30px; font-weight: bold; text-align: center; border-bottom: 1px solid black; border-top: 1px solid black;">COMPONENT LIST</td>
					<td colspan="5" class="sHead" style="padding: 0; border-top: 1px solid black;">Revision Rule</td>
				</tr>
				<tr>
					<td onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" colspan="5" style="font-size: 20px; font-weight: bold; border-left: 1px solid black; text-align: center;"><xsl:value-of select="$mainRevRule/@name"/></td>
				</tr>
				<tr>
					<td colspan="2" class="sHead" style="border-right: 1px solid black; text-align: left;" width="120">Project No</td>
					<td width="140" class="sHead" style="border-right: 1px solid black; text-align: left;">Item ID</td>
					<td class="sHead" style="border-right: 1px solid black; text-align: left;">Rev</td>
					<td colspan="3" width="150" class="sHead" style=" border-top: 1px solid black; border-bottom: 1px solid black; text-align: left;">Item Name</td>
					<td colspan="3" class="sHead" style="text-align: left; border-top: 1px solid black;">Date</td>
				</tr>
				<tr class="lastLine">
					<td colspan="2" onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" style="border-left: 1px solid black; border-bottom: 1px solid black; border-right: 1px solid black; font-size: 20px;"><xsl:value-of select="$mainProjectId"/></td>
					<td onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" style="border-right: 1px solid black;">
						<xsl:value-of select="$mainItemId"/>
					</td>
					<td onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" style="border-right: 1px solid black;">
						<xsl:value-of select="$mainItemRev"/>
					</td>
					<td onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" colspan="3" style="border-right: 1px solid black;">
						<xsl:value-of select="$mainItemRevObj/name"/>
					</td>
					<td colspan="3" onMouseOver="chBg(this);" onMouseOut="chBgBack(this);">
						<xsl:value-of select="$mainDate"/>&#32;<xsl:value-of select="$mainTime"/>
					</td>
				</tr>	
				<tr>
					<td colspan="10" class="sHead" style=" border-top: 1px solid black; text-align: left; ">Project Name</td>
				</tr>
				<tr>
					<td onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" colspan="10" style="font-size: 20px; padding: 2px; border-right: 1px solid black;">
						<xsl:value-of select="$root/project[@id=$mainProjectId]/name"/>
					</td>
				</tr>
				<tr>
					<td colspan="10" class="tableHeader">COMPONENT LIST OF #<xsl:value-of select="$mainItemId"/>/<xsl:value-of select="$mainItemRev"/></td>
				</tr>
				<tr>
					<th>TC ID</th>
					<th>REV</th>
					<th colspan="2">NAME</th>
					<th colspan="1" width="300">ARTICLE NUMBER</th>
					<th colspan="2" width="300">MOVEX NUMBER</th>
					<th style="text-align: right;">QTY</th>
					<th style="text-align: right;">WEIGHT</th>
				</tr>
			</thead>
			<tbody>
				<xsl:variable name="bomlines" select="/bom/bomlines"/>
				<xsl:for-each select="$bomlines/bomline">
					<xsl:sort select="itemid"/>
					<xsl:sort select="itemrev"/>
					<xsl:call-template name="createBOMLine">
						<xsl:with-param name="currentLine" select="."/>
					</xsl:call-template>
				</xsl:for-each>
				<tr onMouseOver="chBg(this);" onMouseOut="chBgBack(this);">
					<td colspan="8" style="text-align: right; border-top: 2px solid black; font-weight: bold;">TOTAL</td>
					<td style="text-align: right; border-top: 2px solid black; font-weight: bold;"><xsl:value-of select="$obj/totalmass"/> KG</td>
				</tr>
			</tbody>
			</table>
		</body>
	</html>
</xsl:template>

<xsl:template name="createBOMLine">
	<xsl:param name="currentLine"/>
		<xsl:variable name="itemid" select="$currentLine/itemid"/>
		<xsl:variable name="itemrev" select="$currentLine/itemrev"/>
		<xsl:variable name="quantity" select="$currentLine/quantity"/>
		<xsl:variable name="itemRevObj" select="/bom/itemrevisions/itemrevision[@id=$itemid][@rev=$itemrev]"/>
		<xsl:variable name="itemObj" select="/bom/items/item[@id=$itemid]"/>
		<xsl:variable name="itemRevName" select="$itemRevObj/name"/>
		<xsl:variable name="weight" select="$itemRevObj/userdatalist/userdata[@name='gh4Weight']"/>
		<tr onMouseOver="chBg(this);" onMouseOut="chBgBack(this);" onClick="permChBg(this);" style="cursor: hand;">
			<td>
				<xsl:value-of select="$itemid"/>
			</td>
			<td>
				<xsl:value-of select="$itemrev"/>
			</td>
			<td width="250" colspan="2">
				<xsl:value-of select="$itemRevName"/>
			</td>
			<td colspan="1">
				ART.NO
			</td>
			<td colspan="2">
				MOVEX. NO
			</td>
			<td style="text-align: right;">
				<xsl:value-of select="$quantity"/>
			</td>
			<td style="text-align: right;">
				<xsl:value-of select="$weight*$quantity"/> KG
			</td>
			<td style="text-align: center">
				<img class="pdfImg" src="pdficon_small.png" width="16" height="16" alt="PDF" />
			</td>
		</tr>
</xsl:template>

</xsl:stylesheet>

