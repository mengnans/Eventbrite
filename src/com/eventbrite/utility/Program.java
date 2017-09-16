package com.eventbrite.utility;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;

import java.io.*;

/**
 * Created by Chensey on 2017/9/12.
 */
public class Program {
   
   public static void main(String[] args) throws IOException, TranscoderException {
       String xml = "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"501px\" height=\"481px\" version=\"1.1\" style=\"background-color: rgb(255, 255, 255);\"><defs/><g transform=\"translate(0.5,0.5)\"><rect x=\"20\" y=\"10\" width=\"460\" height=\"440\" fill=\"none\" stroke=\"#000000\" pointer-events=\"none\"/><rect x=\"0\" y=\"0\" width=\"500\" height=\"480\" fill=\"none\" stroke=\"#000000\" pointer-events=\"none\"/><g transform=\"translate(334.5,204.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"120\" height=\"30\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 27px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 122px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">Mengnan Shi</div></div></foreignObject><text x=\"60\" y=\"29\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"27px\" font-family=\"Helvetica\">Mengnan Shi</text></switch></g><g transform=\"translate(390.5,243.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"24\" height=\"12\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 24px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">2017/09/15 20:24:55</div></div></foreignObject><text x=\"12\" y=\"12\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"12px\" font-family=\"Helvetica\">2017/09/15 20:24:55</text></switch></g><g transform=\"translate(76.5,50.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"226\" height=\"58\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 50px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 226px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">Certificate</div></div></foreignObject><text x=\"113\" y=\"54\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"50px\" font-family=\"Helvetica\">Certificate</text></switch></g></g></svg>\n";
          convertToPng(xml,"");
   }

    public static void convertToPng(String svgCode, String pngFilePath) throws IOException,
            TranscoderException {

        File file = new File("E:\\example.png");

        FileOutputStream outputStream = new FileOutputStream("E:\\example.png");
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void convertToPng(String svgCode, OutputStream outputStream)
            throws TranscoderException, IOException {
        try {
            byte[] bytes = svgCode.getBytes("utf-8");
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
