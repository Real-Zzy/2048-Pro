import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Scanner;

public class SlidingTiles extends Application {

    //my fields
    private int numRows=0;
    private int numColumns=0;
    private GridPane gridPane = new GridPane();
    private Button[][] buttons;
    private int[][] database;




    //getter and setter for field database
    public int[][] getDatabase() {
        return database;
    }

    public void setDatabase(int[][] database) {
        this.database = database;
    }

    //getter and setter for field numRows
    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    //getter and setter for field numColumns
    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getNumColumns() {
        return numColumns;
    }

    //getter and setter for field buttons
    public Button[][] getButtons() {
        return buttons;
    }

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    //getter and setter for field gridPane
    public GridPane getGridPane() {
        return gridPane;
    }

    /**
     *this method make a grid pane to be the game board
     */
    public void makeGridPane() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                getButtons()[i][j] = new Button();
                getButtons()[i][j].setPrefSize(100, 100);
                getGridPane().add(getButtons()[i][j], j, i);
            }
        }


        getGridPane().setHgap(10);
        getGridPane().setVgap(8);
    }


    /**
     * this method initialize the field database which stores all the data on the game board
     */
    public void initializeDatabase() {
        int counter = 0;
        Random random = new Random();
        int randomNumber = random.nextInt(getNumRows() * getNumColumns() - 1);
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (randomNumber == counter) {
                    database1[i][j] = 1;
                } else {
                    database1[i][j] = 0;
                }
                counter += 1;
            }
        }
        setDatabase(database1);
    }


    /**
     * this method print all the data on database on the buttons
     */
    public void printOnButtons() {
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (getDatabase()[i][j] != 0) {
                    getButtons()[i][j].setText("" + getDatabase()[i][j]);
                    switch (getDatabase()[i][j]) {
                        case 1:
                            getButtons()[i][j].setStyle("-fx-background-color: #4bf605;-fx-font-size: 40");
                            break;
                        case 2:
                            getButtons()[i][j].setStyle("-fx-background-color: #68a035;-fx-font-size: 40");
                            break;
                        case 4:
                            getButtons()[i][j].setStyle("-fx-background-color: #376c0c;-fx-font-size: 40");
                            break;
                        case 8:
                            getButtons()[i][j].setStyle("-fx-background-color: #085315;-fx-font-size: 40");
                            break;
                        case 16:
                            getButtons()[i][j].setStyle("-fx-background-color: #1ed4ab;-fx-font-size: 35");
                            break;
                        case 32:
                            getButtons()[i][j].setStyle("-fx-background-color: #0d6fc7;-fx-font-size: 35");
                            break;
                        case 64:
                            getButtons()[i][j].setStyle("-fx-background-color: #091986;-fx-font-size: 35");
                            break;
                        case 128:
                            getButtons()[i][j].setStyle("-fx-background-color: #eeff0d;-fx-font-size: 25");
                            break;
                        case 256:
                            getButtons()[i][j].setStyle("-fx-background-color: #b4b13d;-fx-font-size: 25");
                            break;
                        case 512:
                            getButtons()[i][j].setStyle("-fx-background-color: #b48715;-fx-font-size: 25");
                            break;
                        case 1024:
                            getButtons()[i][j].setStyle("-fx-background-color: #f60000;-fx-font-size: 20");
                            break;
                        case 2048:
                            getButtons()[i][j].setStyle("-fx-background-color: #a54242;-fx-font-size: 20");
                            break;
                        case 4096:
                            getButtons()[i][j].setStyle("-fx-background-color: #8e1616;-fx-font-size: 20");
                            break;
                        case 8192:
                            getButtons()[i][j].setStyle("-fx-background-color: #5a0909;-fx-font-size: 20");
                            break;
                        default:
                            getButtons()[i][j].setStyle("-fx-background-color: #b01bbf;-fx-font-size: 15");
                            break;
                    }
                } else {
                    getButtons()[i][j].setText("");
                    getButtons()[i][j].setStyle("-fx-background-color: #9dc977");
                }
            }
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the left
     */
    public void moveLeft() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        int columnCounter = 0;
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = 0;
            }
        }
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                if (getDatabase()[j][k] != 0) {
                    boolean flag = true;
                    columnCounter = 0;
                    //columnCounter >= getNumColumns() ||
                    while (flag) {
                        if (database1[j][columnCounter] == 0) {
                            database1[j][columnCounter] = getDatabase()[j][k];
                            flag = false;
                        }
                        while (columnCounter > 0 && database1[j][columnCounter] == database1[j][columnCounter - 1]) {
                            database1[j][columnCounter - 1] *= 2;
                            database1[j][columnCounter] = 0;
                        }
                        columnCounter += 1;
                    }

                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }

    }

    /**
     * this method move all the numbers, according to the rule, to the right
     */
    public void moveRight() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        int columnCounter = getNumColumns() - 1;
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = 0;
            }
        }
        for (int j = getNumRows() - 1; j >= 0; j--) {
            for (int k = getNumColumns() - 1; k >= 0; k--) {
                if (getDatabase()[j][k] != 0) {
                    boolean flag = true;
                    columnCounter = getNumColumns() - 1;
                    //columnCounter < 0 ||
                    while (flag) {
                        if (database1[j][columnCounter] == 0) {
                            database1[j][columnCounter] = getDatabase()[j][k];
                            flag = false;
                        }
                        while (columnCounter < getNumColumns() - 1 && database1[j][columnCounter] == database1[j][columnCounter + 1]) {
                            database1[j][columnCounter + 1] *= 2;
                            database1[j][columnCounter] = 0;
                        }
                        columnCounter -= 1;
                    }

                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the top
     */
    public void moveUp() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        int rowCounter = 0;
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = 0;
            }
        }
        for (int j = 0; j < getNumColumns(); j++) {
            for (int k = 0; k < getNumRows(); k++) {
                if (getDatabase()[k][j] != 0) {
                    boolean flag = true;
                    rowCounter = 0;
                    //rowCounter >= getNumRows() ||
                    while (flag) {
                        if (database1[rowCounter][j] == 0) {
                            database1[rowCounter][j] = getDatabase()[k][j];
                            flag = false;
                        }
                        while (rowCounter > 0 && database1[rowCounter][j] == database1[rowCounter - 1][j]) {
                            database1[rowCounter - 1][j] *= 2;
                            database1[rowCounter][j] = 0;
                        }
                        rowCounter += 1;
                    }

                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the bottom
     */
    public void moveDown() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        int rowCounter = getNumColumns() - 1;
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = 0;
            }
        }
        for (int j = getNumColumns() - 1; j >= 0; j--) {
            for (int k = getNumRows() - 1; k >= 0; k--) {
                if (getDatabase()[k][j] != 0) {
                    boolean flag = true;
                    rowCounter = getNumRows() - 1;
                    //rowCounter < 0 ||
                    while (flag) {
                        if (database1[rowCounter][j] == 0) {
                            database1[rowCounter][j] = getDatabase()[k][j];
                            flag = false;
                        }
                        while (rowCounter < getNumRows() - 1 && database1[rowCounter][j] == database1[rowCounter + 1][j]) {
                            database1[rowCounter + 1][j] *= 2;
                            database1[rowCounter][j] = 0;
                        }
                        rowCounter -= 1;
                    }

                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the left top
     */
    public void moveLeftUp() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];

        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = getDatabase()[j][k];
            }
        }

        int rowCounter = 0;
        int columnCounter = 0;
        while (Math.max(rowCounter, columnCounter) < Math.max(getNumRows(), getNumColumns())) {
            for (int row = rowCounter; row < getNumRows(); row++) {
                int r = row;
                int c = columnCounter;
                if (columnCounter != 0) {
                    while (r != 0 && c != 0) {
                        if (database1[r][c] != 0) {
                            if (database1[r - 1][c - 1] == 0) {
                                database1[r - 1][c - 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r - 1][c - 1]) {
                                database1[r][c] = 0;
                                database1[r - 1][c - 1] *= 2;
                            }
                        }
                        r -= 1;
                        c -= 1;
                    }
                }
            }


            for (int column = columnCounter; column < getNumColumns(); column++) {
                int r = rowCounter;
                int c = column;
                if (rowCounter != 0) {
                    while (r != 0 && c != 0) {
                        if (database1[r][c] != 0) {
                            if (database1[r - 1][c - 1] == 0) {
                                database1[r - 1][c - 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r - 1][c - 1]) {
                                database1[r][c] = 0;
                                database1[r - 1][c - 1] *= 2;
                            }
                        }
                        r -= 1;
                        c -= 1;
                    }


                }
            }

            if (getNumColumns() == getNumRows()) {
                rowCounter += 1;
                columnCounter += 1;
            } else {
                if (rowCounter != columnCounter) {
                    if (columnCounter > rowCounter)
                        columnCounter += 1;
                    else
                        rowCounter += 1;
                } else {
                    if (rowCounter < getNumRows() - 1)
                        rowCounter += 1;
                    else
                        rowCounter = getNumRows() - 1;

                    if (columnCounter < getNumColumns() - 1)
                        columnCounter += 1;
                    else
                        columnCounter = getNumColumns() - 1;
                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the right top
     */
    public void moveRightUp() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = getDatabase()[j][k];
            }
        }
        int rowCounter = 0;
        int columnCounter = getNumColumns() - 1;
        while (Math.max(rowCounter, getNumColumns() - 1 - columnCounter) < Math.max(getNumRows(), getNumColumns())) {
            for (int row = rowCounter; row < getNumRows(); row++) {
                int r = row;
                int c = columnCounter;
                if (columnCounter != getNumColumns() - 1) {
                    while (r != 0 && c != getNumColumns() - 1) {
                        if (database1[r][c] != 0) {
                            if (database1[r - 1][c + 1] == 0) {
                                database1[r - 1][c + 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r - 1][c + 1]) {
                                database1[r][c] = 0;
                                database1[r - 1][c + 1] *= 2;
                            }
                        }
                        r -= 1;
                        c += 1;
                    }
                }
            }


            for (int column = columnCounter; column > -1; column--) {
                int r = rowCounter;
                int c = column;
                if (rowCounter != 0) {
                    while (r != 0 && c != getNumColumns() - 1) {
                        if (database1[r][c] != 0) {
                            if (database1[r - 1][c + 1] == 0) {
                                database1[r - 1][c + 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r - 1][c + 1]) {
                                database1[r][c] = 0;
                                database1[r - 1][c + 1] *= 2;
                            }
                        }
                        r -= 1;
                        c += 1;
                    }
                }
            }

            if (getNumColumns() == getNumRows()) {
                rowCounter += 1;
                columnCounter -= 1;
            } else {
                if (rowCounter != getNumColumns() - 1 - columnCounter) {
                    if (getNumColumns() - 1 - columnCounter > rowCounter)
                        columnCounter -= 1;
                    else
                        rowCounter += 1;
                } else {
                    if (rowCounter < getNumRows() - 1)
                        rowCounter += 1;
                    else
                        rowCounter = getNumRows() - 1;

                    if (getNumColumns() - 1 - columnCounter < getNumColumns() - 1)
                        columnCounter -= 1;
                    else
                        columnCounter = 0;
                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the left bottom
     */
    public void moveLeftDown() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];

        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = getDatabase()[j][k];
            }
        }

        int rowCounter = getNumRows() - 1;
        int columnCounter = 0;

        while (Math.max(getNumRows() - 1 - rowCounter, columnCounter) < Math.max(getNumRows(), getNumColumns())) {

            for (int row = rowCounter; row > -1; row--) {
                int r = row;
                int c = columnCounter;
                if (columnCounter != 0) {
                    while (r != getNumRows() - 1 && c != 0) {
                        if (database1[r][c] != 0) {
                            if (database1[r + 1][c - 1] == 0) {
                                database1[r + 1][c - 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r + 1][c - 1]) {
                                database1[r][c] = 0;
                                database1[r + 1][c - 1] *= 2;
                            }
                        }
                        r += 1;
                        c -= 1;
                    }
                }
            }


            for (int column = columnCounter; column < getNumColumns(); column++) {
                int r = rowCounter;
                int c = column;
                if (rowCounter != getNumRows() - 1) {
                    while (r != getNumRows() - 1 && c != 0) {
                        if (database1[r][c] != 0) {
                            if (database1[r + 1][c - 1] == 0) {
                                database1[r + 1][c - 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r + 1][c - 1]) {
                                database1[r][c] = 0;
                                database1[r + 1][c - 1] *= 2;
                            }
                        }
                        r += 1;
                        c -= 1;
                    }
                }
            }

            if (getNumColumns() == getNumRows()) {
                rowCounter -= 1;
                columnCounter += 1;
            } else {
                if (getNumRows() - 1 - rowCounter != columnCounter) {
                    if (columnCounter > getNumRows() - 1 - rowCounter)
                        columnCounter += 1;
                    else
                        rowCounter -= 1;
                } else {
                    if (getNumRows() - 1 - rowCounter < getNumRows() - 1)
                        rowCounter -= 1;
                    else
                        rowCounter = 0;

                    if (columnCounter < getNumColumns() - 1)
                        columnCounter += 1;
                    else
                        columnCounter = getNumColumns() - 1;
                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method move all the numbers, according to the rule, to the right bottom
     */
    public void moveRightDown() {
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        for (int j = 0; j < getNumRows(); j++) {
            for (int k = 0; k < getNumColumns(); k++) {
                database1[j][k] = getDatabase()[j][k];
            }
        }
        int rowCounter = getNumRows() - 1;
        int columnCounter = getNumColumns() - 1;
        while (Math.max(getNumRows() - 1 - columnCounter, getNumColumns() - 1 - columnCounter) < Math.max(getNumRows(), getNumColumns())) {
            for (int row = rowCounter; row > -1; row--) {
                int r = row;
                int c = columnCounter;
                if (columnCounter != getNumColumns() - 1) {
                    while (r != getNumRows() - 1 && c != getNumColumns() - 1) {
                        if (database1[r][c] != 0) {
                            if (database1[r + 1][c + 1] == 0) {
                                database1[r + 1][c + 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r + 1][c + 1]) {
                                database1[r][c] = 0;
                                database1[r + 1][c + 1] *= 2;
                            }
                        }
                        r += 1;
                        c += 1;
                    }
                }
            }


            for (int column = columnCounter; column > -1; column--) {
                int r = rowCounter;
                int c = column;
                if (rowCounter != getNumRows() - 1) {
                    while (r != getNumRows() - 1 && c != getNumColumns() - 1) {
                        if (database1[r][c] != 0) {
                            if (database1[r + 1][c + 1] == 0) {
                                database1[r + 1][c + 1] = database1[r][c];
                                database1[r][c] = 0;
                            } else if (database1[r][c] == database1[r + 1][c + 1]) {
                                database1[r][c] = 0;
                                database1[r + 1][c + 1] *= 2;
                            }
                        }
                        r += 1;
                        c += 1;
                    }
                }
            }

            if (getNumColumns() == getNumRows()) {
                rowCounter -= 1;
                columnCounter -= 1;
            } else {
                if (getNumRows() - 1 - rowCounter != getNumColumns() - 1 - columnCounter) {
                    if (getNumColumns() - 1 - columnCounter > getNumRows() - 1 - rowCounter)
                        columnCounter -= 1;
                    else
                        rowCounter -= 1;
                } else {
                    if (getNumRows() - 1 - rowCounter < getNumRows() - 1)
                        rowCounter -= 1;
                    else
                        rowCounter = 0;

                    if (getNumColumns() - 1 - columnCounter < getNumColumns() - 1)
                        columnCounter -= 1;
                    else
                        columnCounter = 0;
                }
            }
        }
        if (!isSame(getDatabase(), database1)) {
            setDatabase(database1);
            randomPlace();
        }
    }

    /**
     * this method place 1 on a random button after making a valid move
     */
    public void randomPlace() {
        int zeroCounter = 0;
        int counter = 0;
        int[][] database1 = new int[getNumRows()][getNumColumns()];
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                database1[i][j] = getDatabase()[i][j];
                if (getDatabase()[i][j] == 0)
                    zeroCounter += 1;
            }
        }
        int randomNumber = 0;

        while (randomNumber <= 1) {
            randomNumber = (int) (Math.random() * zeroCounter + 1);
        }

        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                if (getDatabase()[i][j] == 0) {
                    counter += 1;
                    if (counter == randomNumber)
                        database1[i][j] = 1;
                }
            }
        }
        setDatabase(database1);
    }

    /**
     * this method tell whether a click on button makes a valid move
     * @param a and b are both arrays. if they are the same, that means it's a invalid move, otherwise it's a valid move
     * @return if it returns true, it's a invalid move and false means a valid move
     */
    public boolean isSame(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter the dimension, no less than 4*4");
            setNumRows(scanner.nextInt());
            setNumColumns(scanner.nextInt());
            while(getNumRows()<4 || getNumColumns()<4) {
                System.out.println("invalid dimension, please enter again");
                setNumRows(scanner.nextInt());
                setNumColumns(scanner.nextInt());
            }
            Button[][] button1 = new Button[getNumRows()][getNumColumns()];
            setButtons(button1);

            initializeDatabase();
            makeGridPane();
            printOnButtons();

            for (int i = 1; i < getNumColumns() - 1; i++) {
                //up
                getButtons()[0][i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        moveUp();
                        printOnButtons();
                    }
                });
                //down
                getButtons()[getNumRows() - 1][i].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        moveDown();
                        printOnButtons();
                    }
                });
            }
            for (int i = 1; i < getNumRows() - 1; i++) {
                //left
                getButtons()[i][0].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        moveLeft();
                        printOnButtons();
                    }
                });
                //right
                getButtons()[i][getNumColumns() - 1].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        moveRight();
                        printOnButtons();
                    }
                });
            }

            getButtons()[0][0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    moveLeftUp();
                    printOnButtons();
                }
            });

            getButtons()[0][getNumColumns() - 1].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    moveRightUp();
                    printOnButtons();
                }
            });

            getButtons()[getNumRows() - 1][getNumColumns() - 1].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    moveRightDown();
                    printOnButtons();
                }
            });

            getButtons()[getNumRows() - 1][0].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    moveLeftDown();
                    printOnButtons();
                }
            });


            Scene scene = new Scene(getGridPane());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter anything to start");
        String start = scanner.next();
        if (start != null)
            Application.launch(args);
    }
}
