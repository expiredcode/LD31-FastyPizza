package com.expiredcode.ld.fastypizza;

public class Init 
{
	MyUtils m = new MyUtils();
	
	Init()
	{
		m.world.add(new Collider("World",0,44,76,713));
		m.world.add(new Collider("World",121,44,190,324));
		m.world.add(new Collider("World",356,44,76,324));
		m.world.add(new Collider("World",591,44,76,713));
		m.world.add(new Collider("World",120,411,76,344));
		m.world.add(new Collider("World",238,411,76,344));
		m.world.add(new Collider("World",355,411,76,344));
		m.world.add(new Collider("World",474,0,76,757));//
		m.world.add(new Collider("World",708,0,76,369));
		m.world.add(new Collider("World",708,411,76,344));
		m.world.add(new Collider("WorldBorder",0,798,800,50));
		m.world.add(new Collider("WorldBorder",0,-53,800,50));
		m.world.add(new Collider("T1",-10,-5,8,60));
		m.world.add(new Collider("T2",-10,755,8,60));
		m.world.add(new Collider("T3",798,365,8,60));
		m.world.add(new Collider("T4",798,745,8,60));
		
		m.carstop.add(new Collider("L1",454,-10,24,34));
		m.carstop.add(new Collider("L2",454,775,24,24));		
		m.carstop.add(new Collider("C2",454,750,24,24));
		m.carstop.add(new Collider("C1",337,-10,24,34));
		m.carstop.add(new Collider("C2",337,750,24,24));
		m.carstop.add(new Collider("L2",337,775,24,24));
		m.carstop.add(new Collider("C1",103,-10,24,34));
		m.carstop.add(new Collider("C2",103,750,24,24));
		m.carstop.add(new Collider("L2",103,775,24,24));
		m.carstop.add(new Collider("C2",220,750,24,24));
		m.carstop.add(new Collider("L2",220,775,24,24));
		m.carstop.add(new Collider("C2",337,367,24,24));
		m.carstop.add(new Collider("C3",337,385,24,24));
		m.carstop.add(new Collider("C3",454,385,24,24));
		m.carstop.add(new Collider("L2",572,775,24,24));		
		m.carstop.add(new Collider("C2",572,750,24,24));
		m.carstop.add(new Collider("L2",689,775,24,24));		
		m.carstop.add(new Collider("C2",689,750,24,24));
		m.carstop.add(new Collider("C2",689,367,24,24));
		m.carstop.add(new Collider("C2",103,367,24,24));
		m.carstop.add(new Collider("L1",689,-10,24,34));
		m.carstop.add(new Collider("L1",454,370,24,24));
		m.carstop.add(new Collider("C4",660,765,22,34));
		m.carstop.add(new Collider("C4",555,765,12,34));
		m.carstop.add(new Collider("C4",437,765,12,34));
		m.carstop.add(new Collider("C4",318,765,12,34));
		m.carstop.add(new Collider("C4",200,765,12,34));
		m.carstop.add(new Collider("C4",83,775,12,34));
		m.carstop.add(new Collider("C4",83,390,12,34));
		m.carstop.add(new Collider("R1",665,23,30,12));
		m.carstop.add(new Collider("R1",430,23,30,12));
		m.carstop.add(new Collider("C5",318,24,31,12));
		m.carstop.add(new Collider("C5",83,24,30,12));
		m.carstop.add(new Collider("C5",318,391,31,12));
		m.carstop.add(new Collider("C5",435,391,31,12));
		m.carstop.add(new Collider("C5",200,391,31,12));
		m.carstop.add(new Collider("C6",82,757,24,24));
		m.carstop.add(new Collider("C6",200,750,24,40));
		m.carstop.add(new Collider("C6",318,750,24,40));
		m.carstop.add(new Collider("C6",435,750,24,40));
		m.carstop.add(new Collider("C6",553,750,24,40));
		m.carstop.add(new Collider("C6",670,750,24,40));
		m.carstop.add(new Collider("C6",316,370,24,40));
		m.carstop.add(new Collider("C6",435,370,24,40));
		m.carstop.add(new Collider("L3",75,0,41,12));
		m.carstop.add(new Collider("L3",550,0,30,12));
		m.carstop.add(new Collider("L3",308,0,41,12));
		m.carstop.add(new Collider("L4",665,387,12,31));
		m.carstop.add(new Collider("R2",570,23,12,31));
		m.carstop.add(new Collider("C7",339,23,12,31));
		m.carstop.add(new Collider("C7",102,23,12,31));
		m.carstop.add(new Collider("C7",690,389,12,31));
		m.carstop.add(new Collider("C7",220,389,12,31));
		m.carstop.add(new Collider("C7",103,389,12,31));
		m.carstop.add(new Collider("L3",190,370,30,12));
		
	}

}
