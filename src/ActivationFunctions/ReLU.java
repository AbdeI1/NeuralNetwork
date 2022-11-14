public class ReLU implements ActivationFunction {
  public double compress(double x) {
    return x > 0 ? x : 0;
  }
  public double getDerivative(double x) {
    return x > 0 ? 1 : 0;
  }
}
