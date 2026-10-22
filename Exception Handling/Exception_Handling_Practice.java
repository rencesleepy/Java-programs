import java.util.InputMismatchException;
import java.util.Scanner;

public class Exception_Handling_Practice {
    public static void display()
    {
        Scanner sc = new Scanner(System.in);
        try 
        {
            System.out.print("Enter the first number: ");
            int num1 = sc.nextInt();
            
            System.out.print("Enter the second number: ");
            int num2 = sc.nextInt();

            int result = num1/num2;
            System.out.println("Result: " + result);
        }
        catch (ArithmeticException e)
        {
            System.out.println("Error! You cannot divide by zero");
        }
        sc.close();
    }
    public static void divisionZero()
    {
        int a = 20;
        int b = 0;
        try
        {
            System.out.println("Result: " + (a / b));
        }
        catch(ArithmeticException e)
        {
            System.out.println("Handle by zero");
        }
        catch (InputMismatchException e)
        {
            System.out.println("ERROR: Cannot divide by zero");
        }
    }
    
    public static void main(String[] args) {
        Exception_Handling_Practice obj = new Exception_Handling_Practice();
        String menu[] = {
            "Arithmetic" , 
            "Division by zero"
        };
        for (int i=0; i<2; i++) {
            System.out.println((i+1) + ". " + menu[i]);
        }
        Scanner scl = new Scanner(System.in);
        System.out.print("Enter choice: ");
        int choice = scl.nextInt();
        switch (choice)
        {
            case 1: 
            {
                obj.display();
                break;
            }
            case 2:
            {
                obj.divisionZero();
                break;
            }
            default:
            {
                System.out.println("Exiting the program");
            }
        }   

    }
}
