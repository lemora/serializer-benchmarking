package results;

import java.awt.geom.Rectangle2D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.jfree.chart.JFreeChart;
import org.jfree.fx.FXGraphics2D;
import serializer.SER_PROTOCOL;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import static results.BmResultsContainer.List_Type;
import static results.PlottingListtypes.plot_CompareSersize_container;
import static results.PlottingListtypes.plot_compareProtocols_container;
import static results.PlottingListtypes.plot_compareProtocols_container_X;
import static results.PlottingListtypes.plot_compareProtocols_ratio_container;
import static results.PlottingListtypes.plot_singleProtocol_container;

public class JMHChartPlotter extends Application {

    static class ChartCanvas extends Canvas {

        JFreeChart chart;

        private FXGraphics2D g2;

        public ChartCanvas(JFreeChart chart) {
            this.chart = chart;
            this.g2 = new FXGraphics2D(getGraphicsContext2D());
            // Redraw canvas when size changes.
            widthProperty().addListener(e -> draw());
            heightProperty().addListener(e -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();
            getGraphicsContext2D().clearRect(0, 0, width, height);
            this.chart.draw(this.g2, new Rectangle2D.Double(0, 0, width,
                    height));
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) { return getWidth(); }

        @Override
        public double prefHeight(double width) { return getHeight(); }
    }


