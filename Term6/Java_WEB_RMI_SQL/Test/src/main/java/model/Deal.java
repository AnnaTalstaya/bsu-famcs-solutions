package model;

public class Deal {
    private int idDeal;
    private String typeDeal;
    private String title;
    private String dateDeal;
    private int sumDeal;

    public Deal(int idDeal, String typeDeal, String title, String dateDeal, int sumDeal) {
        this.idDeal = idDeal;
        this.typeDeal = typeDeal;
        this.title = title;
        this.dateDeal = dateDeal;
        this.sumDeal = sumDeal;
    }

    public int getIdDeal() {
        return idDeal;
    }

    public void setIdDeal(int idDeal) {
        this.idDeal = idDeal;
    }

    public String getTypeDeal() {
        return typeDeal;
    }

    public void setTypeDeal(String typeDeal) {
        this.typeDeal = typeDeal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateDeal() {
        return dateDeal;
    }

    public void setDateDeal(String dateDeal) {
        this.dateDeal = dateDeal;
    }

    public int getSumDeal() {
        return sumDeal;
    }

    public void setSumDeal(int sumDeal) {
        this.sumDeal = sumDeal;
    }
}
