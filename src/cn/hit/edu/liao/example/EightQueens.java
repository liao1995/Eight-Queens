package cn.hit.edu.liao.example;

/**
 * Main class, Eight Queens Puzzle using Genetic Algorithm
 * @author liao
 * Date: 2016.10.21
 * Github: http://github.com/liao1995
 */
public class EightQueens {
	
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		// create initial population
		Population pop = GeneticAlgorithm.geneRandPop();
		for (int i = 0; i < GeneticAlgorithm.MAXGENE; ++i) {
			System.out.println("Generation " + (i+1));
			Population newPop = GeneticAlgorithm.epoch(pop);
			Chrom best = newPop.getBestChrom();
			System.out.println("Best individual: "+best+ " fitness: "+best.fitness());
			System.out.println("Average fitness: " + pop.getAverageFitness() + "\n");
			pop = newPop;
		}
	}
}