    @Override
    public void start(Stage stage) throws IOException {
//        JFreeChart chart = plot_compareMachines_container(SER_PROTOCOL.AVRO_JSON, SER_MODE.SER, List_Type.STRING_LIST);
//        JFreeChart chart = plot_compareMachines_barchart_container(SER_PROTOCOL.PROTOBUF, SER_MODE.SER, List_Type.INT_LIST);
//        JFreeChart chart = plot_compareMachines_testobj(SER_PROTOCOL.PROTOSTUFF);
//        JFreeChart chart = plot_CompareSersize_pmpum();

//        JFreeChart chart = plot_singleProtocol_container(SER_PROTOCOL.PROTOSTUFF, SER_MODE.SER, "dot13-cpulock");
//        JFreeChart chart = plot_compareProtocols_container(SER_MODE.SER, BmResultsContainer.List_Type.DOUBLE_LIST, "dot13-cpulock");
//        JFreeChart chart = plot_compareProtocols_ratio_container(SER_MODE.SER, BmResultsContainer.List_Type.DOUBLE_LIST, "dot13-cpulock");
//        JFreeChart chart = plot_compareProtocols_container_X(SER_MODE.DESER, BmResultsContainer.List_Type.DOUBLE_LIST, "dot13-cpulock");
//        JFreeChart chart = plot_CompareSersize_container(Container_Type.INT_LIST);

//        JFreeChart chart = plot_singleProtocol_testobj(SER_PROTOCOL.AVRO, "dot13-cpulock");
        JFreeChart chart = PlottingPMPUM.plot_multiProtocol_pmpum("dot13-cpulock");
//        writeChartImgToFile(chart, "dcachey-avgspeed-400-400", 400, 400);

//        JFreeChart chart = plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW05);
//        JFreeChart chart = plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW05);

        // ---- other writing outs
//        writeChartImgToFile(chart, "dcachey-avgspeed-400-400", 400, 400);

// -- Generate Container plots
//        createIndividualCharts_container();
//        createComparisonChart_container();
//        createComparisonChart_ratio_container();
//        createComparisonChart_container_x();

// -- Generate Testobj plots
//        createIndividualCharts_testobj();
//        createComparisonChart_testobj();
//        createComparisonChart_testobj_x();

// -- Generate Machine Comparison plots
//        createComparisonChart_machines_testobj();

// -- Generate Sersize Comparison plots
//        createComparisonChart_sersize_container();
//        createComparisonChart_sersize_pmpum();

//        JFreeChart chart_msg_c5 = plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW05);
//        writeChartImgToFile(chart_msg_c5, "comparison-final-avgspeed-c5-400-400-yaxis75", 400, 400);
//        createComparisonChart_ratio_container();


//        ChartCanvas canvas = new ChartCanvas(chart_msg_c5);
        ChartCanvas canvas = new ChartCanvas(chart);
//        Canvas canvas = new Canvas();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(canvas);
        // Bind canvas size to stack pane size.
        canvas.widthProperty().bind( stackPane.widthProperty());
        canvas.heightProperty().bind( stackPane.heightProperty());
        stage.setScene(new Scene(stackPane));
        stage.setTitle("PlotterTest.java");
        stage.setWidth(500);
        stage.setMinWidth(500);
        stage.setHeight(500);
        stage.setMinHeight(500);
        stage.show();
//        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

// ------------------------------------
// ------ Helpers ------
// ------------------------------------

    private static void writeChartImgToFile(JFreeChart chart, String filename, int width, int height) throws IOException {
        ChartCanvas canvas = new ChartCanvas(chart);
//        BufferedImage chartImage = canvas.chart.createBufferedImage(1920,1080);
        BufferedImage chartImage = canvas.chart.createBufferedImage(width,height);

//        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//        String date = sdf.format( Calendar.getInstance().getTime() );

        File outputfile = new File("jmh-results/plot-images/" + filename + ".png");
        ImageIO.write(chartImage, "png", outputfile);
    }

    private static void createComparisonChart_sersize_pmpum() throws IOException {
        JFreeChart chart_compare_sersize_pmpum = PlottingPMPUM.plot_CompareSersize_pmpum();
        writeChartImgToFile(chart_compare_sersize_pmpum, "comparison-sersize-pmpum-400-400", 400, 400);
    }

    private static void createComparisonChart_sersize_container() throws IOException {
        JFreeChart chart_compare_sersize_intlist = plot_CompareSersize_container(List_Type.INT_LIST);
        writeChartImgToFile(chart_compare_sersize_intlist, "comparison-sersize-intlist-400-400", 400, 400);

        JFreeChart chart_compare_sersize_doublelist = plot_CompareSersize_container(List_Type.DOUBLE_LIST);
        writeChartImgToFile(chart_compare_sersize_doublelist, "comparison-sersize-doublelist-400-400", 400, 400);

        JFreeChart chart_compare_sersize_stringlist = plot_CompareSersize_container(List_Type.STRING_LIST);
        writeChartImgToFile(chart_compare_sersize_stringlist, "comparison-sersize-stringlist-400-400", 400, 400);
    }

    private static void createComparisonChart_machines_testobj() throws IOException {
        JFreeChart chart_compare_machines_protobuf = PlottingTestobj.plot_compareMachines_testobj(SER_PROTOCOL.PROTOBUF);
        writeChartImgToFile(chart_compare_machines_protobuf, "comparison-machines-avgspeed-testobj-protobuf-800-400", 900, 400);

        JFreeChart chart_compare_machines_protostuff = PlottingTestobj.plot_compareMachines_testobj(SER_PROTOCOL.PROTOSTUFF);
        writeChartImgToFile(chart_compare_machines_protostuff, "comparison-machines-avgspeed-testobj-protostuff-800-400", 900, 400);

        JFreeChart chart_compare_machines_hessian = PlottingTestobj.plot_compareMachines_testobj(SER_PROTOCOL.HESSIAN);
        writeChartImgToFile(chart_compare_machines_hessian, "comparison-machines-avgspeed-testobj-hessian-800-400", 900, 400);

        JFreeChart chart_compare_machines_kryo = PlottingTestobj.plot_compareMachines_testobj(SER_PROTOCOL.KRYO);
        writeChartImgToFile(chart_compare_machines_kryo, "comparison-machines-avgspeed-testobj-kryo-800-400", 900, 400);
    }

    private static void createComparisonChart_testobj() throws IOException {
        JFreeChart chart_msg_c0 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG);
        writeChartImgToFile(chart_msg_c0, "comparison-avgspeed-c0-400-400", 400, 400);

        JFreeChart chart_msg_c1 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW01);
        writeChartImgToFile(chart_msg_c1, "comparison-avgspeed-c1-400-400", 400, 400);

        JFreeChart chart_msg_c2 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW02);
        writeChartImgToFile(chart_msg_c2, "comparison-avgspeed-c2-400-400", 400, 400);

