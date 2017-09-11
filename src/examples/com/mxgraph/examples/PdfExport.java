package examples.com.mxgraph.examples;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxCellRenderer.CanvasFactory;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import com.mxpdf.text.Document;
import com.mxpdf.text.Rectangle;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfWriter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.FileOutputStream;

// This example requires iText from http://www.lowagie.com/iText/
public class PdfExport
{
	public PdfExport() throws Exception
	{


        String xml = "\n" +
                "<mxGraphModel dx=\"1604\" dy=\"958\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"2\" value=\"Hello,\" vertex=\"1\"><mxGeometry x=\"620\" y=\"620\" width=\"80\" height=\"30\" as=\"geometry\"/></mxCell><mxCell id=\"3\" value=\"World!\" vertex=\"1\"><mxGeometry x=\"720\" y=\"720\" width=\"80\" height=\"30\" as=\"geometry\"/></mxCell><mxCell id=\"4\" value=\"\" edge=\"1\" source=\"2\" target=\"3\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
//        xml = "<mxGraphModel grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/></root></mxGraphModel>";
//        xml = "<mxGraphModel dx=\"1363\" dy=\"814\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><mxCell id=\"2\" value=\"thundersmn&lt;div&gt;&lt;br&gt;&lt;/div&gt;\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"290\" y=\"210\" width=\"40\" height=\"20\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
//        xml = "<mxGraphModel dx=\"1604\" dy=\"958\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><mxCell id=\"3\" value=\"thundersmn\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"260\" y=\"150\" width=\"360\" height=\"20\" as=\"geometry\"/></mxCell><mxCell id=\"4\" value=\"\" style=\"shape=ext;double=1;whiteSpace=wrap;html=1;aspect=fixed;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"260\" y=\"340\" width=\"80\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"5\" value=\"ffffffffffffffffffffffffffffffffffffffffffffff\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"530\" y=\"320\" width=\"40\" height=\"20\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
        xml = "<mxGraphModel dx=\"1604\" dy=\"958\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><mxCell id=\"3\" value=\"thundersmn\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"260\" y=\"150\" width=\"360\" height=\"20\" as=\"geometry\"/></mxCell><mxCell id=\"4\" value=\"\" style=\"shape=ext;double=1;whiteSpace=wrap;html=1;aspect=fixed;fontColor=#66FF66;fillColor=#B266FF;strokeWidth=8;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"260\" y=\"340\" width=\"80\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"5\" value=\"ffffffffffffffffffffffffffffffffffffffffffffff\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"530\" y=\"320\" width=\"40\" height=\"20\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
        org.w3c.dom.Document doc = mxUtils.parseXml(xml);
        mxCodec codec = new mxCodec(doc);
        Node elt = doc.getDocumentElement().getFirstChild().getFirstChild();
        Node model = doc.getDocumentElement();
        NamedNodeMap map = model.getAttributes();
        String pageWidthString = map.getNamedItem("pageWidth").getNodeValue();
        String pageHeightString = map.getNamedItem("pageHeight").getNodeValue();
        int pageWidth = Integer.parseInt(pageWidthString);
        int pageHeight = Integer.parseInt(pageHeightString);
        System.out.println(pageWidth);
        System.out.println(pageHeight);
        mxCell[] cells = new mxCell[100];
        // get all the cells
        int i = 0;
        while (elt != null)
        {
            cells[i] = (mxCell) codec.decode(elt);
            elt = elt.getNextSibling();
            i++;
        }

        mxGraph graph = new mxGraph();
        graph.addCells(cells);

		// Creates graph with model
//		mxGraph graph = new mxGraph();
//		Object parent = graph.getDefaultParent();
//
//		graph.getModel().beginUpdate();
//		try
//		{
//			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
//					30);
//			mxCell v2 = (mxCell) graph.insertVertex(parent, null, "World!",
//					240, 150, 80, 30);
//			graph.insertEdge(parent, null, "e1", v1, v2);
//		}
//		finally
//		{
//			graph.getModel().endUpdate();
//		}

        // turn graph to xml and show it
		mxCodec enc = new mxCodec();
		Object[] newcells;
		graph.selectAll();
        newcells = graph.getSelectionCells();
        System.out.println("cells.");
        System.out.println(mxUtils.getPrettyXml(enc.encode(newcells)));


		mxRectangle bounds = graph.getGraphBounds();
		System.out.println((float) (bounds.getWidth()));
//		Document document = new Document(new Rectangle((float) (bounds
//				.getWidth()), (float) (bounds.getHeight())));
 		Document document = new Document(new Rectangle((float) (pageWidth), (float) (pageHeight)));
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("E:/1.pdf"));
		document.open();
		final PdfContentByte cb = writer.getDirectContent();

        mxGraphics2DCanvas canvas = (mxGraphics2DCanvas) mxCellRenderer
                .drawCells(graph, null, 1, null, new CanvasFactory()
                {
                    public mxICanvas createCanvas(int width, int height)
                    {
                        Graphics2D g2 = cb.createGraphics(width, height);
                        return new mxGraphics2DCanvas(g2);
                    }
                });

		canvas.getGraphics().dispose();
		document.close();
	}

	public static void main(String[] args)
	{
		try
		{
			new PdfExport();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
