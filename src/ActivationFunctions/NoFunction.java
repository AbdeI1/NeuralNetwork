public class NoFunction implements ActivationFunction {
  public double compress(double x) {
    return x;
  }
  public double getDerivative(double x) {
    return 1;
  }
}
