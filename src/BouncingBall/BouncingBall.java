package BouncingBall;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;

public class BouncingBall extends GraphicsApp {

    /* Private constants */
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

    /* Private instance variables */
    private Circle ball; /* The ball object */
    private float dx; /* Velocity delta in the x direction */
    private float dy; /* Velocity delta in the y direction */

    /*
     * This method is called once when the program is started.
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
     * This method is called repeatedly while the program is running.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        if (ball.getXPos() + BALL_RADIUS < getWidth()) {
            moveBall();
            checkForCollision();
        }
    }

    // Update, move and draw the ball
    private void moveBall() {
        dy += GRAVITY;
        ball.move(dx, dy);
        ball.draw();
    }

    /*
     * Determine if collision with floor, update velocities and location as
     * appropriate.
     */
    private void checkForCollision() {
        // if ball hits the floor
        if (ball.getYPos() + BALL_RADIUS > getHeight()) {
            // reverse y-direction to bounce upwards and reduce speed
            dy = -dy * SPEED_REDUCTION_BOUNCE;

            // assume bounce will move ball an amount above the
            // floor equal to the amount it would have dropped
            // below the floor.
            float diff = ball.getYPos() - (getHeight() - BALL_RADIUS);
            ball.move(0, -2 * diff);
            ball.draw();
        }
    }
}