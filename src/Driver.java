public class Driver {
  public static void main(String[] args) {
    Matrix m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    Matrix m2 = new Matrix(new double[][]{{3},{3},{3}});
    Network n = new Network(new int[]{5, 10, 7, 6, 2});
    System.out.println(n.getResult(new Matrix(new double[][]{{1},{0},{0},{1},{0}})));
  }
}
