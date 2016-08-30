//Mock world object to test the PocketWatch rightclick method
package com.example.examplemod;

public class Worldfake {

	//PocketWatch variables
	int time;


	public Worldfake(int time) //time must be between 0 (6am) and 23000(24 hours later)
	{
		this.time=time;
	}


	public int getWorldTime() //returns the time value
	{
		return time;
	}

}