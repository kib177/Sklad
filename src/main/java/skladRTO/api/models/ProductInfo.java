package skladRTO.api.models;

public class ProductInfo {
    private int id;
    private String article;
    private String arrivalDate;
    private String description;
    public ProductInfo(){}

    public ProductInfo(int id, String article, String arrivalDate, String description) {
        this.id = id;
        this.article = article;
        this.arrivalDate = arrivalDate;
        this.description = description;
    }

    public ProductInfo(String article, String arrivalDate, String description) {
        this.article = article;
        this.arrivalDate = arrivalDate;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
