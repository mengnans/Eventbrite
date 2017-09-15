package com.eventbrite.utility;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Chensey on 2017/9/12.
 */
public class Program {
   
   public static void main(String[] args) {
      try {
//         Transcoder transcoder = new PDFTranscoder();
//         TranscoderInput transcoderInput = new TranscoderInput(new FileInputStream(new File("D:\\svg.svg")));
//         TranscoderOutput transcoderOutput = new TranscoderOutput(new FileOutputStream(new File("D:\\example.pdf")));
//         transcoder.transcode(transcoderInput, transcoderOutput);
         new SVGExport().setInput(new FileInputStream("D:\\if_view-01_186381.svg"))
               .setOutput(new FileOutputStream("D:\\example.pdf"))
               .setTranscoder(Format.PDF)
               .transcode();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
//      } catch (TranscoderException e) {
//         e.printStackTrace();
      }
   }
}
