import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.lang.Math.*;
import java.io.PrintStream;


public class FileProcess {
	 private static int countAlphabet;
	
	public static void main(String[] args) {

	}
	
	public FileProcess(File file) {
		try {
			int[] size = getSize(file);
			int row = size[0];
			int col = size[1];
			double totalTime = 0;
			char[][] wordMatrix = new char[row][col];
			int cntRow = 0;
			boolean activate = false;
            // Save original out stream.
            PrintStream originalOut = System.out;
            // Create a new file output stream.
            PrintStream fileOut = new PrintStream("./out.txt");
            // Redirect standard out to file.
            System.setOut(fileOut);
			File myObj = file;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data == "") {
					activate = true;
				}
				else if (!activate) {
					int cntCol = 0;
					for (int i = 0; i < data.length(); i++) {
						if (data.charAt(i) != ' ') {
							wordMatrix[cntRow][cntCol] = data.charAt(i);
							cntCol += 1;
						}
					}
				}
				else if (activate) {
					//start process for the search
					FileProcess.countAlphabet = 0;
					double getTime = searchWord(data, wordMatrix, row, col);
					totalTime += getTime;
					System.out.print("Total perbandingan huruf: ");
					System.out.println(FileProcess.countAlphabet);
					System.out.println();
				}
				cntRow += 1;
			}
			myReader.close();
			System.out.print("Total Waktu Eksekusi: ");
			System.out.print(totalTime);
			System.out.println(" ms");
	        System.setOut(originalOut);
	        ShowResult result = new ShowResult();
	        result.setVisible(true);
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	public static int[] getSize(File fileOpened) throws FileNotFoundException {
		//size[0] = row, size[1] = col
		int[] size = new int[2];
		int row = 0;
		int col = 0;
		boolean notStop = true;
		File myObj = fileOpened;
		Scanner myReader = new Scanner(myObj);
		while ((myReader.hasNextLine()) && (notStop)) {
			String data = myReader.nextLine();
			if (data == "") {
				notStop = false;
			}
			else {
				col = data.length()/2 + 1;
				row += 1;
			}
		}
		myReader.close();
		size[0] = row;
		size[1] = col;
		return size;
	}
	
	public static double searchWord(String word, char[][] wordMatrix, int row, int col) {
		long endTime = 0;
		boolean found = false;
		int i = 0;
		long startTime = System.nanoTime();
		while ((i < row) && !found) {
			int j = 0;
			while ((j < col) && !found) {
				FileProcess.countAlphabet += 1;
				if (word.charAt(0) == wordMatrix[i][j]) {
					//check for all possible word in 8 direction
					if (up(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"up");
					}
					else if(down(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"down");
					}
					else if (right(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"right");
					}
					else if (left(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"left");
					}
					else if (upRight(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"upright");
					}
					else if (upLeft(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"upleft");
					}
					else if (downRight(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"downright");
					}
					else if (downLeft(word,wordMatrix,row,col,i,j)) {
						found = true;
						FileProcess.countAlphabet -= 1;
						endTime = System.nanoTime();
						//cetak jawaban
						printWord(word,row,col,i,j,"downleft");
					}
				}				
				j += 1;
			}
			i += 1;
		}
		if (!found) {
			System.out.println("Kata Tidak Ditemukan");
			return 0;
		}
		else {
			long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
			double dur = (double) duration/1000000.0;
			System.out.print(dur);
			System.out.println(" ms");
			return dur;
		}
	}
	
	public static boolean up(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		if (word.length()-1 > rowStart) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart-cnt][colStart]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean down(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		if (word.length() > (row-rowStart)) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart+cnt][colStart]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean right(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		if (word.length() > (col-colStart)) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart][colStart+cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean left(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		if (word.length()-1 > colStart) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart][colStart-cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean upRight(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		int cmpr = Math.min(rowStart+1,col-colStart);
		if (word.length() > cmpr) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart-cnt][colStart+cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean upLeft(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		int cmpr = Math.min(rowStart+1,colStart+1);
		if (word.length() > cmpr) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart-cnt][colStart-cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean downRight(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		int cmpr = Math.min(row-rowStart,col-colStart);
		if (word.length() > cmpr) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart+cnt][colStart+cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static boolean downLeft(String word, char[][] wordMatrix, int row, int col, int rowStart, int colStart) {
		boolean found = true;
		int cmpr = Math.min(row-rowStart,colStart+1);
		if (word.length() > cmpr) {
			return !found;
		}
		else {
			int  cnt = 0;
			while ((cnt < word.length()) && found) {
				if (word.charAt(cnt) != wordMatrix[rowStart+cnt][colStart-cnt]) {
					found = false;
				}
				cnt += 1;
			}
			FileProcess.countAlphabet += cnt;
		}
		return found;
	}

	public static void printWord(String word, int row, int col, int rowStart, int colStart, String keyword) {
		char[][] newMatrix = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				newMatrix[i][j] = '-';
			}
		}
		int cnt = 0;
		if (keyword == "up") {
			while (cnt < word.length()) {
				newMatrix[rowStart-cnt][colStart] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "down") {
			while (cnt < word.length()) {
				newMatrix[rowStart+cnt][colStart] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "right") {
			while (cnt < word.length()) {
				newMatrix[rowStart][colStart+cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "left") {
			while (cnt < word.length()) {
				newMatrix[rowStart][colStart-cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "upright") {
			while (cnt < word.length()) {
				newMatrix[rowStart-cnt][colStart+cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "upleft") {
			while (cnt < word.length()) {
				newMatrix[rowStart-cnt][colStart-cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "downright") {
			while (cnt < word.length()) {
				newMatrix[rowStart+cnt][colStart+cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		else if (keyword == "downleft") {
			while (cnt < word.length()) {
				newMatrix[rowStart+cnt][colStart-cnt] = word.charAt(cnt);
				cnt += 1;
			}
		}
		//print matrix
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(newMatrix[i][j]);
				if (j != col-1) {
					System.out.print(" ");					
				}
			}
			System.out.println();
		}
		
	}
}
