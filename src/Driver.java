public class Driver {
  public static void main(String[] args) {
    Matrix m1 = new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    Matrix m2 = new Matrix(new double[][]{{3},{3},{3}});
    Network n = new Network(new int[]{5, 16, 16, 10, 5}, new SigmoidFunction());
    n.eta = 0.1;
    Matrix input = new Matrix(new double[][]{{0},{0},{0},{0},{0}});
    Matrix expected = new Matrix(new double[][]{{0.25},{1},{0.5},{1},{0.5}});
    for(int i = 0; i < 100; i++) {
      System.out.println(n.getResult(input));
      n.train(input, expected);
    }
    System.out.println(n.getResult(input));
  }
}
