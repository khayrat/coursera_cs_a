/******************************************************************************
 * Name:
 * NetID:
 * Precept:
 *
 * Description: Upgraded version of BouncingBall.java, as seen in Lecture 4D.
 ******************************************************************************/

public class BouncingBallDeluxe { 
    public static void main(String[] args) {

        // initial values for a single ball
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity - between .01 and .03
        double radius = 0.05;              // radius of image
        
        // initialize standard drawing
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        // main animation loop
        while (true)  {
            
            // bounce off wall according to law of elastic collision
            if (Math.abs(rx + vx) + radius > 1.0) {
                vx = -vx;
                StdAudio.play("pipebang.wav");
            }
            if (Math.abs(ry + vy) + radius > 1.0)  {
                vy = -vy;
                StdAudio.play("pipebang.wav");
            }

            // update position - velocity is constant
            rx = rx + vx; 
            ry = ry + vy; 

            // set the background to light gray
            StdDraw.clear(StdDraw.LIGHT_GRAY);

            // draw ball on the screen
            StdDraw.picture(rx, ry, "TennisBall.png");

            // display and pause for 20 ms
            StdDraw.show();
            StdDraw.pause(20);
        } 
    } 
} 
