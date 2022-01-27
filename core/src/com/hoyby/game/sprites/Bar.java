package com.hoyby.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hoyby.game.MyPongGame;

import java.util.Random;

public class Bar {

    private final ShapeRenderer bar;
    private final int barWidth = 100;
    private final int barHeight = 15;
    private final Vector2 position;
    private int points;
    private int computerRandomAim;

    public Bar(int y) {
        computerRandomAim = new Random().nextInt(150) - 75;
        points = 0;
        bar = new ShapeRenderer();
        position = new Vector2((MyPongGame.WIDTH / 2f) - (barWidth / 2f), y - barHeight / 2f);
    }

    public int getPoints() {
        return points;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return barWidth;
    }

    public int getHeight() {
        return barHeight;
    }

    public void addPoint() {
        points++;
    }

    public void setX(float x) {
        if (x < barWidth / 2f){
            position.x = 0;
        }else if(x > MyPongGame.WIDTH - barWidth / 2f){
            position.x = MyPongGame.WIDTH - barWidth;
        }else{
            position.x = x - barWidth / 2f;
        }
    }

    public void newRandomAim(){
        computerRandomAim = new Random().nextInt(75) - 75/2;
    }

    public void moveTowards(float x, long speed) {
        double multiplierFunction = 2 * (Math.log(speed / 90f) / Math.log(2));
        if ((x > 0) && (x < MyPongGame.WIDTH - barWidth / 2f)) {
            if ((x - barWidth / 2f) + computerRandomAim > position.x) {
                if (position.x + multiplierFunction > MyPongGame.WIDTH - barWidth) {
                    position.x = MyPongGame.WIDTH - barWidth;
                }else{
                    position.x += multiplierFunction;
                }
            } else if ((x - barWidth / 2f) + computerRandomAim < position.x) {
                if (position.x - multiplierFunction < 0) {
                    position.x = 0;
                }else {
                    position.x -= multiplierFunction;
                }
            }
        }
    }

    public void render(SpriteBatch sb){
        bar.setProjectionMatrix(sb.getProjectionMatrix());
        bar.begin(ShapeRenderer.ShapeType.Filled);
        bar.rect(position.x, position.y, barWidth, barHeight);
        bar.end();
    }

    public void dispose() {
        bar.dispose();
    }

    public void resetPoints() {
        points = 0;
    }
}
