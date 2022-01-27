package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoyby.game.MyPongGame;

public class WinState extends State {

    private final BitmapFont gameOver = new BitmapFont();
    private final BitmapFont finalScore = new BitmapFont();
    private final BitmapFont pressAnywhere = new BitmapFont();
    private final GlyphLayout gameOverGlyph = new GlyphLayout();
    private final GlyphLayout finalScoreGlyph = new GlyphLayout();
    private final GlyphLayout pressAnywhereGlyph = new GlyphLayout();
    private final PlayState playState;


    public WinState(GameStateManager gsm, PlayState playState) {
        super(gsm);
        cam.setToOrtho(false, MyPongGame.WIDTH, MyPongGame.HEIGHT);
        this.playState = playState;

        gameOverGlyph.setText(gameOver, "Game over!");
        finalScoreGlyph.setText(gameOver, "Final score:");
        pressAnywhereGlyph.setText(pressAnywhere, "Restart?");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.pop();
            playState.resetScores();
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
        gameOver.draw(sb, gameOverGlyph, (MyPongGame.WIDTH - gameOverGlyph.width) / 2, (MyPongGame.HEIGHT - gameOverGlyph.height) / 2 + 100);
        finalScore.draw(sb, finalScoreGlyph, (MyPongGame.WIDTH - finalScoreGlyph.width) / 2, (MyPongGame.HEIGHT - finalScoreGlyph.height) / 2 + 75);
        playState.getScoreText().draw(sb, playState.getScoreGlyphLayout(), (MyPongGame.WIDTH - playState.getScoreGlyphLayout().width) / 2, (MyPongGame.HEIGHT - playState.getScoreGlyphLayout().height) / 2 + 50);
        pressAnywhere.draw(sb, pressAnywhereGlyph, (MyPongGame.WIDTH - pressAnywhereGlyph.width) / 2, (MyPongGame.HEIGHT - pressAnywhereGlyph.height) / 2 - 50);
        sb.end();
    }

    @Override
    public void dispose() {
        gameOver.dispose();
        pressAnywhere.dispose();
        finalScore.dispose();
    }
}
