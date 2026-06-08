package org.example.AppResources;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Formatter;
import java.util.Random;

public class Generate_Recu {
    static final DeviceRgb BLUE       = new DeviceRgb(0x18, 0x5F, 0xA5);
    static final DeviceRgb BLUE_LIGHT = new DeviceRgb(0xB5, 0xD4, 0xF4);
    static final DeviceRgb BLUE_BG    = new DeviceRgb(0xE6, 0xF1, 0xFB);
    static final DeviceRgb BLUE_DARK  = new DeviceRgb(0x0C, 0x44, 0x7C);
    static final DeviceRgb GREEN_BG   = new DeviceRgb(0xEA, 0xF3, 0xDE);
    static final DeviceRgb GREEN_TEXT = new DeviceRgb(0x27, 0x50, 0x0A);
    static final DeviceRgb GRAY_BG    = new DeviceRgb(0xF5, 0xF5, 0xF5);
    static final DeviceRgb GRAY_TEXT  = new DeviceRgb(0x88, 0x87, 0x80);
    static final DeviceRgb LINE_COLOR = new DeviceRgb(0xE0, 0xE0, 0xE0);
    static final DeviceRgb TEXT_DARK  = new DeviceRgb(0x2C, 0x2C, 0x2A);

    public boolean generer_recu(int montant , String iban) {
        try {
            int x = new Random().nextInt(10000);
            LocalDate today = LocalDate.now();
            String path = "src/main/java/org/example/Depot_Recu/recu" + x + today + ".pdf";
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf , PageSize.A6);
            document.setMargins(0,0,0,0);
            Table header = new Table(1).useAllAvailableWidth();
            Cell headerCell = new Cell();
            headerCell.setBackgroundColor(BLUE);
            headerCell.setPadding(14);
            headerCell.setBorder(Border.NO_BORDER);
            headerCell.add(new Paragraph("EXPRESS BANK")
                            .setFontSize(45)).setBackgroundColor(BLUE_BG).setPadding(14).setBorder(Border.NO_BORDER)
                    .setFontSize(45);
            headerCell.add(new Paragraph("Recu De Depot")).setFontColor(ColorConstants.WHITE)
                    .setFontSize(12).setMarginBottom(6);
            header.addCell(headerCell);
            document.add(header);
            String mode = "DEPOSIT";
            String[][] lignes = {
                    {"Datum",            LocalDate.now().toString()},
                    {"Iban",             iban},
                    {"Einzahlungsart",   mode},
                    {"Betrag",           montant + " €"}
            };

            Table body = new Table(new float[]{1, 1}).useAllAvailableWidth();

            for (String[] ligne : lignes) {
                Cell gauche = new Cell()
                        .add(new Paragraph(ligne[0])
                                .setFontColor(GRAY_TEXT)
                                .setFontSize(9))
                        .setBorder(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(LINE_COLOR, 0.5f))
                        .setPaddingTop(7).setPaddingBottom(7)
                        .setPaddingLeft(10);

                Cell droite = new Cell()
                        .add(new Paragraph(ligne[1])
                                .setFontColor(TEXT_DARK)
                                .setFontSize(9))
                        .setBorder(Border.NO_BORDER)
                        .setBorderBottom(new SolidBorder(LINE_COLOR, 0.5f))
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setPaddingTop(7).setPaddingBottom(7)
                        .setPaddingRight(10);
                body.addCell(gauche);
                body.addCell(droite);
            }
            document.add(body);
            document.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
