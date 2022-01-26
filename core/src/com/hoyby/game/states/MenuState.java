package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoyby.game.MyPongGame;

public class MenuState extends State {

    private final BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();


    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyPongGame.WIDTH,MyPongGame.HEIGHT);
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1);
        glyphLayout.setText(font, "Press anywhere to play");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
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
        font.draw(sb, glyphLayout, (MyPongGame.WIDTH - glyphLayout.width)/2, (MyPongGame.HEIGHT - glyphLayout.height) / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
