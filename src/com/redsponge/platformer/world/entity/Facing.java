package com.redsponge.platformer.world.entity;

public enum Facing {
	RIGHT("right"),
	LEFT("left");
	
	private String id;
	
	private Facing(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
