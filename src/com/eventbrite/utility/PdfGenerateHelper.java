package com.eventbrite.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerateHelper {

   public static void main(String args[]) {
//        String argFileAddress = "E:\\pdf\\37025475193\\Mengnan Shi,thundersmn@gmail.com.pdf";
//        String argSgvContent = "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\"501px\" height=\"481px\" version=\"1.1\" style=\"background-color: rgb(255, 255, 255);\"><defs/><g transform=\"translate(0.5,0.5)\"><rect x=\"20\" y=\"10\" width=\"460\" height=\"440\" fill=\"none\" stroke=\"#000000\" pointer-events=\"none\"/><rect x=\"0\" y=\"0\" width=\"500\" height=\"480\" fill=\"none\" stroke=\"#000000\" pointer-events=\"none\"/><g transform=\"translate(334.5,204.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"120\" height=\"30\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 27px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 122px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">Mengnan Shi</div></div></foreignObject><text x=\"60\" y=\"29\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"27px\" font-family=\"Helvetica\">Mengnan Shi</text></switch></g><g transform=\"translate(390.5,243.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"24\" height=\"12\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 12px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 24px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">2017/09/15 20:24:55</div></div></foreignObject><text x=\"12\" y=\"12\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"12px\" font-family=\"Helvetica\">2017/09/15 20:24:55</text></switch></g><g transform=\"translate(76.5,50.5)\"><switch><foreignObject style=\"overflow:visible;\" pointer-events=\"all\" width=\"226\" height=\"58\" requiredFeatures=\"http://www.w3.org/TR/SVG11/feature#Extensibility\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display: inline-block; font-size: 50px; font-family: Helvetica; color: rgb(0, 0, 0); line-height: 1.2; vertical-align: top; width: 226px; white-space: nowrap; word-wrap: normal; text-align: center;\"><div xmlns=\"http://www.w3.org/1999/xhtml\" style=\"display:inline-block;text-align:inherit;text-decoration:inherit;\">Certificate</div></div></foreignObject><text x=\"113\" y=\"54\" fill=\"#000000\" text-anchor=\"middle\" font-size=\"50px\" font-family=\"Helvetica\">Certificate</text></switch></g></g></svg>\n";
      CreatePdfFile("D:\\123\\WE - 123.svg", "D:\\123.pdf");
   }

   /*
    * Generate a PDF file by svg data content
    *
    * @argSgvContent The address the svg file
    * @argFileAddress The address of the generated pdf file
    *
    */
   public static boolean CreatePdfFile(String argSgvFileAddress, String argFileAddress) {
      try {
         if (argFileAddress.endsWith(".pdf") == false) argFileAddress = argFileAddress + ".pdf";
         FileInputStream input = new FileInputStream(argSgvFileAddress);
         FileOutputStream output = new FileOutputStream(argFileAddress);
         new SVGExport().setInput(input)
               .setOutput(output)
               .setTranscoder(Format.PDF)
               .transcode();
         input.close();
         output.close();
         return true;
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return false;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }
   }

   /*
    * Generate a PDF file by svg data content
    *
    * @argSgvContent The content of the svg file
    * @argFileAddress The address of the generated pdf file
    *
    */
   public static boolean CreatePdfFileByContent(String argSgvContent, String argFileAddress) {
      try {
         if (argFileAddress.endsWith(".pdf") == false) argFileAddress = argFileAddress + ".pdf";
         FileOutputStream output = new FileOutputStream(argFileAddress);
         argSgvContent = argSgvContent.replaceAll("<switch>", "");
         argSgvContent = argSgvContent.replaceAll("</switch>", "");
         new SVGExport().setInputAsString(argSgvContent)
               .setOutput(output)
               .setTranscoder(Format.PDF)
               .transcode();
         output.close();
         return true;
      } catch (FileNotFoundException e) {
         e.printStackTrace();
         return false;
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }

}