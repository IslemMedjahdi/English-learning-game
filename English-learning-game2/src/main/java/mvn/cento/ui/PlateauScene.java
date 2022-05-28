package mvn.cento.ui;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mvn.cento.Main;
import mvn.cento.Noyeau.Couleur;
import mvn.cento.Noyeau.Exceptions.positionInvalideException;
import mvn.cento.Noyeau.Partie;
import mvn.cento.Noyeau.Plateau;

import java.io.IOException;
import java.util.Objects;

public class PlateauScene {

    private static int deplacement;
    private static int positionAdeplacer;
    private static GridPane layout = new GridPane();
    private static GridPane plateauContainer = new GridPane();
    private static StackPane popUpContainer ;

    public static Scene getPlateauScene(Partie partie) throws IOException {

        Plateau generatedPlateau = partie.getPlateau();
        FXMLLoader plateauFXML = new FXMLLoader(Main.class.getResource("view/home.fxml"));


        AnchorPane plateau = plateauFXML.load();
        plateau.setScaleX(1.3);
        plateau.setScaleY(0.99);
        plateauContainer.getChildren().add( plateau);
        plateauContainer.setAlignment(Pos.CENTER);
        plateauContainer.setPrefWidth(2000);

        GridPane.setHalignment(plateauContainer, HPos.CENTER);


        GridPane sideBar = new GridPane();
        sideBar.setId("SideBar");
        sideBar.setPrefWidth(320);
        sideBar.setMinWidth(320);
        sideBar.setPrefHeight(1500);
        sideBar.setAlignment(Pos.TOP_CENTER);


        //user Data box
        VBox userData = new VBox();
        userData.setId("userDataBox");
        userData.setMinWidth(270);
        userData.setMaxWidth(270);
        GridPane.setMargin(userData,new Insets(30,30,30,30));

        //user account
        HBox userAccount = new HBox();
        Region icon = new Region();
        icon.setId("UserIcon");
        Text text = new Text();
        text.setText("Marouane");
        text.getStyleClass().add("white-text");
        userAccount.getChildren().add(icon);
        userAccount.getChildren().add(text);

        HBox.setMargin(icon,new Insets(0,8,0,16));
        HBox.setMargin(text,new Insets(0,0,0,16));
        userData.getChildren().add(userAccount);
        VBox.setMargin(userAccount,new Insets(12,0,4,0));

        //user best score
        HBox userBestScore = new HBox();
        icon = new Region();
        icon.setId("CupIcon");
        text = new Text();
        text.setText("9,325");
        text.getStyleClass().add("white-text");
        userBestScore.getChildren().add(icon);
        userBestScore.getChildren().add(text);

        HBox.setMargin(icon,new Insets(0,8,0,16));
        HBox.setMargin(text,new Insets(0,0,0,16));
        userData.getChildren().add(userBestScore);
        VBox.setMargin(userBestScore,new Insets(4,0,12,0));

        //board informations

        VBox infos = new VBox();
        infos.setAlignment(Pos.CENTER_LEFT);
        Text score = new Text();

        score.setText("Total Score : 0");
        score.getStyleClass().add("white-text");
        infos.getChildren().add(score);
        Text position = new Text();
        position.setText("Postion : 1");
        position.getStyleClass().add("white-text");
        infos.getChildren().add(position);
        Text caseType = new Text();
        caseType.setText("Case : Start");
        caseType.getStyleClass().add("white-text");
        infos.getChildren().add(caseType);
        Text errorMessage = new Text();
        errorMessage.setText("Incorrect position");
        errorMessage.getStyleClass().add("errorMessage");
        errorMessage.setVisible(false);
        infos.getChildren().add(errorMessage);

        VBox.setMargin(score ,new Insets(2,8,2,8));
        VBox.setMargin(position ,new Insets(2,8,2,8));
        VBox.setMargin(caseType ,new Insets(2,8,2,8));
        VBox.setMargin(errorMessage ,new Insets(24,8,2,8));

        GridPane.setMargin(infos,new Insets(80,30,10,30));

        //dices informations
        VBox dices = new VBox();
        //dices.setAlignment(Pos.BOTTOM_CENTER);
        dices.setId("dicesBox");
        dices.setMinWidth(270);
        dices.setMaxWidth(270);
        dices.setAlignment(Pos.CENTER);


        HBox dicesImages = new HBox();
        dicesImages.setAlignment(Pos.CENTER);

        ImageView dice1 = new ImageView(Objects.requireNonNull(Main.class.getResource("images/dices/dice1.png")).toExternalForm())  ;
        dice1.setFitWidth(66);
        dice1.setPreserveRatio(true);

        ImageView dice2 = new ImageView(Objects.requireNonNull(Main.class.getResource("images/dices/dice6.png")).toExternalForm())  ;
        dice2.setFitWidth(66);
        dice2.setPreserveRatio(true);


        dicesImages.getChildren().add(dice1);
        dicesImages.getChildren().add(dice2);

        HBox.setMargin(dice1,new Insets(4,16,4,16));
        HBox.setMargin(dice2,new Insets(4,16,4,16));


        Button throwButton = new Button();

        throwButton.setId("throwButton");
        throwButton.setPrefWidth(100);
        throwButton.setText("Roll");
        VBox.setMargin(throwButton,new Insets(20,2,10,2));

        dices.getChildren().add(dicesImages);
        dices.getChildren().add(throwButton);

        GridPane.setMargin(dices, new Insets(10,0,20,0));
        GridPane.setHalignment(dices,HPos.CENTER);


        //sizedBox
        VBox sizedBox = new VBox();
        sizedBox.setPrefHeight(1000);

        sideBar.add(userData, 0,0);
        sideBar.add(infos,0,1);
        sideBar.add(sizedBox,0,2);
        sideBar.add(dices,0,3);





        layout.setAlignment(Pos.CENTER_LEFT);
        layout.add(sideBar,0,0 );
        layout.add(plateauContainer,1,0);



        GridPane.setHalignment(plateauContainer, HPos.CENTER);
        layout.setId("pane");


        Scene scene = new Scene(layout, 1300, 750);
        scene.getStylesheets().addAll(Objects.requireNonNull(Main.class.getResource("css/style.css")).toExternalForm());






        // BonusCase.setGraphic(bonusIcon);
        for (int i = 0 ; i< 100 ; i++){
            Button casePlateau =(Button) plateau.getChildren().get(i);
            switch (generatedPlateau.getCaseParPosition(i).getCouleur()){
                case ROUGE ->{

                    casePlateau.getStyleClass().add("malusCase");
                }
                case VERT -> {
                    casePlateau.getStyleClass().add("bonusCase");
                }
                case JAUNE -> {
                    casePlateau.getStyleClass().add("departCase");
                    casePlateau.setText("Start");
                }
                case BLEU -> {
                    casePlateau.getStyleClass().add("questionDefCase");
                }

                case ROSE -> {
                    casePlateau.getStyleClass().add("questionImgCase");
                }
                case ORANGE -> {
                    casePlateau.getStyleClass().add("sautCase") ;
                }
                case BLANC -> casePlateau.getStyleClass().add("parcourCase");
                case NOIR -> {
                    casePlateau.getStyleClass().add("finCase");
                    casePlateau.setText("END");
                }
            }


            casePlateau.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                Button button = casePlateau;
                try{
                    int pos =  Integer.parseInt(button.getId()) -1 ;
                    generatedPlateau.positioner(pos,positionAdeplacer );
                    button.getStyleClass().remove("selectedButton");
                    button.toBack();
                    errorMessage.setVisible(false);

                    Couleur couleurCase = generatedPlateau.getCaseParPosition(positionAdeplacer).getCouleur() ;
                    switch (couleurCase){
                        case BLANC -> caseType.setText("Case : Normal");
                        case NOIR -> caseType.setText("Case : End");
                        case ROSE, BLEU -> caseType.setText("Case : Question");
                        case VERT -> caseType.setText("Case : Bonus");
                        case ORANGE -> caseType.setText("Case : Jump");
                        case JAUNE -> caseType.setText("Case : Start");
                        case ROUGE -> caseType.setText("Case : Penalty");
                    }

                    do {

                        positionAdeplacer= generatedPlateau.getPositionCourante();
                        couleurCase = generatedPlateau.getCaseParPosition(positionAdeplacer).getCouleur() ;
                        position.setText("Position : "+ (positionAdeplacer + 1));
                        if(couleurCase == Couleur.ROSE){
                           addImgPopUp();
                        }else if(couleurCase == Couleur.BLEU){
                            addDefPopUp();
                        }else {
                            generatedPlateau.getCaseParPosition(positionAdeplacer).traiter(partie);

                        }
                        score.setText("Total Score : "+partie.getScore());

                    } while (generatedPlateau.getPositionCourante() != positionAdeplacer);

                    if(partie.getFinPartie()){
                        throwButton.setDisable(true);
                    }else {
                        throwButton.setDisable(false);
                    }



                }catch (positionInvalideException e){
                    errorMessage.setVisible(true);
                    System.out.println(e.getMessage());
                }
            });
        }


        //event handler for dices
        throwButton.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {



            deplacement = generatedPlateau.lancerDes();


            if((generatedPlateau.getPositionCourante() + deplacement)>99 || generatedPlateau.getCaseParPosition(generatedPlateau.getPositionCourante() + deplacement).getCouleur()== Couleur.BLANC){
                deplacement = generatedPlateau.lancerDes();
                System.out.println("again");
            }


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.2), new EventHandler<>() {

                private int i = 1;

                @Override
                public void handle(ActionEvent event) {

                    if (i < 6) {
                        dice1.setImage(new Image(Objects.requireNonNull(Main.class.getResource("images/dices/dice" + i + ".png")).toExternalForm()));
                        dice2.setImage(new Image(Objects.requireNonNull(Main.class.getResource("images/dices/dice" + (7 - i) + ".png")).toExternalForm()));

                    } else {
                        dice1.setImage(new Image(Objects.requireNonNull(Main.class.getResource("images/dices/dice" + generatedPlateau.getDe1() + ".png")).toExternalForm()));
                        dice2.setImage(new Image(Objects.requireNonNull(Main.class.getResource("images/dices/dice" + generatedPlateau.getDe2() + ".png")).toExternalForm()));
                        positionAdeplacer = generatedPlateau.getPositionCourante() + deplacement;
                        if (positionAdeplacer <= 99) {
                            Button selectedButton = (Button) scene.lookup("#" + (positionAdeplacer + 1));
                            selectedButton.getStyleClass().add("selectedButton");
                            selectedButton.toFront();
                        } else {
                            positionAdeplacer = generatedPlateau.getPositionCourante() - (positionAdeplacer - 100);
                            Button selectedButton = (Button) scene.lookup("#" + (positionAdeplacer + 1));
                            selectedButton.getStyleClass().add("selectedButton");
                        }


                    }

                    i++;

                }
            }));
            timeline.setCycleCount(6);
            timeline.play();

            throwButton.setDisable(true);

        });




        return scene;
    }

    public static void removePopUp(){
        layout.getChildren().remove(2);
        plateauContainer.setEffect(new GaussianBlur(0));
    }

    public static void addDefPopUp(){
        popUpContainer = new StackPane(DefinitionPopUp.getDefinitionPopUp());
        popUpContainer.setAlignment(Pos.CENTER);
        popUpContainer.setPrefHeight(2000);
        popUpContainer.setPrefWidth(2000);
        layout.add(popUpContainer,1,0);
        plateauContainer.setEffect(new GaussianBlur(30));
    }
    public static void addImgPopUp(){
        popUpContainer = new StackPane(ImagePopUp.getImagePopUp());
        popUpContainer.setAlignment(Pos.CENTER);
        popUpContainer.setPrefHeight(2000);
        popUpContainer.setPrefWidth(2000);
        layout.add(popUpContainer,1,0);
        plateauContainer.setEffect(new GaussianBlur(30));
    }





}

