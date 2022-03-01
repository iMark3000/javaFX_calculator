import javafx.scene.control.Button;

public class ValuedButton extends Button {

    private double value;

    public ValuedButton(String s, double val) {
        super(s);
        value = val;
    }

    public double getButtonValue() {
        return value;
    }

}
