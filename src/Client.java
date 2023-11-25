public class Client {
    private int ID;
    private String name;
    private String industry;
    private String contactPerson;
    private double revenue;

    public Client(int ID, String name, String industry, String contactPerson, double revenue) {
        this.ID = ID;
        this.name = name;
        this.industry = industry;
        this.contactPerson = contactPerson;
        this.revenue = revenue;
        }
    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getIndustry(){
        return industry;
    }
    public String[] convertToStringArray(){
        String result = Integer.toString(ID) + "," + name + "," + industry + "," + contactPerson + "," + Double.toString(revenue);
        return result.split(",");
    }

    @Override
    public String toString(){
        return "\nClient{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", Industry='" + industry + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}
