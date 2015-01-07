package com.steftmax.larrys_epic_misadventures.entity;

import java.awt.Rectangle;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.physics.Point;
import com.steftmax.larrys_epic_misadventures.physics.Vector2F;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public abstract class Entity extends LevelObject implements Drawable, Updatable {

	public final Vector2F lastPos, newPos, velocity;
	protected float HP;
	protected final int mass, maxHP;
	protected Texture drawingTexture;
	protected Rectangle hitbox;
	protected TiledMap map;
	

	public Entity(TiledMap map, float x, float y, final int mass, final int maxHP) {
		this.map = map;
		this.HP = maxHP;
		this.maxHP = maxHP;
		this.mass = mass;
		newPos = new Vector2F(x, y);
		lastPos = new Vector2F(x, y);
		velocity = new Vector2F(0,0);
		hitbox = new Rectangle(0, 0, 0, 0);
		
	}

	public Rectangle getHitbox() {
		int width = drawingTexture.width;
		int height = drawingTexture.height;

		hitbox.setBounds((int) newPos.x, (int) newPos.y - height, width, height);

		return hitbox;
	}

	public void onCollide(Entity collideEnt) {
		// TODO
	}
	
	

	
//	public void applyForce(float x, float y) {
//		//F = m * a
//		//a = F / m
//		
////		last_acceleration = acceleration
////		position += velocity * time_step + ( 0.5 * last_acceleration * time_step^2 )
////		new_acceleration = force / mass 
////		avg_acceleration = ( last_acceleration + new_acceleration ) / 2
////		velocity += avg_acceleration * time_step
//	}
}