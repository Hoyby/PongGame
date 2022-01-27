package com.hoyby.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoyby.game.MyPongGame;
import com.hoyby.game.sprites.Ball;
import com.hoyby.game.sprites.Bar;


public class PlayState extends State{

    private final BitmapFont scoreText = new BitmapFont();
    private final GlyphLayout scoreGlyphLayout = new GlyphLayout();
    private final Bar playerBar;
    private final Bar computerBar;
    private Ball ball;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, MyPongGame.WIDTH, MyPongGame.HEIGHT);

        playerBar = new Bar(75);
        computerBar = new Bar(MyPongGame.HEIGHT - 75);

        scoreGlyphLayout.setText(scoreText, "Player: " + playerBar.getPoints() + "\nComputer " + computerBar.getPoints());

        ball = new Ball();
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            if (!ball.isMoving()) {
                ball.startBall();
            }
            playerBar.setX(Gdx.input.getX());
            Gdx.input.getY();
        }
    }

    public GlyphLayout getScoreGlyphLayout() {
        return scoreGlyphLayout;
    }

    public BitmapFont getScoreText() {
        return scoreText;
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (ball.ballHitTop()) {
            playerBar.addPoint();
            scoreGlyphLayout.setText(scoreText, "Player: " + playerBar.getPoints() + "\nComputer " + computerBar.getPoints());
            ball = new Ball();
            if (playerBar.getPoints() >= 21) {
                gsm.push(new WinState(gsm, this));
            }else{
                gsm.push(new RoundOverState(gsm, this));
            }

        }else if (ball.ballHitBottom()) {
            computerBar.addPoint();
            scoreGlyphLayout.setText(scoreText, "Player: " + playerBar.getPoints() + "\nComputer " + computerBar.getPoints());
            ball = new Ball();
            if (computerBar.getPoints() >= 21) {
                gsm.push(new WinState(gsm, this));
            } else {
                gsm.push(new RoundOverState(gsm, this));
            }
        } else {
            ball.update(dt);
            computerBar.moveTowards(ball.getX(), ball.getSpeed());
            ball.collision(playerBar);
            ball.collision(computerBar);
        }
    }

    public void resetScores(){
        playerBar.resetPoints();
        computerBar.resetPoints();
        scoreGlyphLayout.setText(scoreText, "Player: " + playerBar.getPoints() + "\nComputer " + computerBar.getPoints());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        scoreText.draw(sb, scoreGlyphLayout, 10,MyPongGame.HEIGHT - 10);
        sb.end();
        playerBar.render();
        computerBar.render();
        ball.render();

    }

    @Override
    public void dispose() {
        scoreText.dispose();
        playerBar.dispose();
        computerBar.dispose();
        ball.dispose();
    }
}
