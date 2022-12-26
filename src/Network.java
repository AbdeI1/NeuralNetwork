import java.util.*;

public class Network {
  private final Matrix[] weights;
  private final Matrix[] biases;
  private final ActivationFunction[] activations;
  private final CostFunction costFunction;
  public double eta;
  public Network(int[] layers){
    this(layers, new NoFunction());
  }
  public Network(int[] layers, ActivationFunction f){
    this(layers, Arrays.stream(new ActivationFunction[layers.length-1]).map(o -> f).toArray(ActivationFunction[]::new));
  }
  public Network(int[] layers, ActivationFunction[] fs){
    weights = new Matrix[layers.length-1];
    biases = new Matrix[layers.length-1];
    activations = new ActivationFunction[layers.length-1];
    for(int i = 1; i < layers.length; i++){
      weights[i-1] = new Matrix(layers[i], layers[i-1]);
      biases[i-1] = new Matrix(layers[i], 1);
      weights[i-1].randomize(0, 0.25);
      biases[i-1].randomize(0, 0.25);
    }
    System.arraycopy(fs, 0, activations, 0, layers.length - 1);
    costFunction = new SquareDifference();
    eta = 0.1;
  }
  public Matrix goThroughLayer(Matrix input, int layer){
    if(layer < 0 || layer >= weights.length){
      throw new IllegalArgumentException("layer is out of bounds");
    }
    if(input.cols != 1 || input.rows != weights[layer].cols){
      throw new IllegalArgumentException("input is not the right size");
    }
    return activations[layer].compressMatrix(weights[layer].multiply(input).add(biases[layer]));
  }
  public Matrix getResult(Matrix input){
    Matrix res = new Matrix(input);
    for(int i = 0; i < weights.length; i++){
      res = goThroughLayer(res, i);
    }
    return res;
  }
  public void train(Matrix input, Matrix expected) {
    Matrix[] outputs = new Matrix[weights.length+1];
    outputs[0] = new Matrix(input);
    for(int i = 1; i <= weights.length; i++) outputs[i] = goThroughLayer(outputs[i-1], i-1);
    Matrix[] dW = new Matrix[weights.length];
    for(int i = 0; i < dW.length; i++) dW[i] = new Matrix(weights[i].rows, weights[i].cols);
    Matrix[] dB = new Matrix[biases.length];
    for(int i = 0; i < dB.length; i++) dB[i] = new Matrix(biases[i].rows, biases[i].cols);
    Matrix[] dA = new Matrix[weights.length+1];
    for(int i = 0; i < dA.length; i++) dA[i] = new Matrix(outputs[i].rows, 1);
    for(int i = 0; i < dA[dA.length-1].rows; i++) dA[dA.length-1].mat[i][0] = costFunction.getDerivative(outputs[dA.length-1].mat[i][0], expected.mat[i][0]);
    for(int l = dA.length-2; l >= 0; l--) {
      for(int k = 0; k < dA[l].rows; k++) {
        dA[l].mat[k][0] = 0;
        for(int j = 0; j < dA[l+1].rows; j++) {
          double D = dA[l+1].mat[j][0]*activations[l].getDerivative(outputs[l+1].mat[j][0]);
          dA[l].mat[k][0] += D*weights[l].mat[j][k];
          dW[l].mat[j][k] = D*outputs[l].mat[k][0];
          dB[l].mat[j][0] = D;
        }
      }
    }
    for(int l = 0; l < weights.length; l++) {
      for(int i = 0; i < weights[l].rows; i++) {
        biases[l].mat[i][0] -= eta*dB[l].mat[i][0];
        for(int j = 0; j < weights[l].cols; j++) {
          weights[l].mat[i][j] -= eta*dW[l].mat[i][j];
        }
      }
    }
  }
  @Override
  public String toString() {
    return "Network{" +
            "weights=" + Arrays.toString(weights) +
            ", biases=" + Arrays.toString(biases) +
            '}';
  }
}
