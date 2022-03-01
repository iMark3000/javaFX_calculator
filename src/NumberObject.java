public class NumberObject extends MathObjects {

    double numValue;
    int orderOfOperationIndex = 0;
    String strValue;
    boolean isNegated = false;

    public NumberObject(double n) {
        numValue = n;
        strValue = Double.toString(n);
    }

    public double getNumValue() {
        return numValue;
    }

    public void setNegation(){
        numValue = numValue * -1;
        if (isNegated){
            isNegated = false;
        } else {
            isNegated = true;
        }
    }

    public String toString() {
        if (isNegated) {
            return "-" + strValue;
        } else {
            return strValue;
        }
    }

    @Override
    public int getOrderIndex(){
        return orderOfOperationIndex;
    }

}
