package Projektas.DataTest;

import Projektas.data.DynamicArray;
import Projektas.data.Timekeeper;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.lang.InterruptedException;

public class Greitaveika
{
    private static final BlockingQueue resultsLogger = new SynchronousQueue();

    private static final Semaphore semaphore = new Semaphore(-1);

    private static final String[] TYRIMU_VARDAI = {"dynamicArrayAdd", "arrayListadd", "dynamicArrayRemove", "arrayListRemove"};
    private static final int[] TIRIAMI_KIEKIAI = {1000, 2000, 4000, 8000};

    private static final Timekeeper tk = new Timekeeper(TIRIAMI_KIEKIAI, resultsLogger, semaphore);
    private static Random random = new Random();

    public static String[] generateRandomStrings(int number, int wordSize){
        String[] stringBuffer = new String[number];
        for (int i = 0; i < number; i++)
        {
            char[] str = new char[wordSize];
            for(int j = 0; j < wordSize; j++){
                str[j] = (char) (random.nextInt(95) + 32);
            }
            stringBuffer[i] = new String(str);
        }
        return stringBuffer;
    }

    public static void pradetiTyrima(){
        try{
            SisteminisTyrimas();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void SisteminisTyrimas() throws InterruptedException {
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        ArrayList<String> arrayList = new ArrayList<>();
        long t1;
        long t2;


        for(int k : TIRIAMI_KIEKIAI){
            String[] stringArray = generateRandomStrings(k, 6);

            tk.startAfterPause();
            tk.start();

            t1 = System.nanoTime();
            for(int i = 0; i < k; i++){
                dynamicArray.insertToEnd(stringArray[i]);
            }
            t2 = System.nanoTime();
            System.out.println((t2 - t1) / 1000 + " microsseconds to add " + k + " elements to DynamicArray.");
            tk.finish(TYRIMU_VARDAI[0]);
            
            t1 = System.nanoTime();
            for (int i = 0; i < k; i++){
                arrayList.add(stringArray[i]);
            }
            t2 = System.nanoTime();
            System.out.println((t2 - t1) / 1000 + " microseconds to add " + k + " elements to ArrayList.");
            tk.finish(TYRIMU_VARDAI[1]);

            t1 = System.nanoTime();
            for (int i = 0; i < k; i++){
                dynamicArray.removeat(dynamicArray.findObj(stringArray[i]));
            }
            t2 = System.nanoTime();
            System.out.println((t2 - t1) / 1000 + " microseconds to remove an element from " + k + " elements DynamicArray.");
            tk.finish(TYRIMU_VARDAI[2]);

            t1 = System.nanoTime();
            for (int i = 0; i < k; i++){
                arrayList.remove(stringArray[i]);
            }
            t2 = System.nanoTime();
            System.out.println((t2 - t1) / 1000 + " microseconds to remove an element from " + k + " elements ArrayList.");
            tk.finish(TYRIMU_VARDAI[3]);



        }
    }
}

