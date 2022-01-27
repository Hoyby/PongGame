package com.hoyby.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.hoyby.game.MyPongGame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private final ShapeRenderer ball;
    private final Vector2 velocity;
    private final Vector2 position;
    private final int radius = 10;
    private int speed;
    private boolean moving;
    private long startTime = 0;

    public Ball() {
        moving = false;
        speed = 300;
        ball = new ShapeRenderer();
        startTime = TimeUtils.nanoTime();
        position = new Vector2(MyPongGame.WIDTH / 2f, MyPongGame.HEIGHT / 2f);
        velocity = new Vector2(0, 0);
    }

    public void startBall(){
        velocity.y = -speed;
        velocity.x = new Random().nextBoolean() ? -speed * 0.5f : speed * 0.5f;
        moving = true;
    }

    public boolean isMoving() {
        return moving;
    }

    public float getX() {
        return position.x;
    }

    public int getSpeed() {
        return speed;
    }

    public Boolean ballHitTop(){
        return position.y > MyPongGame.HEIGHT - radius;
    }

    public Boolean ballHitBottom() {
        return position.y < radius;
    }

    public void BounceOnWallHit(){
        if(position.x > MyPongGame.WIDTH - radius) {
            velocity.x = -Math.abs(velocity.x);
        }
        if(position.x < radius) {
            velocity.x = Math.abs(velocity.x);
        }
    }

    public void collision(Bar bar) {
        float barCenter = bar.getPosition().x + bar.getWidth() / 2f;
        if (position.y - radius < bar.getPosition().y + bar.getHeight() && position.y + radius > bar.getPosition().y) {
            if (position.x + radius > bar.getPosition().x && position.x - radius < bar.getPosition().x + bar.getWidth()) {

                // check which side the bar is on
                if (bar.getPosition().y + bar.getHeight() > MyPongGame.HEIGHT / 2f){
                    velocity.y = 3 * -speed;
                    velocity.x = 1.5f * (position.x - barCenter) * (speed / (bar.getWidth() / 2f));
                }else{
                    velocity.y = speed;
                    velocity.x = (position.x - barCenter) * (speed / (bar.getWidth() / 2f));
                }
                bar.newRandomAim();
            }
        }
    }

    public void update(float dt) {
        if (position.y > 0) {
            velocity.add(0, 0);
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y);
        BounceOnWallHit();
        velocity.scl(1 / dt);

        if (TimeUtils.timeSinceNanos(startTime) > 1000000000) {
            speed += 10;
            startTime = TimeUtils.nanoTime();
        }
    }

    public void render(SpriteBatch sb){
        ball.setProjectionMatrix(sb.getProjectionMatrix());
        ball.begin(ShapeRenderer.ShapeType.Filled);
        ball.circle(position.x, position.y, radius);
        ball.end();
    }

    public void dispose(){
        ball.dispose();
    }

}
