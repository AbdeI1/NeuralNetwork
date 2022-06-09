public class Driver {
  public static void main(String[] args) {
    Matrix m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    Matrix m2 = new Matrix(new double[][]{{3},{3},{3}});
    Network n = new Network(new int[]{5, 16, 16, 10, 5}, new SigmoidFunction());
    System.out.println(n.getResult(new Matrix(new double[][]{{0},{0},{0},{0},{0}})));
  }
}
