package com.tareqyassin.bignumber;

import java.util.ArrayList;

public class BigNumber {
    private ArrayList<Integer> number;
    private boolean isNegative;
    private final String[] LARGE_NUMBERS_NAMES = {
            "k",     // Thousand
            "M",     // Million
            "B",     // Billion
            "T",     // Trillion
            "P",     // Quadrillion
            "E",     // Quintillion
            "Z",     // Sextillion
            "Y",     // Septillion
            "R",     // Octillion
            "Q",     // Nonillion
    };

    public BigNumber(){
        this.number = new ArrayList<>();
        isNegative = false;
    }

    public BigNumber(String inputNum) {
      initNumber(inputNum);
    }

    private void initNumber(String inputNum) {
        if(inputNum.charAt(0) == '-'){
            isNegative = true;
        }

        this.number = new ArrayList<>();
        inputNum = this.cleanInput(inputNum);
        for(int i = inputNum.length() - 1 ; i >= 0 ; i--){
            this.number.add(Integer.parseInt(inputNum.charAt(i) + ""));
        }
    }

    private String cleanInput(String input) {
        input = input.replace("_", "").replace("-", "");
        if(input.charAt(0) == '0'){
            int count = 0;
            for(int i = 0 ; i < input.length(); i++){
                if(input.charAt(i) == '0'){
                    count ++;
                }else{
                    break;
                }
            }

            return input.substring(count);

        }

        return input;
    }


    public void setNumber(String number){
        initNumber(number);
    }

    public BigNumber add(BigNumber other) {
        int cary = 0;
        BigNumber result = new BigNumber();
        BigNumber bigger;
        BigNumber smaller;

        if(this.isGreaterThan(other)){
            bigger = this;
            smaller = other;
        }else {
            bigger = other;
            smaller = this;
        }

        for(int i = 0; i < smaller.number.size(); i++){
            int value = cary + smaller.number.get(i) + bigger.number.get(i);
            cary = value / 10;
            result.number.add(value % 10);
        }

        for(int i = smaller.number.size(); i < bigger.number.size(); i++ ){
            int value = cary + bigger.number.get(i);
            cary = value / 10;
            result.number.add(value % 10);
        }
        return result;
    }

    public BigNumber sub(BigNumber other) {
        if (this.equals(other)) {
            return new BigNumber("0");
        }

        int borrow = 0;
        BigNumber result = new BigNumber();
        BigNumber bigger;
        BigNumber smaller;

        if (this.isGreaterThan(other)) {
            bigger = this;
            smaller = other;
        } else {
            bigger = other;
            smaller = this;
            result.isNegative = true;
        }

        for (int i = 0; i < smaller.number.size(); i++) {
            int diff = bigger.number.get(i) - borrow - smaller.number.get(i);
            if (diff < 0) {
                borrow = 1;
                diff += 10;
            } else {
                borrow = 0;
            }
            result.number.add(diff);
        }

        for (int i = smaller.number.size(); i < bigger.number.size(); i++) {
            int diff = bigger.number.get(i) - borrow;
            if (diff < 0) {
                borrow = 1;
                diff += 10;
            } else {
                borrow = 0;
            }
            result.number.add(diff);
        }

        while (result.number.size() > 1 && result.number.get(result.number.size() - 1) == 0) {
            result.number.remove(result.number.size() - 1);
        }

        return result;
    }

    public BigNumber mul(BigNumber other) {
        BigNumber result = new BigNumber();
        ArrayList<Integer> temp = new ArrayList<>();

        for(int i = 0; i < this.number.size() + other.number.size(); i++){
            temp.add(0);
        }

        for(int i = 0; i < other.number.size(); i++){
            int carry = 0;
            for(int j = 0; j < this.number.size(); j++){
                int value = temp.get(i+j) + carry + this.number.get(j) * other.number.get(i);
                carry = value / 10;
                temp.set(i+j, value % 10);
            }
            temp.set(i + this.number.size(), carry);
        }

        while(temp.size() > 0 && temp.get(temp.size() - 1) == 0){
            temp.remove(temp.size() - 1);
        }

        result.number = temp;
        return result;
    }

    @Override
    public String toString() {
        int i;
        int symbolIndex = (this.number.size() - 1) / 3 - 1;
        StringBuilder str = new StringBuilder();
        //number is less than 1k
        if(symbolIndex < 0){
            for(i = this.number.size() - 1; i >=0; i--){
                str.append(this.number.get(i));
            }

            return str.toString();
        }
        //number is bigger equal to 1k
        String symbol = LARGE_NUMBERS_NAMES[symbolIndex];
        if(isNegative){
            str.append("-");
        }
        for(i = 0; i < (this.number.size() - 1) % 3 + 1; i++){
            str.append(this.number.get(this.number.size() - 1 - i ));
        }

        return   str + "." + this.number.get(i) + symbol;
    }

    private boolean isGreaterThan(BigNumber other) {
        if(this.isNegative && !other.isNegative)
            return false;

        if(!this.isNegative && other.isNegative)
            return true;

        if (this.number.size() > other.number.size()) {
            return true;
        }

        if (this.number.size() < other.number.size()) {
            return false;
        }

        // compare the digits from the most significant to the least significant
        for (int i = this.number.size() - 1; i >= 0; i--) {
            if(!this.isNegative){
                if (this.number.get(i) > other.number.get(i)) {
                    return true;
                } else if (this.number.get(i) < other.number.get(i)) {
                    return false;
                }
            }else {
                if (this.number.get(i) <= other.number.get(i)) {
                    return true;
                } else if (this.number.get(i) >= other.number.get(i)) {
                    return false;
                }
            }
        }
        // the numbers are equal
        return false;
    }

}
