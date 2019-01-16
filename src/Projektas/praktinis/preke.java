package Projektas.praktinis;

// @author Paulius Ratkevicius

public class preke
{
    private double margins = 0.2;
    public String Name;
    public double Price;
    public double OriginalPrice;
    public int daysInStore;
    public int validFor;
	public preke(String name, double price, int daysinstore, int validfor) {
        this.Name = name;
        this.Price = price;
        this.OriginalPrice = price;
        this.daysInStore = daysinstore;
        this.validFor = validfor;
	}

    public void dayPasses(){
       daysInStore++;
       double diff = OriginalPrice / (double) validFor * (double) daysInStore * margins;
       this.Price = OriginalPrice - diff;
    }

    @Override
    public String toString(){
        return String.format("%15s|%15f|%15f|%15d|%15d", Name, roundUp(Price), roundUp(OriginalPrice), daysInStore, validFor);
    }
    public double roundUp(double number)
    {
         return (double) Math.round(number * 100) / 100;
    }
}

