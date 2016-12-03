package com.expiredcode.ld.fastypizza;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Home extends GameScreen{
	
	int WORLD_WIDTH = 800, WORLD_HEIGHT = 800;

	SpriteBatch batcher;
	float x,y;
	boolean done=false,end=false,slider=false;
	Texture menu;
	float enddelta=0;
	float slide = 0;
	
	MyUtils myu=new MyUtils();
	
	OrthographicCamera camera;

	
	Home()
	{
		menu = new Texture(Gdx.files.internal("data/ho.png"));
		batcher = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
	}
	

	@Override
	public void render(float deltatime)
	{
		update(deltatime);
		// tell the camera to update its matrices.
		camera.update();  
	
		// tell the SpriteBatch to render in the coordinate system specified by the camera.
		batcher.setProjectionMatrix(camera.combined);
		if(slider)
		if(end&&enddelta>0.5)
			done=true;
		else
			enddelta+=deltatime;
		
		//setting up draw queue
		batcher.begin();
		batcher.draw(menu, 0, slide);
		batcher.end();
	}
	
	void update(float deltatime){
		if(Gdx.input.isTouched())
		{
					myu.screen=1;
					end=true;			
		}		
		if(end)
		{
			slide+=200*deltatime;
			if(slide>0)
			{
				slide = 0;
				slider = true;
			}
		}
	}
	@Override
	public void dispose()
	{
		menu.dispose();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		
		return done;
	}

}
