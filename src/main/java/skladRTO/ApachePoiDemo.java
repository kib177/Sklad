//https://www.javatpoint.com/apache-poi-microsoft-word - ссылка на курс

package skladRTO;

import javafx.collections.ObservableList;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import skladRTO.api.FX.models.ProductFX;
import skladRTO.dao.modelDAO.MachinesDAO;
import skladRTO.dao.modelDAO.UnitsDAO;
import skladRTO.dao.modelDAO.UserDAO;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ApachePoiDemo {
    private Date currentDate = new Date();
    private DateFormat dateFormat;
    XWPFDocument doc = new XWPFDocument();
    UserDAO userDAO = new UserDAO();

    public void CreateWord(ObservableList<ProductFX> list, String nameFile, int idUser) {


        try (FileOutputStream out = new FileOutputStream(new File(selectedDir() + "\\" + nameFile))) {
            // создаем абзац Делком40 вверхнем углу
            XWPFParagraph DELKOM40 = doc.createParagraph();

            DELKOM40.setFirstLineIndent(6000); // отступ первой строки от левого края
            XWPFRun runDelkom = DELKOM40.createRun();// run это что-то типа строки в абзаце
            runDelkom.setFontSize(28);
            runDelkom.setItalic(true);// курсив
            runDelkom.setBold(true);// жирный
            runDelkom.setFontFamily("Times New Roman");// стиль текста
            runDelkom.setText("ДЕЛКОМ 40");
            // зам директора
            XWPFParagraph ZamDir = doc.createParagraph();
            ZamDir.setIndentationLeft(5000);// отступ всего абзаца от левого края
            XWPFRun runZamDir = ZamDir.createRun();
            runZamDir.setFontSize(14);
            runZamDir.setFontFamily("Times New Roman");
            runZamDir.setText("Зам. директора ООО “ДЕЛКОМ 40” " +
                    "Протуро П. И.");
            runZamDir.addBreak();

            // созадние надписи заявка на приобретение
            XWPFParagraph order = doc.createParagraph();
            order.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runOrder = order.createRun();
            runOrder.setFontSize(16);
            runOrder.setBold(true);
            runOrder.setFontFamily("Times New Roman");
            runOrder.setText("Заявка на приобретение");
            runOrder.addBreak();

            // абзац инициатора
            XWPFParagraph init = doc.createParagraph();
            init.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runInit = init.createRun();
            XWPFRun runInitName = init.createRun();
            runInit.setFontSize(14);
            runInitName.setFontSize(14);
            runInit.setBold(true);
            runInit.setFontFamily("Times New Roman");
            runInitName.setFontFamily("Times New Roman");
            runInit.setText("ИНИЦИАТОР");
            runInitName.setText(userDAO.getUser(idUser).getFullName());
            runInit.addBreak();

            //абзац цель приобретения
            XWPFParagraph priob = doc.createParagraph();
            priob.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runpriob = priob.createRun();
            runpriob.setFontSize(14);
            runpriob.setBold(true);
            runpriob.setFontFamily("Times New Roman");
            runpriob.setText("ЦЕЛЬ ПРИОБРЕТЕНИЯ");

            //создание таблицы с целью приобретения
            XWPFTable table = doc.createTable(); // создание таблицы
            CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();
            width.setType(STTblWidth.DXA);
            width.setW(BigInteger.valueOf(10000));// размер таблицы задал
            table.setTableAlignment(TableRowAlign.CENTER);

            XWPFTableRow row = table.getRow(0);
            // заполнение строки
            row.getCell(0).setText("o Ремонт/замена");
            row.addNewTableCell().setText("o Сервис/обслуж.");
            row.addNewTableCell().setText("o Дооборудование");
            row.addNewTableCell().setText("o Благоустройство");
            row.addNewTableCell().setText("o Прочее");

            XWPFParagraph paragraph2 = doc.createParagraph();
            XWPFRun run2 = paragraph2.createRun();

            // Создание таблицы с заказами
            XWPFTable tableProduct = doc.createTable();
            CTTblWidth width2 = tableProduct.getCTTbl().addNewTblPr().addNewTblW();
            width2.setType(STTblWidth.DXA);
            width2.setW(BigInteger.valueOf(10000));// размер таблицы задал
            tableProduct.setTableAlignment(TableRowAlign.CENTER);
            XWPFTableRow rowProduct = tableProduct.getRow(0);// создание строки
            // Первая строка описания постоянная
            rowProduct.getCell(0).setText("№ п/п"); // заполнение строки таблицы начиная с 0
            rowProduct.addNewTableCell().setText("Наименование"); // добавляем новую строку и заполняем
            rowProduct.addNewTableCell().setText("Применяется для машин:");
            rowProduct.addNewTableCell().setText("Ед. изм.");
            rowProduct.addNewTableCell().setText("Кол-во");
            fillTable(list);

            XWPFParagraph Date = doc.createParagraph();
            Date.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun runDate = Date.createRun();
            runDate.addBreak();
            runDate.setFontSize(12);
            runDate.setFontFamily("Times New Roman");
            dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            runDate.setText("Дата составления" + "                                                                      " +
                    "                              "
                    + dateFormat.format(currentDate));
            doc.write(out);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //метод создания таблицы с позициями
    public void fillTable(ObservableList<ProductFX> list) {
        MachinesDAO machinesDAO = new MachinesDAO();
        UnitsDAO unitsDAO = new UnitsDAO();
        int number = 1;

        for (ProductFX product : list) {
            XWPFTable tableProduct = doc.createTable();
            tableProduct.setTableAlignment(TableRowAlign.CENTER);
            XWPFTableRow rowProduct = tableProduct.getRow(0);
            rowProduct.getCell(0).setText(String.valueOf(number++));
            rowProduct.addNewTableCell().setText(product.getName());
            rowProduct.addNewTableCell().setText(product.getMachine());
            rowProduct.addNewTableCell().setText(product.getUnits());
            rowProduct.addNewTableCell().setText(String.valueOf(product.getAmount()));
        }
    }

    // метод вызова диалогового окна для выбора пути сохранения word файла
    public File selectedDir() {
        JFileChooser fileopen = new JFileChooser(); // класс для работы с файлами
        fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ограничение на выбор только директории
        fileopen.setDialogTitle("Выбор директории");// имя окна
        fileopen.setAcceptAllFileFilterUsed(false); // запрет на множественный выбор
        fileopen.showOpenDialog(null); // вызов самого окна
        System.out.println("getCurrentDirectory(): " + fileopen.getSelectedFile()); // для нас виазулизация
        return fileopen.getSelectedFile();
    }
}








