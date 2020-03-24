package com.example.calculator2;

import java.text.DecimalFormat;

public class Work{
    private double firstNum;

    public double getFirstNum() {
        return firstNum;
    }

    public double getSecondNum() {
        return secondNum;
    }

    private double secondNum;

    private StringBuilder inputStr = new StringBuilder();

    public int getActionSelected() {
        return actionSelected;
    }

    private int actionSelected;

    public String getState() {
        return state.toString();
    }

    DecimalFormat df = new DecimalFormat("#.#######");

    private State state;

    private enum State{
        firstNumInput,
        operatorInput,
        secondInput,
        resultShow
    }

    public Work (){
        state = State.firstNumInput;
        inputStr.append("0");
    }

    public Work (String inputStr, String state, int actionSelected, double firstNum, double secondNum){
        switch (state){
            case "firstNumInput":
                this.state = State.firstNumInput;
                break;
            case "operatorInput":
                this.state = State.operatorInput;
                break;
            case "secondInput":
                this.state = State.secondInput;
                break;
            case "resultShow":
                this.state = State.resultShow;
                break;
        }
        this.inputStr.append(inputStr);
        this.actionSelected = actionSelected;
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public void onNumPressed(int buttonId){

        if(state == State.resultShow) {
            state = State.firstNumInput;
            inputStr.setLength(0);
        }

        if(inputStr.length() < 10) {
            switch (buttonId) {
                case R.id.num0:
                    if (!inputStr.toString().equals("0")) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.num1:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("1");
                    } else{
                        inputStr.append("1");
                    }
                    break;
                case R.id.num2:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("2");
                    } else{
                        inputStr.append("2");
                    }
                    break;
                case R.id.num3:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("3");
                    } else{
                        inputStr.append("3");
                    }
                    break;
                case R.id.num4:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("4");
                    } else{
                        inputStr.append("4");
                    }
                    break;
                case R.id.num5:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("5");
                    } else{
                        inputStr.append("5");
                    }
                    break;
                case R.id.num6:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("6");
                    } else{
                        inputStr.append("6");
                    }
                    break;
                case R.id.num7:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("7");
                    } else{
                        inputStr.append("7");
                    }
                    break;
                case R.id.num8:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("8");
                    } else{
                        inputStr.append("8");
                    }
                    break;
                case R.id.num9:
                    if (inputStr.toString().equals("0")) {
                        inputStr.setLength(0);
                        inputStr.append("9");
                    } else{
                        inputStr.append("9");
                    }
                    break;
            }
        }

        switch (buttonId) {
            case R.id.pi:
                inputStr.setLength(0);
                inputStr.append(df.format(Math.PI).replace(',', '.'));
                break;
            case R.id.exponent:
                inputStr.setLength(0);
                inputStr.append(df.format(Math.exp(1)).replace(',', '.'));
                break;
            case R.id.negative:
                if (!inputStr.toString().equals("0") && inputStr.length() != 0) {
                    double number = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    if(number < 0){
                        inputStr.append(Math.abs(number));
                    } else{
                        inputStr.append(number * (-1));
                    }
                }
                break;
        }

        if(state == State.operatorInput)
            state = State.secondInput;
    }

    public void onActionsPressed(int actionId){
        if(inputStr.toString().equals("You have been divided by zero!") || inputStr.toString().equals("Error!") || inputStr.toString().equals("The number is negative!") || inputStr.toString().equals("The number is not integer!")) {
            return;
        }

        if(actionId == R.id.delete){

            if(inputStr.length() == 0){
                inputStr.append("0");
            }else{
                inputStr.setLength(inputStr.length() - 1);
                if (inputStr.length() == 0)
                    inputStr.append("0");
            }
            return;
        }

        if((inputStr.toString().indexOf('.') == -1) && (actionId == R.id.dot)){
            if(inputStr.length() == 0){
                inputStr.append("0");
                inputStr.append(".");
                return;
            }else{
                inputStr.append(".");
                return;
            }
        }


        if ( state == State.firstNumInput) {
            if(actionId == R.id.equals)
                return;

            switch (actionId) {
                case R.id.sin:
                    firstNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.sin(Math.toRadians(firstNum)));
                    return;
                case R.id.cos:
                    firstNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.cos(Math.toRadians(firstNum)));
                    return;
                case R.id.tan:
                    firstNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.tan(Math.toRadians(firstNum)));
                    return;
                case R.id.ln:
                    firstNum = parseToDouble(inputStr.toString());
                    if(firstNum <= 0){
                        inputStr.setLength(0);
                        inputStr.append("Error!");
                        state = State.resultShow;
                        return;
                    }else {
                        inputStr.setLength(0);
                        inputStr.append(Math.log(firstNum));
                    }
                    return;
                case R.id.log:
                    firstNum = parseToDouble(inputStr.toString());
                    if(firstNum <= 0){
                        inputStr.setLength(0);
                        inputStr.append("Error!");
                        state = State.resultShow;
                        return;
                    }else {
                        inputStr.setLength(0);
                        inputStr.append(Math.log10(firstNum));
                    }
                    return;
                case R.id.factorial:
                    int number = 0;
                    try{
                        number = Integer.parseInt(inputStr.toString());
                    }catch (Exception e){
                        inputStr.setLength(0);
                        inputStr.append("The number is not integer!");
                        state = State.resultShow;
                        return;
                    }
                    if(number < 0){
                        inputStr.setLength(0);
                        inputStr.append("The number is negative!");
                        state = State.resultShow;
                        return;
                    }
                    int fact = 1;
                    for (int i = 1; i <= number; i++)
                        fact *= i;
                    firstNum = fact;
                    inputStr.setLength(0);
                    inputStr.append(fact);
                    return;
            }

            if(inputStr.length() != 0) {
                firstNum = parseToDouble(inputStr.toString());
                inputStr.setLength(0);
                state = State.operatorInput;
                switch (actionId) {
                    case R.id.multiply:
                        actionSelected = R.id.multiply;
                        break;
                    case R.id.division:
                        actionSelected = R.id.division;
                        break;
                    case R.id.minus:
                        actionSelected = R.id.minus;
                        break;
                    case R.id.plus:
                        actionSelected = R.id.plus;
                        break;
                    case R.id.degree:
                        actionSelected = R.id.degree;
                        break;
                    case R.id.root:
                        actionSelected = R.id.root;
                        break;
                }
            }
        }else if(state == State.operatorInput) {
            switch (actionId) {
                case R.id.multiply:
                    actionSelected = R.id.multiply;
                    return;
                case R.id.division:
                    actionSelected = R.id.division;
                    return;
                case R.id.minus:
                    actionSelected = R.id.minus;
                    return;
                case R.id.plus:
                    actionSelected = R.id.plus;
                    return;
                case R.id.degree:
                    actionSelected = R.id.degree;
                    return;
                case R.id.root:
                    actionSelected = R.id.root;
                    break;
            }
        }else if( state == State.secondInput){

            switch (actionId) {
                case R.id.sin:
                    secondNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.sin(Math.toRadians(secondNum)));
                    return;
                case R.id.cos:
                    secondNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.cos(Math.toRadians(secondNum)));
                    return;
                case R.id.tan:
                    secondNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);
                    inputStr.append(Math.tan(Math.toRadians(secondNum)));
                    return;
                case R.id.ln:
                    secondNum = parseToDouble(inputStr.toString());
                    if(firstNum <= 0){
                        inputStr.setLength(0);
                        inputStr.append("Error!");
                        state = State.resultShow;
                        return;
                    }else {
                        inputStr.setLength(0);
                        inputStr.append(Math.log(secondNum));
                    }
                    return;
                case R.id.log:
                    secondNum = parseToDouble(inputStr.toString());
                    if(firstNum <= 0){
                        inputStr.setLength(0);
                        inputStr.append("Error!");
                        state = State.resultShow;
                        return;
                    }else {
                        inputStr.setLength(0);
                        inputStr.append(Math.log10(secondNum));
                    }
                    return;
                case R.id.factorial:
                    int number = 0;
                    try{
                        number = Integer.parseInt(inputStr.toString());
                    }catch (Exception e){
                        inputStr.setLength(0);
                        inputStr.append("The number is not integer!");
                        state = State.resultShow;
                        return;
                    }
                    if(number < 0){
                        inputStr.setLength(0);
                        inputStr.append("The number is negative!");
                        state = State.resultShow;
                        return;
                    }
                    long fact = 1;
                    for (int i = 1; i <= number; i++)
                        fact *= i;
                    secondNum = fact;
                    inputStr.setLength(0);
                    inputStr.append(fact);
                    return;
            }

            if(actionId == R.id.percent){
                secondNum = parseToDouble(inputStr.toString());
                inputStr.setLength(0);

                switch (actionSelected) {
                    case R.id.multiply:
                        inputStr.append(firstNum * secondNum * firstNum / 100);
                        firstNum = firstNum * secondNum * firstNum / 100;
                        break;
                    case R.id.division:
                        if (secondNum == 0) {
                            inputStr.append("You have been divided by zero!");
                        } else {
                            inputStr.append(firstNum / secondNum * firstNum / 100);
                            firstNum = firstNum / secondNum * firstNum / 100;
                        }
                        break;
                    case R.id.minus:
                        inputStr.append(firstNum - secondNum * firstNum / 100);
                        firstNum = firstNum - secondNum * firstNum / 100;
                        break;
                    case R.id.plus:
                        inputStr.append(firstNum + secondNum * firstNum / 100);
                        firstNum = firstNum + secondNum * firstNum / 100;
                        break;
                }
                state = State.resultShow;
            }

            if(inputStr.length() != 0)
                if(actionId == R.id.equals || actionId == R.id.plus || actionId == R.id.minus || actionId == R.id.division || actionId == R.id.multiply || actionId == R.id.degree || actionId == R.id.root) {
                    secondNum = parseToDouble(inputStr.toString());
                    inputStr.setLength(0);

                    switch (actionSelected) {
                        case R.id.multiply:
                            if(actionId == R.id.equals)
                                inputStr.append(firstNum * secondNum);
                            firstNum = firstNum * secondNum;
                            break;
                        case R.id.division:
                            if (secondNum == 0) {
                                inputStr.append("You have been divided by zero!");
                            } else {
                                if (actionId == R.id.equals)
                                    inputStr.append(firstNum / secondNum);
                                firstNum = firstNum / secondNum;
                            }
                            break;
                        case R.id.minus:
                            if(actionId == R.id.equals)
                                inputStr.append(firstNum - secondNum);
                            firstNum = firstNum - secondNum;
                            break;
                        case R.id.plus:
                            if(actionId == R.id.equals)
                                inputStr.append(firstNum + secondNum);
                            firstNum = firstNum + secondNum;
                            break;
                        case R.id.degree:
                            if(actionId == R.id.equals)
                                inputStr.append(Math.pow(firstNum,secondNum));
                            firstNum = Math.pow(firstNum,secondNum);
                            break;
                        case R.id.root:
                            if(firstNum < 0){
                                inputStr.append("The number is negative!");
                                state = State.resultShow;
                                return;
                            }else{
                                if(actionId == R.id.equals)
                                    inputStr.append(Math.pow(firstNum, 1/secondNum));
                                firstNum = Math.pow(firstNum, 1/secondNum);
                            }
                            break;
                    }
                    if(actionId == R.id.equals)
                        state = State.resultShow;
                    else{
                        state = State.operatorInput;
                        onActionsPressed(actionId);
                    }
                }
        } else if( state == State.resultShow){
            state = State.firstNumInput;
            onActionsPressed(actionId);
        }
    }

    public String getText(){
        return inputStr.toString();
    }

    public void deleteAll(){
        inputStr.setLength(0);
        inputStr.append("0");
        state = State.firstNumInput;
    }

    public double parseToDouble(String string){
        try {
            double number = Double.parseDouble(string);
            return number;
        }catch (Exception e){
            state = State.resultShow;
            return 0;
        }
    }
}