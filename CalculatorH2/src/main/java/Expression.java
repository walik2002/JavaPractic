public class Expression {
    private Money first_value;
    private Money second_value;
    private int operation;
    private int result_currency;

    public Expression(String first_value, String second_value, int operation, int result_currency) {
        this.first_value = new Money(first_value);
        this.second_value = new Money(second_value);
        this.operation = operation;
        this.result_currency = result_currency;
    }

    public String getExpression(){
        String expression =first_value.getValue();
        if(operation ==1){
            expression+=" + ";
        }
        else expression +=" - ";
        expression+= second_value.getValue() +" = ";
        float res;
        if(result_currency==1){
            if(operation==1)
                res = first_value.toDollar().getNumber()+second_value.toDollar().getNumber();
            else
                res = first_value.toDollar().getNumber()-second_value.toDollar().getNumber();
            expression+="$"+res;
        }
        else {
            if(operation==1)
                res = first_value.toRubles().getNumber()+second_value.toRubles().getNumber();
            else
                res = first_value.toRubles().getNumber()-second_value.toRubles().getNumber();
            expression+=res+"rub";
        }
        return expression;
    }
}
