import java.util.*;

public class Network {
  private Matrix[] weights;
  private Matrix[] biases;
  private ActivationFunction[] activations;
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
    for(int i = 1; i < layers.length; i++){
      activations[i-1] = fs[i-1];
    }
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
  public double getCost(Matrix a, Matrix b) {
    if(a.rows != b.rows || b.cols != a.cols) {
      throw new IllegalArgumentException("inputs are not the same size");
    }
    double res = 0;
    for(int i = 0; i < a.rows; i++){
      for(int j = 0; j < b.cols; j++){
        res += (a.mat[i][j] - b.mat[i][j])*(a.mat[i][j] - b.mat[i][j]);
      }
    }
    return res;
  }
  public void train(Matrix input, Matrix expected) {
    Matrix output = getResult(input);
    double cost = getCost(output, expected);
    Matrix[] dW = new Matrix[weights.length];
    for(int i = 0; i < dW.length; i++) dW[i] = new Matrix(weights[i].rows, weights[i].cols);
    Matrix[] dB = new Matrix[biases.length];
    for(int i = 0; i < dB.length; i++) dB[i] = new Matrix(biases[i].rows, biases[i].cols);
  }
  @Override
  public String toString() {
    return "Network{" +
            "weights=" + Arrays.toString(weights) +
            ", biases=" + Arrays.toString(biases) +
            '}';
  }
}
