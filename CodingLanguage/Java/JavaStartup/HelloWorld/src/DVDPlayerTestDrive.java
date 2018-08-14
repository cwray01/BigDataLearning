
public class DVDPlayerTestDrive {

	public static void main(String [] args) {
	
	DVDPlayer d = new DVDPlayer();
	d.canRecord = true;
	d.playDVD();  //缺失此条方法函数
	
	if (d.canRecord == true){
		d.recordDVD();
	}
	}
}
	
