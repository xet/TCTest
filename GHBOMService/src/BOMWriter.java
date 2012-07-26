
public class BOMWriter {
	private BOMXMLHandler datahandler;
	private ItemRevision topRevision;
	public BOMWriter(String stylesheet_filename) {
		
		BOMXMLHandler datahandler = new BOMXMLHandler();
		datahandler.defineStyleSheet(stylesheet_filename);
		startBOM();
	}
	public BOMWriter(String stylesheet_filename, ItemRevision rev) {
		this(stylesheet_filename);
		setTopRevision(rev);
	}
	public BOMXMLHandler getHandler() {
			return datahandler;
	}
	public void setTopRevision(ItemRevision rev) {
		topRevision = rev;
	}
	private void startBOM() {
		datahandler.writeToBuff("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		// Main tag: bom
		datahandler.writeToBuff("<bom>");
	}
	public void endBOM() {
		datahandler.writeToBuff("</bom>");
	}
	public void startBOMLines() {
		datahandler.writeToBuff("<bomlines>");
	}
	public void endBOMLines() {
		datahandler.writeToBuff("</bomlines>");
	}
	public void startRevisionLines() {
		datahandler.writeToBuff("<itemrevisions>");
	}
	public void endRevisionLines() {
		datahandler.writeToBuff("</itemrevisions>");
	}
	public void startItemLines() {
		datahandler.writeToBuff("<items>");
	}
	public void endItemLines() {
		datahandler.writeToBuff("</items>");
	}
	public void addTopObject() {
		datahandler.writeToBuff("<object>");
		datahandler.writeToBuff("<itemid>" + topRevision.get_item_id() + "</itemid>");
		datahandler.writeToBuff("<itemrev>" + topRevision.get_item_revision_id() + "</itemrev>");
		datahandler.writeToBuff("<projectid>" + topRevision.get_project_id() + "</projectid>");
		datahandler.writeToBuff("<date>2012-03-04</date>");
		datahandler.writeToBuff("<time>16:34:16</time>");
		datahandler.writeToBuff("<revisionrule name=\"Latest Working\">");
		datahandler.writeToBuff("<description>Latest Working else Latest Any Status</description>");
		datahandler.writeToBuff("</revisionrule>");
		datahandler.writeToBuff("<totalmass>98</totalmass>");
		datahandler.writeToBuff("</object>");
	}
	public void addTopProjectData() {
		datahandler.writeToBuff("<project id=\"" + topRevision.get_project_id() + "\">");
		datahandler.writeToBuff("<name>" + topRevision.get_project_id().split("-",2)[1] + "</name>");
		datahandler.writeToBuff("</project>");
	}
	public void addBOMLine(ItemRevision itemRev, BOMOccurence bl) {
		datahandler.writeToBuff("<bomline>");
		datahandler.writeToBuff("<itemid>" + itemRev.get_item_id() +"</itemid>");
		datahandler.writeToBuff("<itemrev>" + itemRev.get_item_revision_id() +"</itemrev>");
		datahandler.writeToBuff("<quantity>" + bl.getQuantity() +"</quantity>");
		datahandler.writeToBuff("</bomline>");
	}
	public void addItemRevision(itemRev) {
		datahandler.writeToBuff("<itemrevision id=\"" + itemRev.get_item_id() + "\" rev=\"" + itemRev.get_item_revision_id() + "\">");
		datahandler.writeToBuff("<name>" + itemRev.get_item_name() + "</name>");
		datahandler.writeToBuff("<userdatalist>");
		datahandler.writeToBuff("<userdata name=\"gh4Weight\">5</userdata>");
		datahandler.writeToBuff("</userdatalist>");
		datahandler.writeToBuff("</itemrevision>");
	}
	public void addItem(item) {
		datahandler.writeToBuff("<item id=\"" + item.get_item_id() + "\">");
		datahandler.writeToBuff("<userdatalist>");
		datahandler.writeToBuff("<userdata name=\"gh4Weight\">5</userdata>");
		datahandler.writeToBuff("</userdatalist>");
		datahandler.writeToBuff("</item>");
	}
	public void createBOMFromBOMStorage(BOMStorage BOMList) {
		HashMap<ItemRevision, BOMOccurence> revisionMap= BOMLIST.getBOMLines();
		addAllBOMLines(revisionMap);
		addAllRevisionLines(revisionMap);
		addAllItemLines(revisionMap);
		endBOM();
	}
	addAllBOMLines(HashMap<ItemREvision, BOMOccurence> revisionMap) {
		startBOMLines();
		for (ItemRevision rev : revisionMap.getKeys() ) {
			addBOMLine(rev, revisionMap.get(rev) );
		}
		endBOMLines();
	}
	public void addAllRevisionLines(HashMap<ItemREvision, BOMOccurence> revisionMap) {
		startRevisionLines();
		for (ItemRevision rev : revisionMap.getKeys()) {
			addItemRevision(rev);
		}
		endRevisionLines();
	}
	public void addAllItemLines(HashMap<ItemREvision, BOMOccurence> revisionMap) {
		startItemLines();
		for (ItemRevision rev : revisionMap.getKeys() ) {
			addItem(rev.get_items_tag());
		}
		endItemLines();
	}
}
