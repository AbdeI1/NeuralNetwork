public class CrossEntropy implements CostFunction {
  public double getCost(double a, double b) {
    return b*Math.log(a) + (1-b)*Math.log(1-a);
  }
  public double getDerivative(double a, double b) {
    return b/a - (1-b)/(1-a);
  }
}
