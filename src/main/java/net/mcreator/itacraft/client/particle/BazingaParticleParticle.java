package net.mcreator.itacraft.client.particle;

public class BazingaParticleParticle extends TextureSheetParticle {
	public static BazingaParticleParticleProvider provider(SpriteSet spriteSet) {
		return new BazingaParticleParticleProvider(spriteSet);
	}

	public static class BazingaParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public BazingaParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new BazingaParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;
	private float angularVelocity;
	private float angularAcceleration;

	protected BazingaParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.quadSize *= 3.5f;
		this.lifetime = 7;
		this.gravity = 0f;
		this.hasPhysics = true;
		this.xd = vx * 1.5;
		this.yd = vy * 1.5;
		this.zd = vz * 1.5;
		this.angularVelocity = 0.2f;
		this.angularAcceleration = 0.1f;
		this.pickSprite(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		this.oRoll = this.roll;
		this.roll += this.angularVelocity;
		this.angularVelocity += this.angularAcceleration;
	}
}