package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoyby.game.MyPongGame;

public class RoundOverState extends State {

    private final BitmapFont gameOver = new BitmapFont();
    private final BitmapFont pressAnywhere = new BitmapFont();
    private final GlyphLayout gameOverGlyph = new GlyphLayout();
    private final GlyphLayout pressAnywhereGlyph = new GlyphLayout();
    private final PlayState playState;


    public RoundOverState(GameStateManager gsm, PlayState playState) {
        super(gsm);
        cam.setToOrtho(false, MyPongGame.WIDTH, MyPongGame.HEIGHT);
        this.playState = playState;
        gameOverGlyph.setText(gameOver, "Round over!");
        pressAnywhereGlyph.setText(pressAnywhere, "Press anywhere to continue playing");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.pop();
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
        playState.getScoreText().draw(sb, playState.getScoreGlyphLayout(), 10, MyPongGame.HEIGHT - 10);
        gameOver.draw(sb, gameOverGlyph, (MyPongGame.WIDTH - gameOverGlyph.width) / 2, (MyPongGame.HEIGHT - gameOverGlyph.height) / 2);
        pressAnywhere.draw(sb, pressAnywhereGlyph, (MyPongGame.WIDTH - pressAnywhereGlyph.width) / 2, (MyPongGame.HEIGHT - pressAnywhereGlyph.height) / 2 - 50);
        sb.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        pressAnywhere.dispose();
    }
}