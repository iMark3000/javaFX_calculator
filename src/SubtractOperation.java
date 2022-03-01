public class SubtractOperation extends OperatorObjects {

    int orderOfOperationIndex = 1;

    @Override
    public double operation(double x, double y) {
        return x - y;
    }

    public String toString(){
        String s = "-";
        return s;
    }

    @Override
    public int getOrderIndex(){
        return orderOfOperationIndex;
    }
}
