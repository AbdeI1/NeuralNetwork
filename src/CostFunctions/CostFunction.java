public interface CostFunction {
  double getCost(double a, double b);
  double getDerivative(double a, double b);
  default double getCostMatrix(Matrix a, Matrix b) {
    if(a.rows != b.rows || b.cols != a.cols) {
      throw new IllegalArgumentException("inputs are not the same size");
    }
    double res = 0;
    for(int i = 0; i < a.rows; i++){
      for(int j = 0; j < b.cols; j++){
        res += getCost(a.mat[i][j], b.mat[i][j]);
      }
    }
    return res;
  }
}
