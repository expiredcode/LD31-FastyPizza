package com.expiredcode.ld.fastypizza;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Fasty 
{
	int x,y;
	float speed=55,angleSpeed=120,angle=0;
	float currentSpeed = 0;
	Vector2 ray= new Vector2(0,0);
	
	Collider c;
	
	Sprite s;
	
	Fasty(Sprite s,int x, int y)
	{
		this.s = s;
		this.x=x;
		this.y=y;
		ray.x = s.getX()+(float)(10*Math.cos(angle*MathUtils.degreesToRadians));
		ray.y = s.getY()+(float)(10*Math.sin(angle*MathUtils.degreesToRadians));
	}
	
	public void update(float deltaTime)
	{
		if(currentSpeed>0)
		{						//Trovo la direzione della navicella usando l'angolo 
			s.setPosition(s.getX()+(float)((currentSpeed*deltaTime)*Math.cos(angle*MathUtils.degreesToRadians)),
					s.getY()+(float)((currentSpeed*deltaTime)*Math.sin(angle*MathUtils.degreesToRadians)));
			
			currentSpeed -= 150*deltaTime;			
		}
		if(currentSpeed<20) currentSpeed = 0;		
		
		ray.x = s.getX()+(float)(10*Math.cos(angle*MathUtils.degreesToRadians));
		ray.y = s.getY()+(float)(10*Math.sin(angle*MathUtils.degreesToRadians));
	}
	
	public void moveForward(float deltaTime)
	{
		currentSpeed = speed;
	}
	
	public void breaking(float deltaTime)
	{
		if(currentSpeed>0)
		{
			currentSpeed -= 500*deltaTime;	
		}
		if(currentSpeed<20)
			currentSpeed = 0;
	}
	
	public void turnLeft(float deltaTime)
	{
		this.angle+=angleSpeed*deltaTime;
		if(this.angle>360) this.angle -= 360;
	}
	
	public void turnRight(float deltaTime)
	{
		this.angle-=angleSpeed*deltaTime;
		if(this.angle<0) this.angle += 360;
	}
	
	public void draw(SpriteBatch b)
	{
		s.rotate(angle-90);
		s.draw(b);
		s.rotate(-(angle-90));
	}

}
