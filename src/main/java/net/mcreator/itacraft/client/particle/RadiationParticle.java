package net.mcreator.itacraft.client.particle;

public class RadiationParticle extends TextureSheetParticle {
	public static RadiationParticleProvider provider(SpriteSet spriteSet) {
		return new RadiationParticleProvider(spriteSet);
	}

	public static class RadiationParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public RadiationParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new RadiationParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected RadiationParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.lifetime = 7;
		this.gravity = 0f;
		this.hasPhysics = true;
		this.xd = vx * 1;
		this.yd = vy * 1;
		this.zd = vz * 1;
		this.pickSprite(spriteSet);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
	}
}