package com.expiredcode.ld.fastypizza;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;



public class Level extends GameScreen
{
	int WORLD_WIDTH = 800, WORLD_HEIGHT = 800;
	//int WORLD_WIDTH = 800, WORLD_HEIGHT = 480;
	
	boolean done = false;
	boolean mobile = false;
	boolean ord,bri,nyy;
	MyUtils m = new MyUtils();
	
	Fasty player;
	Car auto;
	String str;
	int cont = 0,pizze=0,money=0,meh=0;
	
	SpriteBatch batch;
	OrthographicCamera camera;
	
	Texture a,fastty,coll,car,order,bring,n,menu;
	
	Music song, pew;
	Sound yay,nay;
	
	float angolo=0;
	float speed=125;
	float dist;
	float spawn =0;
	float time,naytime;
	float pos = 20;
	Collider c1,c2,ordery,bringy;
	
	BitmapFont font;
	
	Vector2 pr = new Vector2(0,0);
	Vector3 touchpos;
	
	Iterator it;
	Init i;

	Level()
	{	
		i= new Init();
	
		//Gdx.app.log("a", "1");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		
		batch = new SpriteBatch();
		
		a = new Texture(Gdx.files.internal("data/a.png"));
		coll = new Texture(Gdx.files.internal("data/c.png"));
		
		order = new Texture(Gdx.files.internal("data/ORDER.png"));
		ordery = new Collider("order",355,0,86,42);
		ord = true;
		bring = new Texture(Gdx.files.internal("data/BRING.png"));
		bringy = new Collider("bring",0,0,39,15);
		bri = false;
		
		fastty = new Texture(Gdx.files.internal("data/fasty.png"));
		Sprite spri = new Sprite(fastty,0,0,5,10);
		spri.setOrigin(spri.getWidth()/2, spri.getHeight()/2);
		player = new Fasty(spri,40,20);		
		c2 = new Collider("player",player.s.getX(),player.s.getY(),5,5);
		
		car = new Texture(Gdx.files.internal("data/car.png"));
		Sprite carr = new Sprite(car,0,0,10,19);
		auto = new Car(carr,1,7);
		//carr.setOrigin(originX, originY)
		m.carlist.add(auto);		
		m.carlist.add(new Car(new Sprite(car,0,0,10,19),21,7));
		m.carlist.add(new Car(new Sprite(car,0,0,10,19),81,7));
		m.carlist.add(new Car(new Sprite(car,0,0,10,19),51,7));
		m.carlist.add(new Car(new Sprite(car,0,0,10,19),121,7));
		m.carlist.add(new Car(new Sprite(car,0,0,10,19),321,7));

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.scale(0.5f);
		
		song = Gdx.audio.newMusic(Gdx.files.internal("data/song.ogg"));
		song.setLooping(true);
		song.setVolume(0.5f);
		song.play();
		
		pew = Gdx.audio.newMusic(Gdx.files.internal("data/motorino.ogg"));
		pew.setVolume(1f);
		//pew.setLooping(true);
		yay =  Gdx.audio.newSound(Gdx.files.internal("data/15.ogg"));
		nay =  Gdx.audio.newSound(Gdx.files.internal("data/3.ogg"));
		nyy = false;
		n = new Texture(Gdx.files.internal("data/n.png"));
		
		menu = new Texture(Gdx.files.internal("data/ho.png"));
	}					
	
	
	@Override
	public void render(float deltaTime) 
	{
		updateScene(deltaTime);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
				
		batch.begin();		
		batch.draw(a, 0, 0,800,800);
		
		if(pos<800)
		{
			batch.draw(menu, 100, pos);
			pos+=100*deltaTime;
		}
		
		if(ord)
			batch.draw(order, 355, 0);
		if(bri)
			batch.draw(bring,bringy.x-57,bringy.y);
		
		
		it = m.carlist.iterator();  //world hitbox
		while(it.hasNext())
		{
			auto = (Car) it.next();
			auto.draw(batch);						
		}
		
		font.draw(batch, ""+money, 750, 730);
		font.draw(batch, ""+pizze, 750, 690);
		font.draw(batch, ""+meh, 750, 645);
		
		//str = String.format("%.2f", time);
		if(bri)
			font.draw(batch, ""+Math.round(time), bringy.x, bringy.y);
		if(nyy)
			batch.draw(n, bringy.x, bringy.y);
		
		if(pos<800)
		{
			batch.draw(menu, 100, pos);
			pos+=80*deltaTime;
		}
		
		player.draw(batch);
		batch.end();
	}
	
