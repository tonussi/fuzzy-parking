class Server {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver1;
	private Driver driver2;
	private Quadro quadro1;
	private Quadro quadro2;
	private int x, y;
	private double angulo;

	Server() {
		quadro1 = new Quadro();
		quadro2 = new Quadro();
		Gui gui = new Gui(quadro1,quadro2, this);
		gui.setVisible(true);
	}

	public void drive(double stepSize) {
		if(driver1!=null)
			driver1.stopSock();
		if(driver2!=null)
			driver2.stopSock();
		
		driver1 = new Driver(quadro1,x, y,stepSize, angulo,4321);
		driver1.start();
		driver2 = new Driver(quadro2,x, y,stepSize, angulo,4322);
		driver2.start();
	}

	void finalizar() {
		System.exit(0);
	}

	public static void main(String[] args) {
		new Server();
	}

	public void setTruckPosition(String x, String y, String angulo) {
		this.x = (int) (Double.parseDouble(x)*quadro1.getWidth());
		this.y = (int) (Double.parseDouble(y)*quadro1.getHeight());
		this.angulo = Double.parseDouble(angulo);
	}

}
