public class SigmoidFunction implements ActivationFunction {
  public double compress(double x) {
    return 1/(1 + Math.exp(-x));
  }
}
