import java.util.ArrayList;
import java.util.List;

public class CalculationEngine {

    public InputStack inputContainer = new InputStack();
    public String calculatedString = "";
    public int orderOfOperation = 0;
    public int iterCounter = 0;

    // Input for when the ENTER button is clicked. NumberObject should be
    // the last object
    public void intake(MathObjects obj) {
        inputContainer.add(obj);
        System.out.println(obj.toString());
    }

    // For when an operator button is hit, a NumberObject should be obj1
    // An OperatorObject should be obj2
    public void intake(MathObjects obj1, MathObjects obj2) {
        inputContainer.add(obj1);
        inputContainer.add(obj2);
        System.out.println(obj1.toString());
        System.out.println(obj2.toString());
    }

    public void clearInputContainer() {
        inputContainer.clearStack();
    }

    public void runCalculation(){
        System.out.println("----------------------");
        System.out.println("Starting Calculation!");
        calculationDriverMethod(inputContainer);
    }

    // This method executes the logical flow of calculating the result of the input
    private void calculationDriverMethod(List<MathObjects> inputArr) {

        System.out.println("-----------------------------------");
        System.out.println("Iteration # " + iterCounter);

        printArray(inputArr);

        List<MathObjects> receivingList;
        int orderOfOperation = setOrderOfOperationIndex(inputArr);
        int nextOperatorIndex = findNextOperatorIndex(inputArr, orderOfOperation);
        MathObjects operator = inputArr.get(nextOperatorIndex);
        MathObjects leftOperand = inputArr.get(nextOperatorIndex - 1);
        MathObjects rightOperand = inputArr.get(nextOperatorIndex + 1);
        MathObjects calculationResult = performOperation(operator, leftOperand, rightOperand);

        receivingList = toNewArray(inputArr, calculationResult,
                nextOperatorIndex -1, nextOperatorIndex + 1);

        // Recursive Call
        if (receivingList.size() == 1) {
            System.out.println("Setting calc string");
            setCalculatedString(receivingList.get(0)); // this give answer
        } else {
            System.out.println("Being recursive");
            iterCounter ++;
            calculationDriverMethod(receivingList);
        }
    }

    private int setOrderOfOperationIndex(List<MathObjects> inputArr){
        int highestOrder = 0;
        int objectOrder = 0;

        for (int index = 0; index < inputArr.size(); index++){
            objectOrder = inputArr.get(index).getOrderIndex();
            if (objectOrder > highestOrder){
                highestOrder = objectOrder;
            }
        }
        return highestOrder;
    }

    private int findNextOperatorIndex(List<MathObjects> inputArr, int orderOfOperation) {
        int objectOrder = 0;
        int objectIndex = 0;
        // System.out.println("----------------------\n");
       //  System.out.println("In findeNextOperatorIndex");

        for (int index = 0; index < inputArr.size(); index++) {
            objectOrder = inputArr.get(index).getOrderIndex();
            if (objectOrder == orderOfOperation) {
                objectIndex = index;
                break;
            }
        }
        return objectIndex;
    }

    private NumberObject performOperation(MathObjects operator, MathObjects val1, MathObjects val2) {
        double output = 0;
        output = ((OperatorObjects)operator).operation(((NumberObject)val1).getNumValue(), ((NumberObject)val2).getNumValue());
        System.out.println(output);
        return new NumberObject(output);
    }

    public List<MathObjects> toNewArray(List<MathObjects> inputArr, MathObjects newResult,
                                        int endIndex, int startIndex ){
        /*
        this for loop works backwards. startIndex is higher than endIndex.
        endindex is where the new result will be inserted
         */
        System.out.println("Start index is " + startIndex);
        System.out.println("End index is " + endIndex);

        for (int index = startIndex; index >= endIndex; index-- ){
            System.out.println("---> Removing " + inputArr.get(index));
            inputArr.remove(index);
        }
        inputArr.add(endIndex, newResult);

        return inputArr;
    }

    private void setCalculatedString(MathObjects obj) {
        calculatedString =obj.toString();
        System.out.println(calculatedString);
    }

    public String getCompletedCalculation(){
        return calculatedString;
    }

    public void printArray(List<MathObjects> inputArr) {
        System.out.println("The Array is : ");
        for (int index = 0; index < inputArr.size(); index++) {
            System.out.print(index + " : ");
            System.out.print(inputArr.get(index) + "\n");
        }
    }
}


