package com.expiredcode.ld.fastypizza;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Car 
{
	MyUtils m = new MyUtils();
	Iterator it;
	int x,y;
	float speed=40,angleSpeed=150,angle=0,objangle;
	float currentSpeed = 0;
	double a;
	boolean goLeft = false,goRight=false;
	float delta,deltaTurn=0;
	float w,h;
	Vector2 ray= new Vector2(0,0);
	
	Collider c,c1;
	
	Car auto;
	
	Sprite s;
	
	Car(Sprite s,int x, int y)
	{
		this.s = s;
		this.s.setPosition(x, y);
		w = s.getWidth();
		h = s.getHeight();
		c = new Collider("car",x,y,w,h);
		ray.x = s.getX()+(float)(10*Math.cos(angle*MathUtils.degreesToRadians));
		ray.y = s.getY()+(float)(10*Math.sin(angle*MathUtils.degreesToRadians));
	}
	
	public void update(float deltaTime)
	{
		if(goLeft)
		{
			turnLeft(deltaTime);
		}
		if(goRight)
		{
			turnRight(deltaTime);
		}
		
		moveForward(deltaTime);
		breaking(deltaTime);
		collisions(deltaTime);
		if(currentSpeed>0)
		{						//Trovo la direzione della navicella usando l'angolo 
			s.setPosition(s.getX()+(float)((currentSpeed*deltaTime)*Math.cos(angle*MathUtils.degreesToRadians)),
					s.getY()+(float)((currentSpeed*deltaTime)*Math.sin(angle*MathUtils.degreesToRadians)));
			
			currentSpeed -= 150*deltaTime;			
		}
		if(currentSpeed<20) currentSpeed = 0;		
		
		/*ray.x = s.getX()+(float)(10*Math.cos(angle*MathUtils.degreesToRadians));
		ray.y = s.getY()+(float)(10*Math.sin(angle*MathUtils.degreesToRadians));*/
		ray.x = (s.getX()-c.width/4)+(float)(10*Math.cos(angle*MathUtils.degreesToRadians));
		ray.y = (s.getY()+c.height/4)+(float)(10*Math.sin(angle*MathUtils.degreesToRadians));
		//Gdx.app.log("carpos", "x"+ray.x+" y"+ray.y);
		
		if(angle==90 || angle == 270)
		{
			c.x = s.getX(); c.y=s.getY();
			c.width=w;
			c.height=h;
		}
		if(angle==180 || angle == 0 || angle == 360)
		{
			c.x = s.getX()-w/4; c.y=s.getY()+h/4;
			c.width=h;
			c.height=w;
		}
		deltaTurn+=deltaTime;
	}
	
	public void moveForward(float deltaTime)
	{
		currentSpeed = speed;
	}
	
	public void breaking(float deltaTime)
	{
		/*it = m.carlist.iterator();
		while(it.hasNext())
		{
			auto = (Car) it.next();
			
			if(Ray2Box.R2BCollision(auto.c, ray))
			{
				currentSpeed = 0;
			}
		}		*/
		
	}
	
	public void turnLeft(float deltaTime)
	{
		this.angle+=angleSpeed*deltaTime;
		if(this.angle>360) this.angle -= 360;
		if(objangle-15<angle && objangle+15>angle)
		{
			angle = objangle;
			goLeft = false;
			delta = 0;
		}
			
	}
	
	public void turnRight(float deltaTime)
	{
		this.angle-=angleSpeed*deltaTime;
		if(this.angle<0) this.angle += 360;
		if(objangle-15<angle && objangle+15>angle)
		{
			angle = objangle;
			goRight = false;
			delta=0;
		}
	}
	
	public void draw(SpriteBatch b)
	{
		s.rotate(angle-90);
		s.draw(b);
		s.rotate(-(angle-90));
	}
	
	void collisions(float deltaTime)
	{
		it = m.cars.iterator();
		while(it.hasNext())			//TODO fix the car collider with the angle
		{
			c1 = (Collider) it.next();
			if(Ray2Box.R2BCollision(c1, this.ray))
			{
				breaking(deltaTime);
			}
		}
		
		it = m.world.iterator();
		while(it.hasNext())
		{
			c1 = (Collider) it.next();
			if(Ray2Box.R2BCollision(c1, this.ray))
			{
				//currentSpeed = 0;	
				//Gdx.app.log("collision", "x"+ray.x+" y"+ray.y);
				if(c1.name.equals("T4"))
				{
					s.setPosition(2, 760);			
				}
				if(c1.name.equals("T3"))
				{//Gdx.app.log("collision", "t3!!");
					s.setPosition(2,2);		
				}
				if(c1.name.equals("T2"))
				{
					s.setPosition(790, 783);			
				}
				if(c1.name.equals("T1"))
				{
					s.setPosition(793, 398);			
				}
			}
		}
		
		it = m.carstop.iterator();
		while(it.hasNext())
		{//Gdx.app.log("L1", "x"+ray.x+" y"+ray.y);
			c1 = (Collider) it.next();
			if(!goLeft&&!goRight&&(deltaTurn>2))
			if(Ray2Box.R2BCollision(c1, ray))
			{//Gdx.app.log("L1", "tuurr");				
				if(c1.name.equals("L1"))
				{
					//Gdx.app.log("L1", "tuuuurn");
					objangle = 90;
					goLeft=true;
					deltaTurn=0;
				}
				if(c1.name.equals("L2")&& angle == 90)
				{
					//Gdx.app.log("L2", "tuuuurn");
					objangle = 180;
					goLeft=true;
					deltaTurn=0;
				}
				if(c1.name.equals("C2")&& angle == 90)
				{
					//Gdx.app.log("L2", "tuuuurn");
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
							
					if(a>1)
					{
						objangle = 0;
						goRight=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}
				if(c1.name.equals("C1")&& angle == 0)
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
							
					if(a>1)
					{
						objangle = 90;
						goLeft=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}
				if(c1.name.equals("C3")&& angle == 90)
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
							
					if(a>1)
					{
						objangle = 180;
						goLeft=true;				
						deltaTurn=0;
					}
					delta+=deltaTime;
				}

				if(c1.name.equals("C4")&& angle == 180)
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
					//Gdx.app.log("c4", "touch");
					if(a<1)
					{
						objangle = 270;
						goLeft=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}

				if(c1.name.equals("C5")&& angle == 270)//going down and turn right
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
							
					if(a>1)
					{
						objangle = 180;
						goRight=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}

				if(c1.name.equals("C6")&& angle == 0)//going right and turn right
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("c6", "touch");
							
					if(a<1)
					{
						objangle = 270;
						goRight=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}

				if(c1.name.equals("L3")&& angle == 270)//going down and turn left
				{
					
					objangle = 0;
					goLeft=true;
					deltaTurn=0;
				}

				if(c1.name.equals("R1")&& angle == 270)//going down and turn right
				{
					
					objangle = 180;
					goRight=true;
					deltaTurn=0;
				}
				if(c1.name.equals("L4")&& angle == 180)//going left and turn left
				{
					
					objangle = 270;
					goLeft=true;
					deltaTurn=0;
				}
				if(c1.name.equals("R2")&& angle == 180)//going left and turn right
				{
					
					objangle = 90;
					goRight=true;
					deltaTurn=0;
				}
				if(c1.name.equals("C7")&& angle == 180)//going left and turn right?
				{
					
					if(delta==0)
					 a =Math.random()*2; //Gdx.app.log("rand", "rand "+a);
							
					if(a>1)
					{
						objangle = 90;
						goRight=true;
						deltaTurn=0;
					}
					delta+=deltaTime;
				}
			}
		}
		
	}

}