	void updateScene(float deltaTime)
	{
		/*if(mobile)
		{
			for(int j = 0;j<5;j++)
			{
				if(Gdx.input.isTouched(j))	
				{	
			
				}
			}
		}else{*/
		if(Gdx.input.isTouched())	
		{	
			//Gdx.app.log("touch", "x: "+Gdx.input.getX()+" y: "+(WORLD_HEIGHT-Gdx.input.getY()));
			/*
			Sprite carr = new Sprite(car,0,0,10,19);
			m.carlist.add(new Car(carr,1,7));*/
		}
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			player.turnLeft(deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			player.turnRight(deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.W))      
		{
			if(!pew.isPlaying())
				pew.play();
			player.moveForward(deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE))      
		{
			player.breaking(deltaTime);
		}
	//	}
		collisions();		
		player.update(deltaTime);
		it = m.carlist.iterator();  //world hitbox
		while(it.hasNext())
		{
			auto = (Car) it.next();
			auto.update(deltaTime);
		}
		
		if(spawn>5)
		{
			Sprite carr = new Sprite(car,0,0,10,19);
			m.carlist.add(new Car(carr,1,7));
			spawn = 0;
		}else
			spawn+=deltaTime;
		
		if(time>0)
		{
			time-=deltaTime;
		}
		//Gdx.app.log("l", ""+time);		
	}
	
	void collisions()
	{
		it = m.world.iterator();
		while(it.hasNext())
		{
			c1 = (Collider) it.next();			
		
			//Gdx.app.log("col", "COLLISION!!");
		
			if(Ray2Box.R2BCollision(c1, player.ray))
			{
				player.currentSpeed = 0;		
				if(c1.name.equals("T4"))
				{
					player.s.setPosition(2, 765);
				/*	player.angle +=180;
					if(player.angle<0) player.angle+=360;
					if(player.angle>360) player.angle-=360;*/
				}
				if(c1.name.equals("T3"))
				{
					player.s.setPosition(1, 7);			
				}
				if(c1.name.equals("T2"))
				{
					player.s.setPosition(790, 789);				
				}
				if(c1.name.equals("T1"))
				{
					player.s.setPosition(793, 398);
				}
			}
		}
		
		it = m.carlist.iterator();
		while(it.hasNext())
		{
			auto = (Car) it.next();
			if(Ray2Box.R2BCollision(auto.c, player.ray))
			{
				player.currentSpeed = 0;
			}
		}
		
		if(ord)
		if(Ray2Box.R2BCollision(ordery, player.ray))
			{
				ord = false;
				nyy = false;
				bri = true;
				bring();
				time = 0.03f * (float)Math.sqrt((ordery.x-bringy.x)*(ordery.x-bringy.x)+(ordery.y-bringy.y)*(ordery.y-bringy.y));
			}
		if(bri)
		{	if(Ray2Box.R2BCollision(bringy, player.ray))
			{
				bri = false; 
				ord = true;
				nyy = false;
				pizze++;
				money+=5+(int)(Math.random()*10);				
				yay.play();
				time = 0;
			}
			if(time<0)
			{
				bri = false; 
				ord = true;
				nyy = true;
				meh++;								
				nay.play();
				time = 0;
			}
		}
	}
	
	void bring()
	{
		switch(cont)
		{
			case 0:{
				bringy.x = 83;
				bringy.y = 44+ (int)(Math.random()*700);
				cont++;
			}break;
			case 1:{
				bringy.x = 318;
				bringy.y = 44+ (int)(Math.random()*700);
				cont++;
			}break;
			case 2:{
				bringy.x = 83;
				bringy.y = 44+ (int)(Math.random()*700);
				cont++;
			}break;
			case 3:{
				bringy.x = 435;
				bringy.y = 44+ (int)(Math.random()*700);
				cont++;
			}break;
			case 4:{
				bringy.x = 83;
				bringy.y = 44+ (int)(Math.random()*700);
				cont=0;
			}break;
		}
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return done;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		 a.dispose();
		 fastty.dispose();
		 coll.dispose();
		 car.dispose();
		 order.dispose();
		 bring.dispose();
		 n.dispose();
		 song.dispose();
		 pew.dispose();
		 yay.dispose();
		 nay.dispose();
		 font.dispose();
		 batch.dispose();
	}

}
