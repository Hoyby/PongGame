package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PlayState extends State{

    private final BitmapFont score;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, 0, 0);

        score = new BitmapFont();
        score.getData().setScale(1.8f);
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        score.draw(sb, "Score", cam.position.x - (cam.viewportWidth / 2) + 20, cam.position.y + (cam.viewportHeight / 2) - 20);
        sb.end();
    }

    @Override
    public void dispose() {
        score.dispose();
    }
}
