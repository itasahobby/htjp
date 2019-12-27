
package Server;

import java.io.BufferedReader;

public interface RequestProcessor {
    public String process(BufferedReader in);
}
