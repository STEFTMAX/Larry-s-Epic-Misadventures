package com.steftmax.larrys_epic_misadventures.draw;

/**
 * @author pieter3457
 *
 */
public interface Camera {
	public void beginFocus();
	public void endFocus();
	
	public float getScale();
	public float getX();
	public float getY();
}
