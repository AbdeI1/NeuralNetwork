public interface ActivationFunction {
  double compress(double x);
  double getDerivative(double x);
  default Matrix compressMatrix(Matrix m){
    Matrix res = new Matrix(m);
    for(int i = 0; i < m.rows; i++){
      for(int j = 0; j < m.cols; j++){
        res.mat[i][j] = compress(m.mat[i][j]);
      }
    }
    return res;
  }
}
