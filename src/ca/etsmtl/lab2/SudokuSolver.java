package ca.etsmtl.lab2;

import java.io.File;
import java.io.FileInputStream;

public class SudokuSolver {
	private byte[][] grid;
	private boolean hasSolution;
	
	public SudokuSolver() {
		this.grid = new byte[9][9];
		this.hasSolution = false;
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
	
	private boolean hasSolution() {
		return this.hasSolution;
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
	
	public boolean isValid(int line, int column, int k){
		if(grid[line][column] == 0){	
			for (int i = 0; i <= 8; i++) {
				if(grid[line][i] == k){
					return false;
				}
				if(grid[i][column] == k){
					return false;
				}
				if(grid[(line/3) * 3 + i/3][(column/3) * 3 + i%3] == k){
					return false;
				}
			}
		}else{
			return false;
		}
		
		return true;
	}
	
	public void solveSudoku(int line, int column){
		if(grid[line][column] != 0){
			if(column<8){
				solveSudoku(line, column+1);
			}else if(line<8){
				solveSudoku(line+1, 0);
			}else{
				hasSolution = true;
			}
		}else{
			for(int i = 1; i<10; i++){
				if(this.isValid(line, column, i)){
					this.grid[line][column] = (byte)i;
					if(column<8){
						solveSudoku(line, column+1);
					}else if(line<8){
						solveSudoku(line+1, 0);
					}else{
						hasSolution = true;
					}
				}
				if(!hasSolution){
					this.grid[line][column] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		CalculateTime ct = new CalculateTime();
		SudokuSolver ss = new SudokuSolver();
		
		ct.startTimer();
		ss.readFile("sudoku4.txt");
		
		ss.solveSudoku(0,0);
		ct.endTimer();
		
		if(ss.hasSolution()){
			System.out.println("Solution found:");
			ss.printGrid();
		}else{
			System.out.println("No solution found");
		}
	}
}
