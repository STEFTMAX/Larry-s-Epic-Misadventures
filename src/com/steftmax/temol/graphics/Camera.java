package com.steftmax.temol.graphics;

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
