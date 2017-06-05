import java.awt.Point;

public class Truck {

	private double rotation, speed;
	private Point[] pontos;
	private Point pos;
	protected double stepSize;
	public static int c = 40, l = 10, nPontos = 8;
	public static double maxAxisAngle = 30; // angulo maximo para virar o volante em um passo

	public Truck(int x, int y,double stepSize, double rot) {
		this.stepSize = stepSize;
		speed = 20*stepSize;
		rotation = rot;
		pos = new Point();
		pontos = new Point[nPontos];
		this.setPos(x, y);
	}

	/**
	 * Atualiza os pontos do caminhao ao mudar de posicao.
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		pos.x = x;
		pos.y = y;
		double sin = Math.sin((rotation / 180) * Math.PI);
		double cos = Math.cos((rotation / 180) * Math.PI);

		pontos[0] = new Point((int) (x + l * sin), (int) (y + l * cos));
		pontos[1] = new Point((int) (x - l * sin), (int) (y - l * cos));
		pontos[2] = new Point((int) (x - c * cos - l * sin),
				(int) (y + c * sin - l * cos));//
		pontos[3] = new Point((int) (x - c * cos + l * sin),
				(int) (y + c * sin + l * cos));
		pontos[4] = new Point(pontos[0].x, pontos[0].y);

		sin = Math.sin(((rotation + 60) / 180) * Math.PI);
		cos = Math.cos(((rotation + 60) / 180) * Math.PI);
		pontos[5] = new Point((int) (x + l * sin), (int) (y + l * cos));

		sin = Math.sin(((rotation - 60) / 180) * Math.PI);
		cos = Math.cos(((rotation - 60) / 180) * Math.PI);
		pontos[6] = new Point((int) (x - l * sin), (int) (y - l * cos));

		pontos[7] = new Point(pontos[1].x, pontos[1].y);
	}

	public Point getPos() {
		return pos;
	}

	// Manobra virando o volante conforme o axisRot, que vai de -1 a 1
	// 1 para virar todo o volante para direita e -1 pra esquerda
	// o angulo maximo do volante e definido por maxAxisAngle
	public void stepManeuver(double axisRot) {
		
		axisRot *= stepSize;
		
		double sin = Math.sin(((-axisRot * maxAxisAngle + rotation) / 180)
				* Math.PI);
		double cos = Math.cos(((-axisRot * maxAxisAngle + rotation) / 180)
				* Math.PI);

		int dx = (int) (pos.x - cos * speed);
		int dy = (int) (pos.y + sin * speed);
		rotation = axisRot * maxAxisAngle + rotation;
		if (rotation < 0)
			rotation += 360;
		if (rotation > 360)
			rotation -= 360;
		this.setPos(dx, dy);
	}

	public void stepManeuver(double axisRot, int speed) {
		if (speed < 10)
			speed = 10;
		this.speed = speed;
		stepManeuver(axisRot);
	}

	public Point getPoint(int index) {
		return pontos[index];
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

}
