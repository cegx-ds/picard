package picard.illumina;

import htsjdk.samtools.util.IOUtil;
import htsjdk.samtools.metrics.MetricsFile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CollectIlluminaBasecallingMetricsTest {
    private static final File TEST_DATA_DIR = new File("testdata/picard/illumina/CollectIlluminaBasecallingMetrics");

    private File rootTestDir;

    @BeforeTest
    private void setUp() throws Exception {
        rootTestDir = File.createTempFile("cibm.", ".tmp");
        Assert.assertTrue(rootTestDir.delete());
        Assert.assertTrue(rootTestDir.mkdir());
        for (final File source : TEST_DATA_DIR.listFiles()) {
            if (source.isDirectory() && !source.isHidden()) {
                IOUtil.copyDirectoryTree(source, new File(rootTestDir.getPath(),source.getName()));
            }
        }
    }

    @AfterTest
    private void tearDown() {
        IOUtil.deleteDirectoryTree(rootTestDir);
    }

    @DataProvider(name="testIndexedRunLane1DataProvider")
    public Object[][] testIndexedRunLane1DataProvider() {
        return new Object[][]{{true}, {false}};
    }

    @Test(dataProvider="testIndexedRunLane1DataProvider")
    public void testIndexedRunLane1(final boolean useBarcodesDir) throws Exception {
        final File barcodesDir = (useBarcodesDir) ? new File(rootTestDir, "/25T8B25T/barcodes_dir") : null;
        final MetricsFile<IlluminaBasecallingMetrics, Integer> metricsFile = runIt(1, "25T8B25T",
                new File(rootTestDir, "25T8B25T/Data/Intensities/BaseCalls"), barcodesDir, true);
        final IlluminaBasecallingMetrics metric1 = metricsFile.getMetrics().get(0);
        Assert.assertEquals(metric1.LANE, "1");
        Assert.assertEquals(metric1.MOLECULAR_BARCODE_SEQUENCE_1, "AACAATGG");
        Assert.assertEquals(metric1.MOLECULAR_BARCODE_NAME, "tagged_117");
        Assert.assertEquals(metric1.MEAN_CLUSTERS_PER_TILE, 2.0);
        Assert.assertEquals(metric1.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric1.MEAN_PF_CLUSTERS_PER_TILE, 2.0);
        Assert.assertEquals(metric1.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric1.MEAN_PCT_PF_CLUSTERS_PER_TILE, 100.0);
        Assert.assertEquals(metric1.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric1.TOTAL_CLUSTERS, 2);
        Assert.assertEquals(metric1.TOTAL_CLUSTERS * 50, metric1.TOTAL_BASES);
        Assert.assertEquals(metric1.PF_BASES, metric1.TOTAL_BASES * metric1.PF_CLUSTERS / metric1.TOTAL_CLUSTERS);

        final IlluminaBasecallingMetrics metric2 = metricsFile.getMetrics().get(1);
        Assert.assertEquals(metric2.LANE, "1");
        Assert.assertEquals(metric2.MOLECULAR_BARCODE_SEQUENCE_1, "AACGCATT");
        Assert.assertEquals(metric2.MOLECULAR_BARCODE_NAME, "tagged_741");
        Assert.assertEquals(metric2.MEAN_CLUSTERS_PER_TILE, 3.0);
        Assert.assertEquals(metric2.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric2.MEAN_PF_CLUSTERS_PER_TILE, 2.0);
        Assert.assertEquals(metric2.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric2.MEAN_PCT_PF_CLUSTERS_PER_TILE, 66.67);
        Assert.assertEquals(metric2.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric2.TOTAL_CLUSTERS, 3);
        Assert.assertEquals(metric2.TOTAL_CLUSTERS * 50, metric2.TOTAL_BASES);
        Assert.assertEquals(metric2.PF_BASES, metric2.TOTAL_BASES * metric2.PF_CLUSTERS / metric2.TOTAL_CLUSTERS);

        final IlluminaBasecallingMetrics metric3 = metricsFile.getMetrics().get(2);
        Assert.assertEquals(metric3.LANE, "1");
        Assert.assertEquals(metric3.MOLECULAR_BARCODE_SEQUENCE_1, "ACAGGTAT");
        Assert.assertEquals(metric3.MOLECULAR_BARCODE_NAME, "tagged_375");
        Assert.assertEquals(metric3.MEAN_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(metric3.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric3.MEAN_PF_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(metric3.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric3.MEAN_PCT_PF_CLUSTERS_PER_TILE, 100.0);
        Assert.assertEquals(metric3.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric3.TOTAL_CLUSTERS, 1);
        Assert.assertEquals(metric3.TOTAL_CLUSTERS * 50, metric3.TOTAL_BASES);
        Assert.assertEquals(metric3.PF_BASES, metric3.TOTAL_BASES * metric3.PF_CLUSTERS / metric3.TOTAL_CLUSTERS);

        final IlluminaBasecallingMetrics metric4 = metricsFile.getMetrics().get(3);
        Assert.assertEquals(metric4.LANE, "1");
        Assert.assertEquals(metric4.MOLECULAR_BARCODE_SEQUENCE_1, "ACTAAGAC");
        Assert.assertEquals(metric4.MOLECULAR_BARCODE_NAME, "tagged_630");
        Assert.assertEquals(metric4.MEAN_CLUSTERS_PER_TILE, 2.0);
        Assert.assertEquals(metric4.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric4.MEAN_PF_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(metric4.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric4.MEAN_PCT_PF_CLUSTERS_PER_TILE, 50.00);
        Assert.assertEquals(metric4.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric4.TOTAL_CLUSTERS, 2);
        Assert.assertEquals(metric4.TOTAL_CLUSTERS * 50, metric4.TOTAL_BASES);
        Assert.assertEquals(metric4.PF_BASES, metric4.TOTAL_BASES * metric4.PF_CLUSTERS / metric4.TOTAL_CLUSTERS);

        final IlluminaBasecallingMetrics metric5 = metricsFile.getMetrics().get(4);
        Assert.assertEquals(metric5.LANE, "1");
        Assert.assertEquals(metric5.MOLECULAR_BARCODE_SEQUENCE_1, "AGCATGGA");
        Assert.assertEquals(metric5.MOLECULAR_BARCODE_NAME, "tagged_908");
        Assert.assertEquals(metric5.MEAN_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(metric5.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric5.MEAN_PF_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(metric5.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric5.MEAN_PCT_PF_CLUSTERS_PER_TILE, 100.0);
        Assert.assertEquals(metric5.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(metric5.TOTAL_CLUSTERS, 1);
        Assert.assertEquals(metric5.TOTAL_CLUSTERS * 50, metric5.TOTAL_BASES);
        Assert.assertEquals(metric5.PF_BASES, metric5.TOTAL_BASES * metric5.PF_CLUSTERS / metric5.TOTAL_CLUSTERS);

        final IlluminaBasecallingMetrics laneMetric = metricsFile.getMetrics().get(34);
        Assert.assertEquals(laneMetric.LANE, "1");
        Assert.assertNull(laneMetric.MOLECULAR_BARCODE_SEQUENCE_1);
        Assert.assertNull(laneMetric.MOLECULAR_BARCODE_NAME);
        Assert.assertEquals(laneMetric.MEAN_CLUSTERS_PER_TILE, 60.0);
        Assert.assertEquals(laneMetric.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PF_CLUSTERS_PER_TILE, 50.0);
        Assert.assertEquals(laneMetric.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PCT_PF_CLUSTERS_PER_TILE, 83.33);
        Assert.assertEquals(laneMetric.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.TOTAL_CLUSTERS, 60);
        Assert.assertEquals(laneMetric.TOTAL_CLUSTERS * 50, laneMetric.TOTAL_BASES);
        Assert.assertEquals(laneMetric.PF_BASES, laneMetric.TOTAL_BASES * laneMetric.PF_CLUSTERS / laneMetric.TOTAL_CLUSTERS);
    }

    @Test
    public void testNonIndexedRunLane1() throws Exception {
        final MetricsFile<IlluminaBasecallingMetrics, Integer> metricsFile = runIt(1, "125T125T",
                new File(rootTestDir, "125T125T/Data/Intensities/BaseCalls"), null, false);
        final IlluminaBasecallingMetrics laneMetric = metricsFile.getMetrics().get(0);

        Assert.assertEquals(laneMetric.LANE, "1");
        Assert.assertNull(laneMetric.MOLECULAR_BARCODE_SEQUENCE_1);
        Assert.assertNull(laneMetric.MOLECULAR_BARCODE_NAME);
        Assert.assertEquals(laneMetric.MEAN_CLUSTERS_PER_TILE, 2000.0);
        Assert.assertEquals(laneMetric.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PF_CLUSTERS_PER_TILE,1863.0);
        Assert.assertEquals(laneMetric.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PCT_PF_CLUSTERS_PER_TILE, 93.15);
        Assert.assertEquals(laneMetric.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.TOTAL_BASES, 500000);
        Assert.assertEquals(laneMetric.TOTAL_READS, 4000);
        Assert.assertEquals(laneMetric.PF_BASES, 465750);
        Assert.assertEquals(laneMetric.PF_READS, 3726);


        Assert.assertEquals(metricsFile.getMetrics().size(),1);
    }

    @Test
    public void testNovaseqIndexedRun() throws Exception {
        final MetricsFile<IlluminaBasecallingMetrics, Integer> metricsFile = runIt(1, "151T8B8B151T",
                new File("testdata/picard/illumina/151T8B8B151T_cbcl/Data/Intensities/BaseCalls"), null, true);
        final IlluminaBasecallingMetrics laneMetric = metricsFile.getMetrics().get(0);
        Assert.assertEquals(laneMetric.LANE, "1");
        Assert.assertEquals(laneMetric.MOLECULAR_BARCODE_SEQUENCE_1, "CACCTAGTACTCGAGT");
        Assert.assertEquals(laneMetric.MOLECULAR_BARCODE_NAME, "SA_CACCTAGTACTCGAGT");
        Assert.assertEquals(laneMetric.MEAN_CLUSTERS_PER_TILE, 1.0);
        Assert.assertEquals(laneMetric.SD_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PF_CLUSTERS_PER_TILE,1.0);
        Assert.assertEquals(laneMetric.SD_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.MEAN_PCT_PF_CLUSTERS_PER_TILE, 100.0);
        Assert.assertEquals(laneMetric.SD_PCT_PF_CLUSTERS_PER_TILE, 0.0);
        Assert.assertEquals(laneMetric.TOTAL_BASES, 302);
        Assert.assertEquals(laneMetric.TOTAL_READS, 2);
        Assert.assertEquals(laneMetric.PF_BASES, 302);
        Assert.assertEquals(laneMetric.PF_READS, 2);


        Assert.assertEquals(metricsFile.getMetrics().size(),6);
    }

    private MetricsFile<IlluminaBasecallingMetrics, Integer> runIt(final int lane, final String readStructure,
                                                                   final File basecallsDir, final File barcodesDir,
                                                                   final boolean isIndexed) throws Exception {
        final File metricsFile = File.createTempFile("cibm.", ".metrics");
        metricsFile.deleteOnExit();

        ArrayList<String> argsList = new ArrayList<>();
        argsList.add("BASECALLS_DIR=" + basecallsDir.getPath());
        if (null != barcodesDir) argsList.add("BARCODES_DIR=" + barcodesDir.getPath());
        argsList.add("LANE=" + lane);
        argsList.add("OUTPUT=" + metricsFile.getPath());

        if (readStructure != null) argsList.add("READ_STRUCTURE=" + readStructure);
        if (isIndexed) argsList.add("INPUT=" + new File(basecallsDir.getPath(),"barcodeData." + lane).getPath());

        final String[] args = new String[argsList.size()];
        argsList.toArray(args);

        Assert.assertEquals(new CollectIlluminaBasecallingMetrics().instanceMain(args),0);

        final MetricsFile<IlluminaBasecallingMetrics,Integer> retval = new MetricsFile<>();
        retval.read(new FileReader(metricsFile));
        return retval;
    }
}
