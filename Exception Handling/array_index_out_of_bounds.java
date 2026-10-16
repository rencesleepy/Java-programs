public class array_index_out_bounds {
    public static void main(String[] args) {

        // ArrayIndexOutOfBoundsException is thrown to indicate that an array has been accessed with an illegal index. 
        // The index is either negative or greater than or equal to the size of the array.

        try {
            char[] myName = { 'E', 'L', 'I', 'Z', 'E', 'R' };

            System.out.println(myName[7]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index is outside the array");
        }
    }
}
