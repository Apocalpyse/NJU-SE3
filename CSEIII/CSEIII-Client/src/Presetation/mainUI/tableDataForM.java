package Presetation.mainUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 常德隆 on 2017/3/17.
 */
public class tableDataForM {

    private SimpleStringProperty quantourName;
    private SimpleStringProperty data;

    public tableDataForM(String quantourName, String data){
        this.quantourName=new SimpleStringProperty(quantourName);
        this.data=new SimpleStringProperty(data);
    }

    public String getQuantourName() {
        return quantourName.get();
    }

    public void setQuantourName(String quantourName) {
        this.quantourName.set(quantourName);
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }
}
