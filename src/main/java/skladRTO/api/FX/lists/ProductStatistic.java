package skladRTO.api.FX.lists;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class ProductStatistic {
    private ObservableList<PieChart.Data> pieCharts = FXCollections.observableArrayList();
    public void create(String status1, String status2, String status3, String status4, int num1, int num2 ,int num3, int num4) {
        pieCharts.add(new PieChart.Data(status1, num1));
        pieCharts.add(new PieChart.Data(status2, num2));
        pieCharts.add(new PieChart.Data(status3, num3));
        pieCharts.add(new PieChart.Data(status4, num4));
    }

    public ObservableList<PieChart.Data> getPieCharts() {
        return pieCharts;
    }

    public void setPieCharts(ObservableList<PieChart.Data> pieCharts) {
        this.pieCharts = pieCharts;
    }
}
