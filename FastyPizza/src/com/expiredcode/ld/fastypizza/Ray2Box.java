package com.expiredcode.ld.fastypizza;

import com.badlogic.gdx.math.Vector2;

public class Ray2Box 
{
	public static boolean R2BCollision(Collider a,Vector2 b)	//RAYS FTW!!
	{
		if(a.x<b.x && a.x+a.width>b.x)
			if(a.y<b.y && a.y+a.height>b.y)
				return true;
		
		return false;
	}

}
