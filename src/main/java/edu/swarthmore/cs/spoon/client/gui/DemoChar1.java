package edu.swarthmore.cs.spoon.client.gui;

import edu.swarthmore.cs.spoon.client.gui.interfaces.GUIPlayerSprites;
import edu.swarthmore.cs.spoon.client.network.GameClientImpl;
import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

public class DemoChar1  extends Pane implements GUIPlayerSprites{
    private int playerID;
    private PlayerCharacter yourPlayer;
    private final int WALKINGTOTAL = 8; //Quantity for how many pngs there should be for walking animations. According to my spritesheet there should always be 8
    private Sprite sp = Sprite.DEMOCHAR1;
    private Image visibleImg; //File for viisbl
    private ImageView visibleImgView;
    private Rectangle collRect;
    private final int TILESIZE = 32;
    private final int CENTER = 16;
    private Collection<Image> moveUpCol = null;
    private Transition animation;
    private String urlPrefix;
    private Collection<Image> moveDownCol = null;
    private Collection<Image> moveLeftCol = null;
    private Collection<Image> moveRightCol = null;
    private Collection<Image> currCycle;
    private final Logger logger = LoggerFactory.getLogger(DemoChar1.class);

    /**
     * The sprite for DemoChar1
     * @return
     */

    public DemoChar1(PlayerCharacter player, int id, String urlToFolder) {
        super();
        this.playerID = id;
        this.urlPrefix = urlToFolder;
        this.setDefaultImg();
        System.out.println("URL for Player " + id + " : " + urlPrefix);

        //collRect = new Rectangle(TILESIZE, TILESIZE);
        //collRect.setFill(Color.color(1.0, 0.0, 0.0, .4));
        System.out.println("viewport: " + visibleImgView.getViewport());

        this.getChildren().add(visibleImgView);
        this.setWidth(this.visibleImgView.getFitWidth()); //Making sure that the Pane has the same Width as the image
        this.setHeight(this.visibleImgView.getFitHeight()); //Making sure that the Pane has the same Height as the image
        //collRect.setX(CENTER); //Setting the X coordinate of the collision rectngle.
        //collRect.setY(CENTER);
        //this.getChildren().add(collRect);
    }



    @Override
    public Image getDefaultImg() {
        return visibleImg;
    }

    @Override
    public void setDefaultImg() {
        System.out.println("Problematic: " + urlPrefix + "/DOWNStand.png");
        this.visibleImg = new Image(urlPrefix + "/DOWNStand.png");
        this.visibleImgView = new ImageView();
        this.visibleImgView.setFitHeight(this.visibleImg.getHeight()); //Turns out when you make an ImageView, it doesn't seem to get values for Height and Width
        this.visibleImgView.setFitWidth(this.visibleImg.getWidth()); //These two lines address that problem and just make sure that the view's h and w are the same as the image's.
        this.visibleImgView.setImage(this.visibleImg);
    }

    @Override
    public void setStillImg(String direction) {
        //System.out.println("/images/demochar1/" + direction.toUpperCase() + "Stand.png");
        if(direction == null) {
            System.out.println("direction null");
            if (yourPlayer.getMovState() == Direction.NONE) {
                this.setDefaultImg();
                return;
            }
        }
        else if (direction == "NONE") {
            this.setDefaultImg();
            return;
        }
        else if(animation == null) {
            System.out.println("animation null, creating new transition");
            animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(2000));
                    setCycleCount(Timeline.INDEFINITE);
                }

