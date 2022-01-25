package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {


    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, 0,0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
//        sb.draw(playBtn, cam.position.x - (playBtn.getWidth() / 2), (cam.position.y));
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
