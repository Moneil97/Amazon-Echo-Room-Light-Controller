package room;

enum Colors {
	
	white(255,255,255), black(0,0,0), red(255,0,0), green(0,255,0), blue(0,0,255);
	
	int r,g,b;
	
	Colors(int red, int green, int blue){
		r = red;
		g = green;
		b = blue;
	}
	
}
