import java.util.Scanner;
import java .util.Random;

public class TicTacToe {

    // блок настроек игры
    private static char [][] map;
    private static int SIZE = 3;

    private static final char DOT_EMPTY = ' ';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static final boolean SILLY_MODE = false;

    public static void main(String[] args){
        initMap();
        printMap();

        while(true){
            humanTry();
            if(isEndGame(DOT_X)){
                break;
            }

            computerTry();
            if(isEndGame(DOT_O)){
                break;
            }
        }

        System.out.println("Игра окончена");
    }

    private static void initMap(){
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
    }
        private static void printMap(){
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < SIZE; i++){
            System.out.print((i + 1) + " ");
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void humanTry(){
        int x, y;

        do{
            System.out.println("Введите координаты ячейки через пробел");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        }while(!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    public static String computerTry() {
        int x = -1;
        int y = -1;

        if(SILLY_MODE){
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }while (!isCellValid(x, y));
            System.out.println("Компьютер выбрал ячейку: " + (y + 1) + " " + (x + 1));
            map[y][x] = DOT_O;

        } else {
            int signV;
            int signH;
            int xW = 0;
            int yW = 0;
            int xWh = 0;
            int yWh = 0;

            for (int i = 0; i < SIZE; i++) {
                signV = 0;
                signH = 0;
                for(int j = 0; i < SIZE; i++){
                    if(map[i][j] == x){
                        signV++;

                    }
                    if((map[i][j] != x) && (map[i][j] != ' ')){
                        signV--;

                    }
                    if(map[i][j] == ' '){
                        y = j;
                        x = i;
                    }
                    if(map[j][i] == x) {
                        signH++;

                    }
                    if((map[j][i] != x) && (map[j][i] != ' ')){
                        signH--;

                    }
                    if(map[j][i] == ' '){
                        x = i;
                        y = j;
                    }

                }
               if (signV == SIZE - 1) return xW + " " + yW;
               if (signH == SIZE - 1) return xWh + " " + yWh;

            }
            
             signV = 0;
             signH = 0;
             xW = 0;
             yW = 0;
             xWh = 0;
             yWh = 0;

            for (int i = 0; i < SIZE; i++) {
                if(map[i][i] == x){
                    signV++;
                }
                if((map[i][i] != x) && (map[i][i] != ' ')){
                    signV--;
                }
                if(map[i][i] == ' '){
                    x = i;
                    y = i;
                }
                if(map[SIZE - 1 - i][i] == x) {
                    signH++;
                }
                if((map[SIZE - 1 - i][i] != x) && (map[SIZE - 1 - i][i] != ' ')){
                    signH--;
                }
                if(map[SIZE - 1 - i][i] == ' '){
                    x = i;
                    y = SIZE - 1 - i;
                }
              if (signV == SIZE - 1) return x + " " + y;
              if (signH == SIZE - 1) return x + " " + y;


            }
            System.out.println("Компьютер выбрал ячейку: " + (y + 1) + " " + (x + 1));
            map[y][x] = DOT_O;

        }


        return null;
    }

    private static boolean isCellValid(int x, int y){
        boolean result = true;

        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE){
            result = false;
        }

        if (map[y][x] != DOT_EMPTY){
            result = false;
        }


        return result;
    }

    private static boolean isEndGame(char playerSymbol){
        boolean result = false;

        printMap();

        if(checkWin(playerSymbol)){
            System.out.println("Победил " + playerSymbol);
            result = true;
        }

        if(isMapNull()){
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }

    private static boolean isMapNull(){
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) result = false;
            }

        }
        return result;
    }

    private static boolean checkWin(char playerSymbol){
        boolean result = false;

                if (
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {
                        result = true;
                }
        return result;
    }

}


