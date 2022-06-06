public interface ActivationFunction {
  public double compress(double x);
  public default Matrix compressMatrix(Matrix m){
    Matrix res = new Matrix(m);
    for(int i = 0; i < m.rows; i++){
      for(int j = 0; j < m.cols; j++){
        res.mat[i][j] = compress(m.mat[i][j]);
      }
    }
    return res;
  }
}
