/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.Component;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Only
 */
public class Move {

    private double initX;
    private double initY;

//    private boolean isMovingWindow;
//    private boolean dragging;
//    private double dragCursor;
//    private double dragOffsetX;
//    private double dragOffsetY;
//    private double dragWidth;
//    private double dragHeight;

    private double stageX;
    private double stageY;
    private double stageWidth;
    private double stageHeight;

    private int type = 0;

    private Rectangle2D backupWindowBounds = null;
    private boolean maximized = false;
    Stage stage;
    Scene scene;

    public Move(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        initMove();
    }

    private void initMove() {

        if (null != scene) {

            scene.setOnMousePressed((MouseEvent me) -> {
                mousePressed(me, stage);
            });

            scene.setOnMouseDragged((MouseEvent me) -> {
//                double maxWidth = stage.getMaxWidth();
//                double minWidth = stage.getMinWidth();
//
//                double maxHeight = stage.getMaxHeight();
//                double minHeight = stage.getMinHeight();

                if (type == 0 && !isMaximized()) {

                    stage.setX(me.getScreenX() - initX);
                    stage.setY(me.getScreenY() - initY);

                } else if (type == 1) {
                    //stage.setX(me.getScreenX());
                    //stage.setY(me.getScreenY());

                    //stage.setWidth(stageWidth + (stageX - me.getScreenX()));
                    //stage.setHeight(stageHeight + (stageY - me.getScreenY()));
                    double w = stageWidth + (stageX - me.getScreenX());
                    double h = stageHeight + (stageY - me.getScreenY());

                    updateWidth(stage, w);
                    updateHeight(stage, h);

                    updateX(stage, me.getScreenX(), w);
                    updateY(stage, me.getScreenY(), h);
                } else if (type == 2) {
                    //stage.setY(me.getScreenY());

                    //stage.setWidth(me.getX());
                    //stage.setHeight(stageHeight + (stageY - me.getScreenY()));
                    double h = stageHeight + (stageY - me.getScreenY());

                    updateWidth(stage, me.getX());
                    updateHeight(stage, h);
                    updateY(stage, me.getScreenY(), h);
                } else if (type == 3) {
                    //stage.setX(me.getScreenX());

                    //stage.setWidth(stageWidth + (stageX - me.getScreenX()));
                    //stage.setHeight(me.getY());
                    double w = stageWidth + (stageX - me.getScreenX());
                    updateWidth(stage, w);
                    updateHeight(stage, me.getY());

                    updateX(stage, me.getScreenX(), w);
                } else if (type == 4) {
                    //stage.setWidth(me.getX());
                    //stage.setHeight(me.getY());

                    updateWidth(stage, me.getX());
                    updateHeight(stage, me.getY());
                } else if (type == 5) {
                    // stage.setX(me.getScreenX());

                    //stage.setWidth(stageWidth + (stageX - me.getScreenX()));
                    double value = stageWidth + (stageX - me.getScreenX());
                    updateWidth(stage, value);
                    updateX(stage, me.getScreenX(), value);
                } else if (type == 6) {
                    //stage.setWidth(me.getX());

                    updateWidth(stage, me.getX());
                } else if (type == 7) {
                    //stage.setY(me.getScreenY());

                    //stage.setHeight(stageHeight + (stageY - me.getScreenY()));
                    double value = stageHeight + (stageY - me.getScreenY());
                    updateHeight(stage, value);
                    updateY(stage, me.getScreenY(), value);
                } else if (type == 8) {
                    //stage.setHeight(me.getY());
                    updateHeight(stage, (me.getY()));
                }
            });
            scene.setOnMouseMoved((MouseEvent me) -> {
                Cursor cursor = getCursor(me, scene, stage);
                if (scene.getCursor() != cursor) {
                    scene.setCursor(cursor);
                }
            });
        }
    }

    private void updateWidth(Stage stage, double value) {
        double max = stage.getMaxWidth();
        double min = stage.getMinWidth();

        if ((min <= value && value <= max)) {
            stage.setWidth(value);
        }
    }

    private void updateHeight(Stage stage, double value) {
        double max = stage.getMaxHeight();
        double min = stage.getMinHeight();

        if ((min <= value && value <= max)) {
            stage.setHeight(value);
        }
    }

    private void updateX(Stage stage, double x, double value) {
        double max = stage.getMaxWidth();
        double min = stage.getMinWidth();

        if ((min <= value && value <= max)) {
            stage.setX(x);
        }
    }

    private void updateY(Stage stage, double y, double value) {
        double max = stage.getMaxHeight();
        double min = stage.getMinHeight();

        if ((min <= value && value <= max)) {
            stage.setY(y);
        }
    }

    private void mousePressed(MouseEvent me, Stage stage) {
        initX = me.getScreenX() - stage.getX();
        initY = me.getScreenY() - stage.getY();
        //

        stageX = stage.getX();
        stageY = stage.getY();

        stageWidth = stage.getWidth();
        stageHeight = stage.getHeight();

//        dragOffsetX = (stage.getX() + stage.getWidth()) - me.getScreenX();
//        dragOffsetY = (stage.getY() + stage.getHeight()) - me.getScreenY();
        me.consume();
    }

    private Cursor getCursor(MouseEvent me, Scene scene, Stage stage) {
        Cursor cursor = Cursor.DEFAULT;

        double grp = 10;
        double width = scene.getWidth();
        double height = scene.getHeight();

        double x = me.getX();
        double y = me.getY();

        if (!stage.isResizable() || isMaximized()) {
            type = isMaximized() ? -1 : 0;
            return cursor;
        }
        if (x < grp && y < grp) {
            cursor = Cursor.SE_RESIZE;
            //System.out.println("1");
            type = 1;
        } else if (x > (width - grp) && y < grp) {
            cursor = Cursor.SW_RESIZE;
            // System.out.println("3");
            type = 2;
        } else if (x < grp && y > (height - grp)) {
            cursor = Cursor.SW_RESIZE;
            // System.out.println("2");
            type = 3;
        } else if (x > (width - grp) && y > (height - grp)) {
            cursor = Cursor.SE_RESIZE;
            //  System.out.println("4");
            type = 4;
        } else if (x < grp) {
            cursor = Cursor.H_RESIZE;
            // System.out.println("5");
            type = 5;
        } else if (x > (width - grp)) {
            cursor = Cursor.H_RESIZE;
            // System.out.println("6");
            type = 6;
        } else if (y < grp) {
            cursor = Cursor.V_RESIZE;
            // System.out.println("7");
            type = 7;
        } else if (y > (height - grp)) {
            cursor = Cursor.V_RESIZE;
            // System.out.println("8");
            type = 8;
        } else {
            type = 0;
        }
        // System.out.println("x=" + x + "y=" + y);
        return cursor;
    }


    public void toogleMaximized() {
        final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
        if (isMaximized()) {
            maximized = false;
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX());
                stage.setY(backupWindowBounds.getMinY());
                stage.setWidth(backupWindowBounds.getWidth());
                stage.setHeight(backupWindowBounds.getHeight());
            }
        } else {
            maximized = true;
            backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            stage.setX(screen.getVisualBounds().getMinX());
            stage.setY(screen.getVisualBounds().getMinY());
            stage.setWidth(screen.getVisualBounds().getWidth());
            stage.setHeight(screen.getVisualBounds().getHeight());
        }
    }

    public boolean isMaximized() {
        return maximized;
    }

}
