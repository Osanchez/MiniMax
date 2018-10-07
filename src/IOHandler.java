import java.io.*;

public class IOHandler {
    private static int columns = 5;
    private static int rows = 4;

    String[][] boardState = new String[rows][columns];

    public void readFile(String filePath) {
        try {
            File file = new File(filePath); //Pathname
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            String read = "";
            String[] split;

            //read all lines of file
            while((st = br.readLine()) != null) {
                read += st;
            }

            split = read.replace(".", ".").split("");

            int index = 0;
            for(int x = 0; x < rows; x++) {
                for(int y = 0; y < columns; y++) {
                    boardState[x][y] = split[index];
                    index++;
                }

            }

            br.close();

            //for(int[] aBoard: finalizedBoards) {
            //    System.out.println(Arrays.toString(aBoard));
            //}

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}