import java.util.Arrays;

public class Matrix {
  public int rows;
  public int cols;
  public double[][] mat;
  public Matrix(){
    this(1);
  }
  public Matrix(int n){
    rows = n;
    cols = n;
    mat = new double[n][n];
    for(int i = 0; i < n; i++) mat[i][i] = 1;
  }
  public Matrix(int h, int w) {
    rows = h;
    cols = w;
    mat = new double[rows][cols];
  }
  @Override
  public String toString() {
    StringBuilder res = new StringBuilder(rows + "x" + cols);
    boolean decimal = false;
    check:
    for(int i = 0; i < rows; i++){ // check if all entries are ints
      for(int j = 0; j < cols; j++){
        int t = (int)mat[i][j];
        if(mat[i][j] - t> 1e-8){
          decimal = true;
          break check;
        }
      }
    }
    if(decimal) {
      for (int i = 0; i < rows; i++) {
        res.append("\n[ ");
        for (int j = 0; j < cols; j++) {
          res.append(String.format("%.2f ", mat[i][j]));
        }
        res.append("]");
      }
    } else {
      for (int i = 0; i < rows; i++) {
        res.append("\n[ ");
        for (int j = 0; j < cols; j++) {
          res.append(String.format("%d ", (int)mat[i][j]));
        }
        res.append("]");
      }
    }
    return res.toString();
  }
}
