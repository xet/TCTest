import java.io.IOException;


public class knark {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BOMWriter bw = new BOMWriter("bom_view.xsl", topItemRev);
		bw.addTopObject();
		bw.addTopProjectData();
		createBOMFromBOMStorage(bomstorage)
		BOMXMLHandler datahandler = bw.getHandler();
		datahandler.transformXMLWithXSL();
			try {
				System.out.println(datahandler.getOutputStream().toString());
			}
			catch (NullPointerException e) {
				System.out.println("No data in stream! ...");
			}
	}

}
