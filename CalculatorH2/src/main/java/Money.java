public class Money {
    private String value;

    public Money() {
    }


    public Money(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    //метод который врзвращает новый объект сконвертированной в другую валюту(рубли)
    public Money toRubles(){
        if(value.startsWith("$"))
            return new Money(Float.parseFloat(value.substring(1))*60+"rub");
        return this;
    }
    //метод который возвращает новый объект сконвертированной в другую валюту(долары)
    public Money toDollar(){
        if(value.endsWith("rub"))
            return new Money("$"+Float.parseFloat(value.substring(0,value.length()-3))/60);
        return this;
    }
    //Метод используемы для получения численного значения указанной валюты
    public float getNumber(){
        if(value.startsWith("$"))
            return Float.parseFloat(value.substring(1));
        else if(value.endsWith("rub"))
            return Float.parseFloat(value.substring(0,value.length()-3));
        return 0;
    }
    //Метод используемы для проверки соответствия строки указанному формату
    public static boolean isCurrencyFormat(String str){
        if(str.startsWith("$")){
            if(IsStringNumber.isNumeric(str.substring(1)))
                return true;
        }
        else if(str.endsWith("rub") && IsStringNumber.isNumeric(str.substring(0,str.length()-3))){
              return true;
        }
        return false;
    }
}
