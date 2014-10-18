package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sessionstraps.game_engine.entity.Entity;
import com.sessionstraps.game_engine.entity.enemy.Enemy;
import com.sessionstraps.game_engine.resources.SpriteManager;

public class AxisZeroPoint extends Entity implements Enemy{

	public AxisZeroPoint(SpriteManager sm) {
		super(sm);
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, 0);
	}

}