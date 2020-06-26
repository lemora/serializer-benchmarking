package messages.typelists;

import java.io.Serializable;
import java.util.List;

public class DoubleList implements Serializable {

    private List<Double> values;

    public DoubleList(List<Double> doubles) {
        this.values = doubles;
    }

    public DoubleList() {
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
