import java.io.*;

public class Driver {
  public static void main(String[] args) throws IOException {

    var s = new DataInputStream(new BufferedInputStream(new FileInputStream("data/train-images.idx3-ubyte")));
    s.readInt();
    int n = s.readInt();
    int R = s.readInt();
    int C = s.readInt();
    Matrix[] trainingData = new Matrix[n];
    for(int i = 0; i < n; i++) {
      trainingData[i] = new Matrix(R, C);
      for(int r = 0; r < R; r++) for(int c = 0; c < C; c++) trainingData[i].mat[r][c] = s.readUnsignedByte();
    }
    s.close();

    s = new DataInputStream(new BufferedInputStream(new FileInputStream("data/train-labels.idx1-ubyte")));
    s.readInt(); s.readInt();
    int[] trainingLabels = new int[n];
    for(int i = 0; i < n; i++) trainingLabels[i] = s.readUnsignedByte();
    s.close();

    s = new DataInputStream(new BufferedInputStream(new FileInputStream("data/t10k-images.idx3-ubyte")));
    s.readInt();
    n = s.readInt();
    s.readInt(); s.readInt();
    Matrix[] testData = new Matrix[n];
    for(int i = 0; i < n; i++) {
      testData[i] = new Matrix(R, C);
      for(int r = 0; r < R; r++) for(int c = 0; c < C; c++) testData[i].mat[r][c] = s.readUnsignedByte();
    }
    s.close();

    s = new DataInputStream(new BufferedInputStream(new FileInputStream("data/t10k-labels.idx1-ubyte")));
    s.readInt(); s.readInt();
    int[] testLabels = new int[n];
    for(int i = 0; i < n; i++) testLabels[i] = s.readUnsignedByte();
    s.close();

    Network N = new Network(new int[]{R*C, 100, 10}, new SigmoidFunction());
    for(int e = 0; e < 1; e++) for(int i = 0; i < trainingData.length; i++) N.train(transformImage(trainingData[i]), tranformLabel(trainingLabels[i]));
    int correct = 0; int tested = 0;
    for(int i = 0; i < 100; i++) {
      Matrix r = N.getResult(transformImage(testData[i]));
      int l = 0; double m = r.mat[0][0];
      for(int j = 1; j < 10; j++) if(r.mat[j][0] > m) {
        m = r.mat[j][0];
        l = j;
      }
      System.out.printf("expected: %d,  predicted: %d\n", testLabels[i], l);
      if(l == testLabels[i]) correct++;
      tested++;
    }
    double acc = ((double)correct/tested)*100;
    System.out.printf("accuracy: %.2f%%\n", acc);
  }

  static Matrix transformImage(Matrix m) {
    Matrix r = new Matrix(m.rows*m.cols, 1);
    for(int i = 0; i < m.rows; i++) for(int j = 0; j < m.cols; j++) r.mat[i*m.cols + j][0] = m.mat[i][j]/255;
    return r;
  }

  static Matrix tranformLabel(int l) {
    Matrix r = new Matrix(10, 1);
    r.mat[l][0] = 1;
    return r;
  }
}
