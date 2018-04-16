package Presetation.mainUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * Created by 常德隆 on 2017/4/9.
 */
public class tableDataForStockPool {
    private SimpleStringProperty stockName;
    private SimpleStringProperty stockCode;
    private SimpleStringProperty plateInfo;

    tableDataForStockPool(){}

    tableDataForStockPool(String stockName, String stockCode, String plateInfo){
        this.stockName=new SimpleStringProperty(stockName);
        this.stockCode=new SimpleStringProperty(stockCode);
        this.plateInfo=new SimpleStringProperty(plateInfo);
    }

    public String getStockName() {
        return stockName.get();
    }

    public SimpleStringProperty stockNameProperty() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName.set(stockName);
    }

    public String getStockCode() {
        return stockCode.get();
    }

    public SimpleStringProperty stockCodeProperty() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode.set(stockCode);
    }

    public String getPlateInfo() {
        return plateInfo.get();
    }

    public SimpleStringProperty plateInfoProperty() {
        return plateInfo;
    }

    public void setPlateInfo(String plateInfo) {
        this.plateInfo.set(plateInfo);
    }
}