                @Override
                protected void interpolate(double v) {
                    System.out.println("Problematic string: " + urlPrefix + "/" + direction.toUpperCase() + "Stand.png");
                    DemoChar1.this.visibleImgView.setImage(new Image(urlPrefix + "/" + direction.toUpperCase() + "Stand.png"));
                }
            };
            animation.pause();
            System.out.println("checking url: " + urlPrefix + "/" + direction.toUpperCase() + "Stand.png");
            this.visibleImgView.setImage(new Image(urlPrefix + "/" + direction.toUpperCase() + "Stand.png"));
            return;
        }
        else if (animation != null || animation.getCurrentRate() != 0.0d) {
            System.out.println("ending animation");
            animation.pause();
            animation.stop();
            animation = null;
            this.visibleImgView.setImage(new Image(urlPrefix + "/" + direction.toUpperCase() + "Stand.png"));

            animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(2000));
                    setCycleCount(Timeline.INDEFINITE);
                }

                @Override
                protected void interpolate(double v) {
                    //DemoChar1.this.visibleImgView.setImage(new Image(urlPrefix + "/UPStand.png"));
                    System.out.println("Potentially problematic line: " + urlPrefix + "/" + direction.toUpperCase() + "Stand.png");
                    DemoChar1.this.visibleImgView.setImage(new Image(urlPrefix + "/" + direction.toUpperCase() + "Stand.png"));
                }
            };
            animation.pause();
        }
        //this.visibleImgView.setImage(new Image("/images/democharsprite/" + direction.toUpperCase() + "Stand.png"));
        //this.visibleImgView =
    }

    @Override
    public Rectangle setCollisionRect() {
        return null;
    }

    @Override
    public Rectangle getCollisionRect() {
        return collRect;
    }

    @Override
    public Rectangle modifyCollisionRectSize(int width, int height) {
        return null;
    }

    @Override
    public Image getLastDirectionImg() {
        return null;
    }

    @Override
    public void setStandUpImg() {
        this.visibleImgView.setImage(new Image(urlPrefix + "/UPStand.png"));
    }

    @Override
    public void setStandDownImg() {
        this.visibleImgView.setImage(new Image(urlPrefix + "/DOWNStand.png"));
    }

    @Override
    public Image getStandLeftImg() {
        return null;
    }

    @Override
    public Image getStandRightImg() {
        return null;
    }

    @Override
    public void walkUPImageCycle() {
        ArrayList<Image> upCycle;
        upCycle = (ArrayList) getMoveUPImages();
        //System.out.println("Walking up!");

        ArrayList<Image> finalUpCycle = upCycle;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000)); // total time for animation
                setCycleCount(Timeline.INDEFINITE);
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(finalUpCycle.size()-1));
                DemoChar1.this.visibleImgView.setImage(finalUpCycle.get(index));//setImage(images.get(index));
            }
        };
        animation.play();
    }

    @Override
    public void walkInDirectionCycle(String direction) {
        ArrayList<Image> desiredCycle = null;
        Method method = null;
        try {
            method = this.getClass().getMethod("getMove" + direction + "Images");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e ) {
            e.printStackTrace();
        }

        try {
            desiredCycle = (ArrayList<Image>) method.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        ArrayList<Image> finalDesiredCycle = desiredCycle;
        animation = new Transition() {

            {
                setCycleDuration(Duration.millis(2000));
                setCycleCount(Timeline.INDEFINITE);
            }

            @Override
            protected void interpolate(double v) {
                int index = (int) (v * (finalDesiredCycle.size() - 1));
                DemoChar1.this.visibleImgView.setImage(finalDesiredCycle.get(index));
            }

        };
        animation.play();
    }

    @Override
    public void walkDOWNImageCycle() {
        ArrayList<Image> downCycle;
        downCycle = (ArrayList) getMoveDOWNImages();
        //System.out.println("Walking up!");
        System.out.println("Downcycle size: " +  downCycle.size());

        ArrayList<Image> finalUpCycle = downCycle;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000)); // total time for animation
                setCycleCount(Timeline.INDEFINITE);
            }

            @Override
            protected void interpolate(double fraction) {
                System.out.println("Downcycle size: " +  downCycle.size());
                int index = (int) (fraction*(downCycle.size()-1));
                DemoChar1.this.visibleImgView.setImage(downCycle.get(index));//setImage(images.get(index));
            }
        };
        animation.play();


    }

    @Override
    public void walkRIGHTImageCycle() {
        ArrayList<Image> rightCycle;
        rightCycle = (ArrayList) getMoveRIGHTImages();
        //System.out.println("Walking up!");

        ArrayList<Image> finalUpCycle = rightCycle;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000)); // total time for animation
                setCycleCount(Timeline.INDEFINITE);
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(finalUpCycle.size()-1));
                DemoChar1.this.visibleImgView.setImage(finalUpCycle.get(index));//setImage(images.get(index));
            }
        };
        animation.play();

    }

    @Override
    public void walkLEFTImageCycle() {
        ArrayList<Image> leftCycle;
        leftCycle = (ArrayList) getMoveLEFTImages();
        //System.out.println("Walking up!");

        ArrayList<Image> finalUpCycle = leftCycle;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000)); // total time for animation
                setCycleCount(Timeline.INDEFINITE);
            }

            @Override
            protected void interpolate(double fraction) {
                int index = (int) (fraction*(finalUpCycle.size()-1));
                DemoChar1.this.visibleImgView.setImage(finalUpCycle.get(index));//setImage(images.get(index));
            }
        };
        animation.play();

    }

    @Override
    public Collection<Image> getMoveRIGHTImages() {
        if(this.moveRightCol == null) {
            this.moveRightCol = new ArrayList<>();
            for (int i = 1; i<=8; i++) {
                System.out.println("Iteration, on image " + i);
                logger.info(urlPrefix + "/RIGHTMove" + i + ".png");

                moveRightCol.add(new Image(urlPrefix + "/RIGHTMove" + i + ".png"));
            }
            return moveRightCol;
        } else {
            return this.moveRightCol;
        }
    }

    @Override
    public Collection<Image> getMoveLEFTImages() {
        if(this.moveLeftCol == null) {
            this.moveLeftCol = new ArrayList<>();
            for(int i = 1; i <=8; i++) {
                moveLeftCol.add(new Image(urlPrefix + "/LEFTMove" + i + ".png"));

            }
            return moveLeftCol;
        } else {
            return this.moveLeftCol;
        }
    }

    @Override
    public Collection<Image> getMoveDOWNImages() {
        if(moveDownCol == null) {
            this.moveDownCol = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                moveDownCol.add(new Image(urlPrefix + "/DOWNMove" + i +".png"));
            }
            return moveDownCol;
        } else {
            return this.moveDownCol;
        }
    }

    @Override
    public Collection<Image> getMoveUPImages() {
        if (this.moveUpCol == null) {
            //If moveupCol is null, retrieve the files
            this.moveUpCol = new ArrayList<>();
            for (int i = 1; i <= 8; i++) {
                moveUpCol.add(new Image(urlPrefix + "/UPMove" + i + ".png"));
            }
            return moveUpCol;
        } else {
            return this.moveUpCol;
        }
    }
}
