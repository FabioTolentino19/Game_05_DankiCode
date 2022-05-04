package com.tolentsgames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;


public class Player extends Entity {
	
	public int xTarget, yTarget;
	public boolean atacando = false;

	public Player(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
		
	public void tick() {
		Enemy enemy = null;
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.calculateDistance(this.getX(), this.getY(), e.getX(), e.getY()) < 40) {
					enemy = (Enemy) e;
				}
			}
		}
		
		if(enemy != null) {
			atacando = true;
			xTarget = enemy.getX();
			yTarget = enemy.getY();
			if(Entity.rand.nextInt(100) < 30)
				enemy.vida -= Entity.rand.nextDouble();
		} else {
			atacando = false;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		// Mostrar ataque as torres
		if(atacando) {
			g.setColor(Color.red);
			g.drawLine((int)x+8, (int)y+8, xTarget+8, yTarget+8);
		}
	}
	
}
	
