/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstpreloader;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author iran
 */
public class FirstPreloader extends Preloader {
    ProgressBar bar;
    Stage stage;
 
    private Scene createPreloaderScene() {
        bar = new ProgressBar();       
        Image image = new Image(this.getClass().getResourceAsStream("2.gif"));        
        ImageView gifCarregando = new ImageView(image);
        gifCarregando.setStyle("-fx-background-color: transparent;");
        gifCarregando.setFitWidth(360);
        gifCarregando.setFitHeight(36);
        
        
        BorderPane p = new BorderPane();
        p.setBackground(Background.EMPTY);
        p.setCenter(gifCarregando);
        return new Scene(p, 360, 36);        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Scene cena = createPreloaderScene();
        cena.setFill(Color.TRANSPARENT);
        stage.setScene(cena);        
        stage.initStyle(StageStyle.UNDECORATED); 
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }    
}
