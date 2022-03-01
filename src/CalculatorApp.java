import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;

// TODO - Add "." button
// TODO - get negation button working
// TODO - get backspace working
// TODO - Add "()"
// TODO - Add menu bar
// TODO - Add history panel to display history of calculations. Toggle visibility in menu

public class CalculatorApp extends Application {

    public static IOdisplay inOutDisplay = new IOdisplay();
    public static CalculationEngine calculationEngine= new CalculationEngine();
    public static InputOutputRouter ioRouter = new InputOutputRouter();
    public static Label ioDisplayLabel = new Label("_");
    public static boolean operatorButtonsDisabled = false;
    public static NumberObject[] numHolder = new NumberObject[1];

    // TODO figure out disabling buttons

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        double buttonMinWidth = 50;
        double buttonMinHeight = 50;


        ioDisplayLabel.setMinHeight(buttonMinHeight);
        ioDisplayLabel.setMinWidth(buttonMinWidth * 5);
        ioDisplayLabel.setTextAlignment(TextAlignment.RIGHT);
        HBox topBox = new HBox(ioDisplayLabel);
        topBox.setAlignment(Pos.TOP_CENTER);


        // Numeric Buttons
        ValuedButton button1 = new ValuedButton("1", 1);
        button1.setMinSize(buttonMinWidth,buttonMinHeight);
        button1.setOnAction(event -> numberButtonAction(button1));

        ValuedButton button2 = new ValuedButton("2", 2);
        button2.setMinSize(buttonMinWidth,buttonMinHeight);
        button2.setOnAction(event -> numberButtonAction(button2));

        ValuedButton button3 = new ValuedButton("3", 3);
        button3.setMinSize(buttonMinWidth,buttonMinHeight);
        button3.setOnAction(event -> numberButtonAction(button3));

        ValuedButton button4 = new ValuedButton("4", 4);
        button4.setMinSize(buttonMinWidth,buttonMinHeight);
        button4.setOnAction(event -> numberButtonAction(button4));

        ValuedButton button5 = new ValuedButton("5", 5);
        button5.setMinSize(buttonMinWidth,buttonMinHeight);
        button5.setOnAction(event -> numberButtonAction(button5));

        ValuedButton button6 = new ValuedButton("6", 6);
        button6.setMinSize(buttonMinWidth,buttonMinHeight);
        button6.setOnAction(event -> numberButtonAction(button6));

        ValuedButton button7 = new ValuedButton("7", 7);
        button7.setMinSize(buttonMinWidth,buttonMinHeight);
        button7.setOnAction(event -> numberButtonAction(button7));

        ValuedButton button8 = new ValuedButton("8", 8);
        button8.setMinSize(buttonMinWidth,buttonMinHeight);
        button8.setOnAction(event -> numberButtonAction(button8));

        ValuedButton button9 = new ValuedButton("9", 9);
        button9.setMinSize(buttonMinWidth,buttonMinHeight);
        button9.setOnAction(event -> numberButtonAction(button9));

        ValuedButton button0 = new ValuedButton("0", 0);
        button0.setMinSize(buttonMinWidth,buttonMinHeight);
        button0.setOnAction(event -> numberButtonAction(button0));

        // Function Buttons
        Button addButton = new Button("+");
        addButton.setMinSize(buttonMinWidth,buttonMinHeight);
        addButton.setDisable(operatorButtonsDisabled);
        addButton.setOnAction(event -> operatorButtonAction(new AddOperation()));

        Button subtractButton = new Button("-");
        subtractButton.setMinSize(buttonMinWidth,buttonMinHeight);
        subtractButton.setDisable(operatorButtonsDisabled);
        subtractButton.setOnAction(event ->  operatorButtonAction(new SubtractOperation()));

        Button multiplyButton = new Button("*");
        multiplyButton.setMinSize(buttonMinWidth,buttonMinHeight);
        // multiplyButton.disarm(operatorButtonsDisabled);
        multiplyButton.setOnAction(event ->  operatorButtonAction(new MultiplyOperation()));

        Button divButton = new Button("/");
        divButton.setMinSize(buttonMinWidth,buttonMinHeight);
        divButton.setDisable(operatorButtonsDisabled);
        divButton.setOnAction(event ->  operatorButtonAction(new DivOperation()));

        Button sumButton = new Button("=");
        sumButton.setMinSize(150,buttonMinHeight);
        sumButton.setOnAction(event -> sumButtonAction());

        // TODO: fix this event
        Button clearButton = new Button("CLR");
        clearButton.setMinSize(buttonMinWidth,buttonMinHeight);
        clearButton.setOnAction(event -> clearButtonAction());

        // TODO: fix this event. Needs to more than just change text. Must also change value
        // of number object
        Button invertValueButton = new Button("-/+");
        invertValueButton.setMinSize(buttonMinWidth,buttonMinHeight);

        // Update function
        Button backSpace = new Button("<-");
        backSpace.setMinSize(buttonMinWidth,buttonMinHeight);

        GridPane buttonPanel = new GridPane();
        buttonPanel.add(button1 ,0,0);
        buttonPanel.add(button4,0,1);
        buttonPanel.add(button7 ,0,2);
        buttonPanel.add(button2,1,0);
        buttonPanel.add(button5,1,1);
        buttonPanel.add(button8,1,2);
        buttonPanel.add(button0,1,3);
        buttonPanel.add(button3,2,0);
        buttonPanel.add(button6,2,1);
        buttonPanel.add(button9,2,2);

        buttonPanel.add(invertValueButton,0,3);
        buttonPanel.add(addButton, 3, 1);
        buttonPanel.add(subtractButton, 3, 2);
        buttonPanel.add(sumButton, 2,3,3,1);
        buttonPanel.add(multiplyButton, 4, 1);
        buttonPanel.add(divButton, 4, 2);
        buttonPanel.add(clearButton, 3, 0);
        buttonPanel.add(backSpace, 4, 0);

        VBox mainContainer = new VBox(topBox, buttonPanel);

        Scene scene = new Scene(mainContainer);
        scene.getStylesheets().add("calcStyle.css");

        // Calculating stage width and height
        double stageWidth = 5 * buttonMinWidth;
        double stageHeight = 5.55 * buttonMinHeight;

        // Setting Min and Max width for primaryStage
        primaryStage.setMaxWidth(stageWidth);
        primaryStage.setMinWidth(stageWidth);

        // Setting Min and Max height for primaryStage
        primaryStage.setMaxHeight(stageHeight);
        primaryStage.setMinHeight(stageHeight);
        primaryStage.setOpacity(0.85);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void numberButtonAction (ValuedButton button){
        ioRouter.inputStream(button.getButtonValue());
        ioDisplayLabel.setText(ioRouter.refreshDisplayLabel());
    }

    public static void operatorButtonAction (OperatorObjects obj){
        ioRouter.inputStream(obj);
        ioDisplayLabel.setText(ioRouter.refreshDisplayLabel());
    }

    public static void sumButtonAction() {
        // This function drives the CalculatorEngine class to perform calculations
        ioRouter.sumButtonEvent();
        ioDisplayLabel.setText(ioRouter.calculationResultDisplayLabel());
    }

    public static void clearButtonAction() {
        String resetString = ioRouter.clearButtonEvent();
        ioDisplayLabel.setText(resetString);
    }
}
