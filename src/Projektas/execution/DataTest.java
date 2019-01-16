/*
 * DataTest.java
 * Copyright (C) 2019 fiplo <fiplo@weebtop>
 *
 * Distributed under terms of the MIT license.
 */

package Projektas.execution;

import Projektas.DataTest.*;
import Projektas.data.*; 
import java.util.Random;


public class DataTest
{
    public static void main(String[] args){
        System.out.println("Results: ");
        DebuggingData();
    }

    public static String[] elements = {
        "Pirmas", "Antras", "Trecias",
        "Ketvirtas", "Penktas", "Sestas",
        "Septintas", "Astuntas", "Devintas"
    };

    private static Random rng = new Random();
    private static String getRandom(){
        return (elements[rng.nextInt(elements.length)]);
    }

    public static DynamicArray<String> generateArray() {
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        for(int i = 0; i < elements.length; i++)
        {
            dynamicArray.insertToEnd(getRandom());
        }
        return dynamicArray;
    }


    public static void DebuggingData() {
        DynamicArray<String> array = generateArray();
        System.out.println("Testing insertToEnd() and array generation: ");
        System.out.println(array.toString());
        String randomString = getRandom();
        System.out.println(String.format("Checking Contains(%s)", randomString));
        if(array.findObj(randomString) != -1){
            System.out.println(String.format("Array contains element %s ond index %d", randomString, array.findObj(randomString) ));
        } else {
            System.out.println(String.format("Array doesn't contain element %s", randomString));
        }

        System.out.println("Checking removeAt()");
        String removableElement = array.removeat(3); 
        System.out.println(String.format("Removable element = %s", removableElement));
        System.out.println("Array after removeAt(): ");
        System.out.println(array.toString());
        System.out.println("Testing getElement()");
        String element = array.getIndex(3);
        System.out.println(String.format("Received element with index %s which is %s", array.findObj(element), element));
        System.out.println("Testing set(): ");
        int index = rng.nextInt(array.getSize());
        array.ReplaceIndex("Naujas vardas", index);
        System.out.println(String.format("Indeksas %d buvo pakeistas pavadinimu Naujas vardas", index));
        System.out.println(array.toString());
       
        System.out.println("Greitaveika: ");
        Greitaveika.pradetiTyrima();        
        
    }
}

