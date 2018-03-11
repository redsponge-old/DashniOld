package com.redsponge.platformer.world.entity.enemy;

import com.redsponge.platformer.handler.Handler;
import com.redsponge.platformer.state.StateManager;
import com.redsponge.platformer.utils.MathUtils;
import com.redsponge.platformer.world.BoundingBox;
import com.redsponge.platformer.world.entity.*;
import com.redsponge.platformer.world.entity.player.EntityPlayer;

public abstract class AbstractEnemy extends AbstractLivingEntity implements IDamager {

	protected float currentX;
	protected float currentY;
	protected EnemyPropertyMap propertyMap;
	
	protected boolean hasBeenOnGround;
	
	public AbstractEnemy(Handler handler, int x, int y, int width, int height) {
		super(handler, x, y, width, height);
		isGravityApplied = true;
		speedX = 3;
		direction = Facing.RIGHT;
		updateCurrentPosition();
		propertyMap = new EnemyPropertyMap();
		propertyMap.canBeStomped = true;
		propertyMap.damage = 2;
		hasBeenOnGround = false;
	}
	
	public void tick() {
	    boundingBox.tick();
		move();
        //moveY(boundingBox);
		tickGravity();
		tickPlayer();
		tickSunkInBlock();
	}
	
	private void turn() {
		speedX *= -1;
		direction = direction.getOpposite();
	}

	public void tickSunkInBlock() {
		if(touchingBlocks(boundingBox) && onTopOf != null) {
			y = onTopOf.getBoundingBox().getTop() - height;
		}
		updateOnGround(StateManager.getLevelState().getWorldBlocks());
	}

	public void tickPlayer() {
        EntityPlayer player = StateManager.getLevelState().getPlayer();
        if(MathUtils.twoRectCollision(boundingBox.asRectangle(), player.getBoundingBox().asRectangle())){
            if(playerOnMe(this, player) && propertyMap.canBeStomped) {
                this.kill(KillCause.KillCauseCreator.generate(KillCause.EnumKillType.STOMP, this, player));
            } else {
                player.hurt(HurtCause.HurtCauseCreator.generate(HurtCause.EnumHurtType.ENEMY, this, player));
            }
        }
    }

	@Override
	public int getStrength() {
		return propertyMap.damage;
	}

	public void kill(KillCause killCause) {
	    switch(killCause.getKillType().getId()) {
            case "stomp":
                KillCause.KillStomp cause = (KillCause.KillStomp) killCause;
                if(propertyMap.bouncePlayerOnKill) {
                    cause.getPlayer().jump(false);
                }
                break;
        }
        StateManager.getLevelState().getWorldEnemies().remove(uuid);
    }


	public void moveX(BoundingBox box) {
		if(touchingBlocks(box, false)) {
            tickSunkInBlock();
			if(onGround) {
				turn();
			}
		}
        if(x <= 0 - handler.getCameraManager().getOffsetX()) {
            turn();
        }
		x += speedX;
	}

	public void updateCurrentPosition() {
		currentX = x;
		currentY = y;
	}

	public static boolean playerOnMe(AbstractEnemy enemy, EntityPlayer player) {
	    if(MathUtils.twoRectCollision(enemy.getBoundingBox().asRectangle(), player.getBoundingBox().asRectangle())) {
	        if(player.isFalling()) {
                return true;
            }
        }
        return false;
    }

	public float getCurrentX() {
		return currentX;
	}
	
	public float getCurrentY() {
		return currentY;
	}
}
