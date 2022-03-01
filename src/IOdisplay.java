public class IOdisplay {

    public String outputString = "";
    public double currentNumber = 0;


    // This takes in doubles as number ar being entered
    public void intake(double input){
        currentNumber = input;
    }

    // This is for when an OperatorButton is hit
    // String comes from OperatorObject toString()
    public void intake(String str){
        addCurrentNumberToOutputString();
        outputString += str;
        resetCurrentNumber();
    }

    // This method is always called when an OperatorObject is created
    private void addCurrentNumberToOutputString(){
        outputString += convertCurrentNumberToString();
    }

    private String convertCurrentNumberToString(){
        String convertedCurrentNubmer;
        if (removeDecimalTest()) {
            int currentInt = (int) currentNumber;
            convertedCurrentNubmer = Integer.toString(currentInt);
        } else {
            convertedCurrentNubmer = Double.toString(currentNumber);
        }
        return convertedCurrentNubmer;
    }

    private boolean removeDecimalTest(){
        if (currentNumber - Math.floor(currentNumber) == 0){
            return true;
        } else {
            return false;
        }
    }

    private void resetCurrentNumber(){
        currentNumber = 0;
    }

    // This gets the string for the IODisplay Label
    public String getStringForDisplay(){
        String refreshedString;
        if (currentNumber == 0){
            refreshedString = outputString;
        } else {
            refreshedString = outputString + convertCurrentNumberToString();
        }
        return refreshedString;
    }

    public void resetOutputString() {
        outputString = "";
    }

    public String getOutputString() {
        return outputString;
    }
}