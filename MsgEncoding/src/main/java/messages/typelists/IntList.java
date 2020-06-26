package messages.typelists;

import java.io.Serializable;
import java.util.List;

public class IntList implements Serializable {

    private List<Integer> values;

    public IntList(List<Integer> ints) {
        this.values = ints;
    }

    public IntList() {
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
