package Presetation.mainUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 常德隆 on 2017/3/17.
 */
public  class tableDataForC {
        private  SimpleStringProperty dataType;
        private  SimpleStringProperty quantourA;
        private  SimpleStringProperty quantourB;


    public tableDataForC(){}

    public tableDataForC(String dataType, String quantourA, String quantourB){
        this.dataType=new SimpleStringProperty(dataType);
        this.quantourA=new SimpleStringProperty(quantourA);
        this.quantourB=new SimpleStringProperty(quantourB);
    }

    public String getDataType() {
        return dataType.get();
    }

    public SimpleStringProperty dataTypeProperty() {
        return dataType;
    }

    public String getQuantourA() {
        return quantourA.get();
    }

    public SimpleStringProperty quantourAProperty() {
        return quantourA;
    }

    public String getQuantourB() {
        return quantourB.get();
    }

    public SimpleStringProperty quantourBProperty() {
        return quantourB;
    }

    public void setQuantourB(String quantourB) {
        this.quantourB.set(quantourB);
    }

    public void setQuantourA(String quantourA) {
        this.quantourA.set(quantourA);
    }

    public void setDataType(String dataType) {
        this.dataType.set(dataType);
    }
}
