package cn.hit.edu.liao.example;

import java.util.BitSet;

/**
 * Chromosome definition, e.g. A state of board eight queens placed
 * Eight Queens Puzzle By Genetic Algorithm
 * @author LIAO
 * Date: 2016.10.21
 * Github: http://github.com/liao1995
 */
public class Chrom implements Comparable<Chrom>{
	private BitSet chromo;
	public static final int BITS = 3;	// number of bits to represent a number 
	private int size;
	public Chrom(int size) { this.size = size; chromo = new BitSet(size); }
	public Chrom() { this(24); }
	public BitSet getChromo() { return chromo; }
	public int size() { return this.size; }
	
	/**
	 * Check queens of two rows attacked each other or not
	 * @return True if attacked, false otherwise
	 */
	private boolean _attack(int row1, int col1, int row2, int col2) {
		if (col1==col2 || Math.abs(col1-col2)==Math.abs(row1-row2)) 
			return true;
		return false;
	}
	
	/**
	 * Convert chrom string to integer array stands for all column indexes
	 * @return integer array, for example:
	 * if chrom is: 000 001 010 011 100 101 110 111, this method will return
	 * its decimal integer array: 0 1 2 3 4 5 6 7
	 */
	public int[] toArray() {
		int numRows = size / BITS;
		int[] arr = new int[numRows];
		for (int i = 0; i < numRows; i++) 
			for (int j = 0; j < BITS; ++j)
				arr[i] += (chromo.get(i*BITS+j)?(int)Math.pow(2,BITS-j-1):0);
		return arr;
	}
	
	/**
	 * Calculate chromo's fitness, that is: number of queens's couples 
	 * do not attack each other
	 * @return the fitness of this chromo
	 */
	public int fitness() {
		int[] arr = toArray();
		int numRows = arr.length, fit = 0;
		for (int i = 0; i < numRows; ++i) 
			for (int j = i + 1; j < numRows; ++j)
				fit += _attack(i, arr[i], j, arr[j]) ? 0 : 1;
		return fit;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < size; ++i) {
			str += chromo.get(i) ? "1" : "0";
			if (i % 3 == 2) str += " ";
		}
		return str;
	}
	
	@Override
	public int compareTo(Chrom o) {
		int res = fitness() - o.fitness();
		if ( res != 0 ) return -res;
		return toString().compareTo(o.toString());
	}
}
