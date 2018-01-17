package com.redsponge.platformer.world.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.redsponge.platformer.handler.Handler;
import com.redsponge.platformer.world.BoundingBoxUser;

public class BoundingBox {
	
	private float x, y;
	private int width, height;
	
	@SuppressWarnings("unused")
	private Handler handler;
	private BoundingBoxUser user;
	
	public BoundingBox(Handler handler, BoundingBoxUser user) {
		this.x = user.getX();
		this.y = user.getY();
		this.width = user.getWidth();
		this.height = user.getHeight();
		this.handler = handler;
		this.user = user;
	}
	
	public void tick() {
		this.x = user.getX();
		this.y = user.getY();
		this.width = user.getWidth();
		this.height = user.getHeight();
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(4));
		g2.drawRect((int) x, (int) y, width, height);
	}
	
	public int getLeft() {
		return (int) x;
	}
	
	public int getTop() {
		return (int) y;
	}
	
	public int getRight() {
		return (int) x + width;
	}
	
	public int getBottom() {
		return (int) y + height;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
}
