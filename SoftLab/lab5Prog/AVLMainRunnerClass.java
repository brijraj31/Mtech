/*
 * @author Brij Raj Kishore

*/

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;

public class AVLMainRunnerClass {
    static class Code {
        private void solve(InputReader in, OutputWriter out) {
            /*int data = in.readInt();

            while (data != -1) {

                tree.insert(data);

                data = in.readInt();
            }

            tree.printTree();*/

            AVLTree<Integer> tree = new AVLTree<Integer>();
            while (true) {
                out.printLine("Enter One Of The Options :\n1. Insert 2. Delete 3. Display Tree Level Wise 4. Print Inorder Traversal 5. Exit");
                int ch = in.readInt();
                switch (ch) {
                    case 1 : out.printLine("Enter The Node Value ");
                    int n = in.readInt();
                    tree.insert(n);
                    break;

                    case 2 : out.printLine("Enter The Node Value To Be Deleted : ");
                    n = in.readInt();
                    tree.delete(n);
                    break;

                    case 3 : out.printLine("Display Tree Level Wise \n ");
                    tree.printTree();
                    out.printLine();
                    break;

                    case 4 : out.printLine("Display Tree Inorder \n");
                    tree.printInOrderTree();
                    out.printLine();
                    break;

                    case 5 : out.printLine("Exiting : ");
                    System.exit(0);

                    default: out.printLine("Sorry Please Try Again \n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //initialize
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Code solver = new Code();
        solver.solve(in, out);
        out.flush();
        out.close();
    }


    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        private InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        private String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c))
                    res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        private int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private double readDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, readInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, readInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return isWhitespace(c);
        }

        public String next() {
            return readString();
        }


        private static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private char readCharacter() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }

    static class OutputWriter {
        private final PrintWriter writer;

        private OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    outputStream)));
        }

        private OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        private void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
            writer.flush();
        }

        private void printLine(Object... objects) {
            print(objects);
            writer.println();
            writer.flush();
        }

        private void printLine(int i) {
            writer.println(i);
        }


        private void close() {
            writer.close();
        }

        private void flush() {
            writer.flush();
        }


    }

    static class IOUtils {

        private static int[] readIntArray(InputReader in, int size) {
            int[] array = new int[size + 1];
            for (int i = 1; i <= size; i++)
                array[i] = in.readInt();
            return array;
        }

        private static long[] readLongArray(InputReader in, int size) {
            long[] array = new long[size];
            for (int i = 0; i < size; i++)
                array[i] = in.readLong();
            return array;
        }

        private static char[] readCharArray(InputReader in, int size) {
            char[] array = new char[size];
            for (int i = 0; i < size; i++)
                array[i] = in.readCharacter();
            return array;
        }

        private static char[][] readTable(InputReader in, int rowCount,
                                          int columnCount) {
            char[][] table = new char[rowCount][];
            for (int i = 0; i < rowCount; i++)
                table[i] = readCharArray(in, columnCount);
            return table;
        }

    }

    static class ArrayUtils {

        private static void fill(int[][] array, int value) {
            for (int[] row : array)
                Arrays.fill(row, value);
        }

    }

    static class MiscUtils {
        private static final int[] DX4 = {1, 0, -1, 0};
        private static final int[] DY4 = {0, -1, 0, 1};

        private static boolean isValidCell(int row, int column, int rowCount,
                                           int columnCount) {
            return row >= 0 && row < rowCount && column >= 0
                    && column < columnCount;
        }

    }

}
