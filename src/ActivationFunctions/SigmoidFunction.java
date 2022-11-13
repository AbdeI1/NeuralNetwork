public class SigmoidFunction implements ActivationFunction {
  public double compress(double x) {
    return 1/(1 + Math.exp(-x));
  }
  public double getDerivative(double x) {
    return compress(x)*(1 - compress(x));
  }
}
