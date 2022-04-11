package userService.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

@Slf4j
public class PDF_Text_CSV {

    public PDF_Text_CSV(String pdfName){
        File inputFile = new File("C:\\Users\\Calvin\\Downloads\\" + pdfName + ".pdf");
        if(inputFile.exists()){
            System.out.println("Input File:"+inputFile.getAbsolutePath());

            File txtFile = convertPDFtoText(inputFile);

            System.out.println("Text... File:"+txtFile.getAbsolutePath());

            File csvFile = convertTextToCSV(txtFile);

            System.out.println("CSV... File:"+csvFile.getAbsolutePath());

        }else{
            System.out.println("File does not exist:"+inputFile.getAbsolutePath());
        }
    }

    public File convertPDFtoText(File inputFile) {
        try {
            String newFileName = replaceSuffix(inputFile.getName(), ".txt");
            String newPath = inputFile.getAbsoluteFile().getParent()+File.separator+newFileName;
            File outFile = new File(newPath);
            PDDocument document = PDDocument.load(inputFile);
            PDFTextStripper stripper = new PDFTextStripper();

            FileWriter txtFile = new FileWriter(outFile);
            String inputText = stripper.getText(document);
            txtFile.write(inputText);
            txtFile.close();
            document.close();
            return outFile;
        } catch (IOException e) {
            throw new FailedException(e);
        }
    }
    public File convertTextToCSV(File inputFile) {
        try {
            String newFileName = replaceSuffix(inputFile.getName(), ".csv");
            String newPath = inputFile.getAbsoluteFile().getParent()+File.separator+newFileName;
            Scanner scanner = new Scanner(inputFile);
            File outFile = new File(newPath);
            PrintWriter writer = new PrintWriter(outFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //String result = convertLineToCSV(line);
                if (line != null)
                    writer.println(line);
            }
            writer.close();
            return outFile;
        } catch (FileNotFoundException e) {
            throw new FailedException(e);
        } catch (IOException e) {
            throw new FailedException(e);
        }
    }
    private class FailedException extends RuntimeException {
        private static final long serialVersionUID = 2L;

        public FailedException(Exception e) {
            super(e);
        }
    }
//    private String convertLineToCSV(String line) {
//        String[] fields = line.split("\\s+");
//
//        if (fields.length < 10)
//            return null;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 10; i >= 1; i--) {
//            if (i < 10)
//                builder.append("|");
//            builder.append(fields[fields.length - i].replace(",", ""));
//        }
//        String result = builder.toString();
//        if (isQuoteLine(result))
//            return result;
//        return null;
//    }
//
//    private static boolean isQuoteLine(String line) {
//        String pattern = "[A-Z0-9]+" + "\\|(\\-)?(\\d+(\\.\\d+)?)?"
//                + "\\|(\\-)?(\\d+(\\.\\d+)?)?" + "\\|(\\-)?(\\d+(\\.\\d+)?)?"
//                + "\\|(\\-)?(\\d+(\\.\\d+)?)?" + "\\|(\\-)?(\\d+(\\.\\d+)?)?"
//                + "\\|(\\-)?(\\d+(\\.\\d+)?)?" + "\\|(\\-)?(\\d+(\\.\\d+)?)?"
//                + "\\|(\\-)?(\\d+(\\.\\d+)?)?" + "\\|(\\-)?(\\(?\\d+(\\.\\d+)?\\)?)?";
//        return Pattern.matches(pattern, line);
//    }

    private static String replaceSuffix(String fileName, String suffix) {
        int index = fileName.indexOf('.', 0);
        if (index != -1) {
            int lastIndex = index;
            while (index != -1) {
                index = fileName.indexOf('.', lastIndex + 1);
                if (index != -1)
                    lastIndex = index;
            }
            return fileName.substring(0, lastIndex) + suffix;
        } else {
            return fileName + "suffix";
        }
    }
}
