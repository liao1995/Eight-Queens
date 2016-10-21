package cn.hit.edu.liao.example;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Population of Eight Queens Board
 * @author LIAO
 * Date: 2016.10.21
 * Github: http://github.com/liao1995
 */
public class Population {
	private TreeSet<Chrom> pop;
	public Population() { pop = new TreeSet<Chrom>(); }
	public TreeSet<Chrom> getPop() { return pop; }
	/**
	 * Get the total fitness of this population.
	 * @return an integer value represent total fitness
	 */
	public int getTotFitness() {
		int sumFitness = 0;
		Iterator<Chrom> it = pop.iterator();
		while (it.hasNext()) sumFitness += it.next().fitness();
		return sumFitness;
	}
	
	/**
	 * Get the average fitness of this population
	 * @return an double value represent average fitness
	 */
	public double getAverageFitness() {
		return getTotFitness() / pop.size();
	}
	
	public int getBestFitness() { return pop.first().fitness(); }
	public Chrom getBestChrom() { return pop.first(); }
}
