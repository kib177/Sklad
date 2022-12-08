package skladRTO.fx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import skladRTO.dao.modelDAO.ProductDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {
    @FXML
    private PieChart PieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProductDAO productDAO = new ProductDAO();
        PieChart.dataProperty().set(productDAO.getStatistic());
        PieChart.setTitle("Статистика позиций");
        PieChart.setClockwise(true);
        PieChart.setLabelLineLength(50);
        PieChart.setLabelsVisible(true);
        PieChart.setStartAngle(180);
    }
}
