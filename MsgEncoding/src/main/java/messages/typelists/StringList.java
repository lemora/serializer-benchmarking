package messages.typelists;

import java.io.Serializable;
import java.util.List;

public class StringList implements Serializable {

    private List<String> values;

    public StringList(List<String> strings) {
        this.values = strings;
    }

    public StringList() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
