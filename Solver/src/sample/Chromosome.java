package sample;

public class Chromosome {


    private int genes[] = new int[Main.GENES_COUNT];

    private float fitness;

    private float likelihood;

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public float getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(float likelihood) {
        this.likelihood = likelihood;
    }


    public float calculateFitness() {
        int a = genes[0];
        int b = genes[1];
        int c = genes[2];
        int d = genes[3];

        int closeness = Math.abs(Main.TARGET_VALUE - Main.function(a, b, c, d));

        return 0 != closeness ? 1 / (float) closeness : Main.TARGET_IS_REACHED_FLAG;
    }


    public Chromosome mutateWithGivenLikelihood() {

        Chromosome result = (Chromosome) this.clone();

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            float randomPercent = Main.getRandomFloat(0, 100);
            if (randomPercent < Main.MUTATION_LIKELIHOOD) {
                int oldValue = result.getGenes()[i];
                int newValue = Main.getRandomGene();

                Main.log("Performing mutation for gene #" + i
                        + ". ( randomPercent ==" + randomPercent
                        + " ). Old value:" + oldValue + "; New value:" + newValue);
                result.getGenes()[i] = newValue;

            }
        }
        return result;
    }

    public Chromosome[] doubleCrossover(Chromosome chromosome) {

        int crossoverline = getRandomCrossoverLine();
        Chromosome[] result = new Chromosome[2];
        result[0] = new Chromosome();
        result[1] = new Chromosome();

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            if (i <= crossoverline) {
                result[0].getGenes()[i] = this.getGenes()[i];
                result[1].getGenes()[i] = chromosome.getGenes()[i];

            } else {
                result[0].getGenes()[i] = chromosome.getGenes()[i];
                result[1].getGenes()[i] = this.getGenes()[i];
            }

        }

        return result;

    }


    public Chromosome singleCrossover(Chromosome chromosome) {
        Chromosome[] children = doubleCrossover(chromosome);
        int childNumber = Main.getRandomInt(0, 1);
        return children[childNumber];
    }


    public boolean equals(Chromosome chromosome) {

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            if (this.genes[i] != chromosome.genes[i]) {
                return false;
            }
        }
        return true;

    }


    public String toString() {

        StringBuffer result = new StringBuffer();

        result.append("Genes: (");

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            result.append("" + genes[i]);
            result.append(i < Main.GENES_COUNT - 1 ? ", " : "");

        }

        result.append(")\n");

        result.append("Fitness:" + fitness + "\n");
        result.append("Likelihood:" + likelihood + "\n");


        return result.toString();


    }

    private static int getRandomCrossoverLine() {
        int line = Main.getRandomInt(0, Main.GENES_COUNT - 2);
        return line;
    }

    protected Object clone() {
        Chromosome resultChromosome = new Chromosome();
        resultChromosome.setFitness(this.getFitness());
        resultChromosome.setLikelihood(this.getLikelihood());

        int resultGenes[] = new int[Main.GENES_COUNT];
        resultGenes = this.genes.clone();

        resultChromosome.setGenes(resultGenes);

        return resultChromosome;
    }


    public void method(String[] args) throws Exception {

        System.out.print(Integer.toBinaryString(2));

    }

}
