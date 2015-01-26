package Core.Workers;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Andrew
 */
public class PdfCreator {

    public PdfCreator() {
    }

    public void pdfCreateFromString(String inputString, String filename) throws IOException, COSVisitorException {
        String stringToPrint = inputString;
        ArrayList<String> stringsSplitUpToPrint = new ArrayList<>();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDFont font = PDType1Font.TIMES_ROMAN;
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(font, 12);
        stringsSplitUpToPrint = splitUpString(stringToPrint);
        for (String s : stringsSplitUpToPrint) {
            contentStream.moveTextPositionByAmount(100, 700);
            contentStream.drawString(s);
        }

        contentStream.endText();
        contentStream.close();
        document.save(filename);
        document.close();
    }
    public void printArrayListOfStrings(ArrayList<String> stringsToPrint, String filename) throws IOException, COSVisitorException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDFont font = PDType1Font.TIMES_ROMAN;
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(font, 12);
        int posx = 100, posy = 700;
        for (String s : stringsToPrint) {
            contentStream.moveTextPositionByAmount(posx, posy);
            contentStream.drawString(s);
            //posx = posx + 20;
            posy = posy + 20;
        }
        contentStream.endText();
        contentStream.close();
        document.save(filename);
        document.close();
    }

    private ArrayList<String> splitUpString(String input) {
        ArrayList<String> completedStrings = new ArrayList<>();
        char[] stringAsCharArray = input.toCharArray();
        if (input.contains("\n")) {
            completedStrings.add("Student detail report");
            String currentString = "";
            for (int i = 0; i < stringAsCharArray.length; i++) {
                if (stringAsCharArray[i] != '\n') {
                    currentString.concat("" + stringAsCharArray[i]);
                } else if (stringAsCharArray[i] == '\n' || stringAsCharArray[i] == '\0') {
                    completedStrings.add(currentString);
                    currentString = "";
                }

            }
        }
        return completedStrings;
    }
}
