package cn.hit.edu.liao.example;

import java.util.Iterator;
import java.util.Random;

/**
 * Genetic Algorithm to solve Eight Queens Puzzle
 * @author LIAO
 * Date: 2016.10.21
 * Github: http://github.com/liao1995
 */
public class GeneticAlgorithm {
	
	public static final int MAXGENE = 100;		// Maximum generations
	public static final int POPSIZE = 100;		// Population size
	public static final double CROSPROB = 0.8;	// Probability of crossover
	public static final double MUTPROB = 0.05;	// Probability of mutation
	
	/**
	 * Generate a chromo randomly
	 * @return new chromo
	 */
	public static Chrom randomGenerate() {
		Chrom c = new Chrom();
		Random gen = new Random();
		for (int i = 0;i < c.size(); ++i)
			c.getChromo().set(i, gen.nextInt(2) == 1);
		return c;
	}
	
	/**
	 * Do mutation with probability mutProb
	 * @return true if occur mutation, false otherwise
	 */
	public static boolean mutate(Chrom c) {
		boolean isMutate = false;
		int size = c.size();
		for (int i = 0; i < size; ++i) 
			if (Math.random() < MUTPROB){
				isMutate = true;
				c.getChromo().set(i, (int)(Math.random() * 1000) % 2 == 1);
			}
		return isMutate;
	}
	
	/**
	 * Generate initial population of size POPSIZE randomly
	 * @return population
	 */
	public static Population geneRandPop() {
		Population pop = new Population();
		for (int i = 0; i < POPSIZE; ++i)
			pop.getPop().add(randomGenerate());
		return pop;
	}
	
	/**
	 * Do crossover between two parent chromos.
	 * @param c1 parent chromo 1
	 * @param c2 parent chromo 2
	 * @param cp cross point
	 * @return child chromo who has larger fitness  
	 */
	public static Chrom crossover(Chrom c1, Chrom c2, int cp) {
		Chrom child1 = new Chrom(), child2 = new Chrom();
		int i = 0, size = c1.size();
		for (; i < cp; ++i) {
			child1.getChromo().set(i, c1.getChromo().get(i));
			child2.getChromo().set(i, c2.getChromo().get(i));
		}
		for (; i < size; ++i) {
			child2.getChromo().set(i, c1.getChromo().get(i));
			child1.getChromo().set(i, c2.getChromo().get(i));
		}
		return child1.fitness() > child2.fitness() ? child1 : child2;
	}
	
	/**
	 * Select a chromo from population based on Roulette Wheel Selection Algotithm
	 * @param Pop population of chromo
	 * @return Selected chromo
	 */
	public static Chrom selection(Population pop) {
		int totFitness = pop.getTotFitness(), cum = 0;
		Iterator<Chrom> it = pop.getPop().iterator();
		int sel = (int) ( totFitness * Math.random() );
		Chrom c = null;
		while (it.hasNext()) {
			c = it.next();
			cum += c.fitness();
			if ( cum >= sel ) break;
		}
		return c;
	}
	
	/**
	 * An epoch which from old population generate new population
	 * @param oldPop old population
	 * @return new population
	 */
	public static Population epoch(Population oldPop) {
		Population newPop = new Population();
		for (int i = 0; i < POPSIZE; ++i) {
			Chrom child = null;
			// selection
			Chrom parent1 = selection(oldPop);
			Chrom parent2 = selection(oldPop);
			// crossover
			if (Math.random() < CROSPROB) 
				child = crossover(parent1, parent2, (int)(Math.random() * parent1.size()));
			// if not crossover, select best one in parent
			if (null == child) child=parent1.fitness()>parent2.fitness()?parent1:parent2;
			// mutation
			if (Math.random() < MUTPROB) mutate(child);
			newPop.getPop().add(child);
		}
		return newPop;
	}
	
}
