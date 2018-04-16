package Presetation.mainUI;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;

/**
 * Created by 常德隆 on 2017/4/19.
 */
public class addTableCell extends TableCell {
    private Button button;

    public addTableCell(){
        button=new Button();
        button.setVisible(true);
    }

    @Override
    public void startEdit(){

    }

    @Override
    public void cancelEdit(){

    }
}
