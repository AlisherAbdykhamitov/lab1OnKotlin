package com.example.calculator;

public class Brain {
    private double firstNum;

    private double secondNum;

    private StringBuilder inputStr = new StringBuilder();

    private int actionSelected;

    private State state;

    private enum State{
        firstNumInput,
        secondNumInput,
        thirdNumInput,
        resultShow
    }

    public Brain (){
        state = State.firstNumInput;
        inputStr.append("0");
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
        if(state == State.secondNumInput)
            state = State.thirdNumInput;
    }

    public void onActionsPressed(int actionId){

        if(actionId == R.id.delete){

            if(inputStr.length() == 0){
                inputStr.append("0");
            }else{
                if(inputStr.toString().equals("You have been divided by zero!")){

                }else {
                    inputStr.setLength(inputStr.length() - 1);
                    if (inputStr.length() == 0)
                        inputStr.append("0");
                }
            }
            return;
        }

        if(inputStr.toString().indexOf('.') == -1 && actionId == R.id.dot){
            if(state == State.firstNumInput){
                inputStr.append(".");
                return;
            }else if(state == State.secondNumInput || state == State.thirdNumInput){
                if(inputStr.length() == 0){
                    inputStr.append("0");
                    inputStr.append(".");
                    return;
                }else{
                    inputStr.append(".");
                    return;
                }
            }else
                return;
        }

        if(state == State.secondNumInput) {
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
            }
        } else if ( state == State.firstNumInput) {

            if(inputStr.length() != 0) {
                firstNum = Double.parseDouble(inputStr.toString());
                inputStr.setLength(0);
                state = State.secondNumInput;
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
                }
            }
        } else if( state == State.thirdNumInput){
            if(inputStr.length() != 0 && actionId == R.id.equals) {
                secondNum = Double.parseDouble(inputStr.toString());
                inputStr.setLength(0);

                switch (actionSelected) {
                    case R.id.plus:
                        inputStr.append(firstNum + secondNum);
                        firstNum = firstNum + secondNum;
                        break;
                    case R.id.minus:
                        inputStr.append(firstNum - secondNum);
                        firstNum = firstNum - secondNum;
                        break;
                    case R.id.division:
                        if (secondNum == 0) {
                            inputStr.append("You have been divided by zero!");
                        } else {
                            inputStr.append(firstNum / secondNum);
                            firstNum = firstNum / secondNum;
                        }
                        break;
                    case R.id.multiply:
                        inputStr.append(firstNum * secondNum);
                        firstNum = firstNum * secondNum;
                        break;
                }

                state = State.resultShow;
            }
        } else if( state == State.resultShow){
            if(actionId == R.id.plus || actionId == R.id.minus || actionId == R.id.division || actionId == R.id.multiply) {
                if(inputStr.toString().equals("You have been divided by zero!")){
                    inputStr.setLength(0);
                    inputStr.append("0");
                }
                else{
                    state = State.firstNumInput;
                    onActionsPressed(actionId);
                }

            }
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
}
