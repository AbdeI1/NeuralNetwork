import java.util.Arrays;
import java.util.Objects;

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
  public Matrix(double[][] a){
    rows = a.length;
    cols = a[0].length;
    mat = new double[rows][cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        mat[i][j] = a[i][j];
      }
    }
  }
  public Matrix(Matrix m){
    rows = m.rows;
    cols = m.cols;
    mat = new double[m.rows][m.cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        mat[i][j] = m.mat[i][j];
      }
    }
  }
  public Matrix add(Matrix m){
    if(m.rows != rows || m.cols != cols){
      throw new ArithmeticException("Matrices for addition are not he same size");
    }
    Matrix res = new Matrix(rows, cols);
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        res.mat[i][j] = mat[i][j] + m.mat[i][j];
      }
    }
    return res;
  }
  public Matrix subtract(Matrix m){
    return this.add(m.multiply(-1));
  }
  public Matrix multiply(double s){
    Matrix res = new Matrix(rows, cols);
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        res.mat[i][j] = s*mat[i][j];
      }
    }
    return res;
  }
  public Matrix multiply(Matrix m){
    if(cols != m.rows){
      throw new ArithmeticException("Matrices for multiplication are not the same size");
    }
    Matrix res = new Matrix(rows, m.cols);
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < m.cols; j++){
        for(int k = 0; k < cols; k++){
          res.mat[i][j] += mat[i][k]*m.mat[k][j];
        }
      }
    }
    return res;
  }
  public void randomize(double a, double b){
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        mat[i][j] = a + Math.random()*(b - a);
      }
    }
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Matrix matrix = (Matrix) o;
    return rows == matrix.rows && cols == matrix.cols && Arrays.equals(mat, matrix.mat);
  }
  @Override
  public int hashCode() {
    int result = Objects.hash(rows, cols);
    result = 31 * result + Arrays.hashCode(mat);
    return result;
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
  public Matrix copy() {
    Matrix res = new Matrix(rows, cols);
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        res.mat[i][j] = mat[i][j];
      }
    }
    return res;
  }
}
