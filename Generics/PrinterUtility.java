/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generics;

/**
 *
 * @author lawre
 */
public class PrinterUtility {
        public static <T> void printItem(T item) {
        System.out.println("Here is your item: " + item);
        System.out.println("It is of type: " + item.getClass().getSimpleName());
    }
    public void printString(String a) {
        System.out.println("Here is your item: " + a);
    }
    public void PrintInt(Integer a) {
        System.out.println("Here is your item: " + a);
    }
    public void PrintDouble (Double a) {
        System.out.println("Here is your item: " + a);
    }
}
