package BouncingBall;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

public class BouncingBall extends GraphicsApp {

    /* Konstanten für Canvas-Aufbau */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final int BALL_RADIUS = 15;
    private static final int BALL_DIAMETER = 2 * BALL_RADIUS;
    private static final float SPEED_REDUCTION_BOUNCE = 0.9f;
    private static final float X_SPEED = 1;
    private static final float INITIAL_YSPEED = 0;
    private static final float GRAVITY = 0.15f;

    /* Private Instanz-Variablen */
    private Circle ball;
    private float dx; /* Geschwindigkeits-Delta in X-Richtung */
    private float dy; /* Geschwindigkeits-Delta in Y-Richtung */

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupCanvas();
        setupBall();
    }

    private void setupBall() {
        ball = new Circle(0, 0, BALL_DIAMETER, Colors.RED);
        // Set the initial speed for the ball
        dx = X_SPEED;
        dy = INITIAL_YSPEED;
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        if (ball.getXPos() + BALL_RADIUS < getWidth()) {
            moveBall();
            checkForCollision();
        }
    }

    // Aktualisiere, bewege und zeichne den Ball
    private void moveBall() {
        dy += GRAVITY;
        ball.move(dx, dy);
        ball.draw();
    }

    /*
     * Bestimme, ob eine Kollision mit dem Boden stattfindet.
     * Wenn ja, aktualisiere die Geschwindigkeit und Ort des Balls.
     */
    private void checkForCollision() {
        // Falls der Ball den Boden trifft
        if (ball.getYPos() + BALL_RADIUS > getHeight()) {
            // Kehre y-Richtung um, um nach oben zu springen.
            // Reduziere dabei die Geschwindigkeit.
            dy = -dy * SPEED_REDUCTION_BOUNCE;

            // Wir nehmen an, der Ball bewegt sich die Strecke nach oben
            // um die er sich unter den Boden bewegt hätte.
            float diff = ball.getYPos() - (getHeight() - BALL_RADIUS);
            ball.move(0, -2 * diff);
        }
    }
}