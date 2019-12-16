
package Server;

import java.io.BufferedReader;

public interface RequestProcessor {
    public void process(BufferedReader in);
}
