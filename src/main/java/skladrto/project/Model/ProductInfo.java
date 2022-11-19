package skladrto.project.Model;

public class ProductInfo {
    private int id;
    private String article;
    private String arrivalDate;

    public ProductInfo(int id, String article, String arrivalDate) {
        this.id = id;
        this.article = article;
        this.arrivalDate = arrivalDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
