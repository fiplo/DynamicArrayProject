package Projektas.data;
import java.util.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Paulius Ratkevicius 
 */

public class DynamicArray<T> implements Iterable <T>{
	private Object[]  Array;
    private int aSize;
    
    public DynamicArray() {
       Array = new Object[4];
       aSize = 0;
	}

    public DynamicArray(int Size){
        if(Size <= 0)
            throw new IndexOutOfBoundsException("Size: " + Size);
        else{
        Array = new Object[Size];
        aSize = 0;
        }
    }

    void ensureFit(int size)
    {/*
        int newSize = Array.length;
        while(size > newSize - 1)
        {
            System.out.println("Doubling");
            newSize = newSize* 2;
        }
        if(size < newSize - 1)
        {
            Object[] Arraycache = Array; 
            Array = new Object[newSize];
            for(int i = 0; i < aSize; i++)
            {
                Array[i] = Arraycache[i];
            }
            System.out.println(Array.length);

        }*/
        if (size >= Array.length -1) {
            //System.out.println("in");
            int newSize = Array.length;
            while(newSize <= size){
                newSize *= 2;
            }
            Object[] newArray = new Object[newSize];
            for (int i = 0; i < Array.length; i++){
                newArray[i] = Array[i];
            }
            Array = newArray;
        }
    }

    public boolean isEmpty(){
        return aSize == 0;
    }
   
    private void checkIfAvailable(int index){
        if(index >= aSize && index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", aSize: " + aSize);
    }

    public T getIndex(int index){
        checkIfAvailable(index);
        return (T) Array[index];
    }

    public T ReplaceIndex(Object obj, int index){
        checkIfAvailable(index);
        Object oldValue = Array[index];
        Array[index] = obj;
        return (T) oldValue;
    }
    public void clear(){
        Array = new Object[2];
        aSize = 0;
    }
    public int getCapacity(){
        return Array.length;
    }

    public void insertToEnd(Object obj){
        //System.out.println("before: " + Array.length);
        ensureFit(aSize++);
        //System.out.println("after: " + Array.length);
        Array[aSize-1] = obj;
    }
    public int getSize(){
        return aSize;
    }

    public T removeat(int index)
    {
        T element = getIndex(index); 
        if(index != aSize) 
            shiftToLeft(index);
        aSize--;
        return element;
    }

    private void shiftToLeft(int index)
    {
        for(int i = index; i < aSize; i++){
            if(i == aSize - 1)
                Array[i] = null;
            else
                Array[i] = Array[i+1];
        }
    }

    public int findObj(Object object)
    {
        for(int i = 0; i < aSize; i++){
            if(object == null){
                if(Array[i] == null)
                    return i;
            } else {
                if (object == Array[i])
                    return i;
            }
        }
        return -1;
    }


    public String toString() {
        String output = ""; 
        for (int i = 0; i < aSize; i++)
        {
            output +=String.format("%3d __ %90s \n", i, getIndex(i).toString()); 
        }
        return output;
    }


    @Override
    public java.util.Iterator<T> iterator(){
        return new java.util.Iterator<T>(){
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < aSize;
            }

            @Override
            public T next(){
                return (T) Array[index++];
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

}

