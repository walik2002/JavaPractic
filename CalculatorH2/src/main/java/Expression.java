public class Expression {
    private Money firstValue;
    private Money secondValue;
    private int operation;
    private int resultCurrency;

    public Expression(String firstValue, String secondValue, int operation, int resultCurrency) {
        this.firstValue = new Money(firstValue);
        this.secondValue = new Money(secondValue);
        this.operation = operation;
        this.resultCurrency = resultCurrency;
    }

    public String getExpression(){
        String expression = firstValue.getValue();
        if(operation ==1){
            expression+=" + ";
        }
        else expression +=" - ";
        expression+= secondValue.getValue() +" = ";
        float res;
        if(resultCurrency ==1){
            if(operation==1)
                res = firstValue.toDollar().getNumber()+ secondValue.toDollar().getNumber();
            else
                res = firstValue.toDollar().getNumber()- secondValue.toDollar().getNumber();
            expression+="$"+res;
        }
        else {
            if(operation==1)
                res = firstValue.toRubles().getNumber()+ secondValue.toRubles().getNumber();
            else
                res = firstValue.toRubles().getNumber()- secondValue.toRubles().getNumber();
            expression+=res+"rub";
        }
        return expression;
    }
}
