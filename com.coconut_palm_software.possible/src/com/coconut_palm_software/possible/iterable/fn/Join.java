package com.coconut_palm_software.possible.iterable.fn;

import static com.coconut_palm_software.possible.iterable.FluentIterable.*;
import static com.coconut_palm_software.possible.iterable.CollectionFactory.*;

import java.util.List;

import com.coconut_palm_software.possible.iterable.F2;

/**
 * Join multiple strings together with a separator string.
 *
 * @author dorme
 */
public class Join implements F2<String, String, String> {

    private final String separator;

    /**
     * Non-API constructor
     * @param separator
     */
    private Join(String separator) {
        this.separator = separator;
    }

    /* (non-Javadoc)
     * @see com.coconut_palm_software.possible.iterable.F2#apply(java.lang.Object, java.lang.Object)
     */
    @Override
    public String apply(String a, String b) {
        if ("".equals(a)) {
            return b;
        }
        return a + separator + b;
    }

    /**
     * Join an Iterable&lt;String&gt; source by concatenating its contents with the specified
     * separator string.
     *
     * @param source The source Iterable&lt;String&gt;.
     * @param separator The separator string.
     * @return The concatenated String.
     */
    public static String join(Iterable<String> source, String separator) {
        return iterateOver(source).reduce(new Join(separator), "");
    }

    public static void main(String[] args) {
        List<String> test = list("Hello", "world");
        String result = join(test, ", ");
        assert result.equals("Hello, world");
    }
}
