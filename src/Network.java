import java.util.Arrays;

public class Network {
  private Matrix[] weights;
  private Matrix[] biases;
  public Network(int[] layers){
    weights = new Matrix[layers.length-1];
    biases = new Matrix[layers.length-1];
    for(int i = 1; i < layers.length; i++){
      weights[i-1] = new Matrix(layers[i], layers[i-1]);
      biases[i-1] = new Matrix(layers[i], 1);
      weights[i-1].randomize(0, 1);
      biases[i-1].randomize(0, 1);
    }
  }
  public Matrix goThroughLayer(Matrix input, int layer){
    if(layer < 0 || layer >= weights.length){
      throw new IllegalArgumentException("layer is out of bounds");
    }
    if(input.cols != 1 || input.rows != weights[layer].cols){
      throw new IllegalArgumentException("input is not the right size");
    }
    return weights[layer].multiply(input).add(biases[layer]);
  }
  public Matrix getResult(Matrix input){
    Matrix res = new Matrix(input);
    for(int i = 0; i < weights.length; i++){
      res = goThroughLayer(res, i);
    }
    return res;
  }
  @Override
  public String toString() {
    return "Network{" +
            "weights=" + Arrays.toString(weights) +
            ", biases=" + Arrays.toString(biases) +
            '}';
  }
}
