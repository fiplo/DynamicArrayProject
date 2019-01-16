/*
 * shop.java
 * Copyright (C) 2019 fiplo <fiplo@weebtop>
 *
 * Distributed under terms of the MIT license.
 */
package Projektas.praktinis;

import Projektas.praktinis.preke;
import Projektas.data.DynamicArray;
import java.util.Random;
public class shop
{
    private static Random rng = new Random();

    public static String[] prekes = {
        "ledai", "morkos", "sviezios sultys", "pienas",
        "mesa", "duona", "agurkai", "mandarinai", "ananasai",
        "bandele", "kopustai", "bulves", "paprika", "greifurtas",
        "mesa", "svogunai"
    };


    private static String getRandomName() {
        return (prekes[rng.nextInt(prekes.length)]);
        
    }

    private static preke genRandomItem(){
       return new preke(getRandomName(), ((double)rng.nextInt(15))+rng.nextDouble(), rng.nextInt(4), rng.nextInt(8)+5);
    } 

    private static DynamicArray generateInitialArray(int size){
        DynamicArray<preke> newArray = new DynamicArray<>();
        for(int i = 0; i < size; i++){
            newArray.insertToEnd(genRandomItem());
        }
        return newArray;
    }
    
    public static void main(String[] args){
        DynamicArray<preke> Prekes;
        int crrDay = 1;
        double cash = 50;
        double totalSold = 0;
        int lastStockUp = 0;
        int bankrotoRiba = -50;
        int itemsSold = 0;
        boolean manual = false;
        Prekes = generateInitialArray(30);
        System.out.println("Pradines prekes: ");
        System.out.println(toString(Prekes));
        while(cash > bankrotoRiba)
        {
            Prekes = nextDay(Prekes);
            System.out.println(String.format("%3d dienos prekes: ", crrDay));
            System.out.println(toString(Prekes));
            DynamicArray<preke> soldList = PickRandomItems(Prekes);
            double todaysEarnings = removeSoldItems(Prekes, soldList);
            cash += todaysEarnings;
            totalSold +=todaysEarnings;
            itemsSold += soldList.getSize();
            System.out.println("Dabartinis balansas: " + cash);
            if(crrDay/5 > lastStockUp)
            {
                addNewItems(Prekes, generateInitialArray(25));
                lastStockUp = crrDay/5;
                cash += -100;
            }
            if(manual)
                System.console().readLine();
            crrDay++;
        }
        System.out.println("Jusu verslas isgyveno: " + crrDay);
        System.out.println("Jus pardavete" + roundUp(itemsSold) + " kurie isviso sudaro sia suma " + roundUp(totalSold));
                  

    }

    public static DynamicArray<preke> PickRandomItems(DynamicArray<preke> Prekes){
        DynamicArray<preke> soldList = new DynamicArray<>();
        for(int i = 0; i < 3; i++){
            preke Target = Prekes.getIndex(rng.nextInt(Prekes.getSize()));
            boolean canSell = true;
            for(preke item : soldList)
                if(Target == item)
                    canSell = false;
            if(canSell)
                soldList.insertToEnd(Target);
        }  
        return soldList;
    }

    public static double removeSoldItems(DynamicArray<preke> Prekes, DynamicArray<preke> SoldItemsList){
        double cashIntake = 0;
        System.out.println("Buvo parduotos sios prekes: \n" + toString(SoldItemsList));
        for(preke item: SoldItemsList){
            cashIntake += item.Price;
            Prekes.removeat(Prekes.findObj(item)); 
        }
        return cashIntake;
    }

    public static void addNewItems(DynamicArray<preke> Prekes, DynamicArray<preke> newItems){
        for(preke item: newItems){
            Prekes.insertToEnd(item); 
        }
    }
        
    public static DynamicArray<preke> nextDay(DynamicArray<preke> Prekes){
        for(preke item : Prekes){
            item.dayPasses();
            if(item.daysInStore == item.validFor){
                Prekes.removeat(Prekes.findObj(item));
            }
        } 
        return Prekes;
    }

    public static String toString(DynamicArray<preke> Prekes){
        String output = String.format("%3s __ %90s \n", "num", String.format("%15s|%15s|%15s|%15s|%15s", "Name", "Price", "OriginalPrice", "daysInStore", "validFor")); 
        output += Prekes.toString();
        return output;
    }	

    public static double roundUp(double number)
    {
         return (double) Math.round(number * 100) / 100;
    }
}

