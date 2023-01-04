public class SquareDifference implements  CostFunction {
  public double getCost(double a, double b) {
    return (a-b)*(a-b);
  }
  public double getDerivative(double a, double b) {
    return 2*a - 2*b;
  }
}
