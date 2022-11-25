package com.axonivy.pdf.convert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.spire.pdf.conversion.PdfStandardsConverter;

public class PdfAConverter {

  public static void main(String[] args) {
    try(InputStream is = PdfAConverter.class.getResourceAsStream("belegscheindummy.pdf")) {
      File converted = convert(is);
      System.out.println("converted "+converted);
    } catch (IOException ex) {
      throw new RuntimeException("conversion failed ", ex);
    }
  }

  public static File convert(File pdf) throws IOException {
    try(InputStream is = Files.newInputStream(pdf.toPath(), StandardOpenOption.READ)) {
      return convert(is);
    }
  }

  public static File convert(InputStream pdf) throws IOException {
    Path outputFolder = Files.createTempDirectory("pdf-convert");
    Path pdfOut = outputFolder.resolve("pdfA1b");
    var converter = new PdfStandardsConverter(pdf);
    try (OutputStream os = Files.newOutputStream(pdfOut, StandardOpenOption.CREATE_NEW)) {
      converter.toPdfA1B(os); // pick whatever standard you like here: use another 'toX'
    }
    return pdfOut.toFile();
  }

}
