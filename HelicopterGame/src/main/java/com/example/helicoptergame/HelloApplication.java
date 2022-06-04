package com.example.helicoptergame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

import static javafx.scene.paint.Color.*;


public class HelloApplication extends Application {
     int score;

    TranslateTransition cloudstrans = new TranslateTransition(Duration.millis(10000));
    TranslateTransition clouds2trans = new TranslateTransition(Duration.millis(11000));
    TranslateTransition clouds3trans = new TranslateTransition(Duration.millis(11000));
    TranslateTransition cloud4trans = new TranslateTransition(Duration.millis(11000));
    TranslateTransition bonustrans = new TranslateTransition(Duration.millis(12000));
    TranslateTransition cointrans = new TranslateTransition(Duration.millis(12000));
    TranslateTransition coin2trans =new TranslateTransition(Duration.millis(11500));
    TranslateTransition clouds5trans= new TranslateTransition(Duration.millis(12000));
    TranslateTransition clouds6trans= new TranslateTransition(Duration.millis(12000));
    TranslateTransition clouds7trans= new TranslateTransition(Duration.millis(12000));








    @Override
    public void start(Stage stage) {
       Pane root = new Pane();
        Scene scene = new Scene(root, 600, 500);
       ImageView coin = createCoin(scene);
       ImageView coin2 =  createCoin2(scene);
        ImageView sekoro = createSekoro(scene);
        ImageView bonus = createBonus(scene);
        ImageView clouds = createClouds(scene);
        ImageView clouds2 = createClouds(scene);
        ImageView clouds3 = createClouds3(scene);
        ImageView clouds4 = createClouds4(scene);
        ImageView clouds5 = createClouds5(scene);
        ImageView clouds6 = createClouds6(scene);
        ImageView clouds7 = createClouds7(scene);
        ImageView copter = createShip(scene);

        //Label loser = new Label();
       Label points= new Label();
       points.setLayoutX(600);
       points.setLayoutY(30);
       points.setTextFill(GREENYELLOW);
       points.setFont(Font.font("Verdana",FontWeight.BOLD,50));

       //Game over
        String title1 = "OOPS..GAME OVER!";
        Label gameover = new Label();
       gameover.setTextFill(RED);
        gameover.setLayoutX(400);
        gameover.setLayoutY(300);
        gameover.setFont(Font.font("Verdana",FontWeight.BOLD,70));






        root.getChildren().addAll(gameover,points,sekoro,copter,  clouds, clouds2, clouds3,
                                   clouds4,clouds5,clouds6,clouds7, bonus,coin,coin2);

        Image img = new Image("1.jpg");
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
        Background bGround = new Background(bImg);
        root.setBackground(bGround);



        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double x = copter.getX();
            double y = copter.getY();
            switch (event.getCode()) {
                case UP -> copter.setY(y - 10);
                case DOWN -> copter.setY(y + 10);
                case LEFT -> copter.setX(x-10);
                case RIGHT -> copter.setX(x+10);
            }
        });




        AnimationTimer checkCollision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                CollisionDetected(clouds,clouds2,clouds3,clouds4,clouds5,clouds6,clouds7);
            }
            private void CollisionDetected( ImageView clouds, ImageView clouds2, ImageView clouds3,
                                            ImageView clouds4,ImageView clouds5,ImageView clouds6,ImageView clouds7) {
                if (copter.getBoundsInParent().intersects(clouds.getBoundsInParent()) ||
                        copter.getBoundsInParent().intersects(clouds2.getBoundsInParent()) ||
                        copter.getBoundsInParent().intersects(clouds3.getBoundsInParent()) ||
                        copter.getBoundsInParent().intersects(clouds4.getBoundsInParent())||
                        copter.getBoundsInParent().intersects(clouds5.getBoundsInParent())||
                        copter.getBoundsInParent().intersects(clouds6.getBoundsInParent())||
                        copter.getBoundsInParent().intersects(clouds7.getBoundsInParent())) {
                    System.out.println("BOOM");
                    gameover.setText(title1);
                    clouds2trans.stop();
                    cloudstrans.stop(); cointrans.stop();coin2trans.stop();
                    cloud4trans.stop();clouds7trans.stop();bonustrans.stop();
                    clouds3trans.stop();clouds5trans.stop();clouds6trans.stop();

                    copter.setImage(new Image("heliexplode.png"));
                }
                clouds.translateXProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if(copter.getBoundsInParent().intersects(bonus.getBoundsInParent())){
                            score=score+1;
                            points.setText(score+"");
                        }
                        else if(copter.getBoundsInParent().intersects(coin.getBoundsInParent())){
                            score=score+1;
                            points.setText(score+"");
                        }
                        else if (copter.getBoundsInParent().intersects(coin2.getBoundsInParent())){
                            score=score+1;
                            points.setText(score+"");
                        }
                    }
                });
            }
        };
        checkCollision.start();
        stage.setTitle("Helicopter Crash Game-Molefi MOlefi");
        stage.setScene(scene);
        stage.show();
    }
    private ImageView createSekoro(Scene scene){
        ImageView sekoro = new ImageView(new Image("score.png"));
        sekoro.setX(500);
        sekoro.setFitHeight(50);
        sekoro.setFitWidth(100);
        sekoro.setY(40);
        return sekoro;
    }


    private ImageView createClouds3(Scene scene) {
        ImageView clouds3 = new ImageView(new Image("cloud.png"));
        clouds3.setFitWidth(80);
        clouds3.setFitHeight(80);
        clouds3.setY(90);
        clouds3.setX(1800);
        clouds3trans.setNode(clouds3);
        clouds3trans.setByX(-2000);
        clouds3trans.setCycleCount(Integer.MAX_VALUE);
       clouds3trans.play();
        return clouds3;
    }

    private ImageView createClouds4(Scene scene) {
        ImageView clouds4 = new ImageView(new Image("leru2.png"));
        clouds4.setFitWidth(80);
        clouds4.setFitHeight(80);
        clouds4.setY(300);
        clouds4.setX(1800);
        cloud4trans.setNode(clouds4);
        cloud4trans.setByX(-2000);
        cloud4trans.setCycleCount(Integer.MAX_VALUE);
        cloud4trans.play();

        return clouds4;
    }
    private ImageView createClouds5(Scene scene){
        ImageView clouds5 = new ImageView(new Image("leru.png"));
        clouds5.setFitWidth(80);
        clouds5.setFitHeight(80);
        clouds5.setX(1900);
        clouds5.setY(400);
        clouds5trans.setNode(clouds5);
        clouds5trans.setByX(-1800);
        clouds5trans.setCycleCount(Integer.MAX_VALUE);
        clouds5trans.play();
        return clouds5;
    }
    private ImageView createClouds6(Scene scene) {
        ImageView clouds6 = new ImageView(new Image("cloud.png"));
        clouds6.setFitWidth(80);
        clouds6.setFitHeight(80);
        clouds6.setX(1900);
        clouds6.setY(200);
        clouds6trans.setNode(clouds6);
        clouds6trans.setByX(-1800);
        clouds6trans.setCycleCount(Integer.MAX_VALUE);
        clouds6trans.play();
        return clouds6;
    }
    private ImageView createClouds7(Scene scene) {
        ImageView clouds7 = new ImageView(new Image("leru.png"));
        clouds7.setFitWidth(80);
        clouds7.setFitHeight(80);
        clouds7.setX(2000);
        clouds7.setY(50);
        clouds7trans.setNode(clouds7);
        clouds7trans.setByX(-1800);
        clouds7trans.setCycleCount(Integer.MAX_VALUE);
        clouds7trans.play();
        return clouds7;
    }

    private ImageView createClouds(Scene scene) {
        ImageView clouds = new ImageView(new Image("cloud.png"));
        ImageView clouds2 = new ImageView(new Image("leru2.png"));
        clouds.setFitWidth(80);
        clouds.setFitHeight(80);
        clouds.setY(200);
        clouds.setX(1800);
        clouds2.setFitWidth(80);
        clouds2.setFitHeight(80);
        clouds2.setY(50);
        clouds2.setX(1750);
        cloudstrans.setNode(clouds);
        cloudstrans.setByX(-1800);
        cloudstrans.setCycleCount(Integer.MAX_VALUE);
        cloudstrans.play();
       clouds2trans.setNode(clouds2);
        clouds2trans.setByX(-1750);
        clouds2trans.setCycleCount(Integer.MAX_VALUE);
        clouds2trans.play();
        return clouds;
    }

    private ImageView createBonus(Scene scene) {
        ImageView bonus = new ImageView(new Image("starcoin.png"));
        bonus.setFitWidth(80);
        bonus.setFitHeight(80);
        bonus.setY(500);
        bonus.setX(1800);
        bonustrans.setNode(bonus);
        bonustrans.setByX(-1800);
        bonustrans.setCycleCount(Integer.MAX_VALUE);
        bonustrans.play();
        return bonus;
    }

    private ImageView createCoin(Scene scene) {
        ImageView coin = new ImageView(new Image("randcoin.png"));
        coin.setFitWidth(80);
        coin.setFitHeight(80);
        coin.setY(100);
        coin.setX(1000);
        cointrans.setNode(coin);
        cointrans.setByX(-2200);
        cointrans.setCycleCount(Integer.MAX_VALUE);
        cointrans.play();
        return coin;
    }
    private ImageView createCoin2(Scene scene) {
        ImageView coin2 = new ImageView(new Image("starcoin.png"));
        coin2.setFitWidth(80);
        coin2.setFitHeight(80);
        coin2.setY(500);
        coin2.setX(2000);
        coin2trans.setNode(coin2);
        coin2trans.setByX(-2000);
        coin2trans.setCycleCount(Integer.MAX_VALUE);
        coin2trans.play();
        return coin2;
    }

    private ImageView createShip(Scene scene) {
        ImageView image = new ImageView(new Image("heli.png"));
        image.setFitWidth(200);
        image.setFitHeight(200);
        image.setX(100);
       image.setY(scene.getHeight() - image.getFitHeight());

        return image;

    }











    public static void main(String[] args) {
        launch();
    }
}