        JFreeChart chart_msg_c3 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW03);
        writeChartImgToFile(chart_msg_c3, "comparison-avgspeed-c3-400-400", 400, 400);

        JFreeChart chart_msg_c4 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW04);
        writeChartImgToFile(chart_msg_c4, "comparison-avgspeed-c4-400-400", 400, 400);

        JFreeChart chart_msg_c5 = PlottingTestobj.plot_compareProtocols_testobj("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW05);
        writeChartImgToFile(chart_msg_c5, "comparison-avgspeed-c5-400-400", 400, 400);
    }

    private static void createComparisonChart_testobj_x() throws IOException {
        JFreeChart chart_msg_c0 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG);
        writeChartImgToFile(chart_msg_c0, "comparison-final-avgspeed-c0-400-400", 400, 400);

        JFreeChart chart_msg_c1 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW01);
        writeChartImgToFile(chart_msg_c1, "comparison-final-avgspeed-c1-400-400", 400, 400);

        JFreeChart chart_msg_c2 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW02);
        writeChartImgToFile(chart_msg_c2, "comparison-final-avgspeed-c2-400-400", 400, 400);

        JFreeChart chart_msg_c3 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW03);
        writeChartImgToFile(chart_msg_c3, "comparison-final-avgspeed-c3-400-400", 400, 400);

        JFreeChart chart_msg_c4 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW04);
        writeChartImgToFile(chart_msg_c4, "comparison-final-avgspeed-c4-400-400", 400, 400);

        JFreeChart chart_msg_c5 = PlottingTestobj.plot_compareProtocols_testobj_x("dot13-cpulock", BmResultsTestobj.Testobj_Type.MSG_RW05);
        writeChartImgToFile(chart_msg_c5, "comparison-final-avgspeed-c5-400-400", 400, 400);
    }

    private static void createComparisonChart_ratio_container() throws IOException {
        JFreeChart chart_stringList_ser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.SER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_ser, "comparison-ratio-stringlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_stringList_deser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.DESER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_deser, "comparison-ratio-stringlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_intList_ser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.SER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_ser, "comparison-ratio-intlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_intList_deser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.DESER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_deser, "comparison-ratio-intlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_doubleList_ser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.SER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_ser, "comparison-ratio-doublelist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_doubleList_deser = plot_compareProtocols_ratio_container(BenchmarkResult.SER_MODE.DESER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_deser, "comparison-ratio-doublelist-avgspeed-deser-400-400", 400, 400);
    }

    private static void createComparisonChart_container() throws IOException {
        JFreeChart chart_stringList_ser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.SER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_ser, "comparison-stringlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_stringList_deser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.DESER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_deser, "comparison-stringlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_intList_ser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.SER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_ser, "comparison-intlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_intList_deser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.DESER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_deser, "comparison-intlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_doubleList_ser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.SER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_ser, "comparison-doublelist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_doubleList_deser = plot_compareProtocols_container(BenchmarkResult.SER_MODE.DESER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_deser, "comparison-doublelist-avgspeed-deser-400-400", 400, 400);
    }

    private static void createComparisonChart_container_x() throws IOException {
        JFreeChart chart_stringList_ser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.SER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_ser, "comparison-final-stringlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_stringList_deser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.DESER, List_Type.STRING_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_stringList_deser, "comparison-final-stringlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_intList_ser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.SER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_ser, "comparison-final-intlist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_intList_deser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.DESER, List_Type.INT_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_intList_deser, "comparison-final-intlist-avgspeed-deser-400-400", 400, 400);

        JFreeChart chart_doubleList_ser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.SER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_ser, "comparison-final-doublelist-avgspeed-ser-400-400", 400, 400);
        JFreeChart chart_doubleList_deser = plot_compareProtocols_container_X(BenchmarkResult.SER_MODE.DESER, List_Type.DOUBLE_LIST, "dot13-cpulock");
        writeChartImgToFile(chart_doubleList_deser, "comparison-final-doublelist-avgspeed-deser-400-400", 400, 400);
    }

    private static void createIndividualCharts_container() throws IOException {
        JFreeChart chart_jos_ser = plot_singleProtocol_container(SER_PROTOCOL.JAVA, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_jos_ser, "container-avgspeed-ser-jos-400-400", 400, 400);
        JFreeChart chart_jos_deser = plot_singleProtocol_container(SER_PROTOCOL.JAVA, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_jos_deser, "container-avgspeed-deser-jos-400-400", 400, 400);

        JFreeChart chart_protobuf_ser = plot_singleProtocol_container(SER_PROTOCOL.PROTOBUF, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_protobuf_ser, "container-avgspeed-ser-protobuf-400-400", 400, 400);
        JFreeChart chart_protobuf_deser = plot_singleProtocol_container(SER_PROTOCOL.PROTOBUF, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_protobuf_deser, "container-avgspeed-deser-protobuf-400-400", 400, 400);

        JFreeChart chart_protostuff_ser = plot_singleProtocol_container(SER_PROTOCOL.PROTOSTUFF, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_protostuff_ser, "container-avgspeed-ser-protostuff-400-400", 400, 400);
        JFreeChart chart_protostuff_deser = plot_singleProtocol_container(SER_PROTOCOL.PROTOSTUFF, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_protostuff_deser, "container-avgspeed-deser-protostuff-400-400", 400, 400);

        JFreeChart chart_avro_ser = plot_singleProtocol_container(SER_PROTOCOL.AVRO, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_avro_ser, "container-avgspeed-ser-avro-400-400", 400, 400);
        JFreeChart chart_avro_deser = plot_singleProtocol_container(SER_PROTOCOL.AVRO, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_avro_deser, "container-avgspeed-deser-avro-400-400", 400, 400);

        JFreeChart chart_avrojson_ser = plot_singleProtocol_container(SER_PROTOCOL.AVRO_JSON, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_avrojson_ser, "container-avgspeed-ser-avrojson-400-400", 400, 400);
        JFreeChart chart_avrojson_deser = plot_singleProtocol_container(SER_PROTOCOL.AVRO_JSON, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_avrojson_deser, "container-avgspeed-deser-avrojson-400-400", 400, 400);

        JFreeChart chart_fst_ser = plot_singleProtocol_container(SER_PROTOCOL.FST, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_fst_ser, "container-avgspeed-ser-fst-400-400", 400, 400);
        JFreeChart chart_fst_deser = plot_singleProtocol_container(SER_PROTOCOL.FST, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_fst_deser, "container-avgspeed-deser-fst-400-400", 400, 400);

        JFreeChart chart_kryo_ser = plot_singleProtocol_container(SER_PROTOCOL.KRYO, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_kryo_ser, "container-avgspeed-ser-kryo-400-400", 400, 400);
        JFreeChart chart_kryo_deser = plot_singleProtocol_container(SER_PROTOCOL.KRYO, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_kryo_deser, "container-avgspeed-deser-kryo-400-400", 400, 400);

        JFreeChart chart_hessian_ser = plot_singleProtocol_container(SER_PROTOCOL.HESSIAN, BenchmarkResult.SER_MODE.SER, "dot13-cpulock");
        writeChartImgToFile(chart_hessian_ser, "container-avgspeed-ser-hessian-400-400", 400, 400);
        JFreeChart chart_hessian_deser = plot_singleProtocol_container(SER_PROTOCOL.HESSIAN, BenchmarkResult.SER_MODE.DESER, "dot13-cpulock");
        writeChartImgToFile(chart_hessian_deser, "container-avgspeed-deser-hessian-400-400", 400, 400);
    }

    private static void createIndividualCharts_testobj() throws IOException {
        JFreeChart chart_jos_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.JAVA, "dot13-cpulock");
        writeChartImgToFile(chart_jos_ser, "testobj-avgspeed-jos-400-400", 400, 400);

        JFreeChart chart_protobuf_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.PROTOBUF, "dot13-cpulock");
        writeChartImgToFile(chart_protobuf_ser, "testobj-avgspeed-protobuf-400-400", 400, 400);

        JFreeChart chart_protostuff_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.PROTOSTUFF, "dot13-cpulock");
        writeChartImgToFile(chart_protostuff_ser, "testobj-avgspeed-protostuff-400-400", 400, 400);

        JFreeChart chart_avro_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.AVRO, "dot13-cpulock");
        writeChartImgToFile(chart_avro_ser, "testobj-avgspeed-avro-400-400", 400, 400);

        JFreeChart chart_avrojson_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.AVRO_JSON, "dot13-cpulock");
        writeChartImgToFile(chart_avrojson_ser, "testobj-avgspeed-avrojson-400-400", 400, 400);

        JFreeChart chart_fst_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.FST, "dot13-cpulock");
        writeChartImgToFile(chart_fst_ser, "testobj-avgspeed-fst-400-400", 400, 400);

        JFreeChart chart_kryo_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.KRYO, "dot13-cpulock");
        writeChartImgToFile(chart_kryo_ser, "testobj-avgspeed-kryo-400-400", 400, 400);

        JFreeChart chart_hessian_ser = PlottingTestobj.plot_singleProtocol_testobj(SER_PROTOCOL.HESSIAN, "dot13-cpulock");
        writeChartImgToFile(chart_hessian_ser, "testobj-avgspeed-hessian-400-400", 400, 400);
    }
}