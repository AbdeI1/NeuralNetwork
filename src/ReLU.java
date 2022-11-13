public class ReLU implements ActivationFunction{
  public double compress(double x) {
    return x > 0 ? x : 0;
  }
}
