package com.tolentsgames.entities;

import java.awt.image.BufferedImage;

import com.tolentsgames.main.Game;
import com.tolentsgames.world.World;

public class TowerController extends Entity {
	
	public boolean isPressed = false;
	public int xTarget, yTarget;

	public TowerController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(isPressed) {
			isPressed = false;
			boolean liberado = true;
			//criar torre no mapa
			int xx = (xTarget/16)*16;
			int yy = (yTarget/16)*16;
			Player player = new Player(xx, yy, 16, 16, 0, Entity.TOWER);
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if( e instanceof Player) {
					if(Entity.isColidding(e, player)) {
						liberado = false;
						// já tem torre na posição
					}
				} 
			}
			
			if(World.isFree(xx, yy)) {
				liberado = false;
			}
			
			if(liberado) {
				if(Game.dinheiro >= 2) {
					Game.entities.add(player);
					Game.dinheiro -= 2;
					} else {
						//sem dinheiro para torres
				}
			}
		}
		if(Game.vida <= 0) {		
			//Game Over
			System.exit(1);
		}
	}
}
