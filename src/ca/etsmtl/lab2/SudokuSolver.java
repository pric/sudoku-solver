package ca.etsmtl.lab2;

import java.io.File;
import java.io.FileInputStream;

public class SudokuSolver {
	private byte[][] grid;
	
	public SudokuSolver() {
		this.grid = new byte[9][9];
	}
	
	public void readFile(String path) {
		File file = new File(path);
		int byteRead = 0;
		int byteCount = 0;
		
		if(!file.exists() || file.isDirectory())
			throw new IllegalArgumentException("File does not exist or file is directory.");
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			while((byteRead = fileInputStream.read()) != -1){
				byte number = (byte) Character.getNumericValue(byteRead);
				if(number >=0 && number<=9){
					this.grid[byteCount/9][byteCount%9] = number;
					byteCount++;
				}
			}
			fileInputStream.close();
			if(byteCount!=9*9) throw new Exception("Bad file format");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void printGrid(){
		int row = 0;
		for (byte[] bs : this.grid) {
			int column = 0;
			for (byte b : bs) {
				if((column+1)%3 == 0){
					System.out.print(b + "  ");
				}else{
					System.out.print(b + " ");
				}
				column++;
			}
			if((row+1)%3 == 0){
				System.out.println();
				System.out.println();
			}else{
				System.out.println();
			}
			row++;
		}
	}
	
	public void isValid(byte x, byte y, byte k){
		if(grid[x][y] == 0){
			
		}
	}
	
	public void solveSudoku(){
		
	}
	
	public static void main(String[] args) {
		SudokuSolver ss = new SudokuSolver();
		ss.readFile("sudoku.txt");
		ss.printGrid();
	}
}
