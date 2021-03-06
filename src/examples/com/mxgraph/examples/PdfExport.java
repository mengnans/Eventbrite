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
	public static void generagePDF(String xml, String path) throws Exception
	{
        xml = "<mxGraphModel dx=\"1364\" dy=\"863\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><mxCell id=\"2\" value=\"hello\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"270\" y=\"180\" width=\"40\" height=\"20\" as=\"geometry\"/></mxCell><mxCell id=\"3\" value=\"\" style=\"ellipse;whiteSpace=wrap;html=1;aspect=fixed;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"520\" y=\"260\" width=\"80\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"4\" value=\"\" style=\"endArrow=classic;startArrow=classic;html=1;exitX=0.75;exitY=1;\" edge=\"1\" parent=\"1\" source=\"2\" target=\"3\"><mxGeometry width=\"50\" height=\"50\" relative=\"1\" as=\"geometry\"><mxPoint x=\"340\" y=\"210\" as=\"sourcePoint\"/><mxPoint x=\"390\" y=\"160\" as=\"targetPoint\"/></mxGeometry></mxCell></root></mxGraphModel>";
        xml ="<mxGraphModel dx=\"1364\" dy=\"863\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" background=\"#ffffff\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><mxCell id=\"2\" value=\"hello\" style=\"text;html=1;strokeColor=none;fillColor=none;align=center;verticalAlign=middle;whiteSpace=wrap;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"270\" y=\"180\" width=\"40\" height=\"20\" as=\"geometry\"/></mxCell><mxCell id=\"3\" value=\"\" style=\"ellipse;whiteSpace=wrap;html=1;aspect=fixed;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"520\" y=\"260\" width=\"80\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"4\" value=\"\" style=\"endArrow=classic;startArrow=classic;html=1;exitX=0.75;exitY=1;\" edge=\"1\" parent=\"1\" source=\"2\" target=\"3\"><mxGeometry width=\"50\" height=\"50\" relative=\"1\" as=\"geometry\"><mxPoint x=\"340\" y=\"210\" as=\"sourcePoint\"/><mxPoint x=\"390\" y=\"160\" as=\"targetPoint\"/></mxGeometry></mxCell><mxCell id=\"5\" value=\"\" style=\"shape=document;whiteSpace=wrap;html=1;boundedLbl=1;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"240\" y=\"290\" width=\"120\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"6\" value=\"\" style=\"shape=cylinder;whiteSpace=wrap;html=1;boundedLbl=1;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"430\" y=\"320\" width=\"60\" height=\"80\" as=\"geometry\"/></mxCell><mxCell id=\"7\" value=\"\" style=\"whiteSpace=wrap;html=1;aspect=fixed;\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"90\" y=\"210\" width=\"80\" height=\"80\" as=\"geometry\"/></mxCell></root></mxGraphModel>";
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
            mxCell cell = (mxCell) codec.decode(elt);
            cells[i] = cell;
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
		Document document = new Document(new Rectangle((float) (bounds
				.getWidth()), (float) (bounds.getHeight())));
// 		Document document = new Document(new Rectangle((float) (pageWidth), (float) (pageHeight)));
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("E:/11221.pdf"));
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
			generagePDF("","");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
