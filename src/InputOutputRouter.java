public class InputOutputRouter {

    double numberHolder = 0;
    NumberObject numberToShip;

    CalculationEngine calcEngine = new CalculationEngine();
    IOdisplay iOdisplay = new IOdisplay();

    /*
    This method is called when the numeric buttons are hit
     */
    public void inputStream(double input){
        if (numberHolder == 0){
            numberHolder = input;
        } else {
            numberHolder = numberHolder * 10 + input;
        }
        iOdisplay.intake(numberHolder);
    }

    /*
    This method is called when an operator button is hit.
     */
    public void inputStream(OperatorObjects obj){
        numberToShip = new NumberObject(numberHolder);
        numberHolder = 0;
        calcEngine.intake(numberToShip, obj);
        iOdisplay.intake(obj.toString());
        // calcEngine.printArray();
    }

    // This method is for when teh sum button is clicked
    public void sumButtonEvent(){
        numberToShip = new NumberObject(numberHolder);
        numberHolder = 0;
        calcEngine.intake(numberToShip);
        calcEngine.runCalculation();
        // calcEngine.printArray();
    }

    public String clearButtonEvent() {
        calcEngine.clearInputContainer();
        iOdisplay.resetOutputString();
        return iOdisplay.getOutputString();
    }


    //Method for ioLabel to access string
    public String refreshDisplayLabel(){
        String refreshedString = iOdisplay.getStringForDisplay();
        return refreshedString;
    }

    //Method for ioLabel to access string
    public String calculationResultDisplayLabel(){
        String result = calcEngine.getCompletedCalculation();
        return result;
    }


    //Method for Backspace
    //This should only call methods in IODisplay
    //Or CalcEngine as these classes should handle the logic

    //Method for Clear
}
