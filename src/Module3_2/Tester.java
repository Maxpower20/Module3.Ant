package Module3_2;


public class Tester {
    public static void main(String[] args) {
        int[] array1 = {2, 2, 3, 3, 4, 4, 5, 5, 9};
        int[] array2 = {9, 2, 9, 3, 9, 4, 9, 5, 9};

        long squareSum1 = new SquareSumImpl().getSquareSum(array1, 3);
        long squareSum2 = new SquareSumImpl().getSquareSum(array2, 4);

        System.out.println(squareSum1);
        System.out.println(squareSum2);
    }
}
